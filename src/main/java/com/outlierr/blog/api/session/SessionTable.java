package com.outlierr.blog.api.session;

import com.outlierr.blog.api.entity.User;
import com.outlierr.blog.infra.constant.RedisKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 为了保证服务端能够主动删除 Session，使用了 Redis 的 Set 来记录每个用户的 Session。
 * 该仓库使用 Redis 的 Set 来记录 UserId -> [SessionIds]。
 *
 * <h2>RedisTemplate 的返回值</h2>
 * SpringDataRedis 的脑残设计，只有事务下才会返回 null 但 RedisTemplate 无法区分也没有事务，所以这里直接忽略警告。
 */
@RequiredArgsConstructor
@SuppressWarnings("ConstantConditions")
@Component
public final class SessionTable {
    private final SessionRepository<?> sessionRepository;
    private final RedisTemplate<String, String> redisTemplate;

    public void addUser(int userId, String sessionId) {
        redisTemplate.boundSetOps(RedisKeys.USER_SESSION.of(userId)).add(sessionId);
    }

    public void clearSessionsOfUser(int userId) {
        String key = RedisKeys.USER_SESSION.of(userId);
        Set<String> members = redisTemplate.opsForSet().members(key);
        // 先取消链接 key 后面再异步删除所有 value
        redisTemplate.unlink(key);
        members.forEach(sessionRepository::deleteById);
    }

    /**
     * 会话如果过期，那么也没有办法及时清理，所以需要搞一个定时清理。
     */
    @Scheduled(fixedDelay = 24 * 60 * 60 * 1000)
    void cleanAccountRecords() {
        ScanOptions options = ScanOptions.scanOptions()
                .match(RedisKeys.USER_SESSION.of("*"))
                .build();
        RedisConnection conn = redisTemplate.getRequiredConnectionFactory().getConnection();
        Cursor<byte[]> users = conn.scan(options);

        while (users.hasNext()) {
            String recordSet = new String(users.next());
            Object[] invalid = redisTemplate.opsForSet().members(recordSet)
                    .stream()
                    .filter(id -> sessionRepository.findById(id) == null)
                    .toArray();
            if (invalid.length > 0) {
                redisTemplate.opsForSet().remove(recordSet, invalid);
            }
        }
    }
}

package com.outlierr.blog.infra.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.Nullable;
import org.springframework.web.cors.CorsConfiguration;

import java.time.Duration;
import java.util.List;

@ConfigurationProperties("app.cors")
@Getter
@Setter
public class CorsProperties {
    @Nullable
    private CorsTemplate template;

    /*
     * Origins 指定源列表，OriginPatterns 允许在其中使用通配符。
     *
     * 【全通配的区别】
     * 这两个都可以使用 "*" 作为值，表示允许所有源，但响应头上有区别 当 allowCredentials 为 true 时, 不允许使用 *
     * Origins 如果使用 "*"，则响应的 Allow-Origins 也是 "*"；
     * OriginPatterns 的话 响应的 Allow-Origins 会返回请求的 Origin，这允许 Allow-Credentials。
     */
    private List<String> allowedOrigins;
    private List<String> allowedOriginPatterns;

    private Boolean allowCredentials;

    private List<String> allowedMethods;
    private List<String> allowedHeaders;
    private List<String> exposedHeaders;

    /**
     * 设置缓存时间，浏览器通常有自己的限制（Firefox:86400，Chrome/Blink: 600）。
     */
    private Duration maxAge = Duration.ofDays(1);

    public enum CorsTemplate{
        /**
         * 将CORS配置为Spring中的默认状态。
         *
         * @see CorsConfiguration#applyPermitDefaultValues()
         */
        DEFAULT,

        /**
         * 将CORS配置为允许所有（Origin，Allowed-Headers，Method...），这些属性都设为"*"
         * 注意 Exposed-Headers 不支持通配而必须手动设置
         */
        ALLOW_ALL
    }
}

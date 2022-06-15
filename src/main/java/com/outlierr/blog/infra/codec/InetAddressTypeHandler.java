package com.outlierr.blog.infra.codec;

import com.outlierr.blog.infra.util.CodecUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;

public final class InetAddressTypeHandler extends BaseTypeHandler<InetAddress> {
    /**
     * 传递给 mybatis 的 InetAddress 类型将会被解析成 IPV6 字节数组存储到数据库
     * @param ps
     * @param i
     * @param address
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, InetAddress address, JdbcType jdbcType) throws SQLException {
        ps.setBytes(i, CodecUtils.toIPv6Bytes(address));
    }

    @Override
    public InetAddress getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return decode(rs.getBytes(columnName));
    }

    @Override
    public InetAddress getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return decode(rs.getBytes(columnIndex));
    }

    @Override
    public InetAddress getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return decode(cs.getBytes(columnIndex));
    }

    /**
     * 把字节数组转换为InetAddress，包装了下异常。
     *
     * @param bytes 字节数组
     * @return InetAddress
     * @throws SQLDataException 读取的数据不是IP地址
     */
    private static InetAddress decode(byte[] bytes) throws SQLDataException {
        try {
            // InetAddress.getByAddress() 能自动识别 IPv4-Mapped addresses
            return InetAddress.getByAddress(bytes);
        } catch (UnknownHostException e) {
            throw new SQLDataException("读取的数据不是IP地址", e);
        }
    }
}

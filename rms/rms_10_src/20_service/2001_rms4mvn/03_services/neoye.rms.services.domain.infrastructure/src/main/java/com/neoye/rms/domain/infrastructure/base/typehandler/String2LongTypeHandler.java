package com.neoye.rms.domain.infrastructure.base.typehandler;
/**
 * add by liuxy 2015-04-28 处理数据库中 int8,64位的数据类型
 */
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
@MappedTypes(String.class)
@MappedJdbcTypes(JdbcType.BIGINT)
public class String2LongTypeHandler extends BaseTypeHandler<String> {

	@Override
	public String getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return rs.getString(columnName);
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return rs.getString(columnIndex);
	}

	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return cs.getString(columnIndex);
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			String parameter, JdbcType jdbcType) throws SQLException {
		if (parameter!=null&&parameter.length()>0)
					ps.setLong(i, Long.parseLong(parameter));
		else 
		    ps.setNull(i, Types.BIGINT);
	}

}

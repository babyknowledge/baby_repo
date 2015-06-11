package com.neoye.rms.domain.infrastructure.base.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
@MappedTypes(Integer.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class Int2StringTypeHandler extends BaseTypeHandler<Integer> {

	@Override
	public Integer getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return rs.getInt(columnName);
	}

	@Override
	public Integer getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return rs.getInt(columnIndex);
	}

	@Override
	public Integer getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return cs.getInt(columnIndex);
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			Integer parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, String.valueOf(parameter));
	}
	


}

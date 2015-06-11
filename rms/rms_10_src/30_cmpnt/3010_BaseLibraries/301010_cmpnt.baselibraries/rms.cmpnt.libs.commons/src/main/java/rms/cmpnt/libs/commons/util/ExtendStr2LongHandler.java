package rms.cmpnt.libs.commons.util;

import java.sql.SQLException;
import java.sql.Types;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

public class ExtendStr2LongHandler implements TypeHandlerCallback{

	@Override
	public Object getResult(ResultGetter getter) throws SQLException {
		String result = null;
		if (!getter.wasNull() && null != getter.getObject()) {
			result = String.valueOf(getter.getLong());
		}
		return result;
	}

	@Override
	public void setParameter(ParameterSetter setter, Object parameter)
			throws SQLException {
		if (null == parameter) {
			setter.setNull(Types.INTEGER); 
		} else {
			setter.setLong(Long.valueOf((String) parameter));
		}
	}

	@Override
	public Object valueOf(String s) {
		return Long.valueOf(s);
	}
}

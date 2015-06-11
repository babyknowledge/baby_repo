package rms.cmpnt.libs.commons.util;

/*
 *    Copyright 2009-2012 The MyBatis Team
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
import java.sql.SQLException;
import java.sql.Types;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

public class ExtendIntStrHandler implements TypeHandlerCallback {

	@Override
	public Object getResult(ResultGetter getter) throws SQLException {
		String result = null;
		if (!getter.wasNull() && null != getter.getObject()) {
			result = String.valueOf(getter.getInt());
		}
		return result;
	}

	@Override
	public void setParameter(ParameterSetter setter, Object parameter)
			throws SQLException {
		if (null == parameter) {
			setter.setNull(Types.INTEGER); 
		} else {
			setter.setInt(Integer.valueOf((String) parameter));
		}
	}

	@Override
	public Object valueOf(String s) {
		return Integer.valueOf(s);
	}

}

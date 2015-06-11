package rms.cmpnt.libs.commons.proxy;

import java.util.HashMap;
import java.util.Map;

import rms.cmpnt.libs.commons.CodeConstant;

public class Message4ViewLayer extends HashMap {

	@Override
	public Object put(Object key, Object value) {
		if (key.toString().intern() == CodeConstant.MESSAGE_DATA) {
			super.clear();
			super.put(CodeConstant.MESSAGE_STATUS,
					CodeConstant.STATUS_CODE_DEFAULT);
			super.put(CodeConstant.MESSAGE_MSG, CodeConstant.MSG_DEFAULT);
		}
		return super.put(key, value);
	}
}

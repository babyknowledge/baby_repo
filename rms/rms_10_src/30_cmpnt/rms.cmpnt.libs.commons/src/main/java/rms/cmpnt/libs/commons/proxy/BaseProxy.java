package rms.cmpnt.libs.commons.proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rms.cmpnt.libs.commons.CodeConstant;
import rms.cmpnt.libs.commons.exception.BeanUtilException;
import rms.cmpnt.libs.commons.util.BeanUtil;

public abstract class BaseProxy {

	public Map<String, Object> getRtnMsg() {
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put(CodeConstant.MESSAGE_STATUS, CodeConstant.STATUS_CODE_DEFAULT);
		msg.put(CodeConstant.MESSAGE_MSG, CodeConstant.MSG_DEFAULT);
		return msg;
	}	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T toComplexDTO(Map m, Class<T> c) {
		T t = null;
		try {
			t = (T) BeanUtil.createObjectChaos(c, m);
		} catch (BeanUtilException e) {
			e.printStackTrace();
		}
		return t;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> toComplexDTO(List<Map> l, Class<T> c) {
		List<T> lst = null;
		for (Map m : l) {
			T t = null;
			try {
				t = (T) BeanUtil.createObjectChaos(c, m);
			} catch (BeanUtilException e) {
				e.printStackTrace();
			}
			if (t != null) {
				if (lst == null)
					lst = new ArrayList<T>();
				lst.add(t);
			}
		}
		return lst;
	}

	/**
	 * List<DTO> -> List<VO>
	 * 或 List<VO> -> List<DTO> 
	 */
	@SuppressWarnings("unchecked")
	public <T, K> List<K> beanToBeanListObject(List<T> t, Class<K> c) {
		if (t == null || t.size() == 0) {
			return null;
		}
		List<K> rs = new ArrayList<K>();
		try {
			for (T item : t) {
				K rsItem = (K) BeanUtil.createObject(c, BeanUtil.getObjectField(item));
				rs.add(rsItem);
			}
		} catch (BeanUtilException e) {
			e.printStackTrace();
		}
		return rs;
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> T toDTO(Map m, Class<T> c) {
        return BeanUtil.toDTO(m,c);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> List<T> toDTO(List<Map> l, Class<T> c) {
    	return BeanUtil.toDTO(l,c);
    }
    
    public Map<Object,Object> packageResData(Object ...objects){
        int loopLen = objects.length;
        if(objects.length % 2 !=0)
            loopLen =  objects.length - objects.length % 2;
        Map<Object,Object> resMap =  new HashMap<Object,Object>();
        for(int i = 0 ; i< loopLen ; i=i+2){
            resMap.put(objects[i], objects[i+1]);
        }
        return resMap;
     }
}

package rms.cmpnt.libs.commons.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import rms.cmpnt.libs.commons.exception.BeanUtilException;

/**
 * 
 * 项目名称: Netpatrol 5.1<br>
 * 模块名称: App Layer 采集处理平台<br>
 * 功能描述: <br>
 * 创建日期: 2013年8月27日 <br>
 * 版权信息: Copyright (c) 2013<br>
 * 公司信息: 东软集团股份有限公司 电信事业部-网管产品与系统部<br>
 * 
 * @author <a href="mailto: huangshk@neusoft.com">黄守凯</a>
 * @version v1.0
 * 
 *          <pre>
 * 修改历史
 *   序号      日期          修改人       修改原因
 *    1    2013年8月27日       黄守凯        创建
 * </pre>
 */
public class NMCollectionUtil {

	public static void fillingMapObject(Map<String, Object> destination, Map<String, Object> source) throws BeanUtilException {
		if (source == null || destination == null) {
			return;
		}
		Iterator<Entry<String, Object>> propertiesIter = source.entrySet().iterator();
		while (propertiesIter.hasNext()) {
			Entry<String, Object> entry = propertiesIter.next();
			if (entry.getValue() == null) {
				destination.put(entry.getKey(), null);
			} else {
				if (entry.getValue() instanceof List) {
					List list = (List)entry.getValue();
					List resultList = new ArrayList();
					for (Object tmp : list) {
						try {
							resultList.add(AssembleTools.obj2MapObj(tmp));
						}
						catch (BeanUtilException e) {
							e.printStackTrace();
						}
					}
					destination.put(entry.getKey(), resultList);
				} else if (entry.getValue() instanceof Map) {
					Map map = (Map)entry.getValue();
					Map<String, Object> newMap = new HashMap<String, Object>();
					Iterator<Map.Entry<String, Object>> iter = ((Map)map).keySet().iterator();
					while (iter.hasNext()) {
						Map.Entry<String, Object> elementsTmp = iter.next();
						Object valueTmp = elementsTmp.getValue();
						if (valueTmp == null) {
							continue;
						}
						newMap.put(elementsTmp.getKey(),AssembleTools.obj2MapObj(valueTmp));
					}
					destination.put(entry.getKey(), newMap);
				} else {
					destination.put(entry.getKey(), String.valueOf(entry.getValue()));
				}
			}
		}
	}
	
	public static void fillingMapStr(Map<String, String> destination, Map<String, Object> source) {
		if (source == null || destination == null) {
			return;
		}
		Iterator<Entry<String, Object>> propertiesIter = source.entrySet().iterator();
		while (propertiesIter.hasNext()) {
			Entry<String, Object> entry = propertiesIter.next();
			if (entry.getValue() == null) {
				destination.put(entry.getKey(), null);
			} else {
				destination.put(entry.getKey(), String.valueOf(entry.getValue()));
			}
		}
	}

	public static Map<String, String> fillingMapStr(Map<String, Object> source) {
		Map<String, String> destination = new HashMap<String, String>();
		fillingMapStr(destination, source);
		return destination;
	}
	
	public static Map<String, Object> fillingMapObj(Map<String, Object> source) throws BeanUtilException {
		Map<String, Object> destination = new HashMap<String, Object>();
		fillingMapObject(destination, source);
		return destination;
	}

	public static void fillingMapInt(Map<String, Integer> destination, Map<String, String> source) {
		if (source == null || destination == null) {
			return;
		}
		Iterator<Entry<String, String>> propertiesIter = source.entrySet().iterator();
		while (propertiesIter.hasNext()) {
			Entry<String, String> entry = propertiesIter.next();
			destination.put(entry.getKey(), Integer.valueOf(entry.getValue().toString()));
		}
	}
}

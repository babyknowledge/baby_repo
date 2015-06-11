package rms.cmpnt.libs.commons.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import rms.cmpnt.libs.commons.exception.BeanUtilException;

/**
 * 
 * 项目名称: Netpatrol产品<br>
 * 模块名称: 处理平台服务开发<br>
 * 功能描述: 配置文件内容到类对象转换工具<br>
 * 创建日期: 2013-6-20 <br>
 * 版权信息: Copyright (c) 2013<br>
 * 公司信息: 东软集团股份有限公司 电信事业部-网管产品与系统部<br>
 * 
 * @author <a href="mailto: huangshk@neusoft.com">黄守凯</a>
 * @version v1.0
 * 
 *          <pre>
 * 修改历史
 *   序号      日期          修改人       修改原因
 *    1    2012-12-19       刘勃宏        创建
 *    2    2013-6-20       黄守凯        重构
 * </pre>
 */
public final class BeanUtil {

	private BeanUtil() {
	}

	/**
	 * 根据传入的class类，创建对象。
	 * 
	 * 创建对象后，根据dataMap赋值object的属性
	 * 
	 * @param clazz
	 * @param dataMap
	 * @return
	 */
	public static Object createObject(Class<?> clazz, Map<String, Object> dataMap) throws BeanUtilException {
		try {
			Object obj = clazz.newInstance();
			fillObjectFields(dataMap, obj);
			return obj;
		}
		catch (InstantiationException e) {
			throw new BeanUtilException("Class is " + clazz.getName() + ": ", e);
		}
		catch (IllegalAccessException e) {
			throw new BeanUtilException("Class is " + clazz.getName() + ": ", e);
		}
		catch (InvocationTargetException e) {
			throw new BeanUtilException("Class is " + clazz.getName() + ": ", e);
		}
	}

	public static Object createObjectFromClazz(Class<?> clazz, Map<String, Object> dataMap) throws BeanUtilException {
		try {
			Object obj = clazz.newInstance();
			fillObjectFieldsFromObj(dataMap, obj);
			return obj;
		}
		catch (InstantiationException e) {
			throw new BeanUtilException("Class is " + clazz.getName() + ": ", e);
		}
		catch (IllegalAccessException e) {
			throw new BeanUtilException("Class is " + clazz.getName() + ": ", e);
		}
		catch (InvocationTargetException e) {
			throw new BeanUtilException("Class is " + clazz.getName() + ": ", e);
		}
	}

	/**
	 * 通过className，查找class类，创建对象。
	 * 
	 * 创建对象后，根据dataMap赋值object的属性
	 * 
	 * @param className 需要赋值的类名称
	 * @param dataMap 赋值数据
	 * @return 需要赋值对象
	 * @throws BeanUtilException
	 */
	public static Object createObject(String className, Map<String, Object> dataMap) throws BeanUtilException {
		try {
			Object obj = Class.forName(className).newInstance();
			fillObjectFields(dataMap, obj);
			return obj;
		}
		catch (InstantiationException e) {
			throw new BeanUtilException("Class is " + className + ": ", e);
		}
		catch (IllegalAccessException e) {
			throw new BeanUtilException("Class is " + className + ": ", e);
		}
		catch (InvocationTargetException e) {
			throw new BeanUtilException("Class is " + className + ": ", e);
		}
		catch (ClassNotFoundException e) {
			throw new BeanUtilException("Class is " + className + ": ", e);
		}
	}

	/**
	 * 填充传入对象的属性值，map的key为待填充的属性名称，value为属性值
	 * 
	 * @param object
	 * @param dataMap
	 * @return
	 * @throws BeanUtilException
	 */
	public static Object createObject(Object object, Map<String, Object> dataMap) throws BeanUtilException {
		try {
			fillObjectFields(dataMap, object);
			return object;
		}
		catch (IllegalAccessException e) {
			throw new BeanUtilException("Class is " + object.getClass().getName() + ": ", e);
		}
		catch (InvocationTargetException e) {
			throw new BeanUtilException("Class is " + object.getClass().getName() + ": ", e);
		}
	}

	private static <T> void forFileld(Field field, Map<String, T> dataMap, Object obj) throws IllegalAccessException, InvocationTargetException, InstantiationException, BeanUtilException {
		String name = field.getName();
		// System.out.println(obj.getClass().getName() + "=================" +
		// name);
		if (dataMap.containsKey(name)) {
			T elements = dataMap.get(name);
			
			if (elements == null) {
				BeanUtils.setProperty(obj, name, elements);
				return;
			}

			Class<?> filedCllazz = field.getType();
			if (filedCllazz.isPrimitive()) { // 判断是否为基本类型
				BeanUtils.setProperty(obj, name, elements);
				return;
			}

			if (filedCllazz.getName().startsWith("java.lang")) { // 返回field的类型全路径
				BeanUtils.setProperty(obj, name, elements);
				return;
			}

			if (filedCllazz.isAssignableFrom(List.class) || filedCllazz.isAssignableFrom(ArrayList.class)) {
				Type fc = field.getGenericType();
				if (fc == null) {
					return;
				}

				if (fc instanceof ParameterizedType) { // 如果是泛型参数的类型
					ParameterizedType pt = (ParameterizedType) fc;
					Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[0];
					BeanUtils.setProperty(obj, name, toDTO((List<Map>)elements, genericClazz));
					return;
				}
			}
			
			if (filedCllazz.isAssignableFrom(Map.class) || filedCllazz.isAssignableFrom(HashMap.class)) {
				Type fc = field.getGenericType();
				if (fc == null) {
					return;
				}

				if (fc instanceof ParameterizedType) { // 如果是泛型参数的类型
					ParameterizedType pt = (ParameterizedType) fc;
					Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[1];
					
					Map<String, Object> map = new HashMap<String, Object>();
					Iterator<Map.Entry<String, Object>> iter = ((Map)obj).keySet().iterator();
					while (iter.hasNext()) {
						Map.Entry<String, Object> elementsTmp = iter.next();
						Object valueTmp = elementsTmp.getValue();
						if (valueTmp == null) {
							continue;
						}
						map.put(elementsTmp.getKey(),transform(valueTmp,genericClazz));
					}
					BeanUtils.setProperty(obj, name, map);
					return;
				}
			}
			
			Class<?> tagClass = elements.getClass();
			if (filedCllazz.equals(tagClass)) {
				BeanUtils.setProperty(obj, name, elements);
			} else {
				if (tagClass.isAssignableFrom(Map.class) || tagClass.isAssignableFrom(HashMap.class)) {
					BeanUtils.setProperty(obj, name, createObjectFromClazz(filedCllazz,(Map<String, Object>)elements));
				} else {
					BeanUtils.setProperty(obj, name, toDTO((Map)elements,filedCllazz));
				}
			}
		}
	}

	private static <T> void fillObjectFieldsFromObj(Map<String, T> dataMap, Object obj) throws IllegalAccessException, InvocationTargetException, BeanUtilException {
		for (Field field : obj.getClass().getDeclaredFields()) {
			try {
				forFileld(field, dataMap, obj);
			}
			catch (InstantiationException e) {
				e.printStackTrace();
			}
		}
		
		Class<?> cl = obj.getClass().getSuperclass();
		while(cl != null) {
			for (Field field : cl.getDeclaredFields()) {
				try {
					forFileld(field, dataMap, obj);
				}
				catch (InstantiationException e) {
					e.printStackTrace();
				}
			}
			cl = cl.getSuperclass();
		}
	}

	private static <T> void fillObjectFields(Map<String, T> dataMap, Object obj) throws IllegalAccessException, InvocationTargetException {
		Iterator<Map.Entry<String, T>> iter = dataMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, T> elements = iter.next();
			String key = elements.getKey();
			if (elements.getValue() == null) {
				continue;
			}
			T value = elements.getValue();
			if (value.getClass().isPrimitive() || value.getClass().getName().startsWith("java.lang") || value.getClass().isAssignableFrom(List.class) || value.getClass().isAssignableFrom(ArrayList.class)) {
				BeanUtils.setProperty(obj, key, value);
			} else {
				try {
					Object oo = createObject(obj.getClass().getDeclaredField(key).getType(), BeanUtil.getObjectField(value));
					BeanUtils.setProperty(obj, key, oo);
				} catch (BeanUtilException e) {
					System.out.println(obj.getClass().getName());
					e.printStackTrace();
				} catch (SecurityException e) {
					System.out.println(obj.getClass().getName());
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					//System.out.println(obj.getClass().getName());
					//e.printStackTrace();
					BeanUtils.setProperty(obj, key, value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static <T, K> void fillObjectFields(T target, K source) throws IllegalAccessException, InvocationTargetException, IntrospectionException {
		BeanInfo beanInfo = Introspector.getBeanInfo(source.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			if (!key.equals("class")) {
				try {
					Method getter = property.getReadMethod();
					Object value = getter.invoke(source);
					BeanUtils.setProperty(target, key, value);
				}
				catch (Exception e) {
					continue;
				}
			}
		}
	}
	
	public static <T, K> void fillObjectFieldsNotNullSet(T target, K source) throws IllegalAccessException, InvocationTargetException, IntrospectionException {
		BeanInfo beanInfo = Introspector.getBeanInfo(source.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			if (!key.equals("class")) {
				try {
					Method getter = property.getReadMethod();
					Object value = getter.invoke(source);
					if (value == null) continue;
					BeanUtils.setProperty(target, key, value);
				}
				catch (Exception e) {
					continue;
				}
			}
		}
	}

	/**
	 * 根据传入的class类，填充类的静态属性。
	 * 
	 * @param clazz
	 * @param dataMap
	 * @throws BeanUtilException
	 */
	public static void fillClassStaticFields(Class<?> clazz, Map<String, String> dataMap) throws BeanUtilException {
		Field[] fields = clazz.getDeclaredFields();
		Map<String, Field> fieldMap = new HashMap<String, Field>();
		for (Field field : fields) {
			fieldMap.put(field.getName(), field);
		}
		Iterator<Map.Entry<String, String>> iter = dataMap.entrySet().iterator();
		try {
			while (iter.hasNext()) {
				Map.Entry<String, String> elements = iter.next();
				processStaticFields(clazz.getName(), fieldMap, elements);
			}
		}
		catch (IllegalAccessException e) {
			throw new BeanUtilException("Class is " + clazz.getName() + ": ", e);
		}
	}

	private static void processStaticFields(String className, Map<String, Field> fieldMap,
			Map.Entry<String, String> elements) throws IllegalAccessException, BeanUtilException {
		String key = elements.getKey();
		String value = elements.getValue();
		if (fieldMap.containsKey(key)) {
			Field field = fieldMap.get(key);
			if (field.getType().equals(Integer.TYPE)) {
				field.set(field.getName(), Integer.valueOf(value));
			}
			else if (field.getType().equals(java.lang.String.class)) {
				field.set(field.getName(), value);
			}
			else if (field.getType().equals(Boolean.TYPE)) {
				field.set(field.getName(), Boolean.valueOf(value));
			}
			else {
				throw new BeanUtilException("UNSUPPORT TYPE OF " + field.getType() + " ,Class is " + className);
			}
		}
	}

	/**
	 * 将传入类的静态属性填充到map中，map的key为类的属性名称，value为类的静态属性值
	 */
	public static Map<String, Object> getClassStaticFieldNames(Class<?> clazz) throws BeanUtilException {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				String name = field.getName();
				map.put(name, field.get(name));
			}
			return map;
		}
		catch (IllegalArgumentException e) {
			throw new BeanUtilException("Class is " + clazz.getName() + ": ", e);
		}
		catch (IllegalAccessException e) {
			throw new BeanUtilException("Class is " + clazz.getName() + ": ", e);
		}
	}

	public static Map<String, Object> getObjectField(Object object) throws BeanUtilException {
		if (object == null) {
			return null;
		}
		try {
			Map<String, Object> propertiesMap = new HashMap<String, Object>();
			BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (!key.equals("class")) {
					try {
						Method getter = property.getReadMethod();
						Object value = getter.invoke(object);
						propertiesMap.put(key, value);
					}
					catch (Exception e) {
						continue;
					}
				}
			}
			return propertiesMap;
		}
		catch (IllegalArgumentException e) {
			throw new BeanUtilException("Object is " + object + ": ", e);
		}
		catch (IntrospectionException e) {
			throw new BeanUtilException("Object is " + object + ": ", e);
		}
		catch (Exception e) {
			throw new BeanUtilException("Object is " + object + ": ", e);
		}
	}

	/**
	 * 复制source中的非空属性到target中，覆盖target中原有的属性；
	 * 
	 * @param target
	 * @param source
	 * @return
	 * @throws BeanUtilException
	 */
	public static Object paddingNNAttrs(Object target, Object source) throws BeanUtilException {
		Map<String, Object> sourceAttrs = getObjectField(source);
		Iterator<Map.Entry<String, Object>> iter = sourceAttrs.entrySet().iterator();
		try {
			while (iter.hasNext()) {
				Map.Entry<String, Object> elements = iter.next();
				String key = elements.getKey();
				Object value = elements.getValue();
				if (value == null) {
					continue;
				}
				BeanUtils.setProperty(target, key, value);
			}
			return target;
		}
		catch (IllegalAccessException e) {
			throw new BeanUtilException("Object is " + target + " and " + source + " : ", e);
		}
		catch (InvocationTargetException e) {
			throw new BeanUtilException("Object is " + target + " and " + source + " : ", e);
		}
	}

	/**
	 * 填充传入对象的属性值，map的key为待填充的属性名称，value为属性值
	 */
	public static <S> S fillObjectAttrsS(S object, Map<String, String> dataMap) throws BeanUtilException {
		try {
			fillObjectFields(dataMap, object);
			return object;
		}
		catch (IllegalAccessException e) {
			throw new BeanUtilException("Class is " + object.getClass().getName() + ": ", e);
		}
		catch (InvocationTargetException e) {
			throw new BeanUtilException("Class is " + object.getClass().getName() + ": ", e);
		}
	}

	/**
	 * 填充传入对象的属性值，map的key为待填充的属性名称，value为属性值
	 */
	public static <S> S fillObjectAttrsO(S object, Map<String, Object> dataMap) throws BeanUtilException {
		try {
			fillObjectFields(dataMap, object);
			return object;
		}
		catch (IllegalAccessException e) {
			throw new BeanUtilException("Class is " + object.getClass().getName() + ": ", e);
		}
		catch (InvocationTargetException e) {
			throw new BeanUtilException("Class is " + object.getClass().getName() + ": ", e);
		}
	}

	/**
	 * 将map转指定对象
	 * 
	 * @param m
	 * @param c
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T toDTO(Map m, Class<T> c) {
		T t = null;
		try {
			if (m == null)
				return t;
			t = (T) createObjectFromClazz(c, m);
		}
		catch (BeanUtilException e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 将list嵌套的map转指定对象list
	 * 
	 * @param m
	 * @param c
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> List<T> toDTO(List<Map> l, Class<T> c) {
		List<T> lst = new ArrayList<T>();
		if (l == null)
			return lst;
		for (Map m : l) {
			T t = null;
			try {
				if (m == null)
					continue;
				t = (T) createObjectFromClazz(c, m);
			}
			catch (BeanUtilException e) {
				e.printStackTrace();
			}
			if (t != null) {
				/*if (lst == null)
					lst = new ArrayList<T>();*/
				lst.add(t);
			}
		}
		return lst;
	}
	
	/**
	 * 将对象转成map
	 * 
	 * @param object
	 * @return
	 * @throws BeanUtilException
	 */
	public static Map<String, String> obj2Map(Object object) throws BeanUtilException {
		Map<String, Object> map = getObjectField(object);
		return NMCollectionUtil.fillingMapStr(map);
	}
	
	/**
	 * 将对象转成map（可以将对象中集合属性进行转换）
	 * 
	 * @param object
	 * @return
	 * @throws BeanUtilException
	 */
	public static Map<String, Object> obj2MapObj(Object object) throws BeanUtilException {
		Map<String, Object> map = getObjectField(object);
		return NMCollectionUtil.fillingMapObj(map);
	}
	
	public static <T> List<Map<String, String>> getListObjectFieldStr(List<T> objects) throws BeanUtilException {
		if (objects == null) return null;
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		for (Object object : objects) { 
			Map<String, Object> map = getObjectField(object);
			resultList.add(NMCollectionUtil.fillingMapStr(map));
		}
		return resultList;
	}
	
	public static <T> List<Map<String, String>> objList2ListMap(List<T> objects) throws BeanUtilException {
		return getListObjectFieldStr(objects);
	}

	/*
	 * flex
	 */
	/**
	 * 根据传入类的类型进行强转，适用于Flex→Java情况；
	 */
	public static <T> Object createObjectChaos(Class<?> clazz, Map<String, T> dataMap) throws BeanUtilException {
		try {
			Object obj = clazz.newInstance();
			fillObjectFieldsChaos(dataMap, obj);
			return obj;
		}
		catch (Exception e) {
			throw new BeanUtilException("Class is " + clazz.getName() + ": ", e);
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> void fillObjectFieldsChaos(Map<String, T> dataMap, Object obj) throws Exception {
		Map<String, Class<?>> types = getPropertiesType(obj.getClass());
		Iterator<Map.Entry<String, T>> iter = dataMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, T> elements = iter.next();
			String key = elements.getKey();
			// if ("kgs".equals(key)){
			// System.out.println("--------");
			// }
			Class<?> type = types.get(key);
			if (type == null) {
				continue;
			}
			if (elements.getValue() == null) {
				continue;
			}
			T value = elements.getValue();
			if (List.class.isAssignableFrom(value.getClass())) {
				processListType(obj, key, value);
			}
			else if ("int".equals(getClassNameByType(type))) {
				BeanUtils.setProperty(obj, key, value);
			}
			else if (Number.class.isAssignableFrom(type)) {
				BeanUtils.setProperty(obj, key, value);
			}
			else if (String.class.equals(type)) {
				BeanUtils.setProperty(obj, key, value);
			}
			else {
				Object newObj = type.newInstance();
				// fillObjectFieldsChaos(getObjectField(value), newObj);
				fillObjectFieldsChaos((Map<String, ?>) value, newObj);
				BeanUtils.setProperty(obj, key, newObj);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> void processListType(Object obj, String key, T value) throws Exception {
		Type listType = getListDeclaredField(obj, key);
		if (listType == null) {
			return;
		}
		List<?> vList = (List<?>) value;
		List<Object> rsList = new ArrayList<Object>();
		for (Object v : vList) {
			Object newObj = Class.forName(getClassNameByType(listType)).newInstance();
			fillObjectFieldsChaos((Map<String, ?>) v, newObj);
			rsList.add(newObj);
		}
		BeanUtils.setProperty(obj, key, rsList);
	}

	/**
	 * 获取class的属性类型。
	 * 
	 * private String name
	 * 
	 * 返回：key:name;value:java.lang.String
	 */
	private static Map<String, Class<?>> getPropertiesType(Class<?> clazz) throws IntrospectionException {
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
		PropertyDescriptor[] prs = beanInfo.getPropertyDescriptors();
		Map<String, Class<?>> rs = new HashMap<String, Class<?>>();
		for (int i = 0; i < prs.length; i++) {
			rs.put(prs[i].getName(), prs[i].getPropertyType());
		}
		return rs;
	}

	/**
	 * 返回List的泛型 参考：http://www.linuxidc.com/Linux/2012-06/62371.htm
	 */
	private static Type getListDeclaredField(Object obj, String key) throws SecurityException, NoSuchFieldException {
		Field f = obj.getClass().getDeclaredField(key);
		Type mapMainType = f.getGenericType();
		if (mapMainType instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) mapMainType;
			Type[] types = parameterizedType.getActualTypeArguments();
			if (types != null) {
				return types[0];
			}
		}
		return null;
	}

	private static String getClassNameByType(Type t) {
		String name = t.toString();
		if ("int".equals(name)) {
			return "int";
		}
		String[] s = t.toString().split(" ");
		if (s == null || s.length != 2) {
			return null;
		}
		return s[1];
	}
	
	private static <T, K> void forFileld(T t, K obj) throws IntrospectionException {
		for (Field field : obj.getClass().getDeclaredFields()) {
			String name = field.getName();
			BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String nameTmp = property.getName();
				if (!nameTmp.equals("class") && nameTmp.equals(name)) {
					try {
						Method getter = property.getReadMethod();
						Object value = getter.invoke(t);
						
						Class<?> filedCllazz = field.getType();
						if (filedCllazz.isPrimitive()) { // 判断是否为基本类型
							BeanUtils.setProperty(obj, name, value);
							break;
						} else if (filedCllazz.getName().startsWith("java.lang")) { // 返回field的类型全路径
							BeanUtils.setProperty(obj, name, value);
							break;
						} else if (filedCllazz.isAssignableFrom(List.class)) {
							Type fc = field.getGenericType();
							if (fc == null) {
								break;
							}

							if (fc instanceof ParameterizedType) { // 如果是泛型参数的类型
								ParameterizedType pt = (ParameterizedType) fc;
								Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[0];
								BeanUtils.setProperty(obj, name, transform((List)value, genericClazz));
								break;
							}
						} else if (filedCllazz.isAssignableFrom(Map.class)) {
							Type fc = field.getGenericType();
							if (fc == null) {
								break;
							}

							if (fc instanceof ParameterizedType) { // 如果是泛型参数的类型
								ParameterizedType pt = (ParameterizedType) fc;
								Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[1];
								
								Map<String, Object> map = new HashMap<String, Object>();
								Iterator<Map.Entry<String, Object>> iter = ((Map)obj).keySet().iterator();
								while (iter.hasNext()) {
									Map.Entry<String, Object> elements = iter.next();
									Object valueTmp = elements.getValue();
									if (valueTmp == null) {
										continue;
									}
									map.put(elements.getKey(),transform(valueTmp,genericClazz));
								}
								BeanUtils.setProperty(obj, name, map);
								break;
							}
						} 
						BeanUtils.setProperty(obj, name, value);
					}
					catch (Exception e) {
						continue;
					}
				}
			}
		}
	}
	
	public static <T, K> K transform(T t, Class<K> c) {
		try {
			K obj = c.newInstance();
	
			forFileld(t,obj);
			
			Class<?> cl = obj.getClass().getSuperclass();
			while(cl != null) {
				for (Field field : cl.getDeclaredFields()) {
					forFileld(t,obj);
				}
				cl = cl.getSuperclass();
			}
			return obj;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T, K> List<K> transform(List<T> t, Class<K> c) {
        if (t == null || t.size() == 0) {
            return null;
        }
        List<K> rs = new ArrayList<K>();
        for (T item : t) {
            K rsItem = transform(item,c);
            rs.add(rsItem);
        }
        return rs;
    }
	
	public static Map<String, String> getObjectFieldStr(Object object)
			throws BeanUtilException {
		Map<String, Object> map = getObjectField(object);
		return NMCollectionUtil.fillingMapStr(map);
	}

	public static void main(String[] args) throws Exception {
		// A a = new A();
		// List<B> bl = new ArrayList<B>();
		// B b1 = new B("B1");
		// B b2 = new B("B2");
		// bl.add(b2);
		// bl.add(b1);
		// C c = new C("C");
		// a.setBlist(bl);
		// a.setC(c);
		// Map<String, Object> map = getObjectField(a);
		// System.out.println(map);
		// System.out.println(createObjectChaos(FA.class, map));
		// System.out.println(getListDeclaredField(fa, "blist"));
	}

}

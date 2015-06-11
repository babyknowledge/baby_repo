package com.neoye.rms.domain.infrastructure.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rms.cmpnt.libs.commons.exception.BeanUtilException;
import rms.cmpnt.libs.commons.util.BeanUtil;

public class AssembleTools {

    /**
     * List<DTO> -> List<VO> 鎴�List<VO> -> List<DTO>
     */
    @SuppressWarnings("unchecked")
    public static <T, K> List<K> transform(List<T> t, Class<K> c) {
        if (t == null || t.size() == 0) {
            return null;
        }
        List<K> rs = new ArrayList<K>();
        try {
            for (T item : t) {
                K rsItem = (K) BeanUtil.createObject(c, BeanUtil.getObjectField(item));
                rs.add(rsItem);
            }
        }
        catch (BeanUtilException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * List<DTO> -> List<VO> 鎴�List<VO> -> List<DTO>
     */
    @SuppressWarnings("unchecked")
    public static <T, K> K transform(T t, Class<K> c) {
        if (t == null) {
            return null;
        }
        K item = null;
        try {
            item = (K) BeanUtil.createObject(c, BeanUtil.getObjectField(t));
        }
        catch (BeanUtilException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Object TO otherObject
     */
    @SuppressWarnings("unchecked")
    public static <T, K> T transform(T target, K source) {
        if (target == null || source == null) {
            return null;
        }
        try {
            BeanUtil.fillObjectFields(target, source);
        }catch (Exception e){
        	 e.printStackTrace();
        }
        return target;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> T toDTO(Map m, Class<T> c) {
        return BeanUtil.toDTO(m,c);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> List<T> toDTO(List<Map> l, Class<T> c) {
        return BeanUtil.toDTO(l,c);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> Map<String, String> obj2Map(Object object) throws BeanUtilException {
        return BeanUtil.obj2Map(object);
    }
    
}

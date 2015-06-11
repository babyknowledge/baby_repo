package com.neoye.rms.domain.infrastructure.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import rms.cmpnt.libs.commons.exception.ServiceException;

import com.neoye.rms.domain.infrastructure.constant.InfrConstant;

public class SessionUtil {

    /**
     * 根据关键字获取session中值
     * 
     * @param paramName
     * @return
     * @throws ServiceException
     */
    /*public static Object getObjectByParam(String paramName) throws ServiceException {
        //for test
        if (FlexContext.getHttpRequest()==null||FlexContext.getHttpRequest().getSession()==null) 
            return new HashMap();
        HttpSession session = FlexContext.getHttpRequest().getSession();
        Object rst = session.getAttribute(paramName);
        return rst;
    }*/

    /**
     * 获取session中的userInfo对象
     * 
     * @return
     * @throws ServiceException
     */
    /*public static Map<String, Object> getUserInfoMap() throws ServiceException {
        Map<String, Object> rst = (Map<String, Object>) getObjectByParam(InfrConstant.USER_INFO_KEY);
        return rst;
    }*/

    /**
     * 获取session中的UserId
     * 
     * @return
     * @throws ServiceException
     */
   /* public static String getUserId() throws ServiceException {
        Map<String, Object> userInfo = getUserInfoMap();
        String rst = (String) userInfo.get(InfrConstant.USER_ID_KEY);
        //for test
        if (rst ==null ||rst.length()==0)
            return "-1";
        return rst;
    }*/

    /**
     * 获取session中的userAccount
     * 
     * @return
     * @throws ServiceException
     */
    /*public static String getUserAccount() throws ServiceException {
        Map<String, Object> userInfo = getUserInfoMap();
        String rst = (String) userInfo.get(InfrConstant.USERACCOUNT_KEY);
        return rst;
    }*/

}

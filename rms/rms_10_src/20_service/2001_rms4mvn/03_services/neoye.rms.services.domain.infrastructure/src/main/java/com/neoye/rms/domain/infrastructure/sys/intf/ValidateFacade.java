package com.neoye.rms.domain.infrastructure.sys.intf;

import java.util.Map;

public interface ValidateFacade {
    /**
     * 判断一个业务数据的唯一性。
     * 
     * @param obj 待校验的数据对象
     * @param validateId 配置表中校验逻辑的ID
     * @return Map key="data" value=冲突记录的结果集<br>
     *         key="info" value=Map<key="属性名" value="属性中文描述">
     */
    public Map<String, Object> validateUnique(Object obj, String validateId);

    /**
     * 通用校验，不需要特殊处理。<br>
     * 
     * @param obj
     * @param validateId
     * @return 成功返回true,失败抛出异常。
     */
    // *add by liuxy 2015-04-30
    public boolean validateUniqueCommon(Object obj, String validateId);
    
    
    /**
     * 返回指定时间范围 数据状态 ---运行图&任务 "yyyy-MM-dd HH:mm:ss"
     * @param startStr
     * @param endStr
     * @return 数据状态  0 当前有效、1预设、2过期、-1格式异常
     * by  and@neusoft.com
     */
    public String  getDataStatus(String startStr,String endStr);
}

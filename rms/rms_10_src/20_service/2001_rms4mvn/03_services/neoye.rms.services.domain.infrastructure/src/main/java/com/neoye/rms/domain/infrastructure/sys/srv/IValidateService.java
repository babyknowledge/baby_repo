package com.neoye.rms.domain.infrastructure.sys.srv;

public interface IValidateService {
   
    
    /**
     * 
     * @param startStr
     * @param endStr
     * @return 数据状态  0 当前有效、1预设、2过期、-1格式异常
     */
    public String getDataStatus(String startStr, String endStr);

}

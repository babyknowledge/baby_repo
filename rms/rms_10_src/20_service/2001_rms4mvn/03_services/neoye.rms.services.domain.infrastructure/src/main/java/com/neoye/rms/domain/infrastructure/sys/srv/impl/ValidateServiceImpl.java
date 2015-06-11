package com.neoye.rms.domain.infrastructure.sys.srv.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import rms.cmpnt.libs.commons.util.DateUtil;

import com.neoye.rms.domain.infrastructure.constant.BizConstant;
import com.neoye.rms.domain.infrastructure.sys.srv.IValidateService;

@SuppressWarnings(value = { "all" })
@Service
public class ValidateServiceImpl implements IValidateService {

    @Override
    public String getDataStatus(String startStr, String endStr) {
   
            Date startDate = DateUtil.dateString(startStr);
            Date endDate = DateUtil.dateString(endStr);
            Date currDate =  DateUtil.dateString(DateUtil.dateToString(new Date(), DateUtil.DEF_DATE_FORMAT_STR));
           
            if(null==startDate||null==endDate||null==currDate){
                return "-1";
            }
             if(currDate.before(startDate)){
                 return BizConstant.DATA_STATUS_PREINSTALL;   //1、预设
             }else if(currDate.after(endDate)){
                 return BizConstant.DATA_STATUS_PASTDUE;  //2、过期
             }else{
                 return BizConstant.DATA_STATUS_EFFECTIVE;  //3、当前有效
             }
 
    }

}

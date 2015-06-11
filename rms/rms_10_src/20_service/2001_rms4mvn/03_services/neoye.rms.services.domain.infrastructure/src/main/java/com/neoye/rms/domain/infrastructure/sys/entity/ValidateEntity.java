package com.neoye.rms.domain.infrastructure.sys.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rms.cmpnt.libs.commons.exception.BeanUtilException;
import rms.cmpnt.libs.commons.exception.ServiceException;
import rms.cmpnt.libs.commons.util.BeanUtil;

import com.neoye.rms.domain.infrastructure.sys.repo.IValidateRepo;

@Service
public class ValidateEntity {
    private String validateGroupId;

    private String validateGroupDesc;

    private String validateTableName;

    private String remark;

    private List<ValidateItemEntity> validateItemEntityList;

    @Autowired
    private IValidateRepo validateRepo;

    public List<ValidateItemEntity> getValidateItemEntityList() {
        return validateItemEntityList;
    }

    public void setValidateItemEntityList(List<ValidateItemEntity> validateItemEntityList) {
        this.validateItemEntityList = validateItemEntityList;
    }

    public String getValidateGroupId() {
        return validateGroupId;
    }

    public void setValidateGroupId(String validateGroupId) {
        this.validateGroupId = validateGroupId;
    }

    public String getValidateGroupDesc() {
        return validateGroupDesc;
    }

    public void setValidateGroupDesc(String validateGroupDesc) {
        this.validateGroupDesc = validateGroupDesc;
    }

    public String getValidateTableName() {
        return validateTableName;
    }

    public void setValidateTableName(String validateTableName) {
        this.validateTableName = validateTableName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Map<String, Object> validateUnique(Object obj) {
        return validateUnique(obj, null);
    }

    public Map<String, Object> validateUnique(Object obj, String validateId) {

        ValidateEntity ent = null;
        if (validateId == null)
            ent = this;
        else
            ent = validateRepo.selectById(validateId);

        List<ValidateItemEntity> itemList = ent.getValidateItemEntityList();
        StringBuilder sb = new StringBuilder();
        sb.append("select * from ");
        sb.append(ent.getValidateTableName());
        sb.append(" where 1=1 ");
        Map infoMap = new HashMap();

        try {
            Map itemProMap = BeanUtil.getObjectField(obj);// 取得入参的值
            String primValue = null;
            for (ValidateItemEntity item : itemList) {
                if (item.getIsPrimkey().equals("1")) {// 如果是主键判断条件，在修改时要过滤掉本身，在新增时不增加此判断条件
                    primValue = (String) itemProMap.get(item.getCompareValue());
                    if (primValue == null || primValue.length() <= 0) {// 新增时，不增加此主键判断条件
                        continue;
                    }
                }                
                sb.append(item.getOperationKey()).append(" ");// and or
                if (item.getIsLeftBrackets().equals("1"))
                    sb.append("( ");
                sb.append(item.getColumeName()).append(" ");
                sb.append(item.getCompareKey()).append(" "); // 比较符
                                                             // =、like、>、<、>=、<=
                if (item.getCompareValueIsFix().equals("1"))// 常量
                    sb.append("\'").append(item.getCompareValue()).append("\'").append(" ");
                else {
                    sb.append("\'").append(itemProMap.get(item.getCompareValue())).append("\'").append(" ");
                    infoMap.put(item.getCompareValue(), item.getValidateInfo());
                }
                if (item.getIsRightBrackets().equals("1")) {
                    sb.append(" ) ");
                }
                if (item.getExpanStr() != null && item.getExpanStr().length() > 0)
                    sb.append(item.getExpanStr());// 加入自定义sql

            }
        }
        catch (BeanUtilException e) {
            throw new ServiceException(e.getMessage());
        }

        String sql = sb.toString();

        List<HashMap> list = validateRepo.validateUnique(sql);

        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("data", list);
        retMap.put("info", infoMap);
        return retMap;
    }
    
    /**
     *add by liuxy 2015-04-30 通用校验，不需要特殊处理，成功返回true,失败抛出异常。
     * 
     * @param obj
     * @param validateId
     * @return
     */    
    public boolean validateUniqueCommon(Object obj, String validateId) {
        Map validateMap = validateUnique(obj, validateId);
        List<Map> dataList = (List) validateMap.get("data");
        Map infoMap = (Map) validateMap.get("info");
        
        if (dataList==null||dataList.size()<=0){//通过
            return true;
        }
        else{
            throw new ServiceException(makeErrorInfo(obj, infoMap) + "等设置与原数据重复！");
        }
    }
    
    private String makeErrorInfo(Object obj, Map<String, Object> infoMap) {
        Map dataMap = new HashMap();
        try {
            dataMap = BeanUtil.getObjectField(obj);// 取得入参的值
        }
        catch (BeanUtilException e) {
        }

        StringBuilder retSb = new StringBuilder();
        String info = null;
        for (String key : infoMap.keySet()) {
            info = (String) infoMap.get(key);
            if (info != null && info.length() > 0)
                retSb.append(infoMap.get(key)).append("：").append(dataMap.get(key)).append(",\n");
        }
        return retSb.toString();
    }
}
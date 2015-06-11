package com.neoye.rms.domain.infrastructure.sys.entity;

public class ValidateItemEntity {
    private String validateItemId;

    private String validateGroupId;

    private String operationKey;

    private String isLeftBrackets;

    private String columeName;

    private String compareKey;

    private String compareValueIsFix;

    private String compareValue;

    private String isRightBrackets;

    private String orderNum;

    private String expanStr;
    
    private String validateInfo;
    
    private String isPrimkey;
    

    public String getIsPrimkey() {
        return isPrimkey;
    }

    public void setIsPrimkey(String isPrimkey) {
        this.isPrimkey = isPrimkey;
    }

    public String getValidateInfo() {
        return validateInfo;
    }

    public void setValidateInfo(String validateInfo) {
        this.validateInfo = validateInfo;
    }

    public String getValidateItemId() {
        return validateItemId;
    }

    public void setValidateItemId(String validateItemId) {
        this.validateItemId = validateItemId;
    }

    public String getValidateGroupId() {
        return validateGroupId;
    }

    public void setValidateGroupId(String validateGroupId) {
        this.validateGroupId = validateGroupId;
    }

    public String getOperationKey() {
        return operationKey;
    }

    public void setOperationKey(String operationKey) {
        this.operationKey = operationKey;
    }

    public String getIsLeftBrackets() {
        return isLeftBrackets;
    }

    public void setIsLeftBrackets(String isLeftBrackets) {
        this.isLeftBrackets = isLeftBrackets;
    }

    public String getColumeName() {
        return columeName;
    }

    public void setColumeName(String columeName) {
        this.columeName = columeName;
    }

    public String getCompareKey() {
        return compareKey;
    }

    public void setCompareKey(String compareKey) {
        this.compareKey = compareKey;
    }

    public String getCompareValueIsFix() {
        return compareValueIsFix;
    }

    public void setCompareValueIsFix(String compareValueIsFix) {
        this.compareValueIsFix = compareValueIsFix;
    }

    public String getCompareValue() {
        return compareValue;
    }

    public void setCompareValue(String compareValue) {
        this.compareValue = compareValue;
    }

    public String getIsRightBrackets() {
        return isRightBrackets;
    }

    public void setIsRightBrackets(String isRightBrackets) {
        this.isRightBrackets = isRightBrackets;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getExpanStr() {
        return expanStr;
    }

    public void setExpanStr(String expanStr) {
        this.expanStr = expanStr;
    }
}
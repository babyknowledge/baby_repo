package com.neoye.rms.domain.infrastructure.sys.intf.dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.neoye.rms.domain.infrastructure.base.BaseDTO;


public class DicDataDTO extends BaseDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 字典类别，如SNMP版本字典
	 */
	String dicType = null;

	/**
	 * 字典项编号
	 */
	String itemId = null;

	/**
	 * 字典项名称
	 */
	String itemName = null;

	/**
	 * 字典项数据
	 */
	String itemValue = null;

	/**
	 * 字典项描述
	 */
	String itemNote = null;
	
	/**
	 * 字典名称 百变表单使用 
	 */
	String label = null;
	
	/**
	 * 字典值 百变表单使用
	 */
	String value = null;

	/**
	 * 是否有效
	 */
	String inUse = "-1";

	 /**
     * 父ID
     */
    String parentId = null;
  
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
	
	public DicDataDTO() {
	}

	public DicDataDTO(String dicType) {
		this.dicType = dicType;
	}
	

	public String getDicType() {
		return dicType;
	}

	public void setDicType(String dicType) {
		this.dicType = dicType;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
		label = itemName;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
		value = itemValue;
	}

	public String getItemNote() {
		return itemNote;
	}

	public void setItemNote(String itemNote) {
		this.itemNote = itemNote;
	}

	public String getInUse() {
		return inUse;
	}

	public void setInUse(String inUse) {
		this.inUse = inUse;
	}
	
	

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	/**
	 * 规定顺序dictype,itemId,itemName,itemValue,itemNote
	 * @param params
	 * @return
	 */
	public static DicDataDTO createObject(String...params){
		if(params == null || params.length ==0)
			return new DicDataDTO();
		DicDataDTO newDto = new DicDataDTO();
		newDto.setDicType(params[0]);
		newDto.setItemId(params[1]);
		newDto.setItemName(params[2]);
		newDto.setItemValue(params[3]);
		newDto.setItemNote(params[4]);
		return newDto;
	}
	
}

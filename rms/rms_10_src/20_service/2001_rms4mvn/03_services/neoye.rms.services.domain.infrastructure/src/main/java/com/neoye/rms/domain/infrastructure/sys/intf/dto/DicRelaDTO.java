package com.neoye.rms.domain.infrastructure.sys.intf.dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.neoye.rms.domain.infrastructure.base.BaseDTO;

/**
* 项目名称: 广播监测网系统智能化升级项目<br>
* 模块名称: <br>
* 功能描述: <br>
* 创建日期: 2015年1月26日 <br>
* 版权信息: Copyright (c) 2015<br>
* 公司信息: 东软集团股份有限公司 电信事业部-网管产品与系统部<br> 
* @author <a href="mailto: yang_xg@neusoft.com">程序员1</a>
* @version v1.0
* <pre>
* 修改历史
*   序号      日期          修改人       修改原因
*    1    2015年1月26日       杨晓光       创建
* </pre>
 */
public class DicRelaDTO extends BaseDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主业务字典类别
	 */
	String masterDicType = null;

	/**
	 * 主字典项值
	 */
	String masterItemValue = null;
	/**
	 * 从业务字典类别
	 */
	String slaveDicType = null;

	

	public String getMasterDicType() {
		return masterDicType;
	}

	public void setMasterDicType(String masterDicType) {
		this.masterDicType = masterDicType;
	}

	
	public String getMasterItemValue() {
		return masterItemValue;
	}

	public void setMasterItemValue(String masterItemValue) {
		this.masterItemValue = masterItemValue;
	}

	public String getSlaveDicType() {
		return slaveDicType;
	}

	public void setSlaveDicType(String slaveDicType) {
		this.slaveDicType = slaveDicType;
	}

	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	/**
	 * 规定顺序 MasterDicType,MasterItemId,SlaveDicType,SlaveItemId
	 * @param params
	 * @return
	 */
	public static DicRelaDTO createObject(String...params){
		if(params == null || params.length ==0)
			return new DicRelaDTO();
		DicRelaDTO newDto = new DicRelaDTO();
		newDto.setMasterDicType(params[0]);
		newDto.setMasterItemValue(params[1]);
		newDto.setSlaveDicType(params[2]);
		return newDto;
	}
	
}

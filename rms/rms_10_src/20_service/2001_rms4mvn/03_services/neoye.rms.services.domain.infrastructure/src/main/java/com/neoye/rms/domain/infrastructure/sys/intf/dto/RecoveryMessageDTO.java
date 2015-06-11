package com.neoye.rms.domain.infrastructure.sys.intf.dto;

import com.neoye.rms.domain.infrastructure.base.BaseDTO;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("RecoveryMessageDTO")
public class RecoveryMessageDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3298376857394673310L;

	@XStreamAsAttribute
	@XStreamAlias("successType")
	private String successType; //返回成功失败类型
	
	@XStreamAsAttribute
	@XStreamAlias("successDicDesc")
	private String successDicDesc; //返回信息值

	public String getSuccessType() {
		return successType;
	}

	public void setSuccessType(String successType) {
		this.successType = successType;
	}

	public String getSuccessDicDesc() {
		return successDicDesc;
	}

	public void setSuccessDicDesc(String successDicDesc) {
		this.successDicDesc = successDicDesc;
	}
	
}

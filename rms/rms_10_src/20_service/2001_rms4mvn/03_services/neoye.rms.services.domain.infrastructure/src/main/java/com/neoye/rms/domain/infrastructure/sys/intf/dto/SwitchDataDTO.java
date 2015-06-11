package com.neoye.rms.domain.infrastructure.sys.intf.dto;

import com.neoye.rms.domain.infrastructure.base.BaseDTO;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("SwitchDataDTO")
public class SwitchDataDTO extends BaseDTO{

	private static final long serialVersionUID = 1L;

	@XStreamAsAttribute
	@XStreamAlias("id")
    private String id = null;

	@XStreamAsAttribute
	@XStreamAlias("switchType")
    private String switchType = null;

	@XStreamAsAttribute
	@XStreamAlias("switchValue")
    private String switchValue = null;

	@XStreamAsAttribute
	@XStreamAlias("inUse")
    private String inUse = "0";

	@XStreamAsAttribute
	@XStreamAlias("switchDesc")
    private String switchDesc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSwitchType() {
		return switchType;
	}

	public void setSwitchType(String switchType) {
		this.switchType = switchType;
	}

	public String getSwitchValue() {
		return switchValue;
	}

	public void setSwitchValue(String switchValue) {
		this.switchValue = switchValue;
	}

	public String getInUse() {
		return inUse;
	}

	public void setInUse(String inUse) {
		this.inUse = inUse;
	}

	public String getSwitchDesc() {
		return switchDesc;
	}

	public void setSwitchDesc(String switchDesc) {
		this.switchDesc = switchDesc;
	}

}

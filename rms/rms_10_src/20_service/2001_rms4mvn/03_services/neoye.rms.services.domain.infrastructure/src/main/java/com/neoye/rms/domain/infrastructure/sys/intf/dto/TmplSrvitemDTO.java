package com.neoye.rms.domain.infrastructure.sys.intf.dto;


public class TmplSrvitemDTO {

	private String tmplId;
	
	private String serverItemId;
	
	private String tmplType;
	
	private String inUse;

	public String getTmplId() {
		return tmplId;
	}

	public void setTmplId(String tmplId) {
		this.tmplId = tmplId;
	}

	public String getServerItemId() {
		return serverItemId;
	}

	public void setServerItemId(String serverItemId) {
		this.serverItemId = serverItemId;
	}

	public String getTmplType() {
		return tmplType;
	}

	public void setTmplType(String tmplType) {
		this.tmplType = tmplType;
	}

	public String getInUse() {
		return inUse;
	}

	public void setInUse(String inUse) {
		this.inUse = inUse;
	}

	
	
	/*public TmplSrvitemVO toTmplSrvItemVO(){
		TmplSrvitemVO tmplSrvItemVO = new TmplSrvitemVO();
		tmplSrvItemVO.setInUse(this.inUse);
		tmplSrvItemVO.setServerItemId(this.serverItemId);
		tmplSrvItemVO.setTmplId(this.tmplId);
		tmplSrvItemVO.setTmplType(this.tmplType);
		return tmplSrvItemVO;
	}*/
	
}

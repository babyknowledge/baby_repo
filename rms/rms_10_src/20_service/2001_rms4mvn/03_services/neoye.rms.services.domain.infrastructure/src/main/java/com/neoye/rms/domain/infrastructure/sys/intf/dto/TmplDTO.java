package com.neoye.rms.domain.infrastructure.sys.intf.dto;


public class TmplDTO {

	private String tmplId;
	
	private String tmplKey;
	
	private String tmplValue;
	
	public TmplDTO(){}
	
	public TmplDTO(String key) {
		this.tmplKey = key;
	}

	public String getTmplId() {
		return tmplId;
	}

	public void setTmplId(String tmplId) {
		this.tmplId = tmplId;
	}

	public String getTmplKey() {
		return tmplKey;
	}

	public void setTmplKey(String tmplKey) {
		this.tmplKey = tmplKey;
	}

	public String getTmplValue() {
		return tmplValue;
	}

	public void setTmplValue(String tmplValue) {
		this.tmplValue = tmplValue;
	}

	
	
	/*public void toTmplDTO(TmplVO tmplVO){
		this.tmplId = tmplVO.getTmplId();
		this.tmplKey = tmplVO.getTmplKey();
		this.tmplValue = tmplVO.getTmplValue();
	}
	
	public TmplVO toTmplVO(){
		TmplVO tmplVO = new TmplVO();
		tmplVO.setTmplId(this.tmplId);
		tmplVO.setTmplKey(this.tmplKey);
		tmplVO.setTmplValue(this.tmplValue);
		return tmplVO;
	}*/
	
}

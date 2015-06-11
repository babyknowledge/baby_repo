package com.neoye.rms.domain.infrastructure.sys.intf.dto;

import java.util.List;
import java.util.Map;

import rms.cmpnt.libs.commons.vo.poiutil.util.ImageBase64DTO;

import com.neoye.rms.domain.infrastructure.base.BaseDTO;

public class ActionURLParamDTO extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 640258431148428385L;
	
	/**
	 * 保存图片集合
	 */
	private List<ImageBase64DTO> imgList;
	
	/**
	 * 自定义传输对象
	 */
	private Map<String, Object> paramObj;
	
	/**
	 * 主键串 格式为：1,2,3
	 */
	private String ids;
	
	/**
	 * 执行方式
	 */
	private String type;

	public List<ImageBase64DTO> getImgList() {
		return imgList;
	}

	public void setImgList(List<ImageBase64DTO> imgList) {
		this.imgList = imgList;
	}

	public Map<String, Object> getParamObj() {
		return paramObj;
	}

	public void setParamObj(Map<String, Object> paramObj) {
		this.paramObj = paramObj;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

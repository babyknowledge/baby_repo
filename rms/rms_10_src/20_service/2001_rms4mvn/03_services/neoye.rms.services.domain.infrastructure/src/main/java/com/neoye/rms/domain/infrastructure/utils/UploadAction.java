package com.neoye.rms.domain.infrastructure.utils;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import rms.cmpnt.libs.commons.util.StringUtil;

import com.neoye.rms.domain.infrastructure.constant.InfrConstant;
import com.neoye.rms.domain.infrastructure.download.IExportExcel;
import com.neoye.rms.domain.infrastructure.sys.intf.DicDataFacade;
import com.neoye.rms.domain.infrastructure.sys.intf.dto.ActionURLParamDTO;
import com.neoye.rms.domain.infrastructure.sys.intf.dto.DicDataDTO;
import com.opensymphony.xwork2.ActionSupport;

@Service
public class UploadAction extends ActionSupport {

	@Autowired
	private ApplicationContext context;
	
	@Autowired
    private DicDataFacade dicFacade;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3739240695209924898L;

	public void getUploadStream() {
		String str = ServletActionContext.getRequest().getParameter("param");
		
		ActionURLParamDTO actionURLParamDTO = StringUtil.json2Bean2(str, ActionURLParamDTO.class);
		String type = actionURLParamDTO.getType();
		DicDataDTO dicParamsDto = DicDataDTO.createObject(InfrConstant.DIC_TYPE_DEF.EXPORT.value,null,null,type,null);
		dicParamsDto = dicFacade.getDicDataByValue(dicParamsDto);
		String ClassName = dicParamsDto.getItemName();

		try {
			if (ClassName != null && !"".equals(ClassName)) {
				Class<?> clazz = Class.forName(ClassName);
				IExportExcel exportExcel = (IExportExcel)context.getBean(clazz);
				
				ServletActionContext.getResponse().reset();
				//ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
				ServletActionContext.getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");
				ServletActionContext.getResponse().setHeader("Content-Disposition","attachment;filename=" + "abc" + ".xls");
				OutputStream output = ServletActionContext.getResponse().getOutputStream();
				actionURLParamDTO.getParamObj().put("output", output);
				exportExcel.exportExcel(actionURLParamDTO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

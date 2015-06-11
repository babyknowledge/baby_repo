package com.neoye.rms.domain.infrastructure.sys.intf.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoye.rms.domain.infrastructure.sys.intf.ITmplFacade;
import com.neoye.rms.domain.infrastructure.sys.intf.dto.TmplDTO;
import com.neoye.rms.domain.infrastructure.sys.intf.dto.TmplSrvitemDTO;
import com.neoye.rms.domain.infrastructure.sys.srv.TmplService;

@Service
public class TmplFacadeImpl implements ITmplFacade {

	@Autowired
	private TmplService tmplService;
	
	@Override
	public String getDesc4Tmpl(TmplDTO tmplDTO, Object obj) {
//		TmplVO tmplVO = tmplDTO.toTmplVO();
		return tmplService.getDesc4Tmpl(tmplDTO, obj);
	}

	@Override
	public int getTmplIdBySevItem(TmplSrvitemDTO tmplSrvitemDTO) {
//		TmplSrvitemVO tmplSrvitemVO = tmplSrvitemDTO.toTmplSrvItemVO();
		return tmplService.getTmplIdBySevItem(tmplSrvitemDTO);
	}

}

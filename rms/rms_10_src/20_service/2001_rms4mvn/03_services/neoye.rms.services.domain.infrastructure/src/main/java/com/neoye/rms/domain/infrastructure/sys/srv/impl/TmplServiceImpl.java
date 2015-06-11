package com.neoye.rms.domain.infrastructure.sys.srv.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rms.cmpnt.libs.commons.exception.ServiceException;

import com.neoye.rms.domain.infrastructure.sys.entity.TmplEntity;
import com.neoye.rms.domain.infrastructure.sys.intf.dto.TmplDTO;
import com.neoye.rms.domain.infrastructure.sys.intf.dto.TmplSrvitemDTO;
import com.neoye.rms.domain.infrastructure.sys.srv.TmplService;

@Service
public class TmplServiceImpl implements TmplService {

	@Autowired
	private TmplEntity tmplEntity;
	
	@Override
	public String getDesc4Tmpl(TmplDTO tmplDto, Object obj) throws ServiceException {
		return tmplEntity.getDescription4Tmpl(tmplDto, obj);
	}

	@Override
	public int getTmplIdBySevItem(TmplSrvitemDTO tmplSrvitemDto) throws ServiceException {
		return tmplEntity.getTmplIdBysevItem(tmplSrvitemDto);
	}

}

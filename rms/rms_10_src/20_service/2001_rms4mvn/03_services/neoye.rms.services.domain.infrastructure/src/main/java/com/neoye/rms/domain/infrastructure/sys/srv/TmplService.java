package com.neoye.rms.domain.infrastructure.sys.srv;

import rms.cmpnt.libs.commons.exception.ServiceException;

import com.neoye.rms.domain.infrastructure.sys.intf.dto.TmplDTO;
import com.neoye.rms.domain.infrastructure.sys.intf.dto.TmplSrvitemDTO;

public interface TmplService {

	public String getDesc4Tmpl(TmplDTO tmplDto, Object obj) throws ServiceException;
	
	public int getTmplIdBySevItem(TmplSrvitemDTO tmplSrvitemDto) throws ServiceException;
}

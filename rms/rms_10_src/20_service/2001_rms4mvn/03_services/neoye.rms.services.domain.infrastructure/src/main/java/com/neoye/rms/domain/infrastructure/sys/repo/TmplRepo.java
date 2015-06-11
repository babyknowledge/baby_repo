package com.neoye.rms.domain.infrastructure.sys.repo;

import com.neoye.rms.domain.infrastructure.sys.intf.dto.TmplDTO;
import com.neoye.rms.domain.infrastructure.sys.intf.dto.TmplSrvitemDTO;


public interface TmplRepo {

	public TmplDTO retrieveTemplate(TmplDTO tmplDto);
	
	public int retrieveTmplIdBySevItem(TmplSrvitemDTO tmplSrvitemDto);
}

package com.neoye.rms.domain.infrastructure.sys.intf;

import com.neoye.rms.domain.infrastructure.sys.intf.dto.TmplDTO;
import com.neoye.rms.domain.infrastructure.sys.intf.dto.TmplSrvitemDTO;



public interface ITmplFacade {

	public String getDesc4Tmpl(TmplDTO tmplDTO, Object obj);
	
	public int getTmplIdBySevItem(TmplSrvitemDTO tmplSrvitemDTO);
}

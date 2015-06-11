package com.neoye.rms.domain.system.intf.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoye.rms.domain.system.intf.ISystemFacade;
import com.neoye.rms.domain.system.intf.dto.UserDTO;
import com.neoye.rms.domain.system.srv.ISystemSrv;
@Service
public class SystemFacade implements ISystemFacade {
	
	@Autowired
	private ISystemSrv sysSrv;

	@Override
	public UserDTO getUserDTO(UserDTO user) {
		return sysSrv.getUserDTO(user);
	}

}

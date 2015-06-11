package com.neoye.rms.domain.system.srv.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoye.rms.domain.system.intf.dto.UserDTO;
import com.neoye.rms.domain.system.repo.ILoginRepo;
import com.neoye.rms.domain.system.srv.ISystemSrv;
@Service
public class SystemSrvImpl implements ISystemSrv {
	
	@Autowired
	private ILoginRepo loginRepo;

	@Override
	public UserDTO getUserDTO(UserDTO user) {
		return loginRepo.selectUserByUserPw(user);
	}

}

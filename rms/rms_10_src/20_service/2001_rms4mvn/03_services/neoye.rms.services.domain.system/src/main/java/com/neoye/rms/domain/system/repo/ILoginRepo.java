package com.neoye.rms.domain.system.repo;

import com.neoye.rms.domain.system.intf.dto.UserDTO;

public interface ILoginRepo {

	UserDTO selectUserByUserPw(UserDTO dto);
}

package com.neoye.rms.domain.infrastructure.sys.repo;

import java.util.List;

import rms.cmpnt.libs.commons.exception.ServiceException;

import com.neoye.rms.domain.infrastructure.sys.entity.DicDataEntity;
import com.neoye.rms.domain.infrastructure.sys.entity.SwitchDataEntity;
import com.neoye.rms.domain.infrastructure.sys.intf.dto.DicDataDTO;
import com.neoye.rms.domain.infrastructure.sys.intf.dto.DicRelaDTO;
import com.neoye.rms.domain.infrastructure.vo.ConditionVO;

public interface IDicDataRepo {


	SwitchDataEntity retrieveSwitchData(SwitchDataEntity switchDataEntity) throws ServiceException;
	
	List<SwitchDataEntity> retrieveSwitchDataIn(ConditionVO vo) throws ServiceException;
	
	List<DicDataEntity> retrieveDicData4DicDataList(DicDataEntity dicDataEntity) throws ServiceException;
	
	List<DicDataEntity> retrieveDicDataByRelaData(DicRelaDTO dicRelaDTO) throws ServiceException;
	
	List<DicDataEntity> retrieveSubDicDataByDicData(DicDataDTO dicDataDTO) throws ServiceException;
	
	Integer updateDicData(DicDataEntity dicDataEntity) throws ServiceException;
	
	
//	public DicDataDTO retrieveDicDataById(String id) throws ServiceException;
//	
}

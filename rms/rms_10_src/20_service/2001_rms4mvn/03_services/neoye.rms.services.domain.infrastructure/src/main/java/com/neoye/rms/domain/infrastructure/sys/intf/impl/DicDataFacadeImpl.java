package com.neoye.rms.domain.infrastructure.sys.intf.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rms.cmpnt.libs.commons.exception.ServiceException;

import com.neoye.rms.domain.infrastructure.sys.entity.DicDataEntity;
import com.neoye.rms.domain.infrastructure.sys.entity.SwitchDataEntity;
import com.neoye.rms.domain.infrastructure.sys.intf.DicDataFacade;
import com.neoye.rms.domain.infrastructure.sys.intf.dto.DicDataDTO;
import com.neoye.rms.domain.infrastructure.sys.intf.dto.DicRelaDTO;
import com.neoye.rms.domain.infrastructure.sys.intf.dto.SwitchDataDTO;
import com.neoye.rms.domain.infrastructure.utils.AssembleTools;

@Service
public class DicDataFacadeImpl implements DicDataFacade {

    @Autowired
    private DicDataEntity dicDataEntity;

    @Autowired
    private SwitchDataEntity switchDataEntity;

    @Override
    public Map<String, Object> getDicData4Map(DicDataDTO dicDataDTO) throws ServiceException {
        DicDataEntity entity = AssembleTools.transform(dicDataDTO, DicDataEntity.class);
        return dicDataEntity.getDicData4Map(entity);
    }

    @Override
    public SwitchDataDTO getSwitchDataByType(SwitchDataDTO switchDataDTO) {
        SwitchDataEntity entity = this.switchDTOToEnt(switchDataDTO);
        SwitchDataEntity retEnt = switchDataEntity.getSwitchData(entity);
        return this.switchEntityToDTO(retEnt);
    }
    
    
	@Override
	public SwitchDataDTO getSwitchDataByType(String type) {
		SwitchDataDTO swtchDTO = new SwitchDataDTO();
		swtchDTO.setSwitchType(type);
		return getSwitchDataByType(swtchDTO);
	}
    
    
    
    
    
    

    @Override
    public List<DicDataDTO> getDicData4List(DicDataDTO dicDataDTO) {
        DicDataEntity entity = this.dicDTOToEnt(dicDataDTO);
        List<DicDataEntity> retEntLs = dicDataEntity.getDicData4List(entity);
        return AssembleTools.transform(retEntLs, DicDataDTO.class);
    }

    @Override
    public List<DicDataDTO> getDicDataByRelaData(DicRelaDTO dicRelaDTO) {
        List<DicDataEntity> retEntLs = dicDataEntity.getDicDataByRelaData(dicRelaDTO);
        return AssembleTools.transform(retEntLs, DicDataDTO.class);
    }

    @Override
    public DicDataDTO getDicDataById(DicDataDTO dic) {
        DicDataEntity entity = this.dicDTOToEnt(dic);
        DicDataEntity retEnt = dicDataEntity.getDicDataById(entity);
        return dicEntityToDTO(retEnt);
    }

    @Override
    public List<DicDataDTO> getAllDicDatas() {
        List<DicDataEntity> retEntLs = dicDataEntity.getAllDicDatas();
        return AssembleTools.transform(retEntLs, DicDataDTO.class);
    }

    private DicDataDTO dicEntityToDTO(DicDataEntity ent) {
        return AssembleTools.transform(ent, DicDataDTO.class);
    }

    private SwitchDataDTO switchEntityToDTO(SwitchDataEntity ent) {
        return AssembleTools.transform(ent, SwitchDataDTO.class);
    }

    private DicDataEntity dicDTOToEnt(DicDataDTO dto) {
        return AssembleTools.transform(dto, DicDataEntity.class);
    }

    private SwitchDataEntity switchDTOToEnt(SwitchDataDTO dto) {
        return AssembleTools.transform(dto, SwitchDataEntity.class);
    }

    /**
     * 根据dic_type，item_value值获取dicDataEntity对象。
     */
    @Override
    public DicDataDTO getDicDataByValue(DicDataDTO dic) {

        DicDataEntity entity = dicDataEntity.getDicDataByValue(this.dicDTOToEnt(dic));
        return this.dicEntityToDTO(entity);
    }

    @Override
    public DicDataDTO getDicDataByValue(String dicType, String dicId) {
        DicDataDTO dto = new DicDataDTO();
        dto.setDicType(dicType);
        dto.setItemValue(dicId);
        return this.getDicDataByValue(dto);
    }

    @Override
    public List<DicDataDTO> getDicData4ListByType(String dicType) {
        DicDataDTO dto = new DicDataDTO();
        dto.setDicType(dicType);
        return getDicData4List(dto);
    }

	@Override
	public List<DicDataDTO> getSubDicData(DicDataDTO dicDataDTO) {
		return AssembleTools.transform(dicDataEntity.getSubDicDataByDicData(dicDataDTO), DicDataDTO.class);
	}

	@Override
	public Integer mdfDicData(DicDataDTO dicDataDTO) {
		DicDataEntity entity = AssembleTools.transform(dicDataDTO, DicDataEntity.class);
		return dicDataEntity.mdfDicData(entity);
	}



}

package com.neoye.rms.domain.infrastructure.sys.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import rms.cmpnt.libs.commons.exception.ServiceException;

import com.neoye.rms.domain.infrastructure.sys.intf.dto.SwitchDataDTO;
import com.neoye.rms.domain.infrastructure.sys.repo.IDicDataRepo;
import com.neoye.rms.domain.infrastructure.utils.AssembleTools;
import com.neoye.rms.domain.infrastructure.vo.ConditionVO;

@Service
public class SwitchDataEntity {

    @Autowired
    private IDicDataRepo dicDataRepo ;

    private String id = null;

    private String switchType = null;

    private String switchValue = null;

    private String inUse = "0";

    public String getSwitchValue() {
        return switchValue;
    }

    public void setSwitchValue(String switchValue) {
        this.switchValue = switchValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSwitchType() {
        return switchType;
    }

    public void setSwitchType(String switchType) {
        this.switchType = switchType;
    }

    public String getInUse() {
        return inUse;
    }

    public void setInUse(String inUse) {
        this.inUse = inUse;
    }

    @Cacheable(value = "DIC_CACHE", key = "#ent.switchType + 'getSwitchData'")
    public SwitchDataEntity getSwitchData(SwitchDataEntity ent) throws ServiceException {
        return dicDataRepo.retrieveSwitchData(ent);
    }

    @Cacheable(value = "DIC_CACHE", key = "#filter")
    public List<SwitchDataDTO> getSwitchDataIn(String filter) throws ServiceException {
    	ConditionVO vo = new ConditionVO();
    	vo.setFiltersSql(filter);
        return AssembleTools.transform(dicDataRepo.retrieveSwitchDataIn(vo),SwitchDataDTO.class);
    }
}

package com.neoye.rms.domain.infrastructure.sys.intf.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoye.rms.domain.infrastructure.sys.entity.ValidateEntity;
import com.neoye.rms.domain.infrastructure.sys.intf.ValidateFacade;
import com.neoye.rms.domain.infrastructure.sys.srv.IValidateService;

@Service
public class ValidateFacadeImpl implements ValidateFacade {

    @Autowired
    private ValidateEntity ent;
    
    @Autowired
    private IValidateService ivService;
    
    
    @Override
    public Map<String, Object> validateUnique(Object obj, String validateId) {
       return ent.validateUnique(obj, validateId);
    }


    @Override
    public boolean validateUniqueCommon(Object obj, String validateId) {
        return ent.validateUniqueCommon(obj, validateId);
    }


    @Override
    public String getDataStatus(String startStr, String endStr) {
        return ivService.getDataStatus(startStr, endStr);
    }

}

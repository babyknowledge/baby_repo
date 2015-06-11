package com.neoye.rms.domain.infrastructure.sys.repo;

import java.util.HashMap;
import java.util.List;

import com.neoye.rms.domain.infrastructure.sys.entity.ValidateEntity;
import com.neoye.rms.domain.infrastructure.sys.entity.ValidateItemEntity;

public interface IValidateRepo {

    ValidateEntity selectById(String validateGroupId);
    
    List<ValidateItemEntity> selectByValidateGroupId(String validateGroupId);
    
    List<HashMap> validateUnique(String sql);

}
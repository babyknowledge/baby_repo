package com.neoye.rms.domain.infrastructure.sys.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import rms.cmpnt.libs.commons.exception.ServiceException;

import com.neoye.rms.domain.infrastructure.sys.intf.dto.DicDataDTO;
import com.neoye.rms.domain.infrastructure.sys.intf.dto.DicRelaDTO;
import com.neoye.rms.domain.infrastructure.sys.repo.IDicDataRepo;

@Service
public class DicDataEntity{
	
    @Autowired
    private IDicDataRepo dicDataRepo;
    
    /**
     * 字典类别，如SNMP版本字典
     */
    String dicType = null;
    
    /**
     * 字典项编号
     */
    String itemId = null;
    
    /**
     * 字典项名称
     */
    String itemName = null;
    
    /**
     * 字典项数据
     */
    String itemValue = null;
    
    /**
     * 字典项描述
     */
    String itemNote = null;
    
    /**
     * 父ID
     */
    String parentId = null;

    /**
     * 是否有效
     */
    String inUse = "0";
  
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDicType() {
        return dicType;
    }

    public void setDicType(String dicType) {
        this.dicType = dicType;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
  
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemValue() {
        return itemValue;
    }
  
    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }
  
    public String getItemNote() {
        return itemNote;
    }
  
    public void setItemNote(String itemNote) {
        this.itemNote = itemNote;
    }
  
    public String getInUse() {
        return inUse;
    }
  
    public void setInUse(String inUse) {
        this.inUse = inUse;
    }

    @Cacheable(value = "DIC_CACHE", key = "#dicDataEntity.dicType + 'getDicData4Map'")
    public Map<String, Object> getDicData4Map(DicDataEntity DicDataEntity) throws ServiceException {
        Map<String, Object> result = new HashMap<String, Object>();
        List<DicDataEntity> DicDataEntitys = dicDataRepo.retrieveDicData4DicDataList(DicDataEntity);
        for (DicDataEntity ent : DicDataEntitys) {
            result.put(ent.getItemValue(), ent.getItemName());
        }
        return result;
    }
  
    @Cacheable(value = "DIC_CACHE", key = "#dicDataEntity.dicType + 'getDicData4List'")
    public List<DicDataEntity> getDicData4List(DicDataEntity dicDataEntity) throws ServiceException {
        return dicDataRepo.retrieveDicData4DicDataList(dicDataEntity);
    }
    
    public List<DicDataEntity> getDicDataByRelaData(DicRelaDTO dicRelaDTO) throws  ServiceException {
        return dicDataRepo.retrieveDicDataByRelaData(dicRelaDTO);
    }
    
    public List<DicDataEntity> getSubDicDataByDicData(DicDataDTO dicDataDTO) throws ServiceException {
    	return dicDataRepo.retrieveSubDicDataByDicData(dicDataDTO);
    }
  
    public DicDataEntity getDicDataById(DicDataEntity dicDataEntity) throws ServiceException {
        List<DicDataEntity> dicVols = this.getDicData4List(dicDataEntity);
        DicDataEntity retVo = null;
        if (null != dicVols || dicVols.size() > 0) {
            for (int i = 0; i < dicVols.size(); i++) {
                retVo = dicVols.get(i);
                if (retVo.getItemId() == dicDataEntity.getItemId()) {
                    return retVo;
                }
            }
        }
        return retVo;
//        throw new ServiceException("未发现字典数据！id=" + dicDataEntity.getItemId());
    }
    
    /**
     * 根据item_value值提取DicDataEntity对象
     * @param dicDataEntity
     * @return
     * @throws ServiceException
     */
    public DicDataEntity getDicDataByValue(DicDataEntity dicDataEntity) throws ServiceException {
        List<DicDataEntity> dicVols = this.getDicData4List(dicDataEntity);
        DicDataEntity retVo = null;

        if(dicVols == null || dicVols.size() == 0 ){
            return null;
//        	  throw new ServiceException("未发现字典数据！value=" + dicDataEntity.getItemValue());
        }
        for(DicDataEntity entity : dicVols){
        	if(!entity.getItemValue().equalsIgnoreCase(dicDataEntity.getItemValue()))
        		continue;
        	return entity;
        }
        return null;
//        throw new ServiceException("未发现字典数据！value=" + dicDataEntity.getItemValue());
    }
    
    public List<DicDataEntity> getAllDicDatas(){
        DicDataEntity dicDataEntity = new DicDataEntity();
        return dicDataRepo.retrieveDicData4DicDataList(dicDataEntity);
    }
    
    public Integer mdfDicData(DicDataEntity entity) {
    	return dicDataRepo.updateDicData(entity);
    }
}

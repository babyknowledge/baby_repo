package com.neoye.rms.domain.infrastructure.sys.intf;

import java.util.List;
import java.util.Map;

import com.neoye.rms.domain.infrastructure.sys.intf.dto.DicDataDTO;
import com.neoye.rms.domain.infrastructure.sys.intf.dto.DicRelaDTO;
import com.neoye.rms.domain.infrastructure.sys.intf.dto.SwitchDataDTO;

public interface DicDataFacade {

	/**
	 * 根据dicType取得一组字典数据
	 * @param dicDataVO
	 * @return Map key=字典ID ，value=字典值
	 */
	public Map<String, Object> getDicData4Map(DicDataDTO dicDataVO);
	
	/**
	 * 根据swichType取得开关数据
	 * @param switchDataVO
	 * @return
	 */
	public SwitchDataDTO getSwitchDataByType(SwitchDataDTO switchDataVO);
	
	
	 /**
     * 根据swichType取得开关数据(String)
     * @param type
     * @return
     */
    public SwitchDataDTO  getSwitchDataByType(String type);
	
	/**
	 * 根据dicType取得一组字典数据
	 * @param dicDataVO
	 * @return List<DicDataDTO>
	 */
	public List<DicDataDTO> getDicData4List(DicDataDTO dicDataVO);
	
	 /**
     * 通过级联关系获取字典数据
     * 
     * @param dicRelaDTO
     * @return List<DicDataDTO>
     */
    public List<DicDataDTO> getDicDataByRelaData(DicRelaDTO dicRelaDTO);
	
	/**
	 * 根据dicType及ID取得一个字典对象
	 * @param dic
	 * @return
	 */
	public DicDataDTO getDicDataById(DicDataDTO dic);
	

	/**
	 * 取得所有字典数据
	 * 
	 * @return
	 */
    public List<DicDataDTO> getAllDicDatas();
    
	
	/**
	 * 根据dicType，item_value获取DicData对象
	 * @param dic
	 * @return
	 */
	public DicDataDTO getDicDataByValue(DicDataDTO dic);
	
	/**
     * 根据dicType，item_value获取DicData对象
     * @param dic
     * @return
     */
    public DicDataDTO getDicDataByValue(String dicType,String dicValue);
    
    /**
     * 根据dicType获取DicData对象
     * @param dic
     * @return
     */
    public List<DicDataDTO> getDicData4ListByType(String dicType);
    
    /**
     * 根据一个字典表对象，查询该字典对应的子字段数据列表
     * @param dicDataDTO
     * @return
     */
    public List<DicDataDTO> getSubDicData(DicDataDTO dicDataDTO);
    
    /**
     * 修改字典表数据
     * @param dicDataDTO
     * @return
     */
    public Integer mdfDicData(DicDataDTO dicDataDTO);
    
    
	
}

package com.neoye.rms.domain.infrastructure.sys.intf;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rms.cmpnt.libs.commons.proxy.BaseProxy;

import com.neoye.rms.domain.infrastructure.sys.intf.dto.DicDataDTO;
import com.neoye.rms.domain.infrastructure.sys.intf.dto.DicRelaDTO;
import com.neoye.rms.domain.infrastructure.sys.intf.dto.SwitchDataDTO;

@Service
public class DicDataProxy extends BaseProxy {

    @Autowired
    private DicDataFacade dicDataFacade;

    /**
     * 获取字典数据
     * 
     * @param map
     * @return Map<String, Object>
     */
    public Object getDicData(Map<String, Object> map) {
        DicDataDTO dto = toDTO(map, DicDataDTO.class);
        return dicDataFacade.getDicData4List(dto);
    }

    /**
     * 根据dicType取得一组字典数据
     * 
     * @param dicDataVO
     * @return Map key=字典ID ，value=字典值
     */
    public Object getDicData4Map(String dicType) {
        DicDataDTO dto = new DicDataDTO();
        dto.setDicType(dicType);
        return dicDataFacade.getDicData4Map(dto);
    }
    
    /**
     * 通过级联关系获取字典数据
     * 
     * @param map
     * @return Map<String, Object>
     */
    public Object getDicDataByRelaData(Map<String, Object> args) {
    	DicRelaDTO dto = toDTO(args, DicRelaDTO.class);
        return dicDataFacade.getDicDataByRelaData(dto);
    }

    /**
     * 根据switchType取得开关数据
     * 
     * @param switchDataVO
     * @return SwitchDataDTO
     */
    public Object getSwitchData(String switchType) {
        SwitchDataDTO dto = new SwitchDataDTO();
        dto.setSwitchType(switchType);
        return dicDataFacade.getSwitchDataByType(dto);
//        Map<String, Object> msg = getRtnMsg();
//        try {
//            msg.put(CodeConstant.MESSAGE_DATA,dicDataFacade.getSwitchData(dto));
//        } catch (ServiceException e) {
//            e.printStackTrace();
//            msg.put(CodeConstant.MESSAGE_STATUS, CodeConstant.STATUS_CODE_ERROR);
//            msg.put(CodeConstant.MESSAGE_MSG, CodeConstant.MSG_ERROR);
//        } catch (Exception e) {
//            e.printStackTrace();
//            msg.put(CodeConstant.MESSAGE_STATUS, CodeConstant.STATUS_CODE_ERROR);
//            msg.put(CodeConstant.MESSAGE_MSG, CodeConstant.MSG_ERROR);
//        }
//        return msg;
    }

    /**
     * 根据dicType取得一组字典数据
     * 
     * @param dicDataVO
     * @return List<DicDataDTO>
     */
    public Object getDicData4List(String dicType) {
        DicDataDTO dto = new DicDataDTO();
        dto.setDicType(dicType);
        return dicDataFacade.getDicData4List(dto);
        
//        Map<String, Object> msg = getRtnMsg();
//        try {
//            msg.put(CodeConstant.MESSAGE_DATA,dicDataFacade.getDicData4List(dto));
//        } catch (ServiceException e) {
//            e.printStackTrace();
//            msg.put(CodeConstant.MESSAGE_STATUS, CodeConstant.STATUS_CODE_ERROR);
//            msg.put(CodeConstant.MESSAGE_MSG, CodeConstant.MSG_ERROR);
//        } catch (Exception e) {
//            e.printStackTrace();
//            msg.put(CodeConstant.MESSAGE_STATUS, CodeConstant.STATUS_CODE_ERROR);
//            msg.put(CodeConstant.MESSAGE_MSG, CodeConstant.MSG_ERROR);
//        }
//        return msg;
    }

    /**
     * 根据dicType及ID取得一个字典对象
     * 
     * @param dic
     * @return
     */
    public Object getDicDataById(String dicType, String dicId) {
        DicDataDTO dto = new DicDataDTO();
        dto.setDicType(dicType);
        dto.setItemId(dicId);
        return dicDataFacade.getDicDataById(dto);
    }
    
    /**
     * 取得所有字典数据
     * 
     * @return List<DicDataDTO>
     */
    public Object getAllDicDatas(){
        return dicDataFacade.getAllDicDatas();
    }
    
    /**
     * 根据一个字典表对象，查询该字典对应的子字段数据列表
     * @param dicDataMap
     * @return
     */
    public Object getSubDicData(Map dicDataMap) {
    	return dicDataFacade.getSubDicData(toDTO(dicDataMap, DicDataDTO.class));
    }
}

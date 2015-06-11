package rms.cmpnt.libs.commons.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 * 项目名称: 广播电视节目监测自动编目与检索系统<br>
 * 模块名称: 数据访问接口<br>
 * 功能描述: 由于权限管理服务目前尚没有DAO层的动态运行组件作为支撑，为权限管理服务与数据库访问的接口能够保持统一。
 * 暂时在本项目中定义了数据库访问的接口，此接口中定义的方法名称也是后续研发数据库访问组件的方法名称。<br>
 * 创建日期: Feb 4, 2013 <br>
 * 版权信息: Copyright (c) 2013<br>
 * 公司信息: 沈阳东软系统集成工程有限公司<br>
 * 
 * @author <a href="mailto: zh_yi@neusoft.com">张义</a>
 * @version v1.0
 * 
 * <pre>
 * 修改历史
 *   序号      日期          修改人       修改原因
 *    1    Feb 4, 2013        张义       创建
 * </pre>
 */
public interface BaseDAO {

    public Object add(String SqlId, Map map) throws  DataAccessException;

    public void addWithNoResult(String SqlId, Map map) throws  DataAccessException;

    public String addWithResult(String SqlId, Map map)  throws  DataAccessException;

    public int upd(String SqlId, Map map) throws  DataAccessException;

    public int del(String SqlId, Object id) throws  DataAccessException;

    public int del(String SqlId, Map map) throws  DataAccessException;

    public int count(String SqlId, Map map) throws  DataAccessException;

    public List searchAll(String SqlId) throws  DataAccessException;

    public List search(String SqlId, Map map) throws  DataAccessException;

    public Map getObject(String SqlId, Map map) throws  DataAccessException;
    
    /**
     * add by linjian@neusoft.com
     * Ibatis支持String类型的单个where条件查询接口
     * 
     * @param SqlId SqlMap查询标签
     * @param value 查询值
     * @return 返回数据对象
     * @throws DataAccessException
     */
//    public Map search(String SqlId,String value) throws DataAccessException;
    
    public void endTransaction();
    
    public void startTransaction();
    
    public void commitTransaction();
    
    public SqlMapClient getSqlMapClient() ;
}
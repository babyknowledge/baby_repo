package rms.cmpnt.libs.commons.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import rms.cmpnt.libs.commons.exception.BaseDataAccessException;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 * 项目名称: 广播电视节目监测自动编目与检索系统<br>
 * 模块名称: 数据访问实现类<br>
 * 功能描述: <br>
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
@Repository
public class BaseDAOImpl implements BaseDAO {

    // private SqlMapClient sqlMapClient = IbatisUtils.getSqlmap();
    private SqlMapClient sqlMapClient;

	
 
/*	private SqlMapClient getSqlMapClient() {
        if (sqlMapClient == null) {

            if (SpringDaoBeanFactoryUtil.getBeanFactory() == null) {
                sqlMapClient = IbatisUtils.getSqlmap();
            }

            else {
                if (SpringDaoBeanFactoryUtil.getBeanFactory().getBean("sqlMapClientFile") == null) {
                    sqlMapClient = IbatisUtils.getSqlmap();
                }
                else {
                    sqlMapClient = (SqlMapClient) SpringDaoBeanFactoryUtil.getBeanFactory()
                            .getBean("sqlMapClientFile");
                }
            }
        }
        return sqlMapClient;
    }*/

    public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public Object add(String SqlId, Map map) throws DataAccessException {
        try {
            return (Object) this.getSqlMapClient().insert(SqlId, map);
        }  catch (SQLException e) {
        	throw new BaseDataAccessException(e.getMessage(),e);
        }
    }

    public void startTransaction() throws DataAccessException {
        try {
            getSqlMapClient().startTransaction();
        }
        catch (SQLException e) {
        	throw new BaseDataAccessException(e.getMessage(),e);
        }
    }

    public void commitTransaction() throws DataAccessException {
        try {
            getSqlMapClient().commitTransaction();
        }
        catch (SQLException e) {
        	throw new BaseDataAccessException(e.getMessage(),e);
        }
    }

    public void endTransaction() throws DataAccessException {
        try {
            getSqlMapClient().endTransaction();
        }
        catch (SQLException e) {
        	throw new BaseDataAccessException(e.getMessage(),e);
        }
    }

    public void addWithNoResult(String SqlId, Map map) throws DataAccessException {
        try {
            this.getSqlMapClient().insert(SqlId, map);
        }catch (SQLException e) {
        	throw new BaseDataAccessException(e.getMessage(),e);
        }
    }

    public String addWithResult(String SqlId, Map map) throws DataAccessException {
        try {
            return (String) this.getSqlMapClient().insert(SqlId, map);
        }catch (SQLException e) {
        	throw new BaseDataAccessException(e.getMessage(),e);
        }
    }

    public int upd(String SqlId, Map map) throws DataAccessException {
        try {
            return this.getSqlMapClient().update(SqlId, map);
        }catch (SQLException e) {
        	throw new BaseDataAccessException(e.getMessage(),e);
        }
    }

    public int del(String SqlId, Object id) throws DataAccessException {
        try {
            return this.getSqlMapClient().delete(SqlId, id);
        }catch (SQLException e) {
        	throw new BaseDataAccessException(e.getMessage(),e);
        }
    }

    public int del(String SqlId, Map map) throws DataAccessException {
        try {
            return this.getSqlMapClient().delete(SqlId, map);
        }catch (SQLException e) {
        	throw new BaseDataAccessException(e.getMessage(),e);
        }
    }

    public int count(String SqlId, Map map) throws BaseDataAccessException {
        try {
            return Integer.parseInt((String) getSqlMapClient().queryForObject(SqlId, map));
        }catch (SQLException e) {
        	throw new BaseDataAccessException(e.getMessage(), e);
        }
    }

    public List search(String SqlId, Map map) throws DataAccessException {
        try {
            return (List) this.getSqlMapClient().queryForList(SqlId, map);
        }catch (SQLException e) {
        	throw new BaseDataAccessException(e.getMessage(), e);
        }
    }

    public Map getObject(String SqlId, Map map) throws DataAccessException {
        try {
            return (Map) getSqlMapClient().queryForObject(SqlId, map);
        }catch (SQLException e) {
        	throw new BaseDataAccessException(e.getMessage(), e);
        }
    }

    public List searchAll(String SqlId) throws DataAccessException {
        try {
            return (List) this.getSqlMapClient().queryForList(SqlId);
        }catch (SQLException e) {
        	throw new BaseDataAccessException(e.getMessage(), e);
        }
    }

    public Map<String, Object> queryForMap(String sqlId, Map queryMap, String keyField,
            String valueField) throws DataAccessException {
        try {
            return this.getSqlMapClient().queryForMap(sqlId, queryMap, keyField, valueField);
        }
        catch (SQLException e) {
            throw new BaseDataAccessException(e.getMessage(), e);
        }

    }

}

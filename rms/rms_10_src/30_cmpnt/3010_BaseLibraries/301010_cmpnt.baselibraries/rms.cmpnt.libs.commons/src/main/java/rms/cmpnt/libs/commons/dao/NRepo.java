package rms.cmpnt.libs.commons.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import rms.cmpnt.libs.commons.CodeConstant;
import rms.cmpnt.libs.commons.log.Log;

public class NRepo {

	static String SIGN = ".";

	static String SIGN_IMPL = "Impl";

	@Autowired
	@Qualifier(CodeConstant.BASE_DAO)
	protected BaseDAO baseDAO = null;

	protected Log log = Log.getClassLogger(NRepo.class);

	protected Object queryForObject() throws SQLException {
		return baseDAO.getSqlMapClient().queryForObject(getStandardSQLId());
	}

	protected Object queryForObject(Object obj) throws SQLException {
		return baseDAO.getSqlMapClient().queryForObject(getStandardSQLId(), obj);
	}

	protected Object queryForList(Object obj) throws SQLException {
		return baseDAO.getSqlMapClient().queryForList(getStandardSQLId(), obj);
	}

	protected Object queryForList() throws SQLException {
		return baseDAO.getSqlMapClient().queryForList(getStandardSQLId());
	}

	protected Object insert(Object obj) throws SQLException {
		return baseDAO.getSqlMapClient().insert(getStandardSQLId(), obj);
	}

	protected Object delete(Object obj) throws SQLException {
		return baseDAO.getSqlMapClient().delete(getStandardSQLId(), obj);
	}

	protected Object update(Object obj) throws SQLException {
		return baseDAO.getSqlMapClient().update(getStandardSQLId(), obj);
	}

	protected static String getStandardSQLId() {
		StackTraceElement[] stacks = new Throwable().getStackTrace();
		String className = stacks[2].getFileName();
		className = className.substring(0, className.indexOf(SIGN));
		if (className.endsWith(SIGN_IMPL)) {
			className = className.substring(0, className.length() - SIGN_IMPL.length());
		}
		String methodName = stacks[2].getMethodName();
		return className + SIGN + methodName;
	}

	protected static Map<String, Object> getParaMap(Object... objs) {
		Map<String, Object> rs = new HashMap<String, Object>();
		int size = objs.length / 2;
		for (int i = 0; i < size; i++) {
			rs.put((String) objs[2 * i], objs[2 * i + 1]);
		}
		return rs;
	}
}

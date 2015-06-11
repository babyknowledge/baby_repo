package rms.cmpnt.libs.commons.dao.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import rms.cmpnt.libs.commons.dao.BaseDAO;
import rms.cmpnt.libs.commons.dao.BaseDAOImpl;
public class DaoInstanceUtil {
	
	private static DaoInstanceUtil daoInstanceUtil;

    public DaoInstanceUtil getDaoInstanceUtil() {
		return daoInstanceUtil;
	}
	public void setDaoInstanceUtil(DaoInstanceUtil daoInstanceUtil) {
		DaoInstanceUtil.daoInstanceUtil = daoInstanceUtil;
	}

	private static DaoInstanceUtil daoInstance = null;
    
    private BaseDAO baseDAO = null;

    private DaoInstanceUtil() {
        if (baseDAO == null) {
            if (SpringDaoBeanFactoryUtil.getBeanFactory() == null) {
                baseDAO = new BaseDAOImpl();
            }
            else {
                if (SpringDaoBeanFactoryUtil.getBeanFactory().getBean("baseDAO") == null) {
                    baseDAO = new BaseDAOImpl();
                }
                else {
                    baseDAO = (BaseDAO) SpringDaoBeanFactoryUtil.getBeanFactory()
                            .getBean("baseDAO");
                }
            }
        }
    }

    public static DaoInstanceUtil getInstance() {
//        if (daoInstance == null) {
//            daoInstance = new DaoInstanceUtil();
//        }
        return daoInstanceUtil;
    }

    public BaseDAO getBaseDAO() {
        return baseDAO;
    }
    
    public void setBaseDAO(BaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }

}

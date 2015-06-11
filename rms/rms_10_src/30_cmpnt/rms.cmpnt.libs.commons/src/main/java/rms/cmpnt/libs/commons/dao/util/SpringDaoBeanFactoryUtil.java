package rms.cmpnt.libs.commons.dao.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class SpringDaoBeanFactoryUtil implements BeanFactoryAware{
    
    private static BeanFactory daoFactory;

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        SpringDaoBeanFactoryUtil.daoFactory = beanFactory;
    }

    // 定义为类静态方法
    public static BeanFactory getBeanFactory() {
        return SpringDaoBeanFactoryUtil.daoFactory;
    }

}

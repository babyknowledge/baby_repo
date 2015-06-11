package rms.cmpnt.libs.commons.log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
* 项目名称: NetPatrolNMSIT5.1<br>
* 模块名称: 获取有Logger注解的Log工具类对象句柄，并实例化Log工具类句柄<br>
* 功能描述: <br>
* 创建日期: 2013年7月17日 <br>
* 版权信息: Copyright (c) 2013<br>
* 公司信息: 沈阳东软系统集成工程有限公司<br> 
* @author <a href="mailto: linjian@neusoft.com"></a>
* @version v1.0
* <pre>
* 修改历史
*   序号      日期          修改人       修改原因
*    1    2013年7月17日     林剑  创建
* </pre>
*/
public class LogBeanPostProcessor implements BeanPostProcessor {   
    
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {   
        return bean;   
    }   
  
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {   
        List<Class<?>> clazzes = getAllClasses(bean);   
  
        for (Class<?> clazz : clazzes) {   
            initializeLog(bean, clazz);   
        }   
  
        return bean;   
    }   
  
    /**  
     * 取得指定bean的class以及所有父类的列表, 该列表排列顺序为从父类到当前类  
     * @param bean  
     * @return  
     */  
    private List<Class<?>> getAllClasses(Object bean) {   
        Class<? extends Object> clazz = bean.getClass();   
        List<Class<?>> clazzes = new ArrayList<Class<?>>();   
        while (clazz != null) {   
            clazzes.add(clazz);   
            clazz = clazz.getSuperclass();   
        }   
  
        Collections.reverse(clazzes);   
        return clazzes;   
    }   
  
    /**  
     * 对logger变量进行初始化  
     *   
     * @param bean  
     * @param clazz  
     */  
    private void initializeLog(Object bean, Class<? extends Object> clazz) {   
        Field[] fields = clazz.getDeclaredFields();   
        for (Field field : fields) {   
            if (field.getAnnotation(Logger.class) == null) {   
                continue;   
            }   
 
  
            if (!field.getType().isAssignableFrom(Log.class)) {   
                continue;   
            }   
  
            field.setAccessible(true); 
            //得到Logger注解
            Logger  annot = field.getAnnotation(Logger.class);
            //获取Logger注解的Type属性值
            String type = annot.type();
            try {   
        	//如果没有配置type，那么使用包路径的日志记录方式，否则使用模块常量向指定文件记录日志
        	if(type.equalsIgnoreCase("null"))
        	    field.set(bean, Log.getClassLogger(clazz));   
        	else
        	    field.set(bean, Log.getTypelogger(type));   
            } catch (Exception e) {   
                throw new BeanInitializationException(String   
                        .format("初始化logger失败!bean=%s;field=%s", bean, field));   
            }   
  
        }   
    }   
  
}

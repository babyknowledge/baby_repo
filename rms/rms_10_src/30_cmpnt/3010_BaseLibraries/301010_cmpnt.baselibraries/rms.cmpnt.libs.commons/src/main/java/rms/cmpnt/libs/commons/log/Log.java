package rms.cmpnt.libs.commons.log;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 项目名称: NetPatrolNMSIT5.1<br>
 * 模块名称: Log4j工具类<br>
 * 功能描述: 封装Log4j日志调用接口，同一日志文件使用单例句柄<br>
 * 创建日期: 2013年7月17日 <br>
 * 版权信息: Copyright (c) 2013<br>
 * 公司信息: 沈阳东软系统集成工程有限公司<br>
 * 
 * @author <a href="mailto: linjian@neusoft.com"></a>
 * @version v1.0
 * 
 *          <pre>
 * 修改历史
 *   序号      日期          修改人       修改原因
 *    1    2013年7月17日   林剑    创建
 * </pre>
 */
public class Log {

    //Log4j日志对象句柄
    private final Logger loger ;

    //句柄集合，用于控制使用同一文件时返回单例对象
    private final static Map<Object, Log> logerManger = new HashMap<Object, Log>();

    /**
     * 私有构造方法。使用包配置方式时，用此构造方法获取日志对象句柄
     * 
     * @coustructor
     */
    private Log(Class<?> clz) {
	loger = Logger.getLogger(clz);
    }
    /**
     * 私有构造方法。当向固定文件中写入时，用此构造方法获取日志对象句柄
     * 
     * @coustructor
     */
    private Log(String type) {
	loger = Logger.getLogger(type);
    }
    /**
     * 私有默认构造方法。获取写全局日志文件的对象句柄
     * 
     * @coustructor
     */
    private Log() {
	loger = Logger.getRootLogger();
    }
    /**
     * 获取日志工具类句柄接口方法，使用包路径配配置方式时调用此方法获取
     * 
     * @param clz 定义Log对象的Class
     * @return 日志工具类对象句柄
     */
    public static Log getClassLogger(Class<?> clz) {
	if (!logerManger.containsKey(clz)) {
	    logerManger.put(clz, new Log(clz));
	}
	return logerManger.get(clz);
    }
    /**
     * 获取日志工具类句柄接口方法，使用模块常量写日志时调用此方法获取
     * 
     * @param type 模块常量
     * @return 日志工具类对象句柄
     */
    public static Log getTypelogger(String type) {
	if (!logerManger.containsKey(type)) {
	    logerManger.put(type, new Log(type));
	}
	return logerManger.get(type);
    }
    /**
     * 记录INFO级别的日志
     * 
     * @param message 日志内容
     */
    public void info(String message) {
	loger.info(message);
    }
    /**
     * 记录INFO级别的日志
     * 
     * @param message 日志内容
     */
    public void info(String message, Throwable t) {
	loger.info(message, t);
    }
    /**
     * 记录ERROR级别的日志
     * 
     * @param message 日志内容
     */
    public void error(String message) {
	loger.error(message);
    }
    /**
     * 记录ERROR级别的日志
     * 
     * @param message 日志内容
     */
    public void error(String message, Throwable t) {
	loger.error(message, t);
    }
    /**
     * 记录DEBUG级别的日志
     * 
     * @param message 日志内容
     */
    public void debug(String message) {
	loger.debug(message);
    }
    /**
     * 记录DEBUG级别的日志
     * 
     * @param message 日志内容
     */
    public void debug(String message, Throwable t) {
	loger.debug(message, t);
    }
    /**
     * 记录FATAL级别的日志
     * 
     * @param message 日志内容
     */
    public void fatal(String message) {
	loger.fatal(message);
    }
    /**
     * 记录FATAL级别的日志
     * 
     * @param message 日志内容
     */
    public void fatal(String message, Throwable t) {
	loger.fatal(message, t);
    }
    /**
     * 记录WRAN级别的日志
     * 
     * @param message 日志内容
     */
    public void warn(String message) {
	loger.warn(message);
    }
    /**
     * 记录WRAN级别的日志
     * 
     * @param message 日志内容
     */
    public void warn(String message, Throwable t) {
	loger.warn(message, t);
    }
}

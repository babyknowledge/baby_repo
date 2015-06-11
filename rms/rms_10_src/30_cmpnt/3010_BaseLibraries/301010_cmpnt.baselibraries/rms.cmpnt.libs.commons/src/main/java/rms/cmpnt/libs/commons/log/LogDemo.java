package rms.cmpnt.libs.commons.log;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
* 项目名称: NetPatrolNMSIT5.1<br>
* 模块名称: 注解式日志记录Demo<br>
* 功能描述: Demo<br>
* 创建日期: 2013年7月17日 <br>
* 版权信息: Copyright (c) 2013<br>
* 公司信息: 沈阳东软系统集成工程有限公司<br> 
* @author <a href="mailto: linjian@neusoft.com"></a>
* @version v1.0
* <pre>
* 修改历史
*   序号      日期          修改人       修改原因
*    1    2013年7月17日       林剑     创建
* </pre>
*/
public class LogDemo {
    
   
    /**
     * 在moudleAlarmLog日志工具类对象句柄上定义 @Logger(type=LogConstants.MOUDLE_ALARM)，表示向LogConstants.MOUDLE_ALARM对应的日志文件中记录，对应的Log4j配置如下：
     * log4j.appender.MOUDLE_ALARM=org.apache.log4j.RollingFileAppender
        log4j.appender.MOUDLE_ALARM.layout=org.apache.log4j.PatternLayout
        log4j.appender.MOUDLE_ALARM.layout.ConversionPattern=%d [%t] %-5p %c - %m%n
        log4j.appender.MOUDLE_ALARM.File=${webapp.root}/logs/alarm/alarm.log
        log4j.appender.MOUDLE_ALARM.MaxFileSize=1MB 
        log4j.appender.MOUDLE_ALARM.MaxBackupIndex=10
        
        log4j.logger.MOUDLE_ALARM=info,MOUDLE_ALARM
     */
    @Logger(type=LogConstants.MOUDLE_ALARM)
    private Log moudleAlarmLog ;
    /**
     * 在moudleAlarmLog日志工具类对象句柄上定义 @Logger，表示LogDemo对象所在包 com.neusoft.np.util.log下所有类的日志记录到指定的文件中，对应的Log4j配置如下：
     * com.neusoft.np.util.log=INFO,log
     * 
     * log4j.appender.log=org.apache.log4j.RollingFileAppender
        log4j.appender.log.layout=org.apache.log4j.PatternLayout
        log4j.appender.log.layout.ConversionPattern=%d [%t] %-5p %c - %m%n
        log4j.appender.log.File=${webapp.root}/logs/log/log.log
        log4j.appender.log.MaxFileSize=1MB 
        log4j.appender.log.MaxBackupIndex=10
     * 
     */
    @Logger
    private Log log;
    
    public void doLog(){
	moudleAlarmLog.info("moudleAlarmLog info");
	moudleAlarmLog.debug("moudleAlarmLog debug");
	moudleAlarmLog.error("moudleAlarmLog error");
	moudleAlarmLog.warn("moudleAlarmLog warn");
	moudleAlarmLog.fatal("moudleAlarmLog fatal");
	
	log.info("log info");
	log.debug("log debug");
	log.error("log error");
	log.warn("log warn");
	log.fatal("log fatal");
    }

    public static void main(String[] args) {   
	
	PropertyConfigurator.configure(LogDemo.class.getClassLoader().getResource("config/common/properties/log4j.properties"));
	
	ApplicationContext ctxapp = new ClassPathXmlApplicationContext(
		    "config/spring/applicationContext_np_cmpnt_suprfm.xml");
	LogDemo demo = (LogDemo) ctxapp.getBean("LogDemo");
	demo.doLog();

    }   
  

}

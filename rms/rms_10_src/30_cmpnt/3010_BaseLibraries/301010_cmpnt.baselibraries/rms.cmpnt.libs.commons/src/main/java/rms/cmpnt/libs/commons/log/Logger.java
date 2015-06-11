package rms.cmpnt.libs.commons.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
* 项目名称: NetPatrolNMSIT5.1<br>
* 模块名称: 注解<br>
* 功能描述: Logger注解，在Log工具类对象句柄上标注，在Spring启动扫描时为对象句柄实例化<br>
* 创建日期: 2013年7月17日 <br>
* 版权信息: Copyright (c) 2013<br>
* 公司信息: 沈阳东软系统集成工程有限公司<br> 
* @author <a href="mailto: linjian@neusoft.com"></a>
* @version v1.0
* <pre>
* 修改历史
*   序号      日期          修改人       修改原因
*    1    2013年7月17日   林剑    创建
* </pre>
*/
@Retention(RetentionPolicy.RUNTIME) //运行是编译
@Target( { ElementType.FIELD })  //修饰的字段
public @interface Logger {
    //类型，模块常量
    public String type() default "null";

}

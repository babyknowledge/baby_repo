package rms.cmpnt.libs.commons.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * 
 * 项目名称: NetPatrol5.0 IT监管系统<br>
 * 模块名称: <br>
 * 功能描述: 基本异常，系统定义的所有异常都需要继承这个基本类<br>
 * 创建日期: 2009-7-23 <br>
 * 版权信息: Copyright (c) 2009<br>
 * 公司信息: 东软集团股份有限公司 电信事业部研发二部<br>
 * 
 * @author <a href="mailto: liu.dong@neusoft.com">刘冬</a>
 * @version v1.0
 * 
 * <pre>
 * 修改历史
 *   序号      日期          修改人       修改原因
 *    1    2009-7-23      刘冬       创建
 *    2    2010-3-9       陈志国     修改基类为非受检异常
 * </pre>
 */

public class BaseException extends NestedRuntimeException {


    private static final long serialVersionUID = 4370420203711740591L;

    /**
	 * 说明：继承父类构造方法
	 * 
	 * @coustructor
	 */
	public BaseException(String msg) {
		super(msg);
	}

	/**
	 * 说明：继承父类构造方法
	 * 
	 * @coustructor
	 */
	public BaseException(String msg,Throwable e) {
		super(msg,e);
	}
}

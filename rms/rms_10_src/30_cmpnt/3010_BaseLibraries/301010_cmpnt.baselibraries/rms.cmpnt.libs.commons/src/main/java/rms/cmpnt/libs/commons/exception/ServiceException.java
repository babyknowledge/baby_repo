/*
 * Created on 2004-10-14
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package rms.cmpnt.libs.commons.exception;

/**
 * 
 * 项目名称: NetPatrol5.0 IT监管系统<br>
 * 模块名称: <br>
 * 功能描述: bo层异常<br>
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
 *    2    2010-3-9       陈志国     修改构造方法
 * </pre>
 */
public class ServiceException extends BaseException {

    private static final long serialVersionUID = 5245252482288832315L;

    /**
	 * 说明：继承父类构造方法
	 * 
	 * @coustructor
	 */
	public ServiceException(String msg) {
		super(msg);
	}

	/**
	 * 说明：继承父类构造方法
	 * 
	 * @coustructor
	 */
	public ServiceException(String msg,Throwable e) {
		super(msg,e);
	}
}

package rms.cmpnt.libs.commons.exception;

/**
 * 
 * 项目名称: NetPatrol5.0 IT监管系统<br>
 * 模块名称: <br>
 * 功能描述: action层异常类<br>
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
 * </pre>
 */

public class ActionException extends BaseException {

    private static final long serialVersionUID = -1319044724031196882L;

    /**
	 * 说明：继承父类构造方法
	 * 
	 * @coustructor
	 */
	public ActionException(String msg) {
		super(msg);
	}

	/**
	 * 说明：继承父类构造方法
	 * 
	 * @coustructor
	 */
	public ActionException(Exception e) {
		this(e.getMessage());
	}

}
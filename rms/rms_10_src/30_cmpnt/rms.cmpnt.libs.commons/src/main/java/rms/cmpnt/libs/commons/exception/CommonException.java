package rms.cmpnt.libs.commons.exception;


/**
 * 
 * 项目名称: NetPatrol5.0 IT监管系统<br>
 * 模块名称: <br>
 * 功能描述: <br>
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
public class CommonException extends Exception {

	public static final long serialVersionUID = 1;

	/**
	 * 说明：继承父类构造方法
	 * 
	 * @coustructor
	 */
	public CommonException() {
		super();
	}

	/**
	 * 说明：继承父类构造方法
	 * 
	 * @coustructor
	 */
	public CommonException(String sArg0) {
		super(sArg0);
	}

	/**
	 * 说明：继承父类构造方法
	 * 
	 * @coustructor
	 */
	public CommonException(Throwable sArg0) {
		super(sArg0);
	}

	/**
	 * 说明：继承父类构造方法
	 * 
	 * @coustructor
	 */
	public CommonException(String sArg0, Throwable sArg1) {
		super(sArg0, sArg1);
	}

	/**
	 * 说明：重载方法
	 * 
	 * @return 异常信息
	 */
	public String toString() {
		return "异常信息：" + this.getMessage();
	}

	// /**
	// * 将堆栈信息形成字符串保存；
	// * @return String
	// */
	// public String getStackTraceString() {
	// StringWriter sw = new StringWriter();
	// PrintWriter pw = new PrintWriter(sw);
	// this.printStackTrace(pw);
	// return sw.toString();
	// }

}

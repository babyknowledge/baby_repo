package rms.cmpnt.libs.commons.exception;


public class ConstructorException extends Exception {

	private static final long serialVersionUID = -1059464223851002308L;

	public ConstructorException() {
		super();
	}

	public ConstructorException(final String msg) {
		super("[ARSF.Common]构造器工具方法异常：" + msg);
	}

	public ConstructorException(final String msg, final Throwable cause) {
		super("[ARSF.Common]构造器工具方法异常：" + msg, cause);
	}
}

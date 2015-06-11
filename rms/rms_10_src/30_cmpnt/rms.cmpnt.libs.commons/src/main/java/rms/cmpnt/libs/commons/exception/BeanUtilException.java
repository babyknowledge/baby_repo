package rms.cmpnt.libs.commons.exception;

public class BeanUtilException extends Exception {

	private static final long serialVersionUID = 7675557573689522835L;

	public BeanUtilException() {
		super();
	}

	public BeanUtilException(final String msg) {
		super("配置文件内容到类对象转换异常：" + msg);
	}

	public BeanUtilException(final String msg, final Throwable cause) {
		super("配置文件内容到类对象转换异常：" + msg, cause);
	}

	public BeanUtilException(Throwable cause) {
		super(cause);
	}
}
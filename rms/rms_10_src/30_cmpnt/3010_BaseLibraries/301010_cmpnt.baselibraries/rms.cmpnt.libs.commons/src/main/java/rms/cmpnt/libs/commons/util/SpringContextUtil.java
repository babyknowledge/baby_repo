package rms.cmpnt.libs.commons.util;

import org.springframework.web.context.WebApplicationContext;

public class SpringContextUtil {

	public static WebApplicationContext wac;

	/**
	 * 得到spring管理的bean对象
	 * 
	 * @param name
	 *            bean名称
	 * @return
	 */
	public static Object getBean(String name) {
		return wac.getBean(name);
	}
}

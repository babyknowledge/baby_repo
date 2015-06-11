package rms.cmpnt.libs.commons.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 项目名称: NetPatrol5.0 IT监管系统<br>
 * 模块名称: 工具类<br>
 * 功能描述: 特殊字符<br>
 * 创建日期: 2009-8-3 <br>
 * 版权信息: Copyright (c) 2009<br>
 * 公司信息: 东软集团股份有限公司 电信事业部研发二部<br>
 * 
 * @author <a href="mailto: luo-b@neusoft.com">骆宾</a>
 * @version v1.0
 * 
 *          <pre>
 * 修改历史
 *   序号      日期          修改人       修改原因
 *    1    2009-8-3      骆宾       创建
 * </pre>
 */

public class SpecialCharUtil {

	/**
	 * 转义特殊字符 特殊字符： \n \t \r \f \b ' " \
	 * 
	 * @param str
	 *            要转义的字符串
	 * @return
	 */
	public static String changeSpecialChar(String str) {
		if (null != str && "".equals(str)) {
			str = str.replace('\\', '/').replaceAll("/", "\\\\\\\\");
			str = str.replaceAll("\n", "\\\\n");
			str = str.replaceAll("\r", "\\\\r");
			str = str.replaceAll("\t", "\\\\t");
			str = str.replaceAll("\f", "\\\\f");
			str = str.replaceAll("\b", "\\\\b");
			str = str.replaceAll("\"", "`");
			str = str.replaceAll("\'", "`");
		}
		return str;
	}

	/**
	 * 校验pattern字符串,去掉多余的括号
	 * 
	 * @param strPattern
	 *            String
	 * @return String
	 */
	public static String replacePattern(String strPattern) {

		Pattern p = Pattern.compile("\\((\\#\\d+\\#)\\)");
		Matcher m = p.matcher(strPattern.replaceAll("\\$", "#"));
		StringBuffer sb = new StringBuffer();
		boolean result = m.find();
		while (result) {
			String replacement = m.group(1);
			m.appendReplacement(sb, replacement);
			result = m.find();
		}
		m.appendTail(sb);
		return sb.toString().replaceAll("#", "\\$");
	}
}

package rms.cmpnt.libs.commons.util;

import java.math.BigDecimal;

/**
 * 
 * 项目名称: NetPatrol5.0 IT监管系统<br>
 * 模块名称: 工具类<br>
 * 功能描述: 数字处理类<br>
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

public class NumberUtil {

	/**
	 * 为了防止浮点数以科学计数法形式显示出来,转化为普通计数法,最多精确到小数点后4位
	 * 
	 * @param value
	 * @param length
	 *            --精确到小数点后的位数
	 * @return String
	 */
	public static String convertDoubleToString(double value, int length) {

		java.text.DecimalFormat df = (java.text.DecimalFormat) java.text.NumberFormat
				.getInstance();
		df.setMinimumFractionDigits(length);
		df.setMaximumFractionDigits(length);
		return df.format(value);
	}

	/**
	 * 为了防止浮点数以科学计数法形式显示出来,转化为普通计数法,最多精确到小数点后4位
	 * 
	 * @param value
	 *            float
	 * @param value
	 *            int --精确到小数点后的位数
	 * @return String
	 */
	public static String convertFloatToString(float value, int length) {

		java.text.DecimalFormat df = (java.text.DecimalFormat) java.text.NumberFormat
				.getInstance();
		df.setMinimumFractionDigits(length);
		df.setMaximumFractionDigits(length);
		return df.format(value);
	}

	/**
	 * 将字符串转换为Double类型
	 * 
	 * @param value
	 * @return Double
	 */
	public static Double convertStringToDouble(String value) {

		if (value == null || value.equals(""))
			value = "0.0";
		value = StringUtil.replaceString(value, ",", "");
		return Double.valueOf(value);
	}

	/**
	 * 判断字符串是否为零
	 * 
	 * @param strNum
	 * @return boolean类型
	 */
	public static boolean equalZero(String strNum) {

		// BigDecimal objBigDecimal = new BigDecimal(strNum);
		if (!StringUtil.isValid(strNum)) {
			return true;
		}
		boolean ret = (Double.parseDouble(strNum) == 0) ? true : false;
		return ret;
	}

	/**
	 * 把一个字符串转换为一个固定长度的字符串，位数不够在前面补0
	 * 
	 * @param str
	 *            String
	 * @param length
	 *            int
	 * @return String
	 */
	public static String fillStr(String str, int length) {

		int len = str.length();
		int replaceLen = length - len;
		String newStr = str;
		if (len < length) {
			for (int i = 0; i < replaceLen; i++) {
				newStr = "0".concat(newStr);
			}
			System.out.println(newStr);
			return newStr;
		} else {
			return str;
		}
	}

	/**
	 * 把前面用0补齐的字符串转换成正常的字符串
	 * 
	 * @param String
	 * @return String
	 */

	public static String convertStringToIntString(String text) {

		return String.valueOf(Integer.valueOf(text));
	}

	/**
	 * 以传入参数numininal的形式格式化一个字符串,按照0.0000格式传递
	 * 
	 * @param String类型
	 * @param String类型
	 * @return String类型
	 */
	public static String formatStr(String str, String numinitial) {

		str = StringUtil.isValid(str) ? str : "0";
		int scale = (numinitial.length() - 2) > 0 ? numinitial.length() - 2 : 0;

		// if (str == null || str.equals("null") || str.equals(""))
		// {
		// str = numinitial;
		// }
		// str =
		// new DecimalFormat(numinitial)
		// .format(Double.valueOf(str))
		// .toString();
		return formatStr(str, scale);
	}

	/**
	 * 格式化字符串
	 * 
	 * @param str
	 *            String类型
	 * @param num
	 *            int类型
	 * @return String
	 */
	public static String formatStr(String str, int num) {

		str = StringUtil.isValid(str) ? str : "0";
		BigDecimal objBigDecimalFirst = new BigDecimal(str);
		return objBigDecimalFirst.setScale(num, BigDecimal.ROUND_HALF_UP)
				.toString();
	}

	/**
	 * 格式化字符串
	 * 
	 * @param val
	 *            double类型
	 * @param num
	 *            int类型
	 * @return double
	 */
	public static double formatStr(double val, int num) {

		BigDecimal objBigDecimalFirst = new BigDecimal(String.valueOf(val));
		return objBigDecimalFirst.setScale(num, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
	}

	/**
	 * 清除掉数值中小数部分多余的0
	 * 
	 * @param String
	 * @return String .
	 */
	public static String cleanZero(String num) {
		if (num.indexOf(".") > 0) {
			byte[] c = num.getBytes();
			int i = c.length - 1;
			for (; ((char) c[i]) == '0'; i--)
				;
			if (c[i] == '.') {
				return new String(c, 0, i);
			} else {
				return new String(c, 0, i + 1);
			}
		} else {
			return num;
		}
	}

}

package rms.cmpnt.libs.commons.util;

import java.text.DecimalFormat;

/**
 * 
 * 项目名称: NetPatrol5.0 IT监管系统<br>
 * 模块名称: 工具类<br>
 * 功能描述: 处理金额有关类<br>
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

public class MoneyUtil {

	/**
	 * 主要功能：将金额转换成大写
	 * 
	 * @param money
	 *            float
	 * @return java.lang.String
	 * 
	 */
	public static String convertMoneyToChinese(String money) {

		if (money == null || money.equals(""))
			return "您要转换的金额是空对象"; // 空对象返回空字符串
		boolean flag = true;
		if (money.substring(0, 1).equals("-")) {
			money = money.substring(1, money.length());
			flag = false;
		}

		String b = "壹贰叁肆伍陆柒捌玖拾";
		String chinese = "";
		// money = moneyFloat(money);
		// String moneySum = Float.toString(money);
		String moneySum = money;

		if (moneySum.substring(moneySum.indexOf("."), moneySum.length())
				.length() < 3)
			moneySum = moneySum + "0";
		// 先将钱数补齐为15位
		while (moneySum.length() < 16) {
			moneySum = " " + moneySum;
		}
		if (moneySum.length() > 16) {
			moneySum = moneySum.substring(moneySum.length() - 16,
					moneySum.length());
		}

		// 获得各位的数值
		String x1 = moneySum.substring(0, 1);
		String x2 = moneySum.substring(1, 2);
		String x3 = moneySum.substring(2, 3);
		String x4 = moneySum.substring(3, 4);
		String x5 = moneySum.substring(4, 5);
		String x6 = moneySum.substring(5, 6);
		String x7 = moneySum.substring(6, 7);
		String x8 = moneySum.substring(7, 8);
		String x9 = moneySum.substring(8, 9);
		String x10 = moneySum.substring(9, 10);
		String x11 = moneySum.substring(10, 11);

		String x12 = moneySum.substring(11, 12);

		String x13 = moneySum.substring(12, 13);

		String x14 = moneySum.substring(14, 15);

		String x15 = moneySum.substring(15, 16);

		int temp = 0;

		if (!x1.equals(" ")) {
			temp = Integer.parseInt(x1);
			chinese = b.substring(temp - 1, temp) + "万";
		}
		if (!x2.equals(" ")) {
			temp = Integer.parseInt(x2);
			if (x2.equals("0") && !x3.equals("0"))
				chinese = chinese + "零";
			if (!x2.equals("0"))
				chinese = chinese + b.substring(temp - 1, temp) + "仟";
		}
		if (!x3.equals(" ")) {
			temp = Integer.parseInt(x3);
			if (x3.equals("0") && !x4.equals("0"))
				chinese = chinese + "零";
			if (!x3.equals("0"))
				chinese = chinese + b.substring(temp - 1, temp) + "佰";
		}
		if (!x4.equals(" ")) {
			temp = Integer.parseInt(x4);
			if (x4.equals("0") && !x5.equals("0"))
				chinese = chinese + "零";
			if (!x4.equals("0"))
				chinese = chinese + b.substring(temp - 1, temp) + "拾";
		}
		if (!x5.equals(" ")) {
			temp = Integer.parseInt(x5);
			if (x5.equals("0"))
				chinese = chinese + "亿";
			else
				chinese = chinese + b.substring(temp - 1, temp) + "亿";
		}

		if (!x6.equals(" ")) {
			temp = Integer.parseInt(x6);
			if (x6.equals("0") && !x7.equals("0"))
				chinese = chinese + "零";
			if (!x6.equals("0"))
				chinese = chinese + b.substring(temp - 1, temp) + "仟";

		}
		if (!x7.equals(" ")) {
			temp = Integer.parseInt(x7);
			if (x7.equals("0") && !x8.equals("0"))
				chinese = chinese + "零";
			if (!x7.equals("0"))
				chinese = chinese + b.substring(temp - 1, temp) + "佰";
		}
		if (!x8.equals(" ")) {
			temp = Integer.parseInt(x8);
			if (x8.equals("0") && !x9.equals("0"))
				chinese = chinese + "零";
			if (!x8.equals("0"))
				chinese = chinese + b.substring(temp - 1, temp) + "拾";
		}
		if (!x9.equals(" ")) {
			// modify by huxiaod --修改金额大写中的BUG
			temp = Integer.parseInt(x9);
			if (x10.equals("0") && x8.equals("0") && x7.equals("0")
					&& x6.equals("0")) {
			} else if (x9.equals("0")
					&& (!x8.equals("0") || !x7.equals("0") || !x6.equals("0"))) {
				chinese = chinese + "万";
			} else
				chinese = chinese + b.substring(temp - 1, temp) + "万";
		}
		if (!x10.equals(" ")) {
			temp = Integer.parseInt(x10);
			if (x10.equals("0") && !x11.equals("0"))
				chinese = chinese + "零";
			if (!x10.equals("0"))
				chinese = chinese + b.substring(temp - 1, temp) + "仟";
		}
		if (!x11.equals(" ")) {
			temp = Integer.parseInt(x11);
			if (x11.equals("0") && !x12.equals("0"))
				chinese = chinese + "零";
			if (!x11.equals("0"))
				chinese = chinese + b.substring(temp - 1, temp) + "佰";
		}
		if (!x12.equals(" ")) {
			temp = Integer.parseInt(x12);
			if (x12.equals("0") && !x13.equals("0"))
				chinese = chinese + "零";
			if (!x12.equals("0"))
				chinese = chinese + b.substring(temp - 1, temp) + "拾";
		}
		if (!x13.equals(" ")) {
			temp = Integer.parseInt(x13);
			if (x13.equals("0") && x12.equals(" ") && x14.equals("0")
					&& x15.equals("0"))
				chinese = "零元";
			else if (x13.equals("0") && (!x14.equals("0") || !x15.equals("0"))) {
			} else if (x13.equals("0"))
				chinese = chinese + "元";
			else
				chinese = chinese + b.substring(temp - 1, temp) + "元";
		}
		if (!x14.equals(" ")) {
			temp = Integer.parseInt(x14);
			if (!x13.equals("0") && x14.equals("0") && !x15.equals("0"))
				chinese = chinese + "零";
			else if (!x14.equals("0"))
				chinese = chinese + b.substring(temp - 1, temp) + "角";
		}
		if (!x15.equals(" ")) {
			temp = Integer.parseInt(x15);
			if (x15.equals("0"))
				chinese = chinese + "整";
			else
				chinese = chinese + b.substring(temp - 1, temp) + "分";
		}
		if (!flag) {
			chinese = "负" + chinese;
		}
		return chinese;
	}

	/**
	 * 将字符串中的','去掉,然后将字符串转化成float型
	 * 
	 * @param strMoney
	 *            字符串形式的钱
	 * @return float
	 */
	public static float changFloat(String strMoney) {
		float floatMoney = 0;

		if (null == strMoney || "".equals(strMoney)) {// 如果为空,相当于没有钱
			return floatMoney;
		}

		String[] strMoneyArray = strMoney.split(",");
		strMoney = "";

		for (int i = 0; i < strMoneyArray.length; i++) {
			strMoney += strMoneyArray[i];
		}

		floatMoney = Float.parseFloat(strMoney);
		return floatMoney;
	}

	/**
	 * 将整数部分转化成字符串
	 * 
	 * @param money
	 * @return
	 */
	public static String formatIntegerToStr(String money) {

		money = StringUtil.isValid(money) ? money : "0";
		DecimalFormat f = new DecimalFormat("###,###");
		return f.format(Double.parseDouble(money));
	}

}

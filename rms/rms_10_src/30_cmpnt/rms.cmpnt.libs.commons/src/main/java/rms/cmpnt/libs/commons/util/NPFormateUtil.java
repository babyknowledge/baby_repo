package rms.cmpnt.libs.commons.util;

import rms.cmpnt.libs.commons.exception.NMFormateException;

/**
 * 
 * 项目名称: Netpatrol 5.1<br>
 * 模块名称: App Layer 采集处理平台<br>
 * 功能描述: 数据模型格式化工具<br>
 * 创建日期: 2013年8月30日 <br>
 * 版权信息: Copyright (c) 2013<br>
 * 公司信息: 东软集团股份有限公司 电信事业部-网管产品与系统部<br> 
 * @author <a href="mailto: huangshk@neusoft.com">黄守凯</a>
 * @version v1.0
 * <pre>
 * 修改历史
 *   序号      日期          修改人       修改原因
 *    1    2013年8月30日       黄守凯        创建
 * </pre>
 */
public class NPFormateUtil {

	private static String ALL_MARK = "ALL";

	public static String getKgStr(String kg) throws NMFormateException {
		if (ALL_MARK.equals(kg)) {
			return kg;
		}
		if (kg.length() != 10) {
			throw new NMFormateException("");
		}
		char[] kgChars = kg.toCharArray();
		return "" + kgChars[2] + kgChars[3] + "." + kgChars[5] + kgChars[6] + "." + kgChars[8] + kgChars[9];
	}

	public static String getKgInt(String kg) throws NMFormateException {
		if (ALL_MARK.equals(kg)) {
			return kg;
		}
		if (kg.length() != 8) {
			throw new NMFormateException("");
		}
		char[] kgChars = kg.toCharArray();
		return "10" + kgChars[0] + kgChars[1] + "0" + kgChars[3] + kgChars[4] + "0" + kgChars[6] + kgChars[7];
	}

	public static String getKpiStr(String kpi) throws NMFormateException {
		if (ALL_MARK.equals(kpi)) {
			return kpi;
		}
		if (kpi.length() != 13) {
			throw new NMFormateException("");
		}
		char[] kpiChars = kpi.toCharArray();
		return "" + kpiChars[2] + kpiChars[3] + "." + kpiChars[5] + kpiChars[6] + "." + kpiChars[8] + kpiChars[9] + "."
				+ kpiChars[11] + kpiChars[12];
	}

	public static String getKpiInt(String kpi) throws NMFormateException {
		if (ALL_MARK.equals(kpi)) {
			return kpi;
		}
		if (kpi.length() != 11) {
			throw new NMFormateException("");
		}
		char[] kpiChars = kpi.toCharArray();
		return "10" + kpiChars[0] + kpiChars[1] + "0" + kpiChars[3] + kpiChars[4] + "0" + kpiChars[6] + kpiChars[7]
				+ "0" + kpiChars[9] + kpiChars[10];

	}

	public static void main(String[] args) throws NMFormateException {
		System.out.println(NPFormateUtil.getKgStr("1003001001"));
		System.out.println(NPFormateUtil.getKpiStr("1003001001008"));
		System.out.println(NPFormateUtil.getKgInt("03.01.01"));
		// 1001004003
		System.out.println(NPFormateUtil.getKpiInt("03.01.01.08"));
		
		System.out.println(NPFormateUtil.getKgStr("1001004003"));
		System.out.println(NPFormateUtil.getKgInt("01.04.03"));
	}

}

package rms.cmpnt.libs.commons.util;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 
 * 项目名称: NetPatrol5.0 IT监管系统<br>
 * 模块名称: 工具类<br>
 * 功能描述: 数学运算类 包括数字的加减乘除方法，参数统一为N<br>
 * 创建日期: 2009-8-3 <br>
 * 版权信息: Copyright (c) 2009<br>
 * 公司信息: 东软集团股份有限公司 电信事业部研发二部<br>
 * 
 * @author <a href="mailto: luo-b@neusoft.com">骆宾</a>
 * @version v1.0
 * 
 * <pre>
 * 修改历史
 *   序号      日期          修改人       修改原因
 *    1    2009-8-3      骆宾       创建
 * </pre>
 */
public class MathUtil {

    private static final Log log = LogFactory.getLog(MathUtil.class);
    
    /**
     * 计算两个数字字符串相加
     * 
     * @param strFirst String
     * @param strSecond String
     * @param numinitial String
     * @return String
     */
    public static String addTwoNumStr(String strFirst, String strSecond) {

        if (strFirst.equalsIgnoreCase("")) {
            strFirst = "0";
        }
        if (strSecond.equalsIgnoreCase("")) {
            strSecond = "0";
        }
        BigDecimal objBigDecimalFirst = new BigDecimal(strFirst);
        BigDecimal objBigDecimalSecond = new BigDecimal(strSecond);
        return (objBigDecimalFirst.add(objBigDecimalSecond).toString());
    }

    /**
     * 两个数字字符串相加,以传入参数numininal的形式返回结果字符串
     * 
     * @param strFirst String
     * @param strSecond String
     * @param numinitial String
     * @return String
     */
    public static String addTwoNumStr(String strFirst, String strSecond, String numinitial) {

        if (strFirst.equalsIgnoreCase("")) {
            strFirst = "0";
        }
        if (strSecond.equalsIgnoreCase("")) {
            strSecond = "0";
        }
        BigDecimal objBigDecimalFirst = new BigDecimal(strFirst);
        BigDecimal objBigDecimalSecond = new BigDecimal(strSecond);
        String str = objBigDecimalFirst.add(objBigDecimalSecond).toString();
        return NumberUtil.formatStr(str, numinitial);
    }

    /**
     * 两个数字字符串相减,以传入参数numininal的形式返回结果字符串
     * 
     * @param strFirst String
     * @param strSecond String
     * @param numinitial String
     * @return String
     */
    public static String subTwoNumStr(String strFirst, String strSecond, String numinitial) {

        if (strFirst.equalsIgnoreCase("")) {
            strFirst = "0";
        }
        if (strSecond.equalsIgnoreCase("")) {
            strSecond = "0";
        }
        BigDecimal objBigDecimalFirst = new BigDecimal(strFirst);
        BigDecimal objBigDecimalSecond = new BigDecimal(strSecond);
        String str = NumberUtil.formatStr(objBigDecimalFirst.subtract(objBigDecimalSecond)
                .toString(), numinitial);
        return str;
    }

    /**
     * 
     * 
     * 得到某个数值的相反数
     * 
     * 
     * @param strFirst String类型
     * @return String
     */
    public static String strSign(String strFirst) {

        if (!StringUtil.isValid(strFirst)) {
            strFirst = "0";
        }
        BigDecimal objBigDecimalFirst = new BigDecimal(strFirst);
        return (objBigDecimalFirst.negate().toString());
    }

    /**
     * 
     * 得到某个BigDecimal实例的绝对值
     * 
     * @param strFirst String类型
     * @return String
     */
    public static String strAbs(String strFirst) {

        if (!StringUtil.isValid(strFirst)) {
            strFirst = "0";
        }
        BigDecimal objBigDecimalFirst = new BigDecimal(strFirst);
        return (objBigDecimalFirst.abs().toString());
    }

    /**
     * 比较两个字符串的大小，返回结果为较小的字符串,如果比较不出来，就返回第一个字符串
     * 
     * @param str1 String
     * @param str2 String
     * @return String
     */
    public static String compareTwoStrSmall(String str1, String str2) {

        if (str1.equals("")) {
            return (str2);
        }
        else if (str2.equals("")) {
            return (str1);
        }
        else {
            BigDecimal objstr1 = new BigDecimal(str1);
            BigDecimal objstr2 = new BigDecimal(str2);
            int reti = objstr1.compareTo(objstr2);
            if (reti == -1) {
                return (str1);
            }
            else if (reti == 0) {
                return (str1);
            }
            else if (reti == 1) {
                return (str2);
            }
            else {
                return (str1);
            }
        }
    }

    /**
     * 
     * 比较两个字符串的大小，返回结果为较大的字符串,如果比较不出来，就返回第一个字符串
     * 
     * @param str1 String类型
     * @param str2 String类型
     * @return String
     */
    public static String compareTwoStrBig(String str1, String str2) {
        if (str1.equals("")) {
            return (str1);
        }
        else if (str2.equals("")) {
            return (str2);
        }
        else {
            BigDecimal objstr1 = new BigDecimal(str1);
            BigDecimal objstr2 = new BigDecimal(str2);
            int reti = objstr1.compareTo(objstr2);
            if (reti == -1) {
                return (str2);
            }
            else if (reti == 0) {
                return (str1);
            }
            else if (reti == 1) {
                return (str1);
            }
            else {
                return (str1);
            }
        }
    }

    /**
     * 判断两个字符串是否相等,相等返回true,否则返回false
     * 
     * @param str1 String
     * @param str2 String
     * @return String
     */
    public static boolean isNumberEqual(String str1, String str2) {

        BigDecimal objstr1 = new BigDecimal(str1);
        BigDecimal objstr2 = new BigDecimal(str2);
        int reti = objstr1.compareTo(objstr2);
        if (reti == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 
     * 两个数字字符串相乘,返回结果字符串 <br>
     * 
     * @param strFirst
     * @param strSecond
     * @return String
     */
    public static String multTwoNumStr(String strFirst, String strSecond) {

        BigDecimal objBigDecimalFirst = new BigDecimal(strFirst);
        BigDecimal objBigDecimalSecond = new BigDecimal(strSecond);
        return objBigDecimalFirst.multiply(objBigDecimalSecond).toString();

    }

    /**
     * 
     * 两个数字字符串相除,返回结果字符串,按照scale精度要求四舍五入<br>
     * 
     * @param strFirst
     * @param strSecond
     * @param scale
     * @return String
     * @throws ArithmeticException
     * @throws IllegalArgumentException
     */
    public static String divTwoNumStr(String strFirst, String strSecond, int scale)
            throws ArithmeticException, IllegalArgumentException {

        // 如果被除数或者除数的值为空,返回0
        if (!StringUtil.isValid(strSecond) || !StringUtil.isValid(strFirst)) {
            return ("0");
        }
        if (Double.parseDouble(strSecond) == 0) {
            return ("0");
        }
        BigDecimal objBigDecimalFirst = new BigDecimal(strFirst);
        BigDecimal objBigDecimalSecond = new BigDecimal(strSecond);
        BigDecimal objBigDecimalResult = objBigDecimalFirst.divide(objBigDecimalSecond, scale, 4);
        return (objBigDecimalResult.toString());
    }

    /**
     * 判断两个数是否相等
     * 
     * @param srcData
     * @param expectData
     * @return
     */

    public static int equals(String srcData, String expectData) {

        BigDecimal srcBigDecimal = new BigDecimal(srcData);
        BigDecimal expectBigDecimal = new BigDecimal(expectData);
        return srcBigDecimal.compareTo(expectBigDecimal);
    }

    /**
     * 
     * 数组数据求和
     * 
     * @return String .
     */
    public static String sumNumberArrStr(String[] number) {

        BigDecimal sum = new BigDecimal(0.0);
        if (number != null) {
            for (int i = 0; i < number.length; i++) {
                sum=sum.add(new BigDecimal(number[i]));
            }
        }
        return sum.toString();
    }

    /**
     * 
     * 利用反射机制获取方法的返回值
     * 
     * @param vo
     * @param method
     * @return
     */
    public static Object getVOValue(Object vo, String method) {
        Method[] methods = vo.getClass().getMethods();
        try {
            // 利用java的反射机制来完成数据赋值；
            for (int i = 0; i < methods.length; i++) {
                if (methods[i].getName().equals(method)) {
                    return methods[i].invoke(vo, null);
                }
            }
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
        return null;
    }

}
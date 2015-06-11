/**
 * 
 * sql时间转换为字符串（时间精确到年月日） 将Sql时间戳转换为字符串（时间精确到年月日时分秒） 将字符串转换为sql时间（时间精确到年月日）
 * 将字符串转换为Sql时间戳（时间精确到年月日时分秒） 将util时间转换为sql时间 将util时间转换为字符串，一个例子："yyyy-MM-dd
 * HH:mm:ss" 将时间转化为util时间 获取当前日期为日期型 获取当前日期为字符串 按格式转换日期的格式到固定格式的时间 将固定格式字符串转化为日期
 * 为 sql 里直接通过result.getObject获取日期型变量准备的方法. 取得与原日期相差一定月数的日期，返回Date型日期
 * 取得与原日期相差一定天数的日期，返回String型日期 将日期型的对象进行运算 计算某天所在月的第一天 计算某天所在月的最后一天
 * 得到两个日期之间相差的天数 根据日期得出当前周在当月的第几周 根据日期得出当前周在当年的第几周
 * 
 */
package rms.cmpnt.libs.commons.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 项目名称: NetPatrol5.0 IT监管系统<br>
 * 模块名称: 工具类<br>
 * 功能描述: 日期处理类<br>
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
 * 
 */

public class DateUtil {

	public static DateUtil du = null;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");

	private static final Log log = LogFactory.getLog(DateUtil.class);
	
	public static final String DEF_DATE_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";
	
	public static final String DATE_FORMAT_WITHOUT_YEARS_STR = "HH:mm:ss";

	static {
		du = getInstance();
	}

	/**
	 * 
	 * 获得唯一的实例
	 * 
	 * @return DateUtils
	 */

	public static DateUtil getInstance() {

		if (du == null) {
			du = new DateUtil();
		}
		return du;
	}

	/**
	 * sql时间转换为字符串（时间精确到年月日）
	 * 
	 * @param inDate
	 * @param pattern
	 * @return String
	 */
	public static String sqlDateToString(java.sql.Date inDate, String pattern) {

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		long lDate = inDate.getTime();
		Date tmpDate = new Date(lDate);
		String sDate = sdf.format(tmpDate);
		return sDate;
	}

	/**
	 * 将Sql时间戳转换为字符串（时间精确到年月日时分秒）
	 * 
	 * @param inDate
	 * @param pattern
	 * @return String
	 */
	public static String sqlTimestampToString(java.sql.Timestamp inDate,
			String pattern) {

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		long lDate = inDate.getTime();
		Date tmpDate = new Date(lDate);
		String sDate = sdf.format(tmpDate);
		return sDate;
	}

	/**
	 * 将字符串转换为sql时间（时间精确到年月日）
	 * 
	 * @param inStr
	 * @param pattern
	 * @return Date
	 */
	public static java.sql.Date stringToSqlDate(String inStr, String pattern) {

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date tmpDate = sdf.parse(inStr, new ParsePosition(0));
		long lDate = tmpDate.getTime();
		java.sql.Date dt = new java.sql.Date(lDate);
		dt.setTime(lDate);
		return dt;
	}

	/**
	 * 将字符串转换为Sql时间戳（时间精确到年月日时分秒）
	 * 
	 * @param inStr
	 * @param pattern
	 * @return Timestamp
	 */
	public static java.sql.Timestamp stringToSqlTimestamp(String inStr,
			String pattern) {

		if (inStr == null) {
			return null;
		}
		java.sql.Timestamp tempTime = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			Date tmpDate = sdf.parse(inStr, new ParsePosition(0));
			tempTime = new java.sql.Timestamp(tmpDate.getTime());
		} catch (Exception exp) {
			log.error(exp.getMessage());
		}
		return tempTime;
	}
	
	public static java.sql.Time stringToSqlTime(String inStr, String pattern){
		if(inStr == null){
			return null;
		}
		java.sql.Time tempTime = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			Date tmpDate = sdf.parse(inStr, new ParsePosition(0));
			tempTime = new java.sql.Time(tmpDate.getTime());
		} catch (Exception exp) {
			log.error(exp.getMessage());
		}
		return tempTime;
	}

	/**
	 * 将util时间转换为sql时间
	 * 
	 * @param tmpDate
	 * @param pattern
	 * @return Date
	 */
	public static java.sql.Date dateToSqlDate(java.util.Date tmpDate,
			String pattern) {

		long lDate = tmpDate.getTime();
		java.sql.Date dt = new java.sql.Date(lDate);
		return dt;
	}

	/**
	 * 将util时间转换为字符串，一个例子："yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param inDate
	 * @param pattern
	 * @return String
	 */
	public static String dateToString(Date inDate, String pattern) {

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String sDate = sdf.format(inDate);
		return sDate;
	}

	/**
	 * 将时间转化为util时间
	 * 
	 * @param inStr
	 * @param pattern
	 * @return Date
	 */
	public static Date stringToDate(String inStr, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date dt = sdf.parse(inStr, new ParsePosition(0));
		return dt;
	}

	/**
	 * 获取当前日期为日期型
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {

		Calendar cal = Calendar.getInstance();
		Date d = cal.getTime();
		return d;
	}

	/**
	 * 获取当前日期为字符串
	 * 
	 * @return
	 */
	public static String getCurrentDateToString(String format) {

		return dateToString(getCurrentDate(), format);
	}

	/**
	 * 取得与原日期相差一定月数的日期，返回Date型日期
	 * 
	 * @param date
	 * @param intBetween
	 * @return
	 */
	public static Date getDateMonthBetween(Date date, int intBetween) {

		Calendar calo = Calendar.getInstance();
		calo.setTime(date);
		calo.add(Calendar.MONTH, intBetween);
		return calo.getTime();
	}

	/**
	 * 取得与原日期相差一定天数的日期，返回Date型日期
	 * 
	 * @param date
	 * @param intBetween
	 * @return
	 */
	public static Date getDateBetween(Date date, int intBetween) {

		Calendar calo = Calendar.getInstance();
		calo.setTime(date);
		calo.add(Calendar.DATE, intBetween);
		return calo.getTime();
	}

	/**
	 * 取得与原日期相差一定天数的日期，返回String型日期
	 * 
	 * @param date
	 * @param intBetween
	 * @param strFromat
	 * @return
	 */
	public static String getDateBetween_String(Date date, int intBetween,
			String strFromat) {

		Date dateOld = getDateBetween(date, intBetween);
		return dateToString(dateOld, strFromat);
	}

	/**
	 * 按格式转换日期的格式到固定格式的时间 <br>
	 * 转换时格式的字符必须符合要求.
	 * 
	 * @param date
	 *            待转换的日期.
	 * 
	 * @param format
	 *            转换格式. 格式必须符合: <br>
	 *            yyyy, 输出四位年 yy, 输出两位年 <br>
	 *            MM, 月 <br>
	 *            dd, 日期 <br>
	 *            HH, 小时24小时制 <br>
	 *            mm, 分钟 <br>
	 *            ss, 秒 <br>
	 *            中间间隔符号按照需要填写. 如: yyyy--MM--dd
	 */
	public static String stringDateTime(Date date, String format) {

		if (date == null)
			return null;

		SimpleDateFormat subDateFormat = new SimpleDateFormat(format);
		return subDateFormat.format(date);
	}

	/**
	 * 为 sql 里直接通过result.getObject获取日期型变量准备的方法. 按格式转换日期的格式到固定格式的时间 <br>
	 * 转换时格式的字符必须符合要求.
	 * 
	 * @param date
	 *            待转换的日期.
	 * @param format
	 *            转换格式. 格式必须符合: <br>
	 *            yyyy, 输出四位年 yy, 输出两位年 <br>
	 *            MM, 月 <br>
	 *            dd, 日期 <br>
	 *            HH, 小时 <br>
	 *            mm, 分钟 <br>
	 *            ss, 秒 <br>
	 *            中间间隔符号按照需要填写. 如: yyyy--MM--dd
	 */
	public static String stringDateTime(Object date, String format) {
		return stringDateTime((java.util.Date) date, format);
	}

	/**
	 * 将日期型的对象进行运算.
	 * 
	 * @param date
	 *            待计算的日期
	 * @param field
	 *            待计算的项目 Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, <br>
	 *            Calendar.HOUR, Calendar.MINUTE, Calendar.SECOND
	 * @param amount
	 *            待计算的数量. 负数表示减.
	 */
	public static Date dateAdd(Date date, int field, int amount) {

		if (date == null)
			return null;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * 将固定格式字符串转化为日期"
	 * 
	 * @param strDate
	 *            格式为:"yyyy-MM-dd HH:mm:ss"
	 *            
	 * 此方法似乎有bug，慎用！
	 * 清使用stringToDate(string,pattern)方法
	 * 
	 * @return
	 */
	public static Date dateString(String strDate) {
		try {
			return dateFormat.parse(strDate);
		} catch (ParseException e) {
			log.error("trans '" + strDate + "' to Date:" + e.getMessage());
			return null;
		}
	}

	public static String dateToString(Date date) {
		return dateFormat.format(date);
	}

	/**
	 * 
	 * 计算某天所在月的第一天
	 * 
	 * @param date
	 *            日期类型
	 * 
	 * @return Date 日期类型
	 */
	public static Date monthlyFirstDate(Date date) {
		if (date == null)
			return null;

		String strDate = stringDateTime(date, "yyyy-MM");
		return dateString(strDate + "-01 00:00:00");
	}

	/**
	 * 计算某天所在月的最后一天
	 * 
	 * @param date
	 *            参加计算的某天日期
	 * 
	 * @return Date 日期类型
	 */
	public static Date monthlyEndDate(Date date) {
		if (date == null)
			return null;
		Date nextMonth = dateAdd(date, Calendar.MONTH, 1);
		String strDate = stringDateTime(nextMonth, "yyyy-MM");
		strDate += "-01 23:59:59";
		return dateAdd(dateString(strDate), Calendar.DATE, -1);
	}

	/**
	 * 得到两个日期之间相差的天数
	 * 
	 * @param newDate
	 * @param oldDate
	 * @return days
	 */
	public static int daysBetweenDates(Date newDate, Date oldDate) {

		int days = 0;
		Calendar calo = Calendar.getInstance();
		Calendar caln = Calendar.getInstance();
		calo.setTime(oldDate);
		caln.setTime(newDate);
		int oday = calo.get(Calendar.DAY_OF_YEAR);
		int nyear = caln.get(Calendar.YEAR);
		int oyear = calo.get(Calendar.YEAR);
		while (nyear > oyear) {
			calo.set(Calendar.MONTH, 11);
			calo.set(Calendar.DATE, 31);
			days = days + calo.get(Calendar.DAY_OF_YEAR);
			oyear = oyear + 1;
			calo.set(Calendar.YEAR, oyear);
		}
		int nday = caln.get(Calendar.DAY_OF_YEAR);
		days = days + nday - oday;
		// int days = newDate.compareTo(oldDate);
		return days;
	}

	/**
	 * 根据日期得出当前周在当月的第几周
	 * 
	 * @param date
	 * @return String
	 */
	public static String getWeekOfMonths(String date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtil.stringToDate(date, "yyyymmdd"));
		int weeks = cal.get(Calendar.WEEK_OF_MONTH);
		return String.valueOf(weeks);
	}

	/**
	 * 根据日期得出当前周在当年的第几周
	 * 
	 * @param date
	 * @return String
	 */
	public static String getWeekOfYears(String date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtil.stringToDate(date, "yyyymmdd"));
		int weeks = cal.get(Calendar.WEEK_OF_YEAR);
		return String.valueOf(weeks);
	}

	/**
	 * 如果dateTime小于系统时间则返回true
	 * 
	 * @param dateTime
	 * @return
	 * @throws ParseException
	 */
	public static boolean compareTime(String dateTime) {
		boolean bool = false;
		Date ackTimeDate;
		try {
			ackTimeDate = dateFormat.parse(dateTime);
			String sCurrentTime = dateFormat.format(new Date());
			Date currentTimeDate = dateFormat.parse(sCurrentTime);
			bool = ackTimeDate.before(currentTimeDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return bool;
	}

	/**
	 * 如果firstTs小于secondTs则返回true
	 * 
	 * @param dateTime
	 * @return
	 * @throws ParseException
	 */
	public static boolean compareTime2Before(String firstTs, String secondTs) {
		boolean bool = false;
		try {
			Date firstDate = dateFormat.parse(firstTs);
			Date secondDate = dateFormat.parse(secondTs);
			bool = firstDate.before(secondDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return bool;
	}
	/**
	 * 取得日期所在月
	 * 
	 * @return
	 */
	public static int getMonthsOfYears(String date){
	    Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.stringToDate(date, "yyyymmdd"));
        int months = cal.get(Calendar.MONTH);
        return months;
	}
	/**
	 * 取得季度
	 * 
	 * @param date
	 * @return
	 */
	public static int getQuarterOfYears(String date){
	    int months = getMonthsOfYears(date);
	    int quarter = (months-1)/3+1;
	    return quarter;
	}
	
	/**
     * 计算两个时间差
     * 
     * @param format 输入时间的字符串格式，如：yy-MM-dd HH:mm:ss
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param type 需要返回的差类型（用Calendar.DAY_OF_YEAR代替相差的天）
     * @return
     */
    public static long dateDiff(String format,String startTime,String endTime,int type) {
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 86400000; // 1000*24*60*60; // 一天的毫秒数
        long nh = 3600000; // 1000*60*60; // 一小时的毫秒数
        long nm = 60000; // 1000*60; // 一分钟的毫秒数
        long ns = 1000; // 一秒钟的毫秒数
        long diff = 0;
        try {
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        switch (type) {
            case Calendar.DAY_OF_YEAR:
            {
            	long day = diff/nd; //
            	long hour = diff/nh; //
            	long min = diff%nh/nm; //
            	long sec = diff%nh%nm/ns; //
            	return day;
                //return String.valueOf(day) + "天" + String.valueOf(hour) + ":" + String.valueOf(min) + ":" + String.valueOf(sec);
            }
            case Calendar.HOUR:
            {
            	long hour = diff/nh; //
            	long min = diff%nh/nm; //
            	long sec = diff%nh%nm/ns; //
            	return hour;
                //return String.valueOf(hour) + ":" + String.valueOf(min) + ":" + String.valueOf(sec);
            }
            case Calendar.MINUTE:
            {
            	long min = diff/nm; //
            	long sec = diff%nm/ns; //
            	return min;
                //return String.valueOf(min) + ":" + String.valueOf(sec);
            }
            case Calendar.SECOND:
            {
            	long sec = diff/ns; //
            	return sec;
                //return String.valueOf(sec);
            }
            case Calendar.MILLISECOND:
            {
                //return String.valueOf(diff);
            	return diff;
            }
        }
        return 0;
    }
    
    /**
     * 获得某天的开始时间，即2012-01-01 00:00:00
     * 
     * @return
     */
    public static  String getDayStartTime(Date date) {
        try {
            date = shortSdf.parse(shortSdf.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateFormat.format(date);
    }
    
    /**
     * 获取某天是周几，周一至周日分别返回：1,2,3,4,5,6,0
     * 
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date){
    	try{
    		date = shortSdf.parse(shortSdf.format(date));
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(date);
    		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    	}catch (Exception e) {
            e.printStackTrace();
            return -100;
        }
    }

    /**
     * 获得某天的结束时间，即2012-01-01 23:59:59
     * 
     * @return
     */
    public static  String getDayEndTime(Date date) {
        try {
            date = dateFormat.parse(shortSdf.format(date) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateFormat.format(date);
    }
    
    /**
     * 获得天所在周的第一天，周一
     * 
     * @return
     */
    public static  String getWeekDayStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(date);
            int weekday = c.get(Calendar.DAY_OF_WEEK) - 2;
            c.add(Calendar.DATE, -weekday);
            c.setTime(dateFormat.parse(shortSdf.format(c.getTime()) + " 00:00:00"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateFormat.format(c.getTime());
    }

    /**
     * 获得天所在周的最后一天，周日
     * 
     * @return
     */
    public static  String getWeekDayEndTime(Date date) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(date);
            int weekday = c.get(Calendar.DAY_OF_WEEK);
            c.add(Calendar.DATE, 8 - weekday);
            c.setTime(dateFormat.parse(shortSdf.format(c.getTime()) + " 23:59:59"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateFormat.format(c.getTime());
    }
    
    /**
     * 获得某天所在月的开始时间，即2012-01-01 00:00:00
     * 
     * @return
     */
    public static  String getMonthStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.setTime(date);
            c.set(Calendar.DATE, 1);
            now = shortSdf.parse(shortSdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateFormat.format(now);
    }

    /**
     * 当前某天所在月的结束时间，即2012-01-31 23:59:59
     * 
     * @return
     */
    public static  String getMonthEndTime(Date date) {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.setTime(date);
            c.set(Calendar.DATE, 1);
            c.add(Calendar.MONTH, 1);
            c.add(Calendar.DATE, -1);
            now = dateFormat.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateFormat.format(now);
    }
    
    /**
     * 某天所在季度的开始时间，即2012-01-1 00:00:00
     * 
     * @return
     */
    public  static String getQuarterStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = dateFormat.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateFormat.format(now);
    }

    /**
     * 某天所在季度的结束时间，即2012-03-31 23:59:59
     * 
     * @return
     */
    public  static String getQuarterEndTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            now = dateFormat.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateFormat.format(now);
    }

    /**
     * 某天所在年的开始时间，即2012-01-01 00:00:00
     * 
     * @return
     */
    public static  String getYearStartTime(Date date) {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.setTime(date);
            c.set(Calendar.MONTH, 0);
            c.set(Calendar.DATE, 1);
            now = shortSdf.parse(shortSdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateFormat.format(now);
    }

    /**
     * 当前年的结束时间，即2012-12-31 23:59:59
     * 
     * @return
     */
    public static  String getYearEndTime(Date date) {
        Calendar c = Calendar.getInstance();
        Date now = null;
        try {
            c.setTime(date);
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DATE, 31);
            now = dateFormat.parse(shortSdf.format(c.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateFormat.format(now);
    }
    /**
     * 取得上一季度任意一天
     * 
     * @param date
     * @return
     */
    public static Date getPreQuarter(Date date){
        int quarter = getQuarterOfYears(dateToString(date));
        int preQuarter = quarter-1;
        
        Calendar c = Calendar.getInstance();
        
        c.setTime(date);
        if (preQuarter == 1) {
            c.set(Calendar.MONTH, 1);
            c.set(Calendar.DATE, 1);
        }
        else if (preQuarter == 2) {
            c.set(Calendar.MONTH, 4);
            c.set(Calendar.DATE, 1);
        }
        else if (preQuarter == 3) {
            c.set(Calendar.MONTH, 7);
            c.set(Calendar.DATE, 1);
        }
        else if (preQuarter == 0) {// 4
            c.set(Calendar.MONTH, 10);
            c.set(Calendar.DATE, 1);
        }
        return  c.getTime();
    }
    
    /**
     * 判断一个日期是星期几
     * 
     * @param time 传入日期
     * @return   1 星期一 2 星期2 ....... 7 星期日
     * @throws ParseException
     */
    public static int weekOfDay(String time) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(time));
        int weekOfDay = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            weekOfDay = 7;
        }
        else {
            weekOfDay = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return weekOfDay;
    }

    public static void main(String[] args) {
//      int ret = (9-1)/3+1;
//      System.out.println(ret+"-----------");
      DateUtil t = new DateUtil();
      
//      System.out.println(DateUtil.dateDiff("yy-MM-dd HH:mm:ss","2015-01-15 08:53:42","2015-01-15 09:53:46",Calendar.MINUTE));
//      System.out.println(5/3);
//      System.out.println(5%3);
      String d = "2015-04-26";
      System.out.println(DateUtil.stringToDate(d, "yyyy-MM-dd"));
      System.out.print(DateUtil.getDayOfWeek(DateUtil.stringToDate(d, "yyyy-MM-dd")));
      /*Date curDate = new Date();
      System.out.println("getDayStartTime========="+t.getDayStartTime(t.dateAdd(curDate, Calendar.DATE, -1)));
      System.out.println("getDayEndTime==========="+t.getDayEndTime(t.dateAdd(curDate, Calendar.DATE, -1)));
      System.out.println("getWeekDayStartTime====="+t.getWeekDayStartTime(t.dateAdd(curDate, Calendar.WEEK_OF_YEAR, -1)));
      System.out.println("getWeekDayEndTime======="+t.getWeekDayEndTime(t.dateAdd(curDate, Calendar.WEEK_OF_YEAR, -1)));
      System.out.println("getMonthStartTime======="+t.getMonthStartTime(t.dateAdd(curDate, Calendar.MONTH, -1)));
      System.out.println("getMonthEndTime========="+t.getMonthEndTime(t.dateAdd(curDate, Calendar.MONTH, -1)));
      System.out.println("getQuarterStartTime====="+t.getQuarterStartTime(getPreQuarter(curDate)));
      System.out.println("getQuarterEndTime======="+t.getQuarterEndTime(getPreQuarter(curDate)));
      System.out.println("getYearStartTime========"+t.getYearStartTime(t.dateAdd(curDate, Calendar.YEAR, -1)));
      System.out.println("getYearEndTime=========="+t.getYearEndTime(t.dateAdd(curDate, Calendar.YEAR, -1)));
      System.out.println("getPreQuarter=========="+dateToString(t.getPreQuarter(curDate)));*/
  }
}

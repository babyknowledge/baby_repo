package com.neoye.rms.domain.infrastructure.constant;

public class InfrConstant {
    
	/**
	 * 中心代号
	 */
	public final static String CENTER_CODE = "000";
	/**
	 * 平台类型
	 */
	public final static int PLATFORM_TYPE = 1;
    /**
     * map-key userInfo
     */
    public final static String USER_INFO_KEY = "userInfo";
    
    /**
     * map-key userName
     */
    public final static String USERACCOUNT_KEY = "account";
    
    /**
     * map-key userId
     */
    public final static String USER_ID_KEY = "userId";
    
    /**
     * downlaod
     */
    public final static String DOWNLOAD_CATEGORY = "category";
    /**
     * downlaod
     */
    public final static String DOWNLOAD_COMMON_KEY = "key";
    
    /**
     * downlaod
     */
    public final static String DOWNLOAD_COMMON_PATH = "path";
    
    /**
     * downlaod
     */
    public final static String DOWNLOAD_COMMON_RELPATH = "relPath";
    
    /**
     * map-key autoDel
     */
    public final static String DOWNLOAD_AUTO_DEL = "autoDel";
    
    /*
     * Scheduled定时任务，格式详见文件底部说明
     */
    public static final String C_CRONTRIGGER_EVALUATION = "0 0/1 * * * *"; // 每分钟
    
    public static final String C_TEST = "0/30 0/1 * * * *"; // 每30秒
    
    
    public static final String C_CRONTRIGGER_REPORT_STATUS_SCAN = "0 15 0 * * ?"; // 0 15 0 * * ? 每天00点15分触发
    
    public static final String RETURN_MSG_DIC_NOT_FOUND = "未识别";
    /**
     * nm_dic_comm_t 表 dic_type 枚举定义
     * @author linjian
     *
     */
    public static enum DIC_TYPE_DEF{
    	MUSIC("20001"),
    	TIME("20002"),
    	FLEXIBLE_FORM("100"),
    	ALARM_TYPE("20003"),
    	RECOVERY_STATUS("20004"),
    	UP_STATUS("20005"),
    	FREQ_SEG_TYPE("20006"),
    	TASK_ORIGIN_TYPE("20007"),
    	TASK_TYPE("20008"),
    	MODEL_TYPE("30005"),
    	MACHINE_CODE("30006"),
    	RUN_STATUS("30007"),
    	EXPORT("30011"),
    	SYSTEM_TYPE("30014"),
    	WEEK("20009"),
    	RECV_STATUS("20011"),
    	DOWN_STATUS("20010"),
//    	90001 报表上报周期类型：4 日、5 周、6 月、7 季度 8 年
    	REPORT_PERIOD_LTYPE("90001"),
//    	90002 报表上报方式：1数据上报 2附件上报 -1数据上报+附件上报
    	REPORT_UPLOAD_MODE("90002"),
//    	90003 处理状态：1处理完成、2待处理、3处理中、4过期、5不处理
    	REPORT_REPORT_STATUS("90003"),
//    	90004 管理状态：1统计、2更新、3校对、4审核、5签发、6上报、7回退
    	REPORT_MGR_STATUS("90004"),
    	//巡检项且
    	RADIO_INSPECT_GROUP("30008")
    	;
    	
    	public final String value ;
        DIC_TYPE_DEF(String value){
    		this.value =  value;
    	}
        
        public String getValue(){
        	return value;
        }
    }
    
//    public static enum DIC_WEEK_DEF{
//    	ALL("20001"),
//    	TIME("20002"),
//    	FLEXIBLE_FORM("100"),
//    	ALARM_TYPE("20003"),
//    	RECOVERY_STATUS("20004"),
//    	UP_STATUS("20005"),
//    	FREQ_SEG_TYPE("20006"),
//    	TASK_ORIGIN_TYPE("20007"),
//    	TASK_TYPE("20008"),
//    	WEEK("20009")
//    	;
//    	
//    	
//    	public final String value;
//    	
//    	DIC_WEEK_DEF(String value){
//    		this.value = value;
//    	}
//    	
//    	public String getValue(){
//    		return this.value;
//    	}
//    }
    
    
   
}

/*
CronTrigger配置格式:
格式: [秒] [分] [小时] [日] [月] [周] [年]
序号 说明 是否必填 允许填写的值 允许的通配符
1    秒    是      0-59 ,         - * /
2    分    是      0-59 ,         - * /
3    小时  是      0-23 ,         - * /
4    日    是      1-31 ,         - * ? / L W
5    月    是      1-12 or JAN-DEC , - * /
6    周    是      1-7 or SUN-SAT , - * ? / L #
7    年    否      empty 或 1970-2099 , - * /

通配符说明:
* 表示所有值. 例如:在分的字段上设置 "*",表示每一分钟都会触发。
? 表示不指定值。使用的场景为不需要关心当前设置这个字段的值。例如:要在每月的10号触发一个操作，但不关心是周几，所以需要周位置的那个字段设置为"?" 具体设置为 0 0 0 10 * ?
- 表示区间。例如在小时上设置 "10-12",表示 10,11,12点都会触发。
, 表示指定多个值，例如在周字段上设置 "MON,WED,FRI" 表示周一，周三和周五触发
/ 用于递增触发。如在秒上面设置"5/15" 表示从5秒开始，每增15秒触发(5,20,35,50)。 在月字段上设置'1/3'所示每月1号开始，每隔三天触发一次。
L 表示最后的意思。在日字段设置上，表示当月的最后一天(依据当前月份，如果是二月还会依据是否是润年[leap]), 在周字段上表示星期六，相当于"7"或"SAT"。如果在"L"前加上数字，则表示该数据的最后一个。例如在周字段上设置"6L"这样的格式,则表示“本 月最后一个星期五"
W 表示离指定日期的最近那个工作日(周一至周五). 例如在日字段上设置"15W"，表示离每月15号最近的那个工作日触发。如果15号正好是周六，则找最近的周五(14号)触发, 如果15号是周未，则找最近的下周一(16号)触发.如果15号正好在工作日(周一至周五)，则就在该天触发。如果指定格式为 "1W",它则表示每月1号往后最近的工作日触发。如果1号正是周六，则将在3号下周一触发。(注，"W"前只能设置具体的数字,不允许区间"-").
小提示
'L'和 'W'可以一组合使用。如果在日字段上设置"LW",则表示在本月的最后一个工作日触发(一般指发工资 )
# 序号(表示每月的第几个周几)，例如在周字段上设置"6#3"表示在每月的第三个周六.注意如果指定"#5",正好第五周没有周六，则不会触发该配置(用 在母亲节和父亲节再合适不过了)
小提示
周字段的设置，若使用英文字母是不区分大小写的 MON 与mon相同.
 
常用示例:
0 0 12 * * ? 每天12点触发
0 15 10 ? * * 每天10点15分触发
0 15 10 * * ? 每天10点15分触发
0 15 10 * * ? * 每天10点15分触发
0 15 10 * * ? 2005 2005年每天10点15分触发
0 * 14 * * ? 每天下午的 2点到2点59分每分触发
0 0/5 14 * * ? 每天下午的 2点到2点59分(整点开始，每隔5分触发)
0 0/5 14,18 * * ? 每天下午的 2点到2点59分(整点开始，每隔5分触发) 每天下午的 18点到18点59分(整点开始，每隔5分触发)
0 0-5 14 * * ? 每天下午的 2点到2点05分每分触发
0 10,44 14 ? 3 WED 3月分每周三下午的 2点10分和2点44分触发
0 15 10 ? * MON-FRI 从周一到周五每天上午的10点15分触发
0 15 10 15 * ? 每月15号上午10点15分触发
0 15 10 L * ? 每月最后一天的10点15分触发
0 15 10 ? * 6L 每月最后一周的星期五的10点15分触发
0 15 10 ? * 6L 2002-2005 从2002年到2005年每月最后一周的星期五的10点15分触发
0 15 10 ? * 6#3 每月的第三周的星期五开始触发
0 0 12 1/5 * ? 每月的第一个中午开始每隔5天触发一次
0 11 11 11 11 ? 每年的11月11号 11点11分触发
*/

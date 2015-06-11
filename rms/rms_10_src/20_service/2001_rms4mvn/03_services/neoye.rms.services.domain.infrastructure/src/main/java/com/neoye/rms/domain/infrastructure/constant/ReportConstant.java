package com.neoye.rms.domain.infrastructure.constant;

public class ReportConstant {
	// 报表类型
    public final static String REPORT_PEROID_TYPE_DAY= "4";//日
    public final static String REPORT_PEROID_TYPE_WEEK= "5";//周
    public final static String REPORT_PEROID_TYPE_MONTH= "6";//月
    public final static String REPORT_PEROID_TYPE_QUARTER= "7";//季度
    public final static String REPORT_PEROID_TYPE_YEAR= "8";//年

    // 处理状态
    public final static String REPORT_STATUS_OVER= "1";//处理完成
    public final static String REPORT_STATUS_TODO= "2";//待处理
    public final static String REPORT_STATUS_DOING= "3";//处理中
    public final static String REPORT_STATUS_OUT= "4";//过期
    
    // 管理状态 
    public final static String REPORT_MGR_STATUS_NULL= "0";//待处理 
    public final static String REPORT_MGR_STATUS_COUNT= "1";//统计 
    public final static String REPORT_MGR_STATUS_UPDATE= "2";//更新
    public final static String REPORT_MGR_STATUS_PROOF= "3";//校对
    public final static String REPORT_MGR_STATUS_AUDIT= "4";//审核
    public final static String REPORT_MGR_STATUS_ISSUE= "5";//签发
    public final static String REPORT_MGR_STATUS_UPLOAD= "6";//上报
    public final static String REPORT_MGR_STATUS_REBACK= "7";//回退
    
    // 上报方式
    public final static String REPORT_UPLOAD_MODE_REST = "1";
    public final static String REPORT_UPLOAD_MODE_FTP = "2";
    public final static String REPORT_UPLOAD_MODE_RESTANDFTP = "3";
    
    // 状态完成
    public final static String REPORT_OVER_STATUS_OVER="1";//完成
    public final static String REPORT_OVER_STATUS_NONE="0"; //未完成

    //  广播质量日报导出Excel字段顺序
    public final static String[] REPORT_BROAD_QUALITY = {"inputDatetime","condition","reason","language","stationfreq","stationName","machnum","remark","alarmLength","programName","startTime","endTime","proofName","centerName"};
    //  广播质量日报模板路径
    public final static String REPORT_BROAD_QUALITY_TEMPLATE_PATH = "srvDownloads/resource/";
    //  广播质量日报模板路径
    public final static String REPORT_BROAD_QUALITY_TEMPLATE_NAME = "reportTemplate.xls";
    //  广播质量日报导出存放临时路径
    public final static String REPORT_BROAD_QUALITY_TARGET_PATH = "srvDownloads/download/";
    //  广播质量日报导出存放临时路径
    public final static String REPORT_BROAD_QUALITY_TARGET_NAME = "template1.xls";
    
    //  三满日报导出Excel字段顺序
    public final static String[] REPORT_AUTHENTIC = {"inputDatetime","condition","reason","language","stationfreq","stationName","machnum","remark","alarmLength","programName","startTime","endTime","proofName","centerName"};
    //  三满日报模板路径
    public final static String REPORT_AUTHENTIC_TEMPLATE_PATH = "srvDownloads/resource/";
    //  三满日报模板路径
    public final static String REPORT_AUTHENTIC_TEMPLATE_NAME = "reportTemplate.xls";
    //  三满日报导出存放临时路径
    public final static String REPORT_AUTHENTIC_TARGET_PATH = "srvDownloads/download/";
    //  三满日报导出存放临时路径
    public final static String REPORT_AUTHENTIC_TARGET_NAME = "template1.xls";
    
}

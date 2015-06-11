package com.neoye.rms.domain.infrastructure.constant;

public class MonitorConstant {
	
	// 站点列表导出Excel字段顺序
    public final static String[] MONITOR_KEYS = {"monitorId","typeId","centerId","monitorCode","monitorName","monitorShortname","createTime","opstatusId","opstatusName","manufacturerId","projectType","versionId","deviceIp","address","inUse","cityCode","gid","locationArea","oppArea","cnrArea","localX","localY"};
    // 站点列表导出模板路径
    public final static String MONITOR_TEMPLATE_PATH = "srvDownloads/resource/";
	// 站点列表导出模板路径
    public final static String MONITOR_TEMPLATE_NAME = "monitorTemplate.xls";
    // 站点列表导出存放临时路径
    public final static String MONITOR_TARGET_PATH = "srvDownloads/download/";
	// 站点列表导出存放临时路径
    public final static String MONITOR_TARGET_NAME = "monitorTemplate1.xls";
}

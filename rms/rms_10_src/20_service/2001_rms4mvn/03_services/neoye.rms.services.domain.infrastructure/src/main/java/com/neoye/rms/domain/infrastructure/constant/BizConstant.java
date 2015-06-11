package com.neoye.rms.domain.infrastructure.constant;

public class BizConstant {
	
	/**
	 * 收测内容类型：广播
	 */
	public final static String KEY_RECEIVE_CONTENT_TYPE_BROADCAST = "3";
	
	/**
	 * 收测内容类型：实验
	 */
	public final static String KEY_RECEIVE_CONTENT_TYPE_TEST = "4";
	/**
	 * 周期：1 
	 */
	public final static String KEY_PERIOD = "1";
	/**
	 * 计划：2 
	 */
	public final static String KEY_PLAN = "2";
	
	// added by zh_yi begin
	//任务类型 :质量  dicType = 10007
    public final static String KEY_TASK_TYPE_ID_QUALITY = "1";
    
    //任务类型: 效果   dicType = 10007
    public final static String KEY_TASK_TYPE_ID_EFFECT = "2";
    
    //任务类型  综合: dicType = 10007
    public final static String KEY_TASK_TYPE_ID_COMMON = "3";
    //added by zh_yi end

	/**
	 * 站点类型：采集点 = 1 dicType = 30001
	 */
	public final static String KEY_MONITOR_TYPE_CAIJIDIAN = "1";
	/**
	 * 站点类型：遥控站  = 2 dicType = 30001
	 */
	public final static String KEY_MONITOR_TYPE_YAOKONGZHAN = "2";
	/**
	 * 站点类型：边境站 = 3 dicType = 30001
	 */
	public final static String KEY_MONITOR_TYPE_BIANJINGZHAN = "3";
	/**
	 * 站点类型：全景 = 4 dicType = 30001
	 */
	public final static String KEY_MONITOR_TYPE_QUANJING = "4";
	
	/**
	 * 历史异态事件类型：1：异态
	 */
	public final static String KEY_HISTORY_ABNORMAL_TYPE_ABNORMAL = "1"; 
	/**
     * 历史异态事件类型：2：历史异态事件
     */
    public final static String KEY_HISTORY_ABNORMAL_TYPE_HISTORYEVENT = "2"; 
    
    // 评估状态:0.一次审核;1.漏采;2.二次审核;3.已完成
    public final static String EVALUATION_ONE_VERIFY = "0"; // 一次审核
    public final static String EVALUATION_LOST = "1"; // 漏采
    public final static String EVALUATION_TWO_VERIFY = "2"; // 二次审核
    public final static String EVALUATION_OVER = "3"; // 已完成
    
	/**
     * 是：1
     */
    public final static String KEY_IS_YES = "1";
    
    /**
     * 否：0
     */
    public final static String KEY_IS_NO = "0";
    /**
     * 运行图及任务 数据状态---当前有效
     */
    public final static String DATA_STATUS_EFFECTIVE = "0";
    /**
     * 运行图及任务 数据状态---预设
     */
    public final static String DATA_STATUS_PREINSTALL = "1";
    /**
     * 运行图及任务 数据状态---过期
     */
    public final static String DATA_STATUS_PASTDUE = "2";
    /**
     * 运行图及任务 数据状态---删除
     */
    public final static String DATA_STATUS_DELETE = "3";
    /**
     * 效果合格
     */
    public final static String EFFECT_QUALIFIED = "2";
    /**
     * 效果不合格
     */
    public final static String EFFECT_DISQUALIFICATION = "1";
}

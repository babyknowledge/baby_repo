package rms.cmpnt.libs.commons;

/**
 * 项目名称: NetPatrol5.1 IT监管系统<br>
 * 模块名称: 整个np <br>
 * 功能描述: 常量信息码配置文件 <br>
 * 创建日期: 2009-8-3 <br>
 * 版权信息: Copyright (c) 2009<br>
 * 公司信息: 东软集团股份有限公司 电信事业部研发二部<br>
 * 
 * @author <a href="mailto: yang_xg@neusoft.com">杨晓光</a>
 * @version v1.0
 * 
 * <pre>
 * 修改历史
 *   序号      日期          修改人       修改原因
 *    1    2009-8-3       杨晓光      创建
 * </pre>
 * 
 */
public class CodeConstant
{

	/**
	 * spring中sqlMapClient标识,Oracle-sqlMapClientHistory
	 */
	public final static String SQLMAPCLIENT_4_ORA = "sqlMapClientHistory";
	/**
	 * spring中sqlMapClient标识,PG-sqlMapClientFile
	 */
	public final static String SQLMAPCLIENT_4_PG = "sqlMapClientFile";
	/**
	 * spring中sqlMapClient标识,H2-sqlMapClientFile_H2
	 */
	public final static String SQLMAPCLIENT_4_H2 = "sqlMapClientFile_H2";
	/**
	 * spring中sqlMapClient标识,H2_Memory-sqlMapClientMem
	 */
	public final static String SQLMAPCLIENT_4_H2_MEM = "sqlMapClientMem";
	
	/**
	 * spring中BaseDAO标识
	 */
	public final static String BASE_DAO = "baseDAO" ; 
	
	public final static String MQ_SEND_TO_DPP_MSG_TOOL = "mqSendToDPPMsgTool";
	
	/**
	 * Flex接口参数Key定义 key:status
	 */
	public final static String MESSAGE_STATUS = "status";
	
	
	/**
	 * Flex接口参数Key定义 key:message
	 */
	public final static String MESSAGE_MSG = "message";
	
	/**
	 * Flex接口参数Key定义 key:data
	 */
	public final static String MESSAGE_DATA = "data";
	
	/**
	 * Flex接口状态编码 default:0
	 */
	public final static String STATUS_CODE_DEFAULT = "0";
	
	/**
	 * Flex接口状态编码  未知 错误:505
	 */
	public final static String STATUS_CODE_ERROR = "-505";
	
	/**
	 * Flex接口消息内容-缺省内容
	 */
	public final static String  MSG_DEFAULT = "操作成功！";
	
	
	/**
	 * Flex接口消息内容-错误内容 
	 */
	public final static String  MSG_ERROR = "温馨提示:服务接口故障,请稍后再试.";
	
	/**
	 * Flex接口状态编码  添加或修改实例出现重复实例名称代码：5001
	 */
	public final static String STATUS_CODE_DUP_INS = "5001";
	/**
	 * Flex接口消息内容-错误内容-添加或修改实例时实例名称重复
	 */
	public final static String MSG_ERROR_DUP_INS = "已存在相同名称的实例";
	/**
	 * ibatis-key MonitorSettingsDAO.retrieveServerType
	 */
	public final static String SQL_ID_RETRIEVESERVERTYPE = "MonitorSettingsDAO.retrieveServerType" ; 
	
	/**
	 * ibatis-key MonitorSettingsDAO.retrieveServerType
	 */
	public final static String SQL_ID_RETRIEVESERVERITEM = "MonitorSettingsDAO.retrieveServerItem" ;
	
	/**
	 * ibatis-key DicDataDAO.retrieveDicData
	 */
	public final static String SQL_ID_RETRIEVEDICDATA = "DicDataDAO.retrieveDicData";
	
	
	/**
	 * ibatis-key DicDataDAO.retrieveSwitchData
	 */
	public final static String SQL_ID_RETRIEVESWITCHDATA = "DicDataDAO.retrieveSwitchData";
	
	/**
	 * map-key resultCount
	 */
	public final static String RESULT_COUNT_KEY = "resultCount";
	
	/**
	 * map-key pageCount
	 */
	public final static String PAGE_COUNT_KEY = "pageCount";
	
	/**
	 * map-key currentPage
	 */
	public final static String CURRENT_PAGE_KEY = "currentPage";
	/**
	 * map-key switchType
	 */
	public final static String SWITCHTYPE_KEY = "switchType";
	
	/**
	 * SQL true= t
	 */
	public final static String VALUE_TRUE_4_SQL = "t";
	/**
	 * SQL false= f
	 */
	public final static String VALUE_FALSE_4_SQL = "f";
	
	/**
	 * 秒 = 1
	 */
	public final static int VALUE_SECOND = 1;
	/**
	 * 分  = 2
	 */
	public final static int VALUE_MINUTE = 2;
	/**
	 * 时  = 3
	 */
	public final static int VALUE_HOUR = 3;
	/**
	 * 天  = 4
	 */
	public final static int VALUE_DAY = 4;
	/**
	 * 周  = 5
	 */
	public final static int VALUE_WEEK = 5;
	/**
	 * 月  = 6
	 */
	public final static int VALUE_MONTH = 6;
	/**
	 * 季  = 7
	 */
	public final static int VALUE_SEASON = 7;
	/**
	 * 年  = 8
	 */
	public final static int VALUE_YEAR = 8;
	
    /**
     * downlaod
     */
    public final static String WEB_INF = "WEB-INF";
	
}

package rms.cmpnt.libs.commons.util;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


/**
 * 项目名称: NetPatrol5.0 IT监管系统<br>
 * 模块名称: 实时性能 <br>
 * 功能描述: 加载错误信息的配置文件，用于从配置文件中获取异常信息 <br>
 * 创建日期: 2009-8-3 <br>
 * 版权信息: Copyright (c) 2009<br>
 * 公司信息: 东软集团股份有限公司 电信事业部研发二部<br>
 * 
 * @author <a href="mailto: liu.shsh@neusoft.com">刘沙沙</a>
 * @version v1.0
 * 
 * <pre>
 * 修改历史
 *   序号      日期          修改人       修改原因
 *    1    2009-8-3       刘沙沙      创建
 * </pre>
 */
public class AppMessageUtil {

    private static String file = "/classes/config/nms/common/properties/AppMessage.properties";// 信息配置文件

    private static PropertiesUtil util;
    
    private static final Log log = LogFactory.getLog(AppMessageUtil.class);

    public AppMessageUtil() {
    }

    /**
     * 加载performance.properties文件
     */
    private static void getAppMessageProperties() {
        util = PropertiesUtil.getInstance();
        try {
            util.setFilePath(file);
        }
        catch (IOException e) {
            log.error("加载AppMessage.properties文件失败！"+e.getMessage());
        }
    }

    /**
     * 把异常信息封装成xml的文档对象
     * 
     * @param message
     *            异常信息字符串
     * @return 封装异常信息的xml字符串
     */
    private static String doCreateErrorXml(String message) {
        String xml = "";
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");
        Element element = root.addElement("message");
        element.setText(message == null ? "" : message);
        xml = document.asXML();
        
        //释放内存
        document=null;
        root=null;
        element=null;
        
        return xml;
    }

    /**
     * 根据错误信息码，从配置文件中获取异常信息,并封装成xml格式字符串
     * 
     * @param code
     *            错误信息码
     * @return 封装异常信息的xml字符串
     */
    public static String getXmlErrorMessage(String code) {
        String message = "";
        message = getStringErrorMessage(code);
        message = doCreateErrorXml(message);
        return message;
    }

    /**
     * 根据错误信息码，从配置文件中获取异常信息的字符串
     * 
     * @param code
     *            错误信息码
     * @return 封装异常信息的xml字符串
     */
    public static String getStringErrorMessage(String code) {
        if (code == null || code.equals("")) {
            return null;
        }
        getAppMessageProperties();
        return util.getProperty(code);
    }

    /**
     * 把错误信息封装成xml格式字符串
     * 
     * @param message
     *            错误信息
     * @return 封装异常信息的xml字符串
     */
    public static String getXmlErrorByMessage(String message) {
        message = doCreateErrorXml(message);
        return message;
    }
}

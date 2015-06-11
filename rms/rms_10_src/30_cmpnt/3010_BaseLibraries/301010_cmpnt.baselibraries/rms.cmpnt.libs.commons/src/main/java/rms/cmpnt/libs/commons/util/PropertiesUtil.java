package rms.cmpnt.libs.commons.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 项目名称: NetPatrol5.0 IT监管系统<br>
 * 模块名称: 工具类<br>
 * 功能描述: 属性文件读写工具类<br>
 * 创建日期: 2009-8-3 <br>
 * 版权信息: Copyright (c) 2009<br>
 * 公司信息: 东软集团股份有限公司 电信事业部研发二部<br>
 * 
 * @author <a href="mailto: zhang-cm@neusoft.com">张翠敏</a>
 * @version v1.0
 * 
 *          <pre>
 * 修改历史
 *   序号      日期          修改人       修改原因
 *    1    2009-8-3         张翠敏       创建
 * </pre>
 */
@SuppressWarnings("unchecked")
public class PropertiesUtil {

	private static final Log log = LogFactory.getLog(PropertiesUtil.class);

	private String filePath = "";

	private InputStream in = null;

	private Properties props = new Properties();

	private static boolean encode = false;

	private static PropertiesUtil instance = null; // 实例化该类的对象

	public static PropertiesUtil getInstance() {
		if (instance == null) {
			instance = new PropertiesUtil();
		}
		return instance;
	}

	/**
	 * 获取web-inf下的properties文件的路径
	 * 
	 * @return 路径名称
	 */
	public static String getBasePath() {
		String lsPath = PropertiesUtil.class
				.getResource("PropertiesUtil.class").getFile();
		for (int i = 0; i < 6; i++) {
			lsPath = lsPath.substring(0, lsPath.lastIndexOf("/"));
		}

		try {
			lsPath = java.net.URLDecoder.decode(lsPath, "utf-8");

		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());

		}
		if (System.getProperty("os.name").indexOf("Windows") != -1) {
			if (lsPath.startsWith("/")) {
				lsPath = lsPath.substring(1);
			}
		}
		return lsPath;

	}

	public String getFilePath() {
		return filePath;
	}

	/**
	 * 设置properties文件路径
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	public void setFilePath(String filePath) throws IOException {
		this.filePath = getBasePath() + filePath;
		in = new BufferedInputStream(new FileInputStream(this.filePath));
		props.load(in);
	}

	/**
	 * 获取属性文件中所有key和value
	 * 
	 * @return 所有属性构成的hashtable
	 */
	public Hashtable getHtProperties() {
		Hashtable hash = new Hashtable();
		Enumeration propEnum = props.propertyNames();
		while (propEnum.hasMoreElements()) {
			String key = (String) propEnum.nextElement();
			String value = (String) props.getProperty(key);
			hash.put(key, value);// 封装Properties对象。
		}
		return hash;
	}

	/**
	 * 获取属性文件中所有key和value
	 * 
	 * @return 所有属性构成的properties
	 */
	public Properties getProperties() {
		Properties pos = new Properties();
		Enumeration propEnum = props.propertyNames();
		while (propEnum.hasMoreElements()) {
			String key = (String) propEnum.nextElement();
			String value = (String) props.getProperty(key);
			pos.setProperty(key, value);// 封装Properties对象。
		}
		return pos;
	}

	/**
	 * 获取某一key值的value
	 * 
	 * @param key
	 * @return 某一key值的value
	 */
	public String getProperty(String key) {
		String value = props.getProperty(key);
		if (value == null) {
			value = "";
		} else {
			if (encode) {
				try {
					value = new String(value.getBytes("ISO8859_1"), "gb2312");
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		}
		return value;
	}

	public static boolean ifEncode() {
		return encode;
	}

	public static void setEncode(boolean iencode) {
		encode = iencode;
	}
}

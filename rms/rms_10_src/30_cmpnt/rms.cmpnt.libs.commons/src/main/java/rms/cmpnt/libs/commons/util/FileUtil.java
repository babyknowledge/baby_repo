/*
 * Created on 2005-3-15
 * 
 * TODO To change the template for this generated file go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
/**
 * 按照给定的目录和正则表达式，返回满足正则表达式要求的文件名列表 在目录末尾补上斜杠，根据系统不同，补充的分割符不同
 * 检查目录dir，并且创建目录，如果父目录不存在，那么创建 在给定的文件中追加文件内容 在给定的文件中追加文件内容，使用给定的字符编码 写入文件，不能追加
 * 判断给定的文件是否变化 在给定的目录中，如果有超过给定日期给定天数的文件，删除。 如果删除了文件，返回真。
 * 获取WEB-INF路径，例如：获取应用路径，例如C:/neusoft/tomcat6.0.13/webapps/patrol/WEB-INF
 * 获取应用路径，例如C:/neusoft/tomcat6.0.13/webapps/patrol 获得tomcat的temp目录 根路径，例如：C:/或者/
 * 获取服务器端的classes目录路径 获取服务器端真实路径 取得目录下最新的文件 清除目录下的文件，只保留固定个数的文件，其余的删除 通过 class
 * loader的相对路径获得 文件流 通过 class loader的相对路径获得一个Properties文件的 Properties的对象.
 * 获取配置的系统信息 获取J2EE版本
 */
package rms.cmpnt.libs.commons.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import rms.cmpnt.libs.commons.exception.CommonException;

/**
 * 
 * 项目名称: NetPatrol5.0 IT监管系统<br>
 * 模块名称: 工具类<br>
 * 功能描述: 文件处理相关工具类<br>
 * 创建日期: 2009-8-3 <br>
 * 版权信息: Copyright (c) 2009<br>
 * 公司信息: 东软集团股份有限公司 电信事业部研发二部<br>
 * 
 * @author <a href="mailto: pan-hl@neusoft.com">潘洪亮</a> <br>
 * @version v1.0
 * 
 * <pre>
 * 修改历史
 *   序号      日期          修改人       修改原因
 *   1 2006-4-11 15:12:16 潘洪亮 创建
 * <br>
 *  2 2007-3-15 董志刚 增加文件操作的方法。以完成数据存储的应用要求。
 * <br>
 *  3 2007-8-8 董志刚 解决Spring读取配置文件的bug，不同平台的绝对路径，给定的值不一样。
 * <br>
 * 在unix平台上，Spring将第一个/字符去掉了，解决的方法是在前面在增加一个/字符。
 * <br>
 * 
 */
@SuppressWarnings("unchecked")
public class FileUtil {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(FileUtil.class);
    
    private static java.util.Properties sysParams = null;

    /**
     * 私有构造方法,防止类的实例化
     */
    private FileUtil() {
    }

    /**
     * 按照给定的目录和正则表达式，返回满足正则表达式要求的文件名列表；<br>
     * 注意：只能列举当前目录，不能列举子目录。
     * 
     * @param as_rootPaht
     *            String目录
     * @param as_regex
     *            String 正则表达式
     * @return String[]
     * @throws CommonException
     */
    public static String[] listFiles(String as_rootPath, String as_regex) throws CommonException {

        File rootPath = null;
        String[] sa_ret = null;
        ArrayList al = new ArrayList();
        File[] files = null;

        try {
            rootPath = new File(as_rootPath);
            if (!rootPath.isDirectory()) {
                return null;
            }
            as_rootPath = addSeparatorTail(as_rootPath);
            files = rootPath.listFiles(); // 列表文件

            for (int i = 0; i < files.length; i++) {
                if (new File(as_rootPath + files[i]).isDirectory()) {
                    continue;
                }
                Pattern p = Pattern.compile(as_regex);
                Matcher m = p.matcher(files[i].getName());
                if (m.find()) {
                    // System.out.println("files[" + i + "]=" + files[i] + "\t
                    // path=" + files[i].getPath() + "\t name=" +
                    // files[i].getName());
                    // 解决Spring在读取配置文件上的绝对路径平台不一致性的bug！
                    if (System.getProperty("file.separator").equals("/")) {
                        al.add("/" + files[i].getPath());
                    }
                    else {
                        al.add(files[i].getPath());
                    }
                    // al.add(files[i].getPath()); // Windows环境下使用
                    // al.add(files[i]); // linux 环境下使用
                    // al.add(files[i].getName()); // 在bin目录下的方式
                }
            }
            if (al.size() <= 0) {
                return null;
            }
            sa_ret = new String[al.size()];
            al.toArray(sa_ret);

            // System.out.println("全部文件为：" + sa_ret.length);
            // for(int i=0;i<sa_ret.length;i++){
            // System.out.println(sa_ret[i]);
            // }

        }
        catch (NullPointerException ne) {
            // ne.printStackTrace();
            throw new CommonException(ne);
        }
        catch (SecurityException e) {
            // e.printStackTrace();
            throw new CommonException(e);
        }
        catch (ArrayStoreException ae) {
            throw new CommonException(ae);
        }

        return sa_ret;
    }

    /**
     * 在目录末尾补上斜杠，根据系统不同，补充的分割符不同。
     * 
     * @param aS_dir
     *            目录
     * @return 补上斜杠的目录(String)
     */
    public static final String addSeparatorTail(String as_dir) {

        String s_temp = as_dir.trim();
        if (!s_temp.endsWith("\\") && !s_temp.endsWith("/")) {
            if (s_temp.indexOf("\\") >= 0) {
                s_temp = s_temp + "\\";
            }
            else {
                s_temp = s_temp + "/";
            }
        }
        return s_temp;

    }

    /**
     * 检查目录dir，并且创建目录，如果父目录不存在，那么创建
     * 
     * @param dir
     * @throws IOException
     */
    public static void forceMkdir(File dir) throws IOException {
        FileUtils.forceMkdir(dir);
    }

    /**
     * 在给定的文件中追加文件内容
     * 
     * @param filename
     * @param content
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void appendToFile(File filename, String content) throws FileNotFoundException,
            IOException {
        appendToFile(filename, content.getBytes());
    }

    /**
     * 在给定的文件中追加文件内容，使用给定的字符编码
     * 
     * @param filename
     * @param content
     * @param encoding
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void appendToFile(File filename, String content, String encoding)
            throws FileNotFoundException, IOException {
        appendToFile(filename, content.getBytes(), encoding);
    }

    /**
     * 在给定的文件中追加文件内容，使用给定的字符编码
     * 
     * @param filename
     * @param content
     * @param encoding
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void appendToFile(File filename, byte[] content, String encoding)
            throws FileNotFoundException, IOException {
        FileOutputStream output = new FileOutputStream(filename, true);
        output.write(new String(content, encoding).getBytes());
    }

    /**
     * 在给定的文件中追加文件内容
     * 
     * @param filename
     * @param content
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void appendToFile(File filename, byte[] content) throws FileNotFoundException,
            IOException {
        FileOutputStream output = new FileOutputStream(filename, true);
        output.write(content);
    }

    /**
     * 写入文件，不能追加
     * 
     * @param filename
     * @param content
     * @throws IOException
     */
    public static void writeToFile(File filename, String content) throws IOException {
        FileUtils.writeStringToFile(filename, content);
    }

    /**
     * 写入文件，不能追加
     * 
     * @param filename
     * @param content
     * @throws IOException
     */
    public static void writeToFile(File filename, byte[] content) throws IOException {
        FileUtils.writeByteArrayToFile(filename, content);
    }

    /**
     * 写入文件，不能追加，给定特定编码
     * 
     * @param filename
     * @param content
     * @param encoding
     * @throws IOException
     */
    public static void writeToFile(File filename, String content, String encoding)
            throws IOException {
        FileUtils.writeStringToFile(filename, content, encoding);
    }

    /**
     * 写入文件，不能追加，给定特定编码
     * 
     * @param filename
     * @param content
     * @param encoding
     * @throws IOException
     */
    public static void writeToFile(File filename, byte[] content, String encoding)
            throws IOException {
        FileUtils.writeStringToFile(filename, new String(content), encoding);
    }

    /**
     * 判断给定的文件是否变化
     * 
     * @param filename
     * @param timeMillis
     */
    public static boolean isFileNewer(File filename, long timeMillis) {
        return FileUtils.isFileNewer(filename, timeMillis);
    }

    /**
     * 判断给定的文件是否变化
     * 
     * @param filename
     * @param date
     * @return
     */
    public static boolean isFileNewer(File filename, Date date) {
        return FileUtils.isFileNewer(filename, date);
    }

    /**
     * 在给定的目录中，如果有超过给定日期给定天数的文件，删除。 如果删除了文件，返回真。
     * 
     * @param dir
     * @param days
     * @param date
     * @return boolean
     * @throws IOException
     */
    public static boolean deleteFileDays(File dir, int days, Date date) throws IOException {
        boolean flag = false;
        long terminalDate = date.getTime() - ((long) days * 24 * 60 * 60 * 1000);
        Collection coll = FileUtils.listFiles(dir, null, false);
        Iterator iter = coll.iterator();
        while (iter.hasNext()) {
            File one = (File) iter.next();
            if (FileUtils.isFileOlder(one, terminalDate)) { // 文件比当前日期旧
                flag = true;
                FileUtils.forceDelete(one);
            }
        }
        return flag;
    }

    /**
     * 在给定的目录中，如果有超过给定日期给定天数的文件，删除。 如果删除了文件，返回真。
     * 
     * @param dir
     * @param days
     * @return
     * @throws IOException
     */
    public static boolean deleteFileDays(File dir, int days) throws IOException {
        return deleteFileDays(dir, days, new Date());
    }

    /**
     * 获取WEB-INF路径，例如：获取应用路径，例如C:/neusoft/tomcat6.0.13/webapps/patrol/WEB-INF
     * 
     * @return ~/WEB-INF/
     */
    public static String getWebinfoPath() {
        // remove string "classes/"
        return getClassPath().substring(0, getClassPath().length() - 8);
    }

    /**
     * 获取应用路径，例如C:/neusoft/tomcat6.0.13/webapps/patrol
     * 
     * @return application "s path
     */
    public static String getAppPath() {
        String[] s = getWebinfoPath().split("/");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length - 1; i++) {
            sb.append(s[i] + "/");
        }
        return sb.toString();
    }

    /**
     * 获得tomcat的temp目录。
     * 
     * @return String
     */
    public static String getTempPath() {
        String[] s = getAppPath().split("/");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length - 2; i++) {
            sb.append(s[i] + "/");
        }
        sb.append("temp").append("/");
        return sb.toString();
    }

    /**
     * 根路径，例如：C:/或者/
     * 
     * @return root "s path
     */
    public static String getRootPath() {
        return getClassPath().split("/")[0] + "/";
    }

    /**
     * 获取服务器端的classes目录路径
     * 例如：C:/neusoft/tomcat6.0.13/webapps/patrol/WEB-INF/classes
     * 
     * @return ~/classes/
     */
    public static String getClassPath() {
        FileUtil j = new FileUtil();
        String t = j.getRealPath();
        if (System.getProperty("file.separator").equals("/")) {
            t = t.replaceAll("file:", ""); // linux,unix
        }
        else {
            t = t.replaceAll("file:/", ""); // windows
        }
        t = t.replaceAll("wsjar:", ""); // websphere wsjar: has to at jar:
        // before
        t = t.replaceAll("jar:", ""); // tomcat,jboss,resin,wasce,apusic
        t = t.replaceAll("zip:", ""); // weblogic
        t = t.replaceAll("/./", "/"); // weblogic
        // if this class be included .jar file,will replace "/lib/*.!/" to
        // "/classes/"
        // t=t.replaceAll("/lib/([^\ " "]+)!/","/classes/"); // jar
        t = t.split("/classes/")[0] + "/classes/";
        try {
            t = java.net.URLDecoder.decode(t, "utf-8");
        }
        catch (UnsupportedEncodingException e) {
            logger.error("配置文件目录编码错误：" + e.getMessage());
        }
        logger.info("返回的路径为：" + t);
        return t;
    }

    /**
     * 获取服务器端真实路径
     * 
     * @return strURL String类型
     */
    private String getRealPath() {
        String strClassName = getClass().getName();
        String strPackageName = "";
        if (getClass().getPackage() != null) {
            strPackageName = getClass().getPackage().getName();
        }
        String strClassFileName = "";
        if (!"".equals(strPackageName)) {
            strClassFileName = strClassName.substring(strPackageName.length() + 1, strClassName
                    .length());
        }
        else {
            strClassFileName = strClassName;
        }
        URL url = getClass().getResource(strClassFileName + ".class");
        String strURL = url.toString();
        strURL = strURL.replaceAll("%20", "   ");
        logger.info("从服务器端获取的绝对真实路径为：" + strURL);
        return strURL;
    }

    /**
     * 取得目录下最新的文件
     * 
     * @param path
     * @return String
     * 
     */
    public static String getLastFile(String path) {
        File lastFile = null;
        Collection coll = FileUtils.listFiles(new File(path), null, false);
        Iterator iter = coll.iterator();
        while (iter.hasNext()) {
            File one = (File) iter.next();
            if (lastFile == null) {
                lastFile = one;
                continue;
            }
            if (FileUtils.isFileNewer(one, lastFile)) {
                lastFile = one;
            }
        }
        if (lastFile == null) {
            return null;
        }
        return lastFile.getPath();
    }

    /**
     * 清除目录下的文件，只保留固定个数的文件，其余的删除
     * 
     * @param path
     *            String类型
     * @param keepNums
     *            int类型
     * @return
     * @throws IOException
     */
    public static void deleteFiles(String path, int keepNums) throws IOException {
        Collection coll = FileUtils.listFiles(new File(path), null, false);
        Iterator iter = coll.iterator();
        if (coll.size() <= keepNums) {
            return;
        }
        Map map = new TreeMap();
        System.out.println(coll.size());

        while (iter.hasNext()) {
            File file = (File) iter.next();
            Long ltime = file.lastModified();
            map.put(ltime, file);
        }
        int i = 0;
        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
            i++;
            Long time = (Long) iterator.next();
            System.out.println(new Timestamp(time.longValue()) + "      "
                    + ((File) map.get(time)).getName());
            if (i <= coll.size() - keepNums) {
                // FileUtils.forceDelete((File) map.get(time));
                ((File) map.get(time)).delete();
                System.err.println("删除文件" + ((File) map.get(time)).getPath());
            }
            else {
                System.out.println(" 保留文件" + ((File) map.get(time)).getPath());
            }
        }

    }

    /**
     * 通过 class loader的相对路径获得 文件流
     * 
     * @param source
     *            Path 相对classloader的路径: 如: com/neusoft/common/config.xml
     */
    public static InputStream getStream(String sourcePath) {
        ClassLoader _loader = FileUtil.class.getClassLoader();
        return _loader.getResourceAsStream(sourcePath);
    }

    /**
     * 通过 class loader的相对路径获得一个Properties文件的 Properties的对象.
     * 
     * @param source
     *            Path 相对classloader的路径: 如: com/neusoft/common/config.xml
     */
    public static Properties getProperties(String sourcePath) throws IOException {
        InputStream in = getStream(sourcePath);

        if (in == null)
            throw new IOException(sourcePath + "文件没有找到! 获取文件流为空.");

        Properties props = new Properties();
        props.load(in);

        return props;
    }

    /**
     * 获取配置的系统信息 数据配置在SysParam.properties文件中. 如果没有找到字符的值,返回NULL
     * 
     * @param paramKey
     *            String类型
     * @return String类型
     */
    public static String getSysParams(String paramKey) {
        if (sysParams == null)
            try {
                sysParams = FileUtil.getProperties("SysParams.properties");
            }
            catch (Exception e) {
                logger.error("getSysParams:" + e.getMessage());
                return null;
            }

        return sysParams.getProperty(paramKey);
    }

    /**
     * 获取配置的系统信息 数据配置文件中. 如果没有找到字符的值,返回NULL
     * 
     * @param properName
     *            String类型
     * @param paramKey
     *            String类型
     * @return String类型
     */
    public static String getConfigParams(String properName, String paramKey) {

        Properties configParams = new Properties();

        try {
            configParams = FileUtil.getProperties(properName);
        }
        catch (Exception e) {
            logger.error("getConfigParams:" + e.getMessage());
            return null;
        }

        return configParams.getProperty(paramKey);
    }

    /**
     * 获取J2EE版本 配置在 SysParam.properties
     * 
     * @return Short类型
     */
    public static short getJ2eeVersion() {

        String j2eeVersion = getSysParams("J2EE_VERSION");

        if (j2eeVersion == null)
            return 140;

        return Short.parseShort(j2eeVersion);
    }
    
    /**
     * 将文件流保存成文件
     * 
     * @param steam
     * @param fileUrl
     * @param fileName
     * @return
     */
    public static String uploadFile(byte[] steam,String fileUrl,String fileName) {
		if (steam != null) {
			File dir = new File(fileUrl);
			if (!dir.exists())
				dir.mkdirs();

			String filePath = fileUrl + fileName;
			File file = new File(filePath);
			if (file.exists()) {
				file.delete();
			}
			try {
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(steam, 0, steam.length);
				fos.flush();
				fos.close();
				return filePath;
			}
			catch (FileNotFoundException e) {
				logger.error("打开文件失败!");
				return "0";
			}
			catch (IOException e) {
				logger.error("保存文件失败!");
				return "0";
			}
		}
		return null;
	}
}

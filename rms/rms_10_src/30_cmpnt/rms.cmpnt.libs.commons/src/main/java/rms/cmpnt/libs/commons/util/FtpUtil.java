package rms.cmpnt.libs.commons.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtil {

    private static FTPClient ftpClient = new FTPClient();

    private static String encoding = System.getProperty("file.encoding");

    /**
     * Description: 向FTP服务器上传文件
     * 
     * @Version1.0
     * @param url FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param path FTP服务器保存目录,如果是根目录则为“/”
     * @param localPath 上传到FTP服务器上的文件名
     * @param filename 本地文件输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String hostname, int port, String username, String password,
            String path, String localPath, String filename) {
        boolean result = false;
        FileInputStream in = null;
        try {
            int reply;
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            //ftpClient.connect(url);
            ftpClient.connect(hostname, port);
            // ftp.connect(url, port);// 连接FTP服务器
            // 登录
            ftpClient.login(username, password);
            ftpClient.setControlEncoding(encoding);
            
            // 检验是否连接成功
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                System.out.println("连接失败");
                ftpClient.disconnect();
                return result;
            }
            // 转移工作目录至指定目录下
            ftpClient.makeDirectory(path);
            boolean change = ftpClient.changeWorkingDirectory(path);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            if (change) {
                if (!localPath.endsWith("/")) {
                    localPath = localPath + "/";
                }
                in = new FileInputStream(new File(localPath + filename));
                result = ftpClient.storeFile(new String(filename.getBytes(encoding), "iso-8859-1"), in);
                if (result) {
                    System.out.println("上传成功!");
                }
            }
            ftpClient.logout();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("关闭上传文件流失败!");
                }
            }
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                }
                catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    /**
     * Description: 从FTP服务器下载文件
     * 
     * @param url FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param fileName 要下载的文件名
     * @param localPath 下载后保存到本地的路径
     * @return
     */
    public static boolean downloadFile(String hostname, int port, String username, String password,
            String remotePath, String fileName, String localPath) {
        boolean result = false;
        try {
            int reply;
            ftpClient.setControlEncoding(encoding);
            /* 
             * 为了上传和下载中文文件，有些地方建议使用以下两句代替 
             * new String(remotePath.getBytes(encoding),"iso-8859-1")转码。 
             * 经过测试，通不过。 
             */
            // FTPClientConfig conf = new
            // FTPClientConfig(FTPClientConfig.SYST_NT);
            // conf.setServerLanguageCode("zh");
            ftpClient.connect(hostname, port);
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftpClient.login(username, password);// 登录
            // 设置文件传输类型为二进制
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 获取ftp登录应答代码
            reply = ftpClient.getReplyCode();
            // 验证是否登陆成功
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                System.err.println("FTP server refused connection.");
                return result;
            }
            // 转移到FTP服务器目录至指定的目录下
            ftpClient.changeWorkingDirectory(new String(remotePath.getBytes(encoding), "iso-8859-1"));
            // 获取文件列表
            FTPFile[] fs = ftpClient.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    if (!localPath.endsWith("/")) {
                        localPath = localPath + "/";
                    }
                    File localFile = new File(localPath + ff.getName());
                    OutputStream is = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(ff.getName(), is);
                    is.close();
                    break;
                }
            }
            ftpClient.logout();
            result = true;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                }
                catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    ///////////////////////////////////////////////////////////////////////////////////

    public static final String ANONYMOUS_LOGIN = "anonymous";

    private boolean is_connected = false;

    public void setFtpUtil(int defaultTimeoutSecond, int connectTimeoutSecond, int dataTimeoutSecond) {
        ftpClient.setDefaultTimeout(defaultTimeoutSecond * 1000);
        ftpClient.setConnectTimeout(connectTimeoutSecond * 1000);
        ftpClient.setDataTimeout(dataTimeoutSecond * 1000);
    }

    /**
     * Connects to FTP server.
     * 
     * @param host FTP server address or name
     * @param port FTP server port
     * @param user user name
     * @param password user password
     * @param isTextMode text / binary mode switch
     * @throws IOException on I/O errors
     */
    public void connect(String host, int port, String user, String password, boolean isTextMode)
            throws IOException {
        // Connect to server.
        try {
            ftpClient.connect(host, port);
        }
        catch (UnknownHostException ex) {
            throw new IOException("Can't find FTP server '" + host + "'");
        }

        // Check rsponse after connection attempt.
        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            disconnect();
            throw new IOException("Can't connect to server '" + host + "'");
        }

        if (user == "") {
            user = ANONYMOUS_LOGIN;
        }

        // Login.
        if (!ftpClient.login(user, password)) {
            is_connected = false;
            disconnect();
            throw new IOException("Can't login to server '" + host + "'");
        }
        else {
            is_connected = true;
        }

        // Set data transfer mode.
        if (isTextMode) {
            ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
        }
        else {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        }
    }

    /**
     * Uploads the file to the FTP server.
     * 
     * @param ftpFileName server file name (with absolute path)
     * @param localFile local file to upload
     * @throws IOException on I/O errors
     */
    public void upload(String ftpFileName, File localFile) throws IOException {
        // File check.
        if (!localFile.exists()) {
            throw new IOException("Can't upload '" + localFile.getAbsolutePath()
                    + "'. This file doesn't exist.");
        }
        // Upload.
        InputStream in = null;
        try {
            // Use passive mode to pass firewalls.
            ftpClient.enterLocalPassiveMode();

            in = new BufferedInputStream(new FileInputStream(localFile));
            if (!ftpClient.storeFile(ftpFileName, in)) {
                throw new IOException("Can't upload file '" + ftpFileName + "' to FTP server. Check FTP permissions and path.");
            }
        }
        finally {
            try {
                in.close();
            }
            catch (IOException ex) {
            }
        }
    }

    /**
     * Downloads the file from the FTP server.
     * 
     * @param ftpFileName server file name (with absolute path)
     * @param localFile local file to download into
     * @throws IOException on I/O errors
     */
    public void download(String ftpFileName, File localFile) throws IOException {
        // Download.
        OutputStream out = null;
        try {
            // Use passive mode to pass firewalls.
            ftpClient.enterLocalPassiveMode();

            // Get file info.
            FTPFile[] fileInfoArray = ftpClient.listFiles(ftpFileName);
            if (fileInfoArray == null) {
                throw new FileNotFoundException("File " + ftpFileName
                        + " was not found on FTP server.");
            }

            // Check file size.
            FTPFile fileInfo = fileInfoArray[0];
            long size = fileInfo.getSize();
            if (size > Integer.MAX_VALUE) {
                throw new IOException("File " + ftpFileName + " is too large.");
            }

            // Download file.
            out = new BufferedOutputStream(new FileOutputStream(localFile));
            if (!ftpClient.retrieveFile(ftpFileName, out)) {
                throw new IOException("Error loading file " + ftpFileName
                        + " from FTP server. Check FTP permissions and path.");
            }

            out.flush();
        }
        finally {
            if (out != null) {
                try {
                    out.close();
                }
                catch (IOException ex) {
                }
            }
        }
    }

    /**
     * Removes the file from the FTP server.
     * 
     * @param ftpFileName server file name (with absolute path)
     * @throws IOException on I/O errors
     */
    public void remove(String ftpFileName) throws IOException {
        if (!ftpClient.deleteFile(ftpFileName)) {
            throw new IOException("Can't remove file '" + ftpFileName + "' from FTP server.");
        }
    }

    /**
     * Lists the files in the given FTP directory.
     * 
     * @param filePath absolute path on the server
     * @return files relative names list
     * @throws IOException on I/O errors
     */
    public List<String> list(String filePath) throws IOException {
        List<String> fileList = new ArrayList<String>();

        // Use passive mode to pass firewalls.
        ftpClient.enterLocalPassiveMode();

        FTPFile[] ftpFiles = ftpClient.listFiles(filePath);
        int size = (ftpFiles == null) ? 0 : ftpFiles.length;
        for (int i = 0; i < size; i++) {
            FTPFile ftpFile = ftpFiles[i];
            if (ftpFile.isFile()) {
                fileList.add(ftpFile.getName());
            }
        }

        return fileList;
    }

    /**
     * Sends an FTP Server site specific command
     * 
     * @param args site command arguments
     * @throws IOException on I/O errors
     */
    public void sendSiteCommand(String args) throws IOException {
        if (ftpClient.isConnected()) {
            try {
                ftpClient.sendSiteCommand(args);
            }
            catch (IOException ex) {
            }
        }
    }

    /**
     * Disconnects from the FTP server
     * 
     * @throws IOException on I/O errors
     */
    public void disconnect() throws IOException {

        if (ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
                is_connected = false;
            }
            catch (IOException ex) {
            }
        }
    }

    /**
     * Makes the full name of the file on the FTP server by joining its path and
     * the local file name.
     * 
     * @param ftpPath file path on the server
     * @param localFile local file
     * @return full name of the file on the FTP server
     */
    public String makeFTPFileName(String ftpPath, File localFile) {
        if (ftpPath == "") {
            return localFile.getName();
        }
        else {
            String path = ftpPath.trim();
            if (path.charAt(path.length() - 1) != '/') {
                path = path + "/";
            }

            return path + localFile.getName();
        }
    }

    /**
     * Test coonection to ftp server
     * 
     * @return true, if connected
     */
    public boolean isConnected() {
        return is_connected;
    }

    /**
     * Get current directory on ftp server
     * 
     * @return current directory
     */
    public String getWorkingDirectory() {
        if (!is_connected) {
            return "";
        }

        try {
            return ftpClient.printWorkingDirectory();
        }
        catch (IOException e) {
        }

        return "";
    }

    /**
     * Set working directory on ftp server
     * 
     * @param dir new working directory
     * @return true, if working directory changed
     */
    public boolean setWorkingDirectory(String dir) {
        if (!is_connected) {
            return false;
        }

        try {
            return ftpClient.changeWorkingDirectory(dir);
        }
        catch (IOException e) {
        }

        return false;
    }

    /**
     * Change working directory on ftp server to parent directory
     * 
     * @return true, if working directory changed
     */
    public boolean setParentDirectory() {
        if (!is_connected) {
            return false;
        }

        try {
            return ftpClient.changeToParentDirectory();
        }
        catch (IOException e) {
        }

        return false;
    }

    /**
     * Get parent directory name on ftp server
     * 
     * @return parent directory
     */
    public String getParentDirectory() {
        if (!is_connected) {
            return "";
        }

        String w = getWorkingDirectory();
        setParentDirectory();
        String p = getWorkingDirectory();
        setWorkingDirectory(w);

        return p;
    }

    /**
     * Get directory contents on ftp server
     * 
     * @param filePath directory
     * @return list of FTPFileInfo structures
     * @throws IOException
     */
    public List<FfpFileInfo> listFiles(String filePath) throws IOException {
        List<FfpFileInfo> fileList = new ArrayList<FfpFileInfo>();

        // Use passive mode to pass firewalls.
        ftpClient.enterLocalPassiveMode();
        FTPFile[] ftpFiles = ftpClient.listFiles(filePath);
        int size = (ftpFiles == null) ? 0 : ftpFiles.length;
        for (int i = 0; i < size; i++) {
            FTPFile ftpFile = ftpFiles[i];
            FfpFileInfo fi = new FfpFileInfo();
            fi.setName(ftpFile.getName());
            fi.setSize(ftpFile.getSize());
            fi.setTimestamp(ftpFile.getTimestamp());
            fi.setType(ftpFile.isDirectory());
            fileList.add(fi);
        }

        return fileList;
    }

    /**
     * Get file from ftp server into given output stream
     * 
     * @param ftpFileName file name on ftp server
     * @param out OutputStream
     * @throws IOException
     */
    public void getFile(String ftpFileName, OutputStream out) throws IOException {
        try {
            // Use passive mode to pass firewalls.
            ftpClient.enterLocalPassiveMode();

            // Get file info.
            FTPFile[] fileInfoArray = ftpClient.listFiles(ftpFileName);
            if (fileInfoArray == null) {
                throw new FileNotFoundException("File '" + ftpFileName
                        + "' was not found on FTP server.");
            }

            // Check file size.
            FTPFile fileInfo = fileInfoArray[0];
            long size = fileInfo.getSize();
            if (size > Integer.MAX_VALUE) {
                throw new IOException("File '" + ftpFileName + "' is too large.");
            }

            // Download file.
            if (!ftpClient.retrieveFile(ftpFileName, out)) {
                throw new IOException("Error loading file '" + ftpFileName
                        + "' from FTP server. Check FTP permissions and path.");
            }

            out.flush();

        }
        finally {
            if (out != null) {
                try {
                    out.close();
                }
                catch (IOException ex) {
                }
            }
        }
    }

    /**
     * Put file on ftp server from given input stream
     * 
     * @param ftpFileName file name on ftp server
     * @param in InputStream
     * @throws IOException
     */
    public void putFile(String ftpFileName, InputStream in) throws IOException {
        try {
            // Use passive mode to pass firewalls.
            ftpClient.enterLocalPassiveMode();

            if (!ftpClient.storeFile(ftpFileName, in)) {
                throw new IOException("Can't upload file '" + ftpFileName
                        + "' to FTP server. Check FTP permissions and path.");
            }
        }
        finally {
            try {
                in.close();
            }
            catch (IOException ex) {
            }
        }
    }

    class FfpFileInfo {

        private String name;

        private boolean type;

        private Calendar timestamp;

        private long size;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean getType() {
            return type;
        }

        public void setType(boolean type) {
            this.type = type;
        }

        public Calendar getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Calendar timestamp) {
            this.timestamp = timestamp;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }
    }
}

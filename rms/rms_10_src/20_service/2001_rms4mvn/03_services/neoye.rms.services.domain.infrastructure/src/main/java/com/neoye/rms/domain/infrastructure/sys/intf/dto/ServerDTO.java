package com.neoye.rms.domain.infrastructure.sys.intf.dto;

import com.neoye.rms.domain.infrastructure.base.BaseDTO;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;


@XStreamAlias("ServerDTO")
public class ServerDTO extends BaseDTO{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -1098597986451519471L;
	// 服务器Id
	@XStreamAsAttribute
	@XStreamAlias("serverId")
    private String serverId;
    // 服务器名称
	@XStreamAsAttribute
	@XStreamAlias("serverName")
    private String serverName;
    // 登录用户
	@XStreamAsAttribute
	@XStreamAlias("userName")
    private String userName;
    // 登录密码
	@XStreamAsAttribute
	@XStreamAlias("passwd")
    private String passwd;
    // 端口
	@XStreamAsAttribute
	@XStreamAlias("uploadPort")
    private String uploadPort;
    // IP
	@XStreamAsAttribute
	@XStreamAlias("uploadIp")
    private String uploadIp;
    // 是否加密传输
	@XStreamAsAttribute
	@XStreamAlias("isEncrypt")
    private String isEncrypt;
	
    // 是否启用
	@XStreamAsAttribute
	@XStreamAlias("inUse")
    private String inUse;

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUploadPort() {
		return uploadPort;
	}

	public void setUploadPort(String uploadPort) {
		this.uploadPort = uploadPort;
	}

	public String getUploadIp() {
		return uploadIp;
	}

	public void setUploadIp(String uploadIp) {
		this.uploadIp = uploadIp;
	}

	public String getIsEncrypt() {
		return isEncrypt;
	}

	public void setIsEncrypt(String isEncrypt) {
		this.isEncrypt = isEncrypt;
	}

	public String getInUse() {
		return inUse;
	}

	public void setInUse(String inUse) {
		this.inUse = inUse;
	}

}
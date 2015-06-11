package com.neoye.rms.domain.infrastructure.sys.entity;

import org.springframework.stereotype.Service;

@Service
public class ServerEntity {

	// 服务器Id
	private String serverId;

	// 服务器名称
	private String serverName;

	// 登录用户
	private String userName;

	// 登录密码
	private String passwd;

	// 端口
	private String uploadPort;

	// IP
	private String uploadIp;

	// 是否加密传输
	private String isEncrypt;

	// 是否启用
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
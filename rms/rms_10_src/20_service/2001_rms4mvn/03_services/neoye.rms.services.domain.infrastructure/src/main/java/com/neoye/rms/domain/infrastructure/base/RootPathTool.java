package com.neoye.rms.domain.infrastructure.base;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import rms.cmpnt.libs.commons.CodeConstant;
import rms.cmpnt.libs.commons.log.Log;


@Service
public class RootPathTool {

	private Log log = Log.getClassLogger(RootPathTool.class);

	@Autowired
	private ApplicationContext applicationContext;

	private String getRootDirWeb() {
		WebApplicationContext webApplicationContext = (WebApplicationContext) applicationContext;
		ServletContext servletContext = webApplicationContext.getServletContext();
		String rootDir = servletContext.getRealPath("/");
		log.info("web方式获取到的服务器根目录地址：" + rootDir);
		return rootDir;
	}

	private String getRootDirClassPath() {
		String webPath = RootPathTool.class.getResource("/").getPath().replaceAll("^\\/", "");
		webPath = webPath.replaceAll("[\\\\\\/]+", "/");
		webPath = webPath.replaceAll("%20", " ");
		if (webPath.matches("^[a-zA-Z]:.*?$")) {
		} else {
			webPath = "/" + webPath;
		}
		webPath += "/";
		webPath = webPath.replaceAll("[\\\\\\/]+", "/");
		int index = webPath.indexOf(CodeConstant.WEB_INF);
		if (index != -1) {
			webPath = webPath.substring(0, index);
		}
		log.info("classPath方式获取到的服务器根目录地址：" + webPath);
		return webPath;
	}

	public String getRootDir() {
		String rootPath = "";
		try {
			rootPath = getRootDirWeb();
		} catch (Exception e) {
			log.warn("web方式获取到的服务器根目录地址失败");
		}
		if ("".equals(rootPath)) {
			rootPath = getRootDirClassPath();
		}
		return rootPath;
	}

}

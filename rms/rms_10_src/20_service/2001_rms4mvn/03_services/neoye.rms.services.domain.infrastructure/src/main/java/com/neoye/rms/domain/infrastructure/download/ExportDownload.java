package com.neoye.rms.domain.infrastructure.download;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import rms.cmpnt.libs.commons.log.Log;

import com.neoye.rms.domain.infrastructure.exception.DownloaderException;

@Service("exportDownloader")
public class ExportDownload implements IDownloader {

	private Log log = Log.getClassLogger(DefaultDownloaderSrv.class);
	
	@Autowired
	private ApplicationContext context;
	
	@Override
	public FileDTO downloadFile(Map<String, String> bizAttr) throws DownloaderException {
		int type = Integer.valueOf(bizAttr.get("type"));
		IExportExcel export = null;
		String targetFileUrl = null;
		try {
			switch (type) {
				case 1: // 报表导出
				{
					export = (IExportExcel) context.getBean(Class.forName("com.neusoft.gbw.infrastructure.report.srv.impl.ReportServiceImpl"));
					targetFileUrl = export.exportExcel(bizAttr);
					break;
				}
				case 2: // 站点导出
				{
					export = (IExportExcel) context.getBean(Class.forName("com.neusoft.gbw.app.monitorMgr.srv.impl.AppMonitorServiceImpl"));
					targetFileUrl = export.exportExcel(null);
					break;
				}
				case 3: // 运行图导出
				{
					export = (IExportExcel) context.getBean(Class.forName("com.neusoft.gbw.app.radioMgr.srv.impl.AppRadioServiceImpl"));
					targetFileUrl = export.exportExcel(bizAttr);
					break;
				}
				case 4: // 收测卡片导出
				{
					export = (IExportExcel) context.getBean(Class.forName("com.neusoft.gbw.app.radioMgr.srv.impl.AppRadioServiceImpl"));
					targetFileUrl = export.exportExcel(bizAttr);
					break;
				}
				case 5: // 收测卡片失败信息导出
				{
					export = (IExportExcel) context.getBean(Class.forName("com.neusoft.gbw.app.radioMgr.srv.impl.AppRadioServiceImpl"));
					targetFileUrl = export.exportExcel(bizAttr);
					break;
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			log.error("downloadFile:" + e);
		}
		
		if (targetFileUrl != null) {
			log.info("通用下载组件需要下载的文件路径为：" + targetFileUrl);
			return new FileDTO(targetFileUrl);
		}
		
		return null;
	}
}

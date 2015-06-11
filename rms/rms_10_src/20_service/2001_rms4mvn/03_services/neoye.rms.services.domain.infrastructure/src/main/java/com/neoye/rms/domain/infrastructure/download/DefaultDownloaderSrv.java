package com.neoye.rms.domain.infrastructure.download;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rms.cmpnt.libs.commons.log.Log;

import com.neoye.rms.domain.infrastructure.base.RootPathTool;
import com.neoye.rms.domain.infrastructure.constant.InfrConstant;
import com.neoye.rms.domain.infrastructure.exception.DownloaderException;
import com.neoye.rms.domain.infrastructure.sys.intf.DicDataFacade;
import com.neoye.rms.domain.infrastructure.sys.intf.dto.SwitchDataDTO;

@Service("defaultDownloader")
public class DefaultDownloaderSrv implements IDownloader {

	@Autowired
	private DicDataFacade dicDataFacade;
	
	@Autowired
	private RootPathTool rootPathTool;

	private Log log = Log.getClassLogger(DefaultDownloaderSrv.class);

	@Override
	public FileDTO downloadFile(Map<String, String> attrs) throws DownloaderException {
		String path = getRelevantDir(attrs);
		log.info("通用下载组件需要下载的文件路径为：" + path);
		if (!checkFile(path)) {
			throw new DownloaderException("下载文件路径不存在文件：" + path);
		}
		return new FileDTO(path);
	}

	private String getRelevantDir(Map<String, String> attrs) {
		SwitchDataDTO switchDataDTO = dicDataFacade.getSwitchDataByType(assmbleSwitchData(attrs));
		switchDataDTO = dicDataFacade.getSwitchDataByType(switchDataDTO);
		return getPath(switchDataDTO.getSwitchValue());
	}

	private String getPath(String correspondingPath) {
		return rootPathTool.getRootDir() + correspondingPath;
	}

	private boolean checkFile(String path) {
		File f = new File(path);
		return f.exists();
	}

	private static SwitchDataDTO getSwitchDataVO(String switchType) {
		SwitchDataDTO switchDataDTO = new SwitchDataDTO();
		switchDataDTO.setSwitchType(switchType);
		return switchDataDTO;
	}

	private static SwitchDataDTO assmbleSwitchData(Map<String, String> attrs) {
	    String switchType = attrs.get(InfrConstant.DOWNLOAD_COMMON_KEY);
		return getSwitchDataVO(switchType);
	}
}

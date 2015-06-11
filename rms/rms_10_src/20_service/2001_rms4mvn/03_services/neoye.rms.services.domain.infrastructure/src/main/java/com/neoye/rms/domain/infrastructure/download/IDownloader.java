package com.neoye.rms.domain.infrastructure.download;

import java.util.Map;

import com.neoye.rms.domain.infrastructure.exception.DownloaderException;

public interface IDownloader {

    FileDTO downloadFile(Map<String, String> bizAttr) throws DownloaderException;

}

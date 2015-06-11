package com.neoye.rms.domain.infrastructure.exception;

import rms.cmpnt.libs.commons.exception.NMException;

public class DownloaderException extends NMException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2384081859990524066L;

	public DownloaderException() {
		super();
	}

	public DownloaderException(String msg) {
		super(msg);
	}

	public DownloaderException(Throwable cause) {
		super(cause);
	}

	public DownloaderException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

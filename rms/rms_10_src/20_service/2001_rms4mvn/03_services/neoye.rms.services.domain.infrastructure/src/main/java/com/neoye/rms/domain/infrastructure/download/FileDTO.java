package com.neoye.rms.domain.infrastructure.download;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class FileDTO {

	private String path;

	private boolean autoDel = false;

	public FileDTO() {
	}

	public FileDTO(String path) {
		this.path = path;
	}

	public FileDTO(String path, boolean autoDel) {
		this.path = path;
		this.autoDel = autoDel;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isAutoDel() {
		return autoDel;
	}

	public void setAutoDel(boolean autoDel) {
		this.autoDel = autoDel;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}

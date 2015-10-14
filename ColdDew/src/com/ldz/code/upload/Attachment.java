package com.ldz.code.upload;

import java.io.File;

/**
 * 上传附件类
 * @author LDZ   
 * @date 2015年10月14日 上午11:31:40
 */
public class Attachment {
	private String filename;
	private String contentType;
	private File uploadFile;
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public File getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	
}

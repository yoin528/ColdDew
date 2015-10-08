package com.ldz.code.upload;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 上传文件管理器
 * @author LDZ
 * @date 2013-12-16 下午09:27:27
 */
public class UploadManager {
	private static UploadManager instance = new UploadManager();
	private UploadManager() {};
	
	public static UploadManager getInstance() {
		return instance;
	}
	/**
	 * 如果是上传文件则取得上传文件包装器，如果没有则返回request
	 * @author LDZ
	 * @date 2013-12-16 下午09:39:36
	 * @param request
	 * @return
	 */
	public HttpServletRequest getRequestWrapper(HttpServletRequest request) {
		if(ServletFileUpload.isMultipartContent(request)) {
			return new MultipartRequestWrapper(request);
		}
		return request;
	}
	/**
	 * 处理己上传的文件
	 * @author LDZ
	 * @date 2013-12-16 下午09:49:06
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	public void dealUploadFile(HttpServletRequest request) {
		if(request instanceof MultipartRequestWrapper) {
			MultipartRequestWrapper uploadRequest = (MultipartRequestWrapper)request;
			Map params = uploadRequest.getParameterMap();
			Collection<Object> objs = params.values();
			for(Object obj : objs) {
				if(obj instanceof Attachment) {
					Attachment attachment = (Attachment)obj;
					attachment.getUploadFile().deleteOnExit();
				}
			}
		}
	}
}

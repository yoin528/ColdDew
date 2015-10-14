package com.ldz.code.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;

import com.ldz.code.config.SystemContext;
/**
 * HttpServletRequest包装器，包装文件上传
 * @author LDZ   
 * @date 2015年10月14日 上午11:31:49
 */
public class MultipartRequestWrapper extends HttpServletRequestWrapper {
	
	@SuppressWarnings("unchecked")
	private Map allParams;
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public MultipartRequestWrapper(HttpServletRequest request) {
		super(request);
		try {
			//是否文件上传
			allParams = new HashMap();
			ServletFileUpload upload = new ServletFileUpload();
			FileItemIterator iter = upload.getItemIterator(request);
			while(iter.hasNext()){
				FileItemStream item = iter.next();
				//得到表单域的名称
			    String name = item.getFieldName();
			    //得到表单域的值（这是一个输入流）
			    InputStream stream = item.openStream();
			    //如果是普通表单域
			    if(item.isFormField()){
			    	String value = Streams.asString(stream,SystemContext.getInstance().getConstant().getEncoding());
			    	addFieldsAndValuesToMap(name, value);
			    }else{ //如果是文件
			    	if(stream.available() != 0){//如果文件域没有选择文件，则忽略处理
				    	String filename = item.getName(); //得到上传的文件名称
				    	if(filename != null){
				    		//因为在IE下面，上传的文件还包含有此文件在客户端机器的路径
				    		//所以，要把这个路径去掉，只取文件名
				    		filename = FilenameUtils.getName(filename);
				    	}
				    	//System.out.println(name+"="+filename);
				    	//将上传文件的输入流输出到磁盘的文件上
				    	String tempFileName = System.currentTimeMillis()+"";
				    	String realPath = request.getRealPath("/") + "tmp";
				    	File file = new File(realPath);
				    	if(!file.exists()) {
				    		file.mkdirs();
				    	}
				    	
				    	Streams.copy(stream, new FileOutputStream(realPath+"/"+tempFileName+".tmp"), true);
				    	Attachment attachment = new Attachment();
				    	attachment.setUploadFile(new File(realPath+"/"+tempFileName+".tmp"));
				    	attachment.setContentType(item.getContentType());
				    	attachment.setFilename(filename);
				    	addFieldsAndValuesToMap(name, attachment);
			    	}
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将参数添加进缓存中
	 * @param name
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	private void addFieldsAndValuesToMap(String name,String value){
		String[] fieldValues = (String[])allParams.get(name);
		if(fieldValues == null) {
			allParams.put(name, new String[]{value});
		}else {
			fieldValues = Arrays.copyOf(fieldValues, fieldValues.length+1);
			fieldValues[fieldValues.length - 1] = value;
			allParams.put(name,fieldValues);
		}
	}
	/**
	 * 将附件添加进缓存中
	 * @param name
	 * @param attachment
	 */
	@SuppressWarnings("unchecked")
	private void addFieldsAndValuesToMap(String name,Attachment attachment){
		Attachment[] fieldValues = (Attachment[])allParams.get(name);
		if(fieldValues == null) {
			allParams.put(name, new Attachment[]{attachment});
		}else {
			fieldValues = Arrays.copyOf(fieldValues, fieldValues.length+1);
			fieldValues[fieldValues.length - 1] = attachment;
			allParams.put(name,fieldValues);
		}
	}
	
	@Override
	public String getParameter(String name) {
		String[] values = (String[])allParams.get(name);
		if(values != null) {
			return values[0];
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map getParameterMap() {
		return allParams;
	}
	
}

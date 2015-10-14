package com.ldz.code.config;

import java.util.HashMap;
import java.util.Map;

import com.ldz.code.render.RenderType;

/**
 * 系统常量类，用于存储系统所需值，如字符编码，默认渲染器等，可在自定义配置时存储系统所需值
 * @author LDZ   
 * @date 2015年10月14日 上午11:27:25
 */
public class SystemConstant {
	private Map<String,Object> properties = new HashMap<String,Object>();
	
	public void setEncoding(String encode) {
		properties.put("__encode", encode);
	}
	public String getEncoding() {
		return properties.get("__encode").toString();
	}
	public void setDefaultRenderType(RenderType renderType) {
		properties.put("__render", renderType);
	}
	public RenderType getDefaultRenderType() {
		return (RenderType)properties.get("__render");
	}
	public String getControllerPkg() {
		return properties.get("__pkg").toString();
	}
	public void setControllerPkg(String pkg) {
		properties.put("__pkg",pkg);
	}
	
	
	/**
	 * 取得系统配置信息
	 * @author LDZ
	 * @date 2013-12-14 下午10:32:25
	 * @param key
	 * @return
	 */
	public Object getProperty(String key) {
		return properties.get(key);
	}
	public void setProperty(String key,Object value) {
		properties.put(key, value);
	}
}

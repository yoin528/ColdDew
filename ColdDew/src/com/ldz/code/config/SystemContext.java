package com.ldz.code.config;

import javax.servlet.ServletContext;

import com.ldz.code.controller.ActionMapping;
import com.ldz.code.controller.ActionScan;
import com.ldz.code.interceptor.Interceptors;
import com.ldz.code.render.RenderFactory;

/**
 * 系统上下文，用于获取系统所需类
 * @author LDZ   
 * @date 2015年10月14日 上午11:27:32
 */
public class SystemContext {
	private static final SystemContext instance = new SystemContext();
	private SystemConstant constant = new SystemConstant();
	private Interceptors interceptors = new Interceptors();
	private ActionMapping mapping;
	private SystemContext() {}
	
	public static SystemContext getInstance() {
		return instance;
	}
	public SystemConstant getConstant() {
		return constant;
	}

	public ActionMapping getMapping() {
		return mapping;
	}

	public void init(SystemConfig config,ServletContext servletContext) {
		config.defaultConfig(constant);
		config.interceptor(interceptors);
		RenderFactory.getInstance().init(servletContext, constant);
		ActionScan scan = new ActionScan(constant.getControllerPkg());
		scan.autoScan();
		mapping = new ActionMapping(scan.getCtrs(),scan.getViewPaths(),interceptors.getGlobalInterceptor());
		mapping.build();
	}
}

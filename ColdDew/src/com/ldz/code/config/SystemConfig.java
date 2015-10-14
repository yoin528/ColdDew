package com.ldz.code.config;

import com.ldz.code.interceptor.Interceptors;
import com.ldz.code.render.RenderType;

/**
 * 系统配置
 * @author LDZ   
 * @date 2015年10月14日 上午11:26:46
 */
public abstract class SystemConfig {
	/**
	 * 系统默认配置用于系统本身调用，子类无法实现
	 * @author LDZ   
	 * @date 2015年10月14日 上午11:26:57 
	 * @param constant
	 */
	void defaultConfig(SystemConstant constant) {
		constant.setDefaultRenderType(RenderType.JSP);
		constant.setEncoding("UTF-8");
		
		constant(constant);
	}
	/**
	 * 用户自定义配置
	 * @author LDZ   
	 * @date 2015年10月14日 上午11:27:04 
	 * @param constant
	 */
	public abstract void constant(SystemConstant constant);
	/**
	 * 全局拦截器
	 * @author LDZ   
	 * @date 2015年10月14日 上午11:27:10 
	 * @param interceptors
	 */
	public abstract void interceptor(Interceptors interceptors);
}

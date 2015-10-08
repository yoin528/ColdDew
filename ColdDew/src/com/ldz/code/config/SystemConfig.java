package com.ldz.code.config;

import com.ldz.code.interceptor.Interceptors;
import com.ldz.code.render.RenderType;

/**
 * 系统配置
 * @author LDZ
 * @date 2013-12-14 下午06:00:07
 */
public abstract class SystemConfig {
	/**
	 * 系统默认配置用于系统本身调用，子类无法实现
	 * @author LDZ
	 * @date 2013-12-14 下午06:00:16
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
	 * @date 2013-12-14 下午06:00:50
	 * @param constant
	 */
	public abstract void constant(SystemConstant constant);
	/**
	 * 全局拦截器
	 * @author LDZ
	 * @date 2013-12-14 下午08:57:22
	 * @param interceptor
	 */
	public abstract void interceptor(Interceptors interceptors);
}

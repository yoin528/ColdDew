package com.ldz.code.interceptor;

import com.ldz.code.controller.ActionInvocation;

/**
 * 全局拦截器
 * @author LDZ   
 * @date 2015年10月14日 上午11:29:03
 */
public abstract class GlobalInterceptor implements Interceptor {
	protected String[] excludeMethods;
	/**
	 * 全局拦截器,过滤需要排除的方法，若无则为null
	 * @author LDZ   
	 * @date 2015年10月14日 上午11:29:10 
	 * @param excludeMethods
	 */
	public GlobalInterceptor(String excludeMethods) {
		if(excludeMethods != null && !"".equals(excludeMethods)) {
			this.excludeMethods = excludeMethods.trim().split(",");
		}
	}
	
	public abstract void doInterceptor(ActionInvocation invoke);

	public String[] getExcludeMethods() {
		return excludeMethods;
	}
}

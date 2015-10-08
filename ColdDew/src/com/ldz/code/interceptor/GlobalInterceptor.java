package com.ldz.code.interceptor;

import com.ldz.code.controller.ActionInvocation;

/**
 * 全局拦截器
 * @author LDZ
 * @date 2013-12-14 下午08:13:37
 */
public abstract class GlobalInterceptor implements Interceptor {
	protected String[] excludeMethods;
	/**
	 * 全局拦截器,过滤需要排除的方法，若无则为null
	 * @author LDZ
	 * @date 2013-12-14 下午09:09:06
	 * @param excludeMethods 方法名，不同的方法以逗号','分隔
	 * 		如：login
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

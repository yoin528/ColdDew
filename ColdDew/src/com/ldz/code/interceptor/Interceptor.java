package com.ldz.code.interceptor;

import com.ldz.code.controller.ActionInvocation;

/**
 * 拦截器
 * @author LDZ   
 * @date 2015年10月14日 上午11:29:18
 */
public interface Interceptor {
	public void doInterceptor(ActionInvocation invoke);
}

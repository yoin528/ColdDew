package com.ldz.code.interceptor;

import com.ldz.code.controller.ActionInvocation;

/**
 * 拦截器
 * @author LDZ
 * @date 2013-12-14 下午08:02:38
 */
public interface Interceptor {
	public void doInterceptor(ActionInvocation invoke);
}

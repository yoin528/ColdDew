package com.ldz.code.interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器容器
 * @author LDZ
 * @date 2013-12-14 下午08:58:05
 */
public class Interceptors {
	private List<GlobalInterceptor> interceptors = new ArrayList<GlobalInterceptor>();
	/**
	 * 添加全局拦截器
	 * @author LDZ
	 * @date 2013-12-14 下午09:20:49
	 * @param interceptor 全局拦截器
	 */
	public void addGlobalInterceptor(GlobalInterceptor interceptor) {
		interceptors.add(interceptor);
	}
	/**
	 * 取得全局拦截器
	 * @author LDZ
	 * @date 2013-12-14 下午10:42:33
	 * @return
	 */
	public List<GlobalInterceptor> getGlobalInterceptor() {
		return interceptors;
	}
}

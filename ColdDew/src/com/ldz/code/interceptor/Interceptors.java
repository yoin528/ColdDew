package com.ldz.code.interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器容器
 * @author LDZ   
 * @date 2015年10月14日 上午11:29:27
 */
public class Interceptors {
	private List<GlobalInterceptor> interceptors = new ArrayList<GlobalInterceptor>();
	/**
	 * 添加全局拦截器
	 * @author LDZ   
	 * @date 2015年10月14日 上午11:29:33 
	 * @param interceptor
	 */
	public void addGlobalInterceptor(GlobalInterceptor interceptor) {
		interceptors.add(interceptor);
	}
	/**
	 * 取得全局拦截器
	 * @author LDZ   
	 * @date 2015年10月14日 上午11:29:39 
	 * @return
	 */
	public List<GlobalInterceptor> getGlobalInterceptor() {
		return interceptors;
	}
}

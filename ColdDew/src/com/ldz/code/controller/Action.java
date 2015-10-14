package com.ldz.code.controller;

import java.lang.reflect.Method;
import java.util.List;

import com.ldz.code.interceptor.Interceptor;


/**
 * 控制器封装类
 * @author LDZ   
 * @date 2015年10月14日 上午11:27:50
 */
public class Action {
	private Class<?> controller;
	private Method method;
	private String controllerKey;
	private String viewPath;
	private List<Interceptor> interceptors;
	
	public Action(Class<?> controller,
			Method method,String viewPath, String controllerKey,List<Interceptor> interceptors) {
		this.controller = controller;
		this.method = method;
		this.controllerKey = controllerKey;
		this.interceptors = interceptors;
		this.viewPath = viewPath;
	}
//	public void addInterceptor(Interceptor interceptor) {
//		interceptors.add(interceptor);
//	}
	public Interceptor[] getInterceptors() {
		return interceptors.toArray(new Interceptor[interceptors.size()]);
	}
	public Class<?> getController() {
		return controller;
	}
	public Method getMethod() {
		return method;
	}
	public String getControllerKey() {
		return controllerKey;
	}
	public String getViewPath() {
		return viewPath;
	}
	public void setViewPath(String viewPath) {
		this.viewPath = viewPath;
	}
	
}

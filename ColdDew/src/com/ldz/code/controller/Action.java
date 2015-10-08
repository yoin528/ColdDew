package com.ldz.code.controller;

import java.lang.reflect.Method;
import java.util.List;

import com.ldz.code.interceptor.Interceptor;


/**
 * 控制器封装类
 * @author LDZ
 * @date 2013-12-14 下午06:03:20
 */
public class Action {
	private Class<?> controller;
	private Method method;
	private String controllerKey;
	private String view;
	private List<Interceptor> interceptors;
	
	public Action(Class<?> controller,
			Method method, String controllerKey,List<Interceptor> interceptors) {
		this.controller = controller;
		this.method = method;
		this.controllerKey = controllerKey;
		this.interceptors = interceptors;
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
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
}

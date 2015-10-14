package com.ldz.code.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.ldz.code.annotation.Before;
import com.ldz.code.interceptor.GlobalInterceptor;
import com.ldz.code.interceptor.Interceptor;

/**
 * Action映射类
 * @author LDZ   
 * @date 2015年10月14日 上午11:28:19
 */
public class ActionMapping {
	final Map<String,Action> mapping = new HashMap<String,Action>();
	private Map<String,Class<?>> controllers;
	private Map<String, String> viewPaths;
	private List<GlobalInterceptor> globalInterceptors;
	
	public ActionMapping(Map<String, Class<?>> controllers,Map<String, String> viewPaths,List<GlobalInterceptor> globalInterceptors) {
		this.controllers = controllers;
		this.globalInterceptors = globalInterceptors;
		this.viewPaths = viewPaths;
	}
	
	public Action getAction(String url) {
		return mapping.get(url);
	}
	
	public void build() {
		mapping.clear();
		Set<Entry<String, Class<?>>> entrySet = controllers.entrySet();
		for (Entry<String, Class<?>> entry : entrySet) {
			Class<?> controller = entry.getValue();
			String controllerKey = entry.getKey();
			Method[] methods = controller.getDeclaredMethods();
			for (Method method : methods) {
				if(method.getParameterTypes().length==0) {
					List<Interceptor> interceptors = new ArrayList<Interceptor>();
					String methodName = method.getName();
					String actionKey = controllerKey;
					if(!methodName.equals("execute")) {
						if(controllerKey.lastIndexOf(methodName) == -1) {
							actionKey = controllerKey + "/" + methodName;
						}
					}else {
						actionKey = controllerKey + "/execute";
					}
					if(mapping.containsKey(actionKey)) {
						throw new RuntimeException("控制器：[" + controller.getName() + "]与[" + mapping.get(actionKey).getController().getName()+"]存在路径重复，请检查："+actionKey);
					}
					buildInterceptors(controller, method,interceptors);
					String viewPath = viewPaths.get(controllerKey);
					if(viewPath!=null && !"".equals(viewPath)) {
						if(!viewPath.endsWith("/")) {
							viewPath += "/";
						}
					}
					mapping.put(actionKey, new Action(controller, method,viewPath,controllerKey,interceptors));
				}
			}
		}
	}
	
	private void buildInterceptors(Class<?> controller,Method method,List<Interceptor> interceptors) {
		if(globalInterceptors != null) {
			for(GlobalInterceptor global : globalInterceptors) {
				boolean isExclude = false;
				String[] methods = global.getExcludeMethods();
				if(methods != null) {
					for(String met : methods) {
						if(method.getName().equals(met)) {
							isExclude = true;
							break;
						}
					}
				}
				if(!isExclude) {
					interceptors.add(global);
				}
//				for(int i=0;i<methods.length;i++) {
//					if(method.getName().equals(methods[i])) {
//						isExclude = true;
//						break;
//					}
//				}
				
			}
		}
		Before before = controller.getAnnotation(Before.class);
		if(before != null) {
			addInterceptors(interceptors, before);
		}
		
		before = method.getAnnotation(Before.class);
		if(before != null) {
			addInterceptors(interceptors, before);
		}
	}

	private void addInterceptors(List<Interceptor> interceptors, Before before) {
		Class<? extends Interceptor>[] classInterceptors = before.value();
		
		for(Class<? extends Interceptor> clsInterceptor : classInterceptors) {
			try {
				Interceptor interceptor = clsInterceptor.newInstance();
				if(!interceptors.contains(interceptor)) {
					interceptors.add(interceptor);
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
//		Class<?> controller = XMLAction.class;
//		Before before = controller.getAnnotation(Before.class);
//		Class<? extends Interceptor>[] ints = before.value();
//		for(Class<?> it : ints) {
//			System.out.println(it.getClass().getName());
//		}
	}
}

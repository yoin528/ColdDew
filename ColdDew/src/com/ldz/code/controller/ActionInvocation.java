package com.ldz.code.controller;

import java.lang.reflect.InvocationTargetException;

import com.ldz.code.interceptor.Interceptor;


/**
 * Action调度器
 * @author LDZ   
 * @date 2015年10月14日 上午11:28:08
 */
public class ActionInvocation {
	private Action action;
	private AbstractController controller;
	private Interceptor[] interceptors;
	private int num = 0;
	
	public ActionInvocation(Action action, AbstractController controller) {
		this.action = action;
		this.controller = controller;
		this.interceptors = action.getInterceptors();
	}

	public void invoke() {
//		this.controller.before();
//		if(controller.getRender() == null) {
			try {
				if(num < interceptors.length) {
					interceptors[num++].doInterceptor(this);
				}else if(num == interceptors.length) {
					controller.before();
					this.action.getMethod().invoke(controller, new Object[0]);
					controller.after();
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
//		}
	}

	public Action getAction() {
		return action;
	}

	public AbstractController getController() {
		return controller;
	}

	public Interceptor[] getInterceptors() {
		return interceptors;
	}
	
	
}

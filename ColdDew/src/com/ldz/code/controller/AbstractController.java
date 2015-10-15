package com.ldz.code.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ldz.code.render.Render;
import com.ldz.code.render.RenderFactory;
import com.ldz.code.render.RenderType;
/**
 * 控制器，所有Action父类
 * @author LDZ   
 * @date 2015年10月14日 上午11:27:42
 */
public abstract class AbstractController {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	private String method;
	private Render render;
	public void before(){}
	public void after(){}
	public void execute(){}
	public void init(HttpServletRequest request,HttpServletResponse response,String method) {
		this.request = request;
		this.response = response;
		this.method = method;
	}
	/**
	 * 默认跳转器
	 * @author LDZ
	 * @date 2013-12-14 下午06:17:29
	 * @param view
	 */
	public void render(String view) {
		render = RenderFactory.getInstance().getDefaultRender(view);
	}
	/**
	 * JSP跳转
	 * @author LDZ
	 * @date 2013-12-14 下午06:17:18
	 * @param view
	 */
	public void renderJsp(String view) {
		render = RenderFactory.getInstance().getRender(view,RenderType.JSP);
	}
	/**
	 * Freemarker跳转
	 * @author LDZ
	 * @date 2013-12-14 下午06:17:38
	 * @param view
	 */
	public void renderFrk(String view) {
		render = RenderFactory.getInstance().getRender(view,RenderType.FREEMARKER);
	}
	/**
	 * Velocity跳转
	 * @author LDZ
	 * @date 2013-12-14 下午06:17:48
	 * @param view
	 */
	public void renderVelocity(String view) {
		render = RenderFactory.getInstance().getRender(view,RenderType.VELOCITY);
	}
	public void redirect(String view) {
		render = RenderFactory.getInstance().getRedirect(view);
	}
	
	public String getParameter(String name) {
		return request.getParameter(name);
	}
	public void putData(String name,Object value) {
		request.setAttribute(name, value);
	}
	public Object getSession(String name) {
		return request.getSession().getAttribute(name);
	}
	public void setSession(String key,Object obj) {
		request.getSession().setAttribute(key, obj);
	}
	public void removeSession(String key) {
		request.getSession().removeAttribute(key);
	}
	Render getRender() {
		return render;
	}

	void setRequest(HttpServletRequest request){
		this.request = request;
	}
	
	public HttpServletRequest getRequest() {
	    return request;
	}
	
	void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
	
	String getMethod() {
		return method;
	}

	void setMethod(String method) {
		this.method = method;
	}
}

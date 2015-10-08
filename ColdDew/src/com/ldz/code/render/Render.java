package com.ldz.code.render;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 渲染器接口
 * @author LDZ
 * @date 2013-12-14 下午06:06:58
 */
public abstract class Render {
	protected transient HttpServletRequest request;
	protected transient HttpServletResponse response;
	
	public Render setContext(HttpServletRequest request,HttpServletResponse response) {
		this.request = request;
		this.response = response;
		return this;
	}
	
	public abstract void rander();
}

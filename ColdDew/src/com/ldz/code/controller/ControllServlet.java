package com.ldz.code.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ldz.code.config.SystemConfig;
import com.ldz.code.config.SystemContext;

public class ControllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SystemConfig systemConfig;
	private SystemContext context = SystemContext.getInstance();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Handler handler = new ActionHandler(context.getMapping());
			handler.handler(request, response);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	public void init() throws ServletException {
		String baseConfig = getInitParameter("baseConfig");
		try {
			try {
				Object obj = Class.forName(baseConfig).newInstance();
				if(obj instanceof SystemConfig) {
					systemConfig = (SystemConfig)obj;
				}
				context.init(systemConfig, getServletContext());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}

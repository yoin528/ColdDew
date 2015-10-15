package com.ldz.code.render;

import java.io.IOException;

import com.ldz.code.config.SystemContext;

/**
 * 重定向渲染器
 * @author LDZ   
 * @date 2015年10月14日 上午11:30:15
 */
public class RedirectRender extends Render {
	private String view;
	private static final String contextPath = getContxtPath();
	
	static String getContxtPath() {
		String contextPath = SystemContext.getInstance().getServletContext().getContextPath();
		return ("".equals(contextPath) || "/".equals(contextPath)) ? null : contextPath;
	}
	public RedirectRender(String view) {
		this.view = view;
	}

	@Override
	public void rander(String viewPath) {
		if (contextPath != null && view.indexOf("://") == -1) {
			view = contextPath + view;
		}
		
		try {
			response.sendRedirect(view);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

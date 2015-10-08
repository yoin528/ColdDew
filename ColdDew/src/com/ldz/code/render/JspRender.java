package com.ldz.code.render;

import java.io.IOException;

import javax.servlet.ServletException;

/**
 * JSP渲染器
 * @author LDZ
 * @date 2013-12-14 下午06:06:36
 */
public class JspRender extends Render {
	private String view;
	
	public JspRender(String view) {
		this.view = view;
	}

	@Override
	public void rander() {
		try {
			request.getRequestDispatcher(view).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

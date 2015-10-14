package com.ldz.code.render;

import java.io.IOException;

import javax.servlet.ServletException;

/**
 * JSP渲染器
 * @author LDZ   
 * @date 2015年10月14日 上午11:30:08
 */
public class JspRender extends Render {
	private String view;
	
	public JspRender(String view) {
		this.view = view;
	}

	@Override
	public void rander(String viewPath) {
		try {
			request.getRequestDispatcher(viewPath + view).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

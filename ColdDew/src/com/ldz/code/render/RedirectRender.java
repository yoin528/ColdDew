package com.ldz.code.render;

import java.io.IOException;

/**
 * 重定向渲染器
 * @author LDZ   
 * @date 2015年10月14日 上午11:30:15
 */
public class RedirectRender extends Render {
	private String view;
	
	public RedirectRender(String view) {
		this.view = view;
	}

	@Override
	public void rander(String viewPath) {
		try {
			response.sendRedirect(view);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

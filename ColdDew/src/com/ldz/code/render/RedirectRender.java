package com.ldz.code.render;

import java.io.IOException;

/**
 * 重定向渲染器
 * @author LDZ
 * @date 2013-12-14 下午06:06:47
 */
public class RedirectRender extends Render {
	private String view;
	
	public RedirectRender(String view) {
		this.view = view;
	}

	@Override
	public void rander() {
		try {
			response.sendRedirect(view);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

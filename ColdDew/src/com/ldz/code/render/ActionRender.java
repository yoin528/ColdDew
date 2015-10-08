package com.ldz.code.render;
/**
 * Action渲染器，用于服务器内部跳转
 * @author LDZ
 * @date 2013-12-14 下午06:06:01
 */
public class ActionRender extends Render {
	private String actionUrl;
	
	public ActionRender(String actionUrl) {
		this.actionUrl = actionUrl;
	}
	public String getActionUrl() {
		return actionUrl;
	}
	@Override
	public void rander() {
	}

}

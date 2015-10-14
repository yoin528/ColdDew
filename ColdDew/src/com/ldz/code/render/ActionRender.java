package com.ldz.code.render;
/**
 * Action渲染器，用于服务器内部跳转
 * @author LDZ   
 * @date 2015年10月14日 上午11:29:53
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
	public void rander(String viewPath) {
	}

}

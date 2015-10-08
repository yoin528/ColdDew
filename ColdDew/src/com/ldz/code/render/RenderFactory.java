package com.ldz.code.render;

import javax.servlet.ServletContext;

import com.ldz.code.config.SystemConstant;

/**
 * 渲染器接口
 * @author LDZ
 * @date 2013-12-14 下午06:07:09
 */
public class RenderFactory {
	private static final RenderFactory instance = new RenderFactory();
	private SystemConstant constant;
	private RenderFactory() {}
	public static RenderFactory getInstance() {
		return instance;
	}
	
	public void init(ServletContext context,SystemConstant constant) {
		this.constant = constant;
		FreemarkerRender.initFrk(context);
	}
	
	public Render getDefaultRender(String view) {
		Render render;
		if(constant.getDefaultRenderType() == RenderType.JSP) {
			render = new JspRender(view);
		}else if(constant.getDefaultRenderType() == RenderType.FREEMARKER) {
			render = new FreemarkerRender(view);
		}else if(constant.getDefaultRenderType() == RenderType.VELOCITY) {
			throw new RuntimeException("未实现VELOCITY!");
		}else {
			throw new RuntimeException("找不到其它渲染器!");
		}
		return render;
	}
	
	public Render getRender(String view,RenderType type) {
		Render render;
		if(type == RenderType.JSP) {
			render = new JspRender(view);
		}else if(type == RenderType.FREEMARKER) {
			render = new FreemarkerRender(view);
		}else if(type == RenderType.VELOCITY) {
			throw new RuntimeException("未实现VELOCITY!");
		}else {
			throw new RuntimeException("找不到其它渲染器!");
		}
		return render;
	}
	public Render getRedirect(String view) {
		return new RedirectRender(view);
	}
}


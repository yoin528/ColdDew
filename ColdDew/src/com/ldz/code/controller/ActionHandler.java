package com.ldz.code.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ldz.code.config.SystemContext;
import com.ldz.code.render.ActionRender;
import com.ldz.code.render.Render;
import com.ldz.code.upload.UploadManager;

/**
 * Action处理类
 * @author LDZ   
 * @date 2015年10月14日 上午11:27:59
 */
public class ActionHandler implements Handler {

	final ActionMapping mapping;
	String target = null;
	public ActionHandler(ActionMapping mapping) {
		this.mapping = mapping;
	}

	public void handler(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(target == null) {
			target = request.getServletPath();
		}
		request.setCharacterEncoding(SystemContext.getInstance().getConstant().getEncoding());
		response.setCharacterEncoding(SystemContext.getInstance().getConstant().getEncoding());
		//包装上传
		request = UploadManager.getInstance().getRequestWrapper(request);
		String path = target;
		int index = path.indexOf(".");
		if(index == -1)  throw new RuntimeException("请求路径不存在,请检查你所请求的路径.");
		path = path.substring(0, index);
		String event = target.substring(target.lastIndexOf("/") + 1);
		event = event.substring(0, event.indexOf("."));
		Action action = mapping.getAction(path);
		if(action == null) {
			throw new RuntimeException("找不到请求相应的控制器，请检查请求路径：["+target+"]");
		}
		
		AbstractController controller = (AbstractController)action.getController().newInstance();
		controller.init(request, response, event);
		
		new ActionInvocation(action, controller).invoke();
		UploadManager.getInstance().dealUploadFile(request);
		Render render = controller.getRender();
		if(render == null) return;
		render.setContext(request, response);
		if(render instanceof ActionRender) {
			target = ((ActionRender)render).getActionUrl();
			handler(request, response);
			return;
		}
		
		render.rander(action.getViewPath());
	}
}

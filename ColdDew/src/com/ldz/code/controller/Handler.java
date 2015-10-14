package com.ldz.code.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理器接口
 * @author LDZ   
 * @date 2015年10月14日 上午11:28:51
 */
public interface Handler {
	public void handler(HttpServletRequest request,HttpServletResponse response)throws Exception;
}

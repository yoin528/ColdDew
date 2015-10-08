package com.ldz.code.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理器接口
 * @author LDZ
 * @date 2013-12-14 下午06:05:47
 */
public interface Handler {
	public void handler(HttpServletRequest request,HttpServletResponse response)throws Exception;
}

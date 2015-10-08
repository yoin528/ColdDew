package com.ldz.code.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 控制器注解类
 * @author LDZ
 * @date 2013-12-14 下午05:59:52
 */
@Target(ElementType.TYPE)   
@Retention(RetentionPolicy.RUNTIME)   
@Documented  
public @interface Controller {
	public String path();
}

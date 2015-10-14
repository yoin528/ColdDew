package com.ldz.code.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 控制器注解类
 * @author LDZ   
 * @date 2015年10月14日 上午11:26:28
 */
@Target(ElementType.TYPE)   
@Retention(RetentionPolicy.RUNTIME)   
@Documented  
public @interface Controller {
	/**
	 * 请求路径前缀
	 * @author LDZ   
	 * @date 2015年10月14日 上午11:18:29 
	 * @return
	 */
	public String path();
	/**
	 * 输入模板目录
	 * @author LDZ   
	 * @date 2015年10月14日 上午11:18:58 
	 * @return
	 */
	public String viewPath() default "";
}

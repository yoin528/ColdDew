package com.ldz.code.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ldz.code.interceptor.Interceptor;

/**
 * 方法前注解
 * @author LDZ   
 * @date 2015年10月14日 上午11:26:10
 */
@Target({ElementType.TYPE,ElementType.METHOD})   
@Retention(RetentionPolicy.RUNTIME)   
@Documented  
public @interface Before {
	public Class<? extends Interceptor>[] value();
}

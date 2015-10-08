package com.ldz.code.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ldz.code.interceptor.Interceptor;

/**
 * @author LDZ
 * @date 2013-12-14 下午08:54:27
 */
@Target({ElementType.TYPE,ElementType.METHOD})   
@Retention(RetentionPolicy.RUNTIME)   
@Documented  
public @interface Before {
	public Class<? extends Interceptor>[] value();
}

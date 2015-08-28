package com.lemon.modules.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**@projectName DBModules
 * @PackageName com.module.orm.util.db.annotation
 * @Title Column
 * @Description  数据字段注解
 * @Author xflu
 * @Date 2014-3-24
 * @Version V1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface Column
{
	/**
	 * 设置字段名
	 * 
	 * @return
	 */
	String name() default "";

	/**
	 * 字段默认值
	 * 
	 * @return
	 */
	public String defaultValue() default "";
}

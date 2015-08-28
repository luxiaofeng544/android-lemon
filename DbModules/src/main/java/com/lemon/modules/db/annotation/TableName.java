package com.lemon.modules.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**@projectName DBModules
 * @PackageName com.module.orm.util.db.annotation
 * @Title TableName 设置表名
 * @Description  设置表名字
 * @Author xflu
 * @Date 2014-3-24
 * @Version V1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TableName
{
	/**
	 * 设置表名字
	 * 
	 * @return
	 */
	String name() default "";
}
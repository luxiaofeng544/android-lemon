package com.lemon.modules.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**@projectName DBModules
 * @PackageName com.module.orm.util.db.annotation
 * @Title CompareAnnotation
 * @Description  对比注释
 * @Author xflu
 * @Date 2014-3-24
 * @Version V1.0
 */
@Retention(RUNTIME)
@Target(
{ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
public @interface CompareAnnotation
{
	/**
	 * 选择进行排序属性标识
	 * 
	 * @return 返回标识码
	 */
	int sortFlag() default 0;
}

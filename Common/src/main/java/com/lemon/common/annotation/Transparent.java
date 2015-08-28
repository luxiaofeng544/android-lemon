package com.lemon.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @projectName BaseModules
 * @PackageName com.xinxin.android.annotation
 * @Title Transparent
 * @Description  设置实体属性不被识别
 * @Author Luxiaofeng
 * @Date 2014-5-5
 * @Version V1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Transparent
{

}

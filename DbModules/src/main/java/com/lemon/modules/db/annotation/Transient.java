package com.lemon.modules.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**@projectName DBModules
 * @PackageName com.module.orm.util.db.annotation
 * @Title Transient
 * @Description  设置实体属性不被识别为表字段
 * @Author xflu
 * @Date 2014-3-24
 * @Version V1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Transient
{

}

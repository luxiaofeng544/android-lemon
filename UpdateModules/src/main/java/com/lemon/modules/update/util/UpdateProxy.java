/**
 * 
 */
package com.lemon.modules.update.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @projectName UpdateModules
 * @PackageName com.xinxin.android
 * @Title UpdateProxy
 * @Description	更新模块,使用的代理(配置文件)
 * @Author Luxiaofeng
 * @Date 2014-4-13
 * @Version V1.0
 */
public class UpdateProxy implements InvocationHandler {

	private Object target;
	
	public Object getProxy(Object target){
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
	}
	
	/**
	 * 使用代理,目的是为了<p>统一处理调用方法前和调用方法后的事物</p>
	 * 
	 * @see InvocationHandler#invoke(Object,
	 * Method, Object[])
	 */
	@Override
	public Object invoke(Object arg0, Method method, Object[] args)
			throws Throwable {
		//调用方法前,to log
		Object result = method.invoke(target, args);
		//调用方法后,to log
		return result;
	}

}

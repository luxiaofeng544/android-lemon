/**
 * 
 */
package com.lemon.modules.update;

/**
 * @projectName UpdateModules
 * @PackageName com.xinxin.android.update
 * @Title IUpdate
 * @Description  
 * @Author Luxiaofeng
 * @Date 2014-4-13
 * @Version V1.0
 */
public interface IUpdate {
	/**
	 * 检测更新版本信息
	 * @param query
	 * @param handler
	 * @param interfaceBean
	 * @return
	 */
	public IUpdateBean checkVersion(IUpdateQuery query, IUpdateHandler handler, IUpdateInterfaceBean interfaceBean);
}

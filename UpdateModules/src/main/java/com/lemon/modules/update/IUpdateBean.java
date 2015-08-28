/**
 * 
 */
package com.lemon.modules.update;

/**
 * @projectName UpdateModules
 * @PackageName com.xinxin.android.update
 * @Title IUpdateBean
 * @Description	更新信息对象接口
 * @Author Luxiaofeng
 * @Date 2014-4-13
 * @Version V1.0
 */
public interface IUpdateBean {
	/**
	 * 获取版本号
	 * @return
	 */
	public String getVersionCode();

	/**
	 * 获取版本描述
	 * @return
	 */
	public String getVersionDescription();

	/**
	 * 获取下载地址
	 * @return
	 */
	public String getVersionDownUrl();

	/**
	 * 获取版本信息
	 * @return
	 */
	public String getVersionName();

	/**
	 * 该版本是否需要强制安装
	 * @return
	 */
	public boolean isForce();
}

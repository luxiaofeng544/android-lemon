/**
 * 
 */
package com.lemon.modules.update.impl;

import com.lemon.modules.update.IUpdateBean;

/**
 * @projectName UpdateModules
 * @PackageName com.lemon.modules.update.impl
 * @Title UpdateBean
 * @Description	更新信息对象
 * @Author Luxiaofeng
 * @Date 2014-4-13
 * @Version V1.0
 */
public class UpdateBean implements IUpdateBean {
	/**
	 * 版本名
	 */
	private String versionName;
	/**
	 * 版本号
	 */
	private String versionCode;
	/**
	 * 版本描述
	 */
	private String versionDescription;
	/**
	 * 版本下载地址
	 */
	private String versionDownUrl;
	/**
	 * 是否强制安装
	 */
	private boolean isForce;

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionDescription() {
		return versionDescription;
	}

	public void setVersionDescription(String versionDescription) {
		this.versionDescription = versionDescription;
	}

	public String getVersionDownUrl() {
		return versionDownUrl;
	}

	public void setVersionDownUrl(String versionDownUrl) {
		this.versionDownUrl = versionDownUrl;
	}

	public boolean isForce() {
		return isForce;
	}

	public void setForce(boolean isForce) {
		this.isForce = isForce;
	}

}

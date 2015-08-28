/**
 * 
 */
package com.lemon.modules.update.impl;

import java.util.HashMap;
import java.util.Map;

import com.lemon.modules.update.IUpdateInterfaceBean;

/**
 * @projectName UpdateModules
 * @PackageName com.lemon.modules.update.impl
 * @Title UpdateInterfaceBean
 * @Description
 * @Author Luxiaofeng
 * @Date 2014-4-13
 * @Version V1.0
 */
public class UpdateInterfaceBean implements IUpdateInterfaceBean {

	private String interfaceUrl;
	private Map<String, String> params;

	public UpdateInterfaceBean() {
		params = new HashMap<String, String>();
	}

	public void addParam(String key,String value){
		params.put(key, value);
	}
	
	public String getInterfaceUrl() {
		return interfaceUrl;
	}

	public void setInterfaceUrl(String interfaceUrl) {
		this.interfaceUrl = interfaceUrl;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

}

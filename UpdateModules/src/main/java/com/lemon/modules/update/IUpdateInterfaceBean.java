package com.lemon.modules.update;

import java.util.Map;

/**
 * @projectName UpdateModules
 * @PackageName com.xinxin.android.update
 * @Title IUpdateInterfaceBean
 * @Description 更新接口对象信息接口
 * @Author Luxiaofeng
 * @Date 2014-4-13
 * @Version V1.0
 */
public interface IUpdateInterfaceBean {
	public String getInterfaceUrl();

	public Map<String, String> getParams();
}

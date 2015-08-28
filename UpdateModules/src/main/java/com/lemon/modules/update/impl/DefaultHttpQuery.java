/**
 * 
 */
package com.lemon.modules.update.impl;

import java.io.IOException;
import java.io.InputStream;


import com.lemon.modules.update.IUpdateInterfaceBean;
import com.lemon.modules.update.IUpdateQuery;
import com.lemon.modules.update.util.HttpUtil;

/**@projectName UpdateModules
 * @PackageName com.lemon.modules.update.impl
 * @Title DefaultHttpQuery
 * @Description  
 * @Author Luxiaofeng
 * @Date 2014-4-13
 * @Version V1.0
 */
public class DefaultHttpQuery implements IUpdateQuery {

	@Override
	public InputStream query(IUpdateInterfaceBean interfaceBean) {
		String url = interfaceBean.getInterfaceUrl();
		try {
			if(HttpUtil.getInstance().excuteHttp(url, null) == SUCCESS){
				return HttpUtil.getInstance().getIsResult();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}

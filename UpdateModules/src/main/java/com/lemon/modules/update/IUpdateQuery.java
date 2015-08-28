/**
 * 
 */
package com.lemon.modules.update;

import java.io.InputStream;

import com.lemon.modules.update.constant.UpdateConstants;

/**@projectName UpdateModules
 * @PackageName com.xinxin.android.update
 * @Title IUpdateQuery
 * @Description  
 * @Author Luxiaofeng
 * @Date 2014-4-13
 * @Version V1.0
 */
public interface IUpdateQuery extends UpdateConstants{
	public InputStream query(IUpdateInterfaceBean interfaceBean);
}

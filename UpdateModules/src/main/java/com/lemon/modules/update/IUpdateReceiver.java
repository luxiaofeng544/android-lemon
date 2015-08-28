/**
 * 
 */
package com.lemon.modules.update;

import android.content.Context;

/**@projectName UpdateModules
 * @PackageName com.xinxin.android.update
 * @Title IUpdateReceiver
 * @Description  接收更新广播
 * @Author Luxiaofeng
 * @Date 2014-4-14
 * @Version V1.0
 */
public interface IUpdateReceiver {
	/**
	 * 检测更新
	 * @param mContext
	 */
	public void checkAppUpdate(Context mContext);
}

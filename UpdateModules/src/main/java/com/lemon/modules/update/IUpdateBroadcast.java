/**
 * 
 */
package com.lemon.modules.update;

import android.content.Context;

/**@projectName UpdateModules
 * @PackageName com.xinxin.android.update
 * @Title IUpdateBroadcast
 * @Description  发送更新的广播
 * @Author Luxiaofeng
 * @Date 2014-4-14
 * @Version V1.0
 */
public interface IUpdateBroadcast {
	/**
	 * 发送更新消息
	 * @param mContext
	 */
	public void sendUpdateBroadcast(Context mContext);
}

/**
 * 
 */
package com.lemon.modules.update.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lemon.modules.update.IUpdateReceiver;
import com.lemon.modules.update.util.UpdateEngine;

/**@projectName UpdateModules
 * @PackageName com.xinxin.android
 * @Title UpdateReceiver
 * @Description  
 * @Author Luxiaofeng
 * @Date 2014-4-13
 * @Version V1.0
 */
public class UpdateReceiver extends BroadcastReceiver implements IUpdateReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		//接收到广播后调用检测的方法
		checkAppUpdate(context);
	}
	
	@Override
	public void checkAppUpdate(Context mContext){
		UpdateEngine.getInstance(mContext).checkAppUpdate(mContext,true);
	}

}

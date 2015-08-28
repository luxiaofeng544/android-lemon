package com.lemon.modules.update.impl;

import android.content.Context;
import android.content.Intent;

import com.lemon.modules.update.IUpdateBroadcast;
import com.lemon.modules.update.constant.UpdateConstants;

/**
 * @projectName UpdateModules
 * @PackageName com.xinxin.android
 * @Title UpdateBroadcast
 * @Description 更新广播发送者
 * @Author Luxiaofeng
 * @Date 2014-4-13
 * @Version V1.0
 */
public class UpdateBroadcast implements IUpdateBroadcast,UpdateConstants{

	@Override
	public void sendUpdateBroadcast(Context mContext) {
		//发送更新的广播
		Intent intent = new Intent();
		intent.setAction(UPDATE_BROADCAST_ACTION);
		mContext.sendBroadcast(intent);
	}

}

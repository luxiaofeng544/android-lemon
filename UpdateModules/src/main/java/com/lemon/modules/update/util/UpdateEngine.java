package com.lemon.modules.update.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lemon.modules.update.IUpdate;
import com.lemon.modules.update.IUpdateBean;
import com.lemon.modules.update.IUpdateHandler;
import com.lemon.modules.update.IUpdateInterfaceBean;
import com.lemon.modules.update.IUpdateQuery;
import com.lemon.modules.update.config.UpdateConfig;
import com.lemon.modules.update.constant.UpdateConstants;
import com.lemon.modules.update.impl.DefaultUpdateClient;

/**
 * @projectName UpdateModules
 * @PackageName com.xinxin.android
 * @Title UpdateEngine
 * @Description 应用程序更新处理引擎
 * @Author Luxiaofeng
 * @Date 2014-4-13
 * @Version V1.0
 */
public class UpdateEngine implements UpdateConstants{
	private volatile static UpdateEngine updateEngine;

	private static final int DOWN_NOSDCARD = 0;
    private static final int DOWN_UPDATE = 1;
    private static final int DOWN_OVER = 2;
	
    private static final int DIALOG_TYPE_LATEST = 0;
    private static final int DIALOG_TYPE_FAIL   = 1;
    
	private Context mContext;
	//应用英文名
	private String appName;
	// 查询者
	private IUpdate updateClient = null;
	// 查询者
	private IUpdateQuery updateQuery = null;
	// 解析者
	private IUpdateHandler updateHandler = null;
	// 接口信息
	private IUpdateInterfaceBean interfaceBean = null;
	//通知对话框
	private Dialog noticeDialog;
	//下载对话框
	private Dialog downloadDialog;
	// 进度条
	private ProgressDialog mProgressDialog ;
	//'已经是最新' 或者 '无法获取最新版本' 的对话框
	private Dialog latestOrFailDialog;
    //进度条
    private ProgressBar mProgress;
    //显示下载数值
    private TextView mProgressText;
    //查询动画
    private ProgressDialog mProDialog;
    //进度值
    private int progress = 0;
    //下载线程
    private Thread downLoadThread;
    //终止标记
    private boolean interceptFlag;
	//提示语
	private String updateMsg = "";
	//返回的安装包url
	private String apkUrl = "";
	//下载包保存路径
    private String savePath = "";
	//apk保存完整路径
	private String apkFilePath = "";
	//临时下载文件路径
	private String tmpFilePath = "";
	//下载文件大小
	private String apkFileSize;
	//已下载文件大小
	private String tmpFileSize;
	
	private String curVersionName = "";
	private int curVersionCode;
	private IUpdateBean mUpdate;
    
	private UpdateEngine(Context mContext) {
		appName = UpdateConfig.getInstance(mContext).getAppName();
		updateClient = (IUpdate)UpdateConfig.getInstance(mContext).find(CLIENT);
		updateQuery = (IUpdateQuery)UpdateConfig.getInstance(mContext).find(QUERY);
		updateHandler = (IUpdateHandler)UpdateConfig.getInstance(mContext).find(HANDLER);
		interfaceBean = UpdateConfig.getInstance(mContext).getUpdateInterfaceBean();
	}

	public static UpdateEngine getInstance(Context mContext) {
		if (updateEngine == null) {
			synchronized (UpdateEngine.class) {
				if (updateEngine == null) {
					updateEngine = new UpdateEngine(mContext);
				}
			}
		}
		return updateEngine;
	}
	
    private Handler mHandler = new Handler(){
    	public void handleMessage(Message msg) {
    		switch (msg.what) {
			case DOWN_UPDATE:
				mProgressDialog.setProgress(msg.arg1);
//				mProgressText.setText(tmpFileSize + "/" + apkFileSize);
				break;
			case DOWN_OVER:
				mProgressDialog.dismiss();
//				downloadDialog.dismiss();
				installApk();
				break;
			case DOWN_NOSDCARD:
				mProgressDialog.dismiss();
				Toast.makeText(mContext, "无法下载安装文件，请检查SD卡是否挂载", Toast.LENGTH_LONG).show();
				break;
			}
    	};
    };
	
	/**
	 * 检查App更新
	 * @param context
	 * @param isShowMsg 是否显示提示消息
	 */
	public void checkAppUpdate(Context context, final boolean isShowMsg){
		this.mContext = context;
		getCurrentVersion();
		if(isShowMsg){
			if(mProDialog == null)
				mProDialog = ProgressDialog.show(mContext, null, "正在检测，请稍后...", true, true);
			else if(mProDialog.isShowing() || (latestOrFailDialog!=null && latestOrFailDialog.isShowing()))
				return;
		}
		final Handler handler = new Handler(){
			public void handleMessage(Message msg) {
				//进度条对话框不显示 - 检测结果也不显示
				if(mProDialog != null && !mProDialog.isShowing()){
					return;
				}
				//关闭并释放释放进度条对话框
				if(isShowMsg && mProDialog != null){
					mProDialog.dismiss();
					mProDialog = null;
				}
				//显示检测结果
				if(msg.what == 1){
					mUpdate = (IUpdateBean)msg.obj;
					if(mUpdate != null){
						if(curVersionCode < Integer.parseInt(mUpdate.getVersionCode())){
							apkUrl = mUpdate.getVersionDownUrl();
							updateMsg = mUpdate.getVersionDescription();
							showNoticeDialog();
						}else if(isShowMsg){
							showLatestOrFailDialog(DIALOG_TYPE_LATEST);
						}
					}
				}else if(isShowMsg){
					showLatestOrFailDialog(DIALOG_TYPE_FAIL);
				}
			}
		};
		new Thread(){
			public void run() {
				Message msg = new Message();
				try {					
					IUpdateBean update = updateClient.checkVersion(updateQuery, updateHandler,interfaceBean);
					msg.what = 1;
					msg.obj = update;
				} catch (Exception e) {
					e.printStackTrace();
				}
				handler.sendMessage(msg);
			}			
		}.start();		
	}	
	
	/**
	 * 显示'已经是最新'或者'无法获取版本信息'对话框
	 */
	private void showLatestOrFailDialog(int dialogType) {
		if (latestOrFailDialog != null) {
			//关闭并释放之前的对话框
			latestOrFailDialog.dismiss();
			latestOrFailDialog = null;
		}
		Builder builder = new Builder(mContext);
		builder.setTitle("系统提示");
		if (dialogType == DIALOG_TYPE_LATEST) {
			builder.setMessage("您当前已经是最新版本");
		} else if (dialogType == DIALOG_TYPE_FAIL) {
			builder.setMessage("无法获取版本更新信息");
		}
		builder.setPositiveButton("确定", null);
		latestOrFailDialog = builder.create();
		latestOrFailDialog.show();
	}

	/**
	 * 获取当前客户端版本信息
	 */
	private void getCurrentVersion(){
        try { 
        	PackageInfo info = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
        	curVersionName = info.versionName;
        	curVersionCode = info.versionCode;
        } catch (NameNotFoundException e) {    
			e.printStackTrace(System.err);
		} 
	}
	
	/**
	 * 显示版本更新通知对话框
	 */
	private void showNoticeDialog(){
		Builder builder = new Builder(mContext);
		builder.setTitle("软件版本更新");
		builder.setMessage(updateMsg);
		builder.setPositiveButton("立即更新", new OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				showDownloadDialog();			
			}
		});
		builder.setNegativeButton("以后再说", new OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();				
			}
		});
		noticeDialog = builder.create();
		noticeDialog.show();
	}
	
	/**
	 * 显示下载对话框
	 */
	@SuppressWarnings("deprecation")
	private void showDownloadDialog(){
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
		mProgressDialog = new ProgressDialog(mContext);
		mProgressDialog.setMessage("正在下载中,请稍等...");
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgressDialog.setCancelable(false);
		mProgressDialog.setButton("取消", new OnClickListener() {
            public void onClick(DialogInterface dialog, int i)
            {
                //点击“确定按钮”取消对话框
            	mProgressDialog.cancel();
				interceptFlag = true;
            }
        });
		mProgressDialog.show();
		downloadApk();
	}
	
	private Runnable mdownApkRunnable = new Runnable() {	
		@Override
		public void run() {
			try {
				String apkName = mUpdate.getVersionName()+".apk";
				String tmpApk = mUpdate.getVersionName()+".tmp";
				//判断是否挂载了SD卡
				String storageState = Environment.getExternalStorageState();		
				if(storageState.equals(Environment.MEDIA_MOUNTED)){
					savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+appName+"/Update/";
					File file = new File(savePath);
					if(!file.exists()){
						file.mkdirs();
					}
					apkFilePath = savePath + apkName;
					tmpFilePath = savePath + tmpApk;
				}
				
				//没有挂载SD卡，无法下载文件
				if(apkFilePath == null || apkFilePath == ""){
					mHandler.sendEmptyMessage(DOWN_NOSDCARD);
					return;
				}
				
				File ApkFile = new File(apkFilePath);
				
				//是否已下载更新文件
				if(ApkFile.exists()){
					/*downloadDialog.dismiss();
					installApk();
					return;*/
					ApkFile.delete();
				}
				
				//输出临时下载文件
				File tmpFile = new File(tmpFilePath);
				FileOutputStream fos = new FileOutputStream(tmpFile);
				
				URL url = new URL(apkUrl);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.connect();
				int length = conn.getContentLength();
				InputStream is = conn.getInputStream();
				
				//显示文件大小格式：2个小数点显示
		    	DecimalFormat df = new DecimalFormat("0.00");
		    	//进度条下面显示的总文件大小
		    	apkFileSize = df.format((float) length / 1024 / 1024) + "MB";
				
				int count = 0;
				byte buf[] = new byte[1024];
				
				
				do{   		   		
		    		int numread = is.read(buf);
		    		count += numread;
		    		//进度条下面显示的当前下载文件大小
		    		tmpFileSize = df.format((float) count / 1024 / 1024) + "MB";
		    		//当前进度值
		    	    progress =(int)(((float)count / length) * 100);
		    	    //更新进度
		    	    Message msg = mHandler.obtainMessage();
					msg.what = DOWN_UPDATE;
					msg.arg1 = progress;
					mHandler.sendMessage(msg);
//		    	    mHandler.sendEmptyMessage(DOWN_UPDATE);
		    		if(numread <= 0){	
		    			//下载完成 - 将临时下载文件转成APK文件
						if(tmpFile.renameTo(ApkFile)){
							//通知安装
							
							mHandler.sendEmptyMessage(DOWN_OVER);
						}
		    			break;
		    		}
		    		fos.write(buf,0,numread);
		    	}while(!interceptFlag);//点击取消就停止下载
				
				fos.close();
				is.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch(IOException e){
				e.printStackTrace();
			}
			
		}
	};
	
	/**
	* 下载apk
	*/
	private void downloadApk(){
		downLoadThread = new Thread(mdownApkRunnable);
		downLoadThread.start();
	}
	
	/**
    * 安装apk
    */
	private void installApk(){
		File apkfile = new File(apkFilePath);
        if (!apkfile.exists()) {
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive"); 
        mContext.startActivity(i);
	}

	
}

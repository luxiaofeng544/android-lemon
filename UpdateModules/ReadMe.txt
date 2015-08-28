程序更新的模块

两部分：
 (1) app更新： 1.1 自动更新，1.2 强制更新 1.3检测更新 1.4统一更新的接口约定与使用方式
 (2) 模块更新   2.1什么情况下适合使用模块更新 2.2 如何更新部分功能模块~
 
 使用方式：
 1、在app中添加相应的权限(网络和sdcard)
 
 2、在app的assets文件夹下添加update-config.xml文件,详细配置参考demo
	 <?xml version="1.0" encoding="UTF-8"?>
	<update isAutoCheck="true" appName="新新应用">
	    <!-- api接口定义 -->
	    <interface url="http://files.cnblogs.com/luxiaofeng54/UpdateResult.xml">
	    </interface>
	    <!-- 基本处理对象配置 -->
	    <items>
		    <!-- 网络更新块 -->
		    <item id="client" name="DefaultUpdateClient" classz="com.xinxin.android.update.impl.DefaultUpdateClient"/>
		    <!-- 网络更新信息查询者 -->
		    <item id="query" name="DefaultHttpQuery" classz="com.xinxin.android.update.impl.DefaultHttpQuery"/>
		    <!-- 网络更新信息查询者 -->
		    <item id="handler" name="DefaultXmlHandler" classz="com.xinxin.android.update.impl.DefaultXmlHandler"/>
	    </items>
	</update>
	
 3、在app中如何使用
 	UpdateFacade updateFacade = UpdateFacade.getInstance(this); 
 	updateFacade.sendUpdateBroadcast();
 	
 4、自定义更新模块功能
 	IUpdate、IUpdateQuery、IUpdateHandler
	实现全部以上接口,或者只实现其中的一部分。通过配置文件的方式将此组合成一套
/**
 * 
 */
package com.lemon.modules.update.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.content.Context;


import com.lemon.modules.update.IUpdateInterfaceBean;
import com.lemon.modules.update.constant.UpdateConstants;
import com.lemon.modules.update.impl.UpdateInterfaceBean;
import com.lemon.modules.update.util.LogUtils;

/**
 * @projectName UpdateModules
 * @PackageName com.lemon.modules.update.config
 * @Title UpdateConfigFacotry
 * @Description
 * @Author Luxiaofeng
 * @Date 2014-4-13
 * @Version V1.0
 */
public class UpdateConfig implements UpdateConstants{

	private volatile static UpdateConfig updateConfig;
	private Context mContext = null;
	
	private UpdateConfig(Context mContext) {
		this.mContext = mContext;
		this.initCache();
	}

	public static UpdateConfig getInstance(Context mContext) {
		if (updateConfig == null) {
			synchronized (UpdateConfig.class) {
				if (updateConfig == null) {
					updateConfig = new UpdateConfig(mContext);
				}
			}
		}
		return updateConfig;
	}
	
	/**
	 * app的名称,用于保存apk时使用
	 */
	private String appName;
	
	/**
	 * 是否开启自动检测更新
	 */
	private boolean isAutoCheck;
	
	/**
	 * 配置的接口信息对象
	 */
	private UpdateInterfaceBean interfaceBean;
	
	/**
	 * 配置好的信息对象
	 */
	private Map<String, Object> cache = new HashMap<String, Object>();

	public String getAppName() {
		return appName;
	}

	public boolean isAutoCheck() {
		return isAutoCheck;
	}

	/**
	 * 获取基本的处理者
	 * @param type @see {@link UpdateConstants#BROADCAST}
	 * <p>{@link UpdateConstants#HANDLER}</p>
	 * <p>{@link UpdateConstants#QUERY}</p>
	 * <p>{@link UpdateConstants#RECEIVER}</p>
	 * @return
	 * @throws Exception
	 */
	public Object find(String type) {
		return cache.get(type);
	}
	
	/**
	 * 获取接口信息对象
	 * @return
	 */
	public IUpdateInterfaceBean getUpdateInterfaceBean(){
		return interfaceBean;
	}

	private void initCache() {
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // 取得DocumentBuilderFactory实例
			DocumentBuilder builder = factory.newDocumentBuilder(); // 从factory获取DocumentBuilder实例
			String strConfig = getFromAssets(UPDATE_CONFIG_FILE);
			LogUtils.printWithLogCat(UpdateConfig.class.getName(), "strConfig\n"+strConfig);
			Document doc = builder.parse(mContext .getResources().getAssets().open(UPDATE_CONFIG_FILE)); // 解析输入流 得到Document实例
			Element rootElement = doc.getDocumentElement();
			
			//配置项基本属性获取设置
			appName = rootElement.getAttribute("appName");
			isAutoCheck = Boolean.parseBoolean(rootElement.getAttribute("isAutoCheck"));
			
			//配置项(接口详细)
			NodeList interfaces = rootElement.getElementsByTagName("interface");
			for (int i = 0; i < interfaces.getLength(); i++) {
				Element item = (Element)interfaces.item(i);
				interfaceBean = new UpdateInterfaceBean();
				String interfaceUrl = item.getAttribute("url");
				interfaceBean.setInterfaceUrl(interfaceUrl );
				//参数获取
				NodeList properties = item.getChildNodes();
				for (int j = 0; j < properties.getLength(); j++) {  
					String key = properties.item(j).getNodeName();
					String value = "";
					interfaceBean.addParam(key, value);
				}
			}
			
			//配置想(基本处理者)
			NodeList items = rootElement.getElementsByTagName("item");
			for (int i = 0; i < items.getLength(); i++) {
				Element item = (Element)items.item(i);
				String id = item.getAttribute("id");
				String name = item.getAttribute("name");
				String classz = item.getAttribute("classz");
				Class c = Class.forName(classz);
				Constructor ctor = c.getDeclaredConstructor();
				cache.put(id, ctor.newInstance());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public String getFromAssets(String fileName) {
		try {
			InputStreamReader inputReader = new InputStreamReader(mContext
					.getResources().getAssets().open(fileName));
			BufferedReader bufReader = new BufferedReader(inputReader);
			String line = "";
			String Result = "";
			while ((line = bufReader.readLine()) != null)
				Result += line;
			return Result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}

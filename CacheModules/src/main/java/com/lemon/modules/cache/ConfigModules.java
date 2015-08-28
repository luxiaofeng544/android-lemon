/**
 * 
 */
package com.lemon.modules.cache;

import android.content.Context;

import com.lemon.common.BaseModules;
import com.lemon.common.util.AssertUtil;
import com.lemon.modules.cache.config.IConfig;
import com.lemon.modules.cache.config.PreferenceConfig;
import com.lemon.modules.cache.config.PropertiesConfig;


/**@projectName ConfigModules
 * @PackageName com.xinxin.android
 * @Title ConfigModulesFacade
 * @Description  配置文件模块对外提供的门面
 * @Author Luxiaofeng
 * @Date 2014-5-1
 * @Version V1.0
 */
public class ConfigModules extends BaseModules implements IConfig {

	private static ConfigModules modules;
	private IConfig mCurrentConfig;

	/**
	 * init Modules , create instance of this Modules
	 * @param mContext
	 */
	public static void initModules(Context mContext) {
		AssertUtil.notNull(mContext);
		modules = new ConfigModules(mContext);
	}

	private ConfigModules(Context mContext){
		//可调整使用配置文件的方式为properties文件
		this.mContext = mContext;
		mCurrentConfig = PreferenceConfig.getPreferenceConfig(mContext);
		mCurrentConfig.loadConfig();
	}

	public static ConfigModules getInstance(){
		AssertUtil.notNull(modules);
		return modules;
	}

	public void setPreferenceConfig()
	{
		mCurrentConfig = PreferenceConfig.getPreferenceConfig(mContext);
		if (!mCurrentConfig.isLoadConfig())
		{
			mCurrentConfig.loadConfig();
		}
	}
	
	public void setPropertiesConfig()
	{
		mCurrentConfig = PropertiesConfig.getPropertiesConfig(mContext);
		if (!mCurrentConfig.isLoadConfig())
		{
			mCurrentConfig.loadConfig();
		}
	}
	
	@Override
	public void loadConfig() {
		mCurrentConfig.loadConfig();
	}

	@Override
	public Boolean isLoadConfig() {
		return mCurrentConfig.isLoadConfig();
	}

	@Override
	public void open() {
		mCurrentConfig.open() ;
	}

	@Override
	public void close() {
		mCurrentConfig.close();
	}

	@Override
	public boolean isClosed() {
		return mCurrentConfig.isClosed();
	}

	@Override
	public void setString(String key, String value) {
		mCurrentConfig.setString(key, value);
	}

	@Override
	public void setInt(String key, int value) {
		mCurrentConfig.setInt(key, value);
	}

	@Override
	public void setBoolean(String key, Boolean value) {
		mCurrentConfig.setBoolean(key, value);		
	}

	@Override
	public void setByte(String key, byte[] value) {
		mCurrentConfig.setByte(key, value);		
	}

	@Override
	public void setShort(String key, short value) {
		mCurrentConfig.setShort(key, value);		
	}

	@Override
	public void setLong(String key, long value) {
		mCurrentConfig.setLong(key, value);		
	}

	@Override
	public void setFloat(String key, float value) {
		mCurrentConfig.setFloat(key, value);		
	}

	@Override
	public void setDouble(String key, double value) {
		mCurrentConfig.setDouble(key, value);		
	}

	@Override
	public void setString(int resID, String value) {
		mCurrentConfig.setString(resID, value);
	}

	@Override
	public void setInt(int resID, int value) {
		mCurrentConfig.setInt(resID, value);
	}

	@Override
	public void setBoolean(int resID, Boolean value) {
		mCurrentConfig.setBoolean(resID, value);
	}

	@Override
	public void setByte(int resID, byte[] value) {
		mCurrentConfig.setByte(resID, value);
	}

	@Override
	public void setShort(int resID, short value) {
		mCurrentConfig.setShort(resID, value);
	}

	@Override
	public void setLong(int resID, long value) {
		mCurrentConfig.setLong(resID, value);
	}

	@Override
	public void setFloat(int resID, float value) {
		mCurrentConfig.setFloat(resID, value);
	}

	@Override
	public void setDouble(int resID, double value) {
		mCurrentConfig.setDouble(resID, value);
	}

	@Override
	public void setConfig(Object entity) {
		mCurrentConfig.setConfig(entity);
	}

	@Override
	public String getString(String key, String defaultValue) {
		return mCurrentConfig.getString(key, defaultValue);
	}

	@Override
	public int getInt(String key, int defaultValue) {
		return mCurrentConfig.getInt(key, defaultValue);
	}

	@Override
	public boolean getBoolean(String key, Boolean defaultValue) {
		return mCurrentConfig.getBoolean(key, defaultValue);
	}

	@Override
	public byte[] getByte(String key, byte[] defaultValue) {
		return mCurrentConfig.getByte(key, defaultValue);
	}

	@Override
	public short getShort(String key, Short defaultValue) {
		return mCurrentConfig.getShort(key, defaultValue);
	}

	@Override
	public long getLong(String key, Long defaultValue) {
		return mCurrentConfig.getLong(key, defaultValue);
	}

	@Override
	public float getFloat(String key, Float defaultValue) {
		return mCurrentConfig.getFloat(key, defaultValue);
	}

	@Override
	public double getDouble(String key, Double defaultValue) {
		return mCurrentConfig.getDouble(key, defaultValue);
	}

	@Override
	public String getString(int resID, String defaultValue) {
		return mCurrentConfig.getString(resID, defaultValue);
	}

	@Override
	public int getInt(int resID, int defaultValue) {
		return mCurrentConfig.getInt(resID, defaultValue);
	}

	@Override
	public boolean getBoolean(int resID, Boolean defaultValue) {
		return mCurrentConfig.getBoolean(resID, defaultValue);
	}

	@Override
	public byte[] getByte(int resID, byte[] defaultValue) {
		return mCurrentConfig.getByte(resID, defaultValue);
	}

	@Override
	public short getShort(int resID, Short defaultValue) {
		return mCurrentConfig.getShort(resID, defaultValue);
	}

	@Override
	public long getLong(int resID, Long defaultValue) {
		return mCurrentConfig.getLong(resID, defaultValue);
	}

	@Override
	public float getFloat(int resID, Float defaultValue) {
		return mCurrentConfig.getFloat(resID, defaultValue);
	}

	@Override
	public double getDouble(int resID, Double defaultValue) {
		return mCurrentConfig.getDouble(resID, defaultValue);
	}

	@Override
	public <T> T getConfig(Class<T> clazz) {
		return mCurrentConfig.getConfig(clazz);
	}

	@Override
	public void remove(String key) {
		mCurrentConfig.remove(key);
	}

	@Override
	public void remove(String... key) {
		mCurrentConfig.remove(key);
	}

	@Override
	public void clear() {
		mCurrentConfig.clear();
	}
	
}

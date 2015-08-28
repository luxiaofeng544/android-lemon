package com.lemon.modules.db.entity;


import com.lemon.common.util.StringUtils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

/**@projectName DBModules
 * @PackageName com.module.orm.util.db.entity
 * @Title ABArrayList
 * @Description  NameValuePair数组
 * @Author xflu
 * @Date 2014-3-24
 * @Version V1.0
 */
public class ABArrayList extends ArrayList<NameValuePair>
{

	private static final long serialVersionUID = 1L;

	@Override
	public boolean add(NameValuePair nameValuePair)
	{
		if (!StringUtils.isEmpty(nameValuePair.getValue()))
		{
			return super.add(nameValuePair);
		} else
		{
			return false;
		}
	}

	/**
	 * 添加数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add(String key, String value)
	{
		return add(new BasicNameValuePair(key, value));
	}

}

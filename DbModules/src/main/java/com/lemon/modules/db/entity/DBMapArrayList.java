package com.lemon.modules.db.entity;

import java.util.ArrayList;

/**@projectName DBModules
 * @PackageName com.module.orm.util.db.entity
 * @Title DBMapArrayList
 * @Description  MapList
 * @Author xflu
 * @Date 2014-3-24
 * @Version V1.0
 */
public class DBMapArrayList<T extends Object> extends ArrayList<DBHashMap<T>>
{
	private static final long serialVersionUID = 1L;

	@Override
	public boolean add(DBHashMap<T> taHashMap)
	{
		if (taHashMap != null)
		{
			return super.add(taHashMap);
		} else
		{
			return false;
		}
	}
}

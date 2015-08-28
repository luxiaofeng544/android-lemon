package com.lemon.modules.db.util.sql;

import com.lemon.common.exception.DBException;
import com.lemon.modules.db.entity.ABArrayList;
import com.lemon.modules.db.util.DBUtils;

import java.lang.reflect.Field;

/**@projectName DBModules
 * @PackageName com.module.orm.util.db.util.sql
 * @Title DeleteSqlBuilder
 * @Description   删除sql语句构建器类
 * @Author xflu
 * @Date 2014-3-24
 * @Version V1.0
 */
public class DeleteSqlBuilder extends SqlBuilder
{
	@Override
	public String buildSql() throws DBException, IllegalArgumentException,
			IllegalAccessException
	{
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder(256);
		stringBuilder.append("DELETE FROM ");
		stringBuilder.append(tableName);
		if (entity == null)
		{
			stringBuilder.append(buildConditionString());
		} else
		{
			stringBuilder.append(buildWhere(buildWhere(this.entity)));
		}

		return stringBuilder.toString();
	}

	/**
	 * 创建Where语句
	 * 
	 * @param entity
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws DBException
	 */
	public ABArrayList buildWhere(Object entity)
			throws IllegalArgumentException, IllegalAccessException,
			DBException
	{
		Class<?> clazz = entity.getClass();
		ABArrayList whereArrayList = new ABArrayList();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields)
		{
			field.setAccessible(true);
			if (!DBUtils.isTransient(field))
			{
				if (DBUtils.isBaseDateType(field))
				{
					// 如果ID不是自动增加的
					if (!DBUtils.isAutoIncrement(field))
					{
						String columnName = DBUtils.getColumnByField(field);
						if (null != field.get(entity)
								&& field.get(entity).toString().length() > 0)
						{
							whereArrayList.add(
									(columnName != null && !columnName
											.equals("")) ? columnName : field
											.getName(), field.get(entity)
											.toString());
						}
					}
				}
			}
		}
		if (whereArrayList.isEmpty())
		{
			throw new DBException("不能创建Where条件，语句");
		}
		return whereArrayList;
	}
}

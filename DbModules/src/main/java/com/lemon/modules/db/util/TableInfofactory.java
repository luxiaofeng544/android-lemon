package com.lemon.modules.db.util;

import com.lemon.common.exception.DBException;
import com.lemon.modules.db.entity.PKProperyEntity;
import com.lemon.modules.db.entity.PropertyEntity;
import com.lemon.modules.db.entity.TableInfoEntity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

/**@projectName DBModules
 * @PackageName com.module.orm.util.db.util
 * @Title TableInfofactory
 * @Description   数据库表工厂类
 * @Author xflu
 * @Date 2014-3-24
 * @Version V1.0
 */
public class TableInfofactory
{
	/**
	 * 表名为键，表信息为值的HashMap
	 */
	private static final HashMap<String, TableInfoEntity> tableInfoEntityMap = new HashMap<String, TableInfoEntity>();

	private TableInfofactory()
	{

	}

	private static TableInfofactory instance;

	/**
	 * 获得数据库表工厂
	 * 
	 * @return 数据库表工厂
	 */
	public static TableInfofactory getInstance()
	{
		if (instance == null)
		{
			instance = new TableInfofactory();
		}
		return instance;
	}

	/**
	 * 获得表信息
	 * 
	 * @param clazz
	 *            实体类型
	 * @return 表信息
	 * @throws DBException
	 */
	public TableInfoEntity getTableInfoEntity(Class<?> clazz)
			throws DBException
	{
		if (clazz == null)
			throw new DBException("表信息获取失败，应为class为null");
		TableInfoEntity tableInfoEntity = tableInfoEntityMap.get(clazz
				.getName());
		if (tableInfoEntity == null)
		{
			tableInfoEntity = new TableInfoEntity();
			tableInfoEntity.setTableName(DBUtils.getTableName(clazz));
			tableInfoEntity.setClassName(clazz.getName());
			Field idField = DBUtils.getPrimaryKeyField(clazz);
			if (idField != null)
			{
				PKProperyEntity pkProperyEntity = new PKProperyEntity();
				pkProperyEntity.setColumnName(DBUtils
						.getColumnByField(idField));
				pkProperyEntity.setName(idField.getName());
				pkProperyEntity.setType(idField.getType());
				pkProperyEntity.setAutoIncrement(DBUtils
						.isAutoIncrement(idField));
				tableInfoEntity.setPkProperyEntity(pkProperyEntity);
			} else
			{
				tableInfoEntity.setPkProperyEntity(null);
			}
			List<PropertyEntity> propertyList = DBUtils
					.getPropertyList(clazz);
			if (propertyList != null)
			{
				tableInfoEntity.setPropertieArrayList(propertyList);
			}

			tableInfoEntityMap.put(clazz.getName(), tableInfoEntity);
		}
		if (tableInfoEntity == null
				|| tableInfoEntity.getPropertieArrayList() == null
				|| tableInfoEntity.getPropertieArrayList().size() == 0)
		{
			throw new DBException("不能创建+" + clazz + "的表信息");
		}
		return tableInfoEntity;
	}
}

package com.lemon.modules.db.entity;

/**@projectName DBModules
 * @PackageName com.module.orm.util.db.entity
 * @Title PKProperyEntity
 * @Description  主键信息
 * @Author xflu
 * @Date 2014-3-24
 * @Version V1.0
 */
public class PKProperyEntity extends PropertyEntity
{

	public PKProperyEntity()
	{

	}

	public PKProperyEntity(String name, Class<?> type, Object defaultValue,
			boolean primaryKey, boolean isAllowNull, boolean autoIncrement,
			String columnName)
	{
		super(name, type, defaultValue, primaryKey, isAllowNull, autoIncrement,
				columnName);
		// TODO Auto-generated constructor stub
	}

}

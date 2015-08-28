package com.lemon.modules.db.entity;

import java.util.ArrayList;
import java.util.List;

/**@projectName DBModules
 * @PackageName com.module.orm.util.db.entity
 * @Title TableInfoEntity
 * @Description  表所有信息，表名，主键，字段等
 * @Author xflu
 * @Date 2014-3-24
 * @Version V1.0
 */
public class TableInfoEntity extends BaseEntity
{
	private static final long serialVersionUID = 488168612576359150L;
	private String tableName = "";
	private String className = "";
	private PKProperyEntity pkProperyEntity = null;

	ArrayList<PropertyEntity> propertieArrayList = new ArrayList<PropertyEntity>();

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public ArrayList<PropertyEntity> getPropertieArrayList()
	{
		return propertieArrayList;
	}

	public void setPropertieArrayList(List<PropertyEntity> propertyList)
	{
		this.propertieArrayList = (ArrayList<PropertyEntity>) propertyList;
	}

	public PKProperyEntity getPkProperyEntity()
	{
		return pkProperyEntity;
	}

	public void setPkProperyEntity(PKProperyEntity pkProperyEntity)
	{
		this.pkProperyEntity = pkProperyEntity;
	}

}

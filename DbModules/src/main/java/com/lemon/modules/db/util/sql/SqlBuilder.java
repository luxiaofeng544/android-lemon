package com.lemon.modules.db.util.sql;

import android.text.TextUtils;

import com.lemon.common.exception.DBException;
import com.lemon.modules.db.entity.ABArrayList;
import com.lemon.modules.db.util.DBUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;


/**@projectName DBModules
 * @PackageName com.module.orm.util.db.util.sql
 * @Title SqlBuilder
 * @Description   sql语句构建器基类
 * @Author xflu
 * @Date 2014-3-24
 * @Version V1.0
 */
public abstract class SqlBuilder
{
	protected Boolean distinct;
	protected String where;
	protected String groupBy;
	protected String having;
	protected String orderBy;
	protected String limit;
	protected Class<?> clazz = null;
	protected String tableName = null;
	protected Object entity;
	protected ABArrayList updateFields;

	public SqlBuilder(Object entity)
	{
		this.entity = entity;
		setClazz(entity.getClass());
	}

	public Object getEntity()
	{
		return entity;
	}

	public void setEntity(Object entity)
	{
		this.entity = entity;
		setClazz(entity.getClass());
	}

	public void setCondition(boolean distinct, String where, String groupBy,
			String having, String orderBy, String limit)
	{
		this.distinct = distinct;
		this.where = where;
		this.groupBy = groupBy;
		this.having = having;
		this.orderBy = orderBy;
		this.limit = limit;
	}

	public ABArrayList getUpdateFields()
	{
		return updateFields;
	}

	public void setUpdateFields(ABArrayList updateFields)
	{
		this.updateFields = updateFields;
	}

	public SqlBuilder()
	{
	}

	public SqlBuilder(Class<?> clazz)
	{
		setTableName(clazz);
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public void setTableName(Class<?> clazz)
	{
		this.tableName = DBUtils.getTableName(clazz);
	}

	public String getTableName()
	{
		return tableName;
	}

	public Class<?> getClazz()
	{
		return clazz;
	}

	public void setClazz(Class<?> clazz)
	{
		setTableName(clazz);
		this.clazz = clazz;
	}

	/**
	 * 获取sql语句
	 * 
	 * @return
	 * @throws DBException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public String getSqlStatement() throws DBException,
			IllegalArgumentException, IllegalAccessException
	{
		onPreGetStatement();
		return buildSql();
	}

	/**
	 * 构建sql语句前执行方法
	 * 
	 * @return
	 * @throws DBException
	 */
	public void onPreGetStatement() throws DBException,
			IllegalArgumentException, IllegalAccessException
	{

	}

	/**
	 * 构建sql语句
	 * 
	 * @return
	 * @throws DBException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public abstract String buildSql() throws DBException,
			IllegalArgumentException, IllegalAccessException;

	/**
	 * 创建条件字句
	 * 
	 * @return 返回条件Sql
	 */
	protected String buildConditionString()
	{
		StringBuilder query = new StringBuilder(120);
		appendClause(query, " WHERE ", where);
		appendClause(query, " GROUP BY ", groupBy);
		appendClause(query, " HAVING ", having);
		appendClause(query, " ORDER BY ", orderBy);
		appendClause(query, " LIMIT ", limit);
		return query.toString();
	}

	protected void appendClause(StringBuilder s, String name, String clause)
	{
		if (!TextUtils.isEmpty(clause))
		{
			s.append(name);
			s.append(clause);
		}
	}

	/**
	 * 构建where子句
	 * 
	 * @param conditions
	 *            XxinArrayList类型的where数据
	 * @return 返回where子句
	 */
	public String buildWhere(ABArrayList conditions)
	{
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder(256);
		if (conditions != null)
		{
			stringBuilder.append(" WHERE ");
			for (int i = 0; i < conditions.size(); i++)
			{
				NameValuePair nameValuePair = conditions.get(i);
				stringBuilder
						.append(nameValuePair.getName())
						.append(" = ")
						.append(StringUtils.isNumeric(nameValuePair
								.getValue().toString()) ? nameValuePair
								.getValue() : "'" + nameValuePair.getValue()
								+ "'");
				if (i + 1 < conditions.size())
				{
					stringBuilder.append(" AND ");
				}
			}
		}
		return stringBuilder.toString();
	}
}

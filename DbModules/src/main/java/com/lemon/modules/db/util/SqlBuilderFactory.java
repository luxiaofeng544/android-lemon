package com.lemon.modules.db.util;

import com.lemon.modules.db.util.sql.DeleteSqlBuilder;
import com.lemon.modules.db.util.sql.InsertSqlBuilder;
import com.lemon.modules.db.util.sql.QuerySqlBuilder;
import com.lemon.modules.db.util.sql.SqlBuilder;
import com.lemon.modules.db.util.sql.UpdateSqlBuilder;

/**@projectName DBModules
 * @PackageName com.module.orm.util.db.util
 * @Title SqlBuilderFactory
 * @Description   Sql构建器工厂,生成sql语句构建器
 * @Author xflu
 * @Date 2014-3-24
 * @Version V1.0
 */
public class SqlBuilderFactory
{
	private static SqlBuilderFactory instance;
	/**
	 * 调用getSqlBuilder(int operate)返回插入sql语句构建器传入的参数
	 */
	public static final int INSERT = 0;
	/**
	 * 调用getSqlBuilder(int operate)返回查询sql语句构建器传入的参数
	 */
	public static final int SELECT = 1;
	/**
	 * 调用getSqlBuilder(int operate)返回删除sql语句构建器传入的参数
	 */
	public static final int DELETE = 2;
	/**
	 * 调用getSqlBuilder(int operate)返回更新sql语句构建器传入的参数
	 */
	public static final int UPDATE = 3;

	/**
	 * 单例模式获得Sql构建器工厂
	 * 
	 * @return sql构建器
	 */
	public static SqlBuilderFactory getInstance()
	{
		if (instance == null)
		{
			instance = new SqlBuilderFactory();
		}
		return instance;
	}

	/**
	 * 获得sql构建器
	 * 
	 * @param operate
	 * @return 构建器
	 */
	public synchronized SqlBuilder getSqlBuilder(int operate)
	{
		SqlBuilder sqlBuilder = null;
		switch (operate)
		{
		case INSERT:
			sqlBuilder = new InsertSqlBuilder();
			break;
		case SELECT:
			sqlBuilder = new QuerySqlBuilder();
			break;
		case DELETE:
			sqlBuilder = new DeleteSqlBuilder();
			break;
		case UPDATE:
			sqlBuilder = new UpdateSqlBuilder();
			break;
		default:
			break;
		}
		return sqlBuilder;
	}
}

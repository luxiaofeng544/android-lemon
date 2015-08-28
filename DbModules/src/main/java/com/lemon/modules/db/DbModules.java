package com.lemon.modules.db;

import android.content.ContentValues;
import android.content.Context;

import com.lemon.common.BaseModules;
import com.lemon.common.util.AssertUtil;
import com.lemon.modules.db.entity.ABArrayList;
import com.lemon.modules.db.entity.DBHashMap;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称:  [lemon]
 * 包:        [com.lemon.modules.db]
 * 类描述:    [简要描述]
 * 创建人:    [xflu]
 * 创建时间:  [2015/8/27 15:42]
 * 修改人:    [xflu]
 * 修改时间:  [2015/8/27 15:42]
 * 修改备注:  [说明本次修改内容]
 * 版本:      [v1.0]
 */
public class DbModules extends BaseModules {

    protected static Context mContext;

    /**
     * init Modules , create instance of this Modules
     * @param mContext
     */
    public static void initModules(Context mContext) {
        AssertUtil.notNull(mContext);
        modules = new DbModules(mContext);
    }

    private  volatile static DbModules modules;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteDatabasePool mSQLiteDatabasePool;

    private DbModules(Context context){
        mContext = context;
        mSQLiteDatabasePool = SQLiteDatabasePool.getInstance(context);
        mSQLiteDatabasePool.createPool();
        sqLiteDatabase = mSQLiteDatabasePool.getSQLiteDatabase();
    }

    public static DbModules getInstance(){
        AssertUtil.notNull(modules);
        return modules;
    }

    /**
     * 获取数据库的操作接口对象
     * @return
     */
    public SQLiteDatabase getSQLiteDatabase(){
        return sqLiteDatabase;
    }

    /**
     * 判断是否存在某个表,为true则存在，否则不存在
     *
     * @param clazz
     * @return true则存在，否则不存在
     */
    public boolean hasTable(Class<?> clazz)
    {
        return sqLiteDatabase.hasTable(clazz);
    }

    /**
     * 判断是否存在某个表,为true则存在，否则不存在
     *
     * @param tableName
     *            需要判断的表名
     * @return true则存在，否则不存在
     */
    public boolean hasTable(String tableName)
    {
        return sqLiteDatabase.hasTable(tableName);
    }

    /**
     * 创建表
     *
     * @param clazz
     * @return 为true创建成功，为false创建失败
     */
    public Boolean creatTable(Class<?> clazz)
    {
        return sqLiteDatabase.creatTable(clazz);
    }

    /**
     * 删除表
     * @param clazz
     * @return
     */
    public Boolean dropTable(Class<?> clazz)
    {
        return sqLiteDatabase.dropTable(clazz);
    }

    /**
     * 删除表
     *
     * @param tableName
     * @return 为true创建成功，为false创建失败
     */
    public Boolean dropTable(String tableName)
    {
        return sqLiteDatabase.dropTable(tableName);
    }

    /**
     * 插入记录
     *
     * @param entity
     *            插入的实体
     * @return
     */
    public Boolean insert(Object entity)
    {
        return sqLiteDatabase.insert(entity);
    }

    /**
     * 插入记录
     *
     * @param table
     *            需要插入到的表
     * @param nullColumnHack
     *            不允许为空的行
     * @param values
     *            插入的值
     * @return
     */
    public Boolean insert(String table, String nullColumnHack,
                          ContentValues values)
    {
        return sqLiteDatabase.insert(table, nullColumnHack, values);
    }

    /**
     * 插入记录
     *
     * @param table
     *            需要插入到的表
     * @param nullColumnHack
     *            不允许为空的行
     * @param values
     *            插入的值
     * @return
     */
    public Boolean insertOrThrow(String table, String nullColumnHack,
                                 ContentValues values)
    {
        return sqLiteDatabase.insertOrThrow(table, nullColumnHack, values);
    }

    /**
     * 插入记录
     *
     * @param entity
     *            传入数据实体
     * @param updateFields
     *            插入到的字段,可设置为空
     * @return 返回true执行成功，否则执行失败
     */
    public Boolean insert(Object entity, ABArrayList updateFields)
    {
        return sqLiteDatabase.insert(entity, updateFields);
    }

    /**
     * 删除记录
     *
     * @param table
     *            被删除的表名
     * @param whereClause
     *            设置的WHERE子句时，删除指定的数据 ,如果null会删除所有的行。
     * @param whereArgs
     *
     * @return 返回true执行成功，否则执行失败
     */
    public Boolean delete(String table, String whereClause, String[] whereArgs)
    {
        return sqLiteDatabase.delete(table, whereClause, whereArgs);
    }


    /**
     * 删除记录
     *
     * @param clazz
     * @param where
     *            where语句
     * @return 返回true执行成功，否则执行失败
     */
    public Boolean delete(Class<?> clazz, String where)
    {
        return sqLiteDatabase.delete(clazz, where);
    }

    /**
     * 删除记录
     *
     * @param entity
     * @return 返回true执行成功，否则执行失败
     */
    public Boolean delete(Object entity)
    {
        return sqLiteDatabase.delete(entity);
    }

    /**
     * 更新记录
     *
     * @param table
     *            表名字
     * @param values
     * @param whereClause
     * @param whereArgs
     * @return 返回true执行成功，否则执行失败
     */
    public Boolean update(String table, ContentValues values,
                          String whereClause, String[] whereArgs)
    {
        return sqLiteDatabase.update(table, values, whereClause, whereArgs);
    }

    /**
     * 更新记录 这种更新方式只有才主键不是自增的情况下可用
     *
     * @param entity
     *            更新的数据
     * @return 返回true执行成功，否则执行失败
     */
    public Boolean update(Object entity)
    {
        return sqLiteDatabase.update(entity);
    }

    /**
     * 更新记录
     *
     * @param entity
     *            更新的数据
     * @param where
     *            where语句
     * @return
     */
    public Boolean update(Object entity, String where)
    {
        return sqLiteDatabase.update(entity, where);
    }

    /**
     * 执行查询，主要是SELECT, SHOW 等指令 返回数据集
     *
     * @param sql
     *            sql语句
     * @param selectionArgs
     * @return
     */
    public ArrayList<DBHashMap<String>> query(String sql, String[] selectionArgs)
    {
        return sqLiteDatabase.query(sql, selectionArgs);
    }

    /**
     * 执行查询，主要是SELECT, SHOW 等指令 返回数据集
     *
     * @param clazz
     * @param distinct
     *            限制重复，如过为true则限制,false则不用管
     * @param where
     *            where语句
     * @param groupBy
     *            groupBy语句
     * @param having
     *            having语句
     * @param orderBy
     *            orderBy语句
     * @param limit
     *            limit语句
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> query(Class<?> clazz, boolean distinct, String where,
                             String groupBy, String having, String orderBy, String limit)
    {
        return sqLiteDatabase.query(clazz, distinct, where, groupBy, having, orderBy, limit);
    }

    /**
     * 查询记录
     *
     * @param table
     *            表名
     * @param columns
     *            需要查询的列
     * @param selection
     *            格式化的作为 SQL WHERE子句(不含WHERE本身)。 传递null返回给定表的所有行。
     * @param selectionArgs
     * @param groupBy
     *            groupBy语句
     * @param having
     *            having语句
     * @param orderBy
     *            orderBy语句
     * @return
     */
    public ArrayList<DBHashMap<String>> query(String table, String[] columns,
                                              String selection, String[] selectionArgs, String groupBy,
                                              String having, String orderBy)
    {
        return sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    /**
     * 查询记录
     *
     * @param distinct
     *            限制重复，如过为true则限制,false则不用管
     * @param table
     *            表名
     * @param columns
     *            需要查询的列
     * @param selection
     *            格式化的作为 SQL WHERE子句(不含WHERE本身)。 传递null返回给定表的所有行。
     * @param selectionArgs
     * @param groupBy
     *            groupBy语句
     * @param having
     *            having语句
     * @param orderBy
     *            orderBy语句
     * @param limit
     *            limit语句
     * @return
     */
    public ArrayList<DBHashMap<String>> query(String table, boolean distinct,
                                              String[] columns, String selection, String[] selectionArgs,
                                              String groupBy, String having, String orderBy, String limit)
    {
        return sqLiteDatabase.query(table, distinct, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
    }

    /**
     * 查询记录
     *
     * @param table
     *            表名
     * @param columns
     *            需要查询的列
     * @param selection
     *            格式化的作为 SQL WHERE子句(不含WHERE本身)。 传递null返回给定表的所有行。
     * @param selectionArgs
     * @param groupBy
     *            groupBy语句
     * @param having
     *            having语句
     * @param orderBy
     *            orderBy语句
     * @param limit
     *            limit语句
     * @return
     */
    public ArrayList<DBHashMap<String>> query(String table, String[] columns,
                                              String selection, String[] selectionArgs, String groupBy,
                                              String having, String orderBy, String limit)
    {
        return sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
    }

    /**
     * 查询记录
     *
     * @param cursorFactory
     * @param distinct
     *            限制重复，如过为true则限制,false则不用管
     * @param table
     *            表名
     * @param columns
     *            需要查询的列
     * @param selection
     *            格式化的作为 SQL WHERE子句(不含WHERE本身)。 传递null返回给定表的所有行。
     * @param selectionArgs
     * @param groupBy
     *            groupBy语句
     * @param having
     *            having语句
     * @param orderBy
     *            orderBy语句
     * @param limit
     *            limit语句
     * @return
     */
    public ArrayList<DBHashMap<String>> queryWithFactory(
            android.database.sqlite.SQLiteDatabase.CursorFactory cursorFactory, boolean distinct, String table,
            String[] columns, String selection, String[] selectionArgs,
            String groupBy, String having, String orderBy, String limit)
    {
        return sqLiteDatabase.queryWithFactory(cursorFactory, distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
    }

}

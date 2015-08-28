package com.lemon.common.exception;

/**@projectName DBModules
 * @PackageName com.module.orm.exception
 * @Title DBException
 * @Description  数据库错误时抛出
 * @Author xflu
 * @Date 2014-3-24
 * @Version V1.0
 */
public class DBException extends Exception
{
	private static final long serialVersionUID = 1L;

	public DBException()
	{
		super();
	}

	public DBException(String detailMessage)
	{
		super(detailMessage);
	}

}

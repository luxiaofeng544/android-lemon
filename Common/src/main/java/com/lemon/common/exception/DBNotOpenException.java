package com.lemon.common.exception;

/**@projectName DBModules
 * @PackageName com.module.orm.exception
 * @Title DBNotOpenException
 * @Description  数据库无法打开异常
 * @Author xflu
 * @Date 2014-3-24
 * @Version V1.0
 */
public class DBNotOpenException extends Exception
{
	private static final long serialVersionUID = 1L;

	public DBNotOpenException()
	{
		super();
	}

	public DBNotOpenException(String detailMessage)
	{
		super(detailMessage);
	}

}

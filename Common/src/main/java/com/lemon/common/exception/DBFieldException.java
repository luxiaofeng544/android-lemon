package com.lemon.common.exception;

/**@projectName DBModules
 * @PackageName com.module.orm.exception
 * @Title DBFieldException
 * @Description  数据库字段错误时抛出
 * @Author xflu
 * @Date 2014-3-24
 * @Version V1.0
 */
public class DBFieldException extends DBException
{

	private static final long serialVersionUID = -6328047121656335941L;

	public DBFieldException()
	{
	}

	public DBFieldException(String detailMessage)
	{
		super(detailMessage);
	}

}

package com.lemon.common.log;

import android.util.Log;

/**@projectName DBModules
 * @PackageName com.module.orm.util.log
 * @Title PrintToLogCatLogger
 * @Description  PrintToLogCatLogger是框架中打印到LogCat上面的日志类
 * @Author xflu
 * @Date 2014-3-24
 * @Version V1.0
 */
public class PrintToLogCatLogger implements ILogger
{
	@Override
	public void d(String tag, String message)
	{
		Log.d(tag, message);
	}

	@Override
	public void e(String tag, String message)
	{
		Log.e(tag, message);
	}

	@Override
	public void i(String tag, String message)
	{
		Log.i(tag, message);
	}

	@Override
	public void v(String tag, String message)
	{
		Log.v(tag, message);
	}

	@Override
	public void w(String tag, String message)
	{
		Log.w(tag, message);
	}

	@Override
	public void println(int priority, String tag, String message)
	{
		Log.println(priority, tag, message);
	}

	@Override
	public void open()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close()
	{
		// TODO Auto-generated method stub
		
	}
}

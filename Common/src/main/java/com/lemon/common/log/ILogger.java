package com.lemon.common.log;

/**@projectName DBModules
 * @PackageName com.module.orm.util.log
 * @Title ILogger
 * @Description   Logger是一个日志的接口
 * @Author xflu
 * @Date 2014-3-24
 * @Version V1.0
 */
public interface ILogger
{
	void v(String tag, String message);

	void d(String tag, String message);

	void i(String tag, String message);

	void w(String tag, String message);

	void e(String tag, String message);

	void open();

	void close();

	void println(int priority, String tag, String message);
}

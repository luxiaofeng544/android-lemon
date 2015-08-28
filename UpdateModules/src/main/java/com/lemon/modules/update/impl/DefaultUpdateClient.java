/**
 * 
 */
package com.lemon.modules.update.impl;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

import com.lemon.modules.update.IUpdate;
import com.lemon.modules.update.IUpdateBean;
import com.lemon.modules.update.IUpdateHandler;
import com.lemon.modules.update.IUpdateInterfaceBean;
import com.lemon.modules.update.IUpdateQuery;

/**@projectName UpdateModules
 * @PackageName com.lemon.modules.update.impl
 * @Title UpdateClient
 * @Description  更新信息
 * @Author Luxiaofeng
 * @Date 2014-4-13
 * @Version V1.0
 */
public class DefaultUpdateClient implements IUpdate {

	public DefaultUpdateClient(){}
	/*private volatile static DefaultUpdateClient updateClient;
	private DefaultUpdateClient(){}
	public static DefaultUpdateClient getInstance(){
		if(updateClient==null){
			synchronized (DefaultUpdateClient.class) {
				if(updateClient==null){
					updateClient = new DefaultUpdateClient();
				}
			}
		}
		return updateClient;
	}*/
	
	/**
	 * @see com.lemon.modules.update.IUpdate#checkVersion(com.lemon.modules.update.IUpdateQuery, com.lemon.modules.update.IUpdateHandler)
	 */
	@Override
	public IUpdateBean checkVersion(IUpdateQuery query, IUpdateHandler handler,IUpdateInterfaceBean interfaceBean) {
		InputStream inStream = query.query(interfaceBean);
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser saxParser = spf.newSAXParser();
			saxParser.parse(inStream, (DefaultHandler) handler);
			if (inStream != null) {
				inStream.close();
				inStream = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return handler.getObject();
	}

}

/**
 * 
 */
package com.lemon.modules.update.impl;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.lemon.modules.update.IUpdateBean;
import com.lemon.modules.update.IUpdateHandler;
import com.lemon.modules.update.util.LogUtils;

/**@projectName UpdateModules
 * @PackageName com.lemon.modules.update.impl
 * @Title DefaultXmlHandler
 * @Description  
 * @Author Luxiaofeng
 * @Date 2014-4-13
 * @Version V1.0
 */
public class DefaultXmlHandler  extends DefaultHandler implements IUpdateHandler{
	private static final String tag = DefaultXmlHandler.class.getName();
	private UpdateBean updateBean;
	private boolean isSystemOut = true;
	private String temp="";
    private String preTag;
	
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		LogUtils.printWithSystemOut(isSystemOut,"startElement  :  " + qName);
		if ("root".equals(qName)) {
			updateBean = new UpdateBean();
		}
		preTag = qName;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (updateBean !=null ){
            String date = new String(ch,start,length);
            if ("versionName".equals(preTag)) {
                temp+=date;
            } else if ("versionCode".equals(preTag)) {
                temp+=date;
            } else if ("versionDescription".equals(preTag)) {
                temp+=date;
            } else if ("versionDownUrl".equals(preTag)) {
                temp+=date;
            } else if ("isForce".equals(preTag)) {
                temp+=date;
            }
        }
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		LogUtils.printWithSystemOut(isSystemOut,"endElement  :  " + localName);
		if(updateBean !=null &&"versionName".equals(qName)){
			updateBean.setVersionName(temp.trim());
			temp = "";
		} else if(updateBean !=null &&"versionCode".equals(qName)){
			updateBean.setVersionCode(temp.trim());
			temp = "";
		} else if(updateBean !=null &&"versionDescription".equals(qName)){
			updateBean.setVersionDescription(temp.trim());
			temp = "";
		} else if(updateBean !=null &&"versionDownUrl".equals(qName)){
			updateBean.setVersionDownUrl(temp.trim());
			temp = "";
		} else if(updateBean !=null &&"isForce".equals(qName)){
			updateBean.setForce(Boolean.parseBoolean(temp.trim()));
			temp = "";
		}
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	public IUpdateBean getObject() {
		return updateBean;
	}
}

package cn.com.shanda.aesop.server;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadIpList {
	
	@SuppressWarnings("rawtypes")
	public static void read() throws DocumentException{
		
		IpList.list.removeAllElements();
		
		SAXReader saxReader = new SAXReader();
		
		//读取动态IP列表
		Document document = saxReader.read(new File(InitParam.xmlpath1));
		
		Element root = document.getRootElement();
		
		List IPList = root.elements("IP");
		
		//清空主服务器的url地址
		for (int i = 0; i < IPList.size(); ++i) {
			
			String url = ((Element)IPList.get(i)).elementText("url");
			
			if (null != url && !"".equals(url)) {
				IpList.list.add(url);
				
			}

		}

	}
}

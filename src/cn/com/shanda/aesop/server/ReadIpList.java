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
		
		//��ȡ��̬IP�б�
		Document document = saxReader.read(new File(InitParam.xmlpath1));
		
		Element root = document.getRootElement();
		
		List IPList = root.elements("IP");
		
		//�������������url��ַ
		for (int i = 0; i < IPList.size(); ++i) {
			
			String url = ((Element)IPList.get(i)).elementText("url");
			
			if (null != url && !"".equals(url)) {
				IpList.list.add(url);
				
			}

		}

	}
}

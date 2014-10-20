package cn.com.shanda.aesop.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class RemoveRemoteIp {
	public void removeRemoteIp() throws DocumentException, IOException{
		SAXReader saxReader = new SAXReader();
		
		//��ȡ��̬IP�б�
		Document document = saxReader.read(new File(InitParam.xmlpath1));
		
		Element root = document.getRootElement();
		
		Element remote = root.element("remote");
		
		//�������������url��ַ
		remote.element("url").setText("");
		
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(InitParam.xmlpath1));

		xmlWriter.write(document);
		
		xmlWriter.close();
	}
}

package cn.com.shanda.aesop.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;



public class IpXML {
	
	public static Vector<String> list = new Vector<String>();
	
	public static void read() throws Exception {
		
		SAXReader saxReader = new SAXReader();
		
		//��ȡ��̬IP�б�
		Document doc = saxReader.read(new File(InitParam.xmlpath2));
		
		Element root = doc.getRootElement();
		
		@SuppressWarnings("rawtypes")
		List IPList = root.elements("IP");
		
		//��ӵ�vector������
		for (int i = 0; i < IPList.size(); ++i) {
			String url = ((Element)IPList.get(i)).elementText("url");
			list.add(url);
		}
		
		//��ʾvector��������IP��ַ
		
	}


	
	@SuppressWarnings("rawtypes")
	public void writeRemote(String remoteip) throws DocumentException, IOException {
		
		SAXReader saxReader = new SAXReader();
		
		SAXReader saxReader2 = new SAXReader();
		
		//��ȡ��̬IP�б�
		Document doc = saxReader.read(new File(InitParam.xmlpath2));
		
		//��ȡ��̬IP�б�
		Document dynamicDoc = saxReader2.read(new File(InitParam.xmlpath1));
		
		Element root = doc.getRootElement();
		
		Element dRoot = dynamicDoc.getRootElement();
		
		List dIPList= dRoot.elements("IP"); 
		
		List IPList = root.elements("IP");
		
		Element remoteElement= dRoot.element("remote"); 
		
		remoteElement.element("url").setText(remoteip);
		
		//�Ӿ�̬IP��ַ�б��еõ���������ַ�ı��
		String id = getId(IPList, remoteip);
		
		//д�붯̬IP�б�
		writeIP(dIPList, id, remoteip);
		
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(InitParam.xmlpath1));
		
		xmlWriter.write(dynamicDoc);
		
		xmlWriter.close();
		
	}
	
	/**
	 * �õ���ǰIP��ַ��Ӧ�������ı��
	 * @param IPList
	 * @param Ip
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getId (List IPList , String Ip) {
		
		for(int j = 0; j < IPList.size(); ++j) {
			
			String url = ((Element)IPList.get(j)).elementText("url");
			
			if (Ip.equals(url)) {
				
				return (String) ((Element)IPList.get(j)).elementText("id");
		
			}
		}
		return null;
	}
	
	/**
	 * �����Ϊid�ķ�������IP��ַд�붯̬IP�б�
	 * @param IPList
	 * @param id
	 * @param Ip
	 */
	@SuppressWarnings("rawtypes")
	public void writeIP (List IPList , String id, String Ip) {
		
		for(int j = 0; j < IPList.size(); ++j) {
			
			String idValue = ((Element)IPList.get(j)).elementText("id");
			
			if (id.equals(idValue)) {
				
				((Element)IPList.get(j)).element("url").setText(Ip);
		
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void clearips() throws IOException, DocumentException{
		
		SAXReader saxReader = new SAXReader();
		
		Document doc = saxReader.read(new File(InitParam.xmlpath1));
		
		Element root = doc.getRootElement();
		
		List IPList = root.elements("IP");
		
		for (int i = 0; i < IPList.size(); ++i) {
			
			((Element)IPList.get(i)).element("url").setText("");

		}
		
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(InitParam.xmlpath1));
		
		xmlWriter.write(doc);
		
		xmlWriter.close();
	}
	
}

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
		
		//读取静态IP列表
		Document doc = saxReader.read(new File(InitParam.xmlpath2));
		
		Element root = doc.getRootElement();
		
		@SuppressWarnings("rawtypes")
		List IPList = root.elements("IP");
		
		//添加到vector向量里
		for (int i = 0; i < IPList.size(); ++i) {
			String url = ((Element)IPList.get(i)).elementText("url");
			list.add(url);
		}
		
		//显示vector向量里存的IP地址
		
	}


	
	@SuppressWarnings("rawtypes")
	public void writeRemote(String remoteip) throws DocumentException, IOException {
		
		SAXReader saxReader = new SAXReader();
		
		SAXReader saxReader2 = new SAXReader();
		
		//读取静态IP列表
		Document doc = saxReader.read(new File(InitParam.xmlpath2));
		
		//读取动态IP列表
		Document dynamicDoc = saxReader2.read(new File(InitParam.xmlpath1));
		
		Element root = doc.getRootElement();
		
		Element dRoot = dynamicDoc.getRootElement();
		
		List dIPList= dRoot.elements("IP"); 
		
		List IPList = root.elements("IP");
		
		Element remoteElement= dRoot.element("remote"); 
		
		remoteElement.element("url").setText(remoteip);
		
		//从静态IP地址列表中得到服务器地址的编号
		String id = getId(IPList, remoteip);
		
		//写入动态IP列表
		writeIP(dIPList, id, remoteip);
		
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(InitParam.xmlpath1));
		
		xmlWriter.write(dynamicDoc);
		
		xmlWriter.close();
		
	}
	
	/**
	 * 得到当前IP地址对应服务器的编号
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
	 * 将编号为id的服务器的IP地址写入动态IP列表
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

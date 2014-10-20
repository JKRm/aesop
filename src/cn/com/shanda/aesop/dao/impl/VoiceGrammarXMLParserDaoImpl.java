package cn.com.shanda.aesop.dao.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import cn.com.shanda.aesop.dao.VoiceGrammarXMLParserDao;

public class VoiceGrammarXMLParserDaoImpl implements VoiceGrammarXMLParserDao {

	private String filePath = System.getenv("CATALINA_HOME") 
									+ "\\webapps\\aesop\\voice\\grammar.xml";
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.aesop.dao.VoiceGrammarXMLParserDao#add(java.lang.String)
	 */
	@Override
	public void add(String text) throws DocumentException, IOException {
		SAXReader saxReader = new SAXReader();
		
		Document doc = saxReader.read(new File(filePath));
		Element root = doc.getRootElement();
		
		List list = root.element("DEFINE").elements();
		
		int insertValue = 1;
		
		if (null != list && 0 != list.size()) {
			
			Element idElement = (Element)list.get(list.size() - 1);
			
			String maxValue = idElement.attributeValue("VAL");
			insertValue = Integer.parseInt(maxValue) + 1;
		}
		String attrName = "VID_SubName" + insertValue % 100;
		String attrValue = String.valueOf(insertValue);
		
		
		Element defineElement = root.element("DEFINE");
		defineElement.addElement("ID").addAttribute("NAME", attrName).addAttribute("VAL", attrValue);
		
		root.elementByID("VID_SubNameRule").element("L").addElement("P").addAttribute("VAL", attrName).setText(text);
		
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("GB2312");
		
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(filePath), format);
		
		xmlWriter.write(doc);
		
		xmlWriter.close();
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.aesop.dao.VoiceGrammarXMLParserDao#add(java.util.List)
	 */
	@Override
	public void add(Set<String> addList) throws DocumentException, IOException {
		SAXReader saxReader = new SAXReader();
		
		Document doc = saxReader.read(new File(filePath));
		Element root = doc.getRootElement();
		
		List list = root.element("DEFINE").elements();
		
		int maxNum = 0;
		
		if (null != list && 0 != list.size()) {
			
			Element idElement = (Element)list.get(list.size() - 1);
			
			String maxValue = idElement.attributeValue("VAL");
			maxNum = Integer.parseInt(maxValue);
		}
		int i = 0;
		for (Iterator<String> it =  addList.iterator(); it.hasNext(); i++) {
			int insertValue = maxNum + 1 + i;
			
			String attrName = "VID_SubName" + insertValue;
			String attrValue = String.valueOf(insertValue);
			
			root.element("DEFINE").addElement("ID").addAttribute("NAME", attrName).addAttribute("VAL", attrValue);
			root.elementByID("VID_SubNameRule").element("L").addElement("P").addAttribute("VAL", attrName).setText(it.next());
		}
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("GB2312");
		
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(filePath), format);
		
		xmlWriter.write(doc);
		
		xmlWriter.close();
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.aesop.dao.VoiceGrammarXMLParserDao#clear()
	 */
	@Override
	public void clear() throws DocumentException, IOException {
		
		SAXReader saxReader = new SAXReader();
		
		Document doc = saxReader.read(new File(filePath));
		Element root = doc.getRootElement();
		
		List list = root.element("DEFINE").elements();
		
		for (int i = 2; i < list.size(); ++i) {
			root.element("DEFINE").remove((Element)list.get(i));
		}
		
		root.elementByID("VID_SubNameRule").element("L").clearContent();
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("GB2312");
		
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(filePath), format);
		
		xmlWriter.write(doc);
		
		xmlWriter.close();
	}
}

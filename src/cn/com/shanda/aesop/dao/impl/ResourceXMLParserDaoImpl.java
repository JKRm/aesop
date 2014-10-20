package cn.com.shanda.aesop.dao.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import cn.com.shanda.aesop.dao.ResourceXMLParserDao;
import cn.com.shanda.aesop.pojo.ResourceItem;
import cn.com.shanda.aesop.server.InitParam;
import cn.com.shanda.aesop.service.impl.IndexServiceImpl;
import cn.com.shanda.aesop.util.DateUtil;



public class ResourceXMLParserDaoImpl implements ResourceXMLParserDao {
	
	
	
	private static final String TEST_XML_PATH = InitParam.filepath;

	/* (non-Javadoc)
	 * @see cn.com.shanda.resourceSelector.dao.impl.XMLParserDao#getResources()
	 */
	@Override
	public ArrayList<ResourceItem> getResources() throws DocumentException, ParseException {
		
		ArrayList<ResourceItem> list = new ArrayList<ResourceItem>();
		
		SAXReader saxReader = new SAXReader();
		
		Document doc = saxReader.read(new File(TEST_XML_PATH));
		Element root = doc.getRootElement();
		
		List itemList = root.elements("resourceitem");
		
		for(int i = 0; i < itemList.size(); ++i) {
			
			ResourceItem item = new ResourceItem();
			
//			item.setId(((Element)itemList.get(i)).elementText("id"));
			item.setTitle(((Element)itemList.get(i)).elementText("title"));
			item.setKeywords(((Element)itemList.get(i)).elementText("keywords"));
			item.setKind(((Element)itemList.get(i)).elementText("kind"));
			item.setDescription(((Element)itemList.get(i)).elementText("describe"));
			item.setUrl(((Element)itemList.get(i)).elementText("url"));
			item.setAuthor(((Element)itemList.get(i)).elementText("author"));
			item.setPublisher(((Element)itemList.get(i)).elementText("publisher"));
			item.setDate(DateUtil.getDate(((Element)itemList.get(i)).elementText("date")));
			
			list.add(item);
		}
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.resourceSelector.dao.impl.XMLParserDao#getResource(java.lang.String)
	 */
	@Override
	public ResourceItem getResource(String url) throws DocumentException, ParseException {
		
		SAXReader saxReader = new SAXReader();
		
		Document doc = saxReader.read(new File(TEST_XML_PATH));
		Element root = doc.getRootElement();
		
		List itemList = root.elements("resourceitem");
		
		for(int i = 0; i < itemList.size(); ++i) {
			
			if (((Element)itemList.get(i)).elementText("url").equals(url)) {
				
				ResourceItem item = new ResourceItem();
//				item.setId(((Element)itemList.get(i)).elementText("id"));
				item.setTitle(((Element)itemList.get(i)).elementText("title"));
				item.setKeywords(((Element)itemList.get(i)).elementText("keywords"));
				item.setKind(((Element)itemList.get(i)).elementText("kind"));
				item.setDescription(((Element)itemList.get(i)).elementText("describe"));
				item.setUrl(((Element)itemList.get(i)).elementText("url"));
				item.setAuthor(((Element)itemList.get(i)).elementText("author"));
				item.setPublisher(((Element)itemList.get(i)).elementText("publisher"));
				item.setDate(DateUtil.getDate(((Element)itemList.get(i)).elementText("date")));
				
				return item;
				
			}
			
		}
		
		return null;
	}
	
	public void add(ResourceItem item) throws DocumentException, IOException {
		
		SAXReader saxReader = new SAXReader();
		
		Document doc = saxReader.read(new File(TEST_XML_PATH));
		Element root = doc.getRootElement();
		
//		Node resource = root.selectSingleNode("//resourceitem[position()=last()]");
//		String lastId = ((Element)resource).elementText("id");
//		String insertId = String.valueOf(Integer.parseInt(lastId) + 1);
		
		Element addResource = root.addElement("resourceitem");
		String date = DateFormat.getDateTimeInstance().format(item.getDate());
		
		addResource.addElement("title").setText(item.getTitle());
		addResource.addElement("keywords").setText(item.getKeywords());
		addResource.addElement("kind").setText(item.getKind());
		addResource.addElement("describe").setText(item.getDescription());
		addResource.addElement("date").setText(date);
		addResource.addElement("url").setText(item.getUrl());
		addResource.addElement("author").setText(item.getAuthor());
		addResource.addElement("publisher").setText(item.getPublisher());
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(TEST_XML_PATH), format);
		
		xmlWriter.write(doc);
		
		xmlWriter.close();
	}
	
	public void add(List<ResourceItem> list) throws DocumentException, IOException {
		
		for (int i = 0; i < list.size(); ++i) {
			add(list.get(i));
		}
	}
	
	public void delete(String url) throws DocumentException, IOException {
		
		SAXReader saxReader = new SAXReader();
		
		Document doc = saxReader.read(new File(TEST_XML_PATH));
		Element root = doc.getRootElement();
		
		List itemList = root.elements("resourceitem");
		
		for(int i = 0; i < itemList.size(); ++i) {
			
			if (((Element)itemList.get(i)).elementText("url").equals(url)) {
				
				root.remove((Element)itemList.get(i));
				break;
			}
		}        
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(TEST_XML_PATH), format);
		
		xmlWriter.write(doc);
		
		xmlWriter.close();
	}
	
	
	
}

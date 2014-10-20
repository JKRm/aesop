package cn.com.shanda.aesop.dao.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import cn.com.shanda.aesop.dao.PreviewXMLParserDao;
import cn.com.shanda.aesop.pojo.PreviewItem;

public class PreviewXMLParserDaoImpl implements PreviewXMLParserDao {

	private static final String PREVIEW_XML_PATH = System.getenv("CATALINA_HOME") + 
										"\\webapps\\aesop\\ResourcePreview.xml";
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.aesop.dao.impl.PreviewXMLParserDao#add(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void add(String resourceName, String previewUrl, String kind) throws DocumentException, IOException {
		
		SAXReader saxReader = new SAXReader();
		
		Document doc = saxReader.read(new File(PREVIEW_XML_PATH));
		Element root = doc.getRootElement();
		
		Element addPreview = root.addElement("preview");
		addPreview.addElement("resourcename").setText(resourceName);
		addPreview.addElement("previewurl").setText(previewUrl);
		addPreview.addElement("kind").setText(kind);
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(PREVIEW_XML_PATH), format);
		
		xmlWriter.write(doc);
		
		xmlWriter.close();
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.aesop.dao.impl.PreviewXMLParserDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String resourceName) throws DocumentException, IOException {
		
		SAXReader saxReader = new SAXReader();
		
		Document doc = saxReader.read(new File(PREVIEW_XML_PATH));
		Element root = doc.getRootElement();
		
		Element preview = (Element)root.selectSingleNode("/allpreview/preview[resourcename=" + resourceName + "]");
		
		root.remove(preview);
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(PREVIEW_XML_PATH), format);
		
		xmlWriter.write(doc);
		
		xmlWriter.close();
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.aesop.dao.impl.PreviewXMLParserDao#getPreivewUrl(java.lang.String)
	 */
	@Override
	public String getPreivewUrl(String resourceName) throws DocumentException {

		SAXReader saxReader = new SAXReader();
		
		Document doc = saxReader.read(new File(PREVIEW_XML_PATH));
		Element root = doc.getRootElement();
		
		Element preview = (Element)root.selectSingleNode("/allpreview/preview[resourcename=" + resourceName + "]");
		
		return preview.elementText("previewUrl");
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.aesop.dao.impl.PreviewXMLParserDao#getPreviewItems()
	 */
	@Override
	public Map<String, List<PreviewItem>> getPreviewItems() {
		
		Map<String, List<PreviewItem>>map = new HashMap<String, List<PreviewItem>>();
		
		SAXReader saxReader = new SAXReader();
		
		Document doc = null;
		
		try {
			doc = saxReader.read(new File(PREVIEW_XML_PATH));
		} catch (DocumentException e) {
//			e.printStackTrace();
		}
		Element root = doc.getRootElement();
		
		Iterator it = root.elementIterator();
		
		while (it.hasNext()) {
			
			Element preview = (Element)it.next();
			
			String name = preview.elementText("resourcename");
			String url = preview.elementText("previewurl");
			PreviewItem item = new PreviewItem(name, url);
			
			String kind = preview.elementText("kind");
			
			if (map.containsKey(kind)) {
				map.get(kind).add(item);
			} else {
				
				List<PreviewItem> list = new ArrayList<PreviewItem>();
				list.add(item);
				
				map.put(kind, list);
			}
		}
		
		return map;
	}
}

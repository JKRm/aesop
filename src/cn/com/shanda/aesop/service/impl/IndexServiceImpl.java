package cn.com.shanda.aesop.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.dom4j.DocumentException;
import org.jdom.JDOMException;

import cn.com.shanda.aesop.dao.IndexDao;
import cn.com.shanda.aesop.dao.RSSXMLParserDao;
import cn.com.shanda.aesop.dao.ResourceXMLParserDao;
import cn.com.shanda.aesop.dao.SuggestDao;
import cn.com.shanda.aesop.dao.impl.IndexDaoImpl;
import cn.com.shanda.aesop.dao.impl.RSSXMLParserDaoImpl;
import cn.com.shanda.aesop.dao.impl.ResourceXMLParserDaoImpl;
import cn.com.shanda.aesop.dao.impl.SuggestDaoImpl;
import cn.com.shanda.aesop.pojo.ResourceItem;
import cn.com.shanda.aesop.service.IndexService;
import cn.com.shanda.aesop.service.PinyinService;
import cn.com.shanda.aesop.service.VoiceService;
import cn.com.shanda.aesop.util.DocumentUtil;

import com.sun.syndication.io.FeedException;


public class IndexServiceImpl implements IndexService {
	
	IndexDao indexDao = new IndexDaoImpl();
	
	SuggestDao suggestDao = new SuggestDaoImpl();
	
	PinyinService pinyinService = new PinyinServiceImpl();
	
	RSSXMLParserDao rssParser = new RSSXMLParserDaoImpl();
	
	VoiceService voiceService = new VoiceServiceImpl();
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.resourceSelector.service.IndexService#createIndex()
	 */
	@Override
	public void createIndex() {
		
		ResourceXMLParserDao parser = new ResourceXMLParserDaoImpl();
		
		try {
			
			deleteALLIndex();
			
			List<ResourceItem> list = parser.getResources();
			
			System.out.println("拼音初始化中...");
			pinyinService.add(list);
			
			System.out.println("提示文本初始化中...");
			suggestDao.createSuggestText(list);
			
			rssParser.add(list);
			
			System.out.println("语音初始化中...");
			voiceService.add(list);
			
			System.out.println("倒排索引建立中...");
			indexDao.createIndex(DocumentUtil.getDocuments(list));
			
		} catch (IOException e) {
//			e.printStackTrace();
		} catch (DocumentException e) {
//			e.printStackTrace();
		} catch (ParseException e) {
//			e.printStackTrace();
		} catch (SQLException e) {
//			e.printStackTrace();
		} catch (BadHanyuPinyinOutputFormatCombination e) {
//			e.printStackTrace();
		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		} catch (Exception e) {
//		e.printStackTrace();
	}
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.resourceSelector.service.IndexService#createIndex(cn.com.shanda.resourceSelector.pojo.ResourceItem)
	 */
	@Override
	public void createIndex(ResourceItem item) {
		
		try {
			
			pinyinService.add(item);
			suggestDao.addSuggestText(item);
			rssParser.add(item);
			voiceService.add(item);
			indexDao.createIndex(DocumentUtil.getDocument(item));
			
		} catch (IOException e) {
//			e.printStackTrace();
		} catch (SQLException e) {
//			e.printStackTrace();
		} catch (BadHanyuPinyinOutputFormatCombination e) {
//			e.printStackTrace();
		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
		} catch (DocumentException e) {
//			e.printStackTrace();
		} catch (FeedException e) {
//			e.printStackTrace();
		} 
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.resourceSelector.service.IndexService#deleteALLIndex()
	 */
	@Override
	public void deleteALLIndex() {
		
		try {
			pinyinService.deleteAll();
			suggestDao.deleteAll();
			rssParser.clear();
			voiceService.clear();
			indexDao.deleteAllIndex();
			
		} catch (IOException e) {
//			e.printStackTrace();
		} catch (SQLException e) {
//			e.printStackTrace();
		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
		} 
		catch (FeedException e) {
//			e.printStackTrace();
		} 
		catch (DocumentException e) {
//			e.printStackTrace();
		} 
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.resourceSelector.service.IndexService#deleteIndex(java.lang.String)
	 */
	@Override
	public void deleteIndex(String url) {
		
		Term deleteTerm = new Term("url", url);
		
		try {
			
			indexDao.deleteIndex(deleteTerm);
			
		} catch (IOException e) {
//			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.resourceSelector.service.IndexService#updateIndex(cn.com.shanda.resourceSelector.pojo.ResourceItem, java.lang.String)
	 */
	@Override
	public void updateIndex(ResourceItem addItem, String deleteUrl) {
		
		Document addDoc = DocumentUtil.getDocument(addItem);
		
		Term deleteTerm = new Term("url", deleteUrl);
		
		try {
			
			indexDao.updateIndex(deleteTerm, addDoc);
			
		} catch (IOException e) {
//			e.printStackTrace();
		}
	}
	
}

package cn.com.shanda.aesop.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dom4j.DocumentException;

import cn.com.shanda.aesop.dao.VoiceGrammarXMLParserDao;
import cn.com.shanda.aesop.dao.impl.VoiceGrammarXMLParserDaoImpl;
import cn.com.shanda.aesop.pojo.ResourceItem;
import cn.com.shanda.aesop.service.VoiceService;

public class VoiceServiceImpl implements VoiceService {

	private VoiceGrammarXMLParserDao voiceDao = new VoiceGrammarXMLParserDaoImpl();
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.aesop.service.VoiceService#add(cn.com.shanda.aesop.pojo.ResourceItem)
	 */
	@Override
	public void add(ResourceItem item) throws DocumentException, IOException {
		voiceDao.add(analyze(item));
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.aesop.service.VoiceService#add(java.util.List)
	 */
	@Override
	public void add(List<ResourceItem> list) throws DocumentException, IOException {
		
		Set<String> set = new HashSet<String>();
		
		for (ResourceItem item:list) {
			set.addAll(analyze(item));
		}
		
		voiceDao.add(set);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.aesop.service.VoiceService#clear()
	 */
	@Override
	public void clear() throws DocumentException, IOException {
		voiceDao.clear();
	}
	
	private Set<String> analyze(ResourceItem item) throws IOException {
		
		String keywords = item.getKeywords();
		
		//将关键字分开
		Set<String> splitKeyword = new HashSet<String>();
		if (keywords.indexOf(",") != -1) {
			
			String[] keywordArray = keywords.split(",");
			for (int i = 0; i < keywordArray.length; ++i) {
				splitKeyword.add(keywordArray[i].trim());
			}
			
		}else if (keywords.indexOf("，") != -1) {
			String[] keywordArray = keywords.split("，");
			for (int i = 0; i < keywordArray.length; ++i) {
				splitKeyword.add(keywordArray[i].trim());
			}
		} else if (keywords.indexOf(" ") != -1) {
			String[] keywordArray = keywords.split(" ");
			for (int i = 0; i < keywordArray.length; ++i) {
				splitKeyword.add(keywordArray[i].trim());
			}
		}
		
		return splitKeyword;
	}
}

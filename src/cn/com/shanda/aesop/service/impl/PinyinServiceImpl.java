package cn.com.shanda.aesop.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import cn.com.shanda.aesop.dao.PinyinDao;
import cn.com.shanda.aesop.dao.impl.PinyinDaoImpl;
import cn.com.shanda.aesop.pojo.ResourceItem;
import cn.com.shanda.aesop.service.PinyinService;
import cn.com.shanda.aesop.util.AnalyzerUtil;

public class PinyinServiceImpl implements PinyinService {
	
	PinyinDao pinyinDao= new PinyinDaoImpl();
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.resourceSelector.service.impl.PinyinService#add(java.util.List)
	 */
	@Override
	public void add(List<ResourceItem> list) throws IOException, SQLException, BadHanyuPinyinOutputFormatCombination {
		
		Set<String> set = new HashSet<String>();
		
		for (int i = 0; i < list.size(); ++i) {
			set.addAll(analyze(list.get(i)));
		}
		
		pinyinDao.add(set);
	}
	
	@Override
	public void deleteAll() throws SQLException, ClassNotFoundException{
		
		pinyinDao.deleteAll();
	}
	
	public void add(ResourceItem item) throws SQLException, BadHanyuPinyinOutputFormatCombination, IOException {
		
		Set<String> set = new HashSet<String>();
		set.addAll(analyze(item));
		
		pinyinDao.add(set);
	}
	
	@Override
	public boolean delete(String chinese) throws SQLException, BadHanyuPinyinOutputFormatCombination {

		return pinyinDao.delete(chinese);
	}

	@Override
	public boolean add(String chinese) throws SQLException, BadHanyuPinyinOutputFormatCombination {
		
		return pinyinDao.add(chinese);
	}
	
	public boolean update(String deleteChinese, String addChinese) throws SQLException, BadHanyuPinyinOutputFormatCombination {
		
		return (delete(deleteChinese) && add(addChinese));
	}

	private List<String> analyze(ResourceItem item) throws IOException {
		
		List<String> chineseList = new ArrayList<String>();
		
		String keywords = item.getKeywords();
		String title = item.getTitle();
		
		//将关键字分开
		List<String> splitKeyword = new ArrayList<String>();
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
		
		//将文档标题分词
		List<String> splitTitle = AnalyzerUtil.analyze(title);
		
		chineseList.addAll(splitKeyword);
		chineseList.addAll(splitTitle);
		
		return chineseList;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.resourceSelector.service.impl.PinyinService#getChinese(java.lang.String)
	 */
	@Override
	public List<String> getChinese(String pinyin) throws SQLException, BadHanyuPinyinOutputFormatCombination {
		
		return pinyinDao.getChinese(pinyin);
	}
}


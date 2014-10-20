/**
 * 更新时间 8.8 10:20
 */
package cn.com.shanda.aesop.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import cn.com.shanda.aesop.pojo.ResourceItem;

public interface PinyinService {

	public abstract void add(List<ResourceItem> list) throws IOException,
			SQLException, BadHanyuPinyinOutputFormatCombination,
			ClassNotFoundException;

	public abstract List<String> getChinese(String pinyin) throws SQLException,
			BadHanyuPinyinOutputFormatCombination, ClassNotFoundException;
	
	public abstract void deleteAll() throws SQLException, ClassNotFoundException;

	public abstract boolean delete(String chinese) throws SQLException, ClassNotFoundException, BadHanyuPinyinOutputFormatCombination;
	
	public abstract boolean add(String chinese) throws SQLException, ClassNotFoundException, BadHanyuPinyinOutputFormatCombination;
	
	public void add(ResourceItem item) throws SQLException, BadHanyuPinyinOutputFormatCombination, ClassNotFoundException, IOException;

}
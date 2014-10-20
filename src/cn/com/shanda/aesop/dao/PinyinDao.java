package cn.com.shanda.aesop.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public interface PinyinDao {

	public abstract boolean add(Set<String> set) throws SQLException,
			BadHanyuPinyinOutputFormatCombination;

	public abstract boolean add(String chinese) throws SQLException,
			BadHanyuPinyinOutputFormatCombination;

	public abstract boolean delete(String chinese) throws SQLException,
			BadHanyuPinyinOutputFormatCombination;

	public abstract List<String> getChinese(String pinyin) throws SQLException,
			BadHanyuPinyinOutputFormatCombination;

	public abstract boolean deleteAll() throws SQLException;

}
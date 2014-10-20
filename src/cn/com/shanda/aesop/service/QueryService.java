package cn.com.shanda.aesop.service;

import java.util.Date;

import cn.com.shanda.aesop.pojo.QueryResult;

public interface QueryService {
	
	/**
	 * 
	 * @param queryText 普通检索条件
	 * @param wholeKey  高级检索中包含完整关键字
	 * @param excludingText  高级检索中不包含该关键字
	 * @param field  高级检索中检索的字段
	 * @param author  高级检索中检索资源发布者
	 * @param publisher  高级检索中检索资源发布者的单位
	 * @param types  检索资源类型
	 * @param date  检索资源发布日期的时间限制
	 * @return
	 */
	public abstract QueryResult search(String queryText, String wholeKey, String excludingText, String field, 
			String author, String publisher, String[] types, Date[] date);

}
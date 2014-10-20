package cn.com.shanda.aesop.service;

import java.util.Date;

import cn.com.shanda.aesop.pojo.QueryResult;

public interface QueryService {
	
	/**
	 * 
	 * @param queryText ��ͨ��������
	 * @param wholeKey  �߼������а��������ؼ���
	 * @param excludingText  �߼������в������ùؼ���
	 * @param field  �߼������м������ֶ�
	 * @param author  �߼������м�����Դ������
	 * @param publisher  �߼������м�����Դ�����ߵĵ�λ
	 * @param types  ������Դ����
	 * @param date  ������Դ�������ڵ�ʱ������
	 * @return
	 */
	public abstract QueryResult search(String queryText, String wholeKey, String excludingText, String field, 
			String author, String publisher, String[] types, Date[] date);

}
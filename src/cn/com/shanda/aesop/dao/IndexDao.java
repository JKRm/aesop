package cn.com.shanda.aesop.dao;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;

import cn.com.shanda.aesop.pojo.QueryResult;

public interface IndexDao {

	/**
	 * Ϊ�����ĵ���������
	 * @param doc
	 * @throws IOException
	 */
	public abstract void createIndex(Document doc) throws IOException;

	public abstract void createIndex(List<Document> list) throws IOException;

	/**
	 * ɾ������term���ĵ�����
	 * @param term
	 * @throws IOException
	 */
	public abstract void deleteIndex(Term term) throws IOException;

	/**
	 * ɾ����������
	 * @throws IOException
	 */
	public abstract void deleteAllIndex() throws IOException;

	/**
	 * ͨ��ɾ������term���ĵ�������µ�document�ĵ�����������
	 * @param term
	 * @param document
	 * @throws IOException
	 */
	public abstract void updateIndex(Term term, Document document)
			throws IOException;

	public abstract QueryResult search(Query query, Filter filter, Sort sort,
			int firstResult, int maxResults) throws IOException;

}
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
	 * 为单个文档建立索引
	 * @param doc
	 * @throws IOException
	 */
	public abstract void createIndex(Document doc) throws IOException;

	public abstract void createIndex(List<Document> list) throws IOException;

	/**
	 * 删除含有term的文档索引
	 * @param term
	 * @throws IOException
	 */
	public abstract void deleteIndex(Term term) throws IOException;

	/**
	 * 删除所有索引
	 * @throws IOException
	 */
	public abstract void deleteAllIndex() throws IOException;

	/**
	 * 通过删除含有term的文档，添加新的document文档来更新索引
	 * @param term
	 * @param document
	 * @throws IOException
	 */
	public abstract void updateIndex(Term term, Document document)
			throws IOException;

	public abstract QueryResult search(Query query, Filter filter, Sort sort,
			int firstResult, int maxResults) throws IOException;

}
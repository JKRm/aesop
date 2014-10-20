package cn.com.shanda.aesop.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import cn.com.shanda.aesop.dao.IndexDao;
import cn.com.shanda.aesop.pojo.QueryResult;
import cn.com.shanda.aesop.server.InitParam;

public class IndexDaoImpl implements IndexDao {
		
	private static final String INDEX_PATH = InitParam.tomcat + "\\aesop\\luceneIndex";
	
	//设置分词器
	Analyzer analyzer = new IKAnalyzer();
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.resourceSelector.dao.impl.IndexDao#createIndex(org.apache.lucene.document.Document)
	 */
	@Override
	public void createIndex(Document doc) throws IOException {
		
		IndexWriter indexWriter = null;
		
		Directory directory = FSDirectory.open(new File(INDEX_PATH));
		
		try {
			
			indexWriter = new IndexWriter(directory, analyzer, MaxFieldLength.LIMITED);
			indexWriter.addDocument(doc);
			
		} catch (Exception e) {
			
			throw new RuntimeException(e);
			
		} finally {
			try {
				
				indexWriter.close();
				
			} catch (Exception e) {
				
//				e.printStackTrace();
				
			}
		}
		
		//test
//		DocumentUtil.printDocumentInfo(doc);
		
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.resourceSelector.dao.impl.IndexDao#createIndex(java.util.List)
	 */
	@Override
	public void createIndex(List<Document> list) throws IOException {
		
		for (int i = 0; i < list.size(); ++i) {
			
			createIndex(list.get(i));
			
		}
		
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.resourceSelector.dao.impl.IndexDao#deleteIndex(org.apache.lucene.index.Term)
	 */
	@Override
	public void deleteIndex(Term term) throws IOException {
		
		IndexWriter indexWriter = null;
		
		Directory directory = FSDirectory.open(new File(INDEX_PATH));
		
		try {
			
			indexWriter = new IndexWriter(directory, analyzer, MaxFieldLength.LIMITED);
			indexWriter.deleteDocuments(term);
			
		} catch (Exception e) {
			
			throw new RuntimeException(e);
			
		} finally {
			
			try {
				
				indexWriter.close();
				
			} catch (Exception e) {
				
//				e.printStackTrace();
			}
			
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.resourceSelector.dao.impl.IndexDao#deleteAllIndex()
	 */
	@Override
	public void deleteAllIndex() throws IOException {
		
		IndexWriter indexWriter = null;
		
		Directory directory = FSDirectory.open(new File(INDEX_PATH));
		
		try {
			
			indexWriter = new IndexWriter(directory, analyzer, MaxFieldLength.LIMITED);
			indexWriter.deleteAll();
			
		} catch (Exception e) {
			
			throw new RuntimeException(e);
			
		} finally {
			
			try {
				
				indexWriter.close();
				
			} catch (Exception e) {
				
//				e.printStackTrace();
			}
			
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.resourceSelector.dao.impl.IndexDao#updateIndex(org.apache.lucene.index.Term, org.apache.lucene.document.Document)
	 */
	@Override
	public void updateIndex(Term term, Document document) throws IOException {
		
		IndexWriter indexWriter = null;
		
		Directory directory = FSDirectory.open(new File(INDEX_PATH));
		
		try {
			
			indexWriter = new IndexWriter(directory, analyzer, MaxFieldLength.LIMITED);
			indexWriter.updateDocument(term, document);
			
		} catch (Exception e) {
			
			throw new RuntimeException(e);
			
		} finally {
			
			try {
				
				indexWriter.close();
				
			} catch (Exception e) {
				
//				e.printStackTrace();
			}
			
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.resourceSelector.dao.impl.IndexDao#search(org.apache.lucene.search.Query, org.apache.lucene.search.Filter, org.apache.lucene.search.Sort, int, int)
	 */
	@Override
	public QueryResult search(Query query, Filter filter, Sort sort, int firstResult, int maxResults) throws IOException {
		
		IndexSearcher indexSearcher = null;	

		Directory directory = FSDirectory.open(new File(INDEX_PATH));
		
		try {
			
			indexSearcher = new IndexSearcher(directory);
	//		// Filter filter = new RangeFilter("size",
	//		// NumberTools.longToString(200)
	//		// , NumberTools.longToString(1000), true, true);
	//		//
	//		// // ========== 排序
	//		 Sort sort = new Sort();
	//		 sort.setSort(new SortField("size", SortField.STRING)); // 默认为升序
	//		 // sort.setSort(new SortField("size", true));
	//		 // ==========
	//	
			TopDocs topDocs = indexSearcher.search(query, filter, 10000);
		
			int recordCount = topDocs.totalHits;
			
			List<Document> recordList = new ArrayList<Document>();
		
			//准备高亮器
			Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
			Scorer scorer = new QueryScorer(query);
			Highlighter highlighter = new Highlighter(formatter, scorer);
		
			//截取文本
			Fragmenter fragmenter = new SimpleFragmenter(50);
			highlighter.setTextFragmenter(fragmenter);
		
			//取出当前页的数据
			int end = Math.min(firstResult + maxResults, topDocs.totalHits);
			
			for (int i = firstResult; i < end; i++) {
				
				int docId = topDocs.scoreDocs[i].doc;
		
				//根据编号取出相应的文档
				Document doc = indexSearcher.doc(docId);
		
				// 返回高亮后的结果，如果当前属性值中没有出现关键字，会返回 null
				String highlightDescription = highlighter.getBestFragment(analyzer, "description", doc.get("description"));
				String highlightTitle = highlighter.getBestFragment(analyzer, "title", doc.get("title"));
				String highlightKeywords = highlighter.getBestFragment(analyzer, "keywords", doc.get("keywords"));
				
				if (highlightDescription == null) {
					
					String description = doc.get("description");
					//最多前50个字符
					int endIndex = Math.min(50, description.length());
					highlightDescription = description.substring(0, endIndex);
					
				}
				
				if (highlightTitle == null) {
					
					highlightTitle = doc.get("title");
					
				}
				
				if (highlightKeywords == null) {
					
					highlightKeywords = doc.get("keywords");
					
				}
				
				doc.getField("description").setValue(highlightDescription);
				doc.getField("title").setValue(highlightTitle);
				doc.getField("keywords").setValue(highlightKeywords);
		
				recordList.add(doc);
			}
		
			// 返回结果
			return new QueryResult(recordCount, recordList);
			
		} catch (Exception e) {
			
			throw new RuntimeException(e);
			
		} finally {
			
			indexSearcher.close();
		}
	}
}


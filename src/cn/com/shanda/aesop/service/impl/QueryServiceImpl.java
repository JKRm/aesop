package cn.com.shanda.aesop.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.DateTools.Resolution;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKQueryParser;

import cn.com.shanda.aesop.dao.IndexDao;
import cn.com.shanda.aesop.dao.impl.IndexDaoImpl;
import cn.com.shanda.aesop.pojo.QueryResult;
import cn.com.shanda.aesop.service.PinyinService;
import cn.com.shanda.aesop.service.QueryService;
import cn.com.shanda.aesop.util.AnalyzerUtil;


public class QueryServiceImpl implements QueryService {
	
	private static final int MAX_RESULT = 10000;

	Analyzer analyzer = new IKAnalyzer();
	
	IndexDao indexDao = new IndexDaoImpl();
	
	PinyinService pinyinService = new PinyinServiceImpl();
	
	public QueryResult search(String queryText, String wholeKey, String excludingText, String field, 
			String author, String publisher, String[] types, Date[] date) {
		
		BooleanQuery query = new BooleanQuery();
		QueryResult result = new QueryResult();
		
		if (null != wholeKey || null != author || null != publisher) {
			query.add(getMultipleQuery(wholeKey, field, author, publisher), Occur.MUST);
		}
		
		if (null != types) {
			query.add(getTypesQuery(types), Occur.MUST);
		}
		
		if (null != date && date.length == 2) {
			query.add(getDateRangeQuery(date[0], date[1]), Occur.MUST);
		}
		
		if (null == queryText || "".equals(queryText)) {
			try {
				result = indexDao.search(query, null, null, 0, MAX_RESULT);
				
				if (null != excludingText && !"".equals(excludingText)) {
					
					query.add(getExcludingTextQuery(excludingText, field), Occur.MUST);
					result.remove(indexDao.search(query, null, null, 0, MAX_RESULT));
				}
			} catch (IOException e) {
//				e.printStackTrace();
			}

		} else {
			
			
			if (null != excludingText && !"".equals(excludingText)) {
				
				BooleanQuery queryCopy = (BooleanQuery)query.clone();
				queryCopy.add(getExcludingTextQuery(excludingText, field), Occur.MUST);
				QueryResult r = null;
				try {
					r = indexDao.search(queryCopy, null, null, 0, MAX_RESULT);
				} catch (IOException e) {
//					e.printStackTrace();
				}
				
				result = search(queryText, field, query);
				result.remove(r);
				
			} else {
				
				result = search(queryText, field, query);
			}
		}
		return result;
		
	}

	private QueryResult search(String queryText, String field, BooleanQuery query) {
		
		QueryResult queryResult = new QueryResult();
		
		List<String> list = new ArrayList<String>();
		//拼音转换
		char check = queryText.charAt(queryText.length()-1);
		if(!String.valueOf(check).matches("[\u4e00-\u9fa5]")) {
			try {
				list = pinyinService.getChinese(queryText);
				
				if (String.valueOf(list.get(0).charAt(list.get(0).length()-1)).matches("[\u4e00-\u9fa5]")) {
					
					queryResult.setTips(list);
				}
				
			} catch (SQLException e) {
//				e.printStackTrace();
				
			} catch (BadHanyuPinyinOutputFormatCombination e) {
//				e.printStackTrace();
				
			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
				
			}
			
		} else {
			list.add(queryText);
			
		}

		for (int i = 0; i < list.size(); ++i) {
			
			Query textQuery;
			try {
				textQuery = getTextQuery(list.get(i), field);
				query.add(textQuery, Occur.MUST);
				
			} catch (ParseException e) {
//				e.printStackTrace();
				
			} catch (IOException e) {
//				e.printStackTrace();
				
			}

			try {
				queryResult.merge(indexDao.search(query, null, null, 0, MAX_RESULT));
			} catch (IOException e) {
//				e.printStackTrace();
			}
			
		}
		return queryResult;
	}
	
	
	/**
	 * 由检索词和检索字段生成Query
	 * @param text
	 * @param field
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	private Query getTextQuery(String text, String field) throws ParseException, IOException {
		
		//将检索条件分词
		List<String> queryTexts = AnalyzerUtil.analyze(text);
		
		BooleanQuery textQuery = new BooleanQuery();

		//判断要查询的范围
		if (field.equals("all")) {
			
			String[] fields = {"title", "keywords", "description"};
			
			Map<String, Float> boosts = new HashMap<String, Float>();
			
			boosts.put("title", 3f);
			boosts.put("keywords", 2f);
			
			for (int i = 0; i < queryTexts.size(); ++i) {
				
				Query query = IKQueryParser.parseMultiField(fields, queryTexts.get(i));
				textQuery.add(query, Occur.SHOULD);
				
			}
		} else {
			
			for (int i = 0; i < queryTexts.size(); ++i) {
				
				Query query = IKQueryParser.parse(field, queryTexts.get(i));
				textQuery.add(query, Occur.SHOULD);
				
			}
		}
		
		return textQuery;
	}
	
	
	
	/**
	 * 由多个资源类型生成query对象
	 * @param types
	 * @return
	 */
	private BooleanQuery getTypesQuery(String[] types) {
		
		BooleanQuery typeQuery = new BooleanQuery();
		
		for (int i = 0; i < types.length; ++i) {

			Term term = new Term("kind", types[i]);
			Query query = new TermQuery(term);
			typeQuery.add(query, Occur.SHOULD);
		}
		
		return typeQuery;
	}
	
	/**
	 * 通过发布时间段获得Query对象
	 * @param begin
	 * @param end
	 * @return
	 */
	private TermRangeQuery getDateRangeQuery(Date begin, Date end) {
		
		if (begin.after(end)) {
			
			Date temp = begin;
			begin = end;
			end = temp;
		}
		
		TermRangeQuery query = new TermRangeQuery("date", DateTools.dateToString(begin, Resolution.DAY),
				DateTools.dateToString(end, Resolution.DAY), true, true);
		
		return query;
	}
	/**
	 * 将多个检索条件生成Query
	 * @param wholeKey
	 * @param excludingText
	 * @param field
	 * @param author
	 * @param publisher
	 * @return
	 */
	
	private Query getExcludingTextQuery(String excludingText, String field) {
		
		if ("all".equals(field)) {
			BooleanQuery bquery = new BooleanQuery();
			
			Term titleTerm = new Term("title", excludingText);
			TermQuery titleQuery = new TermQuery(titleTerm);

			Term keywordTerm = new Term("keywords", excludingText);
			TermQuery keywordQuery = new TermQuery(keywordTerm);
			
			Term descripTerm = new Term("description", excludingText);
			TermQuery descripQuery = new TermQuery(descripTerm);

			bquery.add(titleQuery, Occur.SHOULD);
			bquery.add(keywordQuery, Occur.SHOULD);
			bquery.add(descripQuery, Occur.SHOULD);
			return bquery;
			
		} else {
			
			Term term = new Term(field, excludingText);
			TermQuery tquery = new TermQuery(term);
			
			return tquery;
		}
	}
	
	private BooleanQuery getMultipleQuery(String wholeKey, String field, String author, String publisher) {
		
		BooleanQuery query = new BooleanQuery();
		
		if (null != author && !"".equals(author)) {
			
			Term term = new Term("author", author);
			FuzzyQuery authorQuery = new FuzzyQuery(term);
			
			
			query.add(authorQuery, Occur.MUST);
		}
		
		if (null != publisher && !"".equals(publisher)) {
			
			Term term = new Term("publisher", publisher);
			FuzzyQuery publisherQuery = new FuzzyQuery(term);
			
			query.add(publisherQuery, Occur.MUST);
		}
		
		
		if (null != wholeKey && !"".equals(wholeKey)) {
			if ("all".equals(field)) {
				BooleanQuery bquery = new BooleanQuery();
				
				Term titleTerm = new Term("title", wholeKey);
				TermQuery titleQuery = new TermQuery(titleTerm);

				Term keywordTerm = new Term("keywords", wholeKey);
				TermQuery keywordQuery = new TermQuery(keywordTerm);
				
				Term descripTerm = new Term("description", wholeKey);
				TermQuery descripQuery = new TermQuery(descripTerm);

				bquery.add(titleQuery, Occur.SHOULD);
				bquery.add(keywordQuery, Occur.SHOULD);
				bquery.add(descripQuery, Occur.SHOULD);
				query.add(bquery,Occur.MUST);
			} else {
				
				Term term = new Term(field, wholeKey);
				TermQuery tquery = new TermQuery(term);
				query.add(tquery, Occur.MUST);
			}
		
		}
		
		return query;
	}
}

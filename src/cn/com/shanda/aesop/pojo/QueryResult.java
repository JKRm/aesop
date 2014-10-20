/**
 * 更新时间 ： 8.7 15:31
 */
package cn.com.shanda.aesop.pojo;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;

public class QueryResult implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 4244194804721719683L;

	private int resultCount;
	

	private List<String> tips = new ArrayList<String>();
	
	private List<ResourceItem> list = new ArrayList<ResourceItem>();
	
	public List<String> getTips() {
		return tips;
	}

	public void setTips(List<String> tips) {
		this.tips = tips;
	}

	public QueryResult(int resultCount, List<Document> list) throws ParseException {
		
		this.resultCount = resultCount;
		
		setList(list);
		
	}
	
	public QueryResult() {
		
		this.resultCount = 0;
	}
	
	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public List<ResourceItem> getList() {
		return list;
	}

	public void setList(List<Document> list) throws ParseException {
		
		for (int i = 0; i < list.size(); ++i) {
			
			ResourceItem item = new ResourceItem();
			
			item.setAuthor(list.get(i).get("author"));
			item.setId(list.get(i).get("id"));
			item.setTitle(list.get(i).get("title"));
			item.setKeywords(list.get(i).get("keywords"));
			item.setDescription(list.get(i).get("description"));
			item.setUrl(list.get(i).get("url"));
			item.setKind(list.get(i).get("kind"));
			item.setAuthor(list.get(i).get("author"));
			item.setPublisher(list.get(i).get("publisher"));
			item.setDate(DateTools.stringToDate((list.get(i).get("date"))));
			
			this.list.add(item);
		}
	}
	
	public void merge(QueryResult result) {
		
		if (null != result) {
			
			this.resultCount += result.resultCount;
			this.list.addAll(result.list);
			
			if (this.tips != null && result.tips != null) {
				
				for (int i = 0; i < result.tips.size(); ++i) {
					
					if (!this.tips.contains(result.tips.get(i))) {
						this.tips.add(result.tips.get(i));
					}
				}
			}
		}
		
	}
	
	public void remove(QueryResult result) {
		
		if (null != result) {
			
			List<String> list = new ArrayList<String>();
			
			for (int i = 0; i < result.list.size(); ++i) {
				list.add(result.list.get(i).getId());
			}
			
			for (int i = 0; i < this.list.size(); ++i) {
				ResourceItem item = this.list.get(i);
				
				if (list.contains(item.getId())) {
					
					this.list.remove(item);
					this.resultCount--;
					i--;
				}
			}
			
		}
		
	}
	
	public ResourceItem getResourceItem(String url) {
		for (ResourceItem child:this.list) {
			if (url.equals(child.getUrl())) {
				return child;
			}
		}
		return null;
	}
	
	public void clear() {
		
		this.list.clear();
		this.tips.clear();
		this.resultCount = 0;
	}
	
	public void print() {
		
		System.out.println("============================================得到结果数：" + this.resultCount);
		
		for (int i = 0; i < list.size(); ++i) {
			System.out.println("---------------------------------------------------------");
			System.out.println("title         " + list.get(i).getTitle());
			System.out.println("keywords      " + list.get(i).getKeywords());
			System.out.println("description   " + list.get(i).getDescription());
			System.out.println("author        " + list.get(i).getAuthor());
			System.out.println("publisher     " + list.get(i).getPublisher());
			System.out.println("url           " + list.get(i).getUrl());
			System.out.println("date          " + list.get(i).getDate());
			System.out.println("id            " + list.get(i).getId());
		}
	}
}


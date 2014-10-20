package cn.com.shanda.aesop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.com.shanda.aesop.pojo.QueryResult;
import cn.com.shanda.aesop.server.FinalResult;
import cn.com.shanda.aesop.server.IpList;
import cn.com.shanda.aesop.server.SearchServer;

import com.opensymphony.xwork2.ActionSupport;

public class AccurateSearchAction extends ActionSupport {
	
	private HttpServletRequest request;
	private HttpSession session;
	
	private String queryText;
	private String wholekey;
	private String excludingText;
	private String field;
	private String author;
	private String publisher;
	private String type;
	private String[] types;
	
	public String execute() throws Exception {
		request = ServletActionContext.getRequest();
		session = request.getSession();
		
		setAllValues();
		
		// 检索
		SearchServer searchServer = new SearchServer();
		
		 FinalResult.serverSize=0;
		 FinalResult.results.clear();

		searchServer.search(queryText, wholekey, excludingText, field, author, publisher, types, null, null);
		
		while(IpList.list.size()!=FinalResult.serverSize){
			
		}
		QueryResult result =  FinalResult.results;
		
		session.setAttribute("result", result);
		session.setAttribute("queryText", queryText);
		session.setAttribute("type", type);
		
		return this.SUCCESS;
	}

	public void setAllValues() {
		
		setQueryText();
		setWholeKey();
		setExcludingText();
		setAuthor();
		setPublisher();
		setTypes();
		
	}
	
	public void setQueryText() {
		setQueryText(queryText.trim());
	}
	
	public void setWholeKey() {
		if (wholekey.trim().equals("")) {
			wholekey = null;
		} else {
			setWholekey(wholekey.trim());
		}
	}
	
	public void setExcludingText() {
		if (excludingText.trim().equals("")) {
			excludingText = null;
		} else {
			setExcludingText(excludingText.trim());
		}
	}
	
	public void setAuthor() {
		if (author.trim().equals("")) {
			author = null;
		} else {
			setAuthor(author.trim());
		}
	}
	
	public void setPublisher() {
		if (publisher.trim().equals("")) {
			publisher = null;
		} else {
			setPublisher(publisher.trim());
		}
	}
	
	public void setTypes() {
		String allTypes = "";
		
		// 设定types(查询条件的参数)
		if (type == null || type.indexOf("all") > -1) {
			types = null;
		}else {
			if (type.indexOf("document") > -1) {
				allTypes += " doc docx ppt txt pdf";
			}
			if (type.indexOf("audio") > -1) {
				allTypes += " wav wma mp3";
			}
			if (type.indexOf("video") > -1) {
				allTypes += " mov rmvb rm avi wmv qt asf mpg mpeg dat flv swf asx";
			}
			if (type.indexOf("other") > -1) {
				allTypes += " zip rar 7z iso jar cab";
			}
			types = allTypes.split(" ");
			System.out.println(types==null?"null":types.length);
			if (types!=null) {
				for (int i=0; i<types.length; ++i) {
					System.out.println(types[i]);
				}
			}
			
		}
		// 设定type(放在session中 用于标记当前类型按钮)
		if (type == null || type.indexOf(",") > -1) {
			type = "all";
		}		
	}
	
	//setters and getters
	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public String getQueryText() {
		return queryText;
	}

	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}

	public String getWholekey() {
		return wholekey;
	}

	public void setWholekey(String wholekey) {
		this.wholekey = wholekey;
	}

	public String getExcludingText() {
		return excludingText;
	}

	public void setExcludingText(String excludingText) {
		this.excludingText = excludingText;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}

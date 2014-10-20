package cn.com.shanda.aesop.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.com.shanda.aesop.pojo.QueryResult;
import cn.com.shanda.aesop.server.FinalResult;
import cn.com.shanda.aesop.server.IpList;
import cn.com.shanda.aesop.server.SearchServer;
import cn.com.shanda.aesop.service.PinyinService;
import cn.com.shanda.aesop.service.impl.PinyinServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

public class SearchAction extends ActionSupport {
	private HttpServletRequest request;
	private HttpSession session;
	
	private String queryText;
	
	private PinyinService pinyinService = new PinyinServiceImpl();

	public String getQueryText() {
		return queryText;
	}

	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}

	
	public String execute() throws Exception {
		request = ServletActionContext.getRequest();
		session = request.getSession();
		
		ShowResultAction.setRecordsPerPage(5);
		
		// 获取文件格式选项并设置检索文件类型
		String type = request.getParameter("type");
		String[] types = setTypes(type);
		
		// 检索
		SearchServer searchServer = new SearchServer();
		
		 FinalResult.serverSize=0;
		 FinalResult.results.clear();

		searchServer.search(queryText.trim(), null, null, "all", null, null, types, null, null);
		
		while(IpList.list.size()!=FinalResult.serverSize){
			
		}
		QueryResult result =  FinalResult.results;
		
		session.setAttribute("result", result);
		session.setAttribute("queryText", queryText.trim());
		session.setAttribute("type", type);
		
		return this.SUCCESS;
	}
	
	public String[] setTypes(String type) {
		String[] types;
		if ("all".equals(type)) {
			types = null;
		} else if ("document".equals(type)) {
			types = new String[]{"doc", "docx", "ppt", "pdf", "txt"};
		} else if ("audio".equals(type)) {
			types = new String[]{"wav", "wma", "mp3"};
		} else if ("video".equals(type)) {
			types = new String[]{"mov", "rmvb", "rm", "avi", "wmv", "qt", "asf", "mpg", "mpeg", "dat", "flv", "swf", "asx"};
		} else if ("other".equals(type)) {
			types = new String[]{"zip", "rar", "7z", "iso", "jar", "cab"};
		} else {
			types = null;
		}
		return types;
	}
}

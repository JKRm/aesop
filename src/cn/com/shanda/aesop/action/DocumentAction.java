package cn.com.shanda.aesop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.com.shanda.aesop.pojo.QueryResult;
import cn.com.shanda.aesop.server.FinalResult;
import cn.com.shanda.aesop.server.IpList;
import cn.com.shanda.aesop.server.SearchServer;

import com.opensymphony.xwork2.ActionSupport;

public class DocumentAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4511558599440341117L;
	private String queryText;
	private HttpServletRequest request;
	private HttpSession session; 
	
	public DocumentAction(){
		request = ServletActionContext.getRequest();
		session = request.getSession();
	}
	
	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}
	
	public String execute(){
		
		SearchServer searchServer = new SearchServer();
		
		ShowResultAction.setRecordsPerPage(8);
		
		FinalResult.serverSize=0;
		FinalResult.results.clear();
		 
		String []types = {"doc", "docx", "ppt", "pdf", "txt"};

		searchServer.search(queryText, null, null, "all", null, null, types, null, null);
		
		while(IpList.list.size()!=FinalResult.serverSize){
			
		}
		
		QueryResult result =  FinalResult.results;
		
		request.setAttribute("destination", "document");
		session.setAttribute("result", result);
		session.setAttribute("type", "document");
		session.setAttribute("queryText", queryText.trim());
		return "success";
		
	}
}

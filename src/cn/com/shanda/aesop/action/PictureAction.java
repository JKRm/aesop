package cn.com.shanda.aesop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.com.shanda.aesop.pojo.QueryResult;
import cn.com.shanda.aesop.server.FinalResult;
import cn.com.shanda.aesop.server.IpList;
import cn.com.shanda.aesop.server.SearchServer;

import com.opensymphony.xwork2.ActionSupport;

public class PictureAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -653926696504890743L;
	private String queryText;
	private HttpServletRequest request;
	private HttpSession session; 
	public PictureAction(){
		request = ServletActionContext.getRequest();
		session = request.getSession();
	}
	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}
	public String execute(){
		
		SearchServer searchServer = new SearchServer();
		
		ShowResultAction.setRecordsPerPage(10);
		
		FinalResult.serverSize=0;
		FinalResult.results.clear();
		 
		String []types={"jpg","gif","png","bmp","jpeg","tif","tife"}; 

		searchServer.search(queryText, null, null, "all", null, null, types, null, null);
		
		while(IpList.list.size()!=FinalResult.serverSize){
			
		}
		
		QueryResult result =  FinalResult.results;
		
		request.setAttribute("destination", "picture");
		session.setAttribute("result", result);
		session.setAttribute("type", "picture");
		session.setAttribute("queryText", queryText.trim());
		return "success";
	}
}

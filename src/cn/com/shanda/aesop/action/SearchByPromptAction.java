package cn.com.shanda.aesop.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.com.shanda.aesop.pojo.QueryResult;
import cn.com.shanda.aesop.server.FinalResult;
import cn.com.shanda.aesop.server.IpList;
import cn.com.shanda.aesop.server.SearchServer;

import com.opensymphony.xwork2.ActionSupport;

public class SearchByPromptAction extends ActionSupport{
	private HttpServletRequest request;
	private HttpSession session;
	
	private String destination;
	
	private String queryText;
	
	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public String execute() throws Exception {
		request = ServletActionContext.getRequest();
		session = request.getSession();
		
		// ȡ��ʾ��Ϣ����������ʾ��Ϣ���в���
//		queryText = request.getParameter("queryText");  	// ��ȡ����������ʾ��Ϣ�����
		if (null != destination && !"".equals(destination)) {
			request.setAttribute("destination", destination);
		}

		// ִ�в���
		SearchServer searchServer = new SearchServer();
		
		 FinalResult.serverSize=0;
		 FinalResult.results.clear();

		searchServer.search(queryText.trim(), null, null, "all", null, null, null, null, null);
		
		while(IpList.list.size()!=FinalResult.serverSize){
			
		}
		QueryResult result =  FinalResult.results;
		
		session.setAttribute("result", result);
		session.setAttribute("queryText", queryText.trim());
		
		return this.SUCCESS;
	}

}

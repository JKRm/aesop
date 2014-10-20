package cn.com.shanda.aesop.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.com.shanda.aesop.dao.impl.PreviewXMLParserDaoImpl;
import cn.com.shanda.aesop.pojo.PreviewItem;
import cn.com.shanda.aesop.server.PreviewSearchServer;
import cn.com.shanda.aesop.server.WenKuTemporaryMap;

import com.opensymphony.xwork2.ActionSupport;

public class WenkuAction extends ActionSupport{
	
		private PreviewXMLParserDaoImpl pxpdi = new PreviewXMLParserDaoImpl();
		
		private List<PreviewItem> list = new ArrayList<PreviewItem>();
		
		private PreviewSearchServer pss = new PreviewSearchServer();
		
		private HttpServletRequest request;
		
		private String queryText;
		
		private HttpSession session;
		
		public void setQueryText(String queryText) {
			this.queryText = queryText;
		}
		
		public WenkuAction(){
			
			request = ServletActionContext.getRequest();
			session = request.getSession();
			
		}
	
	public String execute(){
		
		WenKuTemporaryMap.map.clear();
		
		pss.previewSearch();
			
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
//			e.printStackTrace();
		}
			
		request.setAttribute("map", WenKuTemporaryMap.map);
		session.setAttribute("type", "document");
		session.setAttribute("queryText", queryText.trim());
		
		return SUCCESS;
	}

}

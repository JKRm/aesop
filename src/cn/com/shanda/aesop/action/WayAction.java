package cn.com.shanda.aesop.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class WayAction extends ActionSupport{
	
	private String from;
	
	private String to;
	
	private String city;
	
	private HttpServletRequest request;
	
	public WayAction(){
		
		request = ServletActionContext.getRequest();
		
	}

	
	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}


	public String getTo() {
		return to;
	}


	public void setTo(String to) {
		this.to = to;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return this.city;
	}

	public String execute(){
		
		request.setAttribute("from", getFrom());
		
		request.setAttribute("to", getTo());

		request.setAttribute("city", getCity());
		
		return SUCCESS;
	}
	
}

package cn.com.shanda.aesop.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class MapAction extends ActionSupport{
	
	private String city;
	
	private HttpServletRequest request;
	
	public MapAction(){
		
		request = ServletActionContext.getRequest();
		
	}

	public void setCity(String city) {
		
		this.city = city;
	}

	public String getCity() {
		
		return city;
	}
	
	public String execute(){
		
		request.setAttribute("city", getCity());
		
		return SUCCESS;
	}
	
}

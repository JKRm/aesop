package cn.com.shanda.aesop.action;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.com.shanda.aesop.dao.impl.SuggestDaoImpl;
import cn.com.shanda.aesop.pojo.SuggestionIndex;

import com.opensymphony.xwork2.ActionSupport;

public class SuggestAction extends ActionSupport {
	
	private String param;
	
	private InputStream inputStream;

	public void setParam(String param) {
		this.param = param;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	@Override
	public String execute() throws Exception {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setContentType("text/xml;charset=UTF-8");

		response.setHeader("Cache-Control", "no-cache");

		List<String> alist = new ArrayList<String>();
		
		try {

			alist = (new SuggestDaoImpl()).getSuggestion(param);
		} catch (Exception e) {

		}

		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		
		xml += "<message>";

		Iterator iter = alist.iterator();

		String text = null;

		while (iter.hasNext()) {

			text = (String)iter.next();
			
			xml += "<info>" + URLEncoder.encode(text, "UTF-8") + "</info>";
		}
		
		xml+="</message>";

		inputStream = new StringBufferInputStream(xml);
		
        return SUCCESS;

	}

}

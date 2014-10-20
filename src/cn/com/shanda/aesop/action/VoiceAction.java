package cn.com.shanda.aesop.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class VoiceAction extends ActionSupport {
	
	private InputStream inputStream;
	
	public InputStream getInputStream() {
		return this.inputStream;
	}
	
	public String execute() throws Exception {
		
		String path = System.getenv("CATALINA_HOME") + "\\webapps\\aesop\\voice";
		File file = new File(path);
		String result = "";
		Runtime s = Runtime.getRuntime();
		Process p = null;
		Process q = null;
		String line = "";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setContentType("text;charset=UTF-8");

		response.setHeader("Cache-Control", "no-cache");
		
		
    	try {
			p = s.exec("cl Prompt.cpp", null, file);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));   
			while ( (line=bufferedReader.readLine())!= null){
			}
			p.waitFor();
			p = s.exec("link /dll Prompt.obj", null, file);
			BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(p.getInputStream()));   
			while ( (line=bufferedReader1.readLine())!= null){
			}
			p.waitFor();
			p = s.exec(" java Prompt", null, file);
			BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(p.getInputStream()));   
			while ( (line=bufferedReader2.readLine())!= null){
				result += line;
			}
    	} catch (Exception e) {
//			e.printStackTrace();
		}
		
		
		inputStream = new StringBufferInputStream(URLEncoder.encode(result, "UTF-8"));
		
		return SUCCESS;

	}

	
}

package cn.com.shanda.aesop.server;

import java.io.File;
import java.io.IOException;

import cn.com.shanda.aesop.service.IndexService;
import cn.com.shanda.aesop.service.impl.IndexServiceImpl;

public class Start {

	@SuppressWarnings("unused")
	public Start() {
		
		GetPath realPath = new GetPath();
		
		InitParam.tomcat = realPath.getPath("webapps");       
		InitParam.filepath = InitParam.tomcat+"\\"+"test.xml";
		InitParam.xmlpath1 = InitParam.tomcat +"\\aesop\\DynamicIP.xml";
		InitParam.xmlpath2 = InitParam.tomcat +"\\aesop\\IPAddress.xml";
        
		String str = "rmic -classpath \""+InitParam.tomcat+"\\aesop\\WEB-INF\\classes\""+" cn.com.shanda.aesop.server.RegisteredImpl"+
		" -d \""+InitParam.tomcat+"\\aesop\\WEB-INF\\classes\"";
		
		String str1 = "rmic -classpath \""+InitParam.tomcat+"\\aesop\\WEB-INF\\classes\""+" cn.com.shanda.aesop.server.ListenerImpl"+
		" -d \""+InitParam.tomcat+"\\aesop\\WEB-INF\\classes\"";
		
		String str2 = "\"C:\\Program Files\\OpenOffice.org 3\\program\\soffice\"";
		str2 = str2 + " -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\" -nofirststartwizard";
		
		File file = new File(System.getenv("CATALINA_HOME") + "\\webapps\\resources\\img");
		file.mkdir();
		
		try {
			Runtime.getRuntime().exec(str);
			
			Runtime.getRuntime().exec(str1);
			
			Runtime.getRuntime().exec(str2);
			
			System.out.println("存根类编译成功   openoffice服务启动成功");
			
		} catch (IOException e) {
			
			System.out.println("openoffice服务启动失败");
			
		}
		
		
		
		StartServer server = new StartServer();
		
		IndexService createindex = new IndexServiceImpl();
		
		createindex.createIndex();
		
	}

}

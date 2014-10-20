package cn.com.shanda.aesop.server;

public class GetPath {
	
	public String getPath(String webapps) {

		String path = System.getenv("CATALINA_HOME");
		
		path = path + "\\" + webapps;
		
		return path;
	}
}

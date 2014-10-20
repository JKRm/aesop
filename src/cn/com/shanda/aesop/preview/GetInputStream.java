package cn.com.shanda.aesop.preview;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * 本类中使用方法 InputStream getInputStream(String path)
 * 作用是得到 "path" 路径对应文件的输入流
 */

public class GetInputStream {
	
	public static InputStream getInputStream(String urlPath) {
		
		urlPath = URLSiteChange.change(urlPath);   // 理论上先对路径中的中文字符进行转码
		HttpURLConnection urlConn = null;
		InputStream in = null;
		URL url;
		try {
			url = new URL(urlPath);
			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.connect();
			in = urlConn.getInputStream();
		} catch (Exception e) {
			return null;
		}
		return in;
	}

}

package cn.com.shanda.aesop.preview;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * ������ʹ�÷��� InputStream getInputStream(String path)
 * �����ǵõ� "path" ·����Ӧ�ļ���������
 */

public class GetInputStream {
	
	public static InputStream getInputStream(String urlPath) {
		
		urlPath = URLSiteChange.change(urlPath);   // �������ȶ�·���е������ַ�����ת��
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

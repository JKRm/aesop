package cn.com.shanda.aesop.preview;

import java.net.URLEncoder;

/*
 * 若URL地址含有字符，则先进行转换，换成UTF-8格式
 */

public final class URLSiteChange {

	public static String change(String path) { // 将含有中文的地址转化为UTF-8格式并返回
		
		String UTF = "";
		try {
			for (int i = 0; i < path.length(); i++) {
				char ch = ' ';
				ch = path.charAt(i);
				if (!String.valueOf(ch).matches("[\u4e00-\u9fa5]")) { // 若不含中文字符，则不改变
					UTF = UTF + ch;
					continue;
				}
				String newCh = URLEncoder.encode(String.valueOf(ch), "UTF-8");
				UTF = UTF + newCh;
			}
			path = UTF;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return path;
	}
	
}

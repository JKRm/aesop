package cn.com.shanda.aesop.preview;

/*
 * 本类作用：
 * 得到URL地址字符串的最后4个字符。
 * 用处: 根据不同文件的后缀名执行不同的操作
 */

public class URLFileType {
	
	public static String type(String file) {
		
		String f = null;
		f = file.substring(file.lastIndexOf("."), file.length()); // 得到文件扩展名
		return f;
	}
	
	
	
}

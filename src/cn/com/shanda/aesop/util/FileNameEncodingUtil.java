package cn.com.shanda.aesop.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class FileNameEncodingUtil {

	public static String encode(String fileName) {
		String result = "";
		if (fileName.contains(" ")) {
			String[] splitNames = fileName.split(" ");
			
			try {
				int i = 0;
				
				for (i = 0; i < splitNames.length - 1; ++i) {
					result += URLEncoder.encode(splitNames[i], "UTF-8") + " ";
				}
				result += URLEncoder.encode(splitNames[i], "UTF-8");
			
			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
			}
			
		} else {
			try {
				result = URLEncoder.encode(fileName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}
		return result;
	}
}

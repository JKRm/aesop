package cn.com.shanda.aesop.preview;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/*
 * 本类的作用是：
 * 1. StringFilter(String str)方法
 * 		清除掉 str 字符串中的非字母、非数字的字符，返回只有字母和数字的字符串
 * 		用处：在预览PPT生成图片时，用来作为不同PPT文件对应的文件夹名称。
 * 2. path()
 * 		得到当前类所在的目录，用处：预览PPT生成图片时，作为保存图片的文件夹的目录
 *  */

public class GetFileName {

	public static String StringFilter(String str) throws PatternSyntaxException {
		
		// 只保留字符串中的字母和数字
		String regEx = "[^a-zA-Z0-9]";
		Pattern p = Pattern.compile(regEx);        
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}	
	
	public String path() {         // 得到本类路径
		
		String s = this.getClass().getResource("/").getPath();
		s = s.substring(0, s.length()-16);
		s = s.replaceAll("%20", " ");
		return s;
	}
	
}

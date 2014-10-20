package cn.com.shanda.aesop.preview;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/*
 * ����������ǣ�
 * 1. StringFilter(String str)����
 * 		����� str �ַ����еķ���ĸ�������ֵ��ַ�������ֻ����ĸ�����ֵ��ַ���
 * 		�ô�����Ԥ��PPT����ͼƬʱ��������Ϊ��ͬPPT�ļ���Ӧ���ļ������ơ�
 * 2. path()
 * 		�õ���ǰ�����ڵ�Ŀ¼���ô���Ԥ��PPT����ͼƬʱ����Ϊ����ͼƬ���ļ��е�Ŀ¼
 *  */

public class GetFileName {

	public static String StringFilter(String str) throws PatternSyntaxException {
		
		// ֻ�����ַ����е���ĸ������
		String regEx = "[^a-zA-Z0-9]";
		Pattern p = Pattern.compile(regEx);        
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}	
	
	public String path() {         // �õ�����·��
		
		String s = this.getClass().getResource("/").getPath();
		s = s.substring(0, s.length()-16);
		s = s.replaceAll("%20", " ");
		return s;
	}
	
}

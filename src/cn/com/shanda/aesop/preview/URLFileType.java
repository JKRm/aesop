package cn.com.shanda.aesop.preview;

/*
 * �������ã�
 * �õ�URL��ַ�ַ��������4���ַ���
 * �ô�: ���ݲ�ͬ�ļ��ĺ�׺��ִ�в�ͬ�Ĳ���
 */

public class URLFileType {
	
	public static String type(String file) {
		
		String f = null;
		f = file.substring(file.lastIndexOf("."), file.length()); // �õ��ļ���չ��
		return f;
	}
	
	
	
}

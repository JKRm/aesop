package cn.com.shanda.aesop.preview;

import java.net.URLEncoder;

/*
 * ��URL��ַ�����ַ������Ƚ���ת��������UTF-8��ʽ
 */

public final class URLSiteChange {

	public static String change(String path) { // ���������ĵĵ�ַת��ΪUTF-8��ʽ������
		
		String UTF = "";
		try {
			for (int i = 0; i < path.length(); i++) {
				char ch = ' ';
				ch = path.charAt(i);
				if (!String.valueOf(ch).matches("[\u4e00-\u9fa5]")) { // �����������ַ����򲻸ı�
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

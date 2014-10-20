package cn.com.shanda.aesop.preview;

import java.io.*;
import org1.textmining.text.extraction.WordExtractor;

/*
 * ��������ã��õ� URL��Ӧ��word �ļ����ı����ֵ�ǰ1000��
 */

public class PreviewWord {
	
	public String getTextFromUrlWord(String url) throws Exception {  // ��ȡURL��Ӧ��word�ļ����ı�ǰ1000��
		
		String text = null;
		InputStream in = GetInputStream.getInputStream(url);
		if(in == null) return "Sorry,�ļ������ڣ�";
		WordExtractor extractor = new WordExtractor();		
		text = extractor.extractText(in);   // ��ȡword�ĵ��е�����
		if(text.length() > 1000) text = text.substring(0, 1000);  // ȡǰ1000����
		return text;
	}
	
}

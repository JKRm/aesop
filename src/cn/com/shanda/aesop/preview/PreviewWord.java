package cn.com.shanda.aesop.preview;

import java.io.*;
import org1.textmining.text.extraction.WordExtractor;

/*
 * 本类的作用：得到 URL对应的word 文件的文本部分的前1000字
 */

public class PreviewWord {
	
	public String getTextFromUrlWord(String url) throws Exception {  // 读取URL对应的word文件的文本前1000字
		
		String text = null;
		InputStream in = GetInputStream.getInputStream(url);
		if(in == null) return "Sorry,文件不存在！";
		WordExtractor extractor = new WordExtractor();		
		text = extractor.extractText(in);   // 获取word文档中的内容
		if(text.length() > 1000) text = text.substring(0, 1000);  // 取前1000个字
		return text;
	}
	
}

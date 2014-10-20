package cn.com.shanda.aesop.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKTokenizer;

public class AnalyzerUtil {
	
	public static List<String> analyze(String text) throws IOException {
		
		Analyzer analyzer = new IKAnalyzer();
		
		ArrayList<String> splitText = new ArrayList<String>();
		
		IKTokenizer tokenStream = (IKTokenizer) analyzer.tokenStream("content",
				new StringReader(text));

		TermAttribute termAtt = tokenStream.getAttribute(TermAttribute.class);
		
		while (tokenStream.incrementToken()) {
			
			splitText.add(termAtt.term());
			
		}
		
		return splitText;
	}
}

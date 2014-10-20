package cn.com.shanda.aesop.dao;

import java.io.IOException;
import java.util.Set;

import org.dom4j.DocumentException;

public interface VoiceGrammarXMLParserDao {

	public abstract void add(String text) throws DocumentException, IOException;

	public abstract void add(Set<String> addList) throws DocumentException,
			IOException;

	public abstract void clear() throws DocumentException, IOException;

}
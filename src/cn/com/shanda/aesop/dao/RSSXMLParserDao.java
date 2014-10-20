package cn.com.shanda.aesop.dao;

import java.io.IOException;
import java.util.List;

import org.jdom.JDOMException;

import cn.com.shanda.aesop.pojo.ResourceItem;

import com.sun.syndication.io.FeedException;

public interface RSSXMLParserDao {

	public abstract void add(ResourceItem item) throws IOException,
			IllegalArgumentException, FeedException;

	public abstract void add(List<ResourceItem> list)
			throws IllegalArgumentException, IOException, FeedException;
	
	public abstract void clear() throws IllegalArgumentException, IOException,
			FeedException;

}
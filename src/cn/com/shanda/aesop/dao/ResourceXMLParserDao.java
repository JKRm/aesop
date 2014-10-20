package cn.com.shanda.aesop.dao;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;

import cn.com.shanda.aesop.pojo.ResourceItem;

public interface ResourceXMLParserDao {

	public abstract ArrayList<ResourceItem> getResources()
			throws DocumentException, ParseException;

	public abstract ResourceItem getResource(String url)
			throws DocumentException, ParseException;
	
	public void add(ResourceItem item) 
			throws DocumentException, IOException;
	
	public void add(List<ResourceItem> list) 
			throws DocumentException, IOException;
	
	public void delete(String url) 
			throws DocumentException, IOException;

}
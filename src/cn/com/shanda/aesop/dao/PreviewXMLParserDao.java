package cn.com.shanda.aesop.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;

import cn.com.shanda.aesop.pojo.PreviewItem;

public interface PreviewXMLParserDao {

	public abstract void add(String resourceName, String previewUrl, String kind)
			throws DocumentException, IOException;

	public abstract void delete(String resourceName) throws DocumentException,
			IOException;

	public abstract String getPreivewUrl(String resourceName)
			throws DocumentException;

	public abstract Map<String, List<PreviewItem>> getPreviewItems();

}
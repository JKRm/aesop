package cn.com.shanda.aesop.service;

import java.io.IOException;
import java.util.List;

import org.dom4j.DocumentException;

import cn.com.shanda.aesop.pojo.ResourceItem;

public interface VoiceService {

	public abstract void add(ResourceItem item) throws DocumentException,
			IOException;

	public abstract void add(List<ResourceItem> list) throws DocumentException,
			IOException;

	public abstract void clear() throws DocumentException, IOException;

}
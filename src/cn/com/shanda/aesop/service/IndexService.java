package cn.com.shanda.aesop.service;

import cn.com.shanda.aesop.pojo.ResourceItem;

public interface IndexService {

	public abstract void createIndex();

	public abstract void createIndex(ResourceItem item);

	public abstract void deleteALLIndex();

	public abstract void deleteIndex(String deleteId);

	public abstract void updateIndex(ResourceItem addItem, String deleteId);

}
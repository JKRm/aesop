package cn.com.shanda.aesop.dao;

import java.util.ArrayList;

import cn.com.shanda.aesop.pojo.Advertisement;

public interface AdvertisementDao {
	
	/**
	 * 获取所有广告记录
	 * @return
	 */
	public abstract ArrayList<Advertisement> getAdList();
}

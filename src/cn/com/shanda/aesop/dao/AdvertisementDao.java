package cn.com.shanda.aesop.dao;

import java.util.ArrayList;

import cn.com.shanda.aesop.pojo.Advertisement;

public interface AdvertisementDao {
	
	/**
	 * ��ȡ���й���¼
	 * @return
	 */
	public abstract ArrayList<Advertisement> getAdList();
}

package cn.com.shanda.aesop.dao;

import java.sql.SQLException;

public interface AdminDao {

	public abstract boolean validate(String username, String password)
			throws SQLException;
	public void updateInfo(String username, String password) 
			throws SQLException;
}
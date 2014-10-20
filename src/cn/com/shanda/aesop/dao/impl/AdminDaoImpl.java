package cn.com.shanda.aesop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.com.shanda.aesop.dao.AdminDao;
import cn.com.shanda.aesop.util.DB;

public class AdminDaoImpl implements AdminDao {

	/* (non-Javadoc)
	 * @see cn.com.shanda.aesop.dao.impl.AdminDao#validate(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean validate(String username, String password) throws SQLException {
		
		String sql = "select * from admin where username=? and password=?";
		Connection conn = DB.createConn();
		PreparedStatement ps = DB.createStmt(conn, sql);
		
		ps.setString(1, username);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public void updateInfo(String username, String password) throws SQLException {
		
		String sql = "update admin set password=? where username=?";
		Connection conn = DB.createConn();
		PreparedStatement ps = DB.createStmt(conn, sql);
		
		ps.setString(1, password);
		ps.setString(2, username);
		
		ps.executeUpdate();
	}
}

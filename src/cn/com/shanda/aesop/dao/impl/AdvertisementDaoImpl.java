package cn.com.shanda.aesop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.com.shanda.aesop.dao.AdvertisementDao;
import cn.com.shanda.aesop.pojo.Advertisement;
import cn.com.shanda.aesop.util.DB;

public class AdvertisementDaoImpl implements AdvertisementDao {

	private DB db;
	/*
	 * (non-Javadoc)
	 * @see cn.com.shanda.aesop.dao.AdvertisementDao#getAdList()
	 */
	@Override
	public ArrayList<Advertisement> getAdList() {
		// TODO Auto-generated method stub
		String sql = "select * from advertisement";
		Connection conn;
		PreparedStatement ps;
		ArrayList<Advertisement> list = new ArrayList<Advertisement>();
		
		try {
			conn = db.createConn();
			ps = db.createStmt(conn, sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String adword = rs.getString("adword");
				String adlink = rs.getString("adlink");
				Advertisement ad = new Advertisement();
				
				ad.setAdword(adword);
				ad.setAdlink(adlink);
				
				list.add(ad);
			}
			db.close(rs);
			db.close(ps);
			db.close(conn);
		} catch (SQLException se) {
//			se.printStackTrace();
		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			
		}
		
		return list;
	}
}

package cn.com.shanda.aesop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import cn.com.shanda.aesop.dao.PinyinDao;
import cn.com.shanda.aesop.util.DB;
import cn.com.shanda.aesop.util.PinyinTransformer;

public class PinyinDaoImpl implements PinyinDao {
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.resourceSelector.dao.impl.PinyinDao#add(java.util.List)
	 */
	@Override
	public boolean add(Set<String> set) throws SQLException, BadHanyuPinyinOutputFormatCombination {
		
		
		
		String sql = "insert into pinyintransform(chinese, pinyin) values(?, ?)";
		
		Connection conn = DB.createConn();
		PreparedStatement ps = DB.createStmt(conn, sql);
		
		conn.setAutoCommit(false);
		
		for (Iterator<String> it = set.iterator(); it.hasNext(); ) {
			
			String chinese = it.next();
			String pinyin = PinyinTransformer.getPinYin(chinese);
			
			ps.setString(1, chinese);
			ps.setString(2, pinyin);
			ps.addBatch();
		}
		
		int[] result = ps.executeBatch();
		
		conn.setAutoCommit(true);
		
		DB.close(ps);
		DB.close(conn);
		
		for (int i = 0 ; i < result.length; ++i) {
			if (0 == result[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.resourceSelector.dao.impl.PinyinDao#add(java.lang.String)
	 */
	@Override
	public boolean add(String chinese) throws SQLException, BadHanyuPinyinOutputFormatCombination {
		
		String sql = "insert into pinyintransform(chinese, pinyin) values(?, ?)";
		
		Connection conn = DB.createConn();
		PreparedStatement ps = DB.createStmt(conn, sql);
		
		String pinyin = PinyinTransformer.getPinYin(chinese);
		ps.setString(1, chinese);
		ps.setString(2, pinyin);
		
		int result = ps.executeUpdate();
		
		DB.close(ps);
		DB.close(conn);
		
		return (1 == result);
		
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.resourceSelector.dao.impl.PinyinDao#delete(java.lang.String)
	 */
	@Override
	public boolean delete(String chinese) throws SQLException, BadHanyuPinyinOutputFormatCombination {
		
		String sql = "delete * from pinyintransform where chinese = ?";
		
		Connection conn = DB.createConn();
		PreparedStatement ps = DB.createStmt(conn, sql);
		
		ps.setString(1, chinese);
		
		int result = ps.executeUpdate();
		
		DB.close(ps);
		DB.close(conn);
		
		return (1 == result);
		
	}
	@Override
	public boolean deleteAll() throws SQLException {
		
		String querySql = "select count(pinyin) as total from pinyintransform";
		String updateSql = "delete from pinyintransform";

		int queryResult = 0;
		int updateResult = 0;
		
		Connection conn = DB.createConn();
		PreparedStatement ps = null;
		
		ps = DB.createStmt(conn, querySql);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			queryResult = rs.getInt("total");
		}
		
		ps = DB.createStmt(conn, updateSql);
		updateResult = ps.executeUpdate();
		
		DB.close(rs);
		DB.close(ps);
		DB.close(conn);
		
		return (queryResult == updateResult);
		
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.resourceSelector.dao.impl.PinyinDao#getChinese(java.lang.String)
	 */
	@Override
	public List<String> getChinese(String pinyin) throws SQLException, BadHanyuPinyinOutputFormatCombination {
		
		List<String> list = new ArrayList<String>();
		String sql = "select distinct chinese from pinyintransform where pinyin = ?";
		
		Connection conn = DB.createConn();
		PreparedStatement ps = DB.createStmt(conn, sql);
		
		ps.setString(1, pinyin);
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			list.add(rs.getString("chinese"));
		}
		
		DB.close(rs);
		DB.close(ps);
		DB.close(conn);
		
		//如果数据库中没有相应记录,则返回pinyin字符串
		if (0 == list.size()) {
			list.add(pinyin);
		}
		
		return list;
	}
}

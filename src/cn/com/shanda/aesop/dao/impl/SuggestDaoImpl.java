package cn.com.shanda.aesop.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import cn.com.shanda.aesop.dao.SuggestDao;
import cn.com.shanda.aesop.pojo.ResourceItem;
import cn.com.shanda.aesop.pojo.SuggestionIndex;
import cn.com.shanda.aesop.util.AnalyzerUtil;
import cn.com.shanda.aesop.util.DB;

public class SuggestDaoImpl implements SuggestDao {

	/* (non-Javadoc)
	 * @see cn.com.shanda.aesop.dao.impl.SuggestDao#createSuggestText(java.util.List)
	 */
	@Override
	public void createSuggestText(List<ResourceItem> list) throws IOException, SQLException {
		
		SortedSet<String> sortedSet = new TreeSet<String>();
		
		for (int i = 0; i < list.size(); ++i) {
			
			ResourceItem item = list.get(i);
			
			List<String> suggest = AnalyzerUtil.analyze(item.getTitle());
			suggest.addAll(AnalyzerUtil.analyze(item.getKeywords()));
			suggest.addAll(AnalyzerUtil.analyze(item.getDescription()));
			
			sortedSet.addAll(suggest);
		}
		
		String sql = "insert into suggest values(?)";
		
		Connection conn = DB.createConn();
		PreparedStatement ps = DB.createStmt(conn, sql);
		
		conn.setAutoCommit(false);
		
		for (Iterator<String> it = sortedSet.iterator(); it.hasNext(); ) {
			ps.setString(1, it.next());
			ps.addBatch();
		}
		
		ps.executeBatch();
		conn.setAutoCommit(true);
		
		DB.close(ps);
		DB.close(conn);
		
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.aesop.dao.impl.SuggestDao#addSuggestText(cn.com.shanda.aesop.pojo.ResourceItem)
	 */
	@Override
	public void addSuggestText(ResourceItem item) throws IOException, SQLException {
		
		String sql = "insert into suggest values(?)";
		
		Connection conn = DB.createConn();
		PreparedStatement ps = DB.createStmt(conn, sql);
		
		List<String> suggest = AnalyzerUtil.analyze(item.getTitle());
		suggest.addAll(AnalyzerUtil.analyze(item.getKeywords()));
		suggest.addAll(AnalyzerUtil.analyze(item.getDescription()));
		
		
		for (String text:suggest) {
			addSuggestText(text);
		}
		
		
		DB.close(ps);
		DB.close(conn);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.aesop.dao.impl.SuggestDao#addSuggestText(java.lang.String)
	 */
	@Override
	public void addSuggestText(String text) throws SQLException {
		
		String sql = "insert into suggest values(?)";
		
		Connection conn = DB.createConn();
		PreparedStatement ps = DB.createStmt(conn, sql);
		try {
			
			ps.setString(1, text);
			ps.execute(sql);
		} catch (SQLException e) {
			//do nothing
		} finally {
			
			DB.close(ps);
			DB.close(conn);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.aesop.dao.impl.SuggestDao#deleteAll()
	 */
	@Override
	public void deleteAll() throws SQLException {
		
		String sql = "delete from suggest";
		
		Connection conn = DB.createConn();
		PreparedStatement ps = DB.createStmt(conn, sql);
		
		ps.executeUpdate();
		
		DB.close(ps);
		DB.close(conn);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.aesop.dao.impl.SuggestDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String text) throws SQLException {

		String sql = "delete * from suggest where text=?";
		
		Connection conn = DB.createConn();
		PreparedStatement ps = DB.createStmt(conn, sql);
		
		ps.setString(1, text);
		ps.executeUpdate();
		
		DB.close(ps);
		DB.close(conn);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.aesop.dao.impl.SuggestDao#delete(java.util.List)
	 */
	@Override
	public void delete(List<String> list) throws SQLException {
		
		for (String text:list) {
			
			delete(text);
		}
	}
	
	public List<String> getSuggestion(String input) throws SQLException {
		
		List<String> suggestion = new ArrayList<String>();
		
		String sql = "select top 10 * from suggest where text like ?";
		
		Connection conn = DB.createConn();
		PreparedStatement ps = DB.createStmt(conn, sql);
		
		ps.setString(1, "%" + input + "%");
		ResultSet rs =  ps.executeQuery();
		
		while (rs.next()) {
			
			suggestion.add(rs.getString("text"));
		}
		
		DB.close(rs);
		DB.close(ps);
		DB.close(conn);
		
		return suggestion;
	}
}

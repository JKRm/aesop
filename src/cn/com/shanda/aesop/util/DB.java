package cn.com.shanda.aesop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	
	private static final String JDBC_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	
	private static String ip = "localhost";
	
	private static String DATABASE_PATH1 = "jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb)};" +
	"DBQ=" + System.getenv("CATALINA_HOME") + "\\webapps\\aesop\\database\\aesop.mdb";

	private static String DATABASE_PATH2 = "jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb, *.accdb)};" +
			"DBQ=" + System.getenv("CATALINA_HOME") + "\\webapps\\aesop\\database\\aesop.mdb";
	
	private static final String USER = "";
	
	private static final String PASSWORD = "";
	
	public static void setIp(String path) {
		ip = path;
	}

	public static Connection createConn() throws SQLException {
		
//		Class.forName(JDBC_DRIVER);
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(DATABASE_PATH1,USER,PASSWORD);
		} catch (SQLException e) {
			conn = DriverManager.getConnection(DATABASE_PATH2, USER, PASSWORD);
		}
		
		return conn;
	}
	
	public static PreparedStatement createStmt(Connection conn, String sql) throws SQLException{
		
		PreparedStatement ps = conn.prepareStatement(sql);
		return ps;
		
	}
	
	public static void close(Statement stmt) throws SQLException{
		
		if (stmt != null)
		{
			stmt.close();
		}
		stmt = null;
	}
	
	public static void close(ResultSet rs) throws SQLException{
		
		if (rs != null)
		{
			rs.close();
		}
		rs = null;
	}
	
	public static void close(Connection conn) throws SQLException{
		
		if (conn != null)
		{
			conn.close();
		}
		conn = null;
	}
}


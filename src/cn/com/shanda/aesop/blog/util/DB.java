package cn.com.shanda.aesop.blog.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	
	private static String DATABASE_PATH1 = "jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb)};" +
	"DBQ=" + System.getenv("CATALINA_HOME") + "\\webapps\\aesop\\database\\aesop.mdb";

	private static String DATABASE_PATH2 = "jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb, *.accdb)};" +
			"DBQ=" + System.getenv("CATALINA_HOME") + "\\webapps\\aesop\\database\\aesop.mdb";
	
	private static final String USER = "";
	
	private static final String PASSWORD = "";

	private Connection conn = null;
    
	private Statement stm=null;
    
    /* 通过构造方法加载数据库驱动 */
    public DB(){
    	
    }
    /* 创建数据库连接 */
    public void createCon() throws SQLException {
    	
    	conn = null;	
		
    	try {
			conn = DriverManager.getConnection(DATABASE_PATH1,USER,PASSWORD);
		} catch (SQLException e) {
			conn = DriverManager.getConnection(DATABASE_PATH2, USER, PASSWORD);
		}
    }
    /* 获取Statement对象 */
    public void getStm(){
   		try {
			createCon();
		} catch (SQLException e1) {
//			e1.printStackTrace();
		}
    	try {
			stm=conn.createStatement();
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("创建Statement对象失败！");
		}
    }
    /** 
     * @功能 对数据库的增加、修改和删除的操作
     * @参数 sql为要执行的SQL语句
     * @返回值 boolean型值 
     */
    public boolean executeUpdate(String sql) {
    	System.out.println(sql);
        boolean mark=false;
    	try {
    		getStm();
            int iCount = stm.executeUpdate(sql);
            if(iCount>0)            	
            	mark=true;            
            else
            	mark=false;
        } catch (Exception e) {
//            e.printStackTrace();
		    mark=false;
        }
        return mark;
    }
    /* 查询数据库 */
    public ResultSet executeQuery(String sql) {
        ResultSet rs=null;
        try {
            getStm();
            try {
                rs = stm.executeQuery(sql);
            } catch (Exception e) {
//            	e.printStackTrace();
                System.out.println("查询数据库失败！");
            }
        } catch (Exception e) {
//            e.printStackTrace();          
        }
        return rs;
    }
    /* 关闭数据库的操作 */
    public void closed() {
    	if(stm!=null)
			try {
				stm.close();
			} catch (SQLException e) {
//				e.printStackTrace();
				System.out.println("关闭stm对象失败！");
			}
    	if(conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
//				e.printStackTrace();
				System.out.println("关闭con对象失败！");
			}
    }
}

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
    
    /* ͨ�����췽���������ݿ����� */
    public DB(){
    	
    }
    /* �������ݿ����� */
    public void createCon() throws SQLException {
    	
    	conn = null;	
		
    	try {
			conn = DriverManager.getConnection(DATABASE_PATH1,USER,PASSWORD);
		} catch (SQLException e) {
			conn = DriverManager.getConnection(DATABASE_PATH2, USER, PASSWORD);
		}
    }
    /* ��ȡStatement���� */
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
			System.out.println("����Statement����ʧ�ܣ�");
		}
    }
    /** 
     * @���� �����ݿ�����ӡ��޸ĺ�ɾ���Ĳ���
     * @���� sqlΪҪִ�е�SQL���
     * @����ֵ boolean��ֵ 
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
    /* ��ѯ���ݿ� */
    public ResultSet executeQuery(String sql) {
        ResultSet rs=null;
        try {
            getStm();
            try {
                rs = stm.executeQuery(sql);
            } catch (Exception e) {
//            	e.printStackTrace();
                System.out.println("��ѯ���ݿ�ʧ�ܣ�");
            }
        } catch (Exception e) {
//            e.printStackTrace();          
        }
        return rs;
    }
    /* �ر����ݿ�Ĳ��� */
    public void closed() {
    	if(stm!=null)
			try {
				stm.close();
			} catch (SQLException e) {
//				e.printStackTrace();
				System.out.println("�ر�stm����ʧ�ܣ�");
			}
    	if(conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
//				e.printStackTrace();
				System.out.println("�ر�con����ʧ�ܣ�");
			}
    }
}

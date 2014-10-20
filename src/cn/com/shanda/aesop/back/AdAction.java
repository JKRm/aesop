package cn.com.shanda.aesop.back;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.com.shanda.aesop.util.DB;

import com.opensymphony.xwork2.ActionSupport;

//��̨��ӹ��ִ�е�Action��

public class AdAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private String adword = null;
	private String adlink = null;
	private String sql = "";
	private boolean mark = false;
	
	public AdAction(){
		
		request = ServletActionContext.getRequest();
		
	}
	
	//��ù����������ӵ�get��set����
	
	public String getAdword() {
		
		return adword;
		
	}
	public void setAdword(String adword) {
		
		this.adword = adword;
		
	}
	public String getAdlink() {
		
		return adlink;
		
	}
	public void setAdlink(String adlink) {
		
		this.adlink = adlink;
		
	}
	
	public String execute(){
		
		//���ʼ�������Ӿ���Ϊ��ʱִ�����ݿ�д�����
		
		if(!(adword.equals("") || adlink.equals(""))){
			
		sql="INSERT INTO advertisement(adword,adlink) VALUES (?,?)";
		
		Connection conn;
		
		try {
			
			conn = DB.createConn();
			
			PreparedStatement ps = DB.createStmt(conn, sql);
			
			ps.setString(1, getAdword());
			
			ps.setString(2, getAdlink());
			
			mark = ps.execute();
			
		} catch (SQLException e) {
			
//			e.printStackTrace();
		}
		
		if(!mark){
			
			request.setAttribute("message", "��ϲ�����λ�ύ�ɹ���");
			
		}
		else{
			
			request.setAttribute("message", "�Բ��𣬹���ύ���������ԡ���");
			
		}
		}
		else
			
			request.setAttribute("message", "�밴��ʽ��д���ʼ�������ӣ�");
		
		return SUCCESS;
	}
	
}

package cn.com.shanda.aesop.back;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.com.shanda.aesop.util.DB;

import com.opensymphony.xwork2.ActionSupport;

//后台添加广告执行的Action类

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
	
	//获得广告词与广告链接的get和set方法
	
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
		
		//广告词及广告链接均不为空时执行数据库写入操作
		
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
			
			request.setAttribute("message", "恭喜您广告位提交成功！");
			
		}
		else{
			
			request.setAttribute("message", "对不起，广告提交出错，请重试……");
			
		}
		}
		else
			
			request.setAttribute("message", "请按格式填写广告词及广告链接！");
		
		return SUCCESS;
	}
	
}

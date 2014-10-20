package cn.com.shanda.aesop.back;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import cn.com.shanda.aesop.util.DB;

import com.opensymphony.xwork2.ActionSupport;

public class DelAdAction extends ActionSupport{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private HttpServletRequest request;
	
	private String[] checkbox;
	
	public DelAdAction(){
		
		request = ServletActionContext.getRequest();
		
	}
	
	//获取多选框选择的资源
	
	public void setCheckbox(String[] checkbox) {
		
		this.checkbox = checkbox;
	}

	public String[] getCheckbox() {
		
		return checkbox;
	}
	
	
	
	public String execute(){
		
		for(int i=0;i<checkbox.length;i++){
			
			String adwords = checkbox[i];
			
			String sql = "delete * from advertisement where adword=?";
			
			Connection conn;
			
			PreparedStatement ps;
			
			try {
				conn = DB.createConn();
				
				ps = DB.createStmt(conn, sql);
				
				ps.setString(1, adwords);
				
				ps.executeUpdate();
				
				DB.close(ps);
				
				DB.close(conn);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}	
			
			
		}			
					
		request.setAttribute("message", "广告删除成功！");
		
		return SUCCESS;
		
		}

}

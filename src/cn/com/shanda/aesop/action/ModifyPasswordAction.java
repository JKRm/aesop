package cn.com.shanda.aesop.action;

import java.sql.SQLException;

import cn.com.shanda.aesop.dao.AdminDao;
import cn.com.shanda.aesop.dao.impl.AdminDaoImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ModifyPasswordAction extends ActionSupport{
	
	private String currentPassword;
	
	private String motifiedPassword;
	
	private String sql = null;
	
	private AdminDao adminDao = new AdminDaoImpl();

	public void setCurrentPassword(String currentPassword) {
		
		this.currentPassword = currentPassword;
		
	}

	public String getCurrentPassword() {
		
		return currentPassword;
		
	}

	public void setMotifiedPassword(String motifiedPassword) {
		
		this.motifiedPassword = motifiedPassword;
		
	}

	public String getMotifiedPassword() {
		
		return motifiedPassword;
		
	}
	
	public String execute(){
		
		String username = (String)ActionContext.getContext().getSession().get("username");
		
		try {
			
			if(adminDao.validate(username, currentPassword)){
				
				adminDao.updateInfo(username, motifiedPassword);
				
				addActionMessage("修改成功");
				
			}
			
		} catch (SQLException e) {
			
			addActionMessage("修改失败");
			
//			e.printStackTrace();
		}
			
		return SUCCESS;
	}
	
	
	
	

}

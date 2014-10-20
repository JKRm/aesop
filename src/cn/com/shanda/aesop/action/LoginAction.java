package cn.com.shanda.aesop.action;

import java.sql.SQLException;

import cn.com.shanda.aesop.dao.AdminDao;
import cn.com.shanda.aesop.dao.impl.AdminDaoImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	private String username;
	
	private String password;
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String execute() {
		
		String user = (String)ActionContext.getContext().getSession().get("username");
		
		if (null != user && !"".equals(user)) {
			
			return SUCCESS;
		}
		
		if (null != username && !"".equals(username)) {
			
			AdminDao adminDao = new AdminDaoImpl();
			
			try {
				
				if (adminDao.validate(username, password)) {
					
					ActionContext.getContext().getSession().put("username", username);
					
					return SUCCESS;
					
				} else {
					
					addActionError("用户名或密码有误！");
					
				}
				
			} catch (SQLException e) {
				
//				e.printStackTrace();
				
			}
		}
		return LOGIN;
	}
	
	
}

package cn.com.shanda.aesop.blog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.com.shanda.aesop.blog.bean.MasterBean;
import cn.com.shanda.aesop.blog.dao.LogonDao;

@SuppressWarnings("serial")
public class LogXAction extends ActionSupport {

	private String temp;
	private HttpServletRequest request;
//	private HttpServletResponse response;
	private HttpSession session;
	private String action;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public LogXAction() {
		request = ServletActionContext.getRequest();
//		response = ServletActionContext.getResponse();
		session = request.getSession();
	}

	@Override
	public String execute()  {
//		String action = request.getParameter("action");
		if (action == null)
			action = "";
		if (action.equals("islogon")) {
			temp = this.isLogon();
		}
		if (action.equals("logon")) {
			temp = this.logon();
		}
		if (action.equals("logout")) {
			temp = this.logout();
		}
		return temp;
	}

	public String isLogon() {
		String forward = "";
		if (session.getAttribute("logoner") != null)
			forward = "isLogonSuccess"; // admin/AdminIndex.jsp;
		else
			forward = "isLogonError"; // admin/logon.jsp;

		return forward;
	}

	public boolean validateLogon() {
		boolean mark = true;
		String messages = "";
		String name = request.getParameter("userName");
		String password = request.getParameter("userPass");
		if (name == null || name.equals("")) {
			mark = false;
			messages += "<li>请输入用户名！";
		}
		if (password == null || password.equals("")) {
			mark = false;
			if (messages!=null && !messages.equals("")) {
				messages += "请输入密码！</li>";
			} else {
				messages += "<li>请输入密码！</li>";
			}
		}
		request.setAttribute("messages", messages);
		return mark;
	}

	public String logon() {
		String forward;
		boolean flag = validateLogon();
		if (flag) {
			LogonDao masterDao = new LogonDao();
			MasterBean logoner = new MasterBean();
			logoner.setMasterName(request.getParameter("userName"));
			logoner.setMasterPass(request.getParameter("userPass"));
			boolean mark = masterDao.logon(logoner);
			if (!mark) {
				request.setAttribute("messages", "<li>输入的用户名或密码错误！</li>");
				forward = "logon"; // admin/logon.jsp

			} else {
				session.setAttribute("logoner", logoner);
				forward = "logonError"; // admin/AdminIndex.jsp
			}
		} else
			forward = "logon"; // admin/logon.jsp
		return forward;
	}

	public String logout() {
		session.removeAttribute("logoner");
		return "logout";	//front/FrontIndex.jsp
	}
}

package cn.com.shanda.aesop.blog.action;

import java.util.List;

//import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.com.shanda.aesop.blog.bean.FriendBean;
import cn.com.shanda.aesop.blog.dao.FriendDao;
import cn.com.shanda.aesop.blog.util.MyTools;

@SuppressWarnings("serial")
public class FriendAction extends ActionSupport {

	private String temp;
	private HttpServletRequest request;
	private String action;
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public FriendAction() {
		request = ServletActionContext.getRequest();
	}


	@Override
	public String execute()  {
//		String action = request.getParameter("action");
		if (action == null)
			action = "";
		if (action.equals("add")) { // 添加友情链接信息
			temp = this.addFriend();
		}
		if (action.equals("modify")) { // 修改友情链接信息
			temp = this.modifyFriend();
		}
		if (action.equals("delete")) { // 删除友情链接信息
			temp = this.deleteFriend();
		}
		if (action.equals("single")) { // 查询某个友情链接详细信息
			temp = this.singleFriend();
		}
		if (action.equals("list")) {
			temp = this.listFriend(); // 查询所有友情链接信息
		}
		if (action.equals("adminSingle")) { // 查询某个友情链接详细信息
			temp = this.adminSingleFriend();
		}
		if (action.equals("adminList")) {
			temp = this.adminListFriend(); // 查询所有友情链接信息
		}
		return temp;
	}

	/**
	 * @功能 后台-修改好友信息
	 */
	public String modifyFriend() {
		FriendDao friendDao = new FriendDao();
		String type = request.getParameter("type");
		String forward = "";
		if (type == null)
			type = "";
		if (!type.equals("doModify")) {
			int id = MyTools.strToint(request.getParameter("id"));
			FriendBean friendBean = friendDao.queryFriendSingle(id);
			request.setAttribute("modifySingle", friendBean);
			forward = "modifyFriend";				//admin/friend/FriendModify.jsp
		} else {
			String messages = "";
			String href = "";
			boolean flag = validateFriend();
			if (flag) {
				FriendBean friendBean = new FriendBean();
				friendBean.setId(MyTools.strToint(request.getParameter("id")));
				friendBean.setName(MyTools.toChinese(request
						.getParameter("name")));
				friendBean.setBlog(request.getParameter("blog"));

				boolean mark = friendDao.operationFriend("modify", friendBean);
				if (mark) {
					messages = "<p class=\"success\">修改成功！</p>";
					href = "<a href='/aesop/blog/FriendAction?action=adminList'>[继续修改其他友情链接]</a>";
					forward = "modifyFriendSuccess"; 				//admin/success.jsp;
				} else {
					messages = "<p class=\"error\">修改失败！</p>";
					href = "<a href='javascript:window.history.go(-1)>[返回]</a>";
					forward = "modifyFriendError";					//admin/error.jsp;
				}
				request.setAttribute("messages", messages);
				request.setAttribute("href", href);
			} else {
				href = "<a href='javascript:window.history.go(-1)>[返回]</a>";
				forward = "modifyFriendError";					//admin/error.jsp;
				request.setAttribute("href", href);
			}
		}
		return forward;
	}

	/**
	 * @功能 前台-查询某个好友信息
	 */
	public String singleFriend() {
		String strId = request.getParameter("id");
		int id = MyTools.strToint(strId);
		FriendDao friendDao = new FriendDao();
		FriendBean friendSingle = friendDao.queryFriendSingle(id);
		request.setAttribute("friendSingle", friendSingle);

		return "singleFriend";								//front/friend/FriendSingle.jsp
	}

	/**
	 * @功能 后台-查询某个好友信息
	 */
	public String adminSingleFriend() {
		String strId = request.getParameter("id");
		int id = MyTools.strToint(strId);
		FriendDao friendDao = new FriendDao();
		FriendBean friendSingle = friendDao.queryFriendSingle(id);
		request.setAttribute("friendSingle", friendSingle);

		return "adminSingleFriend";					//admin/friend/FriendSingle.jsp
	}

	/**
	 * @功能 后台-删除好友信息
	 */
	public String deleteFriend() {
		FriendDao friendDao = new FriendDao();
		FriendBean friendBean = new FriendBean();

		String messages = "";
		String href = "";
		String forward = "";

		friendBean.setId(MyTools.strToint(request.getParameter("id")));
		boolean mark = friendDao.operationFriend("delete", friendBean);
		if (mark) {
			messages += "<p class=\"success\">删除好友成功！</p>";
			href = "<a href='/aesop/blog/FriendAction?action=adminList'>[继续删除其他好友]</a>";
			forward = "deleteFriendSuccess";						//admin/success.jsp;

		} else {
			messages += "<p class=\"error\">删除好友失败！</p>";
			href = "<a href='javascript:window.history.go(-1)'>[返回]</a>";
			forward = "deleteFriendError";							//admin/error.jsp;
		}
		request.setAttribute("messages", messages);
		request.setAttribute("href", href);
		return forward;
	}

	/**
	 * @功能 验证表单数据
	 */
	public boolean validateFriend(){
		boolean mark = true;
		String messages = "";

		String name = request.getParameter("name");
		String blog = request.getParameter("blog");

		if (name == null || name.equals("")) {
			mark = false;
			messages += "<p class=\"error\">请输入 <b>好友姓名！</b></p>";
		}
		
		if (blog == null || blog.equals("")) {
			mark = false;
			messages += "<p class=\"error\">请输入 <b>BLOG地址！</b></p>";
		}
		request.setAttribute("messages", messages);
		return mark;
	}

	/**
	 * @功能 后台-添加友情链接信息
	 */
	public String addFriend() {
		String messages = "";
		String href = "";
		String forward = "";

		boolean flag = validateFriend();
		if (flag) {
			FriendDao friendDao = new FriendDao();
			FriendBean friendBean = new FriendBean();
			friendBean.setName(MyTools.toChinese(request.getParameter("name")));
			friendBean.setBlog(request.getParameter("blog"));

			boolean mark = friendDao.operationFriend("add", friendBean);
			friendDao.closeConnection();
			if (mark) {
				messages = "<p class=\"success\">添加友情链接成功！</p>";
				href = "<a href='/aesop/admin/friend/FriendAdd.jsp'>[继续添加]</a>";
				forward = "addFriendSuccess";						//admin/success.jsp;
			} else {
				messages = "<p class=\"error\">添加友情链接失败！</p>";
				href = "<a href='javascript:window.history.go(-1)'>[返回]</a>";
				forward = "addFriendError";							//admin/error.jsp;
			}
			request.setAttribute("messages", messages);
		} else {
			href = "<a href='javascript:window.history.go(-1)'>[返回]</a>";
			forward = "addFriendError";							//admin/error.jsp;
		}
		request.setAttribute("href", href);
		return forward;
	}

	/**
	 * @功能 前台-查询所有好友
	 */
	@SuppressWarnings("rawtypes")
	public String listFriend() {
		FriendDao friendDao = new FriendDao();
		List friendList = friendDao.queryFriend("all");
		request.setAttribute("friendList", friendList);

		return "listFriend";						//front/friend/FriendList.jsp
	}

	/**
	 * @功能 后台-查询所有好友
	 */
	@SuppressWarnings("rawtypes")
	public String adminListFriend() {
		FriendDao friendDao = new FriendDao();
		List friendList = friendDao.queryFriend("all");
		request.setAttribute("friendList", friendList);

		return "adminListFriend";					//admin/friend/FriendList.jsp
	}
}

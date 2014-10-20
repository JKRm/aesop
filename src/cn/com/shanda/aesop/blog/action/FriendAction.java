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
		if (action.equals("add")) { // �������������Ϣ
			temp = this.addFriend();
		}
		if (action.equals("modify")) { // �޸�����������Ϣ
			temp = this.modifyFriend();
		}
		if (action.equals("delete")) { // ɾ������������Ϣ
			temp = this.deleteFriend();
		}
		if (action.equals("single")) { // ��ѯĳ������������ϸ��Ϣ
			temp = this.singleFriend();
		}
		if (action.equals("list")) {
			temp = this.listFriend(); // ��ѯ��������������Ϣ
		}
		if (action.equals("adminSingle")) { // ��ѯĳ������������ϸ��Ϣ
			temp = this.adminSingleFriend();
		}
		if (action.equals("adminList")) {
			temp = this.adminListFriend(); // ��ѯ��������������Ϣ
		}
		return temp;
	}

	/**
	 * @���� ��̨-�޸ĺ�����Ϣ
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
					messages = "<p class=\"success\">�޸ĳɹ���</p>";
					href = "<a href='/aesop/blog/FriendAction?action=adminList'>[�����޸�������������]</a>";
					forward = "modifyFriendSuccess"; 				//admin/success.jsp;
				} else {
					messages = "<p class=\"error\">�޸�ʧ�ܣ�</p>";
					href = "<a href='javascript:window.history.go(-1)>[����]</a>";
					forward = "modifyFriendError";					//admin/error.jsp;
				}
				request.setAttribute("messages", messages);
				request.setAttribute("href", href);
			} else {
				href = "<a href='javascript:window.history.go(-1)>[����]</a>";
				forward = "modifyFriendError";					//admin/error.jsp;
				request.setAttribute("href", href);
			}
		}
		return forward;
	}

	/**
	 * @���� ǰ̨-��ѯĳ��������Ϣ
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
	 * @���� ��̨-��ѯĳ��������Ϣ
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
	 * @���� ��̨-ɾ��������Ϣ
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
			messages += "<p class=\"success\">ɾ�����ѳɹ���</p>";
			href = "<a href='/aesop/blog/FriendAction?action=adminList'>[����ɾ����������]</a>";
			forward = "deleteFriendSuccess";						//admin/success.jsp;

		} else {
			messages += "<p class=\"error\">ɾ������ʧ�ܣ�</p>";
			href = "<a href='javascript:window.history.go(-1)'>[����]</a>";
			forward = "deleteFriendError";							//admin/error.jsp;
		}
		request.setAttribute("messages", messages);
		request.setAttribute("href", href);
		return forward;
	}

	/**
	 * @���� ��֤������
	 */
	public boolean validateFriend(){
		boolean mark = true;
		String messages = "";

		String name = request.getParameter("name");
		String blog = request.getParameter("blog");

		if (name == null || name.equals("")) {
			mark = false;
			messages += "<p class=\"error\">������ <b>����������</b></p>";
		}
		
		if (blog == null || blog.equals("")) {
			mark = false;
			messages += "<p class=\"error\">������ <b>BLOG��ַ��</b></p>";
		}
		request.setAttribute("messages", messages);
		return mark;
	}

	/**
	 * @���� ��̨-�������������Ϣ
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
				messages = "<p class=\"success\">����������ӳɹ���</p>";
				href = "<a href='/aesop/admin/friend/FriendAdd.jsp'>[�������]</a>";
				forward = "addFriendSuccess";						//admin/success.jsp;
			} else {
				messages = "<p class=\"error\">�����������ʧ�ܣ�</p>";
				href = "<a href='javascript:window.history.go(-1)'>[����]</a>";
				forward = "addFriendError";							//admin/error.jsp;
			}
			request.setAttribute("messages", messages);
		} else {
			href = "<a href='javascript:window.history.go(-1)'>[����]</a>";
			forward = "addFriendError";							//admin/error.jsp;
		}
		request.setAttribute("href", href);
		return forward;
	}

	/**
	 * @���� ǰ̨-��ѯ���к���
	 */
	@SuppressWarnings("rawtypes")
	public String listFriend() {
		FriendDao friendDao = new FriendDao();
		List friendList = friendDao.queryFriend("all");
		request.setAttribute("friendList", friendList);

		return "listFriend";						//front/friend/FriendList.jsp
	}

	/**
	 * @���� ��̨-��ѯ���к���
	 */
	@SuppressWarnings("rawtypes")
	public String adminListFriend() {
		FriendDao friendDao = new FriendDao();
		List friendList = friendDao.queryFriend("all");
		request.setAttribute("friendList", friendList);

		return "adminListFriend";					//admin/friend/FriendList.jsp
	}
}

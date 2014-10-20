package cn.com.shanda.aesop.blog.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.com.shanda.aesop.blog.bean.ArticleBean;
import cn.com.shanda.aesop.blog.bean.ArticleTypeBean;
import cn.com.shanda.aesop.blog.bean.ReviewBean;
import cn.com.shanda.aesop.blog.dao.ArticleDao;
import cn.com.shanda.aesop.blog.dao.ArticleTypeDao;
import cn.com.shanda.aesop.blog.dao.ReviewDao;
import cn.com.shanda.aesop.blog.util.MyTools;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ArticleAction extends ActionSupport {

	private String temp;
	private HttpServletRequest request;
	private HttpSession session;
	private String action;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public ArticleAction() {
		request = ServletActionContext.getRequest();
		session = request.getSession();
	}

	@Override
	public String execute()  {
		if (action == null)
			action = "";
		if (action.equals("select"))
			temp = this.selectArticle(); // ��ȡĳ�������������
		if (action.equals("adminSelectList"))
			temp = this.adminSelectList();
		if (action.equals("adminSelectSingle"))
			temp = this.adminSelectSingle();
		if (action.equals("add"))
			temp = this.addArticle(); // ��������
		if (action.equals("delete"))
			temp = this.deleteArticle(); // ɾ������
		if (action.equals("modify"))
			temp = this.modifyArticle(); // �޸�����
		if (action.equals("read"))
			temp = this.readArticle(); // �Ķ�����
		if (action.equals("followAdd"))
			temp = this.validateFollow(); // �������»ظ�
		if (action.equals("typeAdd"))
			temp = this.addArticleType(); // �����������
		if (action.equals("typeSelect"))
			temp = this.selectArticleType(); // �鿴�������
		if (action.equals("typeModify"))
			temp = this.modifyArticleType(); // �޸��������
		if (action.equals("typeDelete"))
			temp = this.deleteArticleType(); // ɾ���������
		return temp;
	}

	/**
	 * @���� ��֤������Ϣ
	 */
	public String validateFollow() {
		String str = "";
		boolean mark = true;
		String messages = "";

		String content = request.getParameter("reContent");
		if (content == null || content.equals("")) {
			mark = false;
			messages = "<p class=\"error\">������ <b>�������ݣ�</b></p>";
		}
		if (mark) {
			content = MyTools.toChinese(content);
			if (content.length() > 1000) {
				mark = false;
				messages = "<p class=\"error\"><b>��������</b> �����������1000���ַ���</p>";
			}
		}
		if (!mark) {
			request.setAttribute("messages", messages);
			str = "validateFollow"; // front/article/error.jsp
		} else {
			str = followAdd();
		}

		return str;
	}

	/**
	 * @���� �����������
	 */
	public String followAdd() {
		int id = Integer.parseInt(request.getParameter("articleId"));
		String author = MyTools.toChinese(request.getParameter("reAuthor"));
		String content = MyTools.toChinese(request.getParameter("reContent"));
		String today = MyTools.changeTime(new Date());
		if (author == null || author.equals(""))
			author = "��������";

		ReviewBean reviewBean = new ReviewBean();
		reviewBean.setArticleId(id);
		reviewBean.setReAuthor(author);
		reviewBean.setReContent(content);
		reviewBean.setReSdTime(today);

		ReviewDao reviewDao = new ReviewDao();
		String messages = "";
		String forward = "";
		boolean mark = reviewDao.operationReview("add", reviewBean);
		if (mark) {
			forward = "followAddSuccess"; // front/article/success.jsp
			messages = "<p class=\"success\">�������۳ɹ���</p>";

		} else {
			forward = "followAddError"; // front/article/error.jsp
			messages = "<p class=\"error\">��������ʧ�ܣ�</p>";
		}
		request.setAttribute("messages", messages);

		return forward;

	}

	/**
	 * @���� �Ķ�����
	 * @ʵ�� 1.���������Ķ�����<br>
	 *     2.��ȡָ��������Ϣ<br>
	 *     3.��ȡ�Ը����µ���������
	 */
	@SuppressWarnings("rawtypes")
	public String readArticle() {
		ArticleBean articleBean = new ArticleBean();
		ArticleDao articleDao = new ArticleDao();
		ReviewDao reviewDao = new ReviewDao();

		String strId = request.getParameter("id");
		int id = MyTools.strToint(strId);
		articleBean.setId(id);

		articleDao.operationArticle("readTimes", articleBean); // �ۼ��Ķ�����
		articleBean = articleDao.queryArticleSingle(id); // ��ѯָ�����µ���ϸ����
		session.setAttribute("readSingle", articleBean); // ����articleBean��request������

		List reviewlist = reviewDao.queryReview(id);
		session.setAttribute("reviewlist", reviewlist);

		return "readArticle";							//front/article/ArticleSingle.jsp
	}

	/**
	 * @���� �޸�����
	 */
	public String modifyArticle() {
		String forward = "";
		ArticleDao articleDao = new ArticleDao();
		String type = request.getParameter("type");
		if (type == null)
			type = "";
		if (!type.equals("doModify")) {
			int id = MyTools.strToint(request.getParameter("id"));
			ArticleBean articleBean = articleDao.queryArticleSingle(id);
			request.setAttribute("modifySingle", articleBean);
			forward = "modifyArticle";				//admin/article/ArticleModify.jsp
		} else {
			String messages = "";
			String href = "";
			boolean flag = validateArticle();
			if (flag) {
				ArticleBean articleBean = new ArticleBean();
				articleBean.setId(MyTools.strToint(request.getParameter("id")));
				articleBean.setTypeId(Integer.valueOf(request
						.getParameter("typeId")));
				articleBean.setTitle(MyTools.toChinese(request
						.getParameter("title")));
				articleBean.setCreate(MyTools.toChinese(request
						.getParameter("create")));
				articleBean.setInfo(MyTools.toChinese(request
						.getParameter("info")));
				articleBean.setContent(MyTools.toChinese(request
						.getParameter("content")));

				boolean mark = articleDao.operationArticle("modify",
						articleBean);
				if (mark) {
					messages = "<p class=\"success\">�޸ĳɹ���</p>";
					href = "<a href='/aesop/blog/ArticleAction?action=adminSelectList&typeId="
							+ session.getAttribute("showTypeId")
							+ "'>[�����޸���������]</a>";
					forward = "modifyArticleSuccess";			//admin/success.jsp;
				} else {
					messages = "<p class=\"error\">�޸�ʧ�ܣ�</p>";
					href = "<a href='javascript:window.history.go(-1)'>[����]</a>";
					forward = "modifyArticleError";				//admin/error.jsp;
				}
				request.setAttribute("messages", messages);
				request.setAttribute("href", href);
			} else {
				href = "<a href='javascript:window.history.go(-1)>[����]</a>";
				forward = "modifyArticleError";				//admin/error.jsp";
				request.setAttribute("href", href);
			}
		}
		return forward;
	}

	/**
	 * @���� ��̨-ɾ������
	 */
	public String deleteArticle() {
		ArticleDao articleDao = new ArticleDao();
		ArticleBean articleBean = new ArticleBean();

		String messages = "";
		String href = "";
		String forward = "";
		articleBean.setId(MyTools.strToint(request.getParameter("id")));
		boolean mark = articleDao.operationArticle("delete", articleBean);
		if (mark) {
			String typeId = request.getParameter("typeId");
			messages += "<p class=\"success\">ɾ�����³ɹ���</p>";
			href = "<a href='/aesop/blog/ArticleAction?action=adminSelectList&typeId="
					+ typeId + "'>[����ɾ����������]</a>";
			forward = "deleteArticleSuccess";						//admin/success.jsp;

		} else {
			messages += "<p class=\"error\">ɾ������ʧ�ܣ�</p>";
			href = "<a href='javascript:window.history.go(-1)'>[����]</a>";
			forward = "deleteArticleError";							//admin/error.jsp;
		}
		request.setAttribute("messages", messages);
		request.setAttribute("href", href);
		
		return forward;
	}

	/**
	 * @���� ��֤����������Ϣ
	 */
	public boolean validateArticle(){
		boolean mark = true;
		String messages = "";

		String typeId = request.getParameter("typeId");
		String title = request.getParameter("title");
		String create = request.getParameter("create");
		String info = request.getParameter("info");
		String content = request.getParameter("content");

		if (typeId == null || typeId.equals("")) {
			mark = false;
			messages += "<p class=\"error\">��ѡ�� <b>�������</b></p>";
		}
		if (title == null || title.equals("")) {
			mark = false;
			messages += "<p class=\"error\">������ <b>���±��⣡</b></p>";
		}
		if (create == null || create.equals("")) {
			mark = false;
			messages += "<p class=\"error\">��ѡ�� <b>������Դ��</b></p>";
		}
		if (info == null || info.equals("")) {
			mark = false;
			messages += "<p class=\"error\">������ <b>����������</b></p>";
		}
		if (content == null || content.equals("")) {
			mark = false;
			messages += "<p class=\"error\">������ <b>�������ݣ�</b></p>";
		}
		if (mark) {
			title = MyTools.toChinese(title);
			content = MyTools.toChinese(content);
			if (title.length() > 20) {
				mark = false;
				messages += "<p class=\"error\"><b>���±���</b> �����������20���ַ���</p>";
			}
			if (content.length() > 4000) {
				mark = false;
				messages += "<p class=\"error\"><b>��������</b> �����������4000���ַ���</p>";
			}
		}
		request.setAttribute("messages", messages);
		return mark;
	}

	/**
	 * @���� ��̨-��������
	 */
	public String addArticle() {
		String messages = "";
		String href = "";
		String forward = "";

		boolean flag = validateArticle();
		if (flag) {
			ArticleBean articleBean = new ArticleBean();
			articleBean.setTypeId(MyTools.strToint(request
					.getParameter("typeId")));
			articleBean.setTitle(MyTools.toChinese(request
					.getParameter("title")));
			articleBean.setContent(MyTools.changeHTML(MyTools.toChinese(request
					.getParameter("content"))));
			articleBean.setSdTime(MyTools.changeTime(new Date()));
			articleBean.setCreate(MyTools.toChinese(request
					.getParameter("create")));
			articleBean
					.setInfo(MyTools.toChinese(request.getParameter("info")));
			articleBean.setCount(0);

			ArticleDao articleDao = new ArticleDao();
			boolean mark = articleDao.operationArticle("add", articleBean);
			if (mark) {
				messages = "<p class=\"success\">�������³ɹ���</p>";
				href = "<a href='/aesop/admin/article/ArticleAdd.jsp'>[��������]</a>";
				forward = "addArticleSuccess";				//admin/success.jsp;
			} else {
				messages = "<p class=\"error\">��������ʧ�ܣ�</p>";
				href = "<a href='javascript:window.history.go(-1)'>[����]</a>";
				forward = "addArticleError";				//admin/error.jsp;
			}
			request.setAttribute("messages", messages);
			request.setAttribute("href", href);
		} else {
			href = "<a href='javascript:window.history.go(-1)'>[����]</a>";
			forward = "addArticleError";				//admin/error.jsp;
			request.setAttribute("href", href);
		}
		return forward;
	}

	/**
	 * @���� ʵ�ֺ�̨���¹����е������������
	 */
	@SuppressWarnings("rawtypes")
	public String adminSelectList() {
		ArticleDao articleDao = new ArticleDao();
		String strId = request.getParameter("typeId");
		if (strId == null || strId.equals(""))
			strId = "-1";
		int typeId = Integer.parseInt(strId);
		session.setAttribute("showTypeId", typeId);
		List articleList = articleDao.queryArticle(typeId, "all");
		request.setAttribute("articleList", articleList);
		return "adminSelectList"; // admin/article/ArticleList.jsp
	}

	public String adminSelectSingle() {
		int id = MyTools.strToint(request.getParameter("id"));
		ArticleDao articleDao = new ArticleDao();

		ArticleBean articleBean = articleDao.queryArticleSingle(id); // ��ѯָ�����µ���ϸ����
		request.setAttribute("articleSingle", articleBean);

		return "adminSelectSingle";				//admin/article/ArticleSingle.jsp
	}

	/**
	 * @���� �����ݱ��л�ȡĳ����µ���������
	 */
	@SuppressWarnings("rawtypes")
	public String selectArticle() {
		ArticleDao articleDao = new ArticleDao();
		String strId = request.getParameter("typeId");
		if (strId == null || strId.equals(""))
			strId = "-1";
		int typeId = Integer.parseInt(strId);
		List articleList = articleDao.queryArticle(typeId, "all");

		request.setAttribute("articleList", articleList);

		return "selectArticle"; // front/article/ArticleList.jsp
	}

	public boolean validateType() {
		boolean mark = true;
		String messages = "";

		String typeName = request.getParameter("typeName");
		String typeInfo = request.getParameter("typeInfo");

		if (typeName == null || typeName.equals("")) {
			mark = false;
			messages += "<p class=\"error\">������ <b>������ƣ�</b></p>";
		}
		if (typeInfo == null || typeInfo.equals("")) {
			mark = false;
			messages += "<p class=\"error\">������ <b>�����ܣ�</b></p>";
		}
		request.setAttribute("messages", messages);
		return mark;
	}

	/**
	 * @���� ��̨-�����������
	 */
	public String addArticleType() {
		String messages = "";
		String href = "";
		String forward = "";

		boolean flag = validateType();
		if (flag) {
			ArticleTypeBean typeBean = new ArticleTypeBean();
			typeBean.setTypeName(MyTools.toChinese(request
					.getParameter("typeName")));
			typeBean.setTypeInfo(MyTools.toChinese(request
					.getParameter("typeInfo")));

			ArticleTypeDao articleTypeDao = new ArticleTypeDao();
			boolean mark = articleTypeDao.operationArticleType("add", typeBean);
			if (mark) {
				messages += "<p class=\"success\">����������ɹ���</p>";
				href = "<a href='/aesop/admin/article/ArticleTypeAdd.jsp'>[��������������]</a>";
				forward = "addArticleTypeSuccess";					//admin/success.jsp;

			} else {
				messages += "<p class=\"error\">����������ʧ�ܣ�</p>";
				href = "<a href='javascript:window.history.go(-1)'>[����]</a>";
				forward = "addArticleTypeError";					//admin/error.jsp;
			}
			request.setAttribute("messages", messages);
			request.setAttribute("href", href);
		} else {
			href = "<a href='javascript:window.history.go(-1)'>[����]</a>";
			forward = "addArticleTypeError";						//admin/error.jsp;
			request.setAttribute("href", href);
		}
		return forward;
	}

	@SuppressWarnings("rawtypes")
	public String selectArticleType(){
		ArticleTypeDao typeDao = new ArticleTypeDao();
		List typelist = typeDao.queryTypeList();
		request.setAttribute("typelist", typelist);
		return "selectArticleType";						//admin/article/ArticleTypeList.jsp
	}

	/**
	 * @���� ��̨-�޸��������
	 */
	public String modifyArticleType() {
		ArticleTypeBean typeBean = null;
		ArticleTypeDao typeDao = new ArticleTypeDao();
		String forward = "";

		String type = request.getParameter("type");
		if (type == null)
			type = "";
		if (!type.equals("doModify")) {
			int typeId = MyTools.strToint(request.getParameter("typeId"));
			typeBean = typeDao.queryTypeSingle(typeId);
			request.setAttribute("typeSingle", typeBean);
			forward = "modifyArticleType";		//admin/article/ArticleTypeModify.jsp
		} else {
			String messages = "";
			String href = "";
			boolean flag = validateType();
			if (flag) {
				typeBean = new ArticleTypeBean();
				typeBean.setId(MyTools.strToint(request.getParameter("typeId")));
				typeBean.setTypeName(MyTools.toChinese(request
						.getParameter("typeName")));
				typeBean.setTypeInfo(MyTools.toChinese(request
						.getParameter("typeInfo")));

				boolean mark = typeDao.operationArticleType("modify", typeBean);
				if (mark) {
					messages = "<p class=\"success\">�޸����ɹ���</p>";
					href = "<a href='/aesop/blog/ArticleAction?action=typeSelect'>[�����޸��������]</a>";
					forward = "modifyArticleTypeSuccess";			//admin/success.jsp;

				} else {
					messages = "<p class=\"error\">�޸�ʧ�ܣ�</p>";
					href = "<a href='javascript:window.history.go(-1)>[����]</a>";
					forward = "modifyArticleTypeError";				//admin/error.jsp;
				}
				request.setAttribute("messages", messages);
				request.setAttribute("href", href);
			} else {
				href = "<a href='javascript:window.history.go(-1)>[����]</a>";
				forward = "modifyArticleTypeError";				//admin/error.jsp;
				request.setAttribute("href", href);
			}
		}
		return forward;
	}

	/**
	 * @���� ��̨-ɾ���������
	 */
	public String deleteArticleType() {
		ArticleTypeDao typeDao = new ArticleTypeDao();
		ArticleTypeBean typeBean = new ArticleTypeBean();

		String messages = "";
		String href = "";
		String forward = "";

		typeBean.setId(MyTools.strToint(request.getParameter("typeId")));
		boolean mark = typeDao.operationArticleType("delete", typeBean);
		if (mark) {
			messages += "<p class=\"success\">ɾ�����ɹ���</p>";
			href = "<a href='/aesop/blog/ArticleAction?action=typeSelect'>[����ɾ���������]</a>";
			forward = "deleteArticleTypeSuccess";					//admin/success.jsp;

		} else {
			messages += "<p class=\"error\">ɾ�����ʧ�ܣ�</p>";
			href = "<a href='javascript:window.history.go(-1)'>[����]</a>";
			forward = "deleteArticleTypeError";						//admin/error.jsp;
		}
		request.setAttribute("messages", messages);
		request.setAttribute("href", href);
		return forward;
	}
}

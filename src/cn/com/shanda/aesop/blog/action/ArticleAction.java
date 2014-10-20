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
			temp = this.selectArticle(); // 获取某类别下所有文章
		if (action.equals("adminSelectList"))
			temp = this.adminSelectList();
		if (action.equals("adminSelectSingle"))
			temp = this.adminSelectSingle();
		if (action.equals("add"))
			temp = this.addArticle(); // 增加文章
		if (action.equals("delete"))
			temp = this.deleteArticle(); // 删除文章
		if (action.equals("modify"))
			temp = this.modifyArticle(); // 修改文章
		if (action.equals("read"))
			temp = this.readArticle(); // 阅读文章
		if (action.equals("followAdd"))
			temp = this.validateFollow(); // 发表文章回复
		if (action.equals("typeAdd"))
			temp = this.addArticleType(); // 增加文章类别
		if (action.equals("typeSelect"))
			temp = this.selectArticleType(); // 查看文章类别
		if (action.equals("typeModify"))
			temp = this.modifyArticleType(); // 修改文章类别
		if (action.equals("typeDelete"))
			temp = this.deleteArticleType(); // 删除文章类别
		return temp;
	}

	/**
	 * @功能 验证评论信息
	 */
	public String validateFollow() {
		String str = "";
		boolean mark = true;
		String messages = "";

		String content = request.getParameter("reContent");
		if (content == null || content.equals("")) {
			mark = false;
			messages = "<p class=\"error\">请输入 <b>评论内容！</b></p>";
		}
		if (mark) {
			content = MyTools.toChinese(content);
			if (content.length() > 1000) {
				mark = false;
				messages = "<p class=\"error\"><b>评论内容</b> 最多允许输入1000个字符！</p>";
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
	 * @功能 添加文章评论
	 */
	public String followAdd() {
		int id = Integer.parseInt(request.getParameter("articleId"));
		String author = MyTools.toChinese(request.getParameter("reAuthor"));
		String content = MyTools.toChinese(request.getParameter("reContent"));
		String today = MyTools.changeTime(new Date());
		if (author == null || author.equals(""))
			author = "匿名好友";

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
			messages = "<p class=\"success\">发表评论成功！</p>";

		} else {
			forward = "followAddError"; // front/article/error.jsp
			messages = "<p class=\"error\">发表评论失败！</p>";
		}
		request.setAttribute("messages", messages);

		return forward;

	}

	/**
	 * @功能 阅读文章
	 * @实现 1.增加文章阅读次数<br>
	 *     2.获取指定文章信息<br>
	 *     3.获取对该文章的所有评论
	 */
	@SuppressWarnings("rawtypes")
	public String readArticle() {
		ArticleBean articleBean = new ArticleBean();
		ArticleDao articleDao = new ArticleDao();
		ReviewDao reviewDao = new ReviewDao();

		String strId = request.getParameter("id");
		int id = MyTools.strToint(strId);
		articleBean.setId(id);

		articleDao.operationArticle("readTimes", articleBean); // 累加阅读次数
		articleBean = articleDao.queryArticleSingle(id); // 查询指定文章的详细内容
		session.setAttribute("readSingle", articleBean); // 保存articleBean到request对象中

		List reviewlist = reviewDao.queryReview(id);
		session.setAttribute("reviewlist", reviewlist);

		return "readArticle";							//front/article/ArticleSingle.jsp
	}

	/**
	 * @功能 修改文章
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
					messages = "<p class=\"success\">修改成功！</p>";
					href = "<a href='/aesop/blog/ArticleAction?action=adminSelectList&typeId="
							+ session.getAttribute("showTypeId")
							+ "'>[继续修改其他文章]</a>";
					forward = "modifyArticleSuccess";			//admin/success.jsp;
				} else {
					messages = "<p class=\"error\">修改失败！</p>";
					href = "<a href='javascript:window.history.go(-1)'>[返回]</a>";
					forward = "modifyArticleError";				//admin/error.jsp;
				}
				request.setAttribute("messages", messages);
				request.setAttribute("href", href);
			} else {
				href = "<a href='javascript:window.history.go(-1)>[返回]</a>";
				forward = "modifyArticleError";				//admin/error.jsp";
				request.setAttribute("href", href);
			}
		}
		return forward;
	}

	/**
	 * @功能 后台-删除文章
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
			messages += "<p class=\"success\">删除文章成功！</p>";
			href = "<a href='/aesop/blog/ArticleAction?action=adminSelectList&typeId="
					+ typeId + "'>[继续删除其他文章]</a>";
			forward = "deleteArticleSuccess";						//admin/success.jsp;

		} else {
			messages += "<p class=\"error\">删除文章失败！</p>";
			href = "<a href='javascript:window.history.go(-1)'>[返回]</a>";
			forward = "deleteArticleError";							//admin/error.jsp;
		}
		request.setAttribute("messages", messages);
		request.setAttribute("href", href);
		
		return forward;
	}

	/**
	 * @功能 验证发表文章信息
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
			messages += "<p class=\"error\">请选择 <b>文章类别！</b></p>";
		}
		if (title == null || title.equals("")) {
			mark = false;
			messages += "<p class=\"error\">请输入 <b>文章标题！</b></p>";
		}
		if (create == null || create.equals("")) {
			mark = false;
			messages += "<p class=\"error\">请选择 <b>文章来源！</b></p>";
		}
		if (info == null || info.equals("")) {
			mark = false;
			messages += "<p class=\"error\">请输入 <b>文章描述！</b></p>";
		}
		if (content == null || content.equals("")) {
			mark = false;
			messages += "<p class=\"error\">请输入 <b>文章内容！</b></p>";
		}
		if (mark) {
			title = MyTools.toChinese(title);
			content = MyTools.toChinese(content);
			if (title.length() > 20) {
				mark = false;
				messages += "<p class=\"error\"><b>文章标题</b> 最多允许输入20个字符！</p>";
			}
			if (content.length() > 4000) {
				mark = false;
				messages += "<p class=\"error\"><b>文章内容</b> 最多允许输入4000个字符！</p>";
			}
		}
		request.setAttribute("messages", messages);
		return mark;
	}

	/**
	 * @功能 后台-增加文章
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
				messages = "<p class=\"success\">发表文章成功！</p>";
				href = "<a href='/aesop/admin/article/ArticleAdd.jsp'>[继续发表]</a>";
				forward = "addArticleSuccess";				//admin/success.jsp;
			} else {
				messages = "<p class=\"error\">发表文章失败！</p>";
				href = "<a href='javascript:window.history.go(-1)'>[返回]</a>";
				forward = "addArticleError";				//admin/error.jsp;
			}
			request.setAttribute("messages", messages);
			request.setAttribute("href", href);
		} else {
			href = "<a href='javascript:window.history.go(-1)'>[返回]</a>";
			forward = "addArticleError";				//admin/error.jsp;
			request.setAttribute("href", href);
		}
		return forward;
	}

	/**
	 * @功能 实现后台文章管理中的文章浏览功能
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

		ArticleBean articleBean = articleDao.queryArticleSingle(id); // 查询指定文章的详细内容
		request.setAttribute("articleSingle", articleBean);

		return "adminSelectSingle";				//admin/article/ArticleSingle.jsp
	}

	/**
	 * @功能 从数据表中获取某类别下的所有文章
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
			messages += "<p class=\"error\">请输入 <b>类别名称！</b></p>";
		}
		if (typeInfo == null || typeInfo.equals("")) {
			mark = false;
			messages += "<p class=\"error\">请输入 <b>类别介绍！</b></p>";
		}
		request.setAttribute("messages", messages);
		return mark;
	}

	/**
	 * @功能 后台-增加文章类别
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
				messages += "<p class=\"success\">添加文章类别成功！</p>";
				href = "<a href='/aesop/admin/article/ArticleTypeAdd.jsp'>[继续添加文章类别]</a>";
				forward = "addArticleTypeSuccess";					//admin/success.jsp;

			} else {
				messages += "<p class=\"error\">添加文章类别失败！</p>";
				href = "<a href='javascript:window.history.go(-1)'>[返回]</a>";
				forward = "addArticleTypeError";					//admin/error.jsp;
			}
			request.setAttribute("messages", messages);
			request.setAttribute("href", href);
		} else {
			href = "<a href='javascript:window.history.go(-1)'>[返回]</a>";
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
	 * @功能 后台-修改文章类别
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
					messages = "<p class=\"success\">修改类别成功！</p>";
					href = "<a href='/aesop/blog/ArticleAction?action=typeSelect'>[继续修改其他类别]</a>";
					forward = "modifyArticleTypeSuccess";			//admin/success.jsp;

				} else {
					messages = "<p class=\"error\">修改失败！</p>";
					href = "<a href='javascript:window.history.go(-1)>[返回]</a>";
					forward = "modifyArticleTypeError";				//admin/error.jsp;
				}
				request.setAttribute("messages", messages);
				request.setAttribute("href", href);
			} else {
				href = "<a href='javascript:window.history.go(-1)>[返回]</a>";
				forward = "modifyArticleTypeError";				//admin/error.jsp;
				request.setAttribute("href", href);
			}
		}
		return forward;
	}

	/**
	 * @功能 后台-删除文章类别
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
			messages += "<p class=\"success\">删除类别成功！</p>";
			href = "<a href='/aesop/blog/ArticleAction?action=typeSelect'>[继续删除其他类别]</a>";
			forward = "deleteArticleTypeSuccess";					//admin/success.jsp;

		} else {
			messages += "<p class=\"error\">删除类别失败！</p>";
			href = "<a href='javascript:window.history.go(-1)'>[返回]</a>";
			forward = "deleteArticleTypeError";						//admin/error.jsp;
		}
		request.setAttribute("messages", messages);
		request.setAttribute("href", href);
		return forward;
	}
}

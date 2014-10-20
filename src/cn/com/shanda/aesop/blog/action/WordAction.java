package cn.com.shanda.aesop.blog.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.com.shanda.aesop.blog.bean.WordBean;
import cn.com.shanda.aesop.blog.dao.WordDao;
import cn.com.shanda.aesop.blog.util.MyTools;

@SuppressWarnings("serial")
public class WordAction extends ActionSupport {

	private String temp;
	private HttpServletRequest request;

	private String action;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public WordAction() {
		request = ServletActionContext.getRequest();
	}

	@Override
	public String execute()  {
//		String action = request.getParameter("action");
		if (action == null)
			action = "";
		if (action.equals("add"))
			temp = this.validateWord(); // �������
		if (action.equals("select"))
			temp = this.selectWord(); // ǰ̨�鿴����
		if (action.equals("adminselect"))
			temp = this.adminSelect();
		if (action.equals("delete"))
			temp = this.delete();
		return temp;
	}

	public String validateWord() {
		boolean mark = true;
		String messages = "";
		String forward;

		String author = request.getParameter("wordAuthor");
		String title = request.getParameter("wordTitle");
		String content = request.getParameter("wordContent");
		if (author == null || author.equals(""))
			author = "��������";
		if (title == null || title.equals("")) {
			mark = false;
			messages += "<p class=\"error\">������ <b>���Ա��⣡</b></p>";
		}
		if (content == null || content.equals("")) {
			mark = false;
			messages += "<p class=\"error\">������ <b>�������ݣ�</b></p>";
		}
		if (!mark) {
			request.setAttribute("messages", messages);
			forward = "validateWord"; // front/word/error.jsp
		} else {
			forward = addWord();
		}
		return forward;
	}

	public String addWord() {
		WordBean wordSingle = new WordBean();

		String author = MyTools.toChinese(request.getParameter("wordAuthor"));
		String title = MyTools.toChinese(request.getParameter("wordTitle"));
		String content = MyTools.toChinese(request.getParameter("wordContent"));
		String sdTime = MyTools.changeTime(new Date());
		if (author == null || author.equals(""))
			author = "��������";

		wordSingle.setWordAuthor(author);
		wordSingle.setWordTitle(title);
		wordSingle.setWordContent(content);
		wordSingle.setWordTime(sdTime);

		WordDao wordDao = new WordDao();
		String messages = "";
		String forward = "";
		boolean mark = wordDao.operationWord("add", wordSingle);
		if (mark) {
			forward = "addWordSuccess"; // front/word/success.jsp;
			messages = "<p class=\"success\">���Գɹ���</p>";

		} else {
			forward = "addWordError"; // front/word/error.jsp;
			messages = "<p class=\"error\">����ʧ�ܣ�</p>";
		}
		request.setAttribute("messages", messages);
		return forward;
	}

	@SuppressWarnings("rawtypes")
	public String selectWord() {
		WordDao wordDao = new WordDao();

		List wordList = wordDao.queryWord("all");
		request.setAttribute("wordList", wordList);

		return "selectWord"; // front/word/WordShow.jsp
	}

	@SuppressWarnings("rawtypes")
	public String adminSelect() {
		WordDao wordDao = new WordDao();
	
		List wordList = wordDao.queryWord("all");
		request.setAttribute("adminwordList", wordList);

		return "adminSelect"; // admin/word/WordList.jsp
	}

	public String delete() {
		WordDao wordDao = new WordDao();
		WordBean wordBean = new WordBean();

		String messages = "";
		String href = "";
		String forward = "";
		wordBean.setId(MyTools.strToint(request.getParameter("id")));
		boolean mark = wordDao.operationWord("delete", wordBean);
		if (mark) {
			messages += "<p class=\"success\">ɾ�����Գɹ���</p>";
			href = "<a href='/aesop/blog/WordAction?action=adminselect'>[����ɾ����������]</a>";
			forward = "deleteSuccess"; // admin/success.jsp;

		} else {
			messages += "<p class=\"error\">ɾ������ʧ�ܣ�</p>";
			href = "<a href='javascript:window.history.go(-1)'>[����]</a>";
			forward = "deleteError"; // admin/error.jsp;
		}
		request.setAttribute("messages", messages);
		request.setAttribute("href", href);
		return forward;
	}
}

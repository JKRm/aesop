package cn.com.shanda.aesop.blog.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.com.shanda.aesop.blog.bean.MasterBean;
import cn.com.shanda.aesop.blog.dao.ArticleDao;
import cn.com.shanda.aesop.blog.dao.ArticleTypeDao;
import cn.com.shanda.aesop.blog.dao.FriendDao;
import cn.com.shanda.aesop.blog.dao.LogonDao;
import cn.com.shanda.aesop.blog.dao.WordDao;

@SuppressWarnings("serial")
public class IndexAction extends ActionSupport {
	
	private static MasterBean masterBean;
	private HttpServletRequest request; 
	private HttpSession session; 
	
	static{
		LogonDao logonDao=new LogonDao();
		masterBean=logonDao.getMaster();		
	}
	
	public IndexAction(){
		request = ServletActionContext.getRequest();
		session = request.getSession(); 
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String execute() {	
		ArticleDao articleDao = new ArticleDao();
		ArticleTypeDao artTypeDao = new ArticleTypeDao();
		WordDao wordDao=new WordDao();
		FriendDao friendDao=new FriendDao();
		
		/********** 获取在主页面的内容显示区中显示的内容 *********/
		//从tb_article数据表中获取前3篇文章 
		List articleList=articleDao.queryArticle(-1,null);
		request.setAttribute("articleList",articleList);
		/********** 获取在页面侧栏中显示的内容 *********/
		/* 从tb_word数据表中获取前5条留言 */
		List wordList=wordDao.queryWord("sub");
		session.setAttribute("wordList",wordList);
		/* 从tb_article数据表中获取前5章推荐文章 */
		List artTJList=articleDao.queryArticle(4,"sub");
		session.setAttribute("artTJList",artTJList);
		/* 从tb_friend数据表中获取前5位好友信息 */
		List friendList=friendDao.queryFriend("sub");
		session.setAttribute("friendList", friendList);		
		
		/********** 获取文章类别 *******************/
		/* 从tb_articleType数据表中获取文章类别 */
		List artTypeList=artTypeDao.queryTypeList();
		session.setAttribute("artTypeList",artTypeList);
		
		/*********** 保存博主信息 *****************/
		session.setAttribute("master",masterBean);
		
		return SUCCESS;				//front/FrontIndex.jsp
	}
	


}

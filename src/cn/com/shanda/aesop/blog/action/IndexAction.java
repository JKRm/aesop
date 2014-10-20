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
		
		/********** ��ȡ����ҳ���������ʾ������ʾ������ *********/
		//��tb_article���ݱ��л�ȡǰ3ƪ���� 
		List articleList=articleDao.queryArticle(-1,null);
		request.setAttribute("articleList",articleList);
		/********** ��ȡ��ҳ���������ʾ������ *********/
		/* ��tb_word���ݱ��л�ȡǰ5������ */
		List wordList=wordDao.queryWord("sub");
		session.setAttribute("wordList",wordList);
		/* ��tb_article���ݱ��л�ȡǰ5���Ƽ����� */
		List artTJList=articleDao.queryArticle(4,"sub");
		session.setAttribute("artTJList",artTJList);
		/* ��tb_friend���ݱ��л�ȡǰ5λ������Ϣ */
		List friendList=friendDao.queryFriend("sub");
		session.setAttribute("friendList", friendList);		
		
		/********** ��ȡ������� *******************/
		/* ��tb_articleType���ݱ��л�ȡ������� */
		List artTypeList=artTypeDao.queryTypeList();
		session.setAttribute("artTypeList",artTypeList);
		
		/*********** ���沩����Ϣ *****************/
		session.setAttribute("master",masterBean);
		
		return SUCCESS;				//front/FrontIndex.jsp
	}
	


}

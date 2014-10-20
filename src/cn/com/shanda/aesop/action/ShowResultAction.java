package cn.com.shanda.aesop.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.com.shanda.aesop.pojo.*;

import com.opensymphony.xwork2.ActionSupport;

public class ShowResultAction extends ActionSupport{
	
	//Ŀ��ҳ��ı�ʾ
	private String destination = "";
	// �������
	private int resultCount;		/* �ܼ�¼�� */
	private QueryResult queryResult;
	
	// ������ʾ�ļ�¼
	private List<String> tips = new ArrayList<String>();			/* ��ǰҳ��ƴ���� */
	private List<ResourceItem> list = new ArrayList<ResourceItem>();/* ��ǰҳ���¼�� */
	
	// ���ҳ��صı���	
	private static int recordsPerPage;		/* ÿҳ��ʾ�ļ�¼�� */
	private static int numbersPerPage;		/* ÿҳ��ʾ��ҳ���� */
	private int pageNum;			/* ҳ�� */
	private int currentPage;		/* ��ǰҳ�� */
	private String pageBar;			/* ҳ���� */
	private String warning;			/* �޼��������ʾ��Ϣ */

	private String prompt;			/* ��ʾ��Ϣ */
	
	// ���ö���
	private HttpServletRequest request; 
	private HttpSession session;
	
	public ShowResultAction() {
		request = ServletActionContext.getRequest();
		session = request.getSession();
		// �趨���н����
		setQueryResult((QueryResult)session.getAttribute("result"));
		// �趨���н����
		setResultCount(queryResult.getResultCount());
		// ���㲢�趨ҳ��
		setPageNum();
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String execute() {
		 
		if (resultCount > 0) {
			setCurrentPage();
			setCurrentRecords();
			setPageBar();
			setPrompt();
		} else {
			setWarning();
		}
		
		if (null == destination || "".equals(destination)) {
			destination = (String)request.getAttribute("destination");
		}
		
		if (null != destination){
			
			return destination;
		} 
		return "success";
	}
	
	/**
	 * �趨ҳ����
	 */
	public void setPageNum() {
		int page = resultCount % recordsPerPage;
		int pages = resultCount / recordsPerPage;
		int pageNum = (page==0)?pages:(pages + 1);
	
		setPageNum(pageNum);
	}

	/**
	 * �趨��ǰҳ
	 */
	public void setCurrentPage() {
		// ���currentP��ֵ�� ��Ϊ��˵����action�Ǵ�search�е��õģ�
		// ����˵����action�Ǵ�ҳ�����л�����ת����õ�
		String currentP = request.getParameter("currentP");
		int page;
		if (currentP == null) {
			currentP = request.getParameter("jump");
			if (currentP == null) {
				page = 1;
			} else {
				try {
					 page = Integer.parseInt(currentP);
				} catch (NumberFormatException e) {
					page = 1;
				}
			}
		} else {
			try {
				 page = Integer.parseInt(currentP);
			} catch (NumberFormatException e) {
				page = 1;
			}
		}
		setCurrentPage(page);
	}
	
	/**
	 * �趨��ǰҳ������ʾ�Ľ����
	 */
	public void setCurrentRecords() {
		int start = (currentPage - 1) * recordsPerPage;
		int end = (currentPage != pageNum) 
					? currentPage * recordsPerPage 
					: resultCount;
		
		for (int i=start; i<end; ++i) {
			ResourceItem item = queryResult.getList().get(i);
			list.add(item);
		}
		tips = queryResult.getTips();
		
	}
	
	/**
	 * �趨ҳ����
	 * ÿҳ��ʾ��ҳ�������ڽ��ҳ��ʱ�� ��ʾȫ��ҳ��
	 * ÿҳ��ʾ��ҳ�������ڽ��ҳ��ʱ�� ��ʾ����ҳ�룬 �ҵ�ǰҳ�뾡��λ���м�λ��
	 */
	public void setPageBar() {
		int start;
		int end;
		
		String mark = "";
		if (null != this.destination && !"".equals(this.destination)) {
			mark = "destination=" + this.destination + "&";
		} else if (null != request.getAttribute("destination")){
			this.destination = (String)request.getAttribute("destination");
			mark = "destination=" + this.destination + "&";
		}
		
		// �趨���Ƿ��� "��һҳ"
		if (numbersPerPage < pageNum && currentPage > numbersPerPage / 2 + 1) {
			setPageBar("<p><a href=/aesop/showResult?" + mark + "currentP=1>��һҳ</a>");
		} else {
			setPageBar("<p><a>��һҳ</a>");
		}
		
		// �趨����ҳ��
		if (numbersPerPage >= pageNum) {			// ÿҳ��ʾ��ҳ�������ڽ��ҳ��ʱ�� ��ʾȫ��ҳ��
			start = 1;							// ÿҳ��ʾ��ҳ�������ڽ��ҳ��ʱ�� ��ʾ����ҳ�룬 �ҵ�ǰҳ�뾡��λ���м�λ��
			end = pageNum;
		} else if (currentPage <= (numbersPerPage / 2) + 1) {	// ǰ�벿�ִ�1��ʼ��ʾ
			start = 1;
			end = numbersPerPage;
		} else if (currentPage < pageNum - numbersPerPage / 2 + 1) {	// �м䲿�ִ��м���������ʾ
			start = currentPage - numbersPerPage / 2;
			end = currentPage + numbersPerPage / 2;
		} else {										// ��벿����ʾ��ĩβҳ
			start = pageNum - numbersPerPage + 1;
			end = pageNum;
		}
		for (int i=start; i<=end; ++i) {
			if (i == currentPage) {
				pageBar += "<a>" + i + "</a>";
			} else {
				pageBar += "<a href=/aesop/showResult?" + mark + "currentP=" + i + "> " + i + "</a>";
			}
		}
		
		// �趨�Ƿ������һҳ
		if (numbersPerPage < pageNum && currentPage <= pageNum - numbersPerPage / 2) {
			pageBar += "<a href=/aesop/showResult?" + mark + "currentP=" + pageNum + ">���һҳ</a></p>";
		} else {
			pageBar += "<a>���һҳ</a></p>";
		}
		
		// �趨�Ƿ�����һҳ����һҳ
		pageBar += "<p>";		// ������ʾ
		if (pageNum > 1) {
			if (currentPage != 1) {
				pageBar += "<a href=/aesop/showResult?" + mark + "currentP=" + (currentPage - 1) + ">��һҳ</a>";
			} else {
				pageBar += "<a>��һҳ</a>";
			}
			if (currentPage != pageNum) {
				pageBar += "<a href=/aesop/showResult?" + mark + "currentP=" + (currentPage + 1) + ">��һҳ</a>";
			} else {
				pageBar += "<a>��һҳ</a></p>";
			}
		}
		pageBar += "<form action=/aesop/showResult method=\"get\"><input type=\"hidden\" value=\"" + destination + "\" name=\"destination\"/><p>��" +
					pageNum + "ҳ&nbsp;��ת����<select name=\"jump\">" + selectPages() + "ҳ<input type=\"submit\" value=\"GO\"></p>";
		
	}
	
	/**
	 * �趨���������ʾ��Ϣ
	 * ����~ƴ�� ������ʾ
	 */
	public void setPrompt() {
		setPrompt(null);
		
		String mark = "";
		if (null != this.destination && !"".equals(this.destination)) {
			mark = "destination=" + this.destination + "&";
		} else if (null != request.getAttribute("destination")){
			this.destination = (String)request.getAttribute("destination");
			mark = "destination=" + this.destination + "&";
		}
		
		if (tips.size() > 0) {
			setPrompt("<p>��Ҫ�ҵ��ǲ��ǣ�");
			for (int i=0; i<tips.size(); ++i) {
				prompt += "<a href=/aesop/searchByPrompt?" + mark + "queryText=" + (String)tips.get(i) + ">" + (String)tips.get(i) + "</a>";
			}
			prompt += "</p>";
		}
	}
	
	/**
	 * �趨�޼����������ʾ��Ϣ
	 */
	public void setWarning() {
		if (resultCount != 0) {
			warning = "";
		} else {
			warning = "<div id=\"warning\"><p>�Բ��� û�м�⵽����������Ľ�����������趨��������<p /></div>";
		}
	}
	public String selectPages() {
		String pages = "";
		for (int i=1; i<=pageNum; ++i) {
			if (i != currentPage) {
				pages += "<option>" + i + "</option>";
			} else {
				pages += "<option selected=\"selected\">" + i + "</option>";
			}
		}
		pages += "</select>";
		return pages;
	}
	
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
		this.warning = warning;
	}
	public List<String> getTips() {
		return tips;
	}
	public void setTips(List<String> tips) {
		this.tips = tips;
	}
	public List<ResourceItem> getList() {
		return list;
	}
	public void setList(List<ResourceItem> list) {
		this.list = list;
	}
	public static int getNumbersPerPage() {
		return numbersPerPage;
	}
	public static void setNumbersPerPage(int numbersPerPage) {
		ShowResultAction.numbersPerPage = numbersPerPage;
	}
	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}
	private void setQueryResult(QueryResult queryResult) {
		this.queryResult = queryResult;
	}
	public QueryResult getQueryResult() {
		return queryResult;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public String getPageBar() {
		return pageBar;
	}
	public void setPageBar(String pageBar) {
		this.pageBar = pageBar;
	}
	public static int getRecordsPerPage() {
		return recordsPerPage;
	}
	public static void setRecordsPerPage(int recordsPerPage) {
		ShowResultAction.recordsPerPage = recordsPerPage;
	}
	public int getNumbersPerpage() {
		return numbersPerPage;
	}
	public void setNumbersPerpage(int numbersPerpage) {
		this.numbersPerPage = numbersPerpage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}

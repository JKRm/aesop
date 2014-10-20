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
	
	//目标页面的标示
	private String destination = "";
	// 搜索结果
	private int resultCount;		/* 总记录数 */
	private QueryResult queryResult;
	
	// 界面显示的记录
	private List<String> tips = new ArrayList<String>();			/* 当前页面拼音集 */
	private List<ResourceItem> list = new ArrayList<ResourceItem>();/* 当前页面记录集 */
	
	// 与分页相关的变量	
	private static int recordsPerPage;		/* 每页显示的记录数 */
	private static int numbersPerPage;		/* 每页显示的页码数 */
	private int pageNum;			/* 页数 */
	private int currentPage;		/* 当前页码 */
	private String pageBar;			/* 页码栏 */
	private String warning;			/* 无检索结果提示信息 */

	private String prompt;			/* 提示信息 */
	
	// 内置对象
	private HttpServletRequest request; 
	private HttpSession session;
	
	public ShowResultAction() {
		request = ServletActionContext.getRequest();
		session = request.getSession();
		// 设定所有结果集
		setQueryResult((QueryResult)session.getAttribute("result"));
		// 设定所有结果数
		setResultCount(queryResult.getResultCount());
		// 计算并设定页数
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
	 * 设定页码数
	 */
	public void setPageNum() {
		int page = resultCount % recordsPerPage;
		int pages = resultCount / recordsPerPage;
		int pageNum = (page==0)?pages:(pages + 1);
	
		setPageNum(pageNum);
	}

	/**
	 * 设定当前页
	 */
	public void setCurrentPage() {
		// 获得currentP的值， 若为空说明该action是从search中调用的，
		// 否则说明该action是从页码栏中或者跳转框调用的
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
	 * 设定当前页面上显示的结果集
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
	 * 设定页码栏
	 * 每页显示的页码数多于结果页数时， 显示全部页码
	 * 每页显示的页码数少于结果页数时， 显示部分页码， 且当前页码尽量位于中间位置
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
		
		// 设定上是否有 "第一页"
		if (numbersPerPage < pageNum && currentPage > numbersPerPage / 2 + 1) {
			setPageBar("<p><a href=/aesop/showResult?" + mark + "currentP=1>第一页</a>");
		} else {
			setPageBar("<p><a>第一页</a>");
		}
		
		// 设定常规页码
		if (numbersPerPage >= pageNum) {			// 每页显示的页码数多于结果页数时， 显示全部页码
			start = 1;							// 每页显示的页码数少于结果页数时， 显示部分页码， 且当前页码尽量位于中间位置
			end = pageNum;
		} else if (currentPage <= (numbersPerPage / 2) + 1) {	// 前半部分从1开始显示
			start = 1;
			end = numbersPerPage;
		} else if (currentPage < pageNum - numbersPerPage / 2 + 1) {	// 中间部分从中间往两边显示
			start = currentPage - numbersPerPage / 2;
			end = currentPage + numbersPerPage / 2;
		} else {										// 后半部分显示至末尾页
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
		
		// 设定是否有最后一页
		if (numbersPerPage < pageNum && currentPage <= pageNum - numbersPerPage / 2) {
			pageBar += "<a href=/aesop/showResult?" + mark + "currentP=" + pageNum + ">最后一页</a></p>";
		} else {
			pageBar += "<a>最后一页</a></p>";
		}
		
		// 设定是否有上一页和下一页
		pageBar += "<p>";		// 换行显示
		if (pageNum > 1) {
			if (currentPage != 1) {
				pageBar += "<a href=/aesop/showResult?" + mark + "currentP=" + (currentPage - 1) + ">上一页</a>";
			} else {
				pageBar += "<a>上一页</a>";
			}
			if (currentPage != pageNum) {
				pageBar += "<a href=/aesop/showResult?" + mark + "currentP=" + (currentPage + 1) + ">下一页</a>";
			} else {
				pageBar += "<a>下一页</a></p>";
			}
		}
		pageBar += "<form action=/aesop/showResult method=\"get\"><input type=\"hidden\" value=\"" + destination + "\" name=\"destination\"/><p>共" +
					pageNum + "页&nbsp;跳转至第<select name=\"jump\">" + selectPages() + "页<input type=\"submit\" value=\"GO\"></p>";
		
	}
	
	/**
	 * 设定检索结果提示信息
	 * 中文~拼音 对照提示
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
			setPrompt("<p>您要找的是不是：");
			for (int i=0; i<tips.size(); ++i) {
				prompt += "<a href=/aesop/searchByPrompt?" + mark + "queryText=" + (String)tips.get(i) + ">" + (String)tips.get(i) + "</a>";
			}
			prompt += "</p>";
		}
	}
	
	/**
	 * 设定无检索结果的提示信息
	 */
	public void setWarning() {
		if (resultCount != 0) {
			warning = "";
		} else {
			warning = "<div id=\"warning\"><p>对不起， 没有检测到与条件相符的结果，请重新设定搜索条件<p /></div>";
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

package cn.com.shanda.aesop.back;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.dom4j.DocumentException;

import cn.com.shanda.aesop.dao.PreviewXMLParserDao;
import cn.com.shanda.aesop.dao.ResourceXMLParserDao;
import cn.com.shanda.aesop.dao.impl.PreviewXMLParserDaoImpl;
import cn.com.shanda.aesop.dao.impl.ResourceXMLParserDaoImpl;
import cn.com.shanda.aesop.service.IndexService;
import cn.com.shanda.aesop.service.impl.IndexServiceImpl;
import cn.com.shanda.aesop.util.FileDeleter;

import com.opensymphony.xwork2.ActionSupport;

public class DelResourceAction extends ActionSupport {

private HttpServletRequest request;
	
	private ResourceXMLParserDao rxpd = new ResourceXMLParserDaoImpl();
	
	private String[] checkbox;
	
	public DelResourceAction(){
		
		request = ServletActionContext.getRequest();
		
	}
	
	public void setCheckbox(String[] checkbox) {
		
		this.checkbox = checkbox;
	}

	public String[] getCheckbox() {
		
		return checkbox;
	}
	
	/**
	 * ����ʱ�� 8.8 9:43
	 * ��������:�����ɾ�������ļ������ݿ�����Դ����ת��ӳ��ļ�¼,����˸�����Ӧ�����Ĳ���
	 */
	public String execute(){
		
		for(int i=0;i<checkbox.length;i++){
			
			checkbox[i] = checkbox[i].replaceAll("%NULL%", " ");
			
			try {
				rxpd.delete(checkbox[i]);
			} catch (DocumentException e) {
//				e.printStackTrace();
			} catch (IOException e) {
//				e.printStackTrace();
			}
					
			//ɾ����Դ
			String filepath1 = System.getenv("CATALINA_HOME") + "\\webapps\\resources\\" + checkbox[i].substring(checkbox[i].lastIndexOf("/") + 1);
			FileDeleter.deleteFile(filepath1);

			PreviewXMLParserDao pxpdi = new PreviewXMLParserDaoImpl();
			
			//��������
			IndexService indexService = new IndexServiceImpl();
			indexService.deleteIndex(checkbox[i]);
		}
		
		request.setAttribute("message", "��Դɾ���ɹ���");
		
		return SUCCESS;
		
	}

}

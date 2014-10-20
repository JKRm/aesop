package cn.com.shanda.aesop.back;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.com.shanda.aesop.convert.Converter;
import cn.com.shanda.aesop.convert.GenerationToFlv;
import cn.com.shanda.aesop.convert.GenerationToJpg;
import cn.com.shanda.aesop.convert.GetTypeName;
import cn.com.shanda.aesop.convert.MainConvert;
import cn.com.shanda.aesop.dao.PreviewXMLParserDao;
import cn.com.shanda.aesop.dao.impl.PreviewXMLParserDaoImpl;
import cn.com.shanda.aesop.dao.impl.ResourceXMLParserDaoImpl;
import cn.com.shanda.aesop.pojo.ResourceItem;
import cn.com.shanda.aesop.preview.GetFileName;
import cn.com.shanda.aesop.service.IndexService;
import cn.com.shanda.aesop.service.impl.IndexServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

 
@SuppressWarnings("serial")
public class UploadAction extends ActionSupport
{
	//��ȡ�ļ��������Ϣ
	
	private String keywords;
	
	private String describe;
	
	private File upload;							//��װ�ϴ��ļ��������
	
	private String uploadContentType;				//��װ�ϴ��ļ����͵�����
	
	private String uploadFileName;					//��װ�ϴ��ļ���������
	
	private String savePath;						//ֱ����struts.xml�ļ������õ�����
	
	private String type;

	private String title;
	
	private String author;
	
	private String publisher;
	
	private String link;
	
	private String GetConvertedName;
	
	private boolean checked = false;
	
	private String doctype;
	
	private String TypeName;
	
	GetTypeName gtn = new GetTypeName();

	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setSavePath(String value)		    //����struts.xml�ļ�����ֵ�ķ���
	{
		this.savePath = value;
	}
	
	private String getSavePath() throws Exception 		//�����ϴ��ļ��ı���λ��
	{
		return ServletActionContext.getServletContext()
			.getRealPath(savePath);
	}

	
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	public String getType() {
		return type;
	}

	//��ȡ�ϴ��ļ�����
	
	public void setType(String type) {
		type=uploadContentType.substring(uploadContentType.lastIndexOf("/")+ 1,uploadContentType.length()).toLowerCase();
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		title=uploadFileName.substring(0,uploadFileName.lastIndexOf("."));
		this.title = title;
	}
	
	public void setUpload(File upload) 				//�ϴ��ļ���Ӧ�ļ����ݵ�setter��getter����
	{
		this.upload = upload; 
	}
	
	public File getUpload() 
	{
		return (this.upload); 
	}

	
	public void setUploadContentType(String uploadContentType) 	    //�ϴ��ļ����ļ����͵�setter��getter����
	{
		this.uploadContentType = uploadContentType; 
	}
	public String getUploadContentType()
	{
		return (this.uploadContentType); 
	}

	
	public void setUploadFileName(String uploadFileName) 			//�ϴ��ļ����ļ�����setter��getter����
	{
		this.uploadFileName = uploadFileName; 
	}
	public String getUploadFileName() 
	{
		return (this.uploadFileName); 
	}
	
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isChecked() {
		return checked;
	}

	

	public String getDoctype() {
		return doctype;
	}

	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}

	public String getTypeName() {
		return gtn.GetType(doctype);
	}

	public void setTypeName(String typeName) {
		TypeName = typeName;
	}

	//ִ���ļ��ϴ���д����Դxml
	
	@Override
	public String execute() throws Exception						//�Է��������ļ������ַ��ԭ�ļ��������ϴ��ļ������
	{
		String PreName = getUploadFileName();
		
		//���ϴ��ļ�д��resources�ļ�����
		FileOutputStream fos = new FileOutputStream(getSavePath() + 
				"\\" + getUploadFileName());
		FileInputStream fis = new FileInputStream(getUpload());
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) > 0)
		{
			fos.write(buffer , 0 , len);
		}
		fos.close();
		
		Thread.sleep(300);
		
		String kind = uploadFileName.substring(uploadFileName.lastIndexOf(".")).substring(1);
		
		setUploadContentType(uploadFileName.substring(uploadFileName.lastIndexOf(".")+1, uploadFileName.length()).toLowerCase());
		setUploadFileName(uploadFileName.substring(0,uploadFileName.lastIndexOf(".")));
		
		String localip = InetAddress.getLocalHost().toString();
		String ip = localip.substring(localip.lastIndexOf("/") + 1);
		String url = "http://"+ip+":8080/"+"resources"+"/"+PreName;
		
		Date uploadDate = new Date();
		
		//��Դxml�ļ��������Ӧ��Դ
		ResourceXMLParserDaoImpl xpdi = new ResourceXMLParserDaoImpl();
		ResourceItem ri = new ResourceItem();
		ri.setTitle(this.getUploadFileName());
		ri.setKind(this.getUploadContentType());
		ri.setKeywords(this.getKeywords());
		ri.setDescription(this.getDescribe());
		ri.setAuthor(this.getAuthor());
		ri.setPublisher(this.getPublisher());
		ri.setDate(uploadDate);
		ri.setUrl(url);
		xpdi.add(ri);
		
		String date = DateFormat.getDateTimeInstance().format(uploadDate);
		
		//���������ƴ����������rss�����
		IndexService indexService = new IndexServiceImpl();
		indexService.createIndex(ri);
		
		System.out.println("����ͼƬ");
		//����ͼƬ
		String oldurl = System.getenv("CATALINA_HOME") +"\\webapps\\resources\\"+PreName;
		GenerationToJpg jpg = new GenerationToJpg();
		jpg.processJPG(oldurl);
		Thread.sleep(300);
		
		System.out.println("����Դת��Ϊflv");
		//����Դת��Ϊflv
		GenerationToFlv flv=new GenerationToFlv();
		String oldurlflv = System.getenv("CATALINA_HOME") +"\\webapps\\resources\\"+PreName;
		flv.setOldPath(oldurlflv);                                       
		String newurlflv = System.getenv("CATALINA_HOME") +"\\webapps\\resources\\" + GetFileName.StringFilter(date) + ".flv";
		flv.setNewPath(newurlflv);
		flv.process();

		String  deKind = PreName.substring(PreName.lastIndexOf(".")+1);
	
	//���ϴ����Ŀ⹴ѡ��ִ���ļ�ת����PDF��SWF����	
	
	if(isChecked()){
		
		System.out.println("isChecked");
		if ("pdf".equals(deKind)) {
			
			Converter ct = new Converter();
			ct.setSourcePath(System.getenv("CATALINA_HOME") + "\\webapps\\resources\\");
			ct.convertPDF2SWF(PreName, GetFileName.StringFilter(date));
			link = url;
		}else {
		
			try {
				GetConvertedName = MainConvert.DoConvert(PreName, GetFileName.StringFilter(date));
				link = url;
			} catch (Exception e) {
				 
//				e.printStackTrace();
			}
		}
		System.out.println("��Ԥ�����Ӽ�Ԥ���ļ���д��Ԥ��xml");
		//��Ԥ�����Ӽ�Ԥ���ļ���д��Ԥ��xml
		PreviewXMLParserDao pxpdi = new PreviewXMLParserDaoImpl();
		pxpdi.add(PreName,link,doctype);
		
	} else if ("pdf".equals(deKind)) {
		
		Converter ct = new Converter();
		ct.setSourcePath(System.getenv("CATALINA_HOME") + "\\webapps\\resources\\");
		ct.convertPDF2SWF(PreName, GetFileName.StringFilter(date));
	}
	
		TypeName = gtn.GetType(doctype);
		return SUCCESS;
	}
	
}

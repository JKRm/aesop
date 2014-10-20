package cn.com.shanda.aesop.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.DateTools.Resolution;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

import cn.com.shanda.aesop.pojo.ResourceItem;


public class DocumentUtil {

	public static List<Document> getDocuments(List<ResourceItem> list) {

		List<Document> docList = new ArrayList<Document>();
		
		for (int i = 0; i < list.size(); ++i) {
			
			Document doc = new Document();
			
			ResourceItem item = list.get(i);
			doc.add(new Field("author", item.getAuthor(), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("description", item.getDescription(), Store.YES, Index.ANALYZED));
			doc.add(new Field("keywords", item.getKeywords(), Store.YES, Index.ANALYZED));
			doc.add(new Field("kind", item.getKind(), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("publisher", item.getPublisher(), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("title", item.getTitle(), Store.YES, Index.ANALYZED));
			doc.add(new Field("url", item.getUrl(), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("date", DateTools.dateToString(item.getDate(), Resolution.SECOND), Store.YES, Index.NOT_ANALYZED));

			docList.add(doc);
		}
		
		return docList;
	}
	
	public static Document getDocument(ResourceItem item) {
		
			Document doc = new Document();
			
			doc.add(new Field("author", item.getAuthor(), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("description", item.getDescription(), Store.YES, Index.ANALYZED));
			doc.add(new Field("keywords", item.getKeywords(), Store.YES, Index.ANALYZED));
			doc.add(new Field("kind", item.getKind(), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("publisher", item.getPublisher(), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("title", item.getTitle(), Store.YES, Index.ANALYZED));
			doc.add(new Field("url", item.getUrl(), Store.YES, Index.NOT_ANALYZED));
			doc.add(new Field("date", DateTools.dateToString(item.getDate(), Resolution.SECOND), Store.YES, Index.NOT_ANALYZED));
			
		return doc;
	}
}

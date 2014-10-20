package cn.com.shanda.aesop.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.List;

import cn.com.shanda.aesop.dao.RSSXMLParserDao;
import cn.com.shanda.aesop.pojo.ResourceItem;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;

public class RSSXMLParserDaoImpl implements RSSXMLParserDao {
	
	private String filePath = System.getenv("CATALINA_HOME") + "/webapps/aesop/rss/test.xml";
	
	private SyndFeed getFeed() throws FileNotFoundException, IllegalArgumentException, IOException, FeedException  {
		
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new File(filePath));
        
        return feed;
	}

	/* (non-Javadoc)
	 * @see cn.com.shanda.aesop.dao.RSSXMLParserDao#add(cn.com.shanda.aesop.pojo.ResourceItem)
	 */
	@Override
	public void add(ResourceItem item) throws IOException, IllegalArgumentException, FeedException {
		
        SyndFeed feed = getFeed();
        List entries = feed.getEntries();
        
        SyndEntry entry = new SyndEntryImpl();
        
	    // 构造一个新的节点并添加到列表中  
	    entry.setTitle(item.getTitle());  
	    entry.setLink(item.getUrl());
	    entry.setAuthor(item.getAuthor());
	    entry.setPublishedDate(new Date());
	    SyndContent description = new SyndContentImpl();  
	    description.setType("text/plain");  
	    description.setValue(item.getDescription());  
	    entry.setDescription(description);  
	    entries.add(entry);
	    
	    feed.setEntries(entries);
	    feed.setEncoding("UTF-8");
	    // 输出feed  
	    Writer writer = new FileWriter(filePath);
//	    writer.
	    SyndFeedOutput output = new SyndFeedOutput();  
        output.output(feed, writer);  
        writer.close();
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.aesop.dao.RSSXMLParserDao#add(java.util.List)
	 */
	@Override
	public void add(List<ResourceItem> list) throws IllegalArgumentException, IOException, FeedException {
		 SyndFeed feed = getFeed();
	     List entries = feed.getEntries();
	     
	     for (ResourceItem item:list) {
	    	SyndEntry entry = new SyndEntryImpl();
	         
	 	    // 构造一个新的节点并添加到列表中  
	 	    entry.setTitle(item.getTitle());  
	 	    entry.setLink(item.getUrl());
	 	    entry.setAuthor(item.getAuthor());
	 	    entry.setPublishedDate(new Date());
	 	    SyndContent description = new SyndContentImpl();  
	 	    description.setType("text/plain");  
	 	    description.setValue(item.getDescription());  
	 	    entry.setDescription(description);  
	 	    entries.add(entry);
	 	    
	 	    feed.setEntries(entries);
	     }
	     Writer writer = new FileWriter(filePath);
	     feed.setEncoding("UTF-8");
	     SyndFeedOutput output = new SyndFeedOutput();  
	     output.output(feed, writer);  
	     writer.close();
	}
	
	/* (non-Javadoc)
	 * @see cn.com.shanda.aesop.dao.RSSXMLParserDao#clear()
	 */
	@Override
	public void clear() throws IllegalArgumentException, IOException, FeedException {
		SyndFeed feed = getFeed();
	    List entries = feed.getEntries();
	    
	    entries.clear();
	    feed.setEntries(entries);
	    
	    Writer writer = new FileWriter(filePath);
	    SyndFeedOutput output = new SyndFeedOutput();  
	    output.output(feed, writer);  
	    writer.close();
	}
}

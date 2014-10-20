package cn.com.shanda.aesop.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import cn.com.shanda.aesop.pojo.ResourceItem;
import cn.com.shanda.aesop.pojo.SuggestionIndex;

public interface SuggestDao {

	public abstract void createSuggestText(List<ResourceItem> list) throws IOException,
			SQLException, ClassNotFoundException;

	public abstract void addSuggestText(ResourceItem item) throws IOException, SQLException,
			ClassNotFoundException;

	public abstract void addSuggestText(String text) throws SQLException, ClassNotFoundException;

	public abstract void deleteAll() throws SQLException, ClassNotFoundException;

	public abstract void delete(String text) throws SQLException, ClassNotFoundException;

	public abstract void delete(List<String> list) throws SQLException, ClassNotFoundException;
	
	public abstract List<String> getSuggestion(String input) throws SQLException;

}
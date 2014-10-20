package cn.com.shanda.aesop.blog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.shanda.aesop.blog.bean.FriendBean;
import cn.com.shanda.aesop.blog.util.DB;


public class FriendDao {
	private DB connection = null;

	public FriendDao() {
		connection = new DB();
	}

	// 修改朋友
	public boolean operationFriend(String operation,FriendBean single) {
		String sql="";
		if(operation==null)
			operation="";		
		if(operation.equals("add"))
			sql="insert into tb_friend(friend_name, friend_blog) values('"+single.getName()+"','"+single.getBlog()+"')";
		if(operation.equals("modify"))
			sql="update tb_friend set friend_name='"+single.getName()+ "',friend_blog='"+single.getBlog()+"' where id="+single.getId();
		if(operation.equals("delete"))
			sql="delete from tb_friend where id="+single.getId();
		
		boolean flag=connection.executeUpdate(sql);
		return flag;
	}

	/**
	 * @功能 查询所有好友
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List queryFriend(String type) {
		String sql="";
		if(type==null||type.equals("")||!type.equals("all"))
			sql="select top 5 * from tb_friend order by friend_name DESC";
		else
			sql="select * from tb_friend order by friend_name DESC";
		List list = new ArrayList();
		FriendBean friendBean = null;
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) {
				friendBean = new FriendBean();
				friendBean.setId(rs.getInt(1));
				friendBean.setName(rs.getString(2));
				friendBean.setBlog(rs.getString(3));
				list.add(friendBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @功能 查询某个好友的详细信息
	 */
	public FriendBean queryFriendSingle(int id) {
		FriendBean friendBean = null;
		String sql = "select * from tb_friend where id=" + id;
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) {
				friendBean = new FriendBean();
				friendBean.setId(Integer.valueOf(rs.getString(1)));
				friendBean.setName(rs.getString(2));
				friendBean.setBlog(rs.getString(3));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			connection.closed();
		}
		return friendBean;
	}
	
	public void closeConnection(){
		connection.closed();
	}
	

}

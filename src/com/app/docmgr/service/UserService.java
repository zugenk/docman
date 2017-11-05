package com.app.docmgr.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

import com.app.connection.ConnectionFactory;
import com.app.docmgr.model.Role;
import com.app.docmgr.model.Topic;
import com.app.docmgr.model.User;
import com.app.docmgr.service.base.UserServiceBase;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

public class UserService extends com.app.docmgr.service.base.UserServiceBase{
	private static UserService instance = null;

	public static synchronized UserService getInstance(){
		if(instance == null){
			instance = new UserService();
		}
		return instance;
	}
	
	public static List<String> getRoleNames(User user) {
		List<String> roleNames=new LinkedList<String>();
		for (Iterator iterator = user.getRoles().iterator(); iterator.hasNext();) {
			Role role = (Role) iterator.next();
			roleNames.add(role.getName());
		}
		return roleNames;
	}

	public static List<Long> getFavTopicIds(User user) {
		List<Long> favTopicIds=new LinkedList<Long>();
		for (Iterator iterator = user.getFavoriteTopics().iterator(); iterator.hasNext();) {
			Topic topic = (Topic) iterator.next();
			favTopicIds.add(topic.getId());
		}
		return favTopicIds;
	}
	

	public User get(Long id) throws Exception{
		User user = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try {
			user = (User) session.get(User.class, id);
			if (user != null){
				Hibernate.initialize(user.getRoles());			
				Hibernate.initialize(user.getFavoriteTopics());			
				Hibernate.initialize(user.getUserLevel());			
				Hibernate.initialize(user.getStatus());			
				Hibernate.initialize(user.getOrganization());			
				Hibernate.initialize(user.getSecurityLevel());			
			}
		} catch (ObjectNotFoundException onfe) {
			System.out.println("ObjectNotFoundException: " + this.getClass().getName() + ".get(Long id) \n" + onfe.getMessage());
			onfe.printStackTrace();
		} catch (HibernateException e) {
			System.err.println("Hibernate Exception" + e.getMessage());
			throw new Exception(e);
		} finally {
			if (session != null){
				try {
					session.close();
				} catch (HibernateException he) {
					System.err.println("Hibernate Exception" + he.getMessage());
					throw new Exception(he);
				}
			}
		}
		return user;
	}
	
	public List getPrivilegeList(User user)  throws Exception{
		Session session = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			session = ConnectionFactory.getInstance().getSession();
		   	List privilegeList = new LinkedList();
		   	String[] param=new String[2];
	   		param[0]=user.getId().toString() ;
			String sqlQuery = ApplicationFactory.mergeParam("SELECT DISTINCT(p.name) as privilege FROM privilege p INNER JOIN role_privilege rp ON rp.privilege_id =p.id INNER JOIN user_role ur ON ur.role_id =rp.role_id  WHERE ur.user_id={0}",param);
			ps = session.connection().prepareStatement(sqlQuery);			
			rs = ps.executeQuery();
			while (rs.next()) { 
				privilegeList.add(rs.getString(1));
			}
			return privilegeList;
		}catch (Exception e) {
			System.err.println("Exception" + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception e) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				}catch (Exception e) {
				}
			}
		}
	}
	public boolean hasPrivilege(User user,String privilegeName)  throws Exception{
		Session session = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			session = ConnectionFactory.getInstance().getSession();
		   	//String sqlQuery = "SELECT count(p.id) as privilege FROM privilege p INNER JOIN role_privilege rp ON rp.privilege_id =p.id INNER JOIN user_role ur ON ur.role_id =rp.role_id  WHERE p.name='"+privilege+"' and ur.user_id= "+ user.getId().toString(); 
		   	String[] param=new String[2];
		   		param[0]=privilegeName;
		   		param[1]=user.getId().toString() ;
			String sqlQuery = ApplicationFactory.mergeParam("SELECT count(p.id) as privilege FROM privilege p INNER JOIN role_privilege rp ON rp.privilege_id =p.id INNER JOIN user_role ur ON ur.role_id =rp.role_id  WHERE p.name='{0}' and ur.user_id={1}",param);
			ps = session.connection().prepareStatement(sqlQuery);			
			rs = ps.executeQuery();
			int count=0;
			if (rs.next()) { 
				count=rs.getInt(1);
			}
			return (count>0);
		}catch (Exception e) {
			System.err.println("Exception" + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception e) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
				}catch (Exception e) {
				}
			}
		}
	}   

	
}

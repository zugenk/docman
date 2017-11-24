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
import org.hibernate.Query;
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
	

/*	

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
	*/
	
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

	public PartialList getPartialJoin(String filterParam, String orderParam, int start, int count) throws Exception{
		PartialList result = new PartialList();
		Session session = null;
		try {
			String filter = " WHERE user.status.state='active' and ft. ";
			if(filterParam!=null) filter = filter + filterParam;
			session = ConnectionFactory.getInstance().getSession();
			Query queryCount = session.createQuery("SELECT count(*) FROM com.app.docmgr.model.User user "+filter+" ");
			if(orderParam!=null && orderParam.length()>0) filter = filter + " ORDER BY "+ orderParam;
			Query query = session.createQuery("SELECT user FROM com.app.docmgr.model.User user join user.favoriteTopics ft "+filter+" ");
			result.setTotal((Integer) queryCount.list().iterator().next());
			result.setStart(start);
			result.setCount(count);
			query.setFirstResult(start);
			query.setMaxResults(count);			
			result.addAll(query.list());	
			java.util.Iterator itr = result.iterator();
			while(itr.hasNext()){
				com.app.docmgr.model.User user = (com.app.docmgr.model.User)itr.next();
				Hibernate.initialize(user.getRoles());			
				Hibernate.initialize(user.getFavoriteTopics());			
				Hibernate.initialize(user.getUserLevel());			
				Hibernate.initialize(user.getPosition());			
				Hibernate.initialize(user.getStatus());			
				Hibernate.initialize(user.getOrganization());			
				Hibernate.initialize(user.getSecurityLevel());	              
			    Hibernate.initialize(user.getFavoriteTopics());     
				
			}			
		} catch(HibernateException he) {
			System.out.println("HibernateException: " + this.getClass().getName() + ".getPartialList() \n" + he.getMessage());
			throw new Exception(he);
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch(Exception e) {
					System.out.println("Exception: " + this.getClass().getName() + ".getPartialList() \n" + e.getMessage());
					e.printStackTrace();
				}
			}
		}		
		
		return result;
	}
	
}

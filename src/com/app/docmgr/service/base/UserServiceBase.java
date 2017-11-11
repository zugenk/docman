package com.app.docmgr.service.base;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.*;

import com.app.connection.ConnectionFactory;
import com.app.shared.PartialList;
import com.app.shared.ApplicationFactory;
import com.app.docmgr.model.User;


/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 12-11-2017 00:00:51
 */

	/**
	 * @author Martin
	 * Service Class is dedicated to handle all data access to database
	 */

public class UserServiceBase {
	private static Logger log = Logger.getLogger("com.app.docmgr.service.base.UserServiceBase");	
	// private static UserServiceBase instance = null;

	/**
	 * @author Martin
	 * getInstance is provided for Singleton on this class
	 * It meant that any given time there is only one instance on memory
	 */
	 /*
	public static synchronized UserServiceBase getInstance(){
		if(instance == null){
			instance = new UserServiceBase();
		}
		return instance;
	}
	*/
	
	/**
	 * @author Martin
	 * @param  Long id;
	 * get() is retriever by id, 
	 * Service Class is dedicated to handle all data access to database
	 */
	public User get(Long id) throws Exception{
		User user = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try {
			user = (User) session.get(User.class, id);
			Hibernate.initialize(user.getRoles());			
			Hibernate.initialize(user.getFavoriteTopics());			
			Hibernate.initialize(user.getUserLevel());			
			Hibernate.initialize(user.getPosition());			
			Hibernate.initialize(user.getStatus());			
			Hibernate.initialize(user.getOrganization());			
			Hibernate.initialize(user.getSecurityLevel());			

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

	/**
	 * @author Martin
	 * @filterParam  query;
	 * getBy() returns single object matching the filter 
	 */
	public User getBy(String filterParam) throws Exception{
		User user = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try {
			String filter = " WHERE user.status.state='active'  ";
			if(filterParam!=null) filter = filter + filterParam;					
			Query query = session.createQuery("SELECT user FROM com.app.docmgr.model.User user "+filter+" ");
			user = (com.app.docmgr.model.User) query.uniqueResult();
			if(user!=null) {
				Hibernate.initialize(user.getRoles());			
				Hibernate.initialize(user.getFavoriteTopics());			
				Hibernate.initialize(user.getUserLevel());			
				Hibernate.initialize(user.getPosition());			
				Hibernate.initialize(user.getStatus());			
				Hibernate.initialize(user.getOrganization());			
				Hibernate.initialize(user.getSecurityLevel());			
			}
			return user;
		} catch (HibernateException e) {
			System.err.println("Hibernate Exception" + e.getMessage());
			log.error("FAIL in execute in method getBy()");
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (HibernateException e) {
					System.err.println("Hibernate Exception" + e.getMessage());
					log.error("FAIL in closing session in method getBy()");
					throw new RuntimeException(e);
				}
			}
		}
	}

	public void add(User user) throws Exception {
		Transaction trx = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try	{
			trx = session.beginTransaction();
			session.save(user);
			trx.commit();
		} catch (HibernateException he) {
			try {
				if (trx != null) trx.rollback();
			} catch(Exception e) {
				e.printStackTrace();	
			}
			System.err.println("Hibernate Exception" + he.getMessage());
			throw new Exception(he);
		} finally {
			if (session != null){
				try	{
					session.close();
				} catch (HibernateException he){
					System.err.println("Hibernate Exception" + he.getMessage());
					throw new Exception(he);
				}
			}
		}
	}	

	public void add(User user, Session session) throws Exception {
		Transaction trx = null;
		try	{
			trx = session.beginTransaction();
			session.save(user);
			trx.commit();
		} catch (HibernateException he) {
			try {
				if (trx != null) trx.rollback();
			} catch(Exception e) {
				e.printStackTrace();	
			}
			System.err.println("Hibernate Exception" + he.getMessage());
			throw new Exception(he);
		} finally {
			if (session != null){
				try	{
					session.close();
				} catch (HibernateException he){
					System.err.println("Hibernate Exception" + he.getMessage());
					throw new Exception(he);
				}
			}
		}
	}	
	
	public void update(User user) throws Exception {
		Transaction trx = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try	{
			trx = session.beginTransaction();
			session.saveOrUpdate(user);
			trx.commit();
		} catch (HibernateException he) {
			try {
				if (trx != null) trx.rollback();
			} catch(Exception e) {
				e.printStackTrace();	
			}
			System.err.println("Hibernate Exception" + he.getMessage());
			throw new Exception(he);
		} finally {
			if (session != null){
				try	{
					session.close();
				} catch (HibernateException he){
					System.err.println("Hibernate Exception" + he.getMessage());
					throw new Exception(he);
				}
			}
		}
	}	

	public void update(User user, Session session) throws Exception {
		Transaction trx = null;
		try	{
			trx = session.beginTransaction();
			session.saveOrUpdate(user);
			trx.commit();
		} catch (HibernateException he) {
			try {
				if (trx != null) trx.rollback();
			} catch(Exception e) {
				e.printStackTrace();	
			}
			System.err.println("Hibernate Exception" + he.getMessage());
			throw new Exception(he);
		} finally {
			if (session != null){
				try	{
					session.close();
				} catch (HibernateException he){
					System.err.println("Hibernate Exception" + he.getMessage());
					throw new Exception(he);
				}
			}
		}
	}	
	
	public void delete(User user) throws Exception {
		Transaction trx = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try	{
			trx = session.beginTransaction();
			session.delete(user);
			trx.commit();
		} catch (HibernateException he) {
			try {
				if (trx != null) trx.rollback();
			} catch(Exception e) {
				e.printStackTrace();	
			}
			System.err.println("Hibernate Exception" + he.getMessage());
			throw new Exception(he);
		} finally {
			if (session != null){
				try	{
					session.close();
				} catch (HibernateException he){
					System.err.println("Hibernate Exception" + he.getMessage());
					throw new Exception(he);
				}
			}
		}
	}	

	public void delete(User user, Session session) throws Exception {
		Transaction trx = null;
		try	{
			trx = session.beginTransaction();
			session.delete(user);
			trx.commit();
		} catch (HibernateException he) {
			try {
				if (trx != null) trx.rollback();
			} catch(Exception e) {
				e.printStackTrace();	
			}
			System.err.println("Hibernate Exception" + he.getMessage());
			throw new Exception(he);
		} finally {
			if (session != null){
				try	{
					session.close();
				} catch (HibernateException he){
					System.err.println("Hibernate Exception" + he.getMessage());
					throw new Exception(he);
				}
			}
		}
	}	
	
	public PartialList getPartialList(String filterParam, String orderParam, int start, int count) throws Exception{
		PartialList result = new PartialList();
		Session session = null;
		try {
			String filter = " WHERE user.status.state='active'  ";
			if(filterParam!=null) filter = filter + filterParam;
			session = ConnectionFactory.getInstance().getSession();
			Query queryCount = session.createQuery("SELECT count(*) FROM com.app.docmgr.model.User user "+filter+" ");
			if(orderParam!=null && orderParam.length()>0) filter = filter + " ORDER BY "+ orderParam;
			Query query = session.createQuery("SELECT user FROM com.app.docmgr.model.User user "+filter+" ");
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

	public List getList(String filterParam, String orderParam) throws Exception{
		if(filterParam!=null) filterParam = " and user.status.state='active' "+filterParam;
		else filterParam=" and user.status.state='active' ";
		return getListAll(filterParam,orderParam);
	}
	
	public List getListAll(String filterParam, String orderParam) throws Exception{
		List result = new LinkedList();
		Session session = null;
		try {
			String filter = " WHERE 1=1 ";
			if(filterParam!=null) filter = filter +  filterParam;
			if(orderParam!=null && orderParam.length()>0) filter = filter + " ORDER BY "+ orderParam;
			session = ConnectionFactory.getInstance().getSession();
			Query query = session.createQuery("SELECT user FROM com.app.docmgr.model.User user "+filter+" ");
			result = query.list();
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
			}                       
		} catch(HibernateException he) {
			System.out.println("HibernateException: " + this.getClass().getName() + ".getListAll() \n" + he.getMessage());
			throw new Exception(he);
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch(Exception e) {
					System.out.println("Exception: " + this.getClass().getName() + ".getListAll() \n" + e.getMessage());
					e.printStackTrace();
				}
			}
		}		
		
		return result;
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
			String sqlQuery = ApplicationFactory.mergeParam("${project.queryList.appUser_privilegeList}",param);
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
			String sqlQuery = ApplicationFactory.mergeParam("${project.queryList.appUser_hasPrivilege}",param);
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

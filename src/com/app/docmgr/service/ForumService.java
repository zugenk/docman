package com.app.docmgr.service;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.connection.ConnectionFactory;
import com.app.docmgr.model.Forum;
import com.app.docmgr.service.base.ForumServiceBase;
import com.app.module.basic.BaseUtil;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 14:33:54
 */

public class ForumService extends com.app.docmgr.service.base.ForumServiceBase{
	private static Logger log = Logger.getLogger("com.app.docmgr.service.ForumService");	
	private static ForumService instance = null;

	public static synchronized ForumService getInstance(){
		if(instance == null){
			instance = new ForumService();
		}
		return instance;
	}
	
	private void encryptData(Forum forum){
		if (!BaseUtil.useDbEncryption ||forum==null) return;
		forum.setDescription(ApplicationFactory.encryptData(forum.getDescription()));
	}
	private void decryptData(Forum forum){
		if (!BaseUtil.useDbEncryption ||forum==null) return;
		forum.setDescription(ApplicationFactory.decryptData(forum.getDescription()));
	}
	
	/**
	 * @author Martin
	 * @param  Long id;
	 * get() is retriever by id, 
	 * Service Class is dedicated to handle all data access to database
	 */
	public Forum get(Long id) throws Exception{
		Forum forum = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try {
			forum = (Forum) session.get(Forum.class, id);
			Hibernate.initialize(forum.getStatus());			
			Hibernate.initialize(forum.getForumType());			
			Hibernate.initialize(forum.getParent());	
			decryptData(forum);
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
		return forum;
	}

	/**
	 * @author Martin
	 * @filterParam  query;
	 * getBy() returns single object matching the filter 
	 */
	public Forum getBy(String filterParam) throws Exception{
		Forum forum = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try {
			String filter = " WHERE forum.status.state='active'  ";
			if(filterParam!=null) filter = filter + filterParam;					
			Query query = session.createQuery("SELECT forum FROM com.app.docmgr.model.Forum forum "+filter+" ");
			forum = (com.app.docmgr.model.Forum) query.uniqueResult();
			if(forum!=null) {
				Hibernate.initialize(forum.getStatus());			
				Hibernate.initialize(forum.getForumType());			
				Hibernate.initialize(forum.getParent());	
				decryptData(forum);
			}
			return forum;
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

	public void add(Forum forum) throws Exception {
		Transaction trx = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try	{
			trx = session.beginTransaction();
			encryptData(forum);
			session.save(forum);
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
			decryptData(forum);
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

	public void add(Forum forum, Session session) throws Exception {
		Transaction trx = null;
		try	{
			trx = session.beginTransaction();
			encryptData(forum);
			session.save(forum);
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
			decryptData(forum);
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
	
	public void update(Forum forum) throws Exception {
		Transaction trx = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try	{
			trx = session.beginTransaction();
			encryptData(forum);
			session.saveOrUpdate(forum);
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
			decryptData(forum);
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

	public void update(Forum forum, Session session) throws Exception {
		Transaction trx = null;
		try	{
			trx = session.beginTransaction();
			encryptData(forum);
			session.saveOrUpdate(forum);
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
			decryptData(forum);
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
	
	public void delete(Forum forum) throws Exception {
		Transaction trx = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try	{
			trx = session.beginTransaction();
			session.delete(forum);
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

	public void delete(Forum forum, Session session) throws Exception {
		Transaction trx = null;
		try	{
			trx = session.beginTransaction();
			session.delete(forum);
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
			String filter = " WHERE forum.status.state='active'  ";
			if(filterParam!=null) filter = filter + filterParam;
			session = ConnectionFactory.getInstance().getSession();
			Query queryCount = session.createQuery("SELECT count(*) FROM com.app.docmgr.model.Forum forum "+filter+" ");
			if(orderParam!=null && orderParam.length()>0) filter = filter + " ORDER BY "+ orderParam;
			Query query = session.createQuery("SELECT forum FROM com.app.docmgr.model.Forum forum "+filter+" ");
			result.setTotal((Integer) queryCount.list().iterator().next());
			result.setStart(start);
			result.setCount(count);
			query.setFirstResult(start);
			query.setMaxResults(count);			
			result.addAll(query.list());	
			java.util.Iterator itr = result.iterator();
			while(itr.hasNext()){
				com.app.docmgr.model.Forum forum = (com.app.docmgr.model.Forum)itr.next();
				Hibernate.initialize(forum.getStatus());			
				Hibernate.initialize(forum.getForumType());			
				Hibernate.initialize(forum.getParent());	
				decryptData(forum);
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
		if(filterParam!=null) filterParam = " and forum.status.state='active' "+filterParam;
		else filterParam=" and forum.status.state='active' ";
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
			Query query = session.createQuery("SELECT forum FROM com.app.docmgr.model.Forum forum "+filter+" ");
			result = query.list();
			java.util.Iterator itr = result.iterator();
			while(itr.hasNext()){
			    com.app.docmgr.model.Forum forum = (com.app.docmgr.model.Forum)itr.next();
			    Hibernate.initialize(forum.getStatus());                    
			    Hibernate.initialize(forum.getForumType());                    
			    Hibernate.initialize(forum.getParent());    
			    decryptData(forum);
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
}

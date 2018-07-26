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
import com.app.docmgr.model.SystemParameter;
import com.app.docmgr.model.SystemParameterHistory;
import com.app.docmgr.service.base.SystemParameterHistoryServiceBase;
import com.app.module.basic.BaseUtil;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 18:40:05
 */

public class SystemParameterHistoryService extends com.app.docmgr.service.base.SystemParameterHistoryServiceBase{
	private static SystemParameterHistoryService instance = null;
	private static Logger log = Logger.getLogger("com.app.docmgr.service.SystemParameterHistoryService");	

	public static synchronized SystemParameterHistoryService getInstance(){
		if(instance == null){
			instance = new SystemParameterHistoryService();
		}
		return instance;
	}
	
	private void encryptData(SystemParameterHistory param){
		if (!BaseUtil.useDbEncryption ||param==null) return;
		param.setSvalue(ApplicationFactory.encryptData(param.getSvalue()));
	}
	private void decryptData(SystemParameterHistory param){
		if (!BaseUtil.useDbEncryption ||param==null) return;
		param.setSvalue(ApplicationFactory.decryptData(param.getSvalue()));
	}
	
	/**
	 * @author Martin
	 * @param  Long id;
	 * get() is retriever by id, 
	 * Service Class is dedicated to handle all data access to database
	 */
	public SystemParameterHistory get(Long id) throws Exception{
		SystemParameterHistory systemParameterHistory = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try {
			systemParameterHistory = (SystemParameterHistory) session.get(SystemParameterHistory.class, id);
			Hibernate.initialize(systemParameterHistory.getStatus());			

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
		return systemParameterHistory;
	}

	/**
	 * @author Martin
	 * @filterParam  query;
	 * getBy() returns single object matching the filter 
	 */
	public SystemParameterHistory getBy(String filterParam) throws Exception{
		SystemParameterHistory systemParameterHistory = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try {
			String filter = " WHERE systemParameterHistory.status.state='active'  ";
			if(filterParam!=null) filter = filter + filterParam;					
			Query query = session.createQuery("SELECT systemParameterHistory FROM com.app.docmgr.model.SystemParameterHistory systemParameterHistory "+filter+" ");
			systemParameterHistory = (com.app.docmgr.model.SystemParameterHistory) query.uniqueResult();
			if(systemParameterHistory!=null) {
				Hibernate.initialize(systemParameterHistory.getStatus());			
			}
			return systemParameterHistory;
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

	public void add(SystemParameterHistory systemParameterHistory) throws Exception {
		Transaction trx = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try	{
			trx = session.beginTransaction();
			encryptData(systemParameterHistory);
			session.save(systemParameterHistory);
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
			decryptData(systemParameterHistory);
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

	public void add(SystemParameterHistory systemParameterHistory, Session session) throws Exception {
		Transaction trx = null;
		try	{
			trx = session.beginTransaction();
			encryptData(systemParameterHistory);
			session.save(systemParameterHistory);
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
			decryptData(systemParameterHistory);
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
	
	public void update(SystemParameterHistory systemParameterHistory) throws Exception {
		Transaction trx = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try	{
			trx = session.beginTransaction();
			encryptData(systemParameterHistory);
			session.saveOrUpdate(systemParameterHistory);
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
			decryptData(systemParameterHistory);
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

	public void update(SystemParameterHistory systemParameterHistory, Session session) throws Exception {
		Transaction trx = null;
		try	{
			trx = session.beginTransaction();
			encryptData(systemParameterHistory);
			session.saveOrUpdate(systemParameterHistory);
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
			decryptData(systemParameterHistory);
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
	
	public void delete(SystemParameterHistory systemParameterHistory) throws Exception {
		Transaction trx = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try	{
			trx = session.beginTransaction();
			session.delete(systemParameterHistory);
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

	public void delete(SystemParameterHistory systemParameterHistory, Session session) throws Exception {
		Transaction trx = null;
		try	{
			trx = session.beginTransaction();
			session.delete(systemParameterHistory);
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
			String filter = " WHERE systemParameterHistory.status.state='active'  ";
			if(filterParam!=null) filter = filter + filterParam;
			session = ConnectionFactory.getInstance().getSession();
			Query queryCount = session.createQuery("SELECT count(*) FROM com.app.docmgr.model.SystemParameterHistory systemParameterHistory "+filter+" ");
			if(orderParam!=null && orderParam.length()>0) filter = filter + " ORDER BY "+ orderParam;
			Query query = session.createQuery("SELECT systemParameterHistory FROM com.app.docmgr.model.SystemParameterHistory systemParameterHistory "+filter+" ");
			result.setTotal((Integer) queryCount.list().iterator().next());
			result.setStart(start);
			result.setCount(count);
			query.setFirstResult(start);
			query.setMaxResults(count);			
			result.addAll(query.list());	
			java.util.Iterator itr = result.iterator();
			while(itr.hasNext()){
				com.app.docmgr.model.SystemParameterHistory systemParameterHistory = (com.app.docmgr.model.SystemParameterHistory)itr.next();
				Hibernate.initialize(systemParameterHistory.getStatus());			
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
		if(filterParam!=null) filterParam = " and systemParameterHistory.status.state='active' "+filterParam;
		else filterParam=" and systemParameterHistory.status.state='active' ";
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
			Query query = session.createQuery("SELECT systemParameterHistory FROM com.app.docmgr.model.SystemParameterHistory systemParameterHistory "+filter+" ");
			result = query.list();
			java.util.Iterator itr = result.iterator();
			while(itr.hasNext()){
			    com.app.docmgr.model.SystemParameterHistory systemParameterHistory = (com.app.docmgr.model.SystemParameterHistory)itr.next();
			    Hibernate.initialize(systemParameterHistory.getStatus());                    
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

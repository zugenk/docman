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
import com.app.docmgr.model.DocumentHistory;


/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-04-2018 18:40:05
 */

	/**
	 * @author Martin
	 * Service Class is dedicated to handle all data access to database
	 */

public class DocumentHistoryServiceBase {
	private static Logger log = Logger.getLogger("com.app.docmgr.service.base.DocumentHistoryServiceBase");	
	// private static DocumentHistoryServiceBase instance = null;

	/**
	 * @author Martin
	 * getInstance is provided for Singleton on this class
	 * It meant that any given time there is only one instance on memory
	 */
	 /*
	public static synchronized DocumentHistoryServiceBase getInstance(){
		if(instance == null){
			instance = new DocumentHistoryServiceBase();
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
	public DocumentHistory get(Long id) throws Exception{
		DocumentHistory documentHistory = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try {
			documentHistory = (DocumentHistory) session.get(DocumentHistory.class, id);
			Hibernate.initialize(documentHistory.getSecurityLevel());			
			Hibernate.initialize(documentHistory.getOwner());			
			Hibernate.initialize(documentHistory.getStatus());			
			Hibernate.initialize(documentHistory.getFolder());			
			Hibernate.initialize(documentHistory.getParent());			

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
		return documentHistory;
	}

	/**
	 * @author Martin
	 * @filterParam  query;
	 * getBy() returns single object matching the filter 
	 */
	public DocumentHistory getBy(String filterParam) throws Exception{
		DocumentHistory documentHistory = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try {
			String filter = " WHERE documentHistory.status.state='active'  ";
			if(filterParam!=null) filter = filter + filterParam;					
			Query query = session.createQuery("SELECT documentHistory FROM com.app.docmgr.model.DocumentHistory documentHistory "+filter+" ");
			documentHistory = (com.app.docmgr.model.DocumentHistory) query.uniqueResult();
			if(documentHistory!=null) {
				Hibernate.initialize(documentHistory.getSecurityLevel());			
				Hibernate.initialize(documentHistory.getOwner());			
				Hibernate.initialize(documentHistory.getStatus());			
				Hibernate.initialize(documentHistory.getFolder());			
				Hibernate.initialize(documentHistory.getParent());			
			}
			return documentHistory;
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

	public void add(DocumentHistory documentHistory) throws Exception {
		Transaction trx = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try	{
			trx = session.beginTransaction();
			session.save(documentHistory);
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

	public void add(DocumentHistory documentHistory, Session session) throws Exception {
		Transaction trx = null;
		try	{
			trx = session.beginTransaction();
			session.save(documentHistory);
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
	
	public void update(DocumentHistory documentHistory) throws Exception {
		Transaction trx = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try	{
			trx = session.beginTransaction();
			session.saveOrUpdate(documentHistory);
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

	public void update(DocumentHistory documentHistory, Session session) throws Exception {
		Transaction trx = null;
		try	{
			trx = session.beginTransaction();
			session.saveOrUpdate(documentHistory);
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
	
	public void delete(DocumentHistory documentHistory) throws Exception {
		Transaction trx = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try	{
			trx = session.beginTransaction();
			session.delete(documentHistory);
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

	public void delete(DocumentHistory documentHistory, Session session) throws Exception {
		Transaction trx = null;
		try	{
			trx = session.beginTransaction();
			session.delete(documentHistory);
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
			String filter = " WHERE documentHistory.status.state='active'  ";
			if(filterParam!=null) filter = filter + filterParam;
			session = ConnectionFactory.getInstance().getSession();
			Query queryCount = session.createQuery("SELECT count(*) FROM com.app.docmgr.model.DocumentHistory documentHistory "+filter+" ");
			if(orderParam!=null && orderParam.length()>0) filter = filter + " ORDER BY "+ orderParam;
			Query query = session.createQuery("SELECT documentHistory FROM com.app.docmgr.model.DocumentHistory documentHistory "+filter+" ");
			result.setTotal((Integer) queryCount.list().iterator().next());
			result.setStart(start);
			result.setCount(count);
			query.setFirstResult(start);
			query.setMaxResults(count);			
			result.addAll(query.list());	
			java.util.Iterator itr = result.iterator();
			while(itr.hasNext()){
				com.app.docmgr.model.DocumentHistory documentHistory = (com.app.docmgr.model.DocumentHistory)itr.next();
				Hibernate.initialize(documentHistory.getSecurityLevel());			
				Hibernate.initialize(documentHistory.getOwner());			
				Hibernate.initialize(documentHistory.getStatus());			
				Hibernate.initialize(documentHistory.getFolder());			
				Hibernate.initialize(documentHistory.getParent());			
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
		if(filterParam!=null) filterParam = " and documentHistory.status.state='active' "+filterParam;
		else filterParam=" and documentHistory.status.state='active' ";
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
			Query query = session.createQuery("SELECT documentHistory FROM com.app.docmgr.model.DocumentHistory documentHistory "+filter+" ");
			result = query.list();
			java.util.Iterator itr = result.iterator();
			while(itr.hasNext()){
			    com.app.docmgr.model.DocumentHistory documentHistory = (com.app.docmgr.model.DocumentHistory)itr.next();
			    Hibernate.initialize(documentHistory.getSecurityLevel());                    
			    Hibernate.initialize(documentHistory.getOwner());                    
			    Hibernate.initialize(documentHistory.getStatus());                    
			    Hibernate.initialize(documentHistory.getFolder());                    
			    Hibernate.initialize(documentHistory.getParent());                    
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

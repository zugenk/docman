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
import com.app.docmgr.model.Folder;


/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 07-10-2017 06:18:15
 */

	/**
	 * @author Martin
	 * Service Class is dedicated to handle all data access to database
	 */

public class FolderServiceBase {
	private static Logger log = Logger.getLogger("com.app.docmgr.service.base.FolderServiceBase");	
	// private static FolderServiceBase instance = null;

	/**
	 * @author Martin
	 * getInstance is provided for Singleton on this class
	 * It meant that any given time there is only one instance on memory
	 */
	 /*
	public static synchronized FolderServiceBase getInstance(){
		if(instance == null){
			instance = new FolderServiceBase();
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
	public Folder get(Long id) throws Exception{
		Folder folder = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try {
			folder = (Folder) session.get(Folder.class, id);
			Hibernate.initialize(folder.getFolderType());			
			Hibernate.initialize(folder.getParentFolder());			

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
		return folder;
	}

	/**
	 * @author Martin
	 * @filterParam  query;
	 * getBy() returns single object matching the filter 
	 */
	public Folder getBy(String filterParam) throws Exception{
		Folder folder = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try {
			String filter = " WHERE 1=1 ";
			if(filterParam!=null) filter = filter + filterParam;					
			Query query = session.createQuery("SELECT folder FROM com.app.docmgr.model.Folder folder "+filter+" ");
			folder = (com.app.docmgr.model.Folder) query.uniqueResult();
			if(folder!=null) {
				Hibernate.initialize(folder.getFolderType());			
				Hibernate.initialize(folder.getParentFolder());			
			}
			return folder;
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

	public void add(Folder folder) throws Exception {
		Transaction trx = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try	{
			trx = session.beginTransaction();
			session.save(folder);
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

	public void add(Folder folder, Session session) throws Exception {
		Transaction trx = null;
		try	{
			trx = session.beginTransaction();
			session.save(folder);
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
	
	public void update(Folder folder) throws Exception {
		Transaction trx = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try	{
			trx = session.beginTransaction();
			session.saveOrUpdate(folder);
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

	public void update(Folder folder, Session session) throws Exception {
		Transaction trx = null;
		try	{
			trx = session.beginTransaction();
			session.saveOrUpdate(folder);
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
	
	public void delete(Folder folder) throws Exception {
		Transaction trx = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try	{
			trx = session.beginTransaction();
			session.delete(folder);
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

	public void delete(Folder folder, Session session) throws Exception {
		Transaction trx = null;
		try	{
			trx = session.beginTransaction();
			session.delete(folder);
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
			String filter = " WHERE 1=1 ";
			if(filterParam!=null) filter = filter + filterParam;
			if(orderParam!=null && orderParam.length()>0) filter = filter + " ORDER BY "+ orderParam;
			session = ConnectionFactory.getInstance().getSession();
			Query queryCount = session.createQuery("SELECT count(*) FROM com.app.docmgr.model.Folder folder "+filter+" ");
			Query query = session.createQuery("SELECT folder FROM com.app.docmgr.model.Folder folder "+filter+" ");
			result.setTotal((Integer) queryCount.list().iterator().next());
			result.setStart(start);
			result.setCount(count);
			query.setFirstResult(start);
			query.setMaxResults(count);			
			result.addAll(query.list());	
			java.util.Iterator itr = result.iterator();
			while(itr.hasNext()){
				com.app.docmgr.model.Folder folder = (com.app.docmgr.model.Folder)itr.next();
				Hibernate.initialize(folder.getFolderType());			
				Hibernate.initialize(folder.getParentFolder());			
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
			Query query = session.createQuery("SELECT folder FROM com.app.docmgr.model.Folder folder "+filter+" ");
			result = query.list();
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

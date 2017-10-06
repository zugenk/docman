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
import com.app.docmgr.model.Status;


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

public class StatusServiceBase {
	private static Logger log = Logger.getLogger("com.app.docmgr.service.base.StatusServiceBase");	
	// private static StatusServiceBase instance = null;

	/**
	 * @author Martin
	 * getInstance is provided for Singleton on this class
	 * It meant that any given time there is only one instance on memory
	 */
	 /*
	public static synchronized StatusServiceBase getInstance(){
		if(instance == null){
			instance = new StatusServiceBase();
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
	public Status get(Long id) throws Exception{
		Status status = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try {
			status = (Status) session.get(Status.class, id);

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
		return status;
	}

	/**
	 * @author Martin
	 * @filterParam  query;
	 * getBy() returns single object matching the filter 
	 */
	public Status getBy(String filterParam) throws Exception{
		Status status = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try {
			String filter = " WHERE 1=1 ";
			if(filterParam!=null) filter = filter + filterParam;					
			Query query = session.createQuery("SELECT status FROM com.app.docmgr.model.Status status "+filter+" ");
			status = (com.app.docmgr.model.Status) query.uniqueResult();
			return status;
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

	public void add(Status status) throws Exception {
		Transaction trx = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try	{
			trx = session.beginTransaction();
			session.save(status);
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

	public void add(Status status, Session session) throws Exception {
		Transaction trx = null;
		try	{
			trx = session.beginTransaction();
			session.save(status);
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
	
	public void update(Status status) throws Exception {
		Transaction trx = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try	{
			trx = session.beginTransaction();
			session.saveOrUpdate(status);
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

	public void update(Status status, Session session) throws Exception {
		Transaction trx = null;
		try	{
			trx = session.beginTransaction();
			session.saveOrUpdate(status);
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
	
	public void delete(Status status) throws Exception {
		Transaction trx = null;
		Session session = ConnectionFactory.getInstance().getSession();
		try	{
			trx = session.beginTransaction();
			session.delete(status);
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

	public void delete(Status status, Session session) throws Exception {
		Transaction trx = null;
		try	{
			trx = session.beginTransaction();
			session.delete(status);
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
			Query queryCount = session.createQuery("SELECT count(*) FROM com.app.docmgr.model.Status status "+filter+" ");
			Query query = session.createQuery("SELECT status FROM com.app.docmgr.model.Status status "+filter+" ");
			result.setTotal((Integer) queryCount.list().iterator().next());
			result.setStart(start);
			result.setCount(count);
			query.setFirstResult(start);
			query.setMaxResults(count);			
			result.addAll(query.list());	
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
			Query query = session.createQuery("SELECT status FROM com.app.docmgr.model.Status status "+filter+" ");
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

	/**
	 * @author Martin
	 * @param  String type
	 * @return List
	 * findByType returns list of objects having type matching with the parameter.
	 */
	public List findbyType(String type) throws Exception{
		String filterParam = " and status.type = '"+type+"' ";
		return getList(filterParam,null);	
	}

	 /**
	 * @author Martin
	 * @param  String type
	 * @param  String code
	 * @return com.app.docmgr.model.Status
	 * getByTypeandCode returns com.app.docmgr.model.Status object having type 
	 * and code matching with the parameter specified.
	 */
	public Status getByTypeandCode(String type, String code) throws Exception {
		String filterParam = " and status.type = '"+type+"' and status.code = '"+code+"'";
		return getBy(filterParam);	
	}

	

}

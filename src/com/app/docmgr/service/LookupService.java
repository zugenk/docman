package com.app.docmgr.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.app.connection.ConnectionFactory;
import com.app.shared.PartialList;
import com.app.shared.ApplicationFactory;
import com.app.docmgr.model.Lookup;
import com.app.docmgr.service.base.LookupServiceBase;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

public class LookupService extends com.app.docmgr.service.base.LookupServiceBase{
	private static Logger log = Logger.getLogger("com.app.docmgr.service.LookupService");	
	private static LookupService instance = null;

	public static synchronized LookupService getInstance(){
		if(instance == null){
			instance = new LookupService();
		}
		return instance;
	}
	
	public List findbyType(String type) {
		Session session = ConnectionFactory.getInstance().getSession();
		
		try {
			log.debug("===========FIND  LOOKUP BY TYPE FROM DB");
			String select = "from com.app.docmgr.model.Lookup C"
					+ " where C.type = '" + type + "' and C.status.code='active' order by C.priority";

			return session.createQuery(select).list();
		} catch (HibernateException e) {
			log.error("Hibernate Exception" + e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (HibernateException e) {
					log.warn("Hibernate Exception" + e.getMessage(), e);
				}
			}
		}
	}

	public List findbyTypeWithFilter(String type, String filter) {

		Session session = ConnectionFactory.getInstance().getSession();

		try {
			String select = "from com.app.docmgr.model.Lookup C"
					+ " where C.type = '" + type + "' " + filter;

			return session.createQuery(select).list();
		} catch (HibernateException e) {
			log.error("Hibernate Exception" + e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (HibernateException e) {
					log.warn("Hibernate Exception" + e.getMessage(), e);
				}
			}
		}
	}	
}

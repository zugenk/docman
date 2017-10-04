package com.app.connection;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.apache.log4j.*;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */


public class ConnectionFactory {
	private static Logger log = Logger.getLogger("com.app.connection.ConnectionFactory");	
	private static ConnectionFactory instance = null;
	private SessionFactory sessionFactory = null;
	
	
	private ConnectionFactory(){
		try{			
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");			
			sessionFactory = cfg.buildSessionFactory();
		}catch(MappingException me){
			me.printStackTrace();
		}catch(HibernateException he){
			he.printStackTrace();
		}
	}
	
	public static synchronized ConnectionFactory getInstance(){
		if(instance == null){
			instance = new ConnectionFactory();
		}
		return instance;
	}

	public Session getSession()
	{
		try
		{
			Session s = sessionFactory.openSession();
			return s;
		}
		catch (HibernateException e)
		{
			System.err.println("Hibernate Exception" + e.getMessage());
			throw new RuntimeException(e);
		}
	}
}

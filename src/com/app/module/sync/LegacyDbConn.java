package com.app.module.sync;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.hibernate.Session;

import com.app.module.basic.OrganizationManager;

public class LegacyDbConn {
	private static Logger log = Logger.getLogger(LegacyDbConn.class);
	
	public static List<Document> getList(String qName,String sqlQuery)  throws Exception{
		Session session = null;
		PreparedStatement ps;
		ResultSet rs=null;
		Connection conn = null;
		try {
			List resultList = new LinkedList();
			log.debug("Executing ["+qName+"] :>> ["+sqlQuery+"] ");
//			String query; 
//			if (params!=null && params.length>0) query=ApplicationFactory.mergeParam(sqlQuery,params);
//			else query= sqlQuery;
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://hostname:port/dbname","username", "password");
			ps = conn.prepareStatement(sqlQuery);			
//			if(params!=null && params.length>0) {
//				for (int i = 0; i < params.length; i++) {
//					ps.setString(i+1, params[i]);
//				}
//			}
			rs = ps.executeQuery();
			ResultSetMetaData meta= rs.getMetaData();
			ps.clearParameters();
			Map<String, Object> row;
			String[] keys=new String[meta.getColumnCount()];
			for (int i = 0; i < keys.length; i++) {
				keys[i]=meta.getColumnLabel(i+1);
			}
			while (rs.next()) { 
				row=new Document();
				for (int i = 0; i < keys.length; i++) {
					row.put(keys[i], rs.getObject(i+1));
				}	
				resultList.add(row);
			}
			return resultList;
		}catch (Exception e) {
			log.error("Exception while running query " + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception e) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				}catch (Exception e) {
				}
			} 	
		}
	}   
	
	
}

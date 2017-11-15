package com.app.module.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.app.connection.ConnectionFactory;
import com.app.docmgr.model.User;
import com.app.shared.ApplicationFactory;

public class DBQueryManager {
//	static Map<String, PreparedStatement> PS_MAP=new HashMap<String, PreparedStatement>(); 
//	
//	private static PreparedStatement getPS(String qName,Connection conn, String sqlQuery) throws Exception {
//		if(!PS_MAP.containsKey(qName))  PS_MAP.put(qName, conn.prepareStatement(sqlQuery));
//		return PS_MAP.get(qName);
//	}
	
	public static List<Map<String, Object>> getList(String qName,String sqlQuery,String... params)  throws Exception{
		Session session = null;
		PreparedStatement ps;
		ResultSet rs=null;
		try {
			List resultList = new LinkedList();
			session = ConnectionFactory.getInstance().getSession();
			System.out.println("Executing ["+qName+"] :>> ["+sqlQuery+"] ");
//			String query; 
//			if (params!=null && params.length>0) query=ApplicationFactory.mergeParam(sqlQuery,params);
//			else query= sqlQuery;
			ps = session.connection().prepareStatement(sqlQuery);			
			//ps=getPS(qName, session.connection(), sqlQuery);
			if(params!=null && params.length>0) {
				for (int i = 0; i < params.length; i++) {
					ps.setString(i+1, params[i]);
				}
			}
			rs = ps.executeQuery();
			ResultSetMetaData meta= rs.getMetaData();
			ps.clearParameters();
			Map<String, Object> row;
			String[] keys=new String[meta.getColumnCount()];
			for (int i = 0; i < keys.length; i++) {
				keys[i]=meta.getColumnLabel(i+1);
			}
			while (rs.next()) { 
				row=new HashMap<String, Object>();
				for (int i = 0; i < keys.length; i++) {
					row.put(keys[i], rs.getObject(i+1));
				}	
				resultList.add(row);
			}
			return resultList;
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
			if (rs != null) {
				try {
					rs.close();
				}catch (Exception e) {
				}
			} 	
		}
	}   
}

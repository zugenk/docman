package com.app.module.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.app.connection.ConnectionFactory;
import com.app.docmgr.model.User;
import com.app.module.report.StatisticManager;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;

public class DBQueryManager extends BaseUtil{
	private static Logger log = Logger.getLogger(DBQueryManager.class);
//	static Map<String, PreparedStatement> PS_MAP=new HashMap<String, PreparedStatement>(); 
//	
//	private static PreparedStatement getPS(String qName,Connection conn, String sqlQuery) throws Exception {
//		if(!PS_MAP.containsKey(qName))  PS_MAP.put(qName, conn.prepareStatement(sqlQuery));
//		return PS_MAP.get(qName);
//	}
	
	public static List getList(String qName,String sqlQuery,String... params)  throws Exception{
		Session session = null;
		PreparedStatement ps;
		ResultSet rs=null;
		try {
			List resultList = new LinkedList();
			session = ConnectionFactory.getInstance().getSession();
			log.debug("Executing ["+qName+"] :>> ["+sqlQuery+"] ");
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
	
	public static List getPlainList(String qName,String sqlQuery,String... params)  throws Exception{
		Session session = null;
		PreparedStatement ps;
		ResultSet rs=null;
		try {
			List resultList = new LinkedList();
			session = ConnectionFactory.getInstance().getSession();
			log.debug("Executing ["+qName+"] :>> ["+sqlQuery+"] ");
			ps = session.connection().prepareStatement(sqlQuery);			
			if(params!=null && params.length>0) {
				for (int i = 0; i < params.length; i++) {
					ps.setString(i+1, params[i]);
				}
			}
			rs = ps.executeQuery();
			ResultSetMetaData meta= rs.getMetaData();
			ps.clearParameters();
			String row="";
			String[] keys=new String[meta.getColumnCount()];
			for (int i = 0; i < keys.length; i++) {
				keys[i]=meta.getColumnLabel(i+1);
				row+=(i>0?"|":"")+keys[i];
			}
			resultList.add(row);
			while (rs.next()) { 
				row="";
				for (int i = 0; i < keys.length; i++) {
					row+=(i>0?"|":"")+rs.getObject(i+1);
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
	
	public static PartialList getPartialList(String qName,String sqlQuery,int start,String... params)  throws Exception{
		return getPartialList(qName,sqlQuery,start,ITEM_PER_PAGE,params);
	}		
	public static PartialList getPartialList(String qName,String sqlQuery,int start,int pageSize,String... params)  throws Exception{
		Session session = null;
		PreparedStatement ps;
		ResultSet rs=null;
		PartialList resultList=new PartialList();
		try {
			session = ConnectionFactory.getInstance().getSession();
			resultList.setTotal(-1);
			String countSql=createCountQuery(sqlQuery);
			if(countSql!=null){
				ps=session.connection().prepareStatement(countSql);
				rs=ps.executeQuery();
				if(rs.next()) resultList.setTotal(rs.getInt(1));
				log.debug("CountQuery ["+countSql+"] = "+rs.getInt(1));
			} 
			resultList.setStart(start);
			resultList.setCount(ITEM_PER_PAGE);
			String paging=" offset "+start+" limit "+ITEM_PER_PAGE+" ";
			log.debug("Executing ["+qName+"] :>> ["+sqlQuery+paging+"] ");
//			String query; 
//			if (params!=null && params.length>0) query=ApplicationFactory.mergeParam(sqlQuery,params);
//			else query= sqlQuery;
			ps = session.connection().prepareStatement(sqlQuery+paging);			
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
	
	public static void main(String[] args) {
		String sqlQuery="select done_by, entity, to_char(date_trunc('day', audit_time),'DD-MM-YYYY') AS period, count(id) as Freq FROM audit_trail "+
				" WHERE audit_time > now() - interval '1 week' "+
				" group by 1,2,3 ";
		log.debug(createCountQuery(sqlQuery) );
	}
	
	public static String createCountQuery(String sqlQuery) {
		if (nvl(sqlQuery)) return null;
		sqlQuery=sqlQuery.replaceFirst("(?i)from", "from");
		int f=sqlQuery.indexOf("from");
		int x=sqlQuery.indexOf(",");
		if (f<=0 || x<=0) return null;
		int j=sqlQuery.indexOf(",",x+1);
		while(j<f) {
			x=j;
			j=sqlQuery.indexOf(",",x+1);
		}
		String countQuery="select count(*) from ("+sqlQuery.substring(0,x)+" "+sqlQuery.substring(f)+") as rpt";
		//log.debug(countQuery);
		return countQuery;
	}
	
	
	public static int executeUpdate(String sqlQuery) throws Exception{
		if (nvl(sqlQuery) && sqlQuery.toLowerCase().startsWith("update ")) throw new Exception("Invalid sql query or not an update query ");
		Session session = null;
		PreparedStatement ps=null;
		try {
			List resultList = new LinkedList();
			session = ConnectionFactory.getInstance().getSession();
			log.debug("Executing Update query :>> ["+sqlQuery+"] ");
			ps = session.connection().prepareStatement(sqlQuery);	
			return ps.executeUpdate();
		}catch (Exception e) {
			log.error("Exception during executing update " + e.getMessage());
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
	
	public static Map<String,Object> getFirst(String qName,String sqlQuery,String... params) throws Exception{
		Session session = null;
		PreparedStatement ps;
		ResultSet rs=null;
		Map<String, Object> row=new HashMap<String, Object>();
		
		try {
			List resultList = new LinkedList();
			session = ConnectionFactory.getInstance().getSession();
			log.debug("Executing ["+qName+"] :>> ["+sqlQuery+"] ");
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
			String[] keys=new String[meta.getColumnCount()];
			for (int i = 0; i < keys.length; i++) {
				keys[i]=meta.getColumnLabel(i+1);
			}
			if (rs.next()) { 
//				row=new HashMap<String, Object>();
				for (int i = 0; i < keys.length; i++) {
					row.put(keys[i], rs.getObject(i+1));
				}	
				return row;
				//resultList.add(row);
			}
			// resultList;
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
		return row;
	}   
	
	
	public static Map<String, Object> decrypt(Map<String, Object> row, String[] keys){
		if (row!=null && !row.isEmpty()) {
			for (int i = 0; i < keys.length; i++) {
				if (row.get(keys[i])!=null) {
					try {
						row.put(keys[i], ApplicationFactory.decryptData((String) row.get(keys[i])));
					} catch (Exception e) {
					}
				}
			}
		}
		return row;
	}
	
	
	public static List decryptList(List list,String[] keys) {
		if(list!=null && !list.isEmpty() && keys.length>0) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map<String, Object> row = (Map<String, Object>) iterator.next();
				decrypt(row,keys);
			}
		}
		return list;
	}
	
	public static PartialList decryptList(PartialList plist, String[] keys) {
		if(plist!=null && !plist.isEmpty() && keys!=null && keys.length>0) {
			for (Iterator iterator = plist.iterator(); iterator.hasNext();) {
				Map<String, Object> row = (Map<String, Object>) iterator.next();
				decrypt(row,keys);
			}
		}	
		return plist;
	}
	
}

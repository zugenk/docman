package com.app.module.basic;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.app.docmgr.model.Lookup;
import com.app.docmgr.model.Organization;
import com.app.docmgr.model.Status;
import com.app.docmgr.model.Bookmark;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.OrganizationService;
import com.app.docmgr.service.StatusService;
import com.app.docmgr.service.UserService;
import com.app.docmgr.service.BookmarkService;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;

public class BookmarkManager extends BaseUtil{
	private static Logger log = Logger.getLogger(BookmarkManager.class);
	
	public static Document create(Document passport,Map<String, Object> data) throws Exception {
		//log.debug("Create Bookmark :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		Bookmark obj= new Bookmark();
		updateFromMap(obj, data,errors);
		obj.setCreatedBy(passport.getString("loginName"));
		obj.setCreatedDate(new Date());
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Bookmark", "new"));
		obj.setOwner(UserService.getInstance().get(passport.getLong("userId")));
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		BookmarkService.getInstance().add(obj);
		return toDocument(obj);
	}
	
	public static Document update(Document passport,Map data,String objId) throws Exception{
		//log.debug("Create Bookmark :/n/r"+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		long uid=Long.parseLong(objId);
		Bookmark obj= BookmarkService.getInstance().get(uid);
		if (obj==null) throw new Exception("error.object.notfound");
		updateFromMap(obj,data,errors) ;
		obj.setLastUpdatedBy(passport.getString("loginName"));
		obj.setLastUpdatedDate(new Date());
		//obj.setStatus(StatusService.getInstance().getByTypeandCode("Bookmark", "new"));
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		BookmarkService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static void delete(Document passport,String objId) throws Exception {
		log.debug("Deleting obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		Bookmark obj=BookmarkService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Bookmark", "deleted"));
		obj.setLastUpdatedDate(new Date());
		obj.setLastUpdatedBy(passport.getString("loginName"));
		BookmarkService.getInstance().update(obj);
	}

	public static Document read(Document passport,String objId) throws Exception {
		log.debug("Read obj["+objId+" "+passport.getString("loginName"));
		long usrId= Long.parseLong(objId);
		Bookmark obj=BookmarkService.getInstance().get(usrId);
		if (obj==null) throw new Exception("error.object.notfound");
		return toDocument(obj);
	}
	
	public static PartialList list(Document passport,Map data) throws Exception{
		String filterParam=null;
		String orderParam=null;
		int start=0;
		if(data!=null && !data.isEmpty()) {
			try {
				start= Integer.parseInt((String) data.get("start"));
			} catch (Exception e) {
				start=0;
			}
			
			Map filterMap= (Map) data.get("filter");
			if (filterMap!=null && !filterMap.isEmpty()) {
				StringBuffer filterBuff=new StringBuffer("");
				for (Iterator iterator = filterMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					filterBuff.append(" AND bookmark."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
				}
				filterParam=filterBuff.toString();
			}
			
			Map orderMap= (Map) data.get("orderBy");
			if (orderMap!=null && !orderMap.isEmpty()) {
				for (Iterator iterator = orderMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					if(orderParam==null) orderParam=" bookmark."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
					else orderParam+=", bookmark."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
				}
			}
		}
		PartialList result=BookmarkService.getInstance().getPartialList((filterParam!=null?filterParam.toString():null), orderParam, start, itemPerPage);
		toDocList(result);
		return result;
	}
	
	public static PartialList listByOwner(Document passport,int start) throws Exception{
		String filterParam=" AND bookmark.owner='"+passport.getString("userId")+"' ";
		String orderParam=" bookmark.category ASC, bookmark.name ASC ";
		PartialList result=BookmarkService.getInstance().getPartialList(filterParam, orderParam, start, itemPerPage);
		toDocList(result);
		return result;
	}
	
	
	private static void updateFromMap(Bookmark obj, Map data,List<String> errors) {
		obj.setCategory((String) data.get("category"));
		obj.setName((String) data.get("name"));
		obj.setNote((String) data.get("note"));
		obj.setUrl((String) data.get("url"));
		
		if(!nvl(data.get("bookmarkTypeId"))){
			try {
				Lookup bookmarkType= LookupService.getInstance().get(toLong(data.get("bookmarkTypeId")));
				if(bookmarkType!=null) obj.setBookmarkType(bookmarkType);
			} catch (Exception e) {
				errors.add("error.invalid.bookmarkType");
			}
		}
		if(!nvl(data.get("statusId"))){
			try {
				long statusId=Long.parseLong((String)data.get("statusId"));
				Status status= StatusService.getInstance().get(toLong(data.get("statusId")));
				if(status!=null) obj.setStatus(status);
			} catch (Exception e) {
				errors.add("error.invalid.status");
			}
		}
	}
	
	public static Document toDocument(Bookmark obj) {
		Document doc=new Document();
		doc.append("modelClass", obj.getClass().getName());
		doc.append("category", obj.getCategory());
		doc.append("id", obj.getId());
		doc.append("name", obj.getName());
		doc.append("note", obj.getNote());
		doc.append("url", obj.getUrl());
		doc.append("owner", obj.getOwner().getLoginName());
		if(obj.getBookmarkType()!=null) {
			doc.append("bookmarkType", obj.getBookmarkType().getName());
			doc.append("bookmarkTypeId", obj.getBookmarkType().getId());
		}
		if(obj.getStatus()!=null){
			doc.append("status", obj.getStatus().getName());
			doc.append("statusId",obj.getStatus().getId());
		}
		return doc;
	}
	
	public static void toDocList(List list){
		//for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		if(list.isEmpty()) return;
		for (int i = 0; i < list.size(); i++) {
			Bookmark obj = (Bookmark) list.get(i);
			list.set(i, toDocument(obj));
		}
	}

}
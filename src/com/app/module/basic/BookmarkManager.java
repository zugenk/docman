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
import com.simas.webservice.Utility;

public class BookmarkManager extends BaseUtil{
	private static Logger log = Logger.getLogger(BookmarkManager.class);
	private static String ACL_MODE="PRIVATE";
	
	public static Document create(Document passport,Map<String, Object> data) throws Exception {
		//log.debug("Creating Bookmark by "+passport.getString("loginName"));
		//log.trace("data="+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		Bookmark obj= new Bookmark();
		updateFromMap(obj, data,errors);
		obj.setCreatedBy(passport.getString("loginName"));
		obj.setCreatedDate(new Date());
		obj.setLastUpdatedBy(obj.getCreatedBy());
		obj.setLastUpdatedDate(obj.getCreatedDate());
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_CREATE, null, toDocument(obj));
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Bookmark", "new"));
		obj.setOwner(UserService.getInstance().get(passport.getLong("userId")));
		checkValidity(obj, errors);
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		BookmarkService.getInstance().add(obj);
		return toDocument(obj);
	}
	
	
	
	public static Document update(Document passport,Map data,String objId) throws Exception{
//		log.debug("Updating Bookmark["+objId+"] by "+passport.getString("loginName"));
//		log.trace("data="+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		Bookmark obj= BookmarkService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, null, toDocument(obj));
		updateFromMap(obj,data,errors) ;
		obj.setLastUpdatedBy(passport.getString("loginName"));
		obj.setLastUpdatedDate(new Date());
		checkValidity(obj, errors);
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		BookmarkService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static void delete(Document passport,String objId) throws Exception {
//		log.debug("Deleting Bookmark["+objId+"] by "+passport.getString("loginName"));
		Bookmark obj=BookmarkService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DELETE, null, toDocument(obj));
		obj.setStatus(StatusService.getInstance().getByTypeandCode("Bookmark", "deleted"));
		obj.setLastUpdatedDate(new Date());
		obj.setLastUpdatedBy(passport.getString("loginName"));
		BookmarkService.getInstance().update(obj);
	}

/*	private static boolean isOwner(Bookmark obj,org.bson.Document passport) {
		if(!nvl(obj.getOwner())) return passport.getLong("userId")==obj.getOwner().getId();
		return passport.getString("loginName").equals(obj.getCreatedBy());
	}
*/	
	public static Document detail(Document passport,String objId) throws Exception {
//		log.debug("Detail Bookmark["+objId+"] by "+passport.getString("loginName"));
		Bookmark obj=BookmarkService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DETAIL, null, toDocument(obj));
		return toDocument(obj);
	}
	
	public static List list(Document passport,Map data) throws Exception{
//		log.debug("List/Search Bookmark by "+passport.getString("loginName"));
//		log.trace("data="+Utility.debug(data));
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, null, new Document("modelClass","Bookmark"));
		String filterParam=null;
		if("PRIVATE".equals(ACL_MODE) && !isAdmin(passport)) filterParam+=" AND bookmark.owner.id='"+passport.getLong("userId")+"' ";
		String orderParam=null;
		int start=defaulStart;
		String mode=null;
		if(data!=null && !data.isEmpty()) {
			mode=(String)data.get("mode");
			start= toInt(data.get("start"),defaulStart);
			Map filterMap= (Map) data.get("filter");
			if (filterMap!=null && !filterMap.isEmpty()) {
				StringBuffer filterBuff=new StringBuffer("");
				for (Iterator iterator = filterMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					filterBuff.append(constructQuery("bookmark",key,filterMap.get(key))); //filterBuff.append(" AND bookmark."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
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
		if("ALL".equals(mode)){
			List result=BookmarkService.getInstance().getListAll((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}
		if("NOPAGE".equals(mode)){
			List result=BookmarkService.getInstance().getList((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}	
		PartialList result=BookmarkService.getInstance().getPartialList((filterParam!=null?filterParam.toString():null), orderParam, start, ITEM_PER_PAGE);
		toDocList(result);
		return result;
	}
	
	public static PartialList listByOwner(Document passport,String start) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, "listByOwner", new Document("modelClass","Bookmark"));
		log.debug("ListByOwner Bookmark by "+passport.getString("loginName"));
		String filterParam=" AND bookmark.owner.id='"+passport.getLong("userId")+"' ";
		String orderParam=" bookmark.category ASC, bookmark.name ASC ";
		PartialList result=BookmarkService.getInstance().getPartialList(filterParam, orderParam, toInt(start,defaulStart), ITEM_PER_PAGE);
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
				Status status= StatusService.getInstance().get(toLong(data.get("statusId")));
				if(status!=null) obj.setStatus(status);
			} catch (Exception e) {
				errors.add("error.invalid.status");
			}
		}
	}
	
	public static Document toDocument(Bookmark obj) {
		Document doc=new Document();
		doc.append("modelClass", obj.getClass().getSimpleName());
		doc.append("id", obj.getId());
		doc.append("createdBy", obj.getCreatedBy());
		doc.append("category", obj.getCategory());
		doc.append("name", obj.getName());
		doc.append("note", obj.getNote());
		doc.append("url", obj.getUrl());
		if(obj.getOwner()!=null){
			doc.append("owner", obj.getOwner().getLoginName());
			doc.append("ownerId", obj.getOwner().getId());
		}
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
	
	public static void checkValidity(Bookmark obj,List errors) {
		//if (obj.getBookmarkType()==null) errors.add("error.bookmarkType.null");
		if (obj.getUrl()==null) errors.add("error.url.null");
		if (obj.getOwner()==null) errors.add("error.owner.null");
		if (obj.getName()==null) errors.add("error.name.null");
	}
	

}
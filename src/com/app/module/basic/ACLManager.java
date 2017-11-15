package com.app.module.basic;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.TargetDataLine;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.app.docmgr.model.AuditTrail;
import com.app.docmgr.model.Bookmark;
import com.app.docmgr.model.Forum;
import com.app.docmgr.model.Organization;
import com.app.docmgr.model.Topic;
import com.app.docmgr.model.User;
import com.app.docmgr.service.OrganizationService;
import com.app.docmgr.service.SharedDocumentService;
import com.app.docmgr.service.UserService;
import com.app.module.security.AuditTrailManager;

public class ACLManager extends BaseUtil{
	public static String DOCMAN_PACKAGE="com.app.docmgr.model";
	public static String ACTION_CREATE="create";
	public static String ACTION_DETAIL="detail";
	public static String ACTION_UPDATE="update";
	public static String ACTION_DELETE="delete";
	public static String ACTION_LIST="list";
	public static String ACTION_TREE="tree";
	public static String ACTION_SEARCH="search";
	public static String ACTION_SHARE="share";
	
	
	/*
	static List<String> actionList=null;
	static boolean inited=false;
	public static void init(){
		if (inited) return;
		actionList=new LinkedList<String>();
		actionList.add("READ");
		actionList.add("UPDATE");
		actionList.add("DELETE");
		actionList.add("CREATE");
		actionList.add("LIST");
		actionList.add("SEARCH");
		actionList.add("SHARE");
//		actionList.add("");
//		actionList.add("");
	}
	
	
	public static boolean isAuthorized(String toAction,Object targetObj,User actor){
		//todo: Need to add accessType to all object (Public/Private/Confidential
		//Target object: Topic,
		if (targetObj instanceof Topic) {
			Topic t=(Topic) targetObj;
			t.getCreatedBy();
		}
		if (targetObj instanceof Document) {
			Document d=(Document) targetObj;
			if (d.getSecurityLevel() == null) {
				//means its publicly accessible;
				
				return true;
			} else if ("PRIVATE".equals(d.getSecurityLevel().getCode())) {
				//means its publicly accessible;
				
				d.getOwner();
			} else if ((d.getSecurityLevel().getCode()).startsWith("LEVEL-") && (actor.getSecurityLevel().getCode()).startsWith("LEVEL-")) {
				//based on security level
				return (d.getSecurityLevel().getPriority() <= actor.getSecurityLevel().getPriority());
			}
			
			//Check Document sharing
			//TODO: Create Filter param for sharedDoc
			String filterParam=null;//" AND sharedDocument.targetUser = '2'   AND sharedDocument.status = '62' 
			//SharedDocumentService.getInstance().getBy(filterParam);
		}
		
		if (targetObj.getClass().getPackage().getName().equals(DOCMAN_PACKAGE) ){
		
			try {
				Method gcb=targetObj.getClass().getMethod("getCreatedBy");
				String creator=(String) gcb.invoke(targetObj, null);
			} catch (Exception e) {
				// TODO: handle exception
			}
			return isSharedTo(toAction,targetObj,actor);
		}
		
		return false;
	}
	*/
	
	private boolean isSibling(User owner, User guest) {
		return guest.getOrganization().getId()==owner.getUserLevel().getId();
	}
	
	private void isSuperior(Organization refOrg, Organization guestOrg) {
		// TODO Auto-generated method stub
//		WITH RECURSIVE q AS (SELECT forum.id, forum.code,forum.name, forum.parent_forum
//                FROM forum
//               WHERE forum.id=6 --parent_forum is null
//               UNION ALL
//              SELECT x.id, x.code,x.name, x.parent_forum
//                FROM forum  x
//                JOIN q ON q.id = x.parent_forum)
// SELECT * FROM q
		

	}
	
	public static boolean isDownlineOf(Long orgId, Long targetOrgId) {
		String sqlQuery="WITH RECURSIVE q AS (  SELECT organization.id, organization.code,organization.name, organization.parent, 1 as level FROM organization "
		   + " WHERE organization.id="+targetOrgId
		   +" UNION ALL "
		   +" SELECT x.id, x.code,x.name, x.parent, (q.level+1) as level FROM organization  x "
		   +" JOIN q ON q.id = x.parent) "
		   +" SELECT * FROM q where id="+orgId;
		try {
			List list=DBQueryManager.getList("isDownlineOf", sqlQuery, null);
			return !list.isEmpty();
		} catch (Exception e) {
		}
		return false;
	}
	
	public static boolean isUplineOf(Long orgId, Long targetOrgId) {
		String sqlQuery="WITH RECURSIVE q AS (  SELECT organization.id, organization.code,organization.name, organization.parent, 1 as level FROM organization "
		   + " WHERE organization.id="+targetOrgId
		   +" UNION ALL "
		   +" SELECT x.id, x.code,x.name, x.parent, (q.level+1) as level FROM organization  x "
		   +" JOIN q ON q.parent = x.id) "
		//   +" JOIN q ON q.id = x.parent) "
		   +" SELECT * FROM q where id="+orgId;
		try {
			List list=DBQueryManager.getList("isUplineOf", sqlQuery, null);
			return !list.isEmpty();
		} catch (Exception e) {
		}
		return false;
	}
	
	public static Long isSharedDoc(String toAction,Long docId,Long userId,Long orgId) {
		String sqlQuery="select id,grant_action from share_document where document="+docId+" and (target_user="+userId+"or target_organization="+orgId+")";
		try {
			List<Map<String, Object>> list=DBQueryManager.getList("isSharedTo", sqlQuery, null);
			if (list.isEmpty()) return null;
			return (Long) list.get(0).get("id");
		} catch (Exception e) {
		}
		return null;
	}
	
//	private void hasPrivilege() {
//		// TODO Auto-generated method stub
//
//	}
	
//	private static boolean isOwner(Document passport,String createdBy, User owner) {
//		if(!nvl(owner)) return passport.getLong("userId")==owner.getId();
//		return passport.getString("loginName").equals(createdBy);
//	}
//	
	public static boolean isAuthorize(Document passport,String aclMode, String action, String subAction, Document entity) {
		String loginUserName= passport.getString("loginName");
		String loginUserLevel= passport.getString("userLevel");
		Long loginUserOrgId=passport.getLong("organizationId");
		Long loginUserId=passport.getLong("userId");
		String createdBy=entity.getString("createdBy");
		
		Long ownerId=new Long(0);
		String approvedBy=null;
		String description=null;
		try {
			ownerId=toLong(entity.get("ownerId"));
		} catch (Exception e) {
		}
		boolean isOwner=(ownerId>0?loginUserId==ownerId: loginUserName.equals(createdBy));
		boolean isAdmin="admin".equals(loginUserLevel);
		boolean isExecutive="executive".equals(loginUserLevel);
		try {
			if (isAdmin) {
				description="Authorized as Admin";
				return true;
			} 
			if("Topic".equals(entity.getString("modelClass")) && subAction!=null){
				description="Authorized tp "+subAction+" to/from Topic Resource";
				return true;
			}

			if ("PUBLIC".equals(aclMode)){
				if (isExecutive) {
					description="Authorized as Executive on Public Resource";
					return true;
				}
				if("update|delete|share".contains(action)) {
					if(isOwner) {
						description="Authorized as Owner on Public Resource";
						return true;
					}
					description="UnAuthorize action on Public Resource";
					return false;
				}
				description="UnRestricted access on Public Resource";
				return true;
			} 
			if("User".equals(entity.getString("modelClass")) && "update".equals(action)) {
				if (entity.getLong("id")==loginUserId) {
					description="Authorize as onwer on User Resource";
				}
			}
	
			if ("SYSTEM".equals(aclMode)){
				if ("detail|list|search".contains(action)) {
					description="UnRestricted access on System Resource";
					return true;
				}
				description="UnAuthorize action "+action+" on System Resource";
				return false;
			}  
			if ("PRIVATE".equals(aclMode)){
				if(isOwner) {
					description="Authorized as Owner on Private Resource";
					return true;
				}
				description="UnAuthorize action "+action+" on Private Resource";
				return false;
			}

			if ("Document".equals(entity.getString("modelClass"))){
				String docSecurityLvl=entity.getString("securityLevel");
				if(docSecurityLvl==null ||"public".equals(docSecurityLvl)){
					if ("update|delete".contains(action)) {
						if (isExecutive) {
							description="Authorized as Executive on Public Document Resource";
							return true;
						}
						if(isOwner) {
							description="Authorized as Owner on Public Document Resource";
							return true;
						}
						description="Restricted action "+action+" on Public Document";
						return false;
					}
					description="Authorized action "+action+" on Public Document";
					return true;
				}
				if ("detail|download".contains(action)){
					Long shareId=isSharedDoc(action, (Long) entity.get("id"), loginUserId, loginUserOrgId);
					if (shareId!=null) {
						description="Authorized by SharedDoc["+shareId+"] to "+action+" of a Document Resource";
						return true;
					}
				}
				
				if("private".equals(docSecurityLvl)){
					if ("update|delete|detail|share".contains(action)) {
						if(isOwner) {
							description="Authorized as Owner on Private Document Resource";
							return true;
						}
						description="Restricted action on Private Document";
						return false;
					}
					if ("list|search".contains(action)){
						description="Restricted list/search to selfOwn Document";
						return false;
					}
					
				}
				
				if(docSecurityLvl.startsWith("LEVEL:")){
					int minLvl=toInt(docSecurityLvl.substring(6));
					int usrLvl=0;
					if (passport.getString("securityLevel").startsWith("LEVEL:")){
						usrLvl= toInt(passport.getString("securityLevel").substring(6));
					}
					if (isExecutive) {
						description="Authorized as Executive on Secured["+docSecurityLvl+"] Document Resource";
						return true;
					}
					if(isOwner) {
						description="Authorized as Owner on Secured["+docSecurityLvl+"] Document Resource";
						return true;
					}
					
					if(usrLvl>=minLvl){
						description="Authorized with higher["+passport.getString("securityLevel")+"] securityLevel  on Secured["+docSecurityLvl+"] Document Resource";
						return true;
					}
					description="Restricted action on on Secured["+docSecurityLvl+"] Document";
					return false;
					
				}
					
			}	
			description="Restricted Area no ACL defined for this action ";
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			description=e.getMessage();
		} finally {
			AuditTrailManager.auditLog(passport,action+(subAction!=null?":"+subAction:""),entity,description, approvedBy);
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println("TEST="+("update|create|list".contains("create")));
	}
}

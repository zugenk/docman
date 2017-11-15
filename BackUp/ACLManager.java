package com.app.module.document;

import java.util.List;

import com.app.docmgr.model.Document;
import com.app.docmgr.model.Lookup;
import com.app.docmgr.model.SharedDocument;
import com.app.docmgr.model.User;
import com.app.docmgr.service.SharedDocumentService;
import com.app.shared.PartialList;

public class ACLManager {
	public static boolean isAllowed(Document doc, User actor,String action) {
		Lookup docSecLvl=doc.getSecurityLevel();
		User owner=doc.getOwner();
		Lookup actorSecLvl=actor.getSecurityLevel();
		if (docSecLvl==null || "public".equalsIgnoreCase(docSecLvl.getCode())) {
			//SECURITY LEVEL : PUBLIC 
			
			//return true;
		} else if ("private".equalsIgnoreCase(docSecLvl.getCode())) {
			//SECURITY LEVEL : PRIVATE 
			
		} else if (docSecLvl.getCode().startsWith("level:")) {
			//SECURITY LEVEL : LEVEL
			if (actorSecLvl==null || !actorSecLvl.getCode().startsWith("level:")){
				//ACTOR HAS NO SEC CLEARANCE
				return false;
			}
			
			try {
				String code=docSecLvl.getCode();
				int minLevel=Integer.parseInt(code.substring(code.indexOf(":")));
				code=actorSecLvl.getCode();
				int actLevel=Integer.parseInt(code.substring(code.indexOf(":")));
//				int minLevel=docSecLvl.getPriority();
//				int actLevel=actorSecLvl.getPriority();
				if (actLevel >= minLevel) {
					//ACTOR HAS SAME or HIGHER SEC CLEARANCE than the doc required
					
					return true;
				} else {
					//ACTOR HAS NOT ENOUGH SEC CLEARANCE
					
					return false;
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else { // if("READ".equals(action)) {
			return isShared(doc,actor,action);
		}
		
		
		if("detail".equalsIgnoreCase(action)) {
		
		
		}
		
		return false;
	}

	
	public static boolean isShared(Document doc,User actor, String action) {
		//SEARCH for INDIVIDUAL SHARING 
		String orderParam=null;
		PartialList sdList;
		try {
			String filterParam=" AND sharedDocument.document="+doc.getId()+" AND sharedDocument.targetUser="+actor.getId(); //+" AND sharedDocument.grantAction like '%"+action+"%' ";
			sdList= SharedDocumentService.getInstance().getPartialList(filterParam, orderParam, 1, 1);
			if(!sdList.isEmpty()) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//SEARCH for ORG SHARING 
		try {
			String filterParam=" AND sharedDocument.document="+doc.getId()+" AND sharedDocument.targetOrganization="+actor.getOrganization().getId(); //+" AND sharedDocument.grantAction like '%"+action+"%' ";
			sdList= SharedDocumentService.getInstance().getPartialList(filterParam, orderParam, 1, 1);
			if(!sdList.isEmpty()) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;

	}
	
	public static boolean isSibling(User actor, User owner) {
		if (actor.getOrganization().getId() == owner.getOrganization().getId()) return true;
		return false;

	}
	 
	public static boolean isSuperior(User actor, User owner) {
		// TODO Auto-generated method stub
		
		return false;
	}
	
	public static boolean isDescendent(User actor, User owner) {
		// TODO Auto-generated method stub

		return false;
	}
	
	
	
	
}

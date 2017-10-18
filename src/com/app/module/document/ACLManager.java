package com.app.module.document;

import com.app.docmgr.model.Document;
import com.app.docmgr.model.Lookup;
import com.app.docmgr.model.User;
import com.app.docmgr.service.SharedDocumentService;

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
			
		} else if("READ".equals(action)) {
			//SEARCH for INDIVIDUAL SHARING 
			String filterParam="";
			String orderParam=null;
			//SharedDocumentService.getInstance().getList(filterParam, orderParam);
			
			
			//SEARCH for ORG SHARING 
			//SharedDocumentService.getInstance().getList(filterParam, orderParam);
			
		}
		
		
		if("detail".equalsIgnoreCase(action)) {
		
		
		}
		
		return false;
	}

	private void isSibling() {
		// TODO Auto-generated method stub

	}
	 
	private void isSuperior() {
		// TODO Auto-generated method stub

	}
	
	private void isDescendent() {
		// TODO Auto-generated method stub

	}
	
	
	
	
}

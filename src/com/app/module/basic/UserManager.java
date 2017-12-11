package com.app.module.basic;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.bson.Document;
import com.app.docmgr.model.Lookup;
import com.app.docmgr.model.Organization;
import com.app.docmgr.model.Role;
import com.app.docmgr.model.Topic;
import com.app.docmgr.model.User;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.OrganizationService;
import com.app.docmgr.service.StatusService;
import com.app.docmgr.service.TopicService;
import com.app.docmgr.service.UserService;
import com.app.module.forum.TopicManager;
import com.app.shared.ApplicationConstant;
import com.app.shared.ApplicationFactory;
import com.app.shared.PartialList;
import com.simas.webservice.Utility;

public class UserManager extends BaseUtil{
	private static Logger log = Logger.getLogger(UserManager.class);
	private static String ACL_MODE="SYSTEM";
	
	public static Document create(Document passport,Map<String, Object> data) throws Exception {
		List<String> errors=new LinkedList<String>();
		User obj= new User();
		updateFromMap(obj, data,errors);
		obj.setCreatedBy(passport.getString("loginName"));
		obj.setCreatedDate(new Date());
		obj.setLastUpdatedBy(obj.getCreatedBy());
		obj.setLastUpdatedDate(obj.getCreatedDate());
		obj.setLoginName((String) data.get("loginName"));
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_CREATE, null, toDocument(obj));
		obj.setStatus(StatusService.getInstance().getByTypeandCode("User", "new"));
		checkValidity(obj,errors);
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		assignNewPassword(obj,true);
		UserService.getInstance().add(obj);
		return toDocument(obj);
	}
	
	public static Document syncUser(Map<String, Object> data) throws Exception {
		//log.trace("Legacy User ="+Utility.debug(data));
		List<String> errors=new LinkedList<String>();
		Map profile= (Map) data.get("profile");
		User obj=UserService.getInstance().getBy(" AND user.loginName='"+profile.get("nip_baru")+"' ");
		if (obj==null) obj= new User();
		updateFromSIP(obj, profile,errors);
		obj.setCreatedBy((String)profile.get("nip_baru"));
		obj.setCreatedDate(new Date());
		
		obj.setLastUpdatedBy(obj.getCreatedBy());
		obj.setLastUpdatedDate(obj.getCreatedDate());
		obj.setLoginName((String) profile.get("nip_baru"));
		ACLManager.isAuthorize(null,ACL_MODE, ACLManager.ACTION_CREATE, "syncUserSIP", toDocument(obj));
		obj.setStatus(StatusService.getInstance().getByTypeandCode("User", "new"));
		checkValidity(obj,errors);
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		assignNewPassword(obj,false);
	//	System.out.println(Utility.debug(obj));
		if(obj.getId()==null) UserService.getInstance().add(obj);
		else UserService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static Document update(Document passport,Map data,String objId) throws Exception{
		List<String> errors=new LinkedList<String>();
		User obj= UserService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, null, toDocument(obj));
		updateFromMap(obj,data,errors) ;
		obj.setLastUpdatedBy(passport.getString("loginName"));
		obj.setLastUpdatedDate(new Date());
		checkValidity(obj,errors);
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		UserService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static Document updateMe(Document passport,Map data) throws Exception{
		List<String> errors=new LinkedList<String>();
		User obj= UserService.getInstance().get(passport.getLong("userId"));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, "updateMe", toDocument(obj));
		updateFromMap(obj,data,errors) ;
		obj.setLastUpdatedBy(passport.getString("loginName"));
		obj.setLastUpdatedDate(new Date());
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		UserService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static Document chgMyPwd(Document passport,Map data) throws Exception{
		List<String> errors=new LinkedList<String>();
		User obj= UserService.getInstance().get(passport.getLong("userId"));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, "chgMyPwd", toDocument(obj));
		String encriptedPassword=ApplicationFactory.encrypt((String) data.get("loginPassword"));
		obj.setLastUpdatedBy(passport.getString("loginName"));
		obj.setLastUpdatedDate(new Date());
	
		if(!encriptedPassword.equals(obj.getLoginPassword())){
	 		incrementLoginCounter(obj);
	 		throw new Exception("error.login.password");
	 	}  
		obj.setLoginPassword((String) data.get("newLoginPassword"));
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		UserService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static Document chgMyPwd(Document passport,String newAuth) throws Exception{
		List<String> errors=new LinkedList<String>();
		User obj= UserService.getInstance().get(passport.getLong("userId"));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, "chgMyPwd", toDocument(obj));
		obj.setLastUpdatedBy(passport.getString("loginName"));
		obj.setLastUpdatedDate(new Date());
		obj.setLoginPassword(LoginManager.encryptPassword(newAuth)); 
		if(!errors.isEmpty()) throw new Exception(listToString(errors));
		UserService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static List myFavTopics(Document passport) throws Exception{
		User obj= UserService.getInstance().get(passport.getLong("userId"));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DETAIL, "myFavTopics", toDocument(obj));
		return TopicManager.toDocSimpleList((Set<Topic>) obj.getFavoriteTopics());
	}
	
	public static List myFollTopics(Document passport) throws Exception{
		User obj= UserService.getInstance().get(passport.getLong("userId"));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DETAIL, "myFollTopics", toDocument(obj));
		List<Topic> result=TopicService.getInstance().getList(" AND topic.subscribers.id='"+passport.getLong("userId")+"' ", null);
		TopicManager.toDocList(result);
		return result;
	}
	
	public static Document myself(Document passport) throws Exception{
		User obj= UserService.getInstance().get(passport.getLong("userId"));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DETAIL, "myself", toDocument(obj));
		return toDocument(obj);
	}
	
	public static Document resetMyPassword(Document passport,String objId) throws Exception{
		User obj= UserService.getInstance().get(toLong(objId)); //passport.getLong("userId"));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_UPDATE, "resetPassword", toDocument(obj));
		obj.setLastUpdatedBy(passport.getString("loginName"));
		obj.setLastUpdatedDate(new Date());
		assignNewPassword(obj,true);
		UserService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	public static Document resetPassword(Map data) throws Exception{
		if(nvl(data.get("loginName"))) throw new Exception("error.loginName.invalid");
		User obj= UserService.getInstance().getBy(" AND user.loginName='"+ data.get("loginName")+"'  AND user.email='"+ data.get("email")+"' "); 
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(null,ACL_MODE, ACLManager.ACTION_UPDATE, "resetPassword", toDocument(obj));
		obj.setLastUpdatedBy("SYSTEM");
		obj.setLastUpdatedDate(new Date());
		assignNewPassword(obj,true);
		UserService.getInstance().update(obj);
		return toDocument(obj);
	}
	
	private static void assignNewPassword(User obj,boolean sendMail) throws Exception{
		String newPwd=genRandomText(12);
		String encriptedPassword=ApplicationFactory.encrypt(newPwd);
		obj.setLoginPassword(encriptedPassword);
		if(sendMail) sendNewPwd(obj,newPwd);
	}
	
	public static void sendNewPwd(User user,String newPwd) {
	if(user==null || user.getEmail()==null) {
		log.error("Error sending new Password to email, user or email null");
		return;
	}
	String message="Password Create/Reset event has been called for your user\n\r\t\tHere is your newly generated password \""+newPwd+"\"";
	String subject="New system generated Password";
	String from=ApplicationFactory.getCustomerServiceEmail();
	List<String> toAddress=new LinkedList<>();
	toAddress.add(user.getEmail());
	ApplicationFactory.sendMail(from, toAddress, subject, message);
}
	
	public static void delete(Document passport,String objId) throws Exception {
		User obj=UserService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DELETE, null, toDocument(obj));
		obj.setStatus(StatusService.getInstance().getByTypeandCode("User", "deleted"));
		obj.setLastUpdatedDate(new Date());
		obj.setLastUpdatedBy(passport.getString("loginName"));
		UserService.getInstance().update(obj);
	}

	public static Document detail(Document passport,String objId) throws Exception {
		User obj=UserService.getInstance().get(toLong(objId));
		if (obj==null) throw new Exception("error.object.notfound");
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_DETAIL, null, toDocument(obj));
		return toDocument(obj);
	}
	
	public static List list(Document passport,Map data) throws Exception{
		ACLManager.isAuthorize(passport,ACL_MODE, ACLManager.ACTION_LIST, null, new Document("modelClass","User"));
		String filterParam=null;
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
					filterBuff.append(constructQuery("user",key,filterMap.get(key))); //filterBuff.append(" AND user."+key+" LIKE '%"+(String) filterMap.get(key)+"%' ");
				}
				filterParam=filterBuff.toString();
			}
			
			Map orderMap= (Map) data.get("orderBy");
			if (orderMap!=null && !orderMap.isEmpty()) {
				for (Iterator iterator = orderMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					if(orderParam==null) orderParam=" user."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
					else orderParam+=", user."+key+("DESC".equalsIgnoreCase((String)orderMap.get(key))?" DESC":" ASC");
				}
			}
		}
		if("ALL".equals(mode)){
			List result=UserService.getInstance().getListAll((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}
		if("NOPAGE".equals(mode)){
			List result=UserService.getInstance().getList((filterParam!=null?filterParam.toString():null), orderParam);
			toDocList(result);
			return result;
		}
		PartialList result=UserService.getInstance().getPartialList((filterParam!=null?filterParam.toString():null), orderParam, start, ITEM_PER_PAGE);
		toDocList(result);
		return result;
	}
	
	
	private static void updateFromSIP(User obj, Map data,List<String> errors) {
		/*
		 *{
	"status" : 1,
  "pesan" : "Sukses",
  "token" : "4664370e4af91052e0e92113d6cb25f9aaf5c5a0",
  "user" : {
    "row_id" : 242,
    "user" : "196402051989031001",
    "pass" : "09039ee80d5f4342b4244de63422e90d",
    "admin" : 0
  },
  "profile" : {
    "row_id" : 9,
    "nip_lama" : "",
    "nip_baru" : "196402051989031001",
    "instansi_induk" : "Badan Nasional Penanggulangan Terorisme",
    "instansi_asal" : "Kementerian Luar Negeri",
    "stts_pegawai" : "Organik",
    "unit_kerja" : "3",
    "gelar_depan" : "",
    "nm_lengkap" : "Mohamad Kamal",
    "gelar_belakang" : "SH.LL.M",
    "jabatan" : "Staf Khusus Biro Umum",
    "pendidikan" : "S II",
    "pangkat" : "PEMBINA TK.I IV/B",
    "angkatan" : "PNS",
    "korps_tni" : "",
    "sumber_pa" : "",
    "tpt_lahir" : "Jakarta",
    "tgl_lahir" : "1964-02-05",
    "status_nikah" : "Menikah",
    "agama" : "Islam",
    "jns_kelamin" : "Laki - laki",
    "gol_darah" : "O",
    "no_karis" : "",
    "no_karpeg" : "E 744277",
    "no_taspen" : "0000038162529",
    "no_askes" : "0000038162529",
    "no_npwp" : "05.939.719.0-025.000",
    "data_rekbank" : "001201152504509",
    "email" : "adekamal@gmail.com",
    "no_tlp_kntr" : "",
    "no_tlp_rmh" : "",
    "no_hp" : "081290331864",
    "no_randis" : "",
    "no_kpi" : "",
    "no_kta" : "",
    "no_label_sec" : "",
    "no_ktp" : 0,
    "foto" : "20171026154323.jpg",
    "status" : 1
  }
}
		 * 
		 */
		try {
			obj.setEmail((String) data.get("email"));
			obj.setEmployeeNumber(toString(data.get("nip_baru")));
			obj.setFullName((String) data.get("nm_lengkap"));
			obj.setHomePhoneNumber(toString(data.get("no_tlp_rmh")));
			obj.setMobileNumber(toString(data.get("no_tlp_kntr")));
			obj.setMobilePhoneNumber(toString(data.get("no_hp")));
			obj.setName((String) data.get("nm_lengkap"));
			obj.setTitle((String) data.get("gelar_depan"));
		//	obj.setAlias((String) data.get("alias"));
//			if(obj.getName()==null){
//				obj.setName((obj.getAlias()!=null?obj.getAlias():obj.getFullName())); 
//			}
			//DEFAULTED
			//Lookup userLevel= LookupService.getInstance().getByTypeandCode("userLevel", "customer"));
			//if(userLevel!=null) 
			obj.setUserLevel(ApplicationConstant.getLookup("userLevel", "customer"));
			
/*
		if(!nvl(data.get("organizationId"))){
			try {
				Organization org= OrganizationService.getInstance().get(toLong(data.get("organizationId")));
				if(org!=null)obj.setOrganization(org);
			} catch (Exception e) {
				errors.add("error.invalid.organization");
			}
		}
		if(!nvl(data.get("positionId"))){
			try {
				Lookup position= LookupService.getInstance().get(toLong(data.get("positionId")));
				if(position!=null) obj.setPosition(position);
			} catch (Exception e) {
				errors.add("error.invalid.position");
			}
		}
		if(!nvl(data.get("userLevelId"))){
			try {
				//Lookup userLevel= LookupService.getInstance().getByTypeandCode("userLevel", (String)data.get("userLevel"));
				Lookup userLevel= LookupService.getInstance().get(toLong(data.get("userLevelId")));
				if(userLevel!=null) obj.setUserLevel(userLevel);
			} catch (Exception e) {
				errors.add("error.invalid.userLevel");
			}
		}
		if(!nvl(data.get("securityLevelId"))){
			try {
				//Lookup securityLevel= LookupService.getInstance().getByTypeandCode("securityLevel", (String)data.get("securityLevel"));
				Lookup securityLevel= LookupService.getInstance().get(toLong(data.get("securityLevelId")));
				if(securityLevel!=null) obj.setSecurityLevel(securityLevel);
			} catch (Exception e) {
				errors.add("error.invalid.securityLevel");
			}
		}*/
		
		} catch (Exception e) {
			e.printStackTrace();
			errors.add(e.getMessage());
		}

		/*
		if(!nvl(data.get("statusId"))){
			try {
				Status status= StatusService.getInstance().get(toLong(data.get("statusId")));
				if(status!=null) obj.setStatus(status);
			} catch (Exception e) {
				errors.add("error.invalid.status");
			}
		}
		*/

	}
	
	private static void updateFromMap(User obj, Map data,List<String> errors) {
		try {
			obj.setEmail((String) data.get("email"));
			obj.setEmployeeNumber(toString(data.get("employeeNumber")));
			//obj.setFavoriteTopics(favoriteTopics);
			obj.setFullName((String) data.get("fullName"));
			obj.setHomePhoneNumber(toString(data.get("homePhoneNumber")));
			obj.setMobileNumber(toString(data.get("mobileNumber")));
			obj.setMobilePhoneNumber(toString(data.get("mobilePhoneNumber")));
			obj.setName((String) data.get("name"));
			obj.setTitle((String) data.get("title"));
			obj.setAlias((String) data.get("alias"));
			if(obj.getName()==null){
				obj.setName((obj.getAlias()!=null?obj.getAlias():obj.getFullName())); 
			}

		if(!nvl(data.get("organizationId"))){
			try {
				Organization org= OrganizationService.getInstance().get(toLong(data.get("organizationId")));
				if(org!=null)obj.setOrganization(org);
			} catch (Exception e) {
				errors.add("error.invalid.organization");
			}
		}
		if(!nvl(data.get("positionId"))){
			try {
				Lookup position= LookupService.getInstance().get(toLong(data.get("positionId")));
				if(position!=null) obj.setPosition(position);
			} catch (Exception e) {
				errors.add("error.invalid.position");
			}
		}
		if(!nvl(data.get("userLevelId"))){
			try {
				//Lookup userLevel= LookupService.getInstance().getByTypeandCode("userLevel", (String)data.get("userLevel"));
				Lookup userLevel= LookupService.getInstance().get(toLong(data.get("userLevelId")));
				if(userLevel!=null) obj.setUserLevel(userLevel);
			} catch (Exception e) {
				errors.add("error.invalid.userLevel");
			}
		}
		if(!nvl(data.get("securityLevelId"))){
			try {
				//Lookup securityLevel= LookupService.getInstance().getByTypeandCode("securityLevel", (String)data.get("securityLevel"));
				Lookup securityLevel= LookupService.getInstance().get(toLong(data.get("securityLevelId")));
				if(securityLevel!=null) obj.setSecurityLevel(securityLevel);
			} catch (Exception e) {
				errors.add("error.invalid.securityLevel");
			}
		}
		
		} catch (Exception e) {
			e.printStackTrace();
			errors.add(e.getMessage());
		}

		/*
		if(!nvl(data.get("statusId"))){
			try {
				Status status= StatusService.getInstance().get(toLong(data.get("statusId")));
				if(status!=null) obj.setStatus(status);
			} catch (Exception e) {
				errors.add("error.invalid.status");
			}
		}
		*/
	}
	
	
	public static Document toSimpleDoc(User obj) {
		Document doc=new Document();
		doc.append("modelClass", obj.getClass().getSimpleName());
		doc.append("id", obj.getId());
		doc.append("alias", obj.getAlias());
		doc.append("fullName", obj.getFullName());
		//doc.append("title", obj.getTitle());
		doc.append("email", obj.getEmail());
		//doc.append("employeeNumber", obj.getEmployeeNumber());
		//doc.append("homePhoneNumber", obj.getHomePhoneNumber());
		//doc.append("language", obj.getLanguage());
		//doc.append("mobilePhoneNumber", obj.getMobilePhoneNumber());
		doc.append("loginName", obj.getLoginName());
		return doc;
	}
	public static Document toDocument(User obj) {
		Document doc=new Document();
		doc.append("modelClass", obj.getClass().getSimpleName());
		doc.append("id", obj.getId());
		doc.append("createdBy", obj.getCreatedBy());
		doc.append("alias", obj.getAlias());
		doc.append("fullName", obj.getFullName());
		doc.append("title", obj.getTitle());
		doc.append("email", obj.getEmail());
		doc.append("employeeNumber", obj.getEmployeeNumber());
		doc.append("homePhoneNumber", obj.getHomePhoneNumber());
		doc.append("language", obj.getLanguage());
		doc.append("mobilePhoneNumber", obj.getMobilePhoneNumber());
		doc.append("loginName", obj.getLoginName());
		if(obj.getPosition()!=null) {
			doc.append("position", obj.getPosition().getName());
			doc.append("positionId", obj.getPosition().getId());
		}
		if(obj.getOrganization()!=null) {
			doc.append("organization", obj.getOrganization().getName());
			doc.append("organizationId", obj.getOrganization().getId());
		}
		if(obj.getSecurityLevel()!=null){
			doc.append("securityLevel", obj.getSecurityLevel().getName());
			doc.append("securityLevelId", obj.getSecurityLevel().getId());
		} if (!nvl(obj.getOrganization()) && !nvl(obj.getOrganization().getSecurityLevel())){
			doc.append("securityLevel", obj.getOrganization().getSecurityLevel().getName());
			doc.append("securityLevelId", obj.getOrganization().getSecurityLevel().getId());
		}
		if (obj.getUserLevel()!=null) {
			doc.append("userLevel", obj.getUserLevel().getName());
			doc.append("userLevelId", obj.getUserLevel().getId());
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
			User obj = (User) list.get(i);
			list.set(i, toDocument(obj));
		}
	}
	
	public static boolean incrementLoginCounter(User loginUser) throws Exception{
		boolean isBlocked=false;
		int ctr=loginUser.getLoginFailed()+1;
 		if (ctr>=MAX_WRONG_PASSWD_ATTEMPT) {
 			loginUser.setStatus(BLOCKED_USER_STATUS);
 			isBlocked=true;
 		}
 		loginUser.setLoginFailed(ctr+1);
 		UserService.getInstance().update(loginUser);
 		return isBlocked;
	}

	
	public static List<String> getRoleNames(User user) {
		List<String> roleNames=new LinkedList<String>();
		for (Iterator iterator = user.getRoles().iterator(); iterator.hasNext();) {
			Role role = (Role) iterator.next();
			roleNames.add(role.getName());
		}
		return roleNames;
	}

	public static List<Long> getFavTopicIds(User user) {
		List<Long> favTopicIds=new LinkedList<Long>();
		for (Iterator iterator = user.getFavoriteTopics().iterator(); iterator.hasNext();) {
			Topic topic = (Topic) iterator.next();
			favTopicIds.add(topic.getId());
		}
		return favTopicIds;
	}
	
	public static boolean isAdmin(User user){
		if (user==null) return false;
		return "admin".equals(user.getUserLevel().getCode());
	}
	
	public static void checkValidity(User obj,List errors) {
		//login_name,login_password,userLevel,name
		if (obj.getLoginName()==null) errors.add("error.loginName.null");
		if (obj.getUserLevel()==null) errors.add("error.userLevel.null");
		if (obj.getName()==null) errors.add("error.name.null");
	}
	
	
	
}

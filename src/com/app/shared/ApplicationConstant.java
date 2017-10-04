package com.app.shared;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import uk.ltd.getahead.dwr.ExecutionContext;
import org.apache.log4j.*;
import com.app.docmgr.model.*;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */
 
public class ApplicationConstant {
	private static Logger log = Logger.getLogger("com.app.shared.ApplicationConstant");	
	public static int MAX_ITEM_PER_PAGE = 10; 
	private static Map<String,String> systemParameter=new HashMap<String,String>();
	//private static Map<String,Status> status=new HashMap<String,Status>();
	//private static Map<String,Lookup> lookup=new HashMap<String,Lookup>();
	//public static Map city=new HashMap();
	//public static List country=new LinkedList();
	public static String contextRealPath=""; //eclipse/workspace/iBankEder/WebContent";
	
	public static Thread daemonThread=null;
	public static int retryMail = 10;
	public static Map propertyMap=null;
	public static String errorkeyPrefix="ERROR.INTERNALERROR.";
	public static String msgPropertyName="/WEB-INF/classes/MessageResources";
	public static String defaultTemplate="default";
	//public static String corebankConf="/WEB-INF/conf/channels.xml";
	//public static String log4jfile=null;// "/WEB-INF/log4j.lcf";
	//public static String serverName=null;

/*	public static void storeSystemParam(String vgroup,String parameter,String svalue) {
		if (systemParameter==null) systemParameter= new HashMap<String, String>();
		systemParameter.put(vgroup+"::"+parameter,svalue);
	}
	
	public static String getSystemParam(String vgroup,String parameter) {
		if (systemParameter==null || !systemParameter.containsKey(vgroup+"::"+parameter)) return null;
		return (String) systemParameter.get(vgroup+"::"+parameter);
	}
*/	

/*	public static void storeLookup(String type,String code,Lookup ilookup) {
		if (lookup==null) lookup= new HashMap<String, Lookup>();
		lookup.put(type+"::"+code,ilookup);
	}
	
	public static Lookup getLookupByTypeAndCode(String type,String code) {
		if (lookup==null || !lookup.containsKey(type+"::"+code)) return null;
		return (Lookup) lookup.get(type+"::"+code);
	}

	public static List<Lookup> getLookupByType(String type) {
		List<Lookup> result=new LinkedList<Lookup>();
		if (lookup==null) return result;
		Iterator lookupItr=lookup.keySet().iterator();
		boolean found=false;
		while (lookupItr.hasNext()) {
			String key = (String) lookupItr.next();
			if (key.startsWith(type+"::")){
				result.add(lookup.get(key));
				found=true;
			} else if (found) break;
		}
		return result;
	}
*/
/*	public static void storeStatus(String type,String code,Status istatus) {
		if (status==null) status= new HashMap<String, Status>();
		status.put(type+"::"+code,istatus);
	}

	
	public static Status getStatusByTypeAndCode(String type,String code) {
		if (status==null || !status.containsKey(type+"::"+code)) return null;
		return (Status) status.get(type+"::"+code);
	}

	public static List getStatusByType(String type) {
		List result=new LinkedList();
		if (status==null) return result;
		Iterator statusItr=status.keySet().iterator();
		boolean found=false;
		while (statusItr.hasNext()) {
			String key = (String) statusItr.next();
			if (key.startsWith(type+"::")){
				result.add(status.get(key));
				found=true;
			} else if (found) break;
		}
		return result;
	}
*/
/*	public String getFirstCityByCountry(String countryName) {
		return ((City)((List)city.get(countryName)).get(0)).getName();
	}
	public List getCitiesByCountry(String countryName) {
		List cityList=(List) city.get(countryName); 
		return cityList;
	}
*/	
/*	public Account getAccountInfo(long id){
	    List accountList= (List) WebContextFactory.get().getHttpServletRequest().getSession().getAttribute("accountList");
	    Iterator accItr =accountList.iterator();
	    while (accItr.hasNext()) {
			Account iaccount = (Account) accItr.next();
			if (iaccount.getId().equals(id)) return iaccount;
		}
	    return null;
	}
*/

/*
	public CurrencyRate getCurrencyInfo(long id){
	    List currList= (List) WebContextFactory.get().getHttpServletRequest().getSession().getAttribute("currencyRateList");
	    Iterator currItr =currList.iterator();
	    while (currItr.hasNext()) {
			CurrencyRate iCurr = (CurrencyRate) currItr.next();
			if (iCurr.getId().equals(id)) return iCurr;
		}
	    return null;
	}

	public String getForwardCustomerServiceEdit() throws ServletException, IOException 
	{
	    System.out.println("hello in AppConstant getForward");
	   
	    //WebContext ctx = WebContextFactory.get();
	    //Customer cust = (Customer)ctx.getHttpServletRequest().getSession().getAttribute("profile");
        //System.out.println("session di dwr : " + cust.getCifCode());
        
	    WebContext wctx = WebContextFactory.get();
        return wctx.forwardToString("/customer.do?action=edit");
	}
	
	public String getForward(String action) throws ServletException, IOException
	{
		System.out.println("hello in AppConstant getForward to action " + action);
		WebContext wctx = WebContextFactory.get();
		return wctx.forwardToString("/custPersonalDetails.do?action="+action);
	}
*/
	
/*	public Customer getCustomer(String cifCode) throws Exception
	{
		//Customer customer = (Customer) CustomerService.getInstance().get(id); 
		System.out.println("masuk di getCustomer");
		
		WebContext ctx = WebContextFactory.get();
	    //Customer cust = (Customer)ctx.getHttpServletRequest().getSession().getAttribute("profile");
		User loginUser = (User) ctx.getHttpServletRequest().getSession().getAttribute("login.user");
		if (loginUser==null ){
			ctx.getHttpServletRequest().getSession().setAttribute("url",ctx.getHttpServletRequest().getRequestURI());
			ctx.forwardToString("/login.do");
		}
		String transRefNum = ApplicationFactory.generateTransactionRefNum(cifCode, "ENQ");
		Customer customer = CorebankManager.getCustomerByCifCode(loginUser.getLoginName(),transRefNum,cifCode);
		
//		System.out.println("customer di dwr server object : "  + customer.getCifCode());
//		System.out.println("customer di dwr server object : "  + customer.getStatus().getName());
//		System.out.println("customer di dwr server object : "  + customer.getType().getName());
//		System.out.println("customer di dwr server object : "  + customer.getLevel().getName());
//		System.out.println("customer di dwr server object : "  + customer.getBirthPlace());
//		System.out.println("customer di dwr server object : "  + customer.getEmail());
		return customer;
	}
*/	
	/*public Account getAccountDetails(Long accountId) throws Exception
	{

//		CustomerServiceBO customerServiceBO = new CustomerServiceBO();
//		Account account = (Account) customerServiceBO.getByAccountId(new Long(accountId));
		Account account = new Account();
		System.out.println(accountId.toString());
		try{
		account = AccountBO.getInstance().findByAccountNumber(accountId);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
				WebContext ctx = WebContextFactory.get();
				ctx.getHttpServletRequest().getSession().setAttribute("account", account);
				System.out.println("account not null, in AppConstant : " + account.toString());
		
		System.out.println("Account in AppConstant : " + account.getAccountNumber());
		return account;
	}
*/
}

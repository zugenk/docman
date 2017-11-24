package com.app.shared;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import uk.ltd.getahead.dwr.ExecutionContext;
import org.apache.log4j.*;
import com.app.docmgr.model.*;
import com.app.docmgr.service.LookupService;
import com.app.docmgr.service.StatusService;
import com.app.docmgr.service.SystemParameterService;
import com.simas.webservice.Utility;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */
 
public class ApplicationConstant {
	private static Logger log = Logger.getLogger("com.app.shared.ApplicationConstant");	
	public static int MAX_ITEM_PER_PAGE = 10; 
	private static Map<String,Map<String,SystemParameter>> SYSTEM_PARAMETER=null;//new HashMap<String,Map<String,SystemParameter>>();
	private static Map<String,Map<String,Status>> STATUS_MAP=null;//new HashMap<String, Map<String,Status>>();
	private static Map<String, Map<String,Lookup>> LOOKUP_MAP=null;//new HashMap<String, Map<String,Lookup>>();
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
	
	public static void reset() {
		SYSTEM_PARAMETER=null;
		STATUS_MAP=null;
		LOOKUP_MAP=null;
		initSystemParam();
		initLookup();
		initStatus();
	
	}
	
	public static void initSystemParam() {
		if (SYSTEM_PARAMETER!=null) return;
		try {
			SYSTEM_PARAMETER= new HashMap<String,Map<String,SystemParameter>>();
			List result=SystemParameterService.getInstance().getList(null, " vgroup ASC, parameter ASC");
			String vgroup="";
			Map<String, SystemParameter> gMap=null;
			for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				SystemParameter param = (SystemParameter) iterator.next();
				if(!vgroup.equals(param.getVgroup())){
					gMap=new HashMap<String,SystemParameter>();
					vgroup=param.getVgroup();
					SYSTEM_PARAMETER.put(vgroup, gMap);
				}
				gMap.put(param.getParameter(), param);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static Map<String, SystemParameter> getSystemParamMap(String vgroup) {
		initSystemParam();
		return SYSTEM_PARAMETER.get(vgroup);
	}
	
	public static SystemParameter getSystemParam(String vgroup,String parameter) {
		return getSystemParamMap(vgroup).get(parameter);
	}
	
	
	public static void initLookup() {
		if (LOOKUP_MAP!=null) return;
		try {
			LOOKUP_MAP= new HashMap<String,Map<String,Lookup>>();
			List result=LookupService.getInstance().getList(null, " lookup.type ASC, lookup.code ASC");
			String type="";
			Map<String, Lookup> tMap=null;
			for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				Lookup lookup = (Lookup) iterator.next();
				if(!type.equals(lookup.getType())){
					tMap=new HashMap<String,Lookup>();
					type=lookup.getType();
					LOOKUP_MAP.put(type, tMap);
				}
				tMap.put(lookup.getCode(), lookup);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static Map<String, Lookup> getLookupMap(String type) {
		initLookup();
		return LOOKUP_MAP.get(type);
	}
	
	public static Lookup getLookup(String type,String code) {
		return getLookupMap(type).get(code);
	}
	

	public static void initStatus() {
		if (STATUS_MAP!=null) return;
		try {
			STATUS_MAP= new HashMap<String,Map<String,Status>>();
			List result=StatusService.getInstance().getList(null, " status.type ASC, status.code ASC");
			String type="";
			Map<String, Status> tMap=null;
			for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				Status status = (Status) iterator.next();
				if(!type.equals(status.getType())){
					tMap=new HashMap<String,Status>();
					type=status.getType();
					STATUS_MAP.put(type, tMap);
				}
				tMap.put(status.getCode(), status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static Map<String, Status> getStatusMap(String type) {
		initStatus();
		return STATUS_MAP.get(type);
	}
	
	public static Status getStatus(String type,String code) {
		return getStatusMap(type).get(code);
	}
	
	

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

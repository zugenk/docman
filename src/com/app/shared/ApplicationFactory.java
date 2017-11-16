package com.app.shared;

import java.io.*;
import java.security.*;
import java.security.Provider;
import java.text.*;
import java.util.*;
import javax.activation.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.*;
import javax.mail.internet.*;
import javax.naming.*;
import javax.naming.directory.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.*;
import org.apache.velocity.*;
import org.apache.velocity.app.*;

import com.app.docmgr.model.*;
import com.app.docmgr.service.*;


/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */


public class ApplicationFactory {
	private static Logger log = Logger.getLogger("com.app.shared.ApplicationFactory");	
	private static final String KEY_STRING = "3816c875da2f2540";
	private static DirContext dirContext = null;
	private static String ipSmtp=null;
	private static boolean needSMTPAuth=false;
	private static String username="";
	private static String password="";
	private static String adminEmail="";
	private static String customerServiceEmail="";
	
	/**
	 * @return  the adminEmail
	 * @uml.property  name="adminEmail"
	 */
	public static String getAdminEmail(){
		init();
		return adminEmail;
	}
	/**
	 * @return  the customerServiceEmail
	 * @uml.property  name="customerServiceEmail"
	 */
	public static String getCustomerServiceEmail(){
		init();
		return customerServiceEmail;	
	}
	
    public static Properties getPropertyFile(ServletContext context, String path) throws Exception {
    	Properties prop = null;
    	try {
            File fprop = new File(context.getRealPath(path));
            if (fprop.exists()) {
                prop = new Properties();
    		    prop.load(new FileInputStream(fprop));
            }
		} catch(Exception ex) {
		    ex.printStackTrace();
		}

		return prop;
    }

    public static DirContext getDirContext(ServletContext context) throws Exception {
		if (dirContext == null) {
	    	Properties prop = getPropertyFile(context, "/WEB-INF/classes/ldap.properties");
	    	if (prop != null) {
				Properties env = new Properties();
			   	env.put(Context.INITIAL_CONTEXT_FACTORY, prop.getProperty("ldap.initial_context_factory"));
			   	env.put(Context.PROVIDER_URL, prop.getProperty("ldap.provider_url"));
			   	env.put(Context.SECURITY_AUTHENTICATION, prop.getProperty("ldap.security_authentication"));
			   	env.put(Context.SECURITY_PRINCIPAL, prop.getProperty("ldap.security_principal"));
			   	env.put(Context.SECURITY_CREDENTIALS, prop.getProperty("ldap.security_credentials"));

			   	// Create the initial context
				dirContext = new InitialDirContext(env);
	    	}
		}

	   	return dirContext;
    }

    public static String getQuery(ServletContext context, String val){
        String result = null;
        try{
            File fprop = new File(context.getRealPath("/WEB-INF/classes/query.properties"));
            if(fprop.exists()){
                Properties prop = new Properties();
                prop.load(new FileInputStream(fprop));
                result = prop.getProperty(val);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public static String encrypt(String value){
		String result = "";
        try{
	        MessageDigest md = MessageDigest.getInstance("MD5");
			byte[]inputBytes = value.getBytes();
			md.update(inputBytes);

			byte[] digest = md.digest();
			StringBuffer md5Hash = new StringBuffer();
			String s = value;
			for (int i = 0; i < digest.length; i++)
			{
				s = Integer.toHexString(0xFF & digest[i]);
				if (s.length() < 2) s = "0" + s;
				md5Hash.append(s);
			}

			result = md5Hash.toString();
		}catch(Exception ex){
		    ex.printStackTrace();
		}
        return result;
    }   

    private static void init(){
        if (ipSmtp!=null) return;
//		ipSmtp=ApplicationConstant.getSystemParam("EMAIL", "SMTP_GATEWAY");
//		needSMTPAuth="true".equalsIgnoreCase(ApplicationConstant.getSystemParam("EMAIL", "NEED_SMTP_AUTH"));
//		adminEmail=ApplicationConstant.getSystemParam("EMAIL", "SYSTEM_ADMIN_EMAIL");
//		customerServiceEmail=ApplicationConstant.getSystemParam("EMAIL", "SYSTEM_CUST_SERVICE_EMAIL");
//		username=ApplicationConstant.getSystemParam("EMAIL", "EMAIL_USER");
//		password=ApplicationConstant.getSystemParam("EMAIL", "EMAIL_USER_PASSWORD");
		

        Map<String,SystemParameter> emailParam= ApplicationConstant.getSystemParamMap("EMAIL");
        ipSmtp=emailParam.get("SMTP_GATEWAY").getSvalue();
    	adminEmail=emailParam.get("SYSTEM_ADMIN_EMAIL").getSvalue();				
    	customerServiceEmail=emailParam.get("SYSTEM_CUST_SERVICE_EMAIL").getSvalue();			
    	needSMTPAuth="true".equalsIgnoreCase(emailParam.get("NEED_SMTP_AUTH").getSvalue());				
    	username=emailParam.get("EMAIL_USER").getSvalue();;
    	password=emailParam.get("EMAIL_USER_PASSWORD").getSvalue();
  
/*        
		if (ipSmtp==null || ipSmtp.length()==0)ipSmtp="mail.amsconsult.com";
		if (adminEmail==null || adminEmail.length()==0) adminEmail="admin@amsconsult.com";
		if (customerServiceEmail==null || customerServiceEmail.length()==0) customerServiceEmail="customerservice@amsconsult.com";
*/
    }
    
/*    public static void sendMail(String emailType,String from,List toAddress,String subject,String emailTemplate,VelocityContext emailContext) throws Exception{
    	init();
    	if (emailTemplate==null || emailTemplate.length()==0) throw new Exception("Email Template is invalid (null)!");
    	if (toAddress==null || toAddress.isEmpty()) throw new Exception("Empty To address !");
    	String message=applyTemplate(emailContext,emailTemplate);
    	List result =sendMail(from,toAddress,subject,message);
    	EmailLog el=null;
    	
    	Date now = new Date();
    	if (result !=null) {
    		el=new EmailLog();
    		el.setDueDate(now);
    		el.setMessage(message);
    		Iterator addressItr=result.iterator();
    		StringBuffer toAddrBuff=new StringBuffer("");
    		boolean first=true;
    		while (addressItr.hasNext()) {
				String iaddr = (String) addressItr.next();
				if (first) {
					toAddrBuff.append(iaddr);
					first=false;
				} else toAddrBuff.append(":"+iaddr);
			}
    		el.setRcptTo(toAddrBuff.toString());
    		el.setRetry(ApplicationConstant.retryMail);
    		el.setSender(ApplicationFactory.getAdminEmail());
    		el.setSentDate(null);
    		el.setStatus(StatusService.getInstance().findbyTypeandCode("email", "sending").getCode());
    		el.setSubject(subject);
    		el.setType(emailType);
    		try{
    			EmailLogService.getInstance().add(el);
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    	}
    }

    public static void broadCastNews(News news){
    	broadCastNews(news,null);
    }
    public static void broadCastNews(News news,EmailLog emailLog){
    	List emailList = new LinkedList();
    	Date now = new Date();
    	try{
    		emailList = CustomerService.getInstance().getEmailList(news.getId());
    		//if (emailList.isEmpty()) return;
//			Iterator emaiListItr = emailList.iterator();
			
    		String from = null;
//			String to[]= new String[emailList.size()];
//			int k=0;
//			while (emaiListItr.hasNext()) {
//				String element = (String) emaiListItr.next();
//				to[k]=element;
//				k++;
//			}
			String subject = news.getTitle();
			String emailTemplate="newsBroadcast.vm";
			VelocityContext emailContext=new VelocityContext();
			emailContext.put("subject", subject);
			emailContext.put("brief",news.getBrief());
			emailContext.put("content",news.getNewsContent());
			String message=applyTemplate(emailContext,emailTemplate);
			if (emailLog==null){
		    	try {
		    		emailLog = (EmailLog) EmailLogService.getInstance().getPartialList(" and emailLog.type='news' and emailLog.subject='"+news.getId()+"' ",null,0,10).iterator().next();
				} catch (Exception e) {
					emailLog =null;
				}
	    	}
			List result =new LinkedList();
			boolean pending=now.before(news.getStartDate()); 
			boolean expired=now.after(news.getEndDate());
			if ( !pending && !expired) {
				
				if (emailLog!=null && emailLog.getRcptTo()!=null && emailLog.getRcptTo().length()>0){
		    		String addresses=emailLog.getRcptTo();
		    		if (addresses!=null ||addresses.length()>0) {
		    			String[] addrArr=addresses.split(":");
		    			emailList.clear();
		    			for(int i=0;i<addrArr.length;i++){
		    				emailList.add(addrArr[i]);
		    			}
		    		}
		    	}
				result = sendMail(from,emailList,subject,message);
			} 
			
			if (emailLog==null){
				if (result==null && !pending&& !expired) return;
				emailLog =new EmailLog();
			}
			if(!pending && !expired){
				if (result==null) {
					emailLog.setStatus(StatusService.getInstance().findbyTypeandCode("email","sent").getCode());
			    	emailLog.setRcptTo("");
				} else if (result.size()==0 || result.size()==emailList.size()){
					emailLog.setStatus(StatusService.getInstance().findbyTypeandCode("email","broken").getCode());
			    	emailLog.setRcptTo("");
				} else {
					emailLog.setStatus(StatusService.getInstance().findbyTypeandCode("email", "sending").getCode());
		    		Iterator addressItr=result.iterator();
		    		StringBuffer toAddrBuff=new StringBuffer("");
		    		boolean first=true;
		    		while (addressItr.hasNext()) {
						String iaddr = (String) addressItr.next();
						if (first) {
							toAddrBuff.append(iaddr);
							first=false;
						} else toAddrBuff.append(":"+iaddr);
					}
		    		emailLog.setRcptTo(toAddrBuff.toString());
				}
			} else if (pending) {
				emailLog.setStatus(StatusService.getInstance().findbyTypeandCode("email","pending").getCode());
		    	emailLog.setRcptTo("");
			} else if (expired) {
				emailLog.setStatus(StatusService.getInstance().findbyTypeandCode("email","expired").getCode());
		    	emailLog.setRcptTo("");
			}
	    	emailLog.setLastTrialDate(now);
	    	emailLog.setDueDate(news.getStartDate());
	    	emailLog.setMessage("");
		
			if(emailLog.getRetry()==null){
				emailLog.setRetry(ApplicationConstant.retryMail);
			} else {emailLog.setRetry(emailLog.getRetry()-1);}
			emailLog.setSender(ApplicationFactory.getAdminEmail());
			emailLog.setSentDate(null);

			emailLog.setSubject(news.getId().toString());
			emailLog.setType(LookupService.getInstance().findbyTypeandCode("email", "news").getCode());
			EmailLogService.getInstance().update(emailLog);
    	} catch(Exception e){
    		log.error("Error cannot save to email log",e);
    	}
    }*/
    
  public static List sendMail(String from,List toAddress,String subject,String message) throws Exception {
	init();
	if (toAddress==null || toAddress.isEmpty()) {
		log.error("Send Email failed, email has no to_address !");
		return null; //new LinkedList();
	}
    List unsentAddress=new LinkedList();
    unsentAddress.addAll(toAddress);
    if (subject==null) subject="No Specific Subject";
    try{
    	if (from==null ||from.length()==0) from=adminEmail;
    	Session session;
    	Properties props = new Properties();
        props.put("mail.transport.protocol", "SMTP");
        props.put("mail.smtp.host",ipSmtp);
        if (needSMTPAuth) {
        	props.put("mail.smtp.auth", "true");
            SimpleAuthenticator auth = new SimpleAuthenticator();
            auth.setUsername(username);
            auth.setPassword(password);

            session = Session.getInstance(props, auth);
            session.setDebug(false);
        }else  {
        	props.put("mail.smtp.auth", "false");
            session = Session.getInstance(props);
            session.setDebug(false);
        }


        if (from==null) from=new String(username);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));

        List validAddress=new LinkedList();
        Iterator toAddrItr=toAddress.iterator();
        while (toAddrItr.hasNext()) {
			String istraddr = (String) toAddrItr.next();
	        try {
	        	validAddress.add(new InternetAddress(istraddr));
	        	unsentAddress.remove(istraddr);
			} catch (Exception e) {
				log.error("Invalid email address :"+istraddr,e);
			}
		}
        if (validAddress.isEmpty()) {
        	log.error("Send Email failed,all to_address are invalid !");
        	return unsentAddress;
        } 
        InternetAddress toaddress[]=new InternetAddress[validAddress.size()];
        toAddrItr=validAddress.iterator();
        int i=0;
        while (toAddrItr.hasNext()) {
        	InternetAddress iaddress = (InternetAddress) toAddrItr.next();
			toaddress[i]=iaddress;
			i++;
		}
        
        msg.setRecipients(javax.mail.Message.RecipientType.TO,toaddress);

        msg.setSubject(subject);
        msg.setSentDate(new java.util.Date());

        MimeBodyPart mbp = new MimeBodyPart();
        mbp.setContent(message,"text/html");
        Multipart mp = new MimeMultipart();
        mp.addBodyPart(mbp);
        msg.setContent(mp);

        try {
        	Transport.send(msg,toaddress);
        	return  null;
        }catch(SendFailedException ex){
        	//Transport.send(msg,ex.getValidUnsentAddresses());
        	//System.out.println(ex.getMessage());
        	Address[] unsentArr=ex.getValidUnsentAddresses();
        	if (unsentArr!=null){
	        	for(i=0;i<unsentArr.length;i++){
	        		unsentAddress.add( ((InternetAddress)unsentArr[i]).getAddress());
	        	}
        	} 
        	log.error("Error Sending Message",ex);
        }	

    	return unsentAddress;
    }catch(Exception ex){
    	log.error("Error Sending Email !",ex);
    	//System.err.println(ex);
    	return unsentAddress;
    }
  }

   public static String generateSessionId(String cifCode){
		String result = "";
        try{
        	if(cifCode==null ||cifCode.length()==0) cifCode="SYSTEM001";
        	if (cifCode.length()>20)cifCode.substring(0,cifCode.length()-20);
        	/*
        	int keyLength=40;
        	String sTime=String.valueOf(System.currentTimeMillis());
        	byte[] bTime=sTime.getBytes();
        	cifCode="0000000000000"+cifCode.trim();
        	int len=cifCode.length();
        	cifCode=cifCode.substring(len-,len)
        	byte[] bCif=cifCode.getBytes();
        	*/
        	result =cifCode+"-"+System.currentTimeMillis();//+"-"+seq;
        	if (result.length()>50) {
        		result=result.substring(50);
        	}
		}catch(Exception ex){
		    ex.printStackTrace();
		}
        return result;
    }   

    public static String generateTransactionRefNum(String cifCode,String transType){
		String result = "";
        try{
        	if(transType==null ||transType.length()==0) transType="ENQ";
        	transType=transType.toUpperCase();
        	if(cifCode==null ||cifCode.length()==0) cifCode="SYSTEM001";
        	result=transType+'-'+cifCode+'-'+System.currentTimeMillis();//+"-"+seq;
        	if (result.length()>50) {
        		result=result.substring(50);
        	}
        }catch(Exception ex){
		    ex.printStackTrace();
		}
        return result;
    }   
    
    
    public static String applyTemplate(VelocityContext emailContext,String emailTemplate) throws Exception{
        StringWriter message=new StringWriter();
        
//        VelocityContext emailContext=new VelocityContext();
//        String emailTemplate="";
        try {
//      	Properties prop = new Properties();
//      	prop.put("resource.manager.logwhenfound", "false");
//	        prop.put("resource.loader", "class");
//	        prop.put("class.resource.loader.cache", "true");
//	        prop.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
//	        prop.put("class.resource.loader.modificationCheckInterval", "0");
//	        Velocity.init(prop);

	        Properties p = new Properties();
	        //p.setProperty("file.resource.loader.path", "c:/eclipse/workspace/iBankEder/WebContent/WEB-INF/emailTemplate");
	        String templatePath=ApplicationConstant.contextRealPath;
	        if(templatePath==null || templatePath.length()==0) templatePath="c:/eclipse/workspace/iBankEder/WebContent";
	        p.setProperty("file.resource.loader.path", templatePath+"/WEB-INF/emailTemplate");
	        Velocity.init( p );

	        //emailTemplate=ApplicationConstant.contextRealPath+"/WEB-INF/emailTemplate/"+emailTemplate;
//	        String templateName="/eclipse/workspace/iBankEder/WebContent/WEB-INF/emailTemplate/"+emailTemplate;
//	        System.out.println("templateName:="+templateName);
//	        FileReader fr= new FileReader(templateName);
//	        BufferedReader br= new BufferedReader(fr);
//	        while (br.read()>0){
//	        	//buff.append(br.readLine());
//	        	System.out.println(br.readLine());
//	        }
//	        //System.out.println(buff.toString());
//	        System.out.println("==================================================================================================================================");

	        Template t = Velocity.getTemplate(emailTemplate);	      
	        t.merge(emailContext, message);
	        //System.out.println("Velocity result =["+message.toString()+"]");
	        log.debug("Velocity result =["+message.toString()+"]");
	        return message.toString();
        } catch (Exception e) {
			log.error("Error while applying velocity template !",e);
			throw new Exception("Error while applying velocity template !",e);
        }
    	
    }
    
	public static void applyTemplate(VelocityContext context, String fileName, String template) {
		FileWriter f = null;
		try {
//			String templateName="c:/eclipse/workspace/iBankEder/WebContent/WEB-INF/emailTemplate/"+template;
//		    System.out.println("templateName:="+templateName);
//		    FileReader fr= new FileReader(templateName);
//		    BufferedReader br= new BufferedReader(fr);
//	        while (br.read()>0){
//	        	System.out.println(br.readLine());
//	        }
		    	
//	        Properties prop = new Properties();
//	        prop.put("resource.manager.logwhenfound", "false");
//	        prop.put("resource.loader", "class");
//	        prop.put("class.resource.loader.cache", "true");
//	        prop.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
//	        prop.put("class.resource.loader.modificationCheckInterval", "0");
//	        Velocity.init(prop);

	        Properties p = new Properties();
	        //p.setProperty("file.resource.loader.path", "c:/eclipse/workspace/iBankEder/WebContent/WEB-INF/emailTemplate");
	        String templatePath=ApplicationConstant.contextRealPath;
	        if(templatePath==null || templatePath.length()==0) templatePath="c:/eclipse/workspace/iBankEder/WebContent";
	        p.setProperty("file.resource.loader.path", templatePath+"/WEB-INF/emailTemplate");
	        Velocity.init( p );

	        f = new FileWriter(fileName);	      	      
		    Template t = Velocity.getTemplate(template);	      
		    t.merge(context, f);
		    f.close();	        		

	    } catch (Exception ex) {
	    	//ex.printStackTrace();
	    	log.error("Error applying velocity template", ex);
	    }
	  }

   
	  public static SecretKeySpec genKey(String algorithm) throws Exception{ 
		  KeyGenerator kgen = KeyGenerator.getInstance(algorithm);//"Blowfish");
		  SecretKey skey = kgen.generateKey();
		  byte[] raw = skey.getEncoded();
		  SecretKeySpec skeySpec = new SecretKeySpec(raw,algorithm);// "Blowfish");
		  //System.out.println("skeySpec=["+skeySpec+":"+skey+"]");
		  log.debug("skeySpec=["+skeySpec.toString()+":"+skey.toString()+"]");
		  return skeySpec;
	  }
	  
	  public static byte[] encrypt(SecretKeySpec skeySpec,String message) throws Exception{
		  Cipher cipher = Cipher.getInstance(skeySpec.getAlgorithm());// "Blowfish");
		  cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		  byte[] encrypted = cipher.doFinal(message.getBytes());
		   
//		  for (int i=0; i<encrypted.length; ++i) {
//		    System.out.print(encrypted[i]);
//		  }
//		  System.out.println("");

//		  System.out.println(new String(encrypted));
//		  System.out.println(encrypted);
		  return encrypted;
	  }
	                                         
	  public static String decrypt(SecretKeySpec skeySpec,byte[] encryptedData) throws Exception {
		  Cipher cipher = Cipher.getInstance(skeySpec.getAlgorithm());
		  cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		  byte[] decrypted = cipher.doFinal(encryptedData);
//		  for (int i=0; i >< decrypted.length; ++i) {
//		    System.out.print(decrypted[i]);
//		  }
//		  System.out.println("");
		  String decryptedString = new String(decrypted);
//		  System.out.println(decryptedString);
		  return decryptedString;
	  }
	  
	  
	  public static String encryptData( String source ) {
		  try {
		      // Get our secret key
			  Key key = getKey();
	      
		      // Create the cipher 
		      Cipher desCipher =  Cipher.getInstance("DES/ECB/PKCS5Padding");
	
		      // Initialize the cipher for encryption
		      desCipher.init(Cipher.ENCRYPT_MODE, key);
	
		      // Our cleartext as bytes
		      byte[] cleartext = source.getBytes();
	
		      // Encrypt the cleartext
		      byte[] ciphertext = desCipher.doFinal(cleartext);
	
		      // Return a String representation of the cipher text
		      return getString( ciphertext );
		  } catch( Exception e ) {
			  e.printStackTrace();
		  }
		  return null;
	  }

	  public static String decryptData( String source ) {
		  try {
		      // Get our secret key
		      Key key = getKey();
	
		      // Create the cipher 
		      Cipher desCipher =  Cipher.getInstance("DES/ECB/PKCS5Padding");
	
		      // Encrypt the cleartext
		      byte[] ciphertext = getBytes( source );
	
		      // Initialize the same cipher for decryption
		      desCipher.init(Cipher.DECRYPT_MODE, key);
	
		      // Decrypt the ciphertext
		      byte[] cleartext = desCipher.doFinal(ciphertext);
	
		      // Return the clear text
		      return new String( cleartext );
		  } catch( Exception e ) {
			  e.printStackTrace();
		  }
		  return null;
	  }

	  public static String generateKey() {
	    try {
	      KeyGenerator keygen = KeyGenerator.getInstance("DES");
	      SecretKey desKey = keygen.generateKey();
	      byte[] bytes = desKey.getEncoded();
	      return getString( bytes );
	    } catch( Exception e ) {
	      e.printStackTrace();
	      return null;
	    }
	  }

	  private static Key getKey() {
	    try {
	      byte[] bytes = getBytes( KEY_STRING );
	      DESKeySpec pass = new DESKeySpec( bytes ); 
	      SecretKeyFactory skf = SecretKeyFactory.getInstance("DES"); 
	      SecretKey s = skf.generateSecret(pass); 
	      return s;
	    } catch( Exception e ) {
	      //e.printStackTrace();
	      log.error("Error generating encryption key ",e);
	    }
	    return null;
	  }
	  private static String getString( byte[] bytes ) {
	    StringBuffer sb = new StringBuffer();
	    String s;
	    for (int i = 0; i < bytes.length; i++) {
			s = Integer.toHexString(0x00FF & bytes[i]);
			if (s.length() < 2) s = "0" + s;
			sb.append(s);
		}
	    
	    return sb.toString();
	  }

	  private static byte[] getBytes( String str ) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int n=0; String token;
		while (n<str.length()) {
			if (n+2<=str.length()) token=str.substring(n,n+2);
			else token=str.substring(n,str.length());
			int i = Integer.valueOf(token, 16).intValue();
			bos.write( ( byte )i );
			n=n+2;
		}
		return bos.toByteArray();  
	  }
	  
	  public static void showProviders() {
	    try {
	      Provider[] providers = Security.getProviders();
	      for( int i=0; i<providers.length; i++ ) {
	        log.debug( "Provider: " +  providers[ i ].getName() + ", " + providers[ i ].getInfo() );
	        for( Iterator itr = providers[ i ].keySet().iterator(); itr.hasNext(); ) {
	          String key = ( String )itr.next();
	          String value = ( String )providers[ i ].get( key );
	          log.debug( "\t" + key + " = " + value );
	        }
	      }
	    } catch( Exception e ) {
	      //e.printStackTrace();
	      log.error("Error showProvider",e);
	    }
	  }

	public static void main( String[] args ) {
		try {
//			String[] param=new String[] {"Mr.","Martinus","Magic"};
//			System.out.println(showErrorMessage("0001",param,null));
//			DaemonRunner.getInstance().start();
			
			
//			CorebankParamService coreService=CorebankParamService.getInstance();
//			Iterator itr =coreService.getList(null,null).iterator();
//			String data;CorebankParam iParam;				
//			while (itr.hasNext()) {
//				iParam = (CorebankParam) itr.next();
//				System.out.println(iParam.getSvalue());
//				data=iParam.getSvalue();
//				iParam.setSvalue(encryptData(data));
//				coreService.update(iParam);
//			}
//			
//			SystemParameterService systemService=SystemParameterService.getInstance();
//			itr =systemService.getList(null,null).iterator();
//			SystemParameter sParam;				
//			while (itr.hasNext()) {
//				sParam = (SystemParameter) itr.next();
//				System.out.println(sParam.getSvalue());
//				data=sParam.getSvalue();
//				sParam.setSvalue(encryptData(data));
//				systemService.update(sParam);
//			}
			
		 } catch (Exception e) {
			// TODO: handle exception
		 }
	 }
	  public static String showErrorMessage(String errorCode,String[] param) {
		  return ApplicationFactory.showErrorMessage(errorCode,param,null);
	  }
	
	  public static String showErrorMessage(String errorCode,String[] param,String language) {
		String languageKey="";   
		if (language!=null) languageKey="_"+language.toUpperCase();
		String errMessage=null;
		log.debug(errorCode+":"+languageKey);
		Properties msgProps=ApplicationFactory.getMessageProperty(languageKey);
		if (msgProps!=null) {
			try {
		    	errMessage=msgProps.getProperty(ApplicationConstant.errorkeyPrefix+errorCode);
		    	log.debug(ApplicationConstant.errorkeyPrefix+errorCode+"="+errMessage);
		    	errMessage = mergeParam(errMessage,param);
		    } catch( Exception e ) {
		    	errMessage=null;
		    	log.error("Exception showError, can't find error message !",e);
		    }
		} else log.error("Exception showError, can't find error message properties !");
		
	    if (errMessage==null)  errMessage="Sorry system can't find message for error code #"+errorCode; //+(language!=null?" in "+language :"");
	    return errMessage;
	  }
	  
	public static Properties getMessageProperty(String languageKey) {
		if (ApplicationConstant.propertyMap==null)ApplicationConstant.propertyMap=new HashMap(); 
		if (ApplicationConstant.propertyMap.keySet().contains(languageKey)) {
			return (Properties) ApplicationConstant.propertyMap.get(languageKey);
		}
		String ctxPath= ApplicationConstant.contextRealPath;
	    String filename = ctxPath+ApplicationConstant.msgPropertyName+languageKey+".properties";
	    log.debug("Start loading property from "+filename);
	    try {
	    	File f = new File(filename);
//	      	System.out.println("Loading file " + filename + " for parameter");
	        try {
	        	log.debug("Loading property from file : " + f.getCanonicalPath());
	        	FileInputStream fis = new FileInputStream(f);
	        	Properties props = new Properties();
	        	props.load(fis);
	        	fis.close();
	        	log.debug("Succesfully loading properties");
	        	ApplicationConstant.propertyMap.put(languageKey,props);
	        	return props;
	        } catch (Exception e) {
	        	ApplicationConstant.propertyMap.put(languageKey,null);
	        	log.error("File not found. Failed to load properties !",e);
	        	//e.printStackTrace();
	        	return null;
	        }
	    } catch (Exception ex) {
	      //System.out.println("File not found. Failed to load properties !");
	      //ex.printStackTrace();
	      log.error("File not found. Failed to load properties !",ex);
	    }
	    return null;
	  }

	  public static String mergeParam(String errMessage,String[] param){
		  if (errMessage==null) return null;
		  if (errMessage.indexOf("{")<0) return errMessage;
		  String merged=errMessage;
		  int i=0;
		  while (param!=null && i < param.length){
			  merged=merged.replaceAll("\\{"+i+"\\}",param[i]);
			  i++;
		  }
		  if (merged.indexOf("{")>0)  merged=merged.replaceAll("\\{\\d+\\}","");
		  return merged;
	  }
	  
	  //public static void initLogger() {
//		  if (ApplicationConstant.log4jfile==null || ApplicationConstant.log4jfile.length()==0) ApplicationConstant.log4jfile="C:/eclipse/workspace/iBankEder/WebContent/WEB-INF/log4j.lcf";
//		  PropertyConfigurator.configure(ApplicationConstant.log4jfile);
//		  org.apache.log4j.
//		  log=Logger.getLogger("com.ams.apps.shared.ApplicationFactory");
//		  log.debug("Log 4J inited="+ApplicationConstant.log4jfile);
	  //}
	  
	  public static String generatePassword() {
		  Random randomizer=new Random();
		  //TODO Generate Random Password;
		  //byte[] byteArr=randomizer.nextBytes(20);
		  return "123456";
	  }

	  public static String getNormSequence(String prefix,long seq,int length) {
		  String norm="00000";
		  String num=String.valueOf(seq);
		  int prefixLength=(prefix!=null?prefix.length():0);
		  while ((norm.length()+num.length()+prefixLength)<length) norm=norm+norm;
		  norm=norm+num;
		  return norm.substring(norm.length()-length,length);
	  }

	  public static String generateUserActivationKey(String cifCode){
		  //TODO Fix User Activation Code Generation;
		  String now= "0000000000"+String.valueOf(System.currentTimeMillis());
		  now= now.substring(now.length()-15);
		  String activationCode="ACT-"+now.substring(0,8)+cifCode+now.substring(8);
		  System.out.println("Generated ActivationCode="+activationCode);
		  return activationCode;
	  }
	  
	  public static String generateAdminUserActivationKey(String loginName){
		  //TODO Fix Admin User Activation Code Generation;
		  String now= "0000000000"+String.valueOf(System.currentTimeMillis());
		  now= now.substring(now.length()-15);
		  String activationCode="ADM-"+now.substring(0,8)+loginName+now.substring(8);
		  System.out.println("Generated ActivationCode="+activationCode);
		  return activationCode;
	  }
	  
}


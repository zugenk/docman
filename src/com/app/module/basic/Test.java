package com.app.module.basic;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.app.module.document.DocumentManager;
import com.app.module.document.RepositoryManager;
import com.app.shared.ApplicationConstant;
import com.app.shared.ApplicationFactory;
import com.mongodb.util.JSON;
import com.simas.webservice.Utility;

public class Test {

	
	  public static void main(String[] args) {
			try {
			//	System.out.println(HttpStatus.UNAUTHORIZED+":"+HttpStatus.BAD_REQUEST+":"+HttpStatus.FORBIDDEN+":"+HttpStatus.MOVED_PERMANENTLY+":");
				//Object test="21322";
		//		Object test=new Integer(21322);
		//		Object test=new Long(21322);
				//long x=(Long) test;
				//System.out.println(""+x);
				
			//	long x=Long.parseLong((String) test);
		//		System.out.println(""+toLong(test)/2); 
//				init();
//				Document response=new Document("errorMessage","error.unauthorized");
//				ResponseEntity<Document> reply=reply(response);
//				System.out.println(Utility.debug(reply.getHeaders()));
				
			/*	List<String> test=new LinkedList<String>();
				test.add("Satu");
				test.add("Dua");
				test.add("Tiga");
				test.add("Empat");
				test.add("Lima");
				System.out.println(JSON.serialize(test));*/
				
			//	String jsonList="[ { \"id\": \"cafebabe-cafe-babe-cafe-babecafebabe\",  \"text\": \"/\",  \"parent\": \"#\"  }, {  \"id\": \"565dd44a-32cb-4cf4-8f2b-e25d16a0df24\",  \"text\": \"application-pdf\", \"parent\": \"cafebabe-cafe-babe-cafe-babecafebabe\" } ]";
			//	System.out.println(Utility.debug(JSON.parse(jsonList)));
		
				//RepositoryManager.getTree();
				//DocumentManager.getRepoFolder(null,"VIDEO");
			//	System.out.println("============EOD=========");
				String[] sArr=new String[]{"12345678","password","admin","seriusamat","kerenabis"};
				for (int i = 0; i < sArr.length; i++) {
					System.out.println("["+sArr[i]+"] = ["+ApplicationFactory.encrypt(sArr[i])+"]");
				}
//				System.out.println(ApplicationFactory.encrypt("12345678")+" : "+ApplicationFactory.encrypt("password")+" : "+ApplicationFactory.encrypt("admin")+" : "+ApplicationFactory.encrypt("seriusamat"));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
}

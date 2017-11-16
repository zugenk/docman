package com.app.module.document;

import java.io.File;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.log4j.Logger;
import org.apache.struts.taglib.tiles.GetTag;
import org.bson.Document;
import org.json.HTTP;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.app.module.basic.BaseUtil;
import com.app.module.basic.LoginManager;
import com.simas.webservice.Utility;

public class RepositoryManager extends BaseUtil{
	private static Logger log = Logger.getLogger(RepositoryManager.class.getName());
//	public static String REPO_ENDPOINT_URL="http://52.187.54.220:8081/PHIDataEngine";
//	public static String REPO_ENDPOINT_URL="http://127.0.0.1:8081/PHIDataEngine";
	
//	public static String REPO_ENDPOINT_URL="http://128.199.128.32:8080/DocumentManager/rest/v1";
//	public static String REPO_ENDPOINT_URL="http://localhost:8080/DocumentManager/rest/v1";

	public static String ContentType=MediaType.APPLICATION_JSON_VALUE;

	public static List getTree() {
		String servicePath="/file/ajax_get_directory_tree";
		return restExchangeList(null, servicePath, HttpMethod.GET, null, ContentType);
	}
	
	public static Document getFolderContent(String folderId) {
		String servicePath="/file/ajax_get_directory?folderid="+folderId;
		return restExchange(null, servicePath, HttpMethod.GET, null, ContentType);
 	}

	public static Document createFolder(String folderName, String parentFolderId) {
		String servicePath="/file/ajax_file_operation?action=new_folder&folder_name="+folderName+"&destination="+parentFolderId;
		return restExchange(null, servicePath, HttpMethod.POST, null, ContentType);
	}
	
	public static Document renameFolder(String newFolderName, String folderId) {
		String servicePath="/file/ajax_file_operation?action=rename&folder_name="+newFolderName+"&destination="+folderId;
		return restExchange(null, servicePath, HttpMethod.POST, null, ContentType);
	}
	
	
	public static Document uploadFile(String folderId,File file,boolean  overwrite) {
		String servicePath="/datapreparation/ajax_upload";
//		String servicePath="/document/upload";
	 
		LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("file",  new ClassPathResource(file.getName())); //file); //
//		map.add("desc", "Coba dari RepoManager");
		map.add("folderId",folderId);
		map.add("overwrite",(overwrite?"true":"false"));
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new    HttpEntity<LinkedMultiValueMap<String, Object>>(
		                    map, headers);
		ResponseEntity<Document> result = restTemplate.exchange(
		                    REPO_BASE_URL + servicePath, HttpMethod.POST, requestEntity,
		                    Document.class);
		System.out.println(Utility.debug(result));
		return result.getBody();
//		return null;
	}
	
	public static ResponseEntity<String> downloadFile(String fileId) {
		RestTemplate restTemplate = new RestTemplate();
		String servicePath="/file/ajax_file_operation?api_key="+REPO_API_KEY+"&action=download&fileid="+fileId;
		//String servicePath="/file/ajax_file_operation?action=download&api_key="+REPO_API_KEY+"&fileid="+fileId
		//return restExchange(null, servicePath, HttpMethod.GET, null, ContentType);
		return restTemplate.getForEntity(REPO_BASE_URL +servicePath ,String.class);
	}

	
	
	public static Document renameFile(String newFileName, String fileId) {
		String servicePath="/file/ajax_file_operation?api_key="+REPO_API_KEY+"&action=new_folder&folder_name="+newFileName+"&destination="+fileId;
		return restExchange(null, servicePath, HttpMethod.POST, null, ContentType);
	}
	
	public static Document copyFile(Map[] fileArr, String sourceFolderId, String destinationFolderId) {
		StringBuffer params=new StringBuffer("&action=copy");
		for (int i = 0; i < fileArr.length; i++) {
			params.append("&item["+i+"][id]="+fileArr[i].get("id"));
			params.append("&item["+i+"][filename]="+fileArr[i].get("name"));
		}
		params.append("&item_count="+fileArr.length);
		String servicePath="/file/ajax_file_operation?api_key="+REPO_API_KEY+"&origin="+sourceFolderId+"&destination="+destinationFolderId+params.toString();
		return restExchange(null, servicePath, HttpMethod.POST, null, ContentType);
	}
	
	public static Document moveFile(Map[] fileArr, String sourceFolderId, String destinationFolderId) {
		StringBuffer params=new StringBuffer("&action=move");
		for (int i = 0; i < fileArr.length; i++) {
			params.append("&item["+i+"][id]="+fileArr[i].get("id"));
			params.append("&item["+i+"][filename]="+fileArr[i].get("name"));
		}
		params.append("&item_count="+fileArr.length);
		String servicePath="/file/ajax_file_operation?api_key="+REPO_API_KEY+"&origin="+sourceFolderId+"&destination="+destinationFolderId+params.toString();
		return restExchange(null, servicePath, HttpMethod.POST, null, ContentType);
	}
	public static Document deleteFile(Map[] fileArr) {
		StringBuffer params=new StringBuffer("api_key="+REPO_API_KEY+"&action=delete");
		for (int i = 0; i < fileArr.length; i++) {
			params.append("&item["+i+"][id]="+fileArr[i].get("id"));
			params.append("&item["+i+"][filename]="+fileArr[i].get("name"));
		}
		params.append("&item_count="+fileArr.length);
		String servicePath="/file/ajax_file_operation?"+params.toString();
		return restExchange(null, servicePath, HttpMethod.POST, null, ContentType);
	}
	
		
	public static Document search(String keyword) {
		String servicePath="/search/query?api_key="+REPO_API_KEY+"&querytext="+keyword;
		return restExchange(null, servicePath, HttpMethod.POST, null, ContentType);
	}
	
	public static Document advanceSearch(String keyword) {
		String servicePath="/search/query?api_key="+REPO_API_KEY+"&querytext="+keyword;
		return restExchange(null, servicePath, HttpMethod.POST, null, ContentType);
	}
	
	public static Document addMeta(String fileId,String metaData) {
		String servicePath="/file/ajax_meta_operation?action=addmeta&api_key="+REPO_API_KEY+"&item_id="+fileId+"&meta_string="+metaData;
		return restExchange(null, servicePath, HttpMethod.POST, null, ContentType);
	}
	
	
	
	
	public static void login() {
		
		//String servicePath="/status/list";
		String servicePath="/action/login";
		String user="admin";
		String password="admin";
		String contentType=MediaType.APPLICATION_JSON_VALUE;
		RestTemplate restTemplate=new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	setBasicAuth(user, password,headers);
		Map<String,Object> request=new HashMap<String,Object>(); //null;//
		HttpMethod method=HttpMethod.GET;
	  	ResponseEntity<Document> response=null;
    	try{
	    	HttpEntity httpEntity = new HttpEntity(toMultiValueMap(request),headers);
	    	response=restTemplate.exchange(SERVER_BASE_URL+servicePath, method, httpEntity, Document.class);
	    	System.out.println(Utility.debug(response));
	  	} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static Document restExchange(Map<String, Object> request,String servicePath,HttpMethod method, HttpHeaders headers, String contentType) {
    	RestTemplate restTemplate = new RestTemplate(); // (restUrl.startsWith("https://")?createTemplate():new RestTemplate());
    	//log.debug(Utility.debug(request));
    	log.debug("REST["+method.toString()+"] to ["+servicePath+"]");
    	ResponseEntity<Document> response=null;
    	try{
	    	HttpEntity httpEntity = new HttpEntity(toMultiValueMap(request),headers);
	    	response=restTemplate.exchange(REPO_BASE_URL+servicePath, method, httpEntity, Document.class);
	  	} catch (Exception e) {
			e.printStackTrace();
		}
	    log.debug(Utility.debug(response.getBody()));
	    return response.getBody();
	}

	
	public static List restExchangeList(Map<String, Object> request,String servicePath,HttpMethod method, HttpHeaders headers, String contentType) {
    	RestTemplate restTemplate = new RestTemplate(); // (restUrl.startsWith("https://")?createTemplate():new RestTemplate());
//    	log.debug(Utility.debug(request));
    	log.debug("REST["+method.toString()+"] to ["+servicePath+"]");
//    	try{
	    	HttpEntity httpEntity = new HttpEntity(toMultiValueMap(request),headers);
	    	ResponseEntity<List> response=restTemplate.exchange(REPO_BASE_URL+servicePath, method, httpEntity, List.class);
//      	} catch (Exception e) {
//    		e.printStackTrace();
//    	}
	    log.debug(Utility.debug(response.getBody()));
	    return response.getBody();
	}
	
/*	public static RestTemplate createTemplate() throws Exception{
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
		        .loadTrustMaterial(null, acceptingTrustStrategy)
		        .build();

		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

		CloseableHttpClient httpClient = HttpClients.custom()
		        .setSSLSocketFactory(csf)
		        .build();
																																																																																																					
		HttpComponentsClientHttpRequestFactory requestFactory =
		        new HttpComponentsClientHttpRequestFactory();

		requestFactory.setHttpClient(httpClient);

		RestTemplate restTemplate = new RestTemplate(requestFactory);
		return restTemplate;
	}												
	*/
	
	public static void main(String[] args) {
//		test();

		String folderId="f33ec991-1f1d-4327-a7ca-71e24ad3f04f";
//		getTree();
//		getFolderContent(folderId);
		//File file=new File("/Users/it.atsbanksinarmas.com/Documents/Invoice - SeatBelt.pdf" );
		
		System.out.println("====================================================================================================");
		File file=new File("Invoice - SeatBelt.pdf" );
		if (file.exists()) {
			System.out.println("File exist.. ");
			uploadFile(folderId, file, false);
		} else System.out.println("File not found..!!");
		
		System.out.println("====================================================================================================");
	//	getFolderContent(folderId);
		System.out.println("====================================================================================================");
		
//		createFolder("CreatedFromJava",folderId);
//		renameFolder("renamedFromJava",folderId);
//		search("color");
	}
	
}

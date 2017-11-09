package com.app.module.document;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.taglib.tiles.GetTag;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.app.module.basic.BaseUtil;
import com.simas.webservice.Utility;

public class RepositoryManager extends BaseUtil{
	public static String REPO_ENDPOINT_URL="http://52.187.54.220:8081/PHIDataEngine";
	
	private void createFolder(String folderName, String parentFolderId) {
		// TODO Auto-generated method stub
		String servicePath="/file/ajax_file_operation?action=new_folder&folder_name="+folderName+"&destination="+parentFolderId;

	}
	
	private void renameFolder(String newFolderName, String folderId) {
		// TODO Auto-generated method stub
		String servicePath="/file/ajax_file_operation?action=rename&folder_name="+newFolderName+"&destination="+folderId;

	}
	
	public static void getTree() {
		String servicePath="/file/ajax_get_directory_tree";
		RestTemplate restTemplate=new RestTemplate();
		List response=null;
    	try {
    		System.out.println("GET from "+ REPO_ENDPOINT_URL+servicePath);
    		response=restTemplate.getForObject(REPO_ENDPOINT_URL+servicePath, List.class);
//    	  	response=restTemplate.getForObject(REPO_ENDPOINT_URL+servicePath, List.class);
    	  	System.out.println("Result="+Utility.debug(response));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
	}
	
	private void getFolderContent() {
		// TODO Auto-generated method stub
		String servicePath="/file/ajax_get_directory?folderid={{folderId}}";

	}
	
	private void uploadFile() {
		// TODO Auto-generated method stub
		String servicePath="/datapreparation/ajax_upload";
		

	}
	
	private void renameFile(String newFileName, String fileId) {
		//return renameFolder(newFileName, fileId);
	}
	
	private void copyFile() {
		// TODO Auto-generated method stub
		String servicePath="";

	}
	
	private void moveFile() {
		// TODO Auto-generated method stub
		String servicePath="";

	}
	
	private void deleteFile() {
		// TODO Auto-generated method stub
		String servicePath="";

	}
	
	private void downloadFile() {
		// TODO Auto-generated method stub
		String servicePath="/file/ajax_file_operation?fileid={{fileid}}&action=download";

	}
	
	private void search(String keyword) {
		// TODO Auto-generated method stub
		String servicePath="";

	}
	
	private void advanceSearch() {
		// TODO Auto-generated method stub
		String servicePath="";

	}
	
	
	public static void test() {
		
		//String url="http://128.199.128.32:8080/DocumentManager/rest/v1";
		String url="http://localhost:8080/DocumentManager/rest/v1";
		//String servicePath="/status/list";
		String servicePath="/action/login";
		String user="admin";
		String password="admin";
		String contentType=MediaType.APPLICATION_JSON_VALUE;
		RestTemplate restTemplate=new RestTemplate();
		
		Map response=null;
    	try {
    		
        	HttpHeaders headers = new HttpHeaders();
        	
        	headers.setContentType(toMediaType(contentType));
        	setBasicAuth(user, password,headers);
        	
        	HttpEntity<MultiValueMap<String,Object>> requestGet=new HttpEntity<MultiValueMap<String,Object>>(toMultiValueMap(null),headers);//(toMultiValueMap(request),headers);
        	response= restTemplate.getForObject (url+servicePath, Map.class);


    		
    		System.out.println("GET from "+ url+servicePath);
//    		response=restTemplate.execute(url, method, requestCallback, responseExtractor, urlVariables)ForObject(REPO_ENDPOINT_URL+servicePath, List.class);
    	  	//response=restTemplate.getForObject(url+servicePath, Map.class);
    	  	System.out.println("Result="+Utility.debug(response));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
	}
	

	
	public static void main(String[] args) {
//		getTree();
		test();
	}
	
}

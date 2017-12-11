package com.app.module.rest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.app.module.basic.BaseUtil;
import com.app.module.basic.LoginManager;
import com.app.module.document.DocumentManager;
import com.app.module.document.RepositoryManager;
import com.mongodb.util.JSON;
import com.simas.webservice.Utility;



@Controller
@RequestMapping("/v1/document")
public class DocumentController extends BaseUtil{
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DocumentController.class);
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(100000);
	    return new CommonsMultipartResolver();
	}

	@RequestMapping(value = "tree",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> getTree(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth) {
		Document response=new Document();
		try {
			log.trace("/v1/document/tree = "+ipassport); 
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",DocumentManager.getTree(iPass,null));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "/{ID}/tree",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> getTree(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="ID") String startId) {
		Document response=new Document();
		try {
			log.trace("/v1/document/"+startId+"/tree = "+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",DocumentManager.getTree(iPass,startId));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "/{ID}/fullTree",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> getFullTree(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="ID") String startId) {
		Document response=new Document();
		try {
			log.trace("/v1/document/"+startId+"/fullTree = "+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",DocumentManager.getFullTree(iPass,startId));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "/{ID}/pushTree",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> pushTree(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="ID") String startId) {
		Document response=new Document();
		try {
			log.trace("/v1/document/"+startId+"/pushTree = "+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			List result=DocumentManager.getFullTree(iPass,startId);
			response.put("result",result); //DocumentManager.getFullTree(iPass,startId));
			String rootRepoId=(String) ((Map) result.get(0)).get("repository_id");
			RepositoryManager.addMeta(rootRepoId, JSON.serialize(result));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "/{ID}/downline",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> getDownline(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="ID") String startId
			) {
		Document response=new Document();
		try {
			log.trace("/v1/document/"+startId+"/downline = "+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",DocumentManager.getDownline(iPass,startId));
			return reply(response);  
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "/{ID}/upline",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> getUpline(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="ID") String startId) {
		Document response=new Document();
		try {
			log.trace("/v1/document/"+startId+"/upline = "+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",DocumentManager.getUpline(iPass,startId));
			return reply(response);  
		
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response);  
	}
	
	
	@RequestMapping(value = "myList",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> myList(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestParam(value = "start", required = false) String start) {
		Document response=new Document();
		try {
			log.trace("/v1/document/myList = "+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			log.debug(" My Document list by "+ iPass.getString("loginName") );
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", DocumentManager.myList(iPass, start));
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			log.error("Error geting my Document List",e);
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "ownBy/{userId}",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> ownBy(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="userId") String userId,
			@RequestParam(value = "startIdx", required = false) String start) {
		Document response=new Document();
		try {
			log.trace("/v1/document/ownBy/"+userId+" = "+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			log.debug(" My Document list by "+ iPass.getString("loginName") );
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", DocumentManager.ownBy(iPass,toLong(userId), start));
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			log.error("Error geting Document-ownByList",e);
		}
		return reply(response);  
	}
	
	
	@RequestMapping(value = "create",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> create(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestBody final Map dataMap) {
		Document response=new Document();
		try {
			log.trace("/v1/document/create/="+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",DocumentManager.create(iPass, dataMap));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response); 
	}
	
	@RequestMapping(value = "{ID}/update",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> update(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestBody final Map dataMap,
			@PathVariable(value="ID") String objId) {
		Document response=new Document();
		try {
			log.trace("/v1/document/"+objId+"/update/="+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",DocumentManager.update(iPass, dataMap, objId));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response); 
	}
	
	@RequestMapping(value = "{ID}/delete",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> delete(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="ID") String objId) {
		Document response=new Document();
		try {
			log.trace("/v1/document/"+objId+"/delete/="+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			DocumentManager.delete(iPass, objId);
			response.put("ipassport",iPass.get("ipassport"));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response); 
	}
	
	@RequestMapping(value = "{ID}/",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> detail(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="ID") String objId) {
		Document response=new Document();
		try {
			log.trace("/v1/document/"+objId+"/ ="+ipassport);
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			response.put("result",DocumentManager.detail(iPass, objId));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response); 
	}
	
	@RequestMapping(value = "list",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Document> list(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestBody final Map dataMap) {
		Document response=new Document();
		try {
			log.trace("/v1/document/list ="+ipassport+" dataMap="+Utility.debug(dataMap));
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", DocumentManager.list(iPass, dataMap));
			return reply(response); 
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return reply(response); 
	}

 
    
    @RequestMapping(value = "/repoFolder/{folderName}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> repoFolder(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable("folderName") String folderName) {
    	Document response=new Document();
    	try {
    		Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			response=DocumentManager.getRepoFolder(iPass, folderName);
	        return reply(response);
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			log.error("Error geting my Document List",e);
		}		
    	return reply(response);

	}
    
	@RequestMapping(value = "/repoDl/{fileId}", method = RequestMethod.GET)
	//public @ResponseBody ResponseEntity<String> repoDl(@PathVariable("fileId") String fileId) {
	public @ResponseBody ResponseEntity<?> repoDl(
		@PathVariable(value="fileId") String fileId,
		@RequestParam(value="itoken", required = false) String itoken,
		@RequestParam(value="ipassport", required = false) String ipassport,
		@RequestParam(value="Authorization", required = false) String basicAuth,
		@RequestParam(value="mode", required=false) String mode) {	
		//String errorMessage="";
		Document response=new Document();
		try {
			log.debug("/v1/document/repoDl/"+fileId);
			Document iPass=LoginManager.authenticate(itoken,ipassport, null);
			DocumentManager.downByRepoId(iPass, fileId);
			
//			RestTemplate restTemplate=new RestTemplate();
//	        ResponseEntity<Resource> resp= restTemplate.getForEntity("http://localhost:8080/DocumentManager/rest/v1/document/download",Resource.class);
	        ResponseEntity<Resource> resp= RepositoryManager.downloadFile(fileId,mode); 
			System.out.println("HTTPStatus:"+resp.getStatusCode());
			System.out.println("HTTPHeaders:"+Utility.debug(resp.getHeaders()));
			System.out.println(resp.getBody());
			return resp;
	        //restTemplate.getForEntity(REPO_BASE_URL + "/file/ajax_file_operation?action=download&api_key="+REPO_API_KEY+"&fileid="+fileId,String.class);
		} catch (Exception e) {
			response.append("errorMessage",e.getMessage());
			log.error("Error geting Document-byRepoId",e);
		}
		return reply(response);  
	}
	
	@RequestMapping(value = "byRepo/{fileId}",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> byRepo(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@PathVariable(value="fileId") String fileId) {
		Document response=new Document();
		try {
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			log.debug(" Get Document by RepoId["+fileId+"] done by"+ iPass.getString("loginName") );
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", DocumentManager.getByRepoId(iPass, fileId));
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			log.error("Error geting Document-byRepoId",e);
		}
		return reply(response);  
	}
	

	
//	static String UPLOAD_FOLDER="./UPLOADED/";
//	RestTemplate restTemplate= new RestTemplate();
	/*		
	@RequestMapping(value="download", method=RequestMethod.GET)
	public void getDownload(HttpServletResponse response) {

		// Get your file stream from wherever.
		InputStream myStream = new FileInputStream("Invoice - SeatBelt.pdf");

		// Set the content type and attachment header.
		response.addHeader("Content-disposition", "attachment;filename=myfilename.txt");
		response.setContentType("txt/plain");

		// Copy the stream to the response's output stream.
		IOUtils.copy(myStream, response.getOutputStream());
		response.flushBuffer();
	}*/
	

//			+ "/file/ajax_get_directory_tree";
			
/*
	@RequestMapping(value = "/restProxy/{restUrlPath}", method = RequestMethod.GET)
	public @ResponseBody String restProxyGet(@PathVariable("restUrlPath") String restUrlPath) {
		System.out.println("====>>> proxy GET :"+restUrlPath);
	    return restTemplate.getForObject(targetBaseUrl+ "/" + restUrlPath, String.class);
	}

	@RequestMapping(value = "/restProxy/{restUrlPath}", method = RequestMethod.POST)
	public @ResponseBody String restProxyPost(@PathVariable("restUrlPath") String restUrlPath, @RequestBody String body, @RequestParam MultiValueMap<String,String> params) {
        System.out.println("====>>> proxy POST :"+restUrlPath+": "+Utility.debug(params));
//	    ResponseEntity<String> response= 
//	    return	restTemplate.postForEntity(targetBaseUrl + "/" + restUrlPath, (params!=null?params:body),String.class);
        return	restTemplate.postForObject(targetBaseUrl + "/" + restUrlPath, (params!=null?params:body),String.class);
	}
*/
	

/*	
    @RequestMapping(value="/upload", method=RequestMethod.POST )
    public @ResponseBody String singleSave(@RequestParam("file") MultipartFile file, @RequestParam("desc") String desc ){
    	log.debug("Masuk kok ke sini Upload!!");
        System.out.println("File Description:"+desc);
        new File(UPLOAD_FOLDER).mkdirs();
        String fileName = null;
        if (!file.isEmpty()) {
            try {
                fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                File target=new File(UPLOAD_FOLDER + fileName);
                BufferedOutputStream buffStream = 
                        new BufferedOutputStream(new FileOutputStream(target));
                buffStream.write(bytes);
                buffStream.close();
                return "You have successfully uploaded " + fileName;
            } catch (Exception e) {
                return "You failed to upload " + fileName + ": " + e.getMessage();
            }
        } else {
            return "Unable to upload. File is empty.";
        }
    }
    
    
    @RequestMapping(value="/multipleUpload")
    public String multiUpload(){
    	log.debug("Masuk kok ke sini multiUpload!!");
        return "multipleUpload";
    }
    @RequestMapping(value="/multipleSave", method=RequestMethod.POST )
    public @ResponseBody String multipleSave(@RequestParam("file") MultipartFile[] files){
    	log.debug("Masuk kok ke sini multiSave!!");
        String fileName = null;
        new File(UPLOAD_FOLDER).mkdirs();
        String msg = "";
        if (files != null && files.length >0) {
            for(int i =0 ;i< files.length; i++){
                try {
                    fileName = files[i].getOriginalFilename();
                    byte[] bytes = files[i].getBytes();
                    File target=new File(UPLOAD_FOLDER + fileName);
                    BufferedOutputStream buffStream = 
                            new BufferedOutputStream(new FileOutputStream(target));
                    buffStream.write(bytes);
                    buffStream.close();
                    msg += "You have successfully uploaded " + fileName +"<br/>";
                } catch (Exception e) {
                    return "You failed to upload " + fileName + ": " + e.getMessage() +"<br/>";
                }
            }
            return msg;
        } else {
            return "Unable to upload. File is empty.";
        }
    }
  */ 	
/*	
	@RequestMapping(value = "getRepo/{fileId}",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Document> getRepo(
			@RequestHeader(value="itoken", required = false) String itoken,
			@RequestHeader(value="ipassport", required = false) String ipassport,
			@RequestHeader(value="Authorization", required = false) String basicAuth,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="fileId") String fileId) {
		Document response=new Document();
		try {
			Document iPass=LoginManager.authenticate(itoken,ipassport, basicAuth);
			log.debug(" Get Document by RepoId["+fileId+"] done by"+ iPass.getString("loginName") );
			response.put("ipassport",iPass.get("ipassport"));
			BaseUtil.putList(response,"result", DocumentManager.getByRepoId(iPass, fileId));
			return reply(response);  
			
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
			log.error("Error geting Document-byRepoId",e);
		}
		return reply(response);  
	}
	*/

	
/*	   
    @RequestMapping(value = "/download/{fileId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> downloadRepo(@PathVariable("fileId") String fileId,@RequestParam MultiValueMap<String,String> params) {
//	    ResponseEntity<String> response= 
//	    return	restTemplate.postForEntity(targetBaseUrl + "/" + restUrlPath, (params!=null?params:body),String.class);
    	restTemplate.getForEntity(url, responseType, urlVariables)
        return	restTemplate.getForEntity(targetBaseUrl + "/ajax_file_operation?fileid="+fileId+"&action=download" + restUrlPath, String.class);
	}
*/	
 
	@RequestMapping(value = "download/{fileId}", method = RequestMethod.GET)
	public ResponseEntity<Resource> download(String param) throws IOException {
		///Users/it.atsbanksinarmas.com/Documents/workspace2/DocumentManager/Invoice - SeatBelt.pdf
		File file=new File("/usr/local/tomcat/");//+fileId);
		if(file.exists()) System.out.println(file.getAbsolutePath()+ " exist ");
		else System.out.println("File not Found");
	    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
	    HttpHeaders headers= new HttpHeaders();
	   // headers.setContentDispositionFormData("inline", file.getName());
	    headers.set("Content-Disposition", "inline;filename="+file.getName());
	    headers.setContentLength(file.length());
	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    
	    return new ResponseEntity<Resource>(resource,headers, HttpStatus.OK);
	}
}

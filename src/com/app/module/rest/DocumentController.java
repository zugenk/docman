package com.app.module.rest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;


import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.app.module.basic.LoginManager;
import com.app.module.document.DocumentManager;



@Controller
@RequestMapping("/v1/document")
public class DocumentController {
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DocumentController.class);

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(100000);
	    return new CommonsMultipartResolver();
	}

	@RequestMapping(value = "tree",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map> getTree(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			if (iPass!=null) {
				response.put("result",DocumentManager.getTree(null));
				return new ResponseEntity<Map>(response,HttpStatus.OK);
			}
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/{ID}/tree",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map> getTree(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String startId) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			if (iPass!=null) {
				response.put("result",DocumentManager.getTree(startId));
				return new ResponseEntity<Map>(response,HttpStatus.OK);
			}
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/{ID}/downline",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map> getDownline(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String startId
			) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			if (iPass!=null) {
				response.put("result",DocumentManager.getDownline(startId));
				return new ResponseEntity<Map>(response,HttpStatus.OK);
			}
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/{ID}/upline",produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map> getUpline(
			@RequestHeader(value="ipassport", defaultValue="") String ipassport,
			@RequestHeader(value="Authorization", defaultValue="") String basicAuth,
			@PathVariable(value="ID") String startId) {
		Map response=new HashMap();
		try {
			Document iPass=LoginManager.authenticate(ipassport, basicAuth);
			if (iPass!=null) {
				response.put("result",DocumentManager.getUpline(startId));
				return new ResponseEntity<Map>(response,HttpStatus.OK);
			}
		} catch (Exception e) {
			response.put("errorMessage", e.getMessage());
		}
		return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
	}
	
	
	static String UPLOAD_FOLDER="./UPLOADED/";
	
	
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
		
}

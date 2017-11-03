package com.app.module.document;

import java.util.List;
import java.util.Map;

import com.app.docmgr.service.DocumentService;
import com.app.module.basic.BaseUtil;
import com.app.module.basic.DBQueryManager;
import com.app.shared.PartialList;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


public class DocumentManager {
	public static int itemPerPage=20;

	public static PartialList getDocumentList(int start){
		PartialList resultList=null;
		try {
			String filterParam=null; 
			String orderParam=" ORDER BY document.id ASC ";
			resultList= DocumentService.getInstance().getPartialList(filterParam, orderParam, 0, itemPerPage);
			//if(!resultList.isEmpty()) return true;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return resultList;
	}
	
	
    @RequestMapping(value="/singleUpload")
    public String singleUpload(){
        return "singleUpload";
    }
    @RequestMapping(value="/singleSave", method=RequestMethod.POST )
    public @ResponseBody String singleSave(@RequestParam("file") MultipartFile file, @RequestParam("desc") String desc ){
        System.out.println("File Description:"+desc);
        String fileName = null;
        if (!file.isEmpty()) {
            try {
                fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream = 
                        new BufferedOutputStream(new FileOutputStream(new File("F:/cp/" + fileName)));
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
        return "multipleUpload";
    }
    @RequestMapping(value="/multipleSave", method=RequestMethod.POST )
    public @ResponseBody String multipleSave(@RequestParam("file") MultipartFile[] files){
        String fileName = null;
        String msg = "";
        if (files != null && files.length >0) {
            for(int i =0 ;i< files.length; i++){
                try {
                    fileName = files[i].getOriginalFilename();
                    byte[] bytes = files[i].getBytes();
                    BufferedOutputStream buffStream = 
                            new BufferedOutputStream(new FileOutputStream(new File("F:/cp/" + fileName)));
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
	
	
	
	
	public static List<Map> getTree(String startId)  throws Exception{
		String sqlQuery = " WITH RECURSIVE frm AS ("+
	   " SELECT document.id as id, document.document_number,document.repository_id,document.name,document.id||'' as tree, COALESCE(document.parent_document,0) as parent_document, 0 AS level FROM document "+
	   ((startId==null||startId.length()==0)?" WHERE document.parent_document is null":" WHERE document.id='"+startId+"' ")+
	   " UNION  ALL"+
	   " SELECT f.id as id, f.document_number,f.repository_id, f.name,(c.tree||'.'||f.id) as tree, COALESCE(f.parent_document,0) as parent_document, (c.level + 1) as level FROM frm  c "+
	   " JOIN document f ON f.parent_document = c.id )"+
	   " SELECT * FROM   frm ORDER  BY  frm.tree";
		List list= DBQueryManager.getList("DocumentTree", sqlQuery, null);
		//log.debug(Utility.debug(list));
		return BaseUtil.constructTreeList(list);
	}	
	


	public static List getDownline(String startId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT document.id,  document.document_number,document.repository_id, document.name, document.parent_document, 1 as level FROM document"+
		  " WHERE document.id='"+startId+"' "+
		  " UNION ALL"+
		  " SELECT x.id, x.document_number,x.repository_id, x.name, x.parent_document, (q.level+1) as level FROM document  x"+
		  " JOIN q ON q.id = x.parent_document) "+ 
		  " SELECT * FROM q order by level ASC";
		List list= DBQueryManager.getList("DocumentDownline", sqlQuery, null); //new String[]{startId});
		//log.debug(Utility.debug(list));
		return list;
	}	

	public static List getUpline(String startId) throws Exception{
		String sqlQuery = " WITH RECURSIVE q AS (  SELECT document.id, document.document_number,document.repository_id, document.name, document.parent_document, 1 as level FROM document"+
		  " WHERE document.id='"+startId+"' "+
		  " UNION ALL"+
		  " SELECT x.id, x.document_number,x.repository_id, x.name, x.parent_document, (q.level+1) as level FROM document  x"+
		  " JOIN q ON q.parent_document = x.id) "+ 
		  " SELECT * FROM q order by level desc";
		List list= DBQueryManager.getList("DocumentUpline", sqlQuery, null);// new String[]{startId});
		//log.debug(Utility.debug(list));
		return list;
	}	

	
	
}

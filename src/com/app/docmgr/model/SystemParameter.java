package com.app.docmgr.model;

import java.util.*;




/**
 * SystemParameter generated by hbm2java
 */
public class SystemParameter  implements java.io.Serializable {

    // Fields    

     private Long id;
     private String vgroup;
     private String parameter;
     private String svalue;
     private String description;
     private Status status;
     private Date createdDate;
     private String createdBy;
     private Date lastUpdatedDate;
     private String lastUpdatedBy;
     private Lookup userLevel;


    // Constructors

    /** default constructor */
    public SystemParameter() {
    }
    
    /** constructor with id */
    public SystemParameter(Long id) {
        this.id = id;
    }
   
    
    

    // Property accessors

    /**
     * 
     */
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     */
    public String getVgroup() {
        return this.vgroup;
    }
    
    public void setVgroup(String vgroup) {
        this.vgroup = vgroup;
    }

    /**
     * 
     */
    public String getParameter() {
        return this.parameter;
    }
    
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    /**
     * 
     */
    public String getSvalue() {
        return this.svalue;
    }
    
    public void setSvalue(String svalue) {
        this.svalue = svalue;
    }

    /**
     * 
     */
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     */
    public Status getStatus() {
        return this.status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * 
     */
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * 
     */
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 
     */
    public Date getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }
    
    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    /**
     * 
     */
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }
    
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * 
     */
    public Lookup getUserLevel() {
        return this.userLevel;
    }
    
    public void setUserLevel(Lookup userLevel) {
        this.userLevel = userLevel;
    }




}
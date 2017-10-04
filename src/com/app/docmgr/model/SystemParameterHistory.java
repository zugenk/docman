package com.app.docmgr.model;

import java.util.*;




/**
 * SystemParameterHistory generated by hbm2java
 */
public class SystemParameterHistory  implements java.io.Serializable {

    // Fields    

     private Long id;
     private Date historyDate;
     private String historyBy;
     private Long auditTrailId;
     private Long systemParameterId;
     private String vgroup;
     private String parameter;
     private String svalue;
     private String description;
     private Status status;
     private Date createdDate;
     private String createdBy;
     private Date lastUpdatedDate;
     private String lastUpdatedBy;


    // Constructors

    /** default constructor */
    public SystemParameterHistory() {
    }
    
    /** constructor with id */
    public SystemParameterHistory(Long id) {
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
    public Date getHistoryDate() {
        return this.historyDate;
    }
    
    public void setHistoryDate(Date historyDate) {
        this.historyDate = historyDate;
    }

    /**
     * 
     */
    public String getHistoryBy() {
        return this.historyBy;
    }
    
    public void setHistoryBy(String historyBy) {
        this.historyBy = historyBy;
    }

    /**
     * 
     */
    public Long getAuditTrailId() {
        return this.auditTrailId;
    }
    
    public void setAuditTrailId(Long auditTrailId) {
        this.auditTrailId = auditTrailId;
    }

    /**
     * 
     */
    public Long getSystemParameterId() {
        return this.systemParameterId;
    }
    
    public void setSystemParameterId(Long systemParameterId) {
        this.systemParameterId = systemParameterId;
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




}
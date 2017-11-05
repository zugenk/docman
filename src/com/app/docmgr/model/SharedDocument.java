package com.app.docmgr.model;

import java.util.*;




/**
 * SharedDocument generated by hbm2java
 */
public class SharedDocument  implements java.io.Serializable {

    // Fields    

     private Long id;
     private Document document;
     private User targetUser;
     private Organization targetOrganization;
     private String grantAction;
     private Status status;
     private Date createdDate;
     private String createdBy;
     private Date lastUpdatedDate;
     private String lastUpdatedBy;


    // Constructors

    /** default constructor */
    public SharedDocument() {
    }
    
    /** constructor with id */
    public SharedDocument(Long id) {
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
    public Document getDocument() {
        return this.document;
    }
    
    public void setDocument(Document document) {
        this.document = document;
    }

    /**
     * 
     */
    public User getTargetUser() {
        return this.targetUser;
    }
    
    public void setTargetUser(User targetUser) {
        this.targetUser = targetUser;
    }

    /**
     * 
     */
    public Organization getTargetOrganization() {
        return this.targetOrganization;
    }
    
    public void setTargetOrganization(Organization targetOrganization) {
        this.targetOrganization = targetOrganization;
    }

    /**
     * 
     */
    public String getGrantAction() {
        return this.grantAction;
    }
    
    public void setGrantAction(String grantAction) {
        this.grantAction = grantAction;
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
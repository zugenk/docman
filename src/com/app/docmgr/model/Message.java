package com.app.docmgr.model;

import java.util.*;




/**
 * Message generated by hbm2java
 */
public class Message  implements java.io.Serializable {

    // Fields    

     private Long id;
     private String content;
     private Lookup postType;
     private Status status;
     private Date createdDate;
     private String createdBy;
     private Date lastUpdatedDate;
     private String lastUpdatedBy;
     private String filterCode;
     private Topic topic;
     private Message parent;


    // Constructors

    /** default constructor */
    public Message() {
    }
    
    /** constructor with id */
    public Message(Long id) {
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
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     */
    public Lookup getPostType() {
        return this.postType;
    }
    
    public void setPostType(Lookup postType) {
        this.postType = postType;
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
    public String getFilterCode() {
        return this.filterCode;
    }
    
    public void setFilterCode(String filterCode) {
        this.filterCode = filterCode;
    }

    /**
     * 
     */
    public Topic getTopic() {
        return this.topic;
    }
    
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    /**
     * 
     */
    public Message getParent() {
        return this.parent;
    }
    
    public void setParent(Message parent) {
        this.parent = parent;
    }




}
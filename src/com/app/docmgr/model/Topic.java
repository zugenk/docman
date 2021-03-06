package com.app.docmgr.model;

import java.util.*;




/**
 * Topic generated by hbm2java
 */
public class Topic  implements java.io.Serializable {

    // Fields    

     private Long id;
     private String code;
     private String icon;
     private String name;
     private String description;
     private Integer numberOfLike;
     private Integer numberOfPost;
     private Status status;
     private Date createdDate;
     private String createdBy;
     private Date lastUpdatedDate;
     private String lastUpdatedBy;
     private String filterCode;
     private Forum forum;
     private Set subscribers;
     private Message latestMessage;
     private String mostPostedBy;


    // Constructors

    /** default constructor */
    public Topic() {
    }
    
    /** constructor with id */
    public Topic(Long id) {
        this.id = id;
    }
   
    
    @Override
    public boolean equals(Object obj) {
    	if(obj==null) return false;
    	if(obj instanceof Topic) return this.getId().longValue()==((Topic) obj).getId();
    	return super.equals(obj);
    }
    
    @Override
    public int hashCode() {
    	return 31*id.hashCode();
    	//return super.hashCode();
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
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 
     */
    public String getIcon() {
        return this.icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 
     */
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
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
    public Integer getNumberOfLike() {
        return this.numberOfLike;
    }
    
    public void setNumberOfLike(Integer numberOfLike) {
        this.numberOfLike = numberOfLike;
    }

    /**
     * 
     */
    public Integer getNumberOfPost() {
        return this.numberOfPost;
    }
    
    public void setNumberOfPost(Integer numberOfPost) {
        this.numberOfPost = numberOfPost;
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
    public Forum getForum() {
        return this.forum;
    }
    
    public void setForum(Forum forum) {
        this.forum = forum;
    }

    /**
     * 
     */
    public Set getSubscribers() {
        return this.subscribers;
    }
    
    public void setSubscribers(Set subscribers) {
        this.subscribers = subscribers;
    }

    /**
     * 
     */
    public Message getLatestMessage() {
        return this.latestMessage;
    }
    
    public void setLatestMessage(Message latestMessage) {
        this.latestMessage = latestMessage;
    }

    /**
     * 
     */
    public String getMostPostedBy() {
        return this.mostPostedBy;
    }
    
    public void setMostPostedBy(String mostPostedBy) {
        this.mostPostedBy = mostPostedBy;
    }


}
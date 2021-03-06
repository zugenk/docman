package com.app.docmgr.model;

import java.util.*;




/**
 * LoginHistory generated by hbm2java
 */
public class LoginHistory  implements java.io.Serializable {

    // Fields    

     private Long id;
     private User user;
     private Date loginTime;
     private Date lastAccess;
     private Date logoutTime;
     private Status status;
     private String sessionId;
     private String description;


    // Constructors

    /** default constructor */
    public LoginHistory() {
    }
    
    /** constructor with id */
    public LoginHistory(Long id) {
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
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 
     */
    public Date getLoginTime() {
        return this.loginTime;
    }
    
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 
     */
    public Date getLastAccess() {
        return this.lastAccess;
    }
    
    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    /**
     * 
     */
    public Date getLogoutTime() {
        return this.logoutTime;
    }
    
    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
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
    public String getSessionId() {
        return this.sessionId;
    }
    
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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




}
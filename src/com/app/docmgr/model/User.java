package com.app.docmgr.model;

import java.util.*;




/**
 * User generated by hbm2java
 */
public class User  implements java.io.Serializable {

    // Fields    

     private Long id;
     private String loginName;
     private String loginPassword;
     private String pinCode;
     private String mobileNumber;
     private Lookup userLevel;
     private String language;
     private String title;
     private String name;
     private String email;
     private String fullName;
     private String homePhoneNumber;
     private String mobilePhoneNumber;
     private String employeeNumber;
     private Status status;
     private Date createdDate;
     private String createdBy;
     private Date lastUpdatedDate;
     private String lastUpdatedBy;
     private String firstLogin;
     private Date lastPasswordUpdate;
     private Date lastPinCodeUpdate;
     private String lastPassword;
     private String lastPinCode;
     private Organization organization;
     private Lookup securityLevel;
     private Set roles;
     private Set favoriteTopics;
     private Integer loginFailed;
     private Integer maxRelease;
     private Date lastReleasedDate;
     private Date lastActive;
     private String sessionCode;
     private String IPassport;


    // Constructors

    /** default constructor */
    public User() {
    }
    
    /** constructor with id */
    public User(Long id) {
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
    public String getLoginName() {
        return this.loginName;
    }
    
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 
     */
    public String getLoginPassword() {
        return this.loginPassword;
    }
    
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    /**
     * 
     */
    public String getPinCode() {
        return this.pinCode;
    }
    
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    /**
     * 
     */
    public String getMobileNumber() {
        return this.mobileNumber;
    }
    
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    /**
     * 
     */
    public String getLanguage() {
        return this.language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 
     */
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
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
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     */
    public String getFullName() {
        return this.fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 
     */
    public String getHomePhoneNumber() {
        return this.homePhoneNumber;
    }
    
    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    /**
     * 
     */
    public String getMobilePhoneNumber() {
        return this.mobilePhoneNumber;
    }
    
    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    /**
     * 
     */
    public String getEmployeeNumber() {
        return this.employeeNumber;
    }
    
    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
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
    public String getFirstLogin() {
        return this.firstLogin;
    }
    
    public void setFirstLogin(String firstLogin) {
        this.firstLogin = firstLogin;
    }

    /**
     * 
     */
    public Date getLastPasswordUpdate() {
        return this.lastPasswordUpdate;
    }
    
    public void setLastPasswordUpdate(Date lastPasswordUpdate) {
        this.lastPasswordUpdate = lastPasswordUpdate;
    }

    /**
     * 
     */
    public Date getLastPinCodeUpdate() {
        return this.lastPinCodeUpdate;
    }
    
    public void setLastPinCodeUpdate(Date lastPinCodeUpdate) {
        this.lastPinCodeUpdate = lastPinCodeUpdate;
    }

    /**
     * 
     */
    public String getLastPassword() {
        return this.lastPassword;
    }
    
    public void setLastPassword(String lastPassword) {
        this.lastPassword = lastPassword;
    }

    /**
     * 
     */
    public String getLastPinCode() {
        return this.lastPinCode;
    }
    
    public void setLastPinCode(String lastPinCode) {
        this.lastPinCode = lastPinCode;
    }

    /**
     * 
     */
    public Organization getOrganization() {
        return this.organization;
    }
    
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    /**
     * 
     */
    public Lookup getSecurityLevel() {
        return this.securityLevel;
    }
    
    public void setSecurityLevel(Lookup securityLevel) {
        this.securityLevel = securityLevel;
    }

    /**
     * 
     */
    public Set getRoles() {
        return this.roles;
    }
    
    public void setRoles(Set roles) {
        this.roles = roles;
    }

    /**
     * 
     */
    public Set getFavoriteTopics() {
        return this.favoriteTopics;
    }
    
    public void setFavoriteTopics(Set favoriteTopics) {
        this.favoriteTopics = favoriteTopics;
    }

    /**
     * 
     */
    public Integer getLoginFailed() {
        return this.loginFailed;
    }
    
    public void setLoginFailed(Integer loginFailed) {
        this.loginFailed = loginFailed;
    }

    /**
     * 
     */
    public Integer getMaxRelease() {
        return this.maxRelease;
    }
    
    public void setMaxRelease(Integer maxRelease) {
        this.maxRelease = maxRelease;
    }

    /**
     * 
     */
    public Date getLastReleasedDate() {
        return this.lastReleasedDate;
    }
    
    public void setLastReleasedDate(Date lastReleasedDate) {
        this.lastReleasedDate = lastReleasedDate;
    }

    /**
     * 
     */
    public Date getLastActive() {
        return this.lastActive;
    }
    
    public void setLastActive(Date lastActive) {
        this.lastActive = lastActive;
    }

    /**
     * 
     */
    public String getSessionCode() {
        return this.sessionCode;
    }
    
    public void setSessionCode(String sessionCode) {
        this.sessionCode = sessionCode;
    }

    /**
     * 
     */
    public String getIPassport() {
        return this.IPassport;
    }
    
    public void setIPassport(String IPassport) {
        this.IPassport = IPassport;
    }




}
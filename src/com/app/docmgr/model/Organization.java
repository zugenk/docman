package com.app.docmgr.model;

import java.util.*;




/**
 * Organization generated by hbm2java
 */
public class Organization  implements java.io.Serializable {

    // Fields    

     private Long id;
     private String code;
     private String mnemonic;
     private String name;
     private String address;
     private Date createdDate;
     private String createdBy;
     private Date lastUpdatedDate;
     private String lastUpdatedBy;
     private String filterCode;
     private Organization parent;
     private Set members;


    // Constructors

    /** default constructor */
    public Organization() {
    }
    
    /** constructor with id */
    public Organization(Long id) {
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
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 
     */
    public String getMnemonic() {
        return this.mnemonic;
    }
    
    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
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
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
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
    public Organization getParent() {
        return this.parent;
    }
    
    public void setParent(Organization parent) {
        this.parent = parent;
    }

    /**
     * 
     */
    public Set getMembers() {
        return this.members;
    }
    
    public void setMembers(Set members) {
        this.members = members;
    }




}
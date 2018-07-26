package com.app.docmgr.model;

import java.util.*;




/**
 * Notification generated by hbm2java
 */
public class Notification  implements java.io.Serializable {

    // Fields    

     private Long id;
     private Lookup notificationType;
     private Message postMessage;
     private User subscriber;
     private String flag;


    // Constructors

    /** default constructor */
    public Notification() {
    }
    
    /** constructor with id */
    public Notification(Long id) {
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
    public Lookup getNotificationType() {
        return this.notificationType;
    }
    
    public void setNotificationType(Lookup notificationType) {
        this.notificationType = notificationType;
    }

    /**
     * 
     */
    public Message getPostMessage() {
        return this.postMessage;
    }
    
    public void setPostMessage(Message postMessage) {
        this.postMessage = postMessage;
    }

    /**
     * 
     */
    public User getSubscriber() {
        return this.subscriber;
    }
    
    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    /**
     * 
     */
    public String getFlag() {
        return this.flag;
    }
    
    public void setFlag(String flag) {
        this.flag = flag;
    }




}
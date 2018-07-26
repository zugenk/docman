package com.app.shared;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */

import java.io.*;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SimpleAuthenticator extends javax.mail.Authenticator {

private String username;
private String password;
private PasswordAuthentication auth;

public java.lang.String getPassword() {
return password;
}

public PasswordAuthentication getPasswordAuthentication() {
if(auth == null) {
auth = new PasswordAuthentication(username, password);

}
return auth;
}

public java.lang.String getUsername() {
return username;
}

public void setPassword(java.lang.String password) {
this.password = password;
}

public void setUsername(java.lang.String username) {
this.username = username;
}

public String getProtocol() {
return getRequestingProtocol();
}

public String getProtokol() {
return getRequestingProtocol();
}
}

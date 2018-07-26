package com.app.module.basic;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;

public class EmailManager extends BaseUtil{
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(EmailManager.class);
	
	public static List sendMail(String from,List toAddress,String subject,String content) {
		return sendMail( from, toAddress, subject, content,null);
	}
	public static List sendMail(String from,List toAddress,String subject,String content,List<File> attachments) {
		Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    //props.put("mail.smtp.host", "smtp.sendgrid.net");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class",
	            "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "465"); 
	    Session session = Session.getDefaultInstance(props,
	        new javax.mail.Authenticator() {
	                            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication("martin.rohadi@gmail.com","deewyoaejupweqln");
	              // return new PasswordAuthentication("apikey","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");
	            }
	        });
	    
	   if (toAddress==null || toAddress.isEmpty()) {
			log.error("Send Email failed, email has no to_address !");
			return null; //new LinkedList();
		}
	    List unsentAddress=new LinkedList();
	    unsentAddress.addAll(toAddress);
	    if (subject==null) subject="No Specific Subject";
	    
	    try {
	    	
	    	 if (from==null) from="zugenk@gmail.com";
	         MimeMessage msg = new MimeMessage(session);
	         msg.setFrom(new InternetAddress(from));

	         List validAddress=new LinkedList();
	         Iterator toAddrItr=toAddress.iterator();
	         while (toAddrItr.hasNext()) {
	 			String istraddr = (String) toAddrItr.next();
	 	        try {
	 	        	validAddress.add(new InternetAddress(istraddr));
	 	        	unsentAddress.remove(istraddr);
	 			} catch (Exception e) {
	 				log.error("Invalid email address :"+istraddr,e);
	 			}
	 		}
	         if (validAddress.isEmpty()) {
	         	log.error("Send Email failed,all to_address are invalid !");
	         	return unsentAddress;
	         } 
	         InternetAddress toaddress[]=new InternetAddress[validAddress.size()];
	         toAddrItr=validAddress.iterator();
	         int i=0;
	         while (toAddrItr.hasNext()) {
	         	InternetAddress iaddress = (InternetAddress) toAddrItr.next();
	 			toaddress[i]=iaddress;
	 			i++;
	 		}
	         
	         msg.setRecipients(javax.mail.Message.RecipientType.TO,toaddress);

	         msg.setSubject(subject);
	         msg.setSentDate(new java.util.Date());

	         MimeBodyPart mbp = new MimeBodyPart();
	         mbp.setContent(content,"text/html");
	         Multipart mp = new MimeMultipart();
	         mp.addBodyPart(mbp); String fname;
	         if(attachments!=null && !attachments.isEmpty()) {
	        	 for (Iterator iterator = attachments.iterator(); iterator.hasNext();) {
					File f = (File) iterator.next();
					 mbp = new MimeBodyPart();
		             DataSource source = new FileDataSource(f);
		             mbp.setDataHandler(new DataHandler(source));
		             if(f.getName().startsWith(TEMP_FILE_PREFIX)) fname=f.getName().substring(TEMP_FILE_PREFIX.length()+19);
		             else fname=f.getName();
		             mbp.setFileName(fname);
		             mp.addBodyPart(mbp);
	        	 }
	        		        	 
	         }
	         msg.setContent(mp);
		     	
	    	/*
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress("zugenk@gmail.com"));
	        message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse("zugenk@yahoo.com"));
	        message.setSubject("Docman Testing Subject");
	        message.setText("Docman Test Mail");
*/
	        Transport.send(msg);

	        System.out.println("Done");
	        log.debug("Email Sent Successfully");

	    } catch (MessagingException e) {
	       // throw new RuntimeException(e);
	        log.error("Email delivery failed ",e);

	    }
	    return unsentAddress;
	}
	
	public static void main(String[] args) {
		List toAddress=new LinkedList<String>();
		toAddress.add("martin.rohadi@yahoo.com");
		String content="KOK nggak bisa html tuh";
		sendMail("zugenk@docman.com", toAddress, "Coba lagi boss", content,null);
	}
	

	public static void SendEmaiWithAttachment() {
      // Recipient's email ID needs to be mentioned.
      String to = "destinationemail@gmail.com";

      // Sender's email ID needs to be mentioned
      String from = "fromemail@gmail.com";

      final String username = "manishaspatil";//change accordingly
      final String password = "******";//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "relay.jangosmtp.net";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "25");

      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
         });

      try {
         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject("Testing Subject");

         // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();

         // Now set the actual message
         messageBodyPart.setText("This is message body");

         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
         
         String filename = "/home/manisha/file.txt";
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         message.setContent(multipart);

         // Send message
         Transport.send(message);

         System.out.println("Sent message successfully....");
  
      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
	   
	}
	
}

package com.app.shared;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.io.*;
import org.apache.log4j.*;

/**
 * @author Martin - Digibox - WebCode Generator 1.5
 * @project Document Manager
 * @version 1.0.0
 * @createDate 03-10-2017 20:59:59
 */


public class EncryptionUtils {
	private static Logger log = Logger.getLogger("com.app.shared.EncryptionUtils");	
  	private static final String KEY_STRING = "193-155-248-97-234-56-100-241";
  	//private static final String KEY_STRING = "3816c875da2f2540";
  
  public static String encrypt( String source )
	  {
	    try
	    {
	      // Get our secret key
	      Key key = getKey();

	      // Create the cipher 
	      Cipher desCipher =  Cipher.getInstance("DES/ECB/PKCS5Padding");

	      // Initialize the cipher for encryption
	      desCipher.init(Cipher.ENCRYPT_MODE, key);

	      // Our cleartext as bytes
	      byte[] cleartext = source.getBytes();

	      // Encrypt the cleartext
	      byte[] ciphertext = desCipher.doFinal(cleartext);

	      // Return a String representation of the cipher text
	      return getString( ciphertext );
	    }
	    catch( Exception e )
	    {
	      e.printStackTrace();
	    }
	    return null;
	  }

	  public static String generateKey()
	  {
	    try
	    {
	      KeyGenerator keygen = KeyGenerator.getInstance("DES");
	      SecretKey desKey = keygen.generateKey();
	      byte[] bytes = desKey.getEncoded();
	      return getString( bytes );
	    }
	    catch( Exception e )
	    {
	      e.printStackTrace();
	      return null;
	    }
	  }

	  public static String decrypt( String source )
	  {
	    try
	    {
	      // Get our secret key
	      Key key = getKey();

	      // Create the cipher 
	      Cipher desCipher =  Cipher.getInstance("DES/ECB/PKCS5Padding");

	      // Encrypt the cleartext
	      byte[] ciphertext = getBytes( source );

	      // Initialize the same cipher for decryption
	      desCipher.init(Cipher.DECRYPT_MODE, key);

	      // Decrypt the ciphertext
	      byte[] cleartext = desCipher.doFinal(ciphertext);

	      // Return the clear text
	      return new String( cleartext );
	    }
	    catch( Exception e )
	    {
	      e.printStackTrace();
	    }
	    return null;
	  }

	  private static Key getKey()
	  {
	    try
	    {
	      byte[] bytes = getBytes( KEY_STRING );
	      DESKeySpec pass = new DESKeySpec( bytes ); 
	      SecretKeyFactory skf = SecretKeyFactory.getInstance("DES"); 
	      SecretKey s = skf.generateSecret(pass); 
	      return s;
	    }
	    catch( Exception e )
	    {
	      e.printStackTrace();
	    }
	    return null;
	  }

	  /**
	   * Returns true if the specified text is encrypted, false otherwise
	   */
	  public static boolean isEncrypted( String text )
	  {
	    // If the string does not have any separators then it is not
	    // encrypted
	    if( text.indexOf( '-' ) == -1 )
	    {
	      ///System.out.println( "text is not encrypted: no dashes" );
	      return false;
	    }

	    StringTokenizer st = new StringTokenizer( text, "-", false );
	    while( st.hasMoreTokens() )
	    {
	      String token = st.nextToken();
	      if( token.length() > 3 )
	      {
	        System.out.println( "text is not encrypted: length of token greater than 3: " + token );
	        return false;
	      }
	      for( int i=0; i<token.length(); i++ )
	      {
	        if( !Character.isDigit( token.charAt( i ) ) )
	        {
	          System.out.println( "text is not encrypted: token	is not a digit" );
	          return false;
	        }
	      }
	    }
	    //System.out.println( "text is encrypted" );
	    return true;
	  }

	  private static String getString( byte[] bytes )
	  {
	    StringBuffer sb = new StringBuffer();
	    for( int i=0; i<bytes.length; i++ )
	    {
	      byte b = bytes[ i ];
	      sb.append( ( int) ( 0xFF & b ) );
	      if( i+1 <bytes.length )
	      {
	        sb.append( "-" );
	      }
	    }
	    return sb.toString();
	  }

	  private static byte[] getBytes( String str )
	  {
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    StringTokenizer st = new StringTokenizer( str, "-", false );
	    while( st.hasMoreTokens() )
	    {
	    	String token=st.nextToken();
	    	System.out.println(token);
	      int i = Integer.parseInt( token); //st.nextToken() );
	      bos.write( ( byte )i );
	    }
	    System.out.println(bos.toByteArray());
	    return bos.toByteArray();
	  }
	  
	  public static void main( String[] args ) {
		String message ="Hwrakadah, Duh Gusti Paringana sabar !";
		System.out.println( "Original Msg = " +  message + ":"+message.length());  
		
		String encryptedMsg=EncryptionUtils.encrypt( message );
		System.out.println( "Encrypted = " +  encryptedMsg+ ":"+encryptedMsg.length() );  
		String decriptedMsg=EncryptionUtils.decrypt( encryptedMsg );
		System.out.println( "Decrypted = " +  decriptedMsg +":"+decriptedMsg.length());
		
	 }

	  public static void showProviders()
	  {
	    try
	    {
	      Provider[] providers = Security.getProviders();
	      for( int i=0; i<providers.length; i++ )
	      {
	        System.out.println( "Provider: " +  providers[ i ].getName() + ", " + providers[ i ].getInfo() );
	        for( Iterator itr = providers[ i ].keySet().iterator(); itr.hasNext(); )
	        {
	          String key = ( String )itr.next();
	          String value = ( String )providers[ i ].get( key );
	          System.out.println( "\t" + key + " = " + value );
	        }

	      }
	    }
	    catch( Exception e )
	    {
	      e.printStackTrace();
	    }
	  }
	  
	} 

package ifrn.examples_extra;
import com.jaunt.*;
import com.jaunt.util.*;

public class Exemplo9_HTTPS_PROXY_SYSTEM_LEVEL_CONFIG{

  public static void main(String[] args){
    try{
      //specify https proxy at System level.
      System.setProperty("https.proxyHost", "12.345.67.8"); 
      System.setProperty("https.proxyPort", "80");         
      
      UserAgent userAgent = new UserAgent();
      userAgent.visit("https://suap.ifrn.edu.br/accounts/login/");               
      System.out.println(userAgent.doc.innerXML());   //print the retrieved document    
    }
    catch(JauntException j){
      System.err.println(j);
    }
  }
}
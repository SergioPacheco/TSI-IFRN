package ifrn.examples;
import com.jaunt.*;
import com.jaunt.component.*;
import java.io.*;

public class Examplo02_findFirst{
  public static void main(String[] args){
    try{
      UserAgent userAgent = new UserAgent();                       //create new userAgent (headless browser).
      // System.out.println("SETTINGS:\n" + userAgent.settings);   //print the userAgent's default settings.
      userAgent.settings.autoSaveAsHTML = true;                    //change settings to autosave last visited page. 
      
      userAgent.visit("http://portal.ifrn.edu.br/");               //visit a url.
      String title = userAgent.doc.findFirst("<title>").getText(); //get child text of title element.
      System.out.println("\nIfrn's website title: " + title);      //print the title

      userAgent.visit("http://www.tribunadonorte.com.br/");        //visit another url.
      title = userAgent.doc.findFirst("<title>").getText();        //get child text of first title element.
      System.out.println("\nTribuna do Norte's website title: " + title);    //print the title
    }
    catch(JauntException e){  //if title element isn't found or HTTP/connection error occurs, handle JauntException.
      System.err.println(e);          
    }
  }
}
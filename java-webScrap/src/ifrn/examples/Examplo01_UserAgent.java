package ifrn.examples;
import com.jaunt.*;
import com.jaunt.component.*;
import java.io.*;

public class Examplo01_UserAgent{
  public static void main(String[] args){
    try{
      UserAgent userAgent = new UserAgent();           //create new userAgent (headless browser).
      userAgent.visit("http://portal.ifrn.edu.br/");   //visit a url   
      System.out.println(userAgent.doc.innerHTML());   //print the content as HTML
    }
    catch(JauntException e){       //if an HTTP/connection error occurs, handle JauntException.
      System.err.println(e);
    }
  }
}
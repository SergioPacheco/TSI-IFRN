package ifrn.examples;
import com.jaunt.*;
import com.jaunt.component.*;
import java.io.*;

public class Examplo03_openContent{
  public static void main(String[] args){
    try{
      UserAgent userAgent = new UserAgent();                       
                                                                    //open HTML from a String.
      userAgent.openContent("<html><body>IFRN WebPage <div>Cursos:<p>Sistemas para Internet</p><p>Redes</p></div> Copyright 2013</body></html>");
      Element body = userAgent.doc.findFirst("<body>");
      Element div = body.findFirst("<div>");
   
      System.out.println("body's text: " + body.getText());         //join child text of body element
      System.out.println("body's innerText: " + body.innerText()); //join all text within body element
      System.out.println("div's text: " + div.getText());           //join child of div element
      System.out.println("div's innerText: " + div.innerText());    //join all text within the div element 
    }
    catch(JauntException e){
      System.err.println(e);
    }
  }
}
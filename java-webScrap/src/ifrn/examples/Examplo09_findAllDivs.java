package ifrn.examples;
import com.jaunt.*;
import com.jaunt.component.*;
import java.util.List;

public class Examplo09_findAllDivs {
  public static void main(String[] args){
    try{ 
      UserAgent userAgent = new UserAgent(); 
      userAgent.visit("http://portal.ifrn.edu.br/");
   
      Elements elements = userAgent.doc.findEvery("<div>");              //find all divs in the document
      System.out.println("Every div: " + elements.size() + " results");  //report number of search results.
   
      elements = userAgent.doc.findEach("<div>");                        //find all non-nested divs
      System.out.println("Each div: " + elements.size() + " results");   //report number of search results.
                                                                         //find non-nested divs within <p class='meat'>
      elements = userAgent.doc.findFirst("<div class=\'each_news\'>").findEach("<div>"); 
      System.out.println("each news search: " + elements.size() + " results");//report number of search results.
    }
    catch(JauntException e){                          
      System.out.println(e);
    }    
  }
}
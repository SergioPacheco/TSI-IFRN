package ifrn.examples;

import com.jaunt.Element;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;

public class GoogleSearch {

    public static void main(String[] args) {

        try {
            final UserAgent userAgent = new UserAgent();
            userAgent.visit("https://www.google.com.br");
            userAgent.doc.apply("Givanaldo Rocha");
            
            userAgent.doc.submit("Pesquisa Google");
            // userAgent.doc.submit("Estou com sorte");
            // System.out.println(userAgent.doc.innerHTML()); 

            for (Element link : userAgent.doc.findEvery("<h3 class=r>").findEvery("<a>")) {
                System.out.println(link.getAt("href"));
            }
        } catch (JauntException e) {
            System.err.println(e);
        }

    }
}

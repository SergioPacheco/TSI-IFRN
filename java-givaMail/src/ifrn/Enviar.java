package ifrn;

import org.apache.commons.mail.*;
import org.apache.commons.mail.DefaultAuthenticator;

public class Enviar {

    public boolean Send(String to, String subject, String message, String path) throws Throwable {
        boolean flag;
        message = message + "\n\n\n\n\n________________________________\n\nSergio Pacheco";
        System.out.println(path);
        
//        Properties login = new Properties();
//        String properties = "login.properties";
//        try {
//            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(properties);
//            login.load(stream);
//        } catch (IOException ex) {
//            System.out.println("Erro: login " + ex.getMessage());
//        }
//        String username = login.getProperty("hotmail.username");
//        String password = login.getProperty("hotmail.password");
        String username = "asfpacheco@hotmail.com";
        String password = "minhasenhasupersecreta";
        
              
        try {
            System.out.println("Enviando e-mail...");
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName("smtp.live.com");
            email.setSmtpPort(587);
            email.setStartTLSRequired(true);
            email.setAuthentication(username, password);
            email.setFrom(username);
            email.setSubject(subject);
            email.setMsg(message);
            email.addTo(to);
            
            
            // email.addTo("albertoavs06@gmail.com");
           
            if (!path.equals("0")) {
                email.attach(incluiAnexo(path));
            }

            
            email.setDebug(true);
            System.out.println("prepando enviar");
            email.send();
            
            System.out.println("Mensagem foi enviada com sucesso para " + to);
            return (flag = true);
            
            
        } catch (EmailException ee) {
            System.out.println("Erro : " + ee.toString());
            return (flag = false);
        }
    }

    private EmailAttachment incluiAnexo(String arquivo) {
        try {
            System.out.println("anexando attach");
            EmailAttachment att = new EmailAttachment();
            att.setPath(arquivo);
            att.setDisposition(EmailAttachment.ATTACHMENT);
            att.setDescription("Anexo");
            // attachment.setName();
            return att;
        } catch (Exception ee) {
            System.out.println("******" + ee.getMessage());
            return null;
        }
    }
}

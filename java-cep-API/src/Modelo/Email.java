package Modelo;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author joao.roberto
 */
public class Email {
    
    private String hostName;
    
    private String emailDestino;
    
    private String emailOrigem;
    
    private String assunto;
    
    private String msg;
    
    private String userName;
    
    private String password;
    
    private String nomeDestino;
    
    private String nomeOrigem;
    
    private Integer porta;
    

    public Integer getPorta() {
        return porta;
    }

    public void setPorta(Integer porta) {
        this.porta = porta;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getEmailDestino() {
        return emailDestino;
    }

    public void setEmailDestino(String emailDestino) {
        this.emailDestino = emailDestino;
    }

    public String getEmailOrigem() {
        return emailOrigem;
    }

    public void setEmailOrigem(String emailOrigem) {
        this.emailOrigem = emailOrigem;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNomeDestino() {
        return nomeDestino;
    }

    public void setNomeDestino(String nomeDestino) {
        this.nomeDestino = nomeDestino;
    }

    public String getNomeOrigem() {
        return nomeOrigem;
    }

    public void setNomeOrigem(String nomeOrigem) {
        this.nomeOrigem = nomeOrigem;
    }
    
    
    
    public void AnexarArquivo(String path, String descricao, String nome) {
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(path); //caminho da imagem
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription(descricao);
        attachment.setName(nome);
    }
    
    public void CriarEmail(Boolean autentica) throws EmailException {
        // Cria o e-mail
        HtmlEmail email = new HtmlEmail();
        email.setHostName(this.hostName);
        email.setSSLOnConnect(true);
        email.setSmtpPort(this.porta);
        email.addTo(this.emailDestino, this.nomeDestino);
        email.setFrom(this.emailOrigem, this.nomeOrigem);
        email.setSubject(this.assunto);
 
        // configura a mensagem para o formato HTML
        email.setHtmlMsg(this.msg);
 
        // configure uma mensagem alternativa caso o servidor não suporte HTML
        email.setTextMsg("Seu servidor de e-mail nao suporta mensagem HTML");
        
        if (autentica) {
            email.setAuthentication(this.userName, this.password);
        }
        
        // envia o e-mail
        email.send();
    }
   
    
}

package ifrn;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Protocolo implements Serializable {

    protected static final long serialVersionUID = 1L;
     
    static final int USUARIOS_ONLINE = 0, 
                            CONECTAR = 1, 
                                SAIR = 2,
                      ENVIAR_PRIVADO = 3,
                        ENVIAR_TODOS = 4;
    
    private int id;
    private String nome;
    private String mensagem; 
    private String privado;
    private Set<String> usuariosOnline = new HashSet<String>();

    Protocolo(int id, String mensagem) {
        this.id = id;
        this.mensagem = mensagem;
    }
    
    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public String getPrivado() {
        return privado;
    }

    public void setPrivado(String privado) {
        this.privado = privado;
    }

     public Set<String> getUsuariosOnline() {
        return usuariosOnline;
    }

    public void setUsuariosOnline(Set<String> usuariosOnline) {
        this.usuariosOnline = usuariosOnline;
    }
    
}

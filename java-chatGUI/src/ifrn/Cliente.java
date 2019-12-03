package ifrn;

import java.net.*;
import java.io.*;

public class Cliente {

    private ObjectInputStream entrada;
    private ObjectOutputStream saida;
    private Socket socket;
    private ClienteGUI cg;
    private String servidor, clienteNome;
    private int porta;
    private Protocolo mensagem;

    Cliente(String servidor, int porta, String clienteNome, ClienteGUI cg) {
        this.servidor = servidor;
        this.porta = porta;
        this.clienteNome = clienteNome;
        this.cg = cg;
    }

    public boolean conecta() {

        try {
            socket = new Socket(servidor, porta);
        } catch (Exception ec) {
            recebeMsg("Erro ao conectar com O servidor\n " + ec);
            return false;
        }

        String msg = "Conectado " + socket.getInetAddress() + ":" + socket.getPort();
        recebeMsg(msg);

        try {

            entrada = new ObjectInputStream(socket.getInputStream());
            saida = new ObjectOutputStream(socket.getOutputStream());

        } catch (IOException eIO) {
            recebeMsg("Erro ao criar I/O Streams. \n " + eIO);
            return false;
        }
        // Cria thread para ouvir o servidor
        new EscutaServidor().start();

        try {
            
            Protocolo msgLogin = new Protocolo(Protocolo.CONECTAR, "");
            msgLogin.setNome(clienteNome);
            
            saida.writeObject(msgLogin);
            
            System.out.println("Enviar o nome do novo cliente");
            
        } catch (IOException eIO) {
            recebeMsg("Erro ao efetuar login :\n " + eIO);
            desconecta();
            return false; // não conectou 
        }

        return true;      // Conectou
    }

    private void recebeMsg(String msg) {
        cg.anexaString(msg + "\n");
    }

    void enviaMsg(Protocolo msg) {
        try {
            
            saida.writeObject(msg);
            
        } catch (IOException e) {
            recebeMsg("Erro ao gravar para o servidor:\n " + e);
        }
    }

    private void desconecta() {
        try {
            if (entrada != null) {
                entrada.close();
            }
        } catch (Exception e) {
        }
        try {
            if (saida != null) {
                saida.close();
            }
        } catch (Exception e) {
        }
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
        }
         
        cg.erroConexao();
         

    }

    class EscutaServidor extends Thread {

        @Override
        public void run() {
            while (true) {
                try {

                    Protocolo msg = (Protocolo) entrada.readObject();

                    switch (msg.getId()) {

                        case Protocolo.CONECTAR:
                            cg.anexaUsuariosOnline(msg);
                            cg.logado(msg); 
                            break;    

                        case Protocolo.ENVIAR_TODOS:
                            cg.anexaUsuariosOnline(msg);
                            cg.anexaMsg(msg);
                            
                            break;

                        case Protocolo.SAIR:
                            cg.anexaString("Desconectado por LOGOFF\n");
                            desconecta();
                            break;

                        case Protocolo.ENVIAR_PRIVADO:
                            cg.anexaUsuariosOnline(msg);
                            String m = msg.getMensagem();
                            msg.setMensagem("<pvdo>"+m);
                            cg.anexaMsg(msg);
                            break;
                    }

                } catch (IOException e) {
                    recebeMsg("Servidor fechou a conexão.\n ");
                    break;
                } catch (ClassNotFoundException e2) {
                }
            }
        }
    }
}

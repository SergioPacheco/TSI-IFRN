/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifrn;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Servidor {

    private static int id;
    private ArrayList<TrataCliente> listaClientes;
    private ServidorGUI sgui;
    private SimpleDateFormat sdf;
    private int porta;
    private boolean ativo;

    public Servidor(int port) {
        this(port, null);
    }

    public Servidor(int porta, ServidorGUI sg) {
        this.sgui = sg;
        this.porta = porta;
        sdf = new SimpleDateFormat("HH:mm:ss");
        listaClientes = new ArrayList<TrataCliente>();
    }

    public void conecta() throws ClassNotFoundException {
        ativo = true;
        try {
            ServerSocket servidorSocket = new ServerSocket(porta);

            while (ativo) {
                exibir("Esperando conexão na porta " + porta + ".");

                Socket socket = servidorSocket.accept();

                if (!ativo) {
                    break;
                }
                TrataCliente trataNovo = new TrataCliente(socket);
                listaClientes.add(trataNovo);
                trataNovo.start();
            }

            // pediu para parar
            try {
                servidorSocket.close();
                for (int i = 0; i < listaClientes.size(); ++i) {
                    TrataCliente tc = listaClientes.get(i);
                    try {
                        tc.entrada.close();
                        tc.saida.close();
                        tc.clienteSocket.close();
                    } catch (IOException ioE) {
                        System.out.println("Erro fechar Sockets");
                    }
                }
            } catch (Exception e) {
                exibir("Erro fechar servidor: " + e);
            }
        } catch (IOException e) {
            String msg = sdf.format(new Date()) + " Erro ao criar ServidorSocket: \n" + e + "\n";
            exibir(msg);
        }
    }

    protected void parar() {
        ativo = false;
        // força erro para fechar automaticamente os sockets
        try {
            new Socket("localhost", porta);
        } catch (Exception e) {
        }
    }

    private void exibir(String msg) {
        String hora = sdf.format(new Date()) + " " + msg;
        sgui.anexarEvento(hora + "\n");
    }

    private synchronized void enviarTodos(Protocolo msg) {

        String hora = sdf.format(new Date());
        String temp = hora + " " + msg.getMensagem() + "\n";

        msg = new Protocolo(Protocolo.ENVIAR_TODOS, temp);

        sgui.anexarMsg(msg);

        for (int i = listaClientes.size(); --i >= 0;) {
            TrataCliente ct = listaClientes.get(i);

            if (!ct.gravaMsg(msg)) {
                listaClientes.remove(i);
                exibir(ct.usuario + ", foi removido da lista.");
            }
        }
    }

    private synchronized void enviarPrivado(Protocolo msg) {
        String hora = sdf.format(new Date());
        String temp = hora + " " + msg.getMensagem() + "\n";

        msg = new Protocolo(Protocolo.ENVIAR_PRIVADO, temp);

        sgui.anexarMsg(msg);

        for (int i = listaClientes.size(); --i >= 0;) {
            TrataCliente ct = listaClientes.get(i);
            if (listaClientes.get(i).usuario.equals(msg.getPrivado())) {

                if (!ct.gravaMsg(msg)) {
                    listaClientes.remove(i);
                    exibir(ct.usuario + ", foi removido da lista.");
                    System.out.println("cliente foi removido");
                }
            }

        }
    }

    private synchronized void enviarUsuariosOnline(String usu) {

        Set<String> usuarios = new HashSet<String>();

        for (int i = 0; i < listaClientes.size(); ++i) {
            usuarios.add(listaClientes.get(i).usuario);
            System.out.println(">>>> USUARIOS ONLINE " + listaClientes.get(i).usuario);
        }

        Protocolo msg = new Protocolo(Protocolo.USUARIOS_ONLINE, "");
        msg.setUsuariosOnline(usuarios);

        for (int i = 0; i < listaClientes.size(); ++i) {
            msg.setNome(listaClientes.get(i).usuario);
            if (listaClientes.get(i).usuario.equals(usu)) {
                try {
                    listaClientes.get(i).saida.writeObject(msg);
                } catch (IOException ex) {

                }
            }

        }
    }

    synchronized void removeCliente(int id) {

        for (int i = 0; i < listaClientes.size(); ++i) {
            TrataCliente ct = listaClientes.get(i);

            if (ct.id == id) {
                listaClientes.remove(i);
                return;
            }
        }
    }

    public ArrayList getClientes() {
        return listaClientes;
    }

    class TrataCliente extends Thread {

        Socket clienteSocket;
        ObjectInputStream entrada;
        ObjectOutputStream saida;
        int id;
        String usuario;
        Protocolo msg;
        String data;

        TrataCliente(Socket socket) throws ClassNotFoundException {

            id = ++id;
            this.clienteSocket = socket;
            System.out.println("Thread está tentando criar um Objeto I/O Streams");
            try {
                saida = new ObjectOutputStream(socket.getOutputStream());
                entrada = new ObjectInputStream(socket.getInputStream());

                msg = (Protocolo) entrada.readObject();
                usuario = msg.getNome();

                exibir(usuario + " conectou-se.");

            } catch (IOException e) {
                exibir("Erro na criação dos novos I/O Streams: " + e);
                return;
            }

            data = new Date().toString() + "\n";
        }

        @Override
        public void run() {

            boolean ativo = true;

            while (ativo) {

                try {

                    msg = (Protocolo) entrada.readObject();

                } catch (IOException e) {
                    exibir(usuario + " Erro ao ler Streams: " + e);
                    break;
                } catch (ClassNotFoundException e2) {
                    break;
                }

                    
                String textoMsg = msg.getMensagem();

                switch (msg.getId()) {

                    case Protocolo.USUARIOS_ONLINE:
                        System.out.println("USUARIOS_ONLINE" + msg.toString());
                        enviarUsuariosOnline(usuario);
                        break;

                    case Protocolo.CONECTAR:
                        System.out.println("ListaClientes =" +listaClientes.toString());   
                        System.out.println("CONECTAR " + msg.toString());
                        enviarUsuariosOnline(usuario);
                        break;

                    case Protocolo.SAIR:
                        System.out.println("SAIR " + msg.toString());
                        exibir(usuario + " desconectado LOGOUT.\n");
                        ativo = false;
                        break;

                    case Protocolo.ENVIAR_PRIVADO:
                        System.out.println("ENVIAR_PRIVADO " + msg.toString());
                        textoMsg = usuario + ": " + textoMsg;
                        msg.setMensagem(textoMsg);
                        if (msg.getPrivado().isEmpty()) {
                            System.out.println("Privado em branco");
                            break;
                        }
                        enviarPrivado(msg);
                        break;

                    case Protocolo.ENVIAR_TODOS:
                        System.out.println("ENVIAR_TODOS " + msg.toString());
                        System.out.println(msg.toString());

                        textoMsg = usuario + "~ " + textoMsg;

                        msg.setMensagem(textoMsg);
                        
                        enviarTodos(msg);
                        break;
                    default:
                        System.out.println("Default " + msg.toString());
                }
            }

            removeCliente(id);
            fechaServidor();
            enviarUsuariosOnline(usuario);
        }

        private void fechaServidor() {

            try {
                if (saida != null) {
                    saida.close();
                }
            } catch (Exception e) {
            }
            try {
                if (entrada != null) {
                    entrada.close();
                }
            } catch (Exception e) {
            }
            try {
                if (clienteSocket != null) {
                    clienteSocket.close();
                }
            } catch (Exception e) {
            }
        }

        private boolean gravaMsg(Protocolo msg) {

            if (!clienteSocket.isConnected()) {
                fechaServidor();
                return false;
            }

            try {

                saida.writeObject(msg);

            } catch (IOException e) {
                exibir("Erro ao enviar mensagem para " + usuario);
                exibir(e.toString());
            }
            return true;
        }
    }
}

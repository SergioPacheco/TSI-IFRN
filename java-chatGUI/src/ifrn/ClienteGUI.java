package ifrn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class ClienteGUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField txtUsuario, txtMsg, txtServidor, txtPorta;
    private JButton btnLogin, btnLogout, btnEnviar;
    private JTextArea taHistorico;
    private JList lstOnline;
    private boolean conectado;
    private Cliente cliente;
    private int portaPadrao;
    private String hostPadrao;
    private Protocolo mensagem;
    private SimpleDateFormat sdf;
    

    ClienteGUI(String host, int porta) {

        super("Cliente Chat");

        sdf = new SimpleDateFormat("HH:mm:ss");
        portaPadrao = porta;
        hostPadrao = host;

        JPanel nortePanel = new JPanel(new GridLayout(3, 1, 0, 6));

        // linha 1
        // nortePanel.add(new JLabel(""));

        JPanel servidorPanel = new JPanel(new GridLayout(1, 5, 3, 3)); // (rows, cols, hgap, vgap) 
        txtServidor = new JTextField(host);
        txtPorta = new JTextField(porta + " ");
        txtPorta.setHorizontalAlignment(SwingConstants.RIGHT);

        servidorPanel.add(new JLabel("Servidor: ", SwingConstants.RIGHT));
        servidorPanel.add(txtServidor);
        servidorPanel.add(new JLabel("Porta: ", SwingConstants.RIGHT));
        servidorPanel.add(txtPorta);
        servidorPanel.add(new JLabel(""));

        // Linha 2
        nortePanel.add(servidorPanel);

        JPanel usuarioPanel = new JPanel(new GridLayout(1, 5, 3, 3));
        txtUsuario = new JTextField("Cliente");
        txtUsuario.setBackground(Color.WHITE);
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this);
        btnLogout = new JButton("Logout");
        btnLogout.addActionListener(this);
        btnLogout.setEnabled(false);
        usuarioPanel.add(new JLabel("Usuário:", SwingConstants.RIGHT));
        usuarioPanel.add(txtUsuario);
        usuarioPanel.add(btnLogin);
        usuarioPanel.add(btnLogout);
        usuarioPanel.add(new JLabel(""));

        // linha 3
        nortePanel.add(usuarioPanel);

        // linha 4
        JPanel onlinePanel = new JPanel(new GridLayout(1, 2, 3, 3));
        onlinePanel.add(new JLabel("Usuarios:", SwingConstants.LEFT));
        onlinePanel.add(new JLabel("História:", SwingConstants.LEFT));
        nortePanel.add(onlinePanel);
        add(nortePanel, BorderLayout.NORTH);

        taHistorico = new JTextArea("Bem vindo ao chat\n", 80, 80);
        
        lstOnline = new JList();
        lstOnline.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstOnline.setLayoutOrientation(JList.VERTICAL);

        JPanel centroPanel = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel historiaPanel = new JPanel(new GridLayout(1, 4, 5, 5));

        // historiaPanel.add(new JScrollPane(lstOnline));
        historiaPanel.add(new JScrollPane(taHistorico));

        centroPanel.add(historiaPanel);
        taHistorico.setEditable(false);

        add(centroPanel, BorderLayout.CENTER);

        // Sul
        txtMsg = new JTextField("");
        btnEnviar = new JButton("Enviar");
        JPanel sulPanel = new JPanel(new GridLayout(4, 1, 3, 3));
        JPanel enviarPanel = new JPanel(new GridLayout(1, 3, 3, 3));

        sulPanel.add(new JLabel("Mensagem:", SwingConstants.CENTER));
        sulPanel.add(txtMsg);

        enviarPanel.add(new JLabel(""));
        enviarPanel.add(btnEnviar);
        enviarPanel.add(new JLabel(""));

        btnEnviar.setEnabled(false);

        sulPanel.add(enviarPanel);
        sulPanel.add(new JLabel(""));
        add(sulPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 500);
        setVisible(true);
        txtMsg.requestFocus();

    }

    void anexaMsg(Protocolo msg) {
        taHistorico.append(msg.getMensagem());
        taHistorico.setCaretPosition(taHistorico.getText().length() - 1);
    }

    void anexaString(String texto) {
        taHistorico.append(texto);
        taHistorico.setCaretPosition(taHistorico.getText().length() - 1);
    }

    void anexaUsuariosOnline(Protocolo msg) {
        System.out.println("1: anexa usuarioas online");
        System.out.println(msg.getUsuariosOnline().toString());

        Set<String> usuarios = msg.getUsuariosOnline();
        usuarios.remove(msg.getNome()); // remove o próprio nome da lista
        String[] array = (String[]) usuarios.toArray(new String[usuarios.size()]);
         
        this.lstOnline.setListData(array);
        this.lstOnline.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.lstOnline.setLayoutOrientation(JList.VERTICAL);
    }

    void logado(Protocolo msg) {

        this.txtMsg.setEnabled(true);
        this.taHistorico.setEnabled(true);
        this.btnEnviar.setEnabled(true);
        this.btnLogin.setEnabled(false);
        this.btnLogout.setEnabled(true);

        anexaMsg(msg);

        // JOptionPane.showMessageDialog(this, "Conectado no chat!");
    }

    void erroConexao() {
        
        btnLogin.setEnabled(true);
        btnLogout.setEnabled(false);
        btnEnviar.setEnabled(false);
        txtUsuario.setText("Super");
        txtMsg.setText("");

        txtPorta.setText("" + portaPadrao);
        txtServidor.setText(hostPadrao);
        txtServidor.setEditable(false);
        txtPorta.setEditable(false);
        btnEnviar.removeActionListener(this);
        conectado = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o == btnEnviar) {
            if (conectado) {
                
                String texto =  txtUsuario.getText();
                
                mensagem = new Protocolo(Protocolo.ENVIAR_TODOS, "");

                if (lstOnline.getSelectedIndex() > -1) {
                    mensagem.setPrivado((String) lstOnline.getSelectedValue());
                    mensagem. setId(Protocolo.ENVIAR_PRIVADO);
                    lstOnline.clearSelection();
                } 

                if (!texto.isEmpty()) {
                    mensagem.setNome(texto.trim());
                    mensagem.setMensagem(txtMsg.getText().trim());
                    cliente.enviaMsg(mensagem);
                }

                txtMsg.setText("");
            }
            return;
        }

        if (o == btnLogout) {
            System.out.println("Logout");
            btnLogin.setEnabled(true);
            btnLogout.setEnabled(false);
            btnEnviar.setEnabled(false);
            txtServidor.setEditable(true);
            txtPorta.setEditable(true);
            btnEnviar.setEnabled(false);
            btnEnviar.removeActionListener(this);
            cliente.enviaMsg(new Protocolo(Protocolo.SAIR, ""));
            return;
        }

        if (o == btnLogin) {
            System.out.println("Login");

            String usuario = txtUsuario.getText().trim();
            if (usuario.length() == 0) {
                System.out.println("Usuario não informado");
                return;
            }

            String server = txtServidor.getText().trim();
            if (server.length() == 0) {
                System.out.println("Nome servidor não informado");
                return;
            }

            String portaString = txtPorta.getText().trim();
            if (portaString.length() == 0) {
                System.out.println("Porta não informada");
                return;
            }
            int portaNumero = 0;
            try {
                portaNumero = Integer.parseInt(portaString);
            } catch (Exception en) {
                System.out.println("Número porta não numérico");
                return;
            }

            System.out.println(server + ":" + portaNumero + " usuario:" + usuario);

            cliente = new Cliente(server, portaNumero, usuario, this);

            if (!cliente.conecta()) {
                System.out.println("Cliente não conectou ...");
                return;
            }

            txtMsg.setText("");
            conectado = true;
            btnLogin.setEnabled(false);
            btnLogout.setEnabled(true);
            btnEnviar.setEnabled(true);
            txtServidor.setEditable(false);
            txtPorta.setEditable(false);

            btnEnviar.addActionListener(this);
        }
    }

    public static void main(String[] args) {
        new ClienteGUI("localhost", 9999);
    }
}

package ifrn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorGUI extends JFrame implements ActionListener, WindowListener {

    private static final long serialVersionUID = 1L;
    private JButton btnPararIniciar;
    private JTextArea chat, eventos;
    private JTextField txtPorta;
    private Servidor servidor;

    ServidorGUI(int porta) {
        super("Servidor Chat");
        servidor = null;
        
        JPanel norte = new JPanel();
        norte.add(new JLabel("Porta: "));
        txtPorta = new JTextField("  " + porta);
        norte.add(txtPorta);
        
        btnPararIniciar = new JButton("Iniciar");
        btnPararIniciar.addActionListener(this);
        norte.add(btnPararIniciar);
        add(norte, BorderLayout.NORTH);

         
        JPanel center = new JPanel(new GridLayout(2, 1));
        chat = new JTextArea(80, 50);
        chat.setEditable(false);
        
        center.add(new JScrollPane(chat));
        eventos = new JTextArea(80, 50);
        eventos.setEditable(false);
        anexarEvento("Eventos.\n");
        center.add(new JScrollPane(eventos));
        add(center);

        addWindowListener(this);
        setSize(300, 500);
        setVisible(true);
    }

    void anexarMsg(Protocolo msg) {
        chat.append(msg.getMensagem());
        chat.setCaretPosition(chat.getText().length() - 1);
    }

    void anexarEvento(String str) {
        eventos.append(str);
        eventos.setCaretPosition(eventos.getText().length() - 1);
    }

    public void actionPerformed(ActionEvent e) {
         
        if (servidor != null) {
            servidor.parar();
            servidor = null;
            txtPorta.setEditable(true);
            btnPararIniciar.setText("Iniciar");
            return;
        }
         
        int porta;
        try {
            porta = Integer.parseInt(txtPorta.getText().trim());
        } catch (Exception er) {
            anexarEvento("Numero de Porta Inválido");
            return;
        }
         
        servidor = new Servidor(porta, this);
        
        new IniciarServidor().start();
        
        btnPararIniciar.setText("Parar");
        txtPorta.setEditable(false);
    }
     
    public static void main(String[] arg) {
        
        new ServidorGUI(9999);
    
    }

    // botao X  
    public void windowClosing(WindowEvent e) {
         
        if (servidor != null) {
            try {
                servidor.parar();			
            } catch (Exception eClose) {
            }
            servidor = null;
        }
        dispose();
        System.exit(0);
    }
     

    public void windowClosed(WindowEvent e) {
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    /*
     * thread inicia o Servidor
     */
    class IniciarServidor extends Thread {

        public void run() {
            
            try {          
                servidor.conecta();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServidorGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Servidor caiu
            btnPararIniciar.setText("Iniciar");
            txtPorta.setEditable(true);
            anexarEvento("Servidor desligado\n");
            servidor = null;
        }
    }
}

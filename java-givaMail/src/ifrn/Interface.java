package ifrn;

import java.text.ParseException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.UnsupportedLookAndFeelException;

public class Interface extends javax.swing.JFrame {

    private JOptionPane jOptionPane;
    private String path = "0";

    public Interface() throws UnsupportedLookAndFeelException, ParseException, IOException {
        initComponents();

//        Properties login = new Properties();
//        String properties = "givamail/login.properties";
//        try {
//            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(properties);
//            login.load(stream);
//        } catch (IOException ex) {
//            System.out.println("Erro: " + ex.getMessage());
//        }
//        String username = login.getProperty("hotmail.username");
//        String password = login.getProperty("hotmail.password");

        txtTo.setText("asfpacheco@hotmail.com");
        txtSubject.setText("Teste givaMail v.1.0");
        txtMsg.setText("Este é simplesmete um teste, para avaliar \n as funcionalidade do servidor de email");

        setResizable(false);
        Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        setPreferredSize(new Dimension(440, 385));
        Dimension windowSize = new Dimension(getPreferredSize());
        int wdwLeft = 50 + screenSize.width / 2 - windowSize.width / 2;
        int wdwTop = screenSize.height / 2 - windowSize.height / 2;
        pack();
        setLocation(wdwLeft, wdwTop);
        setTitle("GIVA Mail v1.0");

        Image im = Toolkit.getDefaultToolkit().getImage("mail.png");
        setIconImage(im);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTo = new javax.swing.JTextField();
        txtSubject = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMsg = new javax.swing.JTextArea();
        btnEnviar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnAnexo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jLabel3.setText("Para");

        jLabel4.setText("Assunto");

        txtMsg.setColumns(20);
        txtMsg.setRows(5);
        jScrollPane1.setViewportView(txtMsg);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("IFRN eMail");

        btnAnexo.setText("Anexo");
        btnAnexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnexoActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ifrn/letter.png"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(128, 128));
        jLabel2.setPreferredSize(new java.awt.Dimension(60, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTo, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAnexo)
                                .addGap(13, 13, 13)
                                .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnexo)
                    .addComponent(btnEnviar)
                    .addComponent(btnLimpar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed

        if(txtTo.getText().equals("") || txtSubject.getText().equals("") || txtMsg.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Boy, esta faltando alguma coisa?", "IFRN Mail", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            carregar();
        } catch (Throwable ex) {
            System.out.println("Erro: ao carregar");
        }
        
    }//GEN-LAST:event_btnEnviarActionPerformed

    public void postMail() {
        try {
            System.out.println("Chegou postmail");
            String to = txtTo.getText();
            String subject = txtSubject.getText();
            String message = txtMsg.getText();
            Enviar sender = new Enviar();

            boolean flag = sender.Send(to, subject, message, path);

            if (flag == true) {
                JOptionPane.showMessageDialog(this, "Enviado com suscesso para " + to, "IFRN Mail", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                limpar();
            } else {
                JOptionPane.showMessageDialog(this, "Boy, aconteceu um erro!", "IFRN Mail", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } catch (Throwable ex) {
            System.out.println("Erro: postmail \n"+ex);
            ex.printStackTrace();
        }
    }

    private void btnAnexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnexoActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.setMultiSelectionEnabled(true); 
        int resultado = jfc.showOpenDialog(this);
        if (resultado == JFileChooser.CANCEL_OPTION) {
            btnAnexo.setText("sem anexo");
            path = "0";
            return;
        }
        try {
            path = jfc.getSelectedFile().getPath();
            JOptionPane.showMessageDialog(this, path, "Arquivo anexado", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            btnAnexo.setText("Arquivo anexado");
            btnAnexo.setToolTipText(path);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            btnAnexo.setText("sem aenxo");
            path = "0";
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAnexoActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limpar();
    }//GEN-LAST:event_btnLimparActionPerformed

    public void limpar() {
        txtTo.setText("");
        txtSubject.setText("");
        txtMsg.setText("");
        path = "0";
        btnAnexo.setText("No Attachment");
        txtTo.requestFocus();
    }
    
    public static boolean validaEmail(String email) {
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email);
        if (m.matches()) {
            return false;
        } else {
            return true;
        }
    }

    public void carregar() throws Throwable {

        final JFrame frame = new JFrame("IFRN eMail");
        frame.setResizable(false);

        final JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        final JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new JLabel("Enviando..."), BorderLayout.NORTH);
        contentPane.add(progressBar, BorderLayout.CENTER);
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                 
                try {
                    System.out.println("Enviando email...");
                    Thread.sleep(1000);
                    postMail();
                } catch (InterruptedException e) {
                }
                 
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        frame.setVisible(false);
                    }
                });

            }
        };
        new Thread(runnable).start();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    new Interface().setVisible(true);
                } catch (UnsupportedLookAndFeelException | ParseException ex) {
                } catch (IOException ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnexo;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtMsg;
    private javax.swing.JTextField txtSubject;
    private javax.swing.JTextField txtTo;
    // End of variables declaration//GEN-END:variables
}

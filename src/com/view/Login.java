package com.view;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.model.User;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
    
/**
 *
 * @author keatnis
 */

public class Login extends javax.swing.JFrame {

    /**
     * Login form
     * create thread for the mainSystem
     * use hash for encrypted the password
     */
    private Login splashFrame = this;
    private static boolean frameStart = false;
    private final User user;
    public Login() {
        initComponents();
        Image img4 = this.toImage(new ImageIcon(getClass().getResource("/com/utils/icon/icon.jpg")));
        ImageIcon img3 = new ImageIcon(img4.getScaledInstance(lbIcon.getWidth(), lbIcon.getHeight(), Image.SCALE_SMOOTH));
        lbIcon.setIcon(img3);
        this.user = new User();
      
    }
    public Image toImage(Icon icon) {
        return ((ImageIcon) icon).getImage();
    }
    /*
    thread for the MainSystem
    loading the feactures
    */
    private void startThread (User user) {
        Thread thread = new Thread( new Runnable() {
            @Override
            public void run() {
                MainSystem appFrame = new MainSystem(user,splashFrame);
                if (!appFrame.isActive() && user.getRole().equals("admin") ){
                    appFrame.setLocationRelativeTo(null);
                    appFrame.setVisible(true);
                    frameStart = true;
                    dispose();
                    
                }else {
                    appFrame.dispose();
                }
            }
        });
        thread.start();
    }
    
    private void login () {
        user.setRole("admin");
        startThread(user);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        info = new javax.swing.JPanel();
        lbIcon = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lightDarkMode1 = new com.theme.LightDarkMode();
        login = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnClose = new JButton( new FlatSVGIcon("com/utils/icon/close.svg"));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        info.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/utils/icon/icon.jpg"))); // NOI18N
        info.add(lbIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 6, 198, 185));

        jLabel3.setText("ControlSystem");
        info.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 203, -1, -1));

        lightDarkMode1.setBackground(new java.awt.Color(204, 204, 204));
        info.add(lightDarkMode1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 228, 246, 36));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.ipady = 36;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        getContentPane().add(info, gridBagConstraints);

        login.setLayout(new java.awt.CardLayout(22, 11));

        jPanel1.setAlignmentX(2.0F);
        jPanel1.setAlignmentY(1.0F);
        jPanel1.setLayout(new java.awt.GridLayout(6, 1, 1, 23));

        jLabel1.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Iniciar Sesión");
        jPanel1.add(jLabel1);
        jPanel1.add(jTextField1);
        jPanel1.add(jPasswordField1);

        btnLogin.setBackground(new java.awt.Color(51, 51, 51));
        btnLogin.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 0));
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogin);

        login.add(jPanel1, "card2");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 124;
        gridBagConstraints.ipady = -28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 6, 0);
        getContentPane().add(login, gridBagConstraints);

        btnClose.setBorderPainted(false);
        btnClose.setContentAreaFilled(false);
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = -23;
        gridBagConstraints.ipady = 23;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 242, 0, 0);
        getContentPane().add(btnClose, gridBagConstraints);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
     login();
    }//GEN-LAST:event_btnLoginActionPerformed

    /**
     * @param args the command line arguments
     */
   public static void main(String args[]) {

        FlatLaf.registerCustomDefaultsSource("com.theme");
        FlatDarculaLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnLogin;
    private javax.swing.JPanel info;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbIcon;
    private com.theme.LightDarkMode lightDarkMode1;
    private javax.swing.JPanel login;
    // End of variables declaration//GEN-END:variables
}

package com.view;

import com.model.User;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import raven.toast.Notifications;

/**
 *
 * @author keatnis
 */
public class MainSystem extends javax.swing.JFrame {

    /**
     * Creates new form MainSystem
     */
    public JDesktopPane desktopPane;
    
    public MainSystem(User user, Login splashFrame) {
        initComponents();
        //desktopPane = new JDesktopPane();
       // this.setContentPane(desktopPane);
       
        this.setLayout(new BorderLayout());
       
    }
    
  public void showForm(Component form, String title) {
        lbTitle.setText(title);
        panelBody.removeAll();
        panelBody.add(form, BorderLayout.CENTER);
        panelBody.repaint();
        panelBody.revalidate();
       
        
    }
    
    public MainSystem() {
        //Notifications.getInstance().setJFrame(this);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        options = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        lbTitle = new javax.swing.JLabel();
        lbUser = new javax.swing.JLabel();
        panelBody = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        dashboard = new javax.swing.JMenu();
        Operador = new javax.swing.JMenu();
        vehiculo = new javax.swing.JMenu();
        servicio = new javax.swing.JMenu();
        nomina = new javax.swing.JMenu();
        workplace = new javax.swing.JMenu();
        recargas = new javax.swing.JMenu();
        menuUser = new javax.swing.JMenu();
        submenuUser = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(900, 600));

        options.setMaximumSize(null);
        options.setPreferredSize(new java.awt.Dimension(800, 30));

        jButton1.setText("Notificaciones");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lbTitle.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        lbTitle.setText("Title > Menu");

        lbUser.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        lbUser.setText("User");

        javax.swing.GroupLayout optionsLayout = new javax.swing.GroupLayout(options);
        options.setLayout(optionsLayout);
        optionsLayout.setHorizontalGroup(
            optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, optionsLayout.createSequentialGroup()
                .addGap(0, 839, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbUser)
                .addContainerGap())
            .addGroup(optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(optionsLayout.createSequentialGroup()
                    .addGap(0, 443, Short.MAX_VALUE)
                    .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 444, Short.MAX_VALUE)))
        );
        optionsLayout.setVerticalGroup(
            optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsLayout.createSequentialGroup()
                .addGroup(optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(lbUser))
                .addGap(0, 5, Short.MAX_VALUE))
            .addGroup(optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(optionsLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lbTitle)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        getContentPane().add(options, java.awt.BorderLayout.PAGE_START);

        panelBody.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("jLabel1");
        panelBody.add(jLabel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelBody, java.awt.BorderLayout.CENTER);

        dashboard.setText("Dashboard");
        MenuBar.add(dashboard);

        Operador.setText("Operadores");
        Operador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OperadorMouseClicked(evt);
            }
        });
        Operador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OperadorActionPerformed(evt);
            }
        });
        MenuBar.add(Operador);

        vehiculo.setText("Vehiculos");
        MenuBar.add(vehiculo);

        servicio.setText("Servicios");
        MenuBar.add(servicio);

        nomina.setText("Nómina");
        MenuBar.add(nomina);

        workplace.setText("Lugar Trabajo");
        MenuBar.add(workplace);

        recargas.setText("Recargas de Combustible");
        MenuBar.add(recargas);

        menuUser.setText("Users");
        menuUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUserActionPerformed(evt);
            }
        });

        submenuUser.setText("Usuarios");
        submenuUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuUserActionPerformed(evt);
            }
        });
        menuUser.add(submenuUser);

        MenuBar.add(menuUser);

        setJMenuBar(MenuBar);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUserActionPerformed

    }//GEN-LAST:event_menuUserActionPerformed

    private void submenuUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuUserActionPerformed
        UserView frame = new UserView();
    
      //  desktopPane.add(frame);
        frame.setVisible(true);
    }//GEN-LAST:event_submenuUserActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void OperadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OperadorMouseClicked
       this.showForm(new TrabajadoresView(), "Menu trbajadores");
       
    }//GEN-LAST:event_OperadorMouseClicked

    private void OperadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OperadorActionPerformed

    }//GEN-LAST:event_OperadorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenu Operador;
    private javax.swing.JMenu dashboard;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbUser;
    private javax.swing.JMenu menuUser;
    private javax.swing.JMenu nomina;
    private javax.swing.JPanel options;
    private javax.swing.JPanel panelBody;
    private javax.swing.JMenu recargas;
    private javax.swing.JMenu servicio;
    private javax.swing.JMenuItem submenuUser;
    private javax.swing.JMenu vehiculo;
    private javax.swing.JMenu workplace;
    // End of variables declaration//GEN-END:variables
}

package com.view;
import java.awt.BorderLayout;
import java.awt.Component;


import javax.swing.JOptionPane;


public class MainSystem extends javax.swing.JFrame {
    private Login splashFrame;

    public MainSystem() {

        initComponents();
        MenuBar.setVisible(false);
        this.showForm(new Dashboard(), "Dasboard");
    
//        this.showForm(new Dasboard(), "Dasboard");

    }


    MainSystem(String  user, Login splashFrame) {
        this.splashFrame = splashFrame;
        setProgress(0, "Cargando componentes del Sistema");
        initComponents();
        setProgress(15, "Conectándose la base de datos");
        setProgress(23, "Cargando Módulos");
        this.showForm(new Dashboard(), "Dasboard");
        setProgress(65, "Cargando interfaces");
        // this.setShape(new RoundRectangle2D.Double(0, 0, getWidth(),getHeight(), 20, 20));
     
        lbUser.setText("USER: "+user);
        this.repaint();
        setProgress(65, "Cargando interfaces");
        setProgress(100, "Carga completa");
//        initn();
    }


    private void setProgress(int percent, String information) {
        splashFrame.getlbprogress().setText(information);
        splashFrame.getProgressBar().setValue(percent);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(null, "No se pudo ejecutar");
        }
    }

    public void showForm(Component form) {
        showForm(form, "");
    }

    public void showForm(Component form, String title) {
        lbTitle.setText(title);
        panelBody.removeAll();
        panelBody.add(form, BorderLayout.CENTER);
        panelBody.repaint();
        panelBody.revalidate();
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popMenuUsuario = new javax.swing.JPopupMenu();
        cambiarAvatar = new javax.swing.JMenuItem();
        cambiarContraseña = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        CerrarSesion = new javax.swing.JMenuItem();
        panelBody = new javax.swing.JPanel();
        panelEnc = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbUser = new javax.swing.JLabel();
        lbTitle = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        Dasboard = new javax.swing.JMenu();
        menuPersonal = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        incidencias = new javax.swing.JMenu();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();

        cambiarAvatar.setText("Cambiar Icono");
        cambiarAvatar.setActionCommand("cvbvb");
        cambiarAvatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarAvatarActionPerformed(evt);
            }
        });
        popMenuUsuario.add(cambiarAvatar);
        cambiarAvatar.getAccessibleContext().setAccessibleName("MenuUser");
        cambiarAvatar.getAccessibleContext().setAccessibleDescription("MenuUser");

        cambiarContraseña.setText("Cambiar conrtraseña");
        cambiarContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarContraseñaActionPerformed(evt);
            }
        });
        popMenuUsuario.add(cambiarContraseña);
        popMenuUsuario.add(jSeparator1);

        CerrarSesion.setText("Cerrar Sesion");
        CerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarSesionActionPerformed(evt);
            }
        });
        popMenuUsuario.add(CerrarSesion);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Software");
        setMaximumSize(new java.awt.Dimension(3647, 2147));
        setPreferredSize(new java.awt.Dimension(1305, 750));

        panelBody.setOpaque(false);
        panelBody.setLayout(new java.awt.BorderLayout());

        panelEnc.setBackground(new java.awt.Color(0, 47, 85));
        panelEnc.setInheritsPopupMenu(true);
        panelEnc.setOpaque(false);
        panelEnc.setPreferredSize(new java.awt.Dimension(1374, 74));

        lbUser.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbUser.setForeground(new java.awt.Color(255, 255, 255));
        lbUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbUser.setText("User Name");
        lbUser.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lbTitle.setFont(new java.awt.Font("Poppins SemiBold", 1, 20)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(255, 255, 255));
        lbTitle.setText("Titulo del Módulo");

        javax.swing.GroupLayout panelEncLayout = new javax.swing.GroupLayout(panelEnc);
        panelEnc.setLayout(panelEncLayout);
        panelEncLayout.setHorizontalGroup(
            panelEncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 344, Short.MAX_VALUE)
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
                .addComponent(lbUser, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addGap(117, 117, 117))
        );
        panelEncLayout.setVerticalGroup(
            panelEncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle)
                .addGap(13, 13, 13)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelEncLayout.createSequentialGroup()
                .addComponent(lbUser)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MenuBar.setBackground(new java.awt.Color(31, 44, 81));
        MenuBar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MenuBar.setMaximumSize(new java.awt.Dimension(1054, 32769));
        MenuBar.setPreferredSize(new java.awt.Dimension(903, 28));

        Dasboard.setText("Home");
        Dasboard.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Dasboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DasboardMouseClicked(evt);
            }
        });
        MenuBar.add(Dasboard);

        menuPersonal.setText("Operadores");
        menuPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPersonalActionPerformed(evt);
            }
        });

        jMenuItem1.setText("List");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuPersonal.add(jMenuItem1);

        jMenuItem3.setText("Agegar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menuPersonal.add(jMenuItem3);

        MenuBar.add(menuPersonal);

        incidencias.setText("Usuarios");
        incidencias.setFocusPainted(true);
        incidencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incidenciasActionPerformed(evt);
            }
        });
        incidencias.add(jSeparator2);

        jMenuItem2.setText("Usuarios");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        incidencias.add(jMenuItem2);

        MenuBar.add(incidencias);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelEnc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1105, Short.MAX_VALUE)
                    .addComponent(panelBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelEnc, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPersonalActionPerformed

    }//GEN-LAST:event_menuPersonalActionPerformed

    private void DasboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DasboardMouseClicked
        // TODO add your handling code here:
        this.showForm(new Dashboard(), "Dasboard");
        this.repaint();
    }//GEN-LAST:event_DasboardMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.showForm(new TrabajadoresView(), "TRABAJADORES");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void CerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarSesionActionPerformed

        int option = JOptionPane.showConfirmDialog(null, "Esta seguro(a) de cerrar el sistema?", "Mensaje", JOptionPane.OK_CANCEL_OPTION);
        if (option == 0) {
            this.dispose();
            Login login = new Login();
            login.setVisible(true);
        }


    }//GEN-LAST:event_CerrarSesionActionPerformed

    private void cambiarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarContraseñaActionPerformed
 
    }//GEN-LAST:event_cambiarContraseñaActionPerformed

    private void cambiarAvatarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarAvatarActionPerformed
       // this.showForm(new UserView(), "Lista de Personal");
    }//GEN-LAST:event_cambiarAvatarActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        //this.showForm(new MainPanel(), "Lista Personalizada");
      
        //this.getContentPane().add(new MainPanel());

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void incidenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incidenciasActionPerformed
        
    }//GEN-LAST:event_incidenciasActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       this.showForm(new UserView(), "Usuarios");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

//    public static void main(ModelUsers user) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainSystem(user).setVisible(true);
//            }
//        });
//    }
//    public static void main(ModelUsers user) {
//        FlatIntelliJLaf.registerCustomDefaultsSource("com/style");
//        FlatLightLaf.setup();
//        FlatIntelliJLaf.setup();
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainSystem2().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem CerrarSesion;
    private javax.swing.JMenu Dasboard;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenuItem cambiarAvatar;
    private javax.swing.JMenuItem cambiarContraseña;
    private javax.swing.JMenu incidencias;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    public javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbUser;
    private javax.swing.JMenu menuPersonal;
    public javax.swing.JPanel panelBody;
    private javax.swing.JPanel panelEnc;
    private javax.swing.JPopupMenu popMenuUsuario;
    // End of variables declaration//GEN-END:variables
}

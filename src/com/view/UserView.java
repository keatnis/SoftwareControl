package com.view;

import com.dao.UserDao;
import com.model.User;
import com.utils.Hash;
import com.utils.table.RenderTable;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.utils.Filter;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;

/**
 *
 * @author keatnis
 */
public class UserView extends javax.swing.JInternalFrame {

    /**
     * Creates new form ListView
     */
    private User user;
    private final UserDao userDao;

    public UserView() {
        /*TODO :
        * opcion de cambiar la contraseña siendo administrador
        * validar formulario
         */
        tread();
        this.userDao = new UserDao();
        showData(tblUser);
        this.showForm(list);
        password.putClientProperty("PasswordField.showRevealButton ", true);

    }

    private void tread() {
        Thread loadDao = new Thread(new Runnable() {
            @Override
            public void run() {
                initComponents();
            }
        });
        loadDao.start();
    }

    //metodo para  asignar los valores de cada atributo y usar el dao para guardar los datos en la db
    private void save() {
        user = new User();
        user.setNombre(txtName.getText());
        user.setApePaterno(txtApaterno.getText());
        user.setApeMaterno(txtAMaterno.getText());
        user.setNickname(txtUsuario.getText());

        StringBuilder newPass = new StringBuilder(
                Hash.sha1(password.getPassword().toString()));
        user.setPassword(newPass.toString());
        newPass = null;
        user.setRole((String) cmbRole.getSelectedItem());
        userDao.addUser(user);

    }

    // metodo para limpiar los componentes
    private void cleaAll() {
        txtName.setText(null);
        txtAMaterno.setText("");
        txtApaterno.setText("");
        txtUsuario.setText("");
        password.setText(null);
        cmbRole.setSelectedIndex(0);

    }

    private void showData(JTable table) {
        Object[] titles = new Object[]{"ID", "NOMBRE COMPLETO", "USUARIO", "ROL"};
        /*coloco el nombre de las  columnas de la tabla USER a el modelo */
        DefaultTableModel model = new DefaultTableModel(null, titles) {

            // desactivamos la opcion de editar los datos en la tabla
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        RenderTable render = new RenderTable();
        tblUser.setDefaultRenderer(Object.class, render);
        /* obtengo la lista de */
        List<User> list = userDao.getData();
        for (User usr : list) {
            model.addRow(new Object[]{
                usr.getId(),
                usr.getNombre() + " " + usr.getApePaterno() + " " + usr.getApeMaterno(),
                usr.getNickname(),
                usr.getRole()

            });
            /*establecemos el modelo  al Jtable llamado jTabla*/

        }
        table.setModel(model);
        table.doLayout();
        /* asignamos el ancho de cada columna de la tabla*/

        int[] anchos = {30, 200, 80, 50};
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

    }

    private void editAction() {
        int row = tblUser.getSelectedRow();
   
        btnSave.setText("actualizar datos");
        btnNew.repaint();
        //pasando datos al formulario
        String[] nombre = String.valueOf(tblUser.getValueAt(row, 1)).split(" ");
        
        txtName.setText(nombre[0]);
        txtApaterno.setText(nombre[1]);
        txtAMaterno.setText(nombre[2]);
        nombre=null;
        /* para cambiar la contraseña del usuario debe ser administrador */
        password.setEnabled(false);
        txtUsuario.setText((String) tblUser.getValueAt(row, 2));
        cmbRole.setSelectedItem(tblUser.getValueAt(row, 3));

        showForm(formPanel);

    }

    private void update() {

        user = new User();
        int idUser = Integer.valueOf(
                tblUser.getValueAt(tblUser.getSelectedRow(), 0)
                        .toString());
        user.setId(idUser);
        user.setNombre(txtName.getText());
        user.setApePaterno(txtApaterno.getText());
        user.setApeMaterno(txtAMaterno.getText());
        user.setNickname(txtUsuario.getText());
        user.setRole((String) cmbRole.getSelectedItem());
        userDao.update(user);

        tblUser.removeAll();
        showData(tblUser);
    }

    private void deleteAction() {
        int idUser = Integer.valueOf(
                tblUser.getValueAt(tblUser.getSelectedRow(), 0)
                        .toString());
        userDao.delete(idUser);

    }

    public void showForm(Component form) {

        root.removeAll();
        root.add(form, BorderLayout.CENTER);
        root.repaint();
        root.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        root = new javax.swing.JPanel();
        list = new javax.swing.JPanel();
        spUser = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        txtSearch = new com.utils.components.txtPlaceholder();
        panelOptions = new javax.swing.JPanel();
        btnNew = new com.utils.components.okButton();
        btnEdit = new javax.swing.JButton();
        tbnDelete = new javax.swing.JButton();
        formPanel = new javax.swing.JPanel();
        options = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        form = new javax.swing.JPanel();
        lbName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lbApa = new javax.swing.JLabel();
        txtApaterno = new javax.swing.JTextField();
        lbAma = new javax.swing.JLabel();
        txtAMaterno = new javax.swing.JTextField();
        lbUser = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lbUser1 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        cmbRole = new javax.swing.JComboBox<>();

        setClosable(true);
        setMaximizable(true);
        setTitle("  Usuarios");
        setMaximumSize(new java.awt.Dimension(800, 750));
        setMinimumSize(new java.awt.Dimension(400, 300));
        setPreferredSize(new java.awt.Dimension(880, 580));
        getContentPane().setLayout(new java.awt.CardLayout());

        root.setLayout(new java.awt.CardLayout());

        list.setPreferredSize(new java.awt.Dimension(800, 400));
        list.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RenderTable render = new RenderTable();
        tblUser.setDefaultRenderer(Object.class, render);
        JButton btn = new JButton();
        tblUser.setAutoCreateRowSorter(true);
        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, new JButton("prueba")},
                {null, null, null, btn},

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        spUser.setViewportView(tblUser);

        list.add(spUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 710, 427));

        txtSearch.setPlaceholder("Buscar ...");
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });
        list.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 408, -1));

        panelOptions.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));
        panelOptions.setToolTipText("");
        panelOptions.setLayout(new java.awt.GridLayout(3, 1, 15, 10));

        btnNew.setText("Nuevo");
        btnNew.setFont(new java.awt.Font("Cantarell", 1, 14)); // NOI18N
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        panelOptions.add(btnNew);

        btnEdit.setText("editar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        panelOptions.add(btnEdit);

        tbnDelete.setText("eliminar");
        tbnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnDeleteActionPerformed(evt);
            }
        });
        panelOptions.add(tbnDelete);

        list.add(panelOptions, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 105, 110, 140));

        root.add(list, "card3");

        formPanel.setPreferredSize(new java.awt.Dimension(800, 400));
        formPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSave.setBackground(new java.awt.Color(51, 51, 51));
        btnSave.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        btnSave.setForeground(new java.awt.Color(204, 204, 0));
        btnSave.setText("guardar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setText("cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout optionsLayout = new javax.swing.GroupLayout(options);
        options.setLayout(optionsLayout);
        optionsLayout.setHorizontalGroup(
            optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, optionsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancel)
                .addGap(18, 18, 18)
                .addComponent(btnSave)
                .addContainerGap())
        );
        optionsLayout.setVerticalGroup(
            optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        formPanel.add(options, new org.netbeans.lib.awtextra.AbsoluteConstraints(459, 194, -1, -1));

        form.setLayout(new java.awt.GridLayout(3, 1, 10, 30));

        lbName.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbName.setText("Nombre:");
        lbName.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        form.add(lbName);
        form.add(txtName);

        lbApa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbApa.setText("A. Paterno:");
        form.add(lbApa);
        form.add(txtApaterno);

        lbAma.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbAma.setText("A. Materno");
        form.add(lbAma);
        form.add(txtAMaterno);

        lbUser.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbUser.setText("Usuario");
        form.add(lbUser);
        form.add(txtUsuario);

        lbUser1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbUser1.setText("Password");
        form.add(lbUser1);
        form.add(password);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Role");
        form.add(jLabel1);

        cmbRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Admin", "User" }));
        form.add(cmbRole);

        formPanel.add(form, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 49, 730, 145));

        root.add(formPanel, "card2");

        getContentPane().add(root, "card4");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        this.showForm(formPanel);
        btnNew.setEnabled(false);
        btnSave.setText("guardar");
        btnSave.repaint();

    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (btnSave.getText().endsWith("ardar")) {
            save();
            cleaAll();
            this.showForm(list);
            btnNew.setEnabled(true);
        } else if (btnSave.getText().endsWith("datos")) {
            update();
            cleaAll();
            this.showForm(list);
            btnNew.setEnabled(true);
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        cleaAll();
        showForm(list);
        btnNew.setEnabled(true);


    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        Filter.searchInTable(txtSearch, tblUser);
    }//GEN-LAST:event_txtSearchKeyTyped

    private void tbnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnDeleteActionPerformed
        deleteAction();
        tblUser.removeAll();
        showData(tblUser);
    }//GEN-LAST:event_tbnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (tblUser.getSelectedRow() >= 0) {
            editAction();
        }


    }//GEN-LAST:event_btnEditActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEdit;
    private com.utils.components.okButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbRole;
    private javax.swing.JPanel form;
    private javax.swing.JPanel formPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbAma;
    private javax.swing.JLabel lbApa;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbUser;
    private javax.swing.JLabel lbUser1;
    private javax.swing.JPanel list;
    private javax.swing.JPanel options;
    private javax.swing.JPanel panelOptions;
    private javax.swing.JPasswordField password;
    private javax.swing.JPanel root;
    private javax.swing.JScrollPane spUser;
    private javax.swing.JTable tblUser;
    private javax.swing.JButton tbnDelete;
    private javax.swing.JTextField txtAMaterno;
    private javax.swing.JTextField txtApaterno;
    private javax.swing.JTextField txtName;
    private com.utils.components.txtPlaceholder txtSearch;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}

package com.view;

import com.dao.TrabajadoresDAO;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.model.ContactoEmergencia;
import com.model.Operador;
import com.mysql.cj.jdbc.Blob;
import com.utils.ChooseFile;
import com.utils.Filter;
import com.utils.Validaciones;
import com.utils.table.RenderTable;
import java.util.List;
import javax.sql.rowset.serial.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author keatnis
 */
public class TrabajadoresView extends javax.swing.JPanel {

    private byte[] pdf;
    private final TrabajadoresDAO trabajadoresDAO;
    private Operador operador;

    public TrabajadoresView() {
        initComponents();
        this.showForms(false, true);

        this.trabajadoresDAO = new TrabajadoresDAO();
        showData(tblOperador);
        clearAll();

    }

    private void showData(JTable table) {
        Filter.removeAllRows(table);
        Object[] titles = new Object[]{
            "ID", "NOMBRE COMPLETO", "PUESTO", "TELEFONO", "TELEFONO 2",
            "DIRECCION", "TIPO SANGRE", "INE", "CONTACTO EMERG."};
        /*coloco el nombre de las  columnas de la tabla USER a el modelo */
        DefaultTableModel model = new DefaultTableModel(null, titles) {

            // desactivamos la opcion de editar los datos en la tabla
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        RenderTable render = new RenderTable();
        table.setDefaultRenderer(Object.class, render);
        /* obtengo la lista de */
        List<Operador> list = trabajadoresDAO.operadores();

        for (Operador op : list) {

            model.addRow(new Object[]{
                op.getId(),
                op.getNombre() + " " + op.getApePaterno() + " " + op.getApeMaterno(),
                op.getPuesto(),
                op.getTelefono(),
                op.getTelefono2(),
                op.getCalle() + " " + op.getNum() + " " + op.getColonia() + " " + op.getCiudad() + " " + op.getEstado(),
                op.getTypeblood(),
                new JButton( new FlatSVGIcon("com/utils/icon/pdf.svg")),
                op.getContactoEmergencia().getTelefono() + " " + op.getContactoEmergencia().getNombre() + " "
                // op.getContactoEmergencia().getNombre() + " " + op.getContactoEmergencia().getApeMaterno() + " "
                //+ op.getContactoEmergencia().getApePaterno() + " " + op.getContactoEmergencia().getTelefono() + " "
                + op.getContactoEmergencia().getParentesco()

            });
            /*establecemos el modelo  al Jtable llamado jTabla*/

        }
        table.setRowHeight(30);
        table.setModel(model);
        table.doLayout();
        /* asignamos el ancho de cada columna de la tabla*/

        int[] anchos = {30, 200, 120, 80, 60, 200, 35, 50, 200};
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

    }

    private void editAction() {
        int row = tblOperador.getSelectedRow();

        btnSave.setText("actualizar datos");
        btnNew.repaint();
        //pasando datos al formulario

        String[] nombre = String.valueOf(tblOperador.getValueAt(row, 1)).split(" ");
        System.out.println("long " + nombre.length);
        switch (nombre.length) {
            case 1:
                txtNombre.setText(nombre[0]);
                break;
            case 2:
                txtNombre.setText(nombre[0]);
                txtAPaterno.setText(nombre[1]);

                break;
            case 3:
                txtNombre.setText(nombre[0]);
                txtAPaterno.setText(nombre[1]);
                txtAMaterno.setText(nombre[2]);
                break;
            default:
                throw new AssertionError();
        }

        /*
        ID", "NOMBRE COMPLETO", "PUESTO", "TELEFONO", "TELEFONO 2",
            "DIRECCION", "TIPO SANGRE", "INE/CREDENCIAL", "CONTACTO EMERG
         */
        nombre = null;
        txtPuesto.setText(String.valueOf(tblOperador.getValueAt(row, 2)));
        txtTelefono.setText(String.valueOf(tblOperador.getValueAt(row, 3)));
        txtTelefono2.setText(String.valueOf(tblOperador.getValueAt(row, 4)));

        cmbTypeblood.setSelectedItem(tblOperador.getValueAt(row, 6));
//        String[] contacto = String.valueOf(tblOperador.getValueAt(row, 8)).split(" ");
//        /*no se agregaron todos los campos*/
//        txtTelefonoCont.setText(contacto[0]);
//        txtNombreCont.setText(contacto[1]);
//        cmbParentescoCont.setSelectedItem(contacto[2]);
//        contacto = null;
        showForms(true, false);

        //  showForm(formPanel);
    }

    private void update() {

        int idUser = Integer.valueOf(
                tblOperador.getValueAt(tblOperador.getSelectedRow(), 0)
                        .toString());

        operador = new Operador();
        operador.setId(idUser);
        operador.setNombre(txtNombre.getText());
        operador.setApePaterno(txtAPaterno.getText());
        operador.setApeMaterno(txtAMaterno.getText());
        operador.setCalle(txtCalle.getText());
        operador.setColonia(txtColonia.getText());
        operador.setCiudad(txtCiudad.getText());
        operador.setEstado(txtEstado.getText());

        operador.setNum(txtNum.getText());
        operador.setTelefono(txtTelefono.getText());
        operador.setTelefono2(txtTelefono2.getText());
        operador.setAlergias(txtAlergias.getText());
        operador.setTypeblood((String) cmbTypeblood.getSelectedItem());
        operador.setPuesto(txtPuesto.getText());
        operador.setFile(pdf);
        // datos contacto emergencia

        ContactoEmergencia contactoEmergencia = new ContactoEmergencia();

        contactoEmergencia.setNombre(txtNombreCont.getText());
        contactoEmergencia.setApeMaterno(txtAPaternoCont.getText());
        contactoEmergencia.setApePaterno(txtAMaternoCont.getText());
        contactoEmergencia.setTelefono(txtTelefonoCont.getText());
        contactoEmergencia.setParentesco((String) cmbParentescoCont.getSelectedItem());

        operador.setContactoEmergencia(contactoEmergencia);
        trabajadoresDAO.edit(operador);

        showData(tblOperador);
    }

    private void deleteAction() {
        int idUser = Integer.valueOf(
                tblOperador.getValueAt(tblOperador.getSelectedRow(), 0)
                        .toString());
        trabajadoresDAO.delete(idUser);
           showData(tblOperador);

    }

    public void showForms(boolean showForm, boolean showList) {

        panelForm.setVisible(showForm);
        if (panelForm.isVisible()) {
            this.getAccessibleContext();
        }
        panelList.setVisible(showList);
    }

    private void save() {
        operador = new Operador();
        operador.setNombre(txtNombre.getText());
        operador.setApePaterno(txtAPaterno.getText());
        operador.setApeMaterno(txtAMaterno.getText());
        operador.setCalle(txtCalle.getText());
        operador.setColonia(txtColonia.getText());
        operador.setCiudad(txtCiudad.getText());
        operador.setEstado(txtEstado.getText());

        operador.setNum(txtNum.getText());
        operador.setTelefono(txtTelefono.getText());
        operador.setTelefono2(txtTelefono2.getText());
        operador.setAlergias(txtAlergias.getText());
        operador.setTypeblood((String) cmbTypeblood.getSelectedItem());
        operador.setPuesto(txtPuesto.getText());
        operador.setFile(pdf);
        // datos contacto emergencia

        ContactoEmergencia contactoEmergencia = new ContactoEmergencia();

        contactoEmergencia.setNombre(txtNombreCont.getText());
        contactoEmergencia.setApeMaterno(txtAPaternoCont.getText());
        contactoEmergencia.setApePaterno(txtAMaternoCont.getText());
        contactoEmergencia.setTelefono(txtTelefonoCont.getText());
        contactoEmergencia.setParentesco((String) cmbParentescoCont.getSelectedItem());

        operador.setContactoEmergencia(contactoEmergencia);

        trabajadoresDAO.save(operador);

    }

    private void clearAll() {
        txtNombre.setText("");
        txtAPaterno.setText("");
        txtAMaterno.setText("");
        txtCalle.setText("");
        txtColonia.setText("");
        txtCiudad.setText("");
        txtEstado.setText("");
        txtNum.setText("");
        txtTelefono.setText("");
        txtTelefono2.setText("");
        txtAlergias.setText("");
        cmbTypeblood.setSelectedIndex(0);
        txtPuesto.setText("");
        pdf = null;
        txtNombreCont.setText("");
        txtAPaternoCont.setText("");
        txtAMaternoCont.setText("");
        txtTelefonoCont.setText("");
        cmbParentescoCont.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        panelList = new javax.swing.JPanel();
        panelSearch = new javax.swing.JPanel();
        txtSearch = new com.utils.components.txtPlaceholder();
        spt = new javax.swing.JScrollPane();
        tblOperador = new javax.swing.JTable();
        options = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        panelForm = new javax.swing.JPanel();
        form = new javax.swing.JPanel();
        lbNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lbAPaterno = new javax.swing.JLabel();
        txtAPaterno = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAMaterno = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTelefono2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPuesto = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtAlergias = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCalle = new javax.swing.JTextField();
        txtNum = new javax.swing.JTextField();
        txtColonia = new javax.swing.JTextField();
        txtCiudad = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtNombreCont = new javax.swing.JTextField();
        txtAPaternoCont = new javax.swing.JTextField();
        txtAMaternoCont = new javax.swing.JTextField();
        txtTelefonoCont = new javax.swing.JTextField();
        cmbParentescoCont = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        cmbTypeblood = new javax.swing.JComboBox<>();
        lbStatus = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        main.setLayout(new java.awt.CardLayout());

        panelList.setLayout(new java.awt.BorderLayout(0, 10));

        panelSearch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtSearch.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtSearch.setMaximumSize(null);
        txtSearch.setPlaceholder("Buscar ...");
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });
        panelSearch.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, -1));

        panelList.add(panelSearch, java.awt.BorderLayout.PAGE_START);

        tblOperador.setAutoCreateRowSorter(true);
        tblOperador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        spt.setViewportView(tblOperador);

        panelList.add(spt, java.awt.BorderLayout.CENTER);

        options.setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridLayout(3, 0, 0, 4));

        btnNew.setText("Agregar Nuevo");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel1.add(btnNew);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar);

        btnDelete.setText("eliminar");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnDelete);

        options.add(jPanel1, new java.awt.GridBagConstraints());

        panelList.add(options, java.awt.BorderLayout.EAST);
        options.getAccessibleContext().setAccessibleName("Option");

        main.add(panelList, "card2");

        panelForm.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        form.setMaximumSize(null);

        lbNombre.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbNombre.setText("Nombre");

        lbAPaterno.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbAPaterno.setText("A. Paterno");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("A. Materno");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Telefono");

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Tel.2 /celular");

        txtTelefono2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefono2KeyTyped(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Puesto/cargo");

        jLabel10.setText("Alergias");

        jLabel11.setText("Tipo Sangre");

        jLabel1.setText("Calle");

        jLabel5.setText("No.");

        jLabel2.setText("Colonia");

        jLabel6.setText("Ciudad");

        jLabel8.setText("Estado");

        txtNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumKeyTyped(evt);
            }
        });

        txtEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Contanto de Emergencia"));

        jLabel12.setText("Nombre");

        jLabel13.setText("A. Pateno");

        jLabel14.setText("A. Materno");

        jLabel15.setText("Teléfono");

        jLabel16.setText("Parentesco");

        txtTelefonoCont.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoContKeyTyped(evt);
            }
        });

        cmbParentescoCont.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Esposa", "Esposo", "Hijo", "Hija", "Padre", "Madre", "Hermana", "Hermano", "Amigo", "Amiga", "Otro parentesco" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbParentescoCont, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtTelefonoCont, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                        .addComponent(txtAMaternoCont, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtAPaternoCont, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtNombreCont, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtNombreCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAPaternoCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAMaternoCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefonoCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cmbParentescoCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jButton2.setText("Agregar INE/Licencia");
        jButton2.setToolTipText("* Copia del INE / Licencia");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnCancel.setText("cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnSave.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.default.focusColor"));
        btnSave.setText("guardar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        cmbTypeblood.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "O+", "O-", "AB+", "AB-", "A+", "A-", "B+", "B-" }));

        javax.swing.GroupLayout formLayout = new javax.swing.GroupLayout(form);
        form.setLayout(formLayout);
        formLayout.setHorizontalGroup(
            formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEstado, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCiudad, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtColonia, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNum, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCalle, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formLayout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAlergias, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                            .addComponent(txtTelefono2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtAMaterno, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formLayout.createSequentialGroup()
                                .addComponent(lbAPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(txtAPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(formLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(formLayout.createSequentialGroup()
                                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(formLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(txtPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(formLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbTypeblood, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 39, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSave)
                        .addGap(22, 22, 22))))
        );
        formLayout.setVerticalGroup(
            formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formLayout.createSequentialGroup()
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbAPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtAlergias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(cmbTypeblood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel1)
                            .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel5)
                            .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtColonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(11, 11, 11)
                        .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel6)
                            .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel8)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(formLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave)
                            .addComponent(btnCancel))
                        .addGap(16, 16, 16))))
        );

        panelForm.add(form, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        main.add(panelForm, "card3");

        add(main, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        this.showForms(true, false);
        btnNew.setEnabled(false);
        btnSave.setText("guardar");
        btnSave.repaint();


    }//GEN-LAST:event_btnNewActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ChooseFile chooseFile = new ChooseFile();
        pdf = chooseFile.agregar_pdf(lbStatus);
        lbStatus.repaint();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtNumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumKeyTyped
        Validaciones.esNumeroEntero(evt);
    }//GEN-LAST:event_txtNumKeyTyped

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        if (btnSave.getText().endsWith("ardar")) {
            save();
            clearAll();
            this.showForms(false, true);
            // this.showForm(list);
            this.showData(tblOperador);
            btnNew.setEnabled(true);
        } else if (btnSave.getText().endsWith("datos")) {
            update();
            clearAll();
            this.showForms(false, true);

            btnNew.setEnabled(true);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoActionPerformed

    }//GEN-LAST:event_txtEstadoActionPerformed

    private void txtTelefono2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefono2KeyTyped
        Validaciones.esNumeroEntero(evt);
    }//GEN-LAST:event_txtTelefono2KeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        Validaciones.esNumeroEntero(evt);
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtTelefonoContKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoContKeyTyped
        Validaciones.esNumeroEntero(evt);
    }//GEN-LAST:event_txtTelefonoContKeyTyped

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        clearAll();
        this.showForms(false, true);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (tblOperador.getSelectedRow() >= 0) {
            this.showForms(true, false);
            editAction();

        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        Filter.searchInTable(txtSearch, tblOperador);
    }//GEN-LAST:event_txtSearchKeyTyped

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
      this.deleteAction();
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbParentescoCont;
    private javax.swing.JComboBox<String> cmbTypeblood;
    private javax.swing.JPanel form;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbAPaterno;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JPanel main;
    private javax.swing.JPanel options;
    private javax.swing.JPanel panelForm;
    private javax.swing.JPanel panelList;
    private javax.swing.JPanel panelSearch;
    private javax.swing.JScrollPane spt;
    private javax.swing.JTable tblOperador;
    private javax.swing.JTextField txtAMaterno;
    private javax.swing.JTextField txtAMaternoCont;
    private javax.swing.JTextField txtAPaterno;
    private javax.swing.JTextField txtAPaternoCont;
    private javax.swing.JTextField txtAlergias;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtColonia;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreCont;
    private javax.swing.JTextField txtNum;
    private javax.swing.JTextField txtPuesto;
    private com.utils.components.txtPlaceholder txtSearch;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefono2;
    private javax.swing.JTextField txtTelefonoCont;
    // End of variables declaration//GEN-END:variables
}

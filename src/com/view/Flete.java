package com.view;

import com.dao.DetalleCombustibleDAO;
import java.util.List;
import com.model.DetalleCombustible;

/**
 *
 * @author keatnis
 */
public class Flete extends javax.swing.JPanel {

    /**
     * Creates new form Flete
     */
    private DetalleCombustible detalleCombustible;
    private DetalleCombustibleDAO detalleCombustibleDAO;

    public Flete() {
        initComponents();
        this.detalleCombustibleDAO = new DetalleCombustibleDAO();
        getITems();
    }

    private void getITems() {
        this.cmbTipoCompustible.removeAllItems();

        List<DetalleCombustible> ve = detalleCombustibleDAO.getAllDetalles();

        for (DetalleCombustible vehiculol : ve) {

            this.cmbTipoCompustible.addItem(vehiculol.getTipo());

            detalleCombustible = new DetalleCombustible();
            detalleCombustible.setId(vehiculol.getId());
            detalleCombustible.setPrecio(vehiculol.getPrecio());
            detalleCombustible.setTipo(vehiculol.getTipo());

        }
        this.cmbTipoCompustible.repaint();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        flete = new javax.swing.JPanel();
        combustible = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        rebEfectivo = new javax.swing.JRadioButton();
        rbTransferencia = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        cmbTipoCompustible = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        txtLitrosCargados = new javax.swing.JTextField();
        txtMonto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtKM = new javax.swing.JTextField();
        btnDetalleCombustible = new javax.swing.JButton();

        flete.setBorder(javax.swing.BorderFactory.createTitledBorder("Flete"));
        flete.setToolTipText("");

        javax.swing.GroupLayout fleteLayout = new javax.swing.GroupLayout(flete);
        flete.setLayout(fleteLayout);
        fleteLayout.setHorizontalGroup(
            fleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        fleteLayout.setVerticalGroup(
            fleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
        );

        combustible.setBorder(javax.swing.BorderFactory.createTitledBorder("Recarga de combustible"));

        jLabel1.setText("Gasolinera");

        jComboBox1.setEditable(true);

        jLabel2.setText("Fecha:");

        jLabel3.setText("Tipo de pago:");

        rebEfectivo.setText("Efectivo");

        rbTransferencia.setText("Transferencia/Deposito");

        jLabel5.setText("Tipo de Combustible:");

        cmbTipoCompustible.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoCompustibleItemStateChanged(evt);
            }
        });

        txtLitrosCargados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLitrosCargadosKeyReleased(evt);
            }
        });

        jLabel6.setText("Litros Cargados");

        jLabel7.setText("Monto");

        jLabel8.setText("Odometro actual:");

        btnDetalleCombustible.setText("Ajuste Precio");
        btnDetalleCombustible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleCombustibleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout combustibleLayout = new javax.swing.GroupLayout(combustible);
        combustible.setLayout(combustibleLayout);
        combustibleLayout.setHorizontalGroup(
            combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(combustibleLayout.createSequentialGroup()
                .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(combustibleLayout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, combustibleLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel6))))
                .addGap(18, 18, 18)
                .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(combustibleLayout.createSequentialGroup()
                        .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(combustibleLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rebEfectivo)
                                .addGap(31, 31, 31)
                                .addComponent(rbTransferencia))
                            .addGroup(combustibleLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(cmbTipoCompustible, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jSeparator1)
                    .addGroup(combustibleLayout.createSequentialGroup()
                        .addComponent(txtLitrosCargados, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtKM, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDetalleCombustible)
                .addContainerGap())
        );
        combustibleLayout.setVerticalGroup(
            combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(combustibleLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(rebEfectivo)
                    .addComponent(rbTransferencia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbTipoCompustible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDetalleCombustible))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(30, 30, 30)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLitrosCargados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(flete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(combustible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(flete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combustible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDetalleCombustibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleCombustibleActionPerformed
        DetallesCombustible dialog = new DetallesCombustible(new javax.swing.JFrame(), true);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                dialog.setVisible(false);
            }
        });
        dialog.setVisible(true);
    }//GEN-LAST:event_btnDetalleCombustibleActionPerformed

    private void cmbTipoCompustibleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoCompustibleItemStateChanged
        txtMonto.setText("");
        if (!txtMonto.getText().equals("")) {
            Float precioxLitro = detalleCombustible.getPrecio();
            Float litros = Float.parseFloat(txtLitrosCargados.getText());
            txtMonto.setText(litros * precioxLitro + "");
        }

    }//GEN-LAST:event_cmbTipoCompustibleItemStateChanged

    private void txtLitrosCargadosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLitrosCargadosKeyReleased
        Float precioxLitro = detalleCombustible.getPrecio();
        Float litros = Float.parseFloat(txtLitrosCargados.getText());
        txtMonto.setText(litros * precioxLitro + "");
    }//GEN-LAST:event_txtLitrosCargadosKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetalleCombustible;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbTipoCompustible;
    private javax.swing.JPanel combustible;
    private javax.swing.JPanel flete;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton rbTransferencia;
    private javax.swing.JRadioButton rebEfectivo;
    private javax.swing.JTextField txtKM;
    private javax.swing.JTextField txtLitrosCargados;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}

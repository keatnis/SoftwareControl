package com.view;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.model.ModelCard;
import javax.swing.Icon;

/**
 *
 * @author keatnis
 */
public class Dashboard extends javax.swing.JPanel {

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        init();
    }
    
    private void init() {
        cardOperadores.setData(new ModelCard("125", "TOTAL TRABAJADORES", "com/utils/icon/person.svg"));
        cardVehiculos.setData(new ModelCard("123", "Total Vehiculos", "com/utils/icon/delivery.svg"));
        cardRecargas.setData(new ModelCard("122", "Recargas del mes", "com/utils/icon/gas-delivery.svg"));
        cardFletes.setData(new ModelCard("1233", "Fletes del Mes", "com/utils/icon/delivery.svg"));
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        root = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        cards = new javax.swing.JPanel();
        cardOperadores = new com.utils.components.Card();
        cardVehiculos = new com.utils.components.Card();
        cardRecargas = new com.utils.components.Card();
        cardFletes = new com.utils.components.Card();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();

        setLayout(new java.awt.CardLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Proximos Servicios");

        jLabel2.setText("Fletes activos");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        cards.setLayout(new java.awt.GridLayout(1, 0, 11, 0));

        cardOperadores.setColor1(new java.awt.Color(0, 153, 255));
        cards.add(cardOperadores);

        cardVehiculos.setColor1(new java.awt.Color(204, 204, 0));
        cards.add(cardVehiculos);

        cardRecargas.setColor1(new java.awt.Color(102, 204, 0));
        cards.add(cardRecargas);

        cardFletes.setColor1(new java.awt.Color(255, 153, 0));
        cards.add(cardFletes);

        jLabel3.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel3.setText("Fletes activos");

        jTable3.setAutoCreateRowSorter(true);
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable3);

        jLabel4.setText("Servicios de este mes");

        jTable4.setAutoCreateRowSorter(true);
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jTable4);

        javax.swing.GroupLayout rootLayout = new javax.swing.GroupLayout(root);
        root.setLayout(rootLayout);
        rootLayout.setHorizontalGroup(
            rootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(rootLayout.createSequentialGroup()
                        .addGroup(rootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(rootLayout.createSequentialGroup()
                                .addGroup(rootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(rootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(cards, javax.swing.GroupLayout.DEFAULT_SIZE, 1215, Short.MAX_VALUE))
        );
        rootLayout.setVerticalGroup(
            rootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootLayout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(rootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rootLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(cards, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(671, Short.MAX_VALUE)))
        );

        jScrollPane3.setViewportView(root);

        add(jScrollPane3, "card2");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.utils.components.Card cardFletes;
    private com.utils.components.Card cardOperadores;
    private com.utils.components.Card cardRecargas;
    private com.utils.components.Card cardVehiculos;
    private javax.swing.JPanel cards;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JPanel root;
    // End of variables declaration//GEN-END:variables
}

package com.utils.components;

import com.model.ModelCard;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Card extends javax.swing.JPanel {

    ImageIcon icon;
    public Card() {
        initComponents();
        init();
    }

    private void init() {
        setOpaque(false);
        setBackground(Color.WHITE);
      
    }

    public void setIcon(String ruta) {
        this.icon = icon;
        lbIcon.setIcon(new ImageIcon(getClass().getResource(ruta)));
    
    }

    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Area area = new Area(new RoundRectangle2D.Double(0, 20, getWidth(), getHeight() - 20, 20, 20));
        g2.setColor(getBackground());
        g2.fill(area);
        area.subtract(new Area(new Rectangle2D.Double(0, 20, getWidth(), getHeight() - 23)));
     //   g2.setPaint(new GradientPaint(0, 0, lbIcon.getBackground(), getWidth(), 0, lbIcon.getForeground()));
        g2.fill(area);
        g2.dispose();
        super.paintComponent(g);
    }

    public void setDescription(String description) {
        lbDescription.setText(description);
    }

    public String getDescription() {
        return lbDescription.getText();
    }

    public void setValues(String values) {
        lbValues.setText(values);
    }

    public String getValues() {
        return lbValues.getText();
    }

    public void setData(ModelCard data) {
        lbValues.setText(data.getValues());
        lbDescription.setText(data.getDescription());
//        if (data.getColor1() != null) {
//            setColor1(data.getColor1());
//        }
//        if (data.getColor2() != null) {
//            setColor2(data.getColor2());
//        }
//        if (data.getIcon() != null) {
//            setIcon(data.getIcon());
//        }
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbValues = new javax.swing.JLabel();
        lbDescription = new javax.swing.JLabel();
        lbIcon = new javax.swing.JLabel();

        lbValues.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        lbValues.setForeground(new java.awt.Color(51, 51, 51));
        lbValues.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbValues.setText("100");

        lbDescription.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lbDescription.setForeground(new java.awt.Color(51, 51, 51));
        lbDescription.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbDescription.setText("Report Income Monthly");

        lbIcon.setText("lc");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbValues, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 36, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbValues, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbDescription)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbDescription;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbValues;
    // End of variables declaration//GEN-END:variables
}

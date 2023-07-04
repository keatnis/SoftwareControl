package com.utils.components;

import com.formdev.flatlaf.FlatClientProperties;

import com.formdev.flatlaf.FlatLaf;
import javax.swing.JButton;

/**
 *
 * @author keatnis
 */
public class okButton extends JButton {

    public okButton() {
        FlatLaf.updateUI();
        addStyle();
    }

    private void addStyle() {
        boolean isDark = FlatLaf.isLafDark();
        if (isDark) {
            this.putClientProperty(FlatClientProperties.STYLE, ""
                    + "arc:200;"
                    + "background:#ede42c;"
                    + "foreground:#10100f;"
                    + "focusWidth:1");

        } else {
            this.putClientProperty(FlatClientProperties.STYLE, ""
                    + "arc:200;"
                    + "background:#10100f;"
                    + "foreground:#fffffa;"
                    + "focusWidth:1");
        }
    }

}

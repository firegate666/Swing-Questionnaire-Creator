package de.mb.swing;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * @author Marco Behnke
 *
 */
public class JStatusBar extends JPanel {

    public JStatusBar() {
        initialize();
    }

    private void initialize() {
        this.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }
}

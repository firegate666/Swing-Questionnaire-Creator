package de.mb.swing;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The JLabelTextField can alter its outfit between Label and TextField by clicking on it.
 * After entering text an commiting it by return, the outfit switches back.
 * 
 * @author Marco Behnke
 *
 */
public class JLabelTextField extends JPanel {

    private JLabel _jlabel;
    private JTextField _jtextfield;

    /**
     * Public constructor
     * @param text Default text
     */
    public JLabelTextField(String text) {
        super(new BorderLayout());
        this._jlabel = new JLabel();
        this._jlabel.addMouseListener(new ClickLabelListener(this));

        this._jtextfield = new JTextField();
        this._jtextfield.addKeyListener(new LeaveTextFieldListener(this));

        this.add(this._jlabel);

        setText(text);

    }

    /**
     * Shows text field in foreground
     */
    public void showTextField() {
        this.remove(this._jlabel);
        this.add(this._jtextfield);
        this.updateUI();
    }
    /**
     * Shows label in foreground
     */
    public void showLabel() {
        this.remove(this._jtextfield);
        this.add(this._jlabel);
        this.setText(this._jtextfield.getText());
        this.updateUI();
    }

    /**
     * Sets text of textfield
     */
    public void setText(String text) {
        this._jlabel.setText(text);
        this._jtextfield.setText(text);
    }

    // Internal classes
    class LeaveTextFieldListener implements KeyListener {
        private JLabelTextField _app;
        public LeaveTextFieldListener(JLabelTextField app) {
            this._app = app;
        }
        public void keyPressed(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {}
        public void keyTyped(KeyEvent e) {
            if ((int) e.getKeyChar() == 10)
                this._app.showLabel();
        }
    }

    class ClickLabelListener implements MouseListener {
        private JLabelTextField _app;
        public ClickLabelListener(JLabelTextField app) {
            this._app = app;
        }
        public void mouseClicked(MouseEvent e) {
            this._app.showTextField();
        }
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
    }
}
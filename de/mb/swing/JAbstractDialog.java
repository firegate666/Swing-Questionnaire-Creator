package de.mb.swing;

import java.awt.AWTEvent;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Marco Behnke
 *
 */
public abstract class JAbstractDialog extends Dialog {

    protected int _width;
    protected int _height;

    public void show() {
        super.show();
    }

    private void initialize() {
        this.addWindowListener(new DialogListener());
        this.setBounds(this.x(), this.y(), _width, _height);
    }
    private int x() {
        return Toolkit.getDefaultToolkit().getScreenSize().width / 2
            - _width / 2;
    }

    private int y() {
        return Toolkit.getDefaultToolkit().getScreenSize().height / 2
            - _height / 2;
    }

    /**
     * Constructor for JAbstractDialog.
     * @param owner
     */
    public JAbstractDialog(Frame owner) {
        super(owner);
        this.initialize();
    }

    /**
     * Constructor for JAbstractDialog.
     * @param owner
     * @param modal
     */
    public JAbstractDialog(Frame owner, boolean modal) {
        super(owner, modal);
        this.initialize();
    }

    /**
     * Constructor for JAbstractDialog.
     * @param owner
     * @param title
     */
    public JAbstractDialog(Frame owner, String title) {
        super(owner, title);
        this.initialize();
    }

    /**
     * Constructor for JAbstractDialog.
     * @param owner
     * @param title
     * @param modal
     */
    public JAbstractDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        this.initialize();
    }

    /**
     * Constructor for JAbstractDialog.
     * @param owner
     * @param title
     * @param modal
     * @param gc
     */
    public JAbstractDialog(
        Frame owner,
        String title,
        boolean modal,
        GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
        this.initialize();
    }

    /**
     * Constructor for JAbstractDialog.
     * @param owner
     */
    public JAbstractDialog(Dialog owner) {
        super(owner);
        this.initialize();
    }

    /**
     * Constructor for JAbstractDialog.
     * @param owner
     * @param title
     */
    public JAbstractDialog(Dialog owner, String title) {
        super(owner, title);
        this.initialize();
    }

    /**
     * Constructor for JAbstractDialog.
     * @param owner
     * @param title
     * @param modal
     */
    public JAbstractDialog(Dialog owner, String title, boolean modal) {
        super(owner, title, modal);
        this.initialize();
    }

    /**
     * Constructor for JAbstractDialog.
     * @param owner
     * @param title
     * @param modal
     * @param gc
     */
    public JAbstractDialog(
        Dialog owner,
        String title,
        boolean modal,
        GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
        this.initialize();
    }

    // Internal Classes
    class DialogCloseAction {
        private void action(AWTEvent e) {
            ((Dialog) e.getSource()).dispose();
        }
        public DialogCloseAction(AWTEvent e) {
            action(e);
        }
    }
    class DialogListener extends WindowAdapter {
        public DialogListener() {
            super();
        }
        public void windowClosing(WindowEvent e) {
            new DialogCloseAction(e);
        }
    }

}

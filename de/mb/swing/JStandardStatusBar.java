package de.mb.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Standard status bar with three containers left, center and right. The right
 * panel contains a system clock panel, left and center can be used freely.
 * 
 * @author Marco Behnke
 *
 */
public class JStandardStatusBar extends JStatusBar {

    private JPanel _left;
    private JPanel _center;
    private JLabel _right;
    private Timer _clock_timer;

    /**
     * Public constructor.
     */
    public JStandardStatusBar() {
        initialize();
    }

    private JLabel getTimePanel() {
        _right = new JLabel();
        setTime();
        return _right;
    }

    /**
     * Return the left panel of the status bar.
     * 
     * @return left panel
     */
    public JPanel getLeft() {
        if (_left == null)
            _left = new JPanel();
        return _left;
    }

    /**
     * Return the center panel of the status bar.
     * 
     * @return center panel
     */
    public JPanel getCenter() {
        if (_center == null)
            _center = new JPanel();
        return _center;
    }

    private void initialize() {
        _left = new JPanel();
        _center = new JPanel();
        _right = getTimePanel();

        this.setLayout(new BorderLayout());
        this.add(_left, BorderLayout.WEST);
        this.add(_center, BorderLayout.CENTER);
        this.add(_right, BorderLayout.EAST);

        _clock_timer = new Timer(1000, new SetClockActionListener(this));
        _clock_timer.start();
    }

    private void setTime() {
        _right.setText((new Date()).toString());
    }

    class SetClockActionListener implements ActionListener {

        private JStandardStatusBar _app;

        public SetClockActionListener(JStandardStatusBar app) {
            _app = app;
        }

        /**
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e) {
            _app.setTime();
        }

    }
}

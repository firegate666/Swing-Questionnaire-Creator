package de.mb.swing;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 * @author Marco Behnke
 *
 */
public class SwingTest {

    public static void main(String[] args) {
        class ClickButtonListener implements MouseListener {
            private JNumberedTextArea _n;
            public ClickButtonListener(JNumberedTextArea n) {
                _n = n;
            }
            public void mouseClicked(MouseEvent e) {
                _n.switchVisibleLineCount();
            }
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
        }
        JDefaultFrame jdf = new JDefaultFrame();
        jdf.getContentPane().setLayout(new BorderLayout());

        JTabbedPane jtp = new JTabbedPane();

        JPanel p = new JPanel(new BorderLayout());
        JNumberedTextArea n = new JNumberedTextArea();
        JButton b = new JButton("Change NumberedLines");
        b.addMouseListener(new ClickButtonListener(n));
        p.add(n, BorderLayout.CENTER);
        p.add(b, BorderLayout.SOUTH);
        jtp.add("JNumberedTextArea", p);

        JScrollPane ta = new JAdvancedTextArea().getScrollPane();
        jtp.add("JAdvancedTextArea", ta);

        jtp.add("JLabelTextField", new JLabelTextField(""));

        jdf.getContentPane().add(jtp, BorderLayout.CENTER);
        jdf.getContentPane().add(new JStandardStatusBar(), BorderLayout.SOUTH);

        jdf.setBounds(10, 10, 640, 480);
        jdf.show();

    }

}

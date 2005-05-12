package de.mb.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class JNumberedTextArea extends JScrollPane {

    private JList _lines;
    private Vector _numbers;
    private JAdvancedTextArea _text;
    private JPanel _panel;

    /**
     * deprecated
     */
    public JNumberedTextArea getPane() {
        return this;
    }

    /**
     * Switches the line count panel from visible to invisible an vice versa.
     */
    public void switchVisibleLineCount() {
        if (_lines.isVisible())
            this.disableNumberedLines();
        else
            this.enableNumberedLines();
    }
    
    public void setEditable(boolean editable) {
    	_text.setEditable( editable);
    }

    /**
     * Disables the line count panel
     */
    public void enableNumberedLines() {
        _lines.setVisible(true);
        this.setLineCount();
    }
    /**
     * Enables the line count panel
     */
    public void disableNumberedLines() {
        _lines.setVisible(false);
    }

    /**
     * Public constructor
     * @param s Default text for text area
     */
    public JNumberedTextArea(String s) {
        this();
        this.setText(s);
    }

    private void setLineCount() {
        if (_lines.isVisible()) {
            if (_numbers.size() < _text.getLineCount()) {
                for (int i = _numbers.size(); i < _text.getLineCount(); i++)
                    _numbers.add(new Integer(i + 1));
            } else if (_numbers.size() == _text.getLineCount()) {} else {
                for (int i = _numbers.size() - 1;
                    i >= _text.getLineCount();
                    i--)
                    _numbers.remove(i);
            }
            _lines.updateUI();
        }
    }

    /**
     * Returns actual number of lines.
     * @return int number of lines
     */
    public int getLineCount() {
        return _text.getLineCount();
    }

    private JNumberedTextArea(Component view) {
        super(view);
    }

    private JList getJList() {
        _numbers = new Vector();
        _lines = new JList(_numbers);
        _lines.setFixedCellHeight(16);
        _lines.setBackground(Color.LIGHT_GRAY);
        _lines.addMouseListener(new SelectLineListener(this));
        return _lines;
    }

    private JAdvancedTextArea getJAdvancedTextArea() {
        _text = new JAdvancedTextArea();
        _text.addKeyListener(new ChangeTextAction(this));
        return _text;
    }

    /**
     * Public default construtor
     */
    public JNumberedTextArea() {
        _panel = new JPanel(new BorderLayout());
        this.setViewportView(_panel);

        this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
        this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);

        _panel.add(this.getJList(), BorderLayout.WEST);
        _panel.add(this.getJAdvancedTextArea(), BorderLayout.CENTER);

        setLineCount();
    }

    /**
     * Returns content of text area.
     * @return String content of text area
     */
    public String getText() {
        return _text.getText();
    }

    /**
     * Sets content of text area
     * @param s new Text
     */
    public void setText(String s) {
        _text.setText(s);
        _text.setSelectionStart(0);
        _text.setSelectionEnd(0);
        this.setLineCount();
    }

    /**
     * Adds content to text area
     * @param s text to be added
     * @param newline if true, line break is added before new content
     */
    public void addText(String s, boolean newline) {
        _text.addText(s, newline);
		this.setLineCount();

    }

    /**
     * Adds content to text area
     * @param s text to be added
     */
    public void addText(String s) {
        this.addText(s,true);
    }

    /**
     * Selects and highlights line <i>linenumber</i>
     * @param linenumber selected line
     */
    public void selectLine(int linenumber) {
        _text.selectLine(linenumber);
    }

    // Internal classes
    class SelectLineListener implements MouseListener {
        private JNumberedTextArea app;
        public SelectLineListener(JNumberedTextArea app) {
            this.app = app;
        }

        public void mouseClicked(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {
            JList l = (JList) e.getSource();
            int line = ((Integer) l.getSelectedValue()).intValue();
            app.selectLine(line);
        }
    }

    class ChangeTextAction implements KeyListener {
        private JNumberedTextArea app;
        public ChangeTextAction(JNumberedTextArea app) {
            this.app = app;
        }
        public void actionPerformed(ActionEvent e) {}
        public void keyTyped(KeyEvent e) {}
        public void keyPressed(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {
            app.setLineCount();
        }
    }
}

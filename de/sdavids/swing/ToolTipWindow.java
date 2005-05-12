package de.sdavids.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.ToolTipManager;

public class ToolTipWindow
			extends TimedWindow {

	JLabel fToolTipLbl;

    public ToolTipWindow() {
		this("");
    }
	
	public String getText() {
	    return fToolTipLbl.getText();
	} 

	public void setText(String text) {
	    fToolTipLbl.setText(text);
	    repaint();
	} 
		
    public ToolTipWindow(String text) {
        super(ToolTipManager.sharedInstance().getDismissDelay());
		
		fToolTipLbl = new JLabel(text);
		
		getContentPane().add(fToolTipLbl);
		setBackground(Color.pink);
     }

	public void paint(Graphics g) {
	    Dimension size = getSize();
    
	    g.drawRect(0, 0, (size.width - 1), (size.height - 1));
	    g.drawString(getText(), 2, (size.height - 4));
	}
	
	public Dimension getPreferredSize() {
	    Dimension result = super.getPreferredSize();

	    int w = result.width + 4;
	    
	    result.setSize(w, result.height);

	    return result;
	}
}


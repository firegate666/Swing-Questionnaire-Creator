package de.sdavids.swing.dialog;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;

import javax.swing.JDialog;

public class CenteredDialog
			extends JDialog {

	public CenteredDialog(Dialog owner) {
		super(owner);
	}
	
	public CenteredDialog(Dialog owner, String title) {
		super(owner, title);
	}
	
	public CenteredDialog(Dialog owner, String title, boolean modal) {
		super(owner, title, modal);
	}
	
	public CenteredDialog(Frame owner) {
		super(owner);
	}
	
	public CenteredDialog(Frame owner, String title) {
		super(owner, title);
	}
	
	public CenteredDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
	}
	
	public CenteredDialog(Frame owner, boolean modal) {
		super(owner, modal);
	}
	
	public void show() {
		Frame     parent = (Frame) getParent();
		Point     loc    = parent.getLocationOnScreen();
		Dimension dim    = parent.getSize();
		Dimension size   = getSize();
		Dimension screen = getToolkit().getScreenSize();
	
		loc.x += (dim.width  - size.width)  / 2;
		loc.y += (dim.height - size.height) / 2;
	
		if (loc.x < 0) loc.x = 0;
		if (loc.y < 0) loc.y = 0;
	
		if (size.width  > screen.width)
			size.width  = screen.width;
		if (size.height > screen.height)
			size.height = screen.height;
	
		if (loc.x + size.width  > screen.width)
			loc.x = screen.width  - size.width;
		if (loc.y + size.height > screen.height)
			loc.y = screen.height - size.height;
	
		setBounds(loc.x, loc.y, size.width, size.height);

		super.show();
	}  
}
package de.sdavids.swing;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.SwingConstants;

public class SwingUtils {
    
    private static InfoTipWindow INFO_TIP;
    
	protected SwingUtils() {}

	public static Window showBelow(Component parent, Window window) {
		return showBelow(parent, window, SwingConstants.CENTER);
	}		
		
	public static Window showBelow(Component parent, Window window,
								   int horizontalAlignment) {
	    final int DISTANCE = 2;
	    
	    if (null == parent)
            throw new IllegalArgumentException("parent null");
	    if (null == window)
            throw new IllegalArgumentException("parent null");
		if (! ((SwingConstants.LEADING == horizontalAlignment)
				|| (SwingConstants.TRAILING == horizontalAlignment)
				|| (SwingConstants.CENTER == horizontalAlignment)
				|| (SwingConstants.SOUTH == horizontalAlignment))) {
				    
		    throw new IllegalArgumentException("Illegal horizontal alignment " 
						    + horizontalAlignment 
						    + " should be either SwingConstants.LEADING or "
						    + "SwingConstants.TRAILING or SwingConstants.CENTER");
		}
						
	    window.pack();
	    
		Dimension windowSize = window.getPreferredSize();
	    Dimension parentSize = parent.getSize();

	    Point loc = parent.getLocationOnScreen();
	    
	    int newX;
	    
		if ((SwingConstants.CENTER == horizontalAlignment)
			|| (SwingConstants.SOUTH == horizontalAlignment)) {
			    
		    newX = (parentSize.width - windowSize.width) / 2 + loc.x;
		} else if (SwingConstants.TRAILING == horizontalAlignment) {
		    newX = loc.x + parentSize.width - windowSize.width;
		} else {
		    newX = loc.x;		    
		}
		
	    window.setLocation(newX, (loc.y + parentSize.height + DISTANCE));
		
		window.setVisible(true);
		
		return window;
	}
	
	public static Window showCentered(Component parent, Window window) {
	    if (null == window)
            throw new IllegalArgumentException("window null");
	    
	    if (window instanceof Dialog) {
	    	Dialog dialog = (Dialog) window;
	    	
			boolean isResizable = dialog.isResizable();
		
		    dialog.setResizable(true);
	    	dialog.pack();
		    dialog.setResizable(isResizable);
	    } else {
	    	window.pack();
	    }
	    
		Dimension windowSize   = window.getPreferredSize();
		
		int newX;
		int newY;
		
		if (null == parent) {
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
			newX = (screen.width  - windowSize.width)  / 2;
			newY = (screen.height - windowSize.height) / 2;
		} else {
			Dimension parentSize = parent.getSize();
		    Point     loc        = parent.getLocation();
		    
			newX = (parentSize.width  - windowSize.width)  / 2 + loc.x;
			newY = (parentSize.height - windowSize.height) / 2 + loc.y;
			
			if (0 > newX) newX = 0;
			if (0 > newY) newY = 0;
		}

		window.setLocation(newX, newY);
		window.setVisible(true);
		
		return window;
	}
	
	public static Window showCentered(Window window) {
	    return showCentered(null, window);
	}
	
	public static void showInfoTip(Component parent, String text) {
	    showInfoTip(parent, text, SwingConstants.SOUTH);
	}
	
	public static void showInfoTip(Component parent, String text, int placement) {
	    INFO_TIP = new InfoTipWindow(text);
	    
	    if (SwingConstants.CENTER == placement) {
			showCentered(parent, INFO_TIP);
	    } else if ((SwingConstants.LEADING == placement)
	    			|| (SwingConstants.TRAILING == placement)
	    			|| (SwingConstants.SOUTH == placement)) {
	    			    
	        showBelow(parent, INFO_TIP, placement);
	    }
	}
}
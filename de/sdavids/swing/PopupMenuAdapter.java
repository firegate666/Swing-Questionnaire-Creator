package de.sdavids.swing;

import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 * An abstract adapter class for receiving popup menu events.
 * 
 * The methods in this class are empty. This class exists as
 * convenience for creating listener objects.
 *
 * @see javax.swing.event.PopupMenuListener
 */
abstract public class PopupMenuAdapter
			implements PopupMenuListener {

	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
	public void popupMenuCanceled(PopupMenuEvent e) {}
}  

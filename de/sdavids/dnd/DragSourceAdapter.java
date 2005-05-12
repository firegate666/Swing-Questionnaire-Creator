package de.sdavids.dnd;

import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceDragEvent;

/**
 * An abstract adapter class for receiving drag events.
 * 
 * The methods in this class are empty. This class exists as
 * convenience for creating listener objects.
 *
 * @see java.awt.dnd.DragSourceListener
 *
 * @version 1.0 00-11-06
 * @author Sebastian Davids
 */
abstract public class DragSourceAdapter
			implements DragSourceListener {
		
	public void dragDropEnd(DragSourceDropEvent e) {}
	public void dragExit(DragSourceEvent e) {}
	public void dropActionChanged(DragSourceDragEvent e) {}
	public void dragOver(DragSourceDragEvent e) {}
	public void dragEnter(DragSourceDragEvent e) {}
}  

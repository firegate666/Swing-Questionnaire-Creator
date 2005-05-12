package de.sdavids.dnd;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetListener;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetDragEvent;

/**
 * An abstract adapter class for receiving drop events.
 * 
 * The methods in this class are empty. This class exists as
 * convenience for creating listener objects.
 *
 * @see java.awt.dnd.DropTargetListener
 *
 * @version 1.0 00-11-06
 * @author Sebastian Davids
 */
abstract public class DropTargetAdapter
			implements DropTargetListener {

	protected boolean isDragAcceptable(DropTargetDragEvent e) {
		return true/*(DnDConstants.ACTION_COPY == e.getDropAction())*/;
	}
	
	protected boolean isDragAcceptable(DropTargetDropEvent e) {
		return true/*(DnDConstants.ACTION_COPY == e.getDropAction())*/;
	}
	
	abstract protected boolean execute(DropTargetDropEvent e);
	
	public void drop(DropTargetDropEvent e) {
    	if (!isDragAcceptable(e)) {
    		e.rejectDrop();
    		return;
    	}
    	
    	e.acceptDrop(e.getDropAction());
    	e.dropComplete(execute(e));
    }
    
    public void dragExit(DropTargetEvent e) {}
    public void dragOver(DropTargetDragEvent e) {}

    public void dropActionChanged(java.awt.dnd.DropTargetDragEvent e) {
    	if (!isDragAcceptable(e)) {
    		e.rejectDrag();
    		return;
    	}
    }

    public void dragEnter(DropTargetDragEvent e) {
    	if (!isDragAcceptable(e)) {
    		e.rejectDrag();

    		return;
    	}
    }		
}  

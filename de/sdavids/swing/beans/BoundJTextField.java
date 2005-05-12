package de.sdavids.swing.beans;

import de.sdavids.beans.IBoundBean;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JTextField;
import javax.swing.event.SwingPropertyChangeSupport;
import javax.swing.text.Document;

public class BoundJTextField
			extends JTextField
			implements IBoundBean {

//////////////////////////////////////////////////////////////////////////////
// ATTRIBUTES
//////////////////////////////////////////////////////////////////////////////

/* ----------- Instance Attributes ---------- */
    /** Bound property support */
    protected transient SwingPropertyChangeSupport fPCS;

	/**
	 * Constructor for BoundJTextField.
	 */
	public BoundJTextField() {
		super();
	}

	/**
	 * Constructor for BoundJTextField.
	 * @param arg0
	 */
	public BoundJTextField(String arg0) {
		super(arg0);
	}

	/**
	 * Constructor for BoundJTextField.
	 * @param arg0
	 */
	public BoundJTextField(int arg0) {
		super(arg0);
	}

	/**
	 * Constructor for BoundJTextField.
	 * @param arg0
	 * @param arg1
	 */
	public BoundJTextField(String arg0, int arg1) {
		super(arg0, arg1);
	}

	/**
	 * Constructor for BoundJTextField.
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public BoundJTextField(Document arg0, String arg1, int arg2) {
		super(arg0, arg1, arg2);
	}
	
	public void setText(String text) {
		String oldText = getText();
		
		super.setText(text);
		
		firePropertyChange("text", oldText, text);
	}
	
/////////////////////////////////////////////////////////////////////////////
// PROTECTED METHODS
/////////////////////////////////////////////////////////////////////////////

/* ----------- Mutators --------------------- */

	/**
	 * Answer the internal <code>SwingPropertyChangeSupport</code>.
	 * 
	 * @return	<code>listener</code> the
	 * 			<code>SwingPropertyChangeSupport</code>.
	 */
	protected SwingPropertyChangeSupport getPropertyChange() {
	    if (null == fPCS)
	    	fPCS = new SwingPropertyChangeSupport(this);
	
	    return fPCS;
	}

//////////////////////////////////////////////////////////////////////////////
// IMPLEMENT IBOUNDBEAN
//////////////////////////////////////////////////////////////////////////////

/* ----------- Mutators --------------------- */

	/**
	 * Add a PropertyChangeListener to the listener list. The listener is
	 * registered for all properties.
	 * 
	 * @param	<code>listener</code> the <code>PropertyChangeListener</code>
	 * 			to be added.
	 */
	public synchronized void addPropertyChangeListener(
											PropertyChangeListener listener) {
	
	    getPropertyChange().addPropertyChangeListener(listener);
	}
	
	/**
	 * Add a PropertyChangeListener for a specific property. The listener
	 * will be invoked only when a call on firePropertyChange names that
	 * specific property.
	 * 
	 * @param	<code>propertyName</code> the name of the property to listen
	 * 			on.
	 * @param	<code>listener</code> the <code>PropertyChangeListener</code>
	 * 			to be added.
	 */	
	public synchronized void addPropertyChangeListener(
						String propertyName, PropertyChangeListener listener) {
	
	    getPropertyChange().addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * Remove a PropertyChangeListener from the listener list. This removes a
	 * PropertyChangeListener that was registered for all properties.
	 * 
	 * @param	<code>listener</code> the <code>PropertyChangeListener</code>
	 * 			to be removed.
	 */	
	public synchronized void removePropertyChangeListener(
											PropertyChangeListener listener) {
												
	    getPropertyChange().removePropertyChangeListener(listener);
	}

	/**
	 * Add a PropertyChangeListener for a specific property. The listener will
	 * be invoked only when a call on firePropertyChange names that specific
	 * property.
	 * 
	 * @param	<code>propertyName</code> the name of the property to listen
	 * 			on.
	 * @param	<code>listener</code> the <code>PropertyChangeListener</code>
	 * 			to be removed.
	 */		
	public synchronized void removePropertyChangeListener(
						String propertyName, PropertyChangeListener listener) {
							
	    getPropertyChange().removePropertyChangeListener(propertyName,
	    												 listener);
	}
	
	/**
	 * Fire an existing PropertyChangeEvent to any registered listeners. No
	 * event is fired if the given event's old and new values are equal and
	 * non-null.
	 * 
	 * @param	<code>evt</code> the <code>PropertyChangeEvent</code> object.
	 */	
	public void firePropertyChange(PropertyChangeEvent evt) {
	    getPropertyChange().firePropertyChange(evt);
	}

	/**
	 * Report a bound property update to any registered listeners. No event is
	 * fired if old and new are equal and non-null.
	 * 
	 * This is merely a convenience wrapper around the more general
	 * <code>firePropertyChange</code> method that takes <code>Object</code>
	 * values.
	 * 
	 * @param	<code>propertyName</code> the programmatic name of the 
	 * 			property that was changed.
	 * @param	<code>oldValue</code> the old value of the property.
	 * @param	<code>newValue</code> the new value of the property.
	 */		
	public void firePropertyChange(String propertyName,
								   Object oldValue, Object newValue) {

	    getPropertyChange().firePropertyChange(propertyName,
	    									   oldValue, newValue);
	}
		
	/**
	 * Report an int bound property update to any registered listeners. No
	 * event is fired if old and new are equal and non-null.
	 * 
	 * This is merely a convenience wrapper around the more general
	 * <code>firePropertyChange</code> method that takes <code>Object</code>
	 * values.
	 * 
	 * @param	<code>propertyName</code> the programmatic name of the
	 * 			property that was changed.
	 * @param	<code>oldValue</code> the old value of the property.
	 * @param	<code>newValue</code> the new value of the property.
	 */		
	public void firePropertyChange(String propertyName, 
								   int oldValue, int newValue) {
								   	
	    getPropertyChange().firePropertyChange(propertyName,
	                                           oldValue, newValue);
	}
	
	/**
	 * Report an boolean bound property update to any registered listeners. No
	 * event is fired if old and new are equal and non-null.
	 * 
	 * This is merely a convenience wrapper around the more general
	 * <code>firePropertyChange</code> method that takes <code>Object</code>
	 * values.
	 * 
	 * @param	<code>propertyName</code> the programmatic name of the
	 *   		property that was changed.
	 * @param	<code>oldValue</code> the old value of the property.
	 * @param	<code>newValue</code> the new value of the property.
	 */		
	public void firePropertyChange(String propertyName,
								   boolean oldValue, boolean newValue) {
								   	
	    getPropertyChange().firePropertyChange(propertyName,
	    									   oldValue, newValue);
	}

/* ----------- Accessors -------------------- */

	/**
	 * Check if there are any listeners for a specific property.
	 * 
	 * @param	<code>listener</code> the property name.
	 * @return	<code>true</code>  if there are ore or more listeners for the
	 * 			given property.
	 */	
	public synchronized boolean hasListeners(String propertyName) {
	    return getPropertyChange().hasListeners(propertyName);
	}  
}


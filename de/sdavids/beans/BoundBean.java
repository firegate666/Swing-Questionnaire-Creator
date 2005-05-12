package de.sdavids.beans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.event.SwingPropertyChangeSupport;

/**
 * Implementation of the <code>IBoundBean</code> interface.
 *
 * Has an internal <code>SwingPropertyChangeSupport</code>.
 */
abstract public class BoundBean
			implements IBoundBean {

	/** Bound property support. */
    private transient SwingPropertyChangeSupport fPCS;

    /**
     * No-argument constructor.
     */
	protected BoundBean() {
		super();
	}

	/**
	 * Answer the internal <code>SwingPropertyChangeSupport</code>.
	 * 
	 * @return	<code>listener</code> the
	 * 			<code>SwingPropertyChangeSupport</code>.
	 */
	protected SwingPropertyChangeSupport getPropertyChange() {
	    if (null == fPCS) fPCS = new SwingPropertyChangeSupport(this);
	
	    return fPCS;
	}

// Implement IBoundBean
	
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
	 * Add a PropertyChangeListener for a specific property. The listener will
	 * be invoked only when a call on firePropertyChange names that specific
	 * property.
	 * 
	 * @param	<code>propertyName</code> the name of the property to listen on.
	 * @param	<code>listener</code> the <code>PropertyChangeListener</code>
	 * 			to be added.
	 */	
	public synchronized void addPropertyChangeListener(
						String propertyName, PropertyChangeListener listener) {
	
	    getPropertyChange().addPropertyChangeListener(propertyName, listener);
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
	 * @param	<code>propertyName</code> the programmatic name of the property
	 * 			that was changed.
	 * @param	<code>oldValue</code> the old value of the property.
	 * @param	<code>newValue</code> the new value of the property.
	 */		
	public void firePropertyChange(String propertyName,
								   Object oldValue, Object newValue) {

//System.out.println("fire "+propertyName+" "+oldValue+" -> "+newValue);								   	
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
	 * @param	<code>propertyName</code> the programmatic name of the property
	 * 			that was changed.
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
	 * @param	<code>propertyName</code> the programmatic name of the property
	 * 			that was changed.
	 * @param	<code>oldValue</code> the old value of the property.
	 * @param	<code>newValue</code> the new value of the property.
	 */		
	public void firePropertyChange(String propertyName,
								   boolean oldValue, boolean newValue) {
								   	
	    getPropertyChange().firePropertyChange(propertyName,
	    									   oldValue, newValue);
	}

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
	 * @param	<code>propertyName</code> the name of the property to listen on.
	 * @param	<code>listener</code> the <code>PropertyChangeListener</code>
	 * 			to be removed.
	 */		
	public synchronized void removePropertyChangeListener(
						String propertyName, PropertyChangeListener listener) {
							
	    getPropertyChange().removePropertyChangeListener(propertyName, listener);
	}
}
package de.sdavids.beans;

import java.beans.PropertyChangeListener;
import java.io.Serializable;

public interface IBoundBean
			extends Serializable {
				
    /**
     * Add a <code>PropertyChangeListener</code> to the listener list.
     *
     * The listener is registered for all properties.
     *
     * @param     <code>listener</code> the
     *            <code>PropertyChangeListener</code> to be added.
     */
    void addPropertyChangeListener(PropertyChangeListener listener);

    /**
     * Add a <code>PropertyChangeListener</code> for a specific property.
     *
     * The listener will be invoked only when a call on
     * <code>firePropertyChange</code> names that specific property.
     *
     * @param     <code>propertyName</code> the name of the property to
     *            listen on.
     * @param     <code>listener</code> the
     *            <code>PropertyChangeListener</code> to be added.
     */
    void addPropertyChangeListener(String propertyName,
								   PropertyChangeListener listener);
                                          
    /**
     * Check if there are any listeners for a specific property.
     *
     * @param     <code>propertyName</code> the property name.
     * @return    <code>true</code> if there are one or more listeners for the
     *            given property.
     */
    boolean hasListeners(String propertyName);

    /**
     * Remove a <code>PropertyChangeListener</code> from the listener list.
     *
     * This removes a <code>PropertyChangeListener</code> that was registered
     * for all properties.
     *
     * @param     <code>listener</code> the <code>PropertyChangeListener</code>
     *            to be removed.
     */
    void removePropertyChangeListener(PropertyChangeListener listener);

    /**
     * Remove a <code>PropertyChangeListener</code> for a specific property.
     *
     * @param     <code>propertyName</code> the name of the property that was
     *            listened on.
     * @param     <code>listener</code> the <code>PropertyChangeListener</code>
     *            to be removed.
     */
    void removePropertyChangeListener(String propertyName,
									  PropertyChangeListener listener); 
}


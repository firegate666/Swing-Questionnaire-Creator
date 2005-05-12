package de.sdavids.beans.boundprimitives;

import java.io.Serializable;

import de.sdavids.beans.BoundBean;

public class BoundBoolean
			extends BoundBean
            implements Serializable {

    private Boolean fValue;
	
	public BoundBoolean() {
	    super();
	}
	
	public boolean getValue() {
	    return fValue.booleanValue();
	}
	
	public void setValue(boolean value) {
	    Boolean oldValue = fValue;
	    
	    fValue = new Boolean(value);
	    
	    firePropertyChange("value", oldValue, new Boolean(value));
	}
}
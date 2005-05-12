package de.sdavids.beans.boundprimitives;

import java.io.Serializable;

import de.sdavids.beans.BoundBean;

public class BoundString
			extends BoundBean
            implements Serializable {

    private String fValue;
	
	public BoundString() {
	    super();
	}
	
	public String getValue() {
	    return fValue;
	}
	
	public void setValue(String value) {
	    String oldValue = fValue;
	    
	    fValue = value;
	    
	    firePropertyChange("value", oldValue, value);
	}
}
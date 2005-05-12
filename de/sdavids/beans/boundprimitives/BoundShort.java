package de.sdavids.beans.boundprimitives;

import java.io.Serializable;

import de.sdavids.beans.BoundBean;

public class BoundShort
			extends BoundBean
            implements Serializable {

    private Short fValue;
	
	public BoundShort() {
	    super();
	}
	
	
	public short getValue() {
	    return fValue.shortValue();
	}
	
	public void setValue(short value) {
	    Short oldValue = fValue;
	    
	    fValue = new Short(value);
	    
	    firePropertyChange("value", oldValue, new Short(value));
	}
}
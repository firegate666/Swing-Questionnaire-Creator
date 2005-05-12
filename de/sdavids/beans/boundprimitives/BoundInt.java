package de.sdavids.beans.boundprimitives;

import java.io.Serializable;

import de.sdavids.beans.BoundBean;

public class BoundInt
			extends BoundBean
            implements Serializable {

    private Integer fValue;
	
	public BoundInt() {
	    super();
	}
	
	public int getValue() {
	    return fValue.intValue();
	}
	
	public void setValue(int value) {
	    Integer oldValue = fValue;
	    
	    fValue = new Integer(value);
	    
	    firePropertyChange("value", oldValue, new Integer(value));
	}
}
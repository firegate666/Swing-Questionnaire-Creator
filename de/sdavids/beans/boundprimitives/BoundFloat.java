package de.sdavids.beans.boundprimitives;

import java.io.Serializable;

import de.sdavids.beans.BoundBean;

public class BoundFloat
			extends BoundBean
            implements Serializable {

    private Double fValue;
	
	public BoundFloat() {
	    super();
	}
	
	
	public double getValue() {
	    return fValue.doubleValue();
	}
	
	public void setValue(double value) {
	    Double oldValue = fValue;
	    
	    fValue = new Double(value);
	    
	    firePropertyChange("value", oldValue, new Double(value));
	}
}
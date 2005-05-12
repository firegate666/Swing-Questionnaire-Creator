package de.sdavids.beans.boundprimitives;

import java.io.Serializable;

import de.sdavids.beans.BoundBean;

public class BoundDouble
			extends BoundBean
            implements Serializable {

    private Double fValue;
	
	public BoundDouble() {
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
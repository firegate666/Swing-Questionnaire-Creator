package de.sdavids.beans.boundprimitives;

import java.io.Serializable;

import de.sdavids.beans.BoundBean;

public class BoundLong
			extends BoundBean
            implements Serializable {

    private Long fValue;
	
	public BoundLong() {
	    super();
	}
	
	public long getValue() {
	    return fValue.longValue();
	}
	
	public void setValue(long value) {
	    Long oldValue = fValue;
	    
	    fValue = new Long(value);
	    
	    firePropertyChange("value", oldValue, new Long(value));
	}
}
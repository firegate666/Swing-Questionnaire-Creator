package de.sdavids.beans.boundprimitives;

import java.io.Serializable;

import java.util.Date;
import de.sdavids.beans.BoundBean;

public class BoundDate
			extends BoundBean
            implements Serializable {

	private Date fValue;
	
	public BoundDate() {
	    super();
	}
	
	public Date getValue() {
	    return fValue;
	}
	
	public void setValue(Date value) {
	    Date oldValue = fValue;
	    
	    fValue = value;
	    
	    firePropertyChange("value", oldValue, value);
	}
}
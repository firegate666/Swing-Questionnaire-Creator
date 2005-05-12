package de.sdavids.beans.boundprimitives;

import java.io.Serializable;

import de.sdavids.beans.BoundBean;

public class BoundByte
			extends BoundBean
            implements Serializable {

	private Byte fValue;
	
	public BoundByte() {
	    super();
	}
	
	public byte getValue() {
	    return fValue.byteValue();
	}
	
	public void setValue(byte value) {
	    Byte oldValue = fValue;
	    
	    fValue = new Byte(value);
	    
	    firePropertyChange("value", oldValue, new Byte(value));
	}
}
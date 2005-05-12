package de.sdavids.beans.boundprimitives;

import java.io.Serializable;

import de.sdavids.beans.BoundBean;

public class BoundChar
			extends BoundBean
            implements Serializable {

    private Character fValue;
	
	public BoundChar() {
	    super();
	}
	
	public char getValue() {
	    return fValue.charValue();
	}
	
	public void setValue(char value) {
	    Character oldValue = fValue;
	    
	    fValue = new Character(value);
	    
	    firePropertyChange("value", oldValue, new Character(value));
	}
}
package de.sdavids.beans;

import java.io.Serializable;

import de.sdavids.interfaces.INullable;

public class NullBean
            implements Serializable, INullable {
            	
    public NullBean() {}

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    public static boolean isNull(Object obj) {
        return (null == obj);
    }
    
    public boolean isNull() {
    	return true;
    }
}
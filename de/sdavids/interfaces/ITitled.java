/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */
package de.sdavids.interfaces;

/**
 * Interface for a bound R/W property called <em>title</em>.
 * 
 * @author		Sebastian Davids
 * @version	1.00	02-01-18
 */
public interface ITitled {
	
	/* ----------- Mutators --------------------- */
	
    /**
     * Set the title.
     *
	 * Fires a <code>PropertyChangeEvent<code> called <em>title</em>.
	 * 
     * @param	title		the title.
     * @throws	IllegalArgumentException	if <code>title</code> is
     *    		<code>null</code> or an empty string.
     */
    void setTitle(String title) throws IllegalArgumentException;

	/* ----------- Accessors -------------------- */
	
    /**
     * Answer the title.
     *
     * @return	the title.
     */
    String getTitle();
}


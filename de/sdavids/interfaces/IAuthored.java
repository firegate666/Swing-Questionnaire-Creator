package de.sdavids.interfaces;

import de.sdavids.dataobjects.person.IPerson;

/**
 * Interface for a bound R/W property called <em>author</em>.
 * 
 * @author		Sebastian Davids
 * @version	1.00	02-01-18
 */
public interface IAuthored {

	/* ----------- Mutators --------------------- */
		
    /**
     * Set the author.
     *
	 * Fires a <code>PropertyChangeEvent<code> called <em>author</em>.
	 * 
     * @param	author		the author.
     * @throws	IllegalArgumentException	if <code>author</code> is
     *    		<code>null</code>.
     */	
	void setAuthor(IPerson author);
	
	/* ----------- Accessors -------------------- */

    /**
     * Answer the author.
     *
     * @return	the author.
     */		
	IPerson getAuthor();
}


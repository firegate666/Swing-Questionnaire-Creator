/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */
package de.sdavids.interfaces;

/**
 * Null-object interface.
 * 
 * Reference:<br>
 * 
 * <a href="http://www.refactoring.com/catalog/introduceNullObject.html">Introduce Null Object</a>
 * 
 * @author		Sebastian Davids
 * @version	1.00	02-01-18
 */
public interface INullable {
	
	/* ----------- Accessors -------------------- */

	/**
	 * Answer if we are the <em>Null-object</em> or not.
	 *
	 * @return	<code>true</code> if we are the <em>Null-object</em>.
	 */	
	public boolean isNull();
}


/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */
package de.sdavids.swing;

import javax.swing.DefaultListCellRenderer;

/**
 * Renders an item in a list.
 * <p>
 * The sole difference to the <code>DefaultListCellRenderer</code> is that
 * cells are rendered non-opaque.
 * 
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @version	1.10	01-08-21
 */
public class NonOpaqueDefaultListCellRenderer
	extends DefaultListCellRenderer {

	//////////////////////////////////////////////////////////////////////////	
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Constructs a default renderer object for an item
	 * in a list.
	 */
	public NonOpaqueDefaultListCellRenderer() {
		super();
		
		setOpaque(false);
	}
}
package de.sdavids.swing;

import de.sdavids.util.ConstantType;

public class OrientationConstants extends ConstantType {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Class Attributes ---------- */
		
	/** Indicates the OK button. */
	public static final OrientationConstants EAST_WEST =
		new OrientationConstants(1, "East-West");

	/** Indicates the Cancel button. */
	public static final OrientationConstants NORTH_SOUTH =
		new OrientationConstants(2, "North-South");		
		
	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	  * Construct and initialize a new <code>OrientationConstants</code>.
	  *
	  * @param <code>code</code> the code.
	  * @param <code>description</code> the descriptive string.
	  */
	protected OrientationConstants(int code, String description) {
		super(code, description);
	}			
}
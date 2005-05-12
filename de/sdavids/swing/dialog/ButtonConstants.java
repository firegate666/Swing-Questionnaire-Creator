package de.sdavids.swing.dialog;

import de.sdavids.util.ConstantType;

public class ButtonConstants extends ConstantType {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Class Attributes ---------- */
		
	/** Indicates the OK button. */
	public static final ButtonConstants OK =
		new ButtonConstants(1, "OK");

	/** Indicates the Cancel button. */
	public static final ButtonConstants CANCEL =
		new ButtonConstants(2, "Cancel");		
		
	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	  * Construct and initialize a new <code>TermintatingButton</code>.
	  *
	  * @param <code>code</code> the code.
	  * @param <code>description</code> the descriptive string.
	  */
	protected ButtonConstants(int code, String description) {
		super(code, description);
	}			
}

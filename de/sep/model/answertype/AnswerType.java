package de.sep.model.answertype;

/*
 * SEP-Projekt WS 2001-2002 -- Questionaire
 * 
 * Projektteilnehmer:
 * Marco Behnke <marco@firegate.de>
 * Sebastian Davids <sdavids@gmx.de>
 * Martin Koose <martin@koose-hh.de>
 * 
 * Projektbegleitung: 
 * Prof. Dr. Bernd Kahlbrandt <Bernd.Kahlbrandt@informatik.fh-hamburg.de>
 * 
 * Copyright (c)2001 Behnke, Davids & Koose. Alle Rechte vorbehalten.
 * ===========================================================================
 */

import java.util.ResourceBundle;

import de.sdavids.util.ConstantType;

/**
 * Type-safe constant for different answer types.
 */
public class AnswerType extends ConstantType{

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Class Attributes ---------- */

	private static final transient int OPEN_QUALIFIED_VALUE;
	private static final transient String OPEN_QUALIFIED_DESCR;
	private static final transient int OPEN_QUANTIFIED_VALUE;
	private static final transient String OPEN_QUANTIFIED_DESCR;
	private static final transient int OPTION_BINARY_VALUE;
	private static final transient String OPTION_BINARY_DESCR;
	private static final transient int MATCH_SINGLE_VALUE;
	private static final transient String MATCH_SINGLE_DESCR;
	private static final transient int MATCH_MULTIPLE_VALUE;
	private static final transient String MATCH_MULTIPLE_DESCR;
	private static final transient int COMPLETION_OPEN_VALUE;
	private static final transient String COMPLETION_OPEN_DESCR;
	private static final transient int COMPLETION_LIMITED_VALUE;
	private static final transient String COMPLETION_LIMITED_DESCR;
	private static final transient int ORDER_VALUE;
	private static final transient String ORDER_DESCR;
	private static final transient int SCALE_LABLED_VALUE;
	private static final transient String SCALE_LABLED_DESCR;
	private static final transient int SCALE_CALCULATED_VALUE;
	private static final transient String SCALE_CALCULATED_DESCR;
	private static final transient int OPTION_VARIABLE_VALUE;
	private static final transient String OPTION_VARIABLE_DESCR;
	private static final transient int OPTION_LIMITED_VALUE;
	private static final transient String OPTION_LIMITED_DESCR;

	static {
		ResourceBundle rb = ResourceBundle.getBundle("client");

		OPEN_QUALIFIED_VALUE = Integer.parseInt(rb.getString("open_qualified_value"));
		OPEN_QUALIFIED_DESCR = rb.getString("open_qualified_descr");

		OPEN_QUANTIFIED_VALUE =
			Integer.parseInt(rb.getString("open_quantified_value"));
		OPEN_QUANTIFIED_DESCR = rb.getString("open_quantified_descr");

		OPTION_BINARY_VALUE = Integer.parseInt(rb.getString("option_binary_value"));
		OPTION_BINARY_DESCR = rb.getString("option_binary_descr");

		OPTION_VARIABLE_VALUE =
			Integer.parseInt(rb.getString("option_variable_value"));
		OPTION_VARIABLE_DESCR = rb.getString("option_variable_descr");

		OPTION_LIMITED_VALUE = Integer.parseInt(rb.getString("option_limited_value"));
		OPTION_LIMITED_DESCR = rb.getString("option_limited_descr");

		MATCH_SINGLE_VALUE = Integer.parseInt(rb.getString("match_single_value"));
		MATCH_SINGLE_DESCR = rb.getString("match_single_descr");

		MATCH_MULTIPLE_VALUE = Integer.parseInt(rb.getString("match_multiple_value"));
		MATCH_MULTIPLE_DESCR = rb.getString("match_multiple_descr");

		COMPLETION_OPEN_VALUE =
			Integer.parseInt(rb.getString("completion_open_value"));
		COMPLETION_OPEN_DESCR = rb.getString("completion_open_descr");

		COMPLETION_LIMITED_VALUE =
			Integer.parseInt(rb.getString("completion_limited_value"));
		COMPLETION_LIMITED_DESCR = rb.getString("completion_limited_descr");

		ORDER_VALUE = Integer.parseInt(rb.getString("order_value"));
		ORDER_DESCR = rb.getString("order_descr");

		SCALE_LABLED_VALUE = Integer.parseInt(rb.getString("scale_labled_value"));
		SCALE_LABLED_DESCR = rb.getString("scale_labled_descr");

		SCALE_CALCULATED_VALUE =
			Integer.parseInt(rb.getString("scale_calculated_value"));
		SCALE_CALCULATED_DESCR = rb.getString("scale_calculated_value");
	}

	/** Indicates an open qualified answer. */
	public static final AnswerType OPEN_QUALIFIED =
		new AnswerType(1, "open qualified");

	/** Indicates an open quantified answer. */
	public static final AnswerType OPEN_QUANTIFIED =
		new AnswerType(2, "open quantified");

	/** Indicates an binary option answer. */
	public static final AnswerType OPTION_BINARY =
		new AnswerType(3, "option binary");

	/** Indicates an match single answer. */
	public static final AnswerType MATCH_SINGLE =
		new AnswerType(4, "match single");

	/** Indicates an match multiple answer. */
	public static final AnswerType MATCH_MULTIPLE =
		new AnswerType(5, "match multiple");

	/** Indicates an open completion answer. */
	public static final AnswerType COMPLETION_OPEN =
		new AnswerType(6, "completion open");

	/** Indicates an limited completion answer. */
	public static final AnswerType COMPLETION_LIMITED =
		new AnswerType(7, "completion limited");

	/** Indicates an order answer. */
	public static final AnswerType ORDER = new AnswerType(8, "order");

	/** Indicates an variable options answer. */
	public static final AnswerType OPTION_VARIABLE =
		new AnswerType(9, "option variable");

	/** Indicates an limited options answer. */
	public static final AnswerType OPTION_LIMITED =
		new AnswerType(10, "option limited");

	/** Indicates an labled scale answer. */
	public static final AnswerType SCALE_LABLED =
		new AnswerType(11, "scale labled");

	/** Indicates an calculated scale answer. */
	public static final AnswerType SCALE_CALCULATED =
		new AnswerType(12, "scale calculated");

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	  * Construct and initialize a new <code>AnswerType</code>.
	  *
	  * @param <code>code</code> the code.
	  * @param <code>description</code> the descriptive string.
	  */
	protected AnswerType(int code, String description) {
		super(code, description);
	}

	//////////////////////////////////////////////////////////////////////////////
	// CLASS METHODS
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Factory methods ---------- */

	/**
	 * Return the <code>AnswerType</code> with the given code.
	 *
	 * @param	<code>code</code> the code.
	 * @throws	<code>IllegalArgumentException</code> if <code>code</code>
	 *        	is  not valid.
	 */
	public static AnswerType lookup(int code) throws IllegalArgumentException {
		switch (code) {
			case 1 :
				return OPEN_QUALIFIED;
			case 2 :
				return OPEN_QUANTIFIED;
			case 3 :
				return OPTION_BINARY;
			case 4 :
				return MATCH_SINGLE;
			case 5 :
				return MATCH_MULTIPLE;
			case 6 :
				return COMPLETION_OPEN;
			case 7 :
				return COMPLETION_LIMITED;
			case 8 :
				return ORDER;
			case 9 :
				return OPTION_VARIABLE;
			case 10 :
				return OPTION_LIMITED;
			case 11 :
				return SCALE_LABLED;
			case 12 :
				return SCALE_CALCULATED;
			default :
				throw new IllegalArgumentException("Unknown code: " + code);
		}
	}
}
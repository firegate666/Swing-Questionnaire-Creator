package de.sep.test.model;

import junit.framework.TestCase;

import de.sep.model.answertype.AnswerType;

public class AnswerTypeTest extends TestCase {
	private final int ILLEGAL_CODE = -1;

	public AnswerTypeTest(String name) {
		super(name);
	}

	public void testLookup() {
		try {
			AnswerType.lookup(ILLEGAL_CODE);
			fail("IllegalArgumentException not thrown");
		} catch (IllegalArgumentException e) {
			//OK
		}

		assertEquals(
			"COMPLETION_LIMITED",
			AnswerType.COMPLETION_LIMITED,
			AnswerType.lookup(AnswerType.COMPLETION_LIMITED.getValue()));
		assertEquals(
			"COMPLETION_OPEN",
			AnswerType.COMPLETION_OPEN,
			AnswerType.lookup(AnswerType.COMPLETION_OPEN.getValue()));
		assertEquals(
			"MATCH_MULTIPLE",
			AnswerType.MATCH_MULTIPLE,
			AnswerType.lookup(AnswerType.MATCH_MULTIPLE.getValue()));
		assertEquals(
			"MATCH_SINGLE",
			AnswerType.MATCH_SINGLE,
			AnswerType.lookup(AnswerType.MATCH_SINGLE.getValue()));
		assertEquals(
			"OPEN_QUALIFIED",
			AnswerType.OPEN_QUALIFIED,
			AnswerType.lookup(AnswerType.OPEN_QUALIFIED.getValue()));
		assertEquals(
			"OPEN_QUANTIFIED",
			AnswerType.OPEN_QUANTIFIED,
			AnswerType.lookup(AnswerType.OPEN_QUANTIFIED.getValue()));
		assertEquals(
			"OPTION_BINARY",
			AnswerType.OPTION_BINARY,
			AnswerType.lookup(AnswerType.OPTION_BINARY.getValue()));
		assertEquals(
			"OPTION_LIMITED",
			AnswerType.OPTION_LIMITED,
			AnswerType.lookup(AnswerType.OPTION_LIMITED.getValue()));
		assertEquals(
			"OPTION_VARIABLE",
			AnswerType.OPTION_VARIABLE,
			AnswerType.lookup(AnswerType.OPTION_VARIABLE.getValue()));
		assertEquals(
			"ORDER",
			AnswerType.ORDER,
			AnswerType.lookup(AnswerType.ORDER.getValue()));
		assertEquals(
			"SCALE_CALCULATED",
			AnswerType.SCALE_CALCULATED,
			AnswerType.lookup(AnswerType.SCALE_CALCULATED.getValue()));
		assertEquals(
			"SCALE_LABLED",
			AnswerType.SCALE_LABLED,
			AnswerType.lookup(AnswerType.SCALE_LABLED.getValue()));
	}

}
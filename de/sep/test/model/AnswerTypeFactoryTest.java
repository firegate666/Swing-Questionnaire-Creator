package de.sep.test.model;

import junit.framework.TestCase;

import de.sep.model.answertype.AnswerType;
import de.sep.model.answertype.AnswerTypeFactory;
import de.sep.model.answertype.IAnswerType;

public class AnswerTypeFactoryTest extends TestCase {

	public AnswerTypeFactoryTest(String name) {
		super(name);
	}

	public void testCreateCollection() {
		assertTrue("isEmpty", AnswerTypeFactory.createCollection().isEmpty());
	}

	public void testCreate() {
		AnswerType type = AnswerType.OPEN_QUALIFIED;
		IAnswerType a = AnswerTypeFactory.create(type);

		assertEquals("type", type, a.getType());
		assertTrue(!a.isNull());		
	}

	public void testGetNullQuestion() {
		IAnswerType t = AnswerTypeFactory.getNullAnswerType();

		assertTrue(t.isNull());
	}
}
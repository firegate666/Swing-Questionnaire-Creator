package de.sep.test.model;

import junit.framework.TestCase;

import de.sep.model.question.IQuestion;
import de.sep.model.question.QuestionFactory;

public class QuestionFactoryTest extends TestCase {

	public QuestionFactoryTest(String name) {
		super(name);		
	}

	public void testCreateCollection() {
		assertTrue("isEmpty", QuestionFactory.createCollection().isEmpty());
	}

	public void testCreate() {
		try {
			QuestionFactory.create(-1);

			fail("neg. number");
		} catch (IllegalArgumentException e) {
			//OK
		}
		
		QuestionFactory.create(0);
		
		int number = 1;
		IQuestion q = QuestionFactory.create(number);

		assertEquals("number", number, q.getNumber());
		assertEquals("text", "", q.getText());
		assertTrue("answers", q.hasNoAnswerTypes());
		assertTrue(!q.isNull());		
	}

	public void testCreateWithText() {
		try {
			QuestionFactory.create(-1);

			fail("neg. number");
		} catch (IllegalArgumentException e) {
			//OK
		}
		
		try {
			QuestionFactory.create(0, null);

			fail("neg. number");
		} catch (IllegalArgumentException e) {
			//OK
		}				
		
		QuestionFactory.create(0, "");
		
		int number = 1;
		String text = "test";
		
		IQuestion q = QuestionFactory.create(number, text);

		assertEquals("number", number, q.getNumber());
		assertEquals("text", text, q.getText());
		assertTrue("answers", q.hasNoAnswerTypes());
		assertTrue(!q.isNull());		
	}
	
	public void testGetNullQuestion() {
		IQuestion q = QuestionFactory.getNullQuestion();
		assertTrue(q.isNull());
	}
}
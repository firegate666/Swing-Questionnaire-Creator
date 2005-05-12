package de.sep.test.model;

import junit.framework.TestCase;

import de.sdavids.dataobjects.person.NullPerson;
import de.sep.model.questionaire.IQuestionaire;
import de.sep.model.questionaire.QuestionaireFactory;

public class QuestionaireFactoryTest extends TestCase {

	public QuestionaireFactoryTest(String name) {
		super(name);
	}

	public void testCreate() {
		try {
			QuestionaireFactory.create(null);

			fail("null");
		} catch (IllegalArgumentException e) {
			//OK
		}

		try {
			QuestionaireFactory.create("");

			fail("empty string");
		} catch (IllegalArgumentException e) {
			//OK
		}

		String title = "test";

		IQuestionaire q = QuestionaireFactory.create(title);

		assertEquals("title", title, q.getTitle());
		assertEquals("author", NullPerson.instance(), q.getAuthor());
		assertTrue("questions", q.isEmpty());
		assertTrue(!q.isNull());		
	}

	public void testGetNullQuestion() {
		IQuestionaire q = QuestionaireFactory.getNullQuestionaire();
		
		assertTrue(q.isNull());
	}
}
package de.sep.test.model;

import de.sdavids.dataobjects.person.IPerson;
import de.sdavids.dataobjects.person.NullPerson;
import de.sdavids.dataobjects.person.PersonFactory;
import de.sdavids.junit.JavaBeanTestCase;
import de.sep.model.question.IQuestion;
import de.sep.model.question.IQuestionIterator;
import de.sep.model.question.QuestionFactory;
import de.sep.model.questionaire.IQuestionaire;
import de.sep.model.questionaire.JUnitBridge;
import de.sep.model.questionaire.QuestionaireFactory;

public class QuestionaireImplTest extends JavaBeanTestCase {
	private IQuestionaire q;

	public QuestionaireImplTest(String name) {
		super(name);
		NullPerson.init();
	}

	protected void setUp() {
		q = QuestionaireFactory.create("test");

		q.addPropertyChangeListener(getPCL());
	}

	public void testConstructorNoArgs() {
		IQuestionaire q = JUnitBridge.newQuestionaireImpl();

		assertEquals("title", "", q.getTitle());	
		assertEquals("author", NullPerson.instance(), q.getAuthor());	
		assertTrue("questions", q.isEmpty());			
	}

	public void testConstructorWithTitle() {
		String title = "test";
		IQuestionaire q = JUnitBridge.newQuestionaireImpl(title);

		assertEquals("title", title, q.getTitle());	
		assertEquals("author", NullPerson.instance(), q.getAuthor());	
		assertTrue("questions", q.isEmpty());			
	}

	public void testSetAuthor() {
		setAllowedProperty("author");
		
		try {
			q.setTitle(null);
			fail("author null");	
		} catch (IllegalArgumentException e) {
			//OK
		}

		IPerson author = PersonFactory.create("Hans", "Test");
		
		q.setAuthor(author);
		
		assertEquals("author", author, q.getAuthor());

		q.setAuthor(NullPerson.instance()); //allowed
				
		assertPCLCount("author", 2);
	}
		
	public void testSetTitle() {
		setAllowedProperty("title");
		
		try {
			q.setTitle(null);
			fail("title null");	
		} catch (IllegalArgumentException e) {
			//OK
		}

		try {
			q.setTitle("");
			fail("title empty string");	
		} catch (IllegalArgumentException e) {
			//OK
		}
		
		String title = "text";
		
		q.setTitle(title)		;
		
		assertEquals("title", title, q.getTitle());
		
		assertPCLCount("title", 1);
	}

	public void testaddQuestion() {
		setIgnoredProperty("number");
		String[] allowed = { "questionsAdd", "questionsInsert" };
		
		setAllowedProperties(allowed);
		
		try {
			q.add(null);
			fail("question null");	
		} catch (IllegalArgumentException e) {
			//OK
		}

		int number1 = 1;
		int number2 = 2;
		
		IQuestion quest1 = QuestionFactory.create(number1);
		IQuestion quest2 = QuestionFactory.create(number2);
		IQuestion quest3 = QuestionFactory.create(number1);

		assertEquals("two", 0, q.numberOfQuestions());
		assertTrue("empty", q.isEmpty());
										
		q.add(quest1);
		
		assertTrue("not empty", q.isNotEmpty());
		
		q.add(quest2);
		
		assertEquals("two", 2, q.numberOfQuestions());
		
		q.add(quest3);
		
		assertEquals("two", 3, q.numberOfQuestions());
		
		IQuestion[] qs = new IQuestion[3];
		
		int i = 0;
		
		IQuestionIterator it = q.iterator();
		
		while (it.hasNext())
			qs[i++] = it.next();
			
		assertEquals("inserted", quest3, qs[0]);

		assertEquals("shifted", quest1, qs[1]);
		assertEquals("shifted number", (number1 + 1), qs[1].getNumber());	

		assertEquals("shifted2", quest2, qs[2]);
		assertEquals("shifted number", (number2 + 1), qs[2].getNumber());			
		
		assertPCLCount("pcl", 3);
	}
	
	public void testContains() {
		bePCLDisabled();
		
		IQuestion quest1 = QuestionFactory.create(1);
				
		assertTrue(!q.contains(quest1));

		q.add(quest1);

		assertTrue(q.contains(quest1));
	}
		
	public void testIsNull() {
		bePCLDisabled();

		assertTrue(!q.isNull());
	}
}
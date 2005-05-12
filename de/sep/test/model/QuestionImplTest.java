package de.sep.test.model;

import de.sdavids.junit.JavaBeanTestCase;
import de.sep.model.answertype.AnswerType;
import de.sep.model.answertype.AnswerTypeFactory;
import de.sep.model.answertype.IAnswerTypeCollection;
import de.sep.model.answertype.IAnswerTypeIterator;
import de.sep.model.question.IQuestion;
import de.sep.model.question.JUnitBridge;
import de.sep.model.question.QuestionFactory;

public class QuestionImplTest extends JavaBeanTestCase {
	private IQuestion q;
	
	public QuestionImplTest(String name) {
		super(name);
	}
	
	protected void setUp() {
		q = QuestionFactory.create(1);

		q.addPropertyChangeListener(getPCL());
	}

	public void testConstructorNoArgs() {
		IQuestion q = JUnitBridge.newQuestionImpl();
		
		assertEquals("number", Integer.MAX_VALUE, q.getNumber());	
		assertEquals("text", "", q.getText());	
		assertTrue("answers", q.hasNoAnswerTypes());		
	}

	public void testConstructorWithNumber() {
	 	int number = 5;
		IQuestion q = JUnitBridge.newQuestionImpl(number);
		
		assertEquals("number", number, q.getNumber());	
		assertEquals("text", "", q.getText());	
		assertTrue("answers", q.hasNoAnswerTypes());		
	}

	public void testConstructorWithNumberAndText() {
	 	int number = 5;
	 	String text = "test";
	 	
		IQuestion q = JUnitBridge.newQuestionImpl(number, text);
		
		assertEquals("number", number, q.getNumber());	
		assertEquals("text", text, q.getText());	
		assertTrue("answers", q.hasNoAnswerTypes());		
	}
			
	public void testSetText() {
		setAllowedProperty("text");
		
		try {
			q.setText(null);
			fail("null");
		} catch (IllegalArgumentException e) {
			//OK
		}

		q.setText("test2");
		assertEquals("set", "test2", q.getText());
		q.setText("test2"); //shouldn't fire				
		q.setText("");
		assertEquals("empty", "", q.getText());
		
		assertPCLCount("pcc", 2);
	}

	public void testSetNumber() {
		setAllowedProperty("number");
		
		try {
			q.setNumber(-1);
			fail("-1");
		} catch (IllegalArgumentException e) {
			//OK
		}

		q.setNumber(0);
		q.setNumber(4);
		assertEquals("set", 4, q.getNumber());
		q.setNumber(4); //shouldn't fire		
		
		assertPCLCount("pcc", 2);
	}

	public void testAddAnswer() {
		setAllowedProperty("answersAppend");
		
		try {
			q.add(null);
			fail("null");
		} catch (IllegalArgumentException e) {
			//OK
		}

		assertTrue("before", q.hasNoAnswerTypes());
		q.add(AnswerTypeFactory.create(AnswerType.COMPLETION_LIMITED));
		assertTrue("before", q.hasAnswerTypes());

		IAnswerTypeIterator it = q.iterator();

		int i = 0;
		
		while (it.hasNext()) {
			i++;
			it.next();
		}
		
		assertEquals("it", 1, i);
		
		assertPCLCount("pcc", 1);
	}
	
	public void testCompareTo() {
		bePCLDisabled();
		
		IQuestion q2 = QuestionFactory.create(2);
		
		assertEquals("smaller q -> q2", -1, q.compareTo(q2));
		assertEquals("greater q2 -> q", 1, q2.compareTo(q));
		
		q2.setNumber(1);
		q.setText("a");
		q2.setText("a");
		
		assertTrue("equal q -> q2", (0 == q.compareTo(q2)));
		assertTrue("equal q2 -> q", (0 == q2.compareTo(q)));

		q2.setText("diff");
		
		assertTrue("equal q -> q2", (0 == q.compareTo(q2)));
		assertTrue("equal q2 -> q", (0 == q2.compareTo(q)));
	}
	
	public void testIsNull() {
		bePCLDisabled();
		
		assertTrue(!q.isNull());	
	}
}
package de.sep.test.model;

import java.util.NoSuchElementException;

import de.sdavids.junit.JavaBeanTestCase;
import de.sep.model.question.IQuestion;
import de.sep.model.question.IQuestionCollection;
import de.sep.model.question.JUnitBridge;
import de.sep.model.question.QuestionFactory;

public class QuestionCollectionImplTest extends JavaBeanTestCase {
	private IQuestionCollection qc;
	private QuestionFactory f;
	private IQuestion q;
	private IQuestion q2;

	public QuestionCollectionImplTest(String name) {
		super(name);
	}

	protected void setUp() {
		q = QuestionFactory.create(10);
		q2 = QuestionFactory.create(20);

		q.setText("q");
		q2.setText("q2");

		qc = JUnitBridge.newQuestionCollectionImpl();

		qc.addPropertyChangeListener(getPCL());
	}

	public void testConstructorNoArgs() {
		IQuestionCollection q = JUnitBridge.newQuestionCollectionImpl();
		
		assertTrue("isEmpty", q.isEmpty());		
	}
	
	public void testAdd() {
		setAllowedProperty("questionsAdd");

		try {
			qc.add(null);
			fail("add null");
		} catch (IllegalArgumentException e) {
			//OK		
		}

		qc.add(q);

		assertEquals("size", 1, qc.size());

		qc.add(q2);

		assertEquals(2, qc.size());

		assertPCLCount("pcc", 2);
	}

	public void testAddInBetween() {
		setAllowedProperty("questionsAdd");
		
		qc.add(q);
		qc.add(q2);

		IQuestion q3 = f.create(15);
		q3.setText("q3");

		qc.add(q3);
				
		assertEquals("q2", q, qc.toArray()[0]);
		assertEquals("q3", q3, qc.toArray()[1]);
		assertEquals("q", q2, qc.toArray()[2]);

		assertPCLCount("pcc", 3);		
	}
	
	public void testAddWithSameNumberAsExisting() {
		String[] ingore = { "questionsAdd", "number", "questionsUpdated" };

		setIgnoredProperties(ingore);
		setAllowedProperty("questionsInsert");
		
		qc.add(q);
		qc.add(q2);

		int number1 = q.getNumber();
		int number2 = q2.getNumber();
		int number3 = number1 + 1;

		IQuestion q3 = f.create(q.getNumber() + 1);
		q3.setText("q3");
		
		qc.add(q3);

		IQuestion q4 = f.create(q.getNumber());
		q4.setText("q4");

		qc.add(q4);
					
		assertEquals("size", 4, qc.size());
		assertEquals("q", (number1 + 1), q.getNumber());
		assertEquals("q2", number2, q2.getNumber());
		assertEquals("q3", (number3 + 1), q3.getNumber());		
		assertEquals("q4", number1, q4.getNumber());

		assertPCLCount("pcc", 1);
	}

	public void testIsEmpty() {
		setIgnoredProperty("questionsAdd");

		assertTrue("empty", qc.isEmpty());

		qc.add(q);

		assertTrue("not empty", !qc.isEmpty());

		assertPCLCount("pcc", 0);
	}

	public void testIsNotEmpty() {
		setIgnoredProperty("questionsAdd");

		assertTrue("empty", !qc.isNotEmpty());

		qc.add(q);

		assertTrue("not empty", qc.isNotEmpty());

		assertPCLCount("pcc", 0);
	}

	public void testIterator() {
		setIgnoredProperty("questionsAdd");

		try {
			qc.iterator().next();
			fail("no exception for accessing imaginary elements");
		} catch (NoSuchElementException e) {}

		qc.add(q);

		assertEquals("one", q, qc.iterator().next());

		assertPCLCount("pcc", 0);
	}

	public void testSize() {
		String[] ingore = { "questionsAdd", "questionsRemove" };

		setIgnoredProperties(ingore);

		assertEquals("empty", 0, qc.size());

		qc.add(q);

		assertEquals("one", 1, qc.size());

		qc.remove(q);

		assertEquals("removed", 0, qc.size());

		assertPCLCount("pcc", 0);
	}

	public void testToArray() {
		String[] ingore = { "questionsAdd", "number", "questionsRemove" };

		setIgnoredProperties(ingore);

		assertEquals("empty", 0, qc.toArray().length);

		qc.add(q);

		assertEquals("one", 1, qc.toArray().length);
		assertEquals("there", q, qc.toArray()[0]);

		qc.remove(q);

		assertEquals("removed", 0, qc.size());

		assertPCLCount("pcc", 0);
	}

	public void testAddAll() {
		String[] ingore = { "questionsAdd", "questionsRemove" };

		setIgnoredProperties(ingore);
		setAllowedProperty("questionsAddAll");

		IQuestion[] qs = null;
		IQuestionCollection qc2 = null;

		try {
			qc.addAll(qs);
			fail("addAll null array");
		} catch (IllegalArgumentException e) {
			//OK
		}

		try {
			qc.addAll(qc2);
			fail("addAll null collection");
		} catch (IllegalArgumentException e) {
			//OK
		}

		qs = new IQuestion[0];
		qc.addAll(qs); //shouldn't fire

		qc2 = QuestionFactory.createCollection();
		qc.addAll(qc2); //shouldn't fire

		assertEquals("addAll empty", 0, qc.size());

		qc2.add(q2);
		qc2.add(QuestionFactory.create(3));

		qc.addAll(qc2);

		assertEquals("addAll two", 2, qc.size());

		qc2.remove(q2);

		assertEquals("remove one from the original", 2, qc.size());

		qc.add(q);

		assertEquals("add one", 3, qc.size());
		assertEquals("no change in original", 1, qc2.size());

		assertPCLCount("pcc", 1);
	}

	public void testRemove() {
		setIgnoredProperty("questionsAdd");
		setAllowedProperty("questionsRemove");

		try {
			qc.remove(null);
			fail("update null");
		} catch (IllegalArgumentException e) {
			//OK		
		}

		try {
			qc.remove(q);
			fail("not in there");
		} catch (NoSuchElementException e) {
			//OK		
		}

		qc.add(q);

		assertEquals("add one", 1, qc.size());

		qc.remove(q);

		assertEquals("remove", 0, qc.size());

		assertPCLCount("pcc", 1);
	}

	public void testContainsObject() {
		setIgnoredProperty("questionsAdd");

		qc.add(q);

		assertTrue(qc.contains(q));

		IQuestion nq = null;

		assertTrue("null", !qc.contains(nq));
		assertTrue("not there", !qc.contains(q2));

		assertPCLCount("pcc", 0);
	}

	public void testContainsNumber() {
		setIgnoredProperty("questionsAdd");

		qc.add(q);

		assertTrue(qc.contains(q.getNumber()));
		assertTrue("not there", !qc.contains(q2.getNumber()));

		assertPCLCount("pcc", 0);
	}

	public void testClear() {
		setAllowedProperty("questionsClear");
		setIgnoredProperty("questionsAdd");

		qc.add(q);
		qc.add(q2);
		assertEquals("two", 2, qc.size());
		qc.clear();
		assertTrue("isEmpty", qc.isEmpty());

		assertPCLCount("pcc", 1);
	}

	public void testUpdateNumber() {
		String[] ingore = { "questionsAdd", "number" };

		setIgnoredProperties(ingore);
		setAllowedProperty("questionsUpdated");

		IQuestion q3 = QuestionFactory.create(30);

		q3.setText("q3");

		qc.add(q);
		qc.add(q2);
		qc.add(q3);

		q3.setNumber(q3.getNumber() + 30);

		assertEquals("inc largest", q3, qc.toArray()[2]);

		q3.setNumber(q3.getNumber() - 5);

		assertEquals("dec largest", q3, qc.toArray()[2]);

		q2.setNumber(q2.getNumber() + 20);

		assertEquals("inc middle", q2, qc.toArray()[1]);
		
		q2.setNumber(q2.getNumber() - 5);

		assertEquals("dec middle", q2, qc.toArray()[1]);

		q.setNumber(q.getNumber() + 10);

		assertEquals("inc middle", q, qc.toArray()[0]);

		q.setNumber(q.getNumber() - 5);

		assertEquals("dec middle", q, qc.toArray()[0]);

		q.setNumber(q.getNumber() + 25);
		
		assertEquals("smallest to middle - q2", q2, qc.toArray()[0]);
		assertEquals("smallest to middle - q", q, qc.toArray()[1]);
		assertEquals("smallest to middle - q3", q3, qc.toArray()[2]);

		q3.setNumber(q3.getNumber() - 25);
		
		assertEquals("largest to smallest - q3", q3, qc.toArray()[0]);
		assertEquals("largest to smallest - q2", q2, qc.toArray()[1]);
		assertEquals("largest to smallest - q", q, qc.toArray()[2]);

		assertPCLCount("pcc", 8);
	}

	public void testUpdateNumberToExisting() {
		String[] ingore = { "questionsAdd", "number" };

		setIgnoredProperties(ingore);
		setAllowedProperty("questionsUpdated");

		IQuestion q3 = QuestionFactory.create(30);

		q3.setText("q3");

		qc.add(q);
		qc.add(q2);
		qc.add(q3);
		
		qc.toArray()[1].setNumber(qc.toArray()[0].getNumber());

		assertEquals(
			"assign middle to smallest none in front  - q2",
			q2,
			qc.toArray()[0]);		
		assertEquals(
			"assign middle to smallest none in front  - q",
			q,
			qc.toArray()[1]);
		assertEquals(
			"assign middle to smallest none in front - q3",
			q3,
			qc.toArray()[2]);
	
		qc.toArray()[2].setNumber(qc.toArray()[1].getNumber());

		assertEquals(
			"assign largest to middle one in front  - q2",
			q2,
			qc.toArray()[0]);			
		assertEquals(
			"assign largest to middle one in front - q3",
			q3,
			qc.toArray()[1]);
		assertEquals(
			"assign largest to middle one in front  - q",
			q,
			qc.toArray()[2]);
			
		assertPCLCount("pcc", 2);					
	}
}
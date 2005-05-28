package de.sep.test.model;

import java.util.NoSuchElementException;

import junit.framework.TestCase;
import de.sep.model.answertype.AnswerType;
import de.sep.model.answertype.AnswerTypeFactory;
import de.sep.model.answertype.IAnswerType;
import de.sep.model.answertype.IAnswerTypeCollection;

public class AnswerTypeCollectionImplTest extends TestCase {
	private IAnswerTypeCollection ac;
	private AnswerTypeFactory f;
	private IAnswerType a;
	private IAnswerType a2;
	private int propertyChangeCount;

	public AnswerTypeCollectionImplTest(String name) {
		super(name);

		a = AnswerTypeFactory.create(AnswerType.COMPLETION_LIMITED);
		a2 = AnswerTypeFactory.create(AnswerType.COMPLETION_OPEN);
	}

	protected void setUp() {
		ac = AnswerTypeFactory.createCollection();

		propertyChangeCount = 0;
	}

	public void testAdd() {
		try {
			ac.add(null);
			fail("add null");
		} catch (IllegalArgumentException e) {
			//OK		
		}

		ac.add(a);
		assertEquals("size", 1, ac.size());
		assertEquals("get", a, ac.get(0));
		ac.add(a2);
		assertEquals(2, ac.size());
		assertEquals("get", a, ac.get(0));
		assertEquals("get", a2, ac.get(1));

		//assertEquals("pcc", 2, propertyChangeCount);
	}

	public void testAddAtIndex() {
		try {
			ac.add(null, 1);
			fail("add null");
		} catch (IllegalArgumentException e) {
			//OK		
		}
		try {
			ac.add(a, -1);
			fail("add negative");
		} catch (IndexOutOfBoundsException e) {
			//OK		
		}

		try {
			ac.add(a, 1);
			fail("add illegal array bound");
		} catch (IndexOutOfBoundsException e) {
			//OK		
		}

		ac.add(a, 0);
		assertEquals("size", 1, ac.size());
		assertEquals("get", a, ac.get(0));
		ac.add(a2, 0);
		assertEquals("size", 2, ac.size());
		assertEquals("get", a2, ac.get(0));
		assertEquals("get", a, ac.get(1));

		//assertEquals("pcc", 2, propertyChangeCount);
	}

	public void testIndexOf() {
		ac.add(a);
		assertEquals("there", 0, ac.indexOf(a));
		assertEquals("not there", -1, ac.indexOf(a2));
		ac.add(a2, 0);
		ac.add(a);
		assertEquals("added two", 1, ac.indexOf(a));

		//assertEquals("pcc", 3, propertyChangeCount);
	}

	public void testLastIndexOf() {
		ac.add(a);
		assertEquals("there", 0, ac.lastIndexOf(a));
		assertEquals("not there", -1, ac.indexOf(a2));
		ac.add(a2, 0);
		ac.add(a);
		assertEquals("added two", 2, ac.lastIndexOf(a));

		//assertEquals("pcc", 3, propertyChangeCount);
	}

	public void testGetAnswers() {
		assertEquals("empty", 0, ac.getAnswers().length);
		ac.add(a);
		assertEquals("one", 1, ac.getAnswers().length);
		assertEquals("there", a, ac.getAnswers()[0]);
		ac.remove(0);
		assertEquals("removed", 0, ac.getAnswers().length);

		//assertEquals("pcc", 2, propertyChangeCount);
	}

	public void testIsEmpty() {
		assertTrue("empty", ac.isEmpty());
		ac.add(a);
		assertTrue("not empty", !ac.isEmpty());

		//assertEquals("pcc", 1, propertyChangeCount);
	}

	public void testIsNotEmpty() {
		assertTrue("empty", !ac.isNotEmpty());
		ac.add(a);
		assertTrue("not empty", ac.isNotEmpty());

		//assertEquals("pcc", 1, propertyChangeCount);
	}

	public void testIterator() {
		try {
			ac.iterator().next();
			fail("no exception for accessing imaginary elements");
		} catch (NoSuchElementException e) {}

		ac.add(a);
		assertEquals("one", a, ac.iterator().next());

		//assertEquals("pcc", 1, propertyChangeCount);
	}

	public void testSize() {
		assertEquals("empty", 0, ac.size());
		ac.add(a);
		assertEquals("one", 1, ac.size());
		ac.remove(0);
		assertEquals("removed", 0, ac.size());

		//assertEquals("pcc", 2, propertyChangeCount);
	}

	public void testToArray() {
		assertEquals("empty", 0, ac.toArray().length);
		ac.add(a);
		assertEquals("one", 1, ac.toArray().length);
		assertEquals("there", a, ac.toArray()[0]);
		ac.remove(0);
		assertEquals("removed", 0, ac.size());

		//assertEquals("pcc", 2, propertyChangeCount);
	}

	public void testAddAll() {
		IAnswerType[] as = null;
		IAnswerTypeCollection ac2 = null;

		ac.addAll(as);
		assertEquals("IQuestion[]", 0, ac.size());
		ac.addAll(ac2);
		assertEquals("IQuestionCollection", 0, ac.size());

		ac2 = AnswerTypeFactory.createCollection();
		ac.addAll(ac2);
		assertEquals("addAll empty", 0, ac.size());
		ac2.add(a2);
		ac.addAll(ac2);
		assertEquals("addAll one", 1, ac.size());
		ac2.remove(0);
		assertEquals("remove original", 1, ac.size());
		ac2.add(a2);
		ac.add(a);
		assertEquals("add one", 2, ac.size());
		ac.remove(1);
		assertEquals("remove new", 1, ac2.size());

		//assertEquals("pcc", 3, propertyChangeCount);
	}

	public void testRemove() {
		try {
			ac.remove(0);
			fail("try to remove nonexistent");
		} catch (IndexOutOfBoundsException e) {
			//OK
		}

		ac.add(a);
		assertEquals("add one", 1, ac.size());
		assertEquals("equals", a, ac.remove(0));
		assertEquals("remove", 0, ac.size());

		//assertEquals("pcc", 2, propertyChangeCount);
	}

	public void testGet() {
		try {
			ac.get(0);
			fail("try to get nonexistent");
		} catch (IndexOutOfBoundsException e) {
			//OK
		}

		ac.add(a);
		assertEquals("add one", 1, ac.size());
		assertEquals("equals", a, ac.get(0));
		assertEquals("get", 1, ac.size());

		//assertEquals("pcc", 1, propertyChangeCount);
	}

	public void testPut() {
		try {
			ac.put(null, 0);
			fail("set illegal array index");
		} catch (IllegalArgumentException e) {
			//OK
		}

		try {
			ac.put(a, 1);
			fail("set illegal array index");
		} catch (IndexOutOfBoundsException e) {
			//OK
		}

		ac.add(a);
		assertEquals("add one", 1, ac.size());
		ac.put(a2, 0);
		assertEquals("get", a2, ac.get(0));

		//assertEquals("pcc", 2, propertyChangeCount);
	}

	public void testContains() {
		ac.add(a);
		assertTrue(ac.contains(a));
		IAnswerType nq = null;
		assertTrue("null", !ac.contains(nq));
		assertTrue("not there", !ac.contains(a2));

		//assertEquals("pcc", 1, propertyChangeCount);
	}

	public void testClear() {
		ac.add(a);
		ac.add(a2);
		assertEquals("two", 2, ac.size());
		ac.clear();
		assertTrue("isEmpty", ac.isEmpty());

		//assertEquals("pcc", 3, propertyChangeCount);
	}
}
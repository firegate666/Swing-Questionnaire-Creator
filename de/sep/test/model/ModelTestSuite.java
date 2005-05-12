package de.sep.test.model;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ModelTestSuite extends TestSuite {

	public static Test suite() {
		TestSuite result = new TestSuite();

		result.addTest(new TestSuite(QuestionaireImplTest.class));
		result.addTest(new TestSuite(QuestionaireFactoryTest.class));
				
		result.addTest(new TestSuite(QuestionImplTest.class));
		result.addTest(new TestSuite(QuestionCollectionImplTest.class));
		result.addTest(new TestSuite(QuestionFactoryTest.class));

		result.addTest(new TestSuite(AnswerTypeTest.class));
		result.addTest(new TestSuite(AnswerTypeCollectionImplTest.class));
		result.addTest(new TestSuite(AnswerTypeFactoryTest.class));

		return result;
	}
}
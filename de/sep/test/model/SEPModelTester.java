package de.sep.test.model;

import junit.swingui.TestRunner;

public class SEPModelTester {

	public static void main(String[] args) {
		String[] arg = { "de.sep.test.model.ModelTestSuite" };

		new TestRunner().start(arg);
	}
}
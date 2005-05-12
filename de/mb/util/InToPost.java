package de.mb.util;

import java.util.Collection;
import java.util.Stack;

// Was geht schon?
// + - * / =
// Klammern

// Was noch fehlt:
// Erstes Minus
// Statements, die keine Zuweisung oder Arithmetischer Ausdruck sind

public final class InToPost {

	private Stack stack = null;
	private String infix;
	private String postfix;
	private Collection _tokens;
	private int infixctr;
	private int infixsize;

	public InToPost() {
		this("");
	}

	public InToPost(String infix) {
		stack = new Stack();
		this.infix = infix;
		this.infixsize = this.infix.length();
	}

	private boolean isP_On(char c) {
		return (c == '(');
	}

	private boolean isP_Off(char c) {
		return (c == ')');
	}

	private boolean isOperator(char c) {
		return ((isMulOp(c)) || (isAddOp(c)) || (isAssignOp(c)));
	}

	private boolean isMulOp(char c) {
		if (c == '*')
			return true;
		if (c == '/')
			return true;
		return false;
	}

	private boolean isAssignOp(char c) {
		if (c == '=')
			return true;
		return false;
	}

	private boolean isAddOp(char c) {
		if (c == '+')
			return true;
		if (c == '-')
			return true;
		return false;
	}

	private int prio(char c) {
		switch (c) {
			case '*' :
				return 50;
			case '/' :
				return 50;
			case '+' :
				return 40;
			case '-' :
				return 40;
			case '(' :
				return 11;
			case ')' :
				return 10;
			case '=' :
				return 0;

			default :
				return 0;
		}
	}

	public String convert(String infix) {
		this.infix = infix;
		this.postfix = "";
		return this.convert();
	}

	public String convert() {
		this.postfix = "";
		if (infix.equals("")) {
			this.postfix = this.infix;
			return postfix;
		}

		infixctr = 0;
		char nextChar;
		while (infixctr < infixsize) {
			nextChar = infix.charAt(infixctr);
			if (this.isP_On(nextChar)) {
				stack.addElement(new Character(nextChar));
			} else if (this.isP_Off(nextChar)) {
				while (!this
					.isP_On(((Character) (stack.peek())).charValue())) {
					infixctr++;
					nextChar = infix.charAt(infixctr);
					postfix += ((Character) stack.pop()).charValue();
				}
				infixctr--;
				stack.pop();
			} else if (!isOperator(nextChar)) {
				postfix += nextChar;
			} else {
				if (stack.isEmpty()) {
					stack.addElement(new Character(nextChar));
				} else {
					while ((!stack.isEmpty())
						&& (prio(((Character) stack.peek()).charValue())
							> prio(nextChar))) {
						postfix += ((Character) stack.pop()).charValue();
					}
					stack.addElement(new Character(nextChar));
				}
			}
			infixctr++;
		}
		while (!stack.isEmpty()) {
			postfix += ((Character) stack.pop()).charValue();
		}

		return postfix;

	}

	public static void main(String[] args) {
		String a = "test=(a+b)*c";
		InToPost test = new InToPost(a);
		System.out.println("Infix: " + a);
		System.out.println("Postfix: " + test.convert());
	}
}

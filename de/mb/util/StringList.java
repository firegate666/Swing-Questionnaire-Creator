package de.mb.util;

import java.util.Vector;

public class StringList {

	private Vector fCollection;

	public boolean add(String string) {
		return fCollection.add(string);
	}

	public boolean add(Object object) {
		return add(object.toString());
	}

	public boolean addAll(StringList items) {
		boolean flag = true;
		for (int i = 0; i < items.size(); i++) {
			flag = add(items.get(i)) && flag;
		}
		return flag;
	}

	public boolean addAll(Vector items) {
		boolean flag = true;
		for (int i = 0; i < items.size(); i++) {
			flag = add(items.get(i).toString()) && flag;
		}
		return flag;
	}

	public void removeAll() {

		fCollection.removeAllElements();

	}
	public void add(int pos, String s) {
		fCollection.add(pos, s);
	}

	public int size() {
		return fCollection.size();
	}

	public String get(int pos) {
		return (String) fCollection.get(pos);
	}

	public String lastElement() {
		return (String) fCollection.lastElement();
	}

	public String firstElement() {
		return (String) fCollection.firstElement();
	}

	public StringList() {
		fCollection = new Vector();
	}

	public StringList(int initialCapacity) {
		fCollection = new Vector(initialCapacity);
	}

	public StringList(int initialCapacity, int capacityIncrement) {
		fCollection = new Vector(initialCapacity, capacityIncrement);
	}
}
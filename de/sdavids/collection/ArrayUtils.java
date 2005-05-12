package de.sdavids.collection;

import java.util.Collection;
import java.util.Iterator;

public class ArrayUtils {

	public static boolean ascending(Comparable[] array) {
		if (null == array)
			throw new IllegalArgumentException("array null");

		int len = array.length;

		if (0 == len)
			throw new IllegalArgumentException("empty array");

		if (1 == len)
			return true;

		for (int i = (len - 2); i >= 0; i--) {
			if (array[i].compareTo(array[i + 1]) > 0)
				return false;
		}

		return true;
	}

	public static boolean descending(Comparable[] array) {
		if (null == array)
			throw new IllegalArgumentException("array null");

		int len = array.length;

		if (0 == len)
			throw new IllegalArgumentException("empty array");

		if (1 == len)
			return true;

		for (int i = (len - 2); i >= 0; i--) {
			if (array[i].compareTo(array[i + 1]) < 0)
				return false;
		}

		return true;
	}

	public static boolean sameOrder(
		Comparable[] anArray,
		Comparable[] anotherArray) {
		int len = anArray.length;

		if (len != anotherArray.length)
			return false;

		for (int i = (len - 1); i >= 0; i--) {
			if (anotherArray[i].compareTo(anotherArray[i]) != 0)
				return false;
		}

		return true;
	}

	//no type-checking!!!
	public static int[] toIntArray(Collection c) {
		if (0 == c.size())
			return new int[0];

		Integer[] ints = (Integer[]) c.toArray(new Integer[c.size()]);
		int[] result = new int[ints.length];

		for (int i = (ints.length - 1); i >= 0; i--)
			result[i] = ints[i].intValue();

		return result;
	}

	static public String toString(Object[] array) {
		return toString(array, false);
	}

	static public String toString(Object[] array, boolean newLine) {
		char seperator = (newLine) ? '\n' : ' ';

		int n = array.length;

		if (0 > n)
			return "";

		StringBuffer result = new StringBuffer(n * 2 - 1);

		result.append(array[0]);

		for (int i = 1; i < n; i++)
			result.append(seperator).append(array[i]);

		return result.toString();
	}

	//removes all null's; preserves the order but not the array indices
	public static Object[] trim(Object[] a) {
		int len = a.length;

		if ((1 == len) && (null == a[0]))
			return new Object[0];

		int i;
		int n = -1; //the position where we found a "null"; also the
		//length of trimmed array

		for (i = 0; i < len; i++) {
			if (null == a[i]) {
				n = i;
				break;
			}
		}

		if (-1 == n)
			return a;

		if (n != (len - 1)) {
			for (i = (n + 1); i < len; i++) {
				if (null == a[i])
					continue;

				a[n] = a[i];
				n++;
			}
		}

		Object[] trimmed = new Object[n];

		System.arraycopy(a, 0, trimmed, 0, n);

		return trimmed;
	}
}
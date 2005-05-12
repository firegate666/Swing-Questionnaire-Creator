package de.sdavids.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StringUtils {

	protected static String NEW_LINE;

	static {
		NEW_LINE = System.getProperty("line.separator"); //non-I18N-NLS
	}

	public static String toString(Object[] objects) {
		return ArrayUtils.toString(objects);
	}
	
	public static StringBuffer replace(
		StringBuffer sb,
		String lookedFor,
		String replacement) {

		if (null == sb)
			return null;
		if ((null == lookedFor) || "".equals(lookedFor))
			return sb;
		if (lookedFor.equals(replacement))
			return sb;
			
		String s = sb.toString();

		int origIndex = s.indexOf(lookedFor);

		if (-1 == origIndex)
			return sb;

		String replace = (null == replacement) ? "" : replacement;

		boolean empty = ("".equals(replace));

		int len = lookedFor.length();

		int index;

		if (empty) {
			int deleted = -len;

			do {
				deleted += len;		

				index = origIndex - deleted;

				sb.delete(index, (index + len));

				origIndex = s.indexOf(lookedFor, (origIndex + len));			
			} while (-1 != origIndex);
		} else {
			int count = -1;
			int len1 = len - 1;

			do {
				count++;
				
				index = origIndex + (count * len1);
				
				sb.delete(index, (index + len)).insert(index, replace);
				
				origIndex = s.indexOf(lookedFor, (origIndex + 1));
			} while (-1 != origIndex);
		}

		return sb;
	}

	public static String replace(
		String s,
		String lookedFor,
		String replacement) {

		return replace(new StringBuffer(s), lookedFor, replacement).toString();
	}

	public static String join(String[] strings, String token) {
		if (null == strings)
			return null;

		int largest = strings.length;

		if ((0 == largest) || (null == strings[0]))
			return null;

		StringBuffer result = new StringBuffer(largest);

		largest--;

		for (int i = 0; i < largest; i++) {
			result.append(strings[i]).append(token);
		}

		result.append(strings[largest]);

		return (result.toString());
	}

	public static String[] split(String s, String token) {
		if ((null == s) || (0 == s.length()))
			return new String[0];

		ArrayList result = new ArrayList();

		int length = s.length();
		int tokenLength = token.length();
		int previousLoc = 0;
		int loc = s.indexOf(token);

		do {
			result.add(s.substring(previousLoc, loc));

			previousLoc = loc + tokenLength;

			loc = s.indexOf(token, previousLoc);
		} while ((loc != -1) && (previousLoc < length));

		result.add(s.substring(previousLoc));

		return ((String[]) result.toArray(new String[result.size()]));
	}

	//removes null's
	public static String[] trim(String[] strings) {
		if (null == strings)
			return new String[0];

		int len = strings.length;

		if ((0 == len) || (null == strings[0]))
			return new String[0];

		int i;
		int n = -1; //the position where we found a "null"; also the
		//length of trimmed array

		for (i = 0; i < len; i++) {
			if (null == strings[i]) {
				n = i;
				break;
			}
		}

		if (-1 == n)
			return strings;

		if (n != (len - 1)) {
			for (i = (n + 1); i < len; i++) {
				if (null == strings[i])
					continue;

				strings[n] = strings[i];
				n++;
			}
		}

		String[] result = new String[n];

		System.arraycopy(strings, 0, result, 0, n);

		return result;
	}

	public static String onlyLettersOrDigits(String s) {
		if (null == s)
			return null;

		int len = s.length();

		if (0 == len)
			return null;

		StringBuffer result = new StringBuffer(len);

		int i = 0;
		char c;

		while (i < len) {
			c = s.charAt(i);

			if (Character.isLetterOrDigit(c))
				result.append(c);

			i++;
		}

		return ((0 == result.length()) ? null : result.toString());
	}

	public static String onlyDigits(String s) {
		if (null == s)
			return null;

		int len = s.length();

		if (0 == len)
			return null;

		StringBuffer result = new StringBuffer(len);

		int i = 0;
		char c;

		while (i < len) {
			c = s.charAt(i);

			if (Character.isDigit(c))
				result.append(c);

			i++;
		}

		return ((0 == result.length()) ? null : result.toString());
	}

	public static String onlyLetters(String s) {
		if (null == s)
			return null;

		int len = s.length();

		if (0 == len)
			return null;

		StringBuffer result = new StringBuffer(len);

		int i = 0;
		char c;

		while (i < len) {
			c = s.charAt(i);

			if (Character.isLetter(c))
				result.append(c);

			i++;
		}

		return ((0 == result.length()) ? null : result.toString());
	}

	/* ----------- Accessors -------------------- */

	public static boolean hasOnlyLettersAndDigits(String s) {
		if (null == s)
			return false;

		int len = s.length();

		if (0 == len)
			return false;

		for (int i = (len - 1); i >= 0; i--) {
			if (!Character.isLetterOrDigit(s.charAt(i)))
				return false;
		}

		return true;
	}

	public static boolean hasOnlyLettersDigitsAndWS(String s) {
		if (null == s)
			return false;

		int len = s.length();

		if (0 == len)
			return false;

		char c;

		for (int i = (len - 1); i >= 0; i--) {
			c = s.charAt(i);

			if (!(Character.isLetterOrDigit(c) || Character.isWhitespace(c)))
				return false;
		}

		return true;
	}

	public static boolean hasOnlyDigits(String s) {
		if (null == s)
			return false;

		int len = s.length();

		if (0 == len)
			return false;

		for (int i = (len - 1); i >= 0; i--) {
			if (!Character.isDigit(s.charAt(i)))
				return false;
		}

		return true;
	}

	public static boolean hasOnlyDigitsAndWS(String s) {
		if (null == s)
			return false;

		int len = s.length();

		if (0 == len)
			return false;

		char c;

		for (int i = (len - 1); i >= 0; i--) {
			c = s.charAt(i);

			if (!(Character.isDigit(c) || Character.isWhitespace(c)))
				return false;
		}

		return true;
	}

	public static boolean hasOnlyLetters(String s) {
		if (null == s)
			return false;

		int len = s.length();

		if (0 == len)
			return false;

		for (int i = (len - 1); i >= 0; i--) {
			if (!Character.isLetter(s.charAt(i)))
				return false;
		}

		return true;
	}

	public static boolean hasOnlyLettersAndWS(String s) {
		if (null == s)
			return false;

		int len = s.length();

		if (0 == len)
			return false;

		char c;

		for (int i = (len - 1); i >= 0; i--) {
			c = s.charAt(i);

			if (!(Character.isLetter(c) || Character.isWhitespace(c)))
				return false;
		}

		return true;
	}

	//map key -> character; value = frequency
	public static Map charFrequencies(String s) {
		final Integer ONE_VALUE = new Integer(1);

		Map result = new HashMap();

		Character c;
		int oldValue;

		for (int i = (s.length() - 1); i >= 0; i--) {
			c = new Character(s.charAt(i));

			if (result.containsKey(c)) {
				oldValue = ((Integer) result.get(c)).intValue();
				result.put(c, new Integer(++oldValue));
			} else {
				result.put(c, ONE_VALUE);
			}
		}

		return result;
	}

	public static String toNewLines(String[] strs) {
		if (null == strs)
			return null;

		int len = strs.length;

		if (0 == len)
			return "";

		StringBuffer result = new StringBuffer(len);

		result.append(strs[0]);

		for (int i = 1; i < len; i++) {
			result.append(NEW_LINE).append(strs[i]);
		}

		return result.toString();
	}

	public static char[] fill(char[] strs, char fill) {
		for (int i = (strs.length - 1); i >= 0; i--)
			strs[i] = fill;

		return strs;
	}

	public static String[] rightAligned(int[] numbers) {
		return rightAligned(numbers, ' ');
	}

	public static String[] rightAligned(int[] numbers, char pad) {
		if (null == numbers)
			return null;

		int size = numbers.length;

		String[] result = new String[size];

		if (0 == size)
			return result;

		size--;

		int i; //loop-counter

		//determine max number of digits (incl. -)
		int max = numbers[size];
		int min = numbers[size];

		int current;

		for (i = (size - 1); i >= 0; i--) {
			current = numbers[i];

			if (current > max) {
				max = current;
			} else if (current < min) {
				min = current;
			}
		}

		max = String.valueOf(max).length();
		min = String.valueOf(min).length();

		final int len = (min > max) ? min : max;
		final char[] empty = fill(new char[len], pad);

		String s;
		int l = 0;
		int diff = 0;
		char[] buf = new char[len];

		for (i = size; i >= 0; i--) {
			s = String.valueOf(numbers[i]);
			l = s.length();
			diff = len - l;

			if (diff > 0)
				System.arraycopy(empty, 0, buf, 0, diff);

			System.arraycopy(s.toCharArray(), 0, buf, diff, l);

			result[i] = new String(buf);
		}

		return result;
	}

	public static String[] rightAligned(Collection c) {
		return rightAligned(c, ' ');
	}

	public static String[] rightAligned(Collection c, char pad) {
		String[] result = new String[c.size()];

		//determine max number of digits (incl. -)
		final int max = String.valueOf(Collections.max(c)).length();
		final int min = String.valueOf(Collections.min(c)).length();
		final int len = (min > max) ? min : max;

		final char[] empty = fill(new char[len], pad);

		String s;
		int l = 0;
		int diff = 0;
		char[] buf = new char[len];

		Iterator it = c.iterator();

		int i = 0;

		while (it.hasNext()) {
			s = String.valueOf(it.next());
			l = s.length();
			diff = len - l;

			if (diff > 0)
				System.arraycopy(empty, 0, buf, 0, diff);

			System.arraycopy(s.toCharArray(), 0, buf, diff, l);

			result[i] = new String(buf);

			i++;
		}

		return result;
	}
}
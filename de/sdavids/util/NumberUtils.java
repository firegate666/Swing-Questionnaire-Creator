package de.sdavids.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberUtils {

	public static boolean isInt(String s) {
		try {
			new Integer(s);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public static boolean isDouble(String s) {
		try {
			new Double(s);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public static boolean isFloat(String s) {
		try {
			new Float(s);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public static boolean isLong(String s) {
		try {
			new Long(s);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public static boolean isShort(String s) {
		try {
			new Short(s);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public static boolean isByte(String s) {
		try {
			new Byte(s);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public static int compare(Number first, Number second)
		throws IllegalArgumentException {

		boolean big =
			(first instanceof BigDecimal)
				|| (second instanceof BigDecimal)
				|| (first instanceof BigInteger)
				|| (second instanceof BigInteger);

		if (big)
			throw new IllegalArgumentException("cannot handle BigDecimals or BigIntegers");

		boolean decimal =
			(first instanceof Float)
				|| (first instanceof Double)
				|| (second instanceof Float)
				|| (second instanceof Double);

		if (decimal) {
			if (first instanceof Double) {
				if (second instanceof Double) {
					return ((Double) first).compareTo((Double) second);
				} else {
					return ((Double) first).compareTo(new Double(second.doubleValue()));
				}
			} else {
				if (second instanceof Double) {
					return (new Double(first.doubleValue())).compareTo((Double) second);
				} else {
					return (new Double(first.doubleValue())).compareTo(new Double(second.doubleValue()));
				}
			}
		} else {
			if (first instanceof Long) {
				if (second instanceof Long) {
					return ((Long) first).compareTo((Long) second);
				} else {
					return ((Long) first).compareTo(new Long(second.longValue()));
				}
			} else {
				if (second instanceof Long) {
					return (new Long(first.longValue())).compareTo((Long) second);
				} else {
					return (new Long(first.longValue())).compareTo(new Long(second.longValue()));
				}
			}
		}
	}
}
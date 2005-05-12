package de.sdavids.net;
// Imports expanded by Importifier - http://www.javadude.com/tools/importifier     // $IMPORTIFIER-1$
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.NumberFormatException;
import java.lang.Object;
import java.lang.String;
import java.lang.StringBuffer;
import java.util.StringTokenizer;

/**
 * Type description.
 *
 * @author  Sebastian Davids <a href="mailto:sdavids@gmx.de">sdavids@gmx.de</a>
 * @version 1.0
 * @since   (01-07-22 17:46:29)
 */
public class IP {
    private static final String POINT = ".";
    private int a_;
    private int b_;
    private int c_;
    private int d_;

    public static final int ANY = -1;

public IP(String ip)
            throws IllegalArgumentException {
    parse(ip);
}


public boolean conforms(int a, int b, int c, int d) {
    if ((IP.ANY != a) && (a_ != a)) return false;
    if ((IP.ANY != b) && (b_ != b)) return false;
    if ((IP.ANY != c) && (c_ != c)) return false;
    if ((IP.ANY != d) && (d_ != d)) return false;

    return true;
}

public static boolean isClassA(IP ip) {
	return ip.conforms(IP.ANY, IP.ANY, IP.ANY, IP.ANY);
}

public static boolean isClassB(IP ip) {
	return ip.conforms(IP.ANY, IP.ANY, IP.ANY, IP.ANY);
}
public static boolean isClassC(IP ip) {
	return ip.conforms(IP.ANY, IP.ANY, IP.ANY, IP.ANY);
}
public static boolean isClassD(IP ip) {
	return ip.conforms(IP.ANY, IP.ANY, IP.ANY, IP.ANY);
}
	
private int convertToken(String token, int lower, int upper)
            throws IllegalArgumentException {

    try {
        int n = Integer.parseInt(token);

        if ((n < lower) || (n > upper)) throw new IllegalArgumentException();

        return n;
    } catch (NumberFormatException e) {
        throw new IllegalArgumentException();
    }
}


/**
 * Compares two objects for equality.
 * <p>Returns a boolean that indicates whether this IP is equivalent
 * to the specified IP.</p>
 *
 * @param    <code>obj</code> the <code>Object</code> to compare with.
 * @return    <tt>true</tt> if both IPs are equal.
 */
public boolean equals(Object obj) {
    if (obj instanceof IP) {
        IP o = (IP) obj;

        return ((this.getA() == o.getA())
                && (this.getB() == o.getB())
                && (this.getC() == o.getC())
                && (this.getD() == o.getD()));
    } else {
        return false;
    }
}


public final int getA() {
    return a_;
}


public final int getB() {
    return b_;
}


public final int getC() {
    return c_;
}


public final int getD() {
    return d_;
}


/**
 * Generates a hash code for the receiver.
 *
 * <p>This method is supported primarily for hash tables, such as those provided
 * in java.util.Hashtable.</p>
 *
 * @return    an integer hash code for the receiver
 * @see        java.util.Hashtable
 */
public int hashCode() {
    return super.hashCode();
}


private void parse(String ip)
            throws IllegalArgumentException {

    StringTokenizer tokenizer = new StringTokenizer(ip, POINT);

    if (tokenizer.countTokens() != 4) throw new IllegalArgumentException();

    a_ = convertToken(tokenizer.nextToken(), 1, 254);
    b_ = convertToken(tokenizer.nextToken(), 0, 254);
    c_ = convertToken(tokenizer.nextToken(), 0, 254);
    d_ = convertToken(tokenizer.nextToken(), 1, 254);
}


public String toString() {
    StringBuffer result = new StringBuffer();

    result.append(a_);
    result.append(".");
    result.append(b_);
    result.append(".");
    result.append(c_);
    result.append(".");
    result.append(d_);

    return result.toString();
}
}
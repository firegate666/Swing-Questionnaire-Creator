package de.sdavids.xml.sax;
/**
 * @version 	1.0
 * @author 	${user}
 */
public class SAXWriter {
	
	protected static final String NEW_LINE;
	
	static {
		NEW_LINE = System.getProperty("line.separator"); //non-I18N-NLS
	}
	
	public static StringBuffer writeHead(StringBuffer result) {
		return result.append("<?xml version=\"1.0\">"); //non-I18N-NLS;
	}
	
	public static StringBuffer append(StringBuffer result, char c) {
		switch (c) {
			case '&' : return result.append("&amp;");
			case '<' : return result.append("&lt;");
			case '>' : return result.append("&gt;");			
			default: return result.append(c);	
		}		
	}
	
	public static StringBuffer append(StringBuffer result, int i) {
		return result.append(i);
	}
	
	public static StringBuffer append(StringBuffer result, short s) {
		return result.append(s);
	}

	public static StringBuffer append(StringBuffer result, long l) {
		return result.append(l);
	}
	
	public static StringBuffer append(StringBuffer result, float f) {
		return result.append(f);
	}		

	public static StringBuffer append(StringBuffer result, double d) {
		return result.append(d);
	}
	
	public static StringBuffer append(StringBuffer result, boolean b) {
		return result.append(b);
	}
	
	public static StringBuffer append(StringBuffer result, byte b) {
		return result.append(b);
	}

	public static StringBuffer append(StringBuffer result, String s) {
		return result.append(s);
	}	
}


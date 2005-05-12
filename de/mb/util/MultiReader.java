package de.mb.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author demb
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MultiReader {
	
	public static String url2string(String newUrl) throws MalformedURLException, IOException{
		String result="";
		URL url = new URL(newUrl);
		BufferedInputStream bis = new BufferedInputStream(url.openStream() );

		while(bis.available() != 0) {
			result+=(char)bis.read();
		}

		return result;
	}

	public static String file2string(String filename) throws IOException {
		String result="";
		File file = new File(filename);
		FileInputStream fis = new FileInputStream(file);

		while(fis.available() != 0) {
			result+=(char)fis.read();
		}
		
		return result;
	}
	
	public static void main(String [] args) {
		try {
			String result = MultiReader.file2string("c:\\WS_FTP.LOG");
			System.out.println(result);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}

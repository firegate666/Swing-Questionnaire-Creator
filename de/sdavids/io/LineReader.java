package de.sdavids.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Reader which reads a file and creates an String-array containing the file's lines.
 * 
 * @author  Sebastian Davids <a href="mailto:sdavids@gmx.de">sdavids@gmx.de</a>
 * @version 1.0
 * @since   (01-03-15 16:48:08)
 */
public class LineReader {
/**
 * Checks the preconditions of readFile().
 * <ul>
 *     <li>The argument is not <tt>null</tt>.
 *     <li>The argument is a file rather than a dictionary.</li>
 *     <li>The file is readable.</li>
 * </ul>
 *
 * @param	<code>file</code> the file to be verified.
 * @throws	NullPointerException if <code>null == file</code>.
 * @throws	IllegalArgumentException if file is not a "file".
 * @throws	FileNotFoundException if the file is not readable.
 * @see		#readFile
 * @see		java.io.File
 */
private static void checkPreconditions(File file) throws NullPointerException, IllegalArgumentException, FileNotFoundException {
	if (null == file)    throw new NullPointerException();
	if (!file.isFile())  throw new IllegalArgumentException();
	if (!file.canRead()) throw new FileNotFoundException();
}


/**
 * Reads the file and stores each line in an array.
 *
 * @param	file the file to be read.
 * @return	an array containing the file's lines.
 * @throws	NullPointerException if <code>null == file</code>.
 * @throws	IllegalArgumentException if file is not a "file".
 * @throws	FileNotFoundException if the file is not readable.
 * @see	readFile
 */
public static String[] getLines(File file) throws NullPointerException, IllegalArgumentException, IOException {
	return readFile(file);
}


/**
 * Reads the file from the given path and stores each line in an array.
 *
 * @param	<code>filePath</code> the path of the file to be read.
 * @return	an array containing the file's lines.
 * @throws	NullPointerException if <code>null == filePath</code>.
 * @throws	IllegalArgumentException if filePath is not a path to a &quot;file&quot;.
 * @throws	FileNotFoundException if the file with the given path is not readable.
 * @see		readFile
 */
public static String[] getLines(String filePath) throws NullPointerException, IllegalArgumentException, IOException {
	return LineReader.getLines(new File(filePath));
}


/**
 * Reads the file and stores each line in an array.
 *
 * @param	<code>file</code> the file to be read.
 * @return	an array containing the file's lines or <tt>null</tt> if the file did not contain lines.
 * @throws	NullPointerException if <code>null == file</code>.
 * @throws	IllegalArgumentException if file is not a "file".
 * @throws	FileNotFoundException if the file is not readable.
 * @see		checkPreconditions
 */
private static String[] readFile(File file) throws NullPointerException, IllegalArgumentException, IOException {
	checkPreconditions(file);
	
	ArrayList      lines = new ArrayList();
	BufferedReader in    = new BufferedReader(new FileReader(file));

	String line = in.readLine();
	
	while(null != line) {
		lines.add(line);
		line = in.readLine();
	}

	in.close();

	return (0 == lines.size()) ? null : (String[]) lines.toArray(new String[lines.size()]);
}
}
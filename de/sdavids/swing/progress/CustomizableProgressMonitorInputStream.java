package de.sdavids.swing.progress;
import java.awt.Component;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;

/** 
 * Monitors the progress of reading from some InputStream. This ProgressMonitor
 * is normally invoked in roughly this form:
 * <pre>
 * InputStream in = new BufferedInputStream(
 *                          new ProgressMonitorInputStream(
 *                                  parentComponent,
 *                                  "Reading " + fileName,
 *                                  new FileInputStream(fileName)));
 * </pre><p>
 * This creates a progress monitor to monitor the progress of reading
 * the input stream.  If it's taking a while, a ProgressDialog will
 * be popped up to inform the user.  If the user hits the Cancel button
 * an InterruptedIOException will be thrown on the next read.
 * All the right cleanup is done when the stream is closed.
 *
 * @see ProgressMonitor
 * @see JOptionPane
 * @author James Gosling
 * @version SD 1.02 [01-09-30 13:30:12] 
 */
public class CustomizableProgressMonitorInputStream extends FilterInputStream
{
	private CustomizableProgressMonitor monitor;
	private int             nread = 0;
	private int             size = 0;


	/**
	 * Constructs an object to monitor the progress of an input stream.
	 *
	 * @param message Descriptive text to be placed in the dialog box
	 *                if one is popped up.
	 * @param parentComponent The component triggering the operation
	 *                        being monitored.
	 * @param in The input stream to be monitored.
	 */
	public CustomizableProgressMonitorInputStream(Component parentComponent,
									  Object message,
									  InputStream in) {
										  
		this(parentComponent, message, in, "Progress..");
	}


	/**
	 * Constructs an object to monitor the progress of an input stream.
	 *
	 * @param title the title of the dialog
	 * @param message a descriptive message that will be shown
	 *        to the user to indicate what operation is being monitored.
	 *        This does not change as the operation progresses.
	 *        See the message parameters to methods in
	 *        {@link JOptionsPane#message}
	 *        for the range of values.
	 * @param messageType the type of message to be displayed:
	 *                    ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE,
	 *                    QUESTION_MESSAGE, or PLAIN_MESSAGE.
	 * @param optionType the options to display in the pane:
	 *                   DEFAULT_OPTION, YES_NO_OPTION, YES_NO_CANCEL_OPTION
	 *                   OK_CANCEL_OPTION
	 * @param icon the Icon image to display			 
	 * @param in The input stream to be monitored.
	 */
	public CustomizableProgressMonitorInputStream(Component parentComponent,
						              Object message,
									  InputStream in,
									  String title) {
										  
		this(parentComponent,message, in, title, JOptionPane.INFORMATION_MESSAGE);
	}


	/**
	 * Constructs an object to monitor the progress of an input stream.
	 *
	 * @param title the title of the dialog
	 * @param message a descriptive message that will be shown
	 *        to the user to indicate what operation is being monitored.
	 *        This does not change as the operation progresses.
	 *        See the message parameters to methods in
	 *        {@link JOptionsPane#message}
	 *        for the range of values.
	 * @param messageType the type of message to be displayed:
	 *                    ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE,
	 *                    QUESTION_MESSAGE, or PLAIN_MESSAGE.
	 * @param optionType the options to display in the pane:
	 *                   DEFAULT_OPTION, YES_NO_OPTION, YES_NO_CANCEL_OPTION
	 *                   OK_CANCEL_OPTION
	 * @param icon the Icon image to display			 
	 * @param in The input stream to be monitored.
	 */
	public CustomizableProgressMonitorInputStream(Component parentComponent,
						              Object message,
						              InputStream in,
						              String title,
						              int messageType) {

		this(parentComponent,message, in, title, messageType, JOptionPane.DEFAULT_OPTION);
	}


	/**
	 * Constructs an object to monitor the progress of an input stream.
	 *
	 * @param title the title of the dialog
	 * @param message a descriptive message that will be shown
	 *        to the user to indicate what operation is being monitored.
	 *        This does not change as the operation progresses.
	 *        See the message parameters to methods in
	 *        {@link JOptionsPane#message}
	 *        for the range of values.
	 * @param messageType the type of message to be displayed:
	 *                    ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE,
	 *                    QUESTION_MESSAGE, or PLAIN_MESSAGE.
	 * @param optionType the options to display in the pane:
	 *                   DEFAULT_OPTION, YES_NO_OPTION, YES_NO_CANCEL_OPTION
	 *                   OK_CANCEL_OPTION
	 * @param icon the Icon image to display			 
	 * @param in The input stream to be monitored.
	 */
	public CustomizableProgressMonitorInputStream(Component parentComponent,
						              Object message,
						              InputStream in,
						              String title,
						              int messageType,
						              int optionType) {
		
		this(parentComponent, message, in, title, messageType, optionType, null);
	}


	/**
	 * Constructs an object to monitor the progress of an input stream.
	 *
	 * @param title the title of the dialog
	 * @param message a descriptive message that will be shown
	 *        to the user to indicate what operation is being monitored.
	 *        This does not change as the operation progresses.
	 *        See the message parameters to methods in
	 *        {@link JOptionsPane#message}
	 *        for the range of values.
	 * @param messageType the type of message to be displayed:
	 *                    ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE,
	 *                    QUESTION_MESSAGE, or PLAIN_MESSAGE.
	 * @param optionType the options to display in the pane:
	 *                   DEFAULT_OPTION, YES_NO_OPTION, YES_NO_CANCEL_OPTION
	 *                   OK_CANCEL_OPTION
	 * @param icon the Icon image to display			 
	 * @param in The input stream to be monitored.
	 */
	public CustomizableProgressMonitorInputStream(Component parentComponent,
						              Object message,
						              InputStream in,
						              String title,
						              int messageType,
						              int optionType,
						              Icon icon) {
		super(in);
		try {
			size = in.available();	
		}
		catch(IOException ioe) {
			size = 0;
		}
		monitor = new CustomizableProgressMonitor(parentComponent, title, message, 
			                             messageType, optionType, icon, null, 0, size);
	}


	/**
	 * Overrides <code>FilterInputStream.close</code> 
	 * to close the progress monitor as well as the stream.
	 */
	public void close() throws IOException {
		in.close();
		monitor.close();
	}


	/**
	 * Get the ProgressMonitor object being used by this stream. Normally
	 * this isn't needed unless you want to do something like change the
	 * descriptive text partway through reading the file.
	 * @return the ProgressMonitor object used by this object 
	 */
	public CustomizableProgressMonitor getProgressMonitor() {
		return monitor;
	}


	/**
	 * Overrides <code>FilterInputStream.read</code> 
	 * to update the progress monitor after the read.
	 */
	public int read() throws IOException {
		int c = in.read();
		if (c >= 0) monitor.setProgress(++nread);
		if (monitor.isCanceled()) {
			InterruptedIOException exc =
									new InterruptedIOException("progress");
			exc.bytesTransferred = nread;
			throw exc;
		}
		return c;
	}


	/**
	 * Overrides <code>FilterInputStream.read</code> 
	 * to update the progress monitor after the read.
	 */
	public int read(byte b[]) throws IOException {
		int nr = in.read(b);
		if (nr > 0) monitor.setProgress(nread += nr);
		if (monitor.isCanceled()) {
			InterruptedIOException exc =
									new InterruptedIOException("progress");
			exc.bytesTransferred = nread;
			throw exc;
		}
		return nr;
	}


	/**
	 * Overrides <code>FilterInputStream.read</code> 
	 * to update the progress monitor after the read.
	 */
	public int read(byte b[],
					int off,
					int len) throws IOException {
		int nr = in.read(b, off, len);
		if (nr > 0) monitor.setProgress(nread += nr);
		if (monitor.isCanceled()) {
			InterruptedIOException exc =
									new InterruptedIOException("progress");
			exc.bytesTransferred = nread;
			throw exc;
		}
		return nr;
	}


	/**
	 * Overrides <code>FilterInputStream.reset</code> 
	 * to reset the progress monitor as well as the stream.
	 */
	public synchronized void reset() throws IOException {
		in.reset();
		nread = size - in.available();
		monitor.setProgress(nread);
	}


	/**
	 * Overrides <code>FilterInputStream.skip</code> 
	 * to update the progress monitor after the skip.
	 */
	public long skip(long n) throws IOException {
		long nr = in.skip(n);
		if (nr > 0) monitor.setProgress(nread += nr);
		return nr;
	}
}
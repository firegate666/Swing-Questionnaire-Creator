package de.sdavids.swing;

import javax.swing.SwingUtilities;

/**
 * This is the 3rd version of SwingWorker (also known as
 * SwingWorker 3), an abstract class that you subclass to
 * perform GUI-related work in a dedicated thread.  For
 * instructions on using this class, see:
 *
 * http://java.sun.com/docs/books/tutorial/uiswing/misc/threads.html
 *
 * Note that the API changed slightly in the 3rd version:
 * You must now invoke start() on the SwingWorker after
 * creating it.
 */
public abstract class SwingWorker {
	private Object value; // see getValue(), setValue()
	private Thread thread;
	private boolean fFinished = false;//SD-02-01-21
	private boolean fInterrupted = false;//SD-02-01-21
	private boolean fIsNotRunning = true;//SD-02-01-21
	
	/**
	 * Class to maintain reference to current worker thread
	 * under separate synchronization control.
	 */
	private static class ThreadVar {
		private Thread thread;
		ThreadVar(Thread t) {
			thread = t;
		}
		synchronized Thread get() {
			return thread;
		}
		synchronized void clear() {
			thread = null;
		}
	}

	private ThreadVar threadVar;

	/**
	 * Start a thread that will call the <code>construct</code> method
	 * and then exit.
	 */
	public SwingWorker() {
		final Runnable doFinished = new Runnable() {
			public void run() {
				finished();
			}
		};

		Runnable doConstruct = new Runnable() {
			public void run() {
				try {
					init();//SD-02-01-21
					setValue(construct());
				} finally {
					threadVar.clear();
				}

				SwingUtilities.invokeLater(doFinished);
			}
		};

		Thread t = new Thread(doConstruct);
		threadVar = new ThreadVar(t);
	}
	/**
	 * Compute the value to be returned by the <code>get</code> method.
	 */
	public abstract Object construct();
	
	/**
	 * Called on the event dispatching thread (not on the worker thread)
	 * after the <code>construct</code> method has returned.
	 */
	public void finished() {}
	
	public boolean hasFinished() {//SD-02-01-21
		return fFinished;//SD-02-01-21
	}//SD-02-01-21

	public boolean hasBeenInterrupted() {//SD-02-01-21
		return fInterrupted;//SD-02-01-21
	}//SD-02-01-21

	public boolean isNotRunning() {//SD-02-01-21
		return fIsNotRunning;//SD-02-01-21
	}//SD-02-01-21

	protected void init() {//SD-02-01-21
		fFinished = false;//SD-02-01-21
		fInterrupted = false;//SD-02-01-21
		fIsNotRunning = true;//SD-02-01-21
	}//SD-02-01-21
		
	/**
	 * Return the value created by the <code>construct</code> method.
	 * Returns null if either the constructing thread or the current
	 * thread was interrupted before a value was produced.
	 *
	 * @return the value created by the <code>construct</code> method
	 */
	public Object get() {
		while (true) {
			Thread t = threadVar.get();
			if (null == t) {
				return getValue();
			}
			try {
				t.join();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // propagate
				return null;
			}
		}
	}
	/**
	 * Get the value produced by the worker thread, or null if it
	 * hasn't been constructed yet.
	 */
	protected synchronized Object getValue() {
		return value;
	}
	/**
	 * Call this method to force the worker to stop what it's doing.
	 * <p>
	 * Use this method to terminate the worker thread in a abnormal way, i.e.
	 * use it if you want to the thread to be marked as <em>interrupted</em> but
	 * not <em>finished</em>.
	 * 
	 * @see	#stop
	 */
	public void interrupt() {
		fInterrupted = true;////SD-02-01-21
		fFinished = false;////SD-02-01-21
		
		Thread t = threadVar.get();
		
		if (t != null) {
			t.interrupt();
		}
		
		threadVar.clear();	
	}
	/**
	 * Set the value produced by worker thread
	 */
	private synchronized void setValue(Object x) {
		value = x;
	}
	/**
	 * Start the worker thread.
	 * <p>
	 * A worker cannot be started twice with this method.
	 */
	public void start() {
		if (isNotRunning()) {//SD-02-01-21
			synchronized (this) {//SD-02-01-21
				if (!isNotRunning())//SD-02-01-21			
					return;//SD-02-01-21
					
				fIsNotRunning = false;//SD-02-01-21
			}//SD-02-01-21
		} else {//SD-02-01-21
			return;//SD-02-01-21
		}//SD-02-01-21
		
		Thread t = threadVar.get();
		if (t != null) {
			t.start();
		}
	}
	
	/**
	 * Stop the worker thread.
	 * <p>
	 * Use this method to terminate the worker thread in a normal way, i.e.
	 * use it if you want to the thread to be marked as <em>finished</em> but
	 * not <em>interrupted</em>.
	 * <p>
	 * Should be overridden for as:
	 * <pre><code>
	 * public synchronized void stop() {
	 *     if (hasFinished() || hasBeenInterrupted())
	 * 	       return;			
	 * 	
	 *     super.stop();
	 *     super.interrupt();
	 * }<code></pre>
	 * 
	 * @see	#interrupt
	 */	
	public synchronized void stop() {//SD-02-01-21
		fInterrupted = false;//SD-02-01-21
		fFinished = true;//SD-02-01-21
	}//SD-02-01-21
}
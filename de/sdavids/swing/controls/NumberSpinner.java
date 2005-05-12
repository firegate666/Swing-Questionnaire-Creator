package de.sdavids.swing.controls;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.SwingPropertyChangeSupport;

import au.com.virturo.guiutils.SpinEvent;
import au.com.virturo.guiutils.SpinListener;
import au.com.virturo.guiutils.Spinner;

import de.sdavids.beans.IBoundBean;
import de.sdavids.swing.SwingUtils;
import de.sdavids.swing.InfoTipWindow;
import de.sdavids.swing.beans.BoundJTextField;

public class NumberSpinner extends JPanel implements IBoundBean {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */
	/** Bound property support */
	protected transient SwingPropertyChangeSupport fPCS;

	private BoundJTextField fNumberTF;
	private int fMin;
	private int fMax;
	private int fDefault;
	private Spinner fSpinner;
	private int fLastLegal;
	private boolean fKeyEntered;
	private boolean fUpIncreasesNumber;
	
	/**
	 * The window showing information.
	 */
	private InfoTipWindow fToolTipWindow;

	protected class IntSpinListener implements SpinListener {
		public void spinnerSpunUp(SpinEvent e) {
			adjustTF(fUpIncreasesNumber);
		}

		public void spinnerSpunDown(SpinEvent e) {
			adjustTF(!fUpIncreasesNumber);			
		}
	}

	public NumberSpinner() {
		this(1, 10, 1, "", true, true);
	}
		
	public NumberSpinner(
		int min,
		int max,
		int defaultVal,
		String infotip,
		boolean editable,
		boolean upIncreasesNumber) {
		if (min > max) {
			setMin(max);
			setMax(min);
		} else {
			setMin(min);
			setMax(max);
		}

		setDefault(defaultVal);

		fLastLegal = getDefault();

		setDirection(upIncreasesNumber);
		
		fToolTipWindow = new InfoTipWindow(infotip);

		getNumberTF().setText(String.valueOf(getDefault()));
		getNumberTF().setHorizontalAlignment(getNumberTF().RIGHT);
		getNumberTF().setColumns(String.valueOf(getMax()).length());
		getNumberTF().setEditable(editable);

		add(getSpinner());

		addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				transferFocusToNumberTF();
			}
		});		
	}

	public void setText(String text) {
		String oldText = getText();

		getNumberTF().setText(text);

		firePropertyChange("text", oldText, text);
	}

	public void setDirection(boolean upIncreasesNumber) {
		fUpIncreasesNumber = upIncreasesNumber;
	}
	
	public void isEditable() {
		getNumberTF().isEditable();
	}
	
	public void setEditable(boolean editable) {
		getNumberTF().setEditable(editable);
	}
	
	public String getText() {
		return getNumberTF().getText();
	}

	public void setToolTipText(String tooltip) {
		getSpinner().setToolTipText(tooltip);
	}

	public void setInfoTipText(String infotip) {
		getToolTipWindow().setMessage(infotip);
	}

	public int getColumns() {
		return getNumberTF().getColumns();
	}
	
	public void setColumns(int columns) {
		getNumberTF().setColumns(columns);
	}
	
	public String getInfoTipText() {
		return getToolTipWindow().getMessage();
	}
		
	protected JTextField getNumberTF() {
		if (null == fNumberTF) {
			fNumberTF = new BoundJTextField();

			fNumberTF.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent evt) {
					keyEnteredAction(evt);
				}
			});

			fNumberTF.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					firePropertyChange(evt);
				}
			});

			fNumberTF.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent e) {
					focusLostAction();
				}
				
				public void focusGained(FocusEvent e) {
					beSelected();
				}
			});

			fNumberTF.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent evt) {
					beSelected();
				}
			});
		}

		return fNumberTF;
	}

	/**
	 * Invoked when a key is entered.
	 * 
	 * Fires a <code>PropertyChangeEvent</code> if the input number is legal
	 * and the user pressed the <em>Enter</em> key.
	 * 
	 * @param	<code>evt</code> the <code>KeyEvent</code> we respond to.
	 */
	protected void keyEnteredAction(KeyEvent evt) {
		fKeyEntered = true;

		beSelected();

		getToolTipWindow().setVisible(false);

		int code = evt.getKeyCode();
		
		if (KeyEvent.VK_DOWN == code) {
			adjustTF(true);

			evt.consume();
			return;
		} else if (KeyEvent.VK_UP == code) {
			adjustTF(false);
			
			evt.consume();
			return;
		}
		
		boolean legal = false;
		int n = fLastLegal;
		String txt = getNumberTF().getText();

		try {
			n = Integer.parseInt(txt);

			if (withinMinMax(n))
				legal = true;
		} catch (NumberFormatException e) {
			//legal = false;
		}

		getNumberTF().setForeground((legal) ? Color.black : Color.red);

		if (!legal) {
			SwingUtils.showBelow(
				getNumberTF(),
				getToolTipWindow(),
				SwingConstants.LEADING);
			return;
		}

		fLastLegal = n;

		if (KeyEvent.VK_ENTER == evt.getKeyCode()) {
			fKeyEntered = false;
			focusLostAction();
			return;
		}

		firePropertyChange("text", null, txt);

		firePropertyChange("update", null, this);
	}

	protected void focusLostAction() {
		boolean legal = false;
		int n = fLastLegal;
		String txt = getNumberTF().getText();

		try {
			n = Integer.parseInt(txt);

			if (withinMinMax(n))
				legal = true;
		} catch (NumberFormatException ex) {
			//legal = false;
		}

		if (legal) {
			fLastLegal = n;

			firePropertyChange("text", null, txt);
		} else {
			getNumberTF().setText(String.valueOf(fLastLegal));
			getNumberTF().setForeground(Color.black);
			beUnselected();
		}

		if (fKeyEntered) {
			fKeyEntered = false;
		} else {
			beUnselected();
		}
	}

	protected void adjustTF(boolean spinUp) {
		JTextField source = getNumberTF();
		
		int choice = getDefault();

		try {
			choice = Integer.parseInt(source.getText());

			int max = getMax();
			int min = getMin();

			if ((max < choice) || ((max == choice) && spinUp)) {
				getToolkit().beep();
				
				choice = max;
			} else if ((min > choice) || ((min == choice) && !spinUp)) {
				getToolkit().beep();
				
				choice = min;
			} else if (spinUp) {
				choice++;
			} else {
				choice--;
			}
		} catch (NumberFormatException n) {
			getToolkit().beep();
		}

		fLastLegal = choice;

		source.setText(String.valueOf(choice));

		source.setForeground(Color.black);
		
		fKeyEntered = false;
		
		transferFocusToNumberTF();
	}


	protected void transferFocusToNumberTF() {
		if (!getNumberTF().hasFocus()) {
			if (!getNumberTF().isRequestFocusEnabled())
				getNumberTF().setRequestFocusEnabled(true);

			getNumberTF().requestFocus();
		}		
	}
					
	protected boolean withinMinMax(int value) {
		return ((value >= getMin()) && (value <= getMax()));
	}

	public void setMin(int min) {
		if (getMin() == min) return;		
		
		fMin = min;

		if (! (withinMinMax(getDefault())))
			setDefault(getMin());		
	}

	public void setDefault(int defaultVal) {
		fDefault = (withinMinMax(defaultVal)) ? defaultVal : getMin();
	}

	public void setMax(int max) {
		if (getMax() == max) return;
		
		fMax = max;
		
		if (! (withinMinMax(getDefault())))
			setDefault(getMax());	
	}

	public int getMin() {
		return fMin;
	}

	public int getMax() {
		return fMax;
	}

	public int getDefault() {
		return fDefault;
	}

	protected Spinner getSpinner() {
		if (null == fSpinner) {
			fSpinner = new Spinner(getNumberTF());

			fSpinner.addSpinListener(new IntSpinListener());
		}

		return fSpinner;
	}

	protected Color getSelectedColor() {
		return Color.yellow;
	}

	protected Color getUnselectedColor() {
		return Color.white;
	}

	public void beSelected() {
		getNumberTF().setBackground(getSelectedColor());
	}

	public void beUnselected() {
		getNumberTF().setBackground(getUnselectedColor());
	}
	
	public boolean requestDefaultFocus() {
		return getNumberTF().requestDefaultFocus();
	}

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Answer the internal <code>SwingPropertyChangeSupport</code>.
	 * 
	 * @return	<code>listener</code> the
	 * 			<code>SwingPropertyChangeSupport</code>.
	 */
	protected SwingPropertyChangeSupport getPropertyChange() {
		if (null == fPCS)
			fPCS = new SwingPropertyChangeSupport(this);

		return fPCS;
	}

	/**
	 * Answer the tooltip window.
	 * 
	 * @return	the panel.
	 */
	protected InfoTipWindow getToolTipWindow() {
		return fToolTipWindow;
	}

	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT IBOUNDBEAN
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Add a PropertyChangeListener to the listener list. The listener is
	 * registered for all properties.
	 * 
	 * @param	<code>listener</code> the <code>PropertyChangeListener</code>
	 * 			to be added.
	 */
	public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {

		getPropertyChange().addPropertyChangeListener(listener);
	}

	/**
	 * Add a PropertyChangeListener for a specific property. The listener
	 * will be invoked only when a call on firePropertyChange names that
	 * specific property.
	 * 
	 * @param	<code>propertyName</code> the name of the property to listen
	 * 			on.
	 * @param	<code>listener</code> the <code>PropertyChangeListener</code>
	 * 			to be added.
	 */
	public synchronized void addPropertyChangeListener(
		String propertyName,
		PropertyChangeListener listener) {

		getPropertyChange().addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * Remove a PropertyChangeListener from the listener list. This removes a
	 * PropertyChangeListener that was registered for all properties.
	 * 
	 * @param	<code>listener</code> the <code>PropertyChangeListener</code>
	 * 			to be removed.
	 */
	public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {

		getPropertyChange().removePropertyChangeListener(listener);
	}

	/**
	 * Add a PropertyChangeListener for a specific property. The listener will
	 * be invoked only when a call on firePropertyChange names that specific
	 * property.
	 * 
	 * @param	<code>propertyName</code> the name of the property to listen
	 * 			on.
	 * @param	<code>listener</code> the <code>PropertyChangeListener</code>
	 * 			to be removed.
	 */
	public synchronized void removePropertyChangeListener(
		String propertyName,
		PropertyChangeListener listener) {

		getPropertyChange().removePropertyChangeListener(propertyName, listener);
	}

	/**
	 * Fire an existing PropertyChangeEvent to any registered listeners. No
	 * event is fired if the given event's old and new values are equal and
	 * non-null.
	 * 
	 * @param	<code>evt</code> the <code>PropertyChangeEvent</code> object.
	 */
	public void firePropertyChange(PropertyChangeEvent evt) {
		getPropertyChange().firePropertyChange(evt);
	}

	/**
	 * Report a bound property update to any registered listeners. No event is
	 * fired if old and new are equal and non-null.
	 * 
	 * This is merely a convenience wrapper around the more general
	 * <code>firePropertyChange</code> method that takes <code>Object</code>
	 * values.
	 * 
	 * @param	<code>propertyName</code> the programmatic name of the 
	 * 			property that was changed.
	 * @param	<code>oldValue</code> the old value of the property.
	 * @param	<code>newValue</code> the new value of the property.
	 */
	public void firePropertyChange(
		String propertyName,
		Object oldValue,
		Object newValue) {

		getPropertyChange().firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Report an int bound property update to any registered listeners. No
	 * event is fired if old and new are equal and non-null.
	 * 
	 * This is merely a convenience wrapper around the more general
	 * <code>firePropertyChange</code> method that takes <code>Object</code>
	 * values.
	 * 
	 * @param	<code>propertyName</code> the programmatic name of the
	 * 			property that was changed.
	 * @param	<code>oldValue</code> the old value of the property.
	 * @param	<code>newValue</code> the new value of the property.
	 */
	public void firePropertyChange(
		String propertyName,
		int oldValue,
		int newValue) {

		getPropertyChange().firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Report an boolean bound property update to any registered listeners. No
	 * event is fired if old and new are equal and non-null.
	 * 
	 * This is merely a convenience wrapper around the more general
	 * <code>firePropertyChange</code> method that takes <code>Object</code>
	 * values.
	 * 
	 * @param	<code>propertyName</code> the programmatic name of the
	 *   		property that was changed.
	 * @param	<code>oldValue</code> the old value of the property.
	 * @param	<code>newValue</code> the new value of the property.
	 */
	public void firePropertyChange(
		String propertyName,
		boolean oldValue,
		boolean newValue) {

		getPropertyChange().firePropertyChange(propertyName, oldValue, newValue);
	}

	/* ----------- Accessors -------------------- */

	/**
	 * Check if there are any listeners for a specific property.
	 * 
	 * @param	<code>listener</code> the property name.
	 * @return	<code>true</code>  if there are ore or more listeners for the
	 * 			given property.
	 */
	public synchronized boolean hasListeners(String propertyName) {
		return getPropertyChange().hasListeners(propertyName);
	}
}
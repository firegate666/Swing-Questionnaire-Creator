package de.sdavids.swing.controls;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
import javax.swing.text.Keymap;

import de.sdavids.swing.SwingCreator;

public class JTextFieldWithPopUp extends JTextField {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Controls ---------- */

	/** The popup menu. */
	private JPopupMenu fPopupMenu;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Constructs a new <code>JTextFieldWithPopUp</code>.
	 * 
	 * A default model is created, the initial string is null, and the number of 
	 * columns is set to 0.
	 */
	public JTextFieldWithPopUp() {
		super();
		initialize();
	}

	/**
	 * Constructs a new <code>JTextFieldWithPopUp</code> initialized with the
	 * specified text.
	 * 
	 * A default model is created and the number of columns is 0.
	 *
	 * @param	<code>text</code> the text to be displayed, or null
	 */
	public JTextFieldWithPopUp(String text) {
		super(text);
		initialize();
	}

	/**
	 * Constructs a new empty <code>JTextFieldWithPopUp</code> with the specified
	 * number of columns.
	 * 
	 * A default model is created and the initial string is set to null.
	 *
	 * @param	<code>columns</code> the number of columns to use to calculate 
	 *   		the preferred width.  If columns is set to zero, the
	 *   		preferred width will be whatever naturally results from
	 *   		the component implementation.
	 */
	public JTextFieldWithPopUp(int columns) {
		super(columns);
		initialize();
	}

	/**
	 * Constructs a new <code>JTextFieldWithPopUp</code> initialized with the
	 * specified text and columns.
	 * 
	 * A default model is created.
	 *
	 * @param	<code>text</code> the text to be displayed, or null
	 * @param	<code>columns</code> the number of columns to use to calculate 
	 *   		the preferred width.  If columns is set to zero, the
	 *   		preferred width will be whatever naturally results from
	 *   		the component implementation.
	 */
	public JTextFieldWithPopUp(String text, int columns) {
		super(text, columns);
		initialize();
	}

	/**
	 * Constructs a new <code>JTextFieldWithPopUp</code> that uses the given text
	 * storage model and the given number of columns.
	 * 
	 * If the document is null, a default model is created.
	 *
	 * @param	<code>doc</code> the text storage to use.  If this is null, a
	 * 			default will be provided by calling the createDefaultModel method.
	 * @param	<code>text</code> the text to be displayed, or null
	 * @param	<code>columns</code> the number of columns to use to calculate 
	 *   		the preferred width.  If columns is set to zero, the
	 *   		preferred width will be whatever naturally results from
	 *   		the component implementation.
	 * @throws	<code>IllegalArgumentException</code> if <code>columns < 0</code>
	 */
	public JTextFieldWithPopUp(Document doc, String text, int columns) {
		super(doc, text, columns);
		initialize();
	}

	/////////////////////////////////////////////////////////////////////////////
	// CONTROLS
	/////////////////////////////////////////////////////////////////////////////

	/**
	 * Answer the popup menu.
	 *
	 * @return	the popup menu.
	 */
	protected JPopupMenu getPopupMenu() {
		if (null == fPopupMenu) {
			fPopupMenu = new JPopupMenu();

			Action copy      = null;
			Action cut       = null;
			Action paste     = null;
			Action selectAll = null;
			
			Action[] actions = getActions();
			Action action;
			String name;

			for (int i = (actions.length - 1); i >= 0; i--) {
				action = actions[i];
				name = (String) action.getValue(action.NAME);

				if (DefaultEditorKit.selectAllAction.equals(name)) {
					selectAll = action;
				} else if (DefaultEditorKit.cutAction.equals(name)) {
					cut = action;
				} else if (DefaultEditorKit.copyAction.equals(name)) {
					copy = action;
				} else if (DefaultEditorKit.pasteAction.equals(name)) {
					paste = action;
				}

				if ((null != selectAll)
					&& (null != cut)
					&& (null != copy)
					&& (null != paste))
					break;
			}

			fPopupMenu.add(SwingCreator.newJMenuItem(cut));
			fPopupMenu.add(SwingCreator.newJMenuItem(copy));
			fPopupMenu.add(SwingCreator.newJMenuItem(paste));
			fPopupMenu.add(SwingCreator.newJMenuItem(selectAll));
		}

		return fPopupMenu;
	}

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Initialize the <code>JTextFieldWithPopUp</code>.
	 *
	 * Associate the popup menu.
	 */
	protected void initialize() {
		enableEvents(AWTEvent.MOUSE_EVENT_MASK);
	}

	/**
	 * Show the popup menu if a <em>PopupTrigger</em> has been sent.
	 */
	public void processMouseEvent(MouseEvent evt) {
		if (evt.isPopupTrigger()) {
			getPopupMenu().show(this, evt.getX(), evt.getY());
		}

		super.processMouseEvent(evt);
	}
}
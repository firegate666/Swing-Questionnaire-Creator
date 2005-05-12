package de.sdavids.dnd;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * The <code>DropPanel</code> is used to for drag & drop operations.
 */
public class DropPanel extends JPanel {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Class Attributes ------------- */

	/** Used to show the panel in its &quot;unexpanded&quot; state. */
	private static final Border NO_BORDER;

	/* ----------- Instance Attributes ---------- */

	/** The highlight color. */
	private Color fHighlight;

	/** Used to show the panel in its &quot;unexpanded&quot; state. */
	private Border fExpandedBorder;

	//////////////////////////////////////////////////////////////////////////////
	// INITIALIZATION
	//////////////////////////////////////////////////////////////////////////////

	static {
		NO_BORDER = BorderFactory.createEmptyBorder();
	}

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////    

	/**
	 * Create a new <code>DropPanel</code> with the default &quot;highlight&quot;
	 * color orange.
	 * 
	 * @param	<code>highlight</code> the &quot;highlighter&quot; color.
	 */
	public DropPanel() {
		this(Color.orange);
	}

	/**
	 * Create a new <code>DropPanel</code> with the given &quot;highlight&quot;
	 * color.
	 * 
	 * @param	<code>highlight</code> the &quot;highlighter&quot; color.
	 */
	public DropPanel(Color highlight) {
		super();

		setHighlight(highlight);

		initialize();
	}

	/////////////////////////////////////////////////////////////////////////////
	// PUBLIC METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Answer the &highlighter&quot; color.
	 * 
	 * @param	the color.
	 */
	public Color getHighlight() {
		return fHighlight;
	}

	/* ----------- Mutators --------------------- */

	/**
	 * Set the &highlighter&quot; color.
	 * 
	 * If <code>null == highlight</code> the default color orange will be used.
	 * 
	 * @param	<code>highlight</code> the color.
	 */
	public void setHighlight(Color highlight) {
		fHighlight = (null == highlight) ? Color.orange : highlight;
	}

	/**
	 * Highlight the drop panel.
	 */
	public void showSelected() {
		setBackground(getHighlight());
		setBorder(getExpandedBorder());
	}

	/**
	 * Remove the highlight from the drop panel.
	 */
	public void showUnselected() {
		setBackground(getUnselectedColor());
		setBorder(NO_BORDER);
	}

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Initialize the panel.
	 * 
	 * Lays out all <code>Components</code> and registers needed
	 * <code>EventListener</code>s.
	 */
	protected void initialize() {
		showUnselected();

		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				if (!hasFocus()) {
					if (!isRequestFocusEnabled())
						setRequestFocusEnabled(true);

					requestFocus();
				}
			}
		});
	}

	/* ----------- Accessors -------------------- */

	/**
	 * Answer the color of an &quot;unhighlighted&quot; panel.
	 */
	protected Color getUnselectedColor() {
		return Color.white;
	}

	/**
	 * Answer the number of pixels by which the panel should be &quot;expanded&quot;.
	 * 
	 * Current implementation: 10 pixels.
	 * 
	 * @return	the number of pixels.
	 */
	protected int getLineSize() {
		return 10;
	}

	/**
	 * Answer the boder used to show the panel in its &quot;unexpanded&quot; state.
	 * 
	 * @return	the number of pixels.
	 * @see	#getLineSize.
	 */
	protected Border getExpandedBorder() {
		if (null == fExpandedBorder)
			fExpandedBorder =
				BorderFactory.createLineBorder(getHighlight(), (getLineSize() / 2));

		return fExpandedBorder;
	}
}
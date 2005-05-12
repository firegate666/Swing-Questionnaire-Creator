package de.sdavids.swing.controls;

/*
 * ===========================================================================
 * Copyright (c)2001 Sebastian Davids. All rights reserved.
 * ===========================================================================
 */

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;
import javax.swing.text.DefaultEditorKit;

public class LabelTextFieldPanel extends JPanel {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Variables ---------- */
	
	/**
	 * The default value.
	 */
	private String fDefault;
	
	/**
	 * Indicates if it is the first click on the label view after setting the value
	 * to it's default value or not.
	 */
	private boolean fFirstClick;

	/**
	 * Indicates the value will be updated to the default value if the user clears the
	 * textfield view.
	 */
	private boolean fSetToDefault;
		
	/* ----------- Bean Properties ---------- */

	/**
	 * The value.
	 * 
	 * Bound property.
	 */
	private String fValue;

	/* ----------- Controls ---------- */

	/** The label view. */
	private JLabel fLabel;

	/** The textfield view. */
	private JTextFieldWithPopUp fTextField;

	/** The <code>CardLayout</code> containing both views. */
	private CardLayout fCards;

	/** The textfield view's caret. */
	private DefaultCaret fCaret;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Create an empty </code>LabelTextFieldPanel</code> showing the label view
	 * initially.
	 */
	public LabelTextFieldPanel() {
		this("");
	}

	/**
	 * Create a </code>LabelTextFieldPanel</code> with the given value
	 * showing the label view initially.
	 *
	 * @param	<code>defaultValue</code> the default value.
	 */
	public LabelTextFieldPanel(String value) {
		this(value, "", true);
	}

	/**
	 * Create a </code>LabelTextFieldPanel</code> with the given values and behavior.
	 * 
	 * @param	<code>value</code> the value.
	 * @param	<code>defaultValue</code> the default value.
	 * @param	<code>setToDefault</code> if <code>true</code> the value will be 
	 * 			updated to the default value if the user clears the textfield, otherwise
	 * 			the default value will be only displayed in the textfield view, but the 
	 * 			value will be set to an empty string.
	 */
	public LabelTextFieldPanel(String value, String defaultValue, boolean setToDefault) {
		super();

		setDefault(defaultValue);
		setToDefault(setToDefault);
		setValue(value);

		initialize();
	}
	
	/////////////////////////////////////////////////////////////////////////////
	// CONTROLS
	/////////////////////////////////////////////////////////////////////////////

	/**
	 * Answer the <code>CardLayout</code>.
	 *
	 * @return	the layout.
	 */
	protected CardLayout getCards() {
		if (null == fCards) {
			fCards = new CardLayout();
		}

		return fCards;
	}

	/**
	 * Answer the caret used for the textfield.
	 *
	 * @return	the caret.
	 */
	protected DefaultCaret getCaret() {
		if (null == fCaret) {
			fCaret = new DefaultCaret();
		}

		return fCaret;
	}

	/**
	 * Answer the label view.
	 *
	 * @return	the label view.
	 */
	protected JLabel getLabel() {
		if (null == fLabel) {
			fLabel = new JLabel(fValue);

			fLabel.setToolTipText(getDefault());
			
			fLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					if (isFirstClick()) {
						getTextField().selectAll();
					} else {
						moveCaret(evt);
					}
					
					showTextField();
				}
			});
		}

		return fLabel;
	}

	/**
	 * Answer the textfield view.
	 *
	 * @return	the textfield view.
	 */
	protected JTextField getTextField() {
		if (null == fTextField) {
			fTextField = new JTextFieldWithPopUp(fValue);

			fTextField.setCaret(getCaret());

			fTextField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					updateValue();
					showLabel();
				}
			});

			fTextField.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent evt) {
					updateValue();
					showLabel();
				}
			});
		}

		return fTextField;
	}

	/////////////////////////////////////////////////////////////////////////////
	// JAVA BEAN BOUND PROPERTIES
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Set the value.
	 *
	 * Updates both views and fires a <code>PropertyChangeEvent</code> called 
	 * &quot;value&quot;.
	 * 
	 * @param	<code>value</code> the new value.
	 */
	public void setValue(String value) {
		//assert null != value;
		if (null == value)
			throw new IllegalArgumentException("value null");
		//

		String oldValue = getValue();

		if (value.equals(oldValue))
			return;

		fValue = value;

		if (!fValue.equals(getTextField().getText()))
			getTextField().setText(fValue);

		if (!fValue.equals(getLabel().getText()))
			getLabel().setText(fValue);

		if (getDefault().equals(fValue))
			beFirstClick();
			
		firePropertyChange("value", oldValue, value);
	}

	/**
	 * Set tooltip of the textfield view.
	 * 
	 * @param	<code>tip</code> the tooltip.
	 */	
    public void setToolTipText(String tip) {
		getTextField().setToolTipText(tip);
    }
    
	/* ----------- Accessors -------------------- */

	/**
	 * Answer the value.
	 *
	 * @return	the value.
	 */
	public String getValue() {
		return fValue;
	}

	/////////////////////////////////////////////////////////////////////////////
	// PUBLIC METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Set the background color of the textfield.
	 * 
	 * @param	<code>color</code> the background color.
	 */
	public void setTextFieldBackground(Color color) {
		getTextField().setBackground(color);
	}

	/**
	 * Show the label view.
	 */
	public void showLabel() {
		getCards().first(this);
	}

	/**
	 * Show the textfield view and set the focus on it.
	 */
	public void showTextField() {
		getCards().last(this);

		if (isFirstClick())
			getTextField().selectAll();			
			
		if (!getTextField().hasFocus()) {
			if (!getTextField().isRequestFocusEnabled())
				getTextField().setRequestFocusEnabled(true);

			getTextField().requestFocus();
		}
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
		setLayout(getCards());

		add("lbl", getLabel()); //non-I18N NLS
		add("tf", getTextField()); //non-I18N NLS

		getLabel().setFont(getTextField().getFont());
	}

	/**
	 * Moves textfield caret to approximate location where the user clicked on the
	 * label.
	 */
	protected void moveCaret(MouseEvent evt) {
		getCaret().mousePressed(evt);
	}

	/**
	 * Set the value.
	 * 
	 * Can be overridden to allow only certain values.
	 */
	protected void updateValue() {
		String text = getTextField().getText();
		String defaultVal = getDefault();
		
		if ("".equals(text)) { 			
			if (isSetToDefault()) {		
				setValue(getDefault());
			} else {
				setValue("");
				getLabel().setText(getDefault());
			}
		} else {
			setValue(text);
		}
											
		if (getValue().equals(defaultVal)) {
			beFirstClick();	
		} else {
			beNotFirstClick();
		}
	}

	/**
	 * Set the default value.
	 * 
	 * @param	<code>defaultValue</code> the default value.
	 */
	protected void setDefault(String defaultValue) {
		fDefault = defaultValue;
		
		beFirstClick();
	}

	/**
	 * Set the behavior regarding clearing the textfield view.
	 * 
	 * @param	<code>setToDefault</code> if <code>true</code> the value will be 
	 * 			updated to the default value if the user clears the textfield, otherwise
	 * 			the default value will be only displayed in the textfield view, but the 
	 * 			value will be set to an empty string.
	 */
	protected void setToDefault(boolean setToDefault) {
		fSetToDefault = setToDefault;
	}
	
	/**
	 * We're in <em>firstClick</em> state.
	 * 
	 * @see	#fFirstClick.
	 */
	protected void beFirstClick() {
		fFirstClick = true;
	}

	/**
	 * We're not in <em>firstClick</em> state.
	 * 
	 * @see	#fFirstClick.
	 */
	protected void beNotFirstClick() {
		fFirstClick = false;
	}
		
	/* ----------- Accessors -------------------- */

	/**
	 * Answer the default value.
	 *
	 * @return	the value.
	 */
	protected String getDefault() {
		return fDefault;
	}
	
	/**
	 * Answer if we're in <em>firstClick</em> state.
	 * 
	 * @see	#fFirstClick.
	 */
	protected boolean isFirstClick() {
		return fFirstClick;
	}
	
	/**
	 * Set the behavior regarding clearing the textfield view.
	 * 
	 * @see	#fSetToDefault.
	 */
	protected boolean isSetToDefault() {
		return fSetToDefault;
	}		
}
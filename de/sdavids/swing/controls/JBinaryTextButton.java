package de.sdavids.swing.controls;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import de.sdavids.util.DimensionUtils;

public class JBinaryTextButton extends JReturnButton {
	private String fInitialText;

	private String fAlternateText = "";

	private boolean fShowInitialText;

	private boolean fHasNotChanged;

	/////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	/////////////////////////////////////////////////////////////////////////////

	public JBinaryTextButton() {
		this("", "");
	}

	public JBinaryTextButton(String inital, String alternate) {
		super();

		setInitialText(inital);

		showText();

		hasChanged();

		setAlternateText(alternate);
	}

	/* ----------- Mutators --------------------- */

	public void setText(String text) {
		if (getText().equals(text))
			return;

		hasChanged();

		setInitialText(text);

		super.setText(getInitialText());

		adjustPreferedSize();
	}

	public void setAlternateText(String text) {
		if (getAlternateText().equals(text))
			return;

		hasChanged();

		String oldAlternate = getAlternateText();

		fAlternateText = (null == text) ? "" : text;

		adjustPreferedSize();

		firePropertyChange("alternateText", oldAlternate, text);
	}

	/* ----------- Accessors --------------------- */

	public String getAlternateText() {
		return fAlternateText;
	}

	public boolean isTextShown() {
		return fShowInitialText;
	}

	public boolean isAlternateTextShown() {
		return !isTextShown();
	}

	public void showText() {
		fShowInitialText = true;

		super.setText(getInitialText());
	}

	public void showAlternateText() {
		fShowInitialText = false;

		super.setText(getAlternateText());
	}
	
	public Dimension getPreferredSize() {
		Dimension result = super.getPreferredSize();

		return result;
	}

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	protected void setInitialText(String text) {
		fInitialText = (null == text) ? "" : text;
	}

	protected void hasChanged() {
		fHasNotChanged = false;
	}

	protected void adjustPreferedSize() {
		if (fHasNotChanged)
			return;

		fHasNotChanged = true;

		if (isTextShown()) {
			if ("".equals(getAlternateText())) {
				return;
			} else if ("".equals(getInitialText())) {
				showAlternateText();

				setPreferredSize(getPreferredSize());

				showText();
			} else {
				Dimension initialSize = getPreferredSize();

				showAlternateText();

				Dimension alternateSize = getPreferredSize();

				setPreferredSize(DimensionUtils.combine(initialSize, alternateSize));

				showText();
			}
		} else {
			if ("".equals(getInitialText())) {
				return;
			} else if ("".equals(getAlternateText())) {
				showText();

				setPreferredSize(getPreferredSize());

				showAlternateText();
			} else {
				Dimension alternateSize = getPreferredSize();

				showText();

				Dimension initialSize = getPreferredSize();

				setPreferredSize(DimensionUtils.combine(initialSize, alternateSize));

				showAlternateText();
			}
		}
	}

	/* ----------- Accessors --------------------- */

	protected String getInitialText() {
		return fInitialText;
	}

	protected void fireActionPerformed(ActionEvent event) {

		if (fShowInitialText) {
			showAlternateText();
		} else {
			showText();
		}

		adjustPreferedSize();

		super.fireActionPerformed(event);
	}
}
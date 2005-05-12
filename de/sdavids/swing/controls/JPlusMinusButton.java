package de.sdavids.swing.controls;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class JPlusMinusButton extends JButton {

	private boolean fPlusShown;
	
	public JPlusMinusButton() {
		this(true);
	}

	public JPlusMinusButton(boolean showPlus) {
		super();
		
		if (showPlus) {
			bePlus();
		} else {
			beMinus();
		}
		
		setMargin(new Insets(2, 2, 2, 2));
	}

	public void bePlus() {
		fPlusShown = true;
		setText("+");
	}
	
	public void beMinus() {
		fPlusShown = false;
		setText("-");
	}
	
    protected void fireActionPerformed(ActionEvent event) {
		if (fPlusShown) {
			beMinus();
		} else {
			bePlus();
		}
				
		super.fireActionPerformed(event);
    }
}

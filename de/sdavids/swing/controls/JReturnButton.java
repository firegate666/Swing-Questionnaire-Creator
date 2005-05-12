package de.sdavids.swing.controls;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

import de.sdavids.util.StringUtils;

public class JReturnButton extends JButton {

    /**
     * Constructor for JReturnButton
     */
    public JReturnButton() {
        super();
        initialize();
    }

    /**
     * Constructor for JReturnButton
     */
    public JReturnButton(Icon arg0) {
        super(arg0);
        initialize();        
    }

    /**
     * Constructor for JReturnButton
     */
    public JReturnButton(String arg0) {
        super(arg0);
        initialize();        
    }

    /**
     * Constructor for JReturnButton
     */
    public JReturnButton(Action arg0) {
        super(arg0);
        initialize();        
    }

    /**
     * Constructor for JReturnButton
     */
    public JReturnButton(String arg0, Icon arg1) {
        super(arg0, arg1);
        initialize();        
    }

	protected void initialize() {
		addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				switch (evt.getKeyCode()) {
					case KeyEvent.VK_ENTER:
						doClick();
						break;
					case KeyEvent.VK_SPACE:  
						doClick();
						break;
					default:
				}
			}
		});
	}
	
	//JButton does not gray out HTML-Text
	private String fHTMLText;
	private String fHTMLTextGrayed;
	
	protected static final String HTML_BLACK = "<body text=black>";
	protected static final String HTML_GRAY  = "<body text=gray>";
	
	public void setText(String text) {

		if (-1 != text.indexOf(HTML_BLACK)) {
			fHTMLText = text;
			fHTMLTextGrayed = new String(text);
			fHTMLTextGrayed = StringUtils.replace(fHTMLTextGrayed, HTML_BLACK, HTML_GRAY);
		} else {
			fHTMLTextGrayed = null;
		}
		
		super.setText(text);
	}
	
	public void setEnabled(boolean enabled) {
		if (enabled == isEnabled())
			return;
		
		if (null != fHTMLTextGrayed) {
			if (enabled) {
				super.setText(fHTMLText);
			} else {		
				super.setText(fHTMLTextGrayed);
			}
		}
				
		super.setEnabled(enabled);
	}
}


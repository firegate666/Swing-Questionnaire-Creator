package de.sep.model.answertype;

import java.awt.Component;

import de.mb.util.StringList;
import de.sdavids.swing.controls.LabelTextFieldPanel;

/*
 * SEP-Projekt WS 2001-2002 -- Questionaire
 *
 * Projektteilnehmer:
 * Marco Behnke <marco@firegate.de>
 * Sebastian Davids <sdavids@gmx.de>
 * Martin Koose <martin@koose-hh.de>
 *
 * Projektbegleitung:
 * Prof. Dr. Bernd Kahlbrandt <Bernd.Kahlbrandt@informatik.fh-hamburg.de>
 *
 * Copyright (c)2001 Behnke, Davids & Koose. Alle Rechte vorbehalten.
 * ===========================================================================
 */

public class CompletionOpen extends AbstractAnswerType {

	private StringList fGivenOptions = new StringList();

    //////////////////////////////////////////////////////////////////////////////
    // IMPLEMENT IANSWERTYPE
    //////////////////////////////////////////////////////////////////////////////

    /* ----------- Accessors -------------------- */

    /**
     * Answer <code>AnswerType.COMPLETION_OPEN</code>.
     *
     * @return	the type.
     */
    public AnswerType getType() {
        return AnswerType.COMPLETION_OPEN;
    }
    
    public StringList getGivenOptions() {
        return fGivenOptions;
    }
   
	public void setGivenOptions(Component[] c){
		Component[] oldOptions = c;
		Component[] options = c;
		getGivenOptions().removeAll();
		for (int i = 0; i < c.length; i++) {
			getGivenOptions().add(((LabelTextFieldPanel) c[i]).getValue());
		}	
		 firePropertyChange("options", oldOptions, options);
	}

}
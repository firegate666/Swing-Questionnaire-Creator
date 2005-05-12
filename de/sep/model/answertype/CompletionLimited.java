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

public class CompletionLimited extends AbstractAnswerType {


	private StringList fGivenOptions;

	private StringList fOptions;

    //////////////////////////////////////////////////////////////////////////////
    // IMPLEMENT IANSWERTYPE
    //////////////////////////////////////////////////////////////////////////////

    /* ----------- Accessors -------------------- */

    /**
     * Answer <code>AnswerType.COMPLETION_LIMITED</code>.
     *
     * @return	the type.
     */
    public AnswerType getType() {
        return AnswerType.COMPLETION_LIMITED;
    }
	
	//in vorgaben umbennen
    public StringList getGivenOptions() {
        if (null == fGivenOptions){
        	StringList fGivenOptions = new StringList();
        }
        return fGivenOptions;
    }
    
    public StringList getOptions() {
     if (null == fOptions){
        	StringList fOptions = new StringList();
        }
        return fOptions;
    }

	public void setOptions(Component[] c){
		if (c.length == 0){fOptions=new StringList(); return;}	
		for (int i = 0; i < c.length; i++) {
			getOptions().add(((LabelTextFieldPanel)c[i]).getValue());	
		}	
	}

	public void setGivenOptions(Component[] c){
		if (c.length == 0){fGivenOptions=new StringList(); return;}	
		for (int i = 0; i < c.length; i++) {
			getGivenOptions().add(((LabelTextFieldPanel)c[i]).getValue());		
		}	
	}


}
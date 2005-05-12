/*
 * (c) Copyright 2001 MyCorporation.
 * All Rights Reserved.
 */
package de.sep.ui.dialog;
import java.awt.Frame;

import javax.swing.JDialog;

import de.sep.model.questionaire.IQuestionaire;
import de.sep.ui.Questionaire;

public class ExitProgramDialog extends JDialog {
    
    public ExitProgramDialog(Frame owner, IQuestionaire app) {
    	this.setModal(true);
    	this.setTitle(((Questionaire)app).getConfig("dlg.closeConfirmQuestionaire.title"));
    
    }
    
}
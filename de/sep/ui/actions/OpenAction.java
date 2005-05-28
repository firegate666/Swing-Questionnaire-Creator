package de.sep.ui.actions;

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

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;

import de.sep.model.questionaire.IQuestionaire;
import de.sep.ui.Questionaire;

/**
 * A <code>OpenAction<code> is invoked if the user wants to open an existing
 * questionaire.
 */
public class OpenAction extends AbstractQuestionaireAction {

    //////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    //////////////////////////////////////////////////////////////////////////////

    /**
     * Defines an <code>OpenAction<code> object with the specified name and a
     * reference to the application.
     * 
     * @param	<code>name<code> the name.
     * @param	<code>app<code> a reference to the application. 
     */
    public OpenAction(String name, Questionaire app) {
        super(name, app);
    }

    //////////////////////////////////////////////////////////////////////////////
    // IMPLEMENT ACTIONLISTENER
    //////////////////////////////////////////////////////////////////////////////

    /**
     * Invoked when an <code>OpenAction<code> occurs.
     * 
     * @param	<code>evt<code> the <code>ActionEvent</code>.
     */
    public void actionPerformed(ActionEvent evt) {
		Questionaire quest = ((Questionaire) getApp());
		quest.setCurrentDoc(((Questionaire)getApp()).getFactory().createQuestionaire("dummy") );

        String filePath = getConfig("file_save_path");

        //filePath += "dummy";
        //filePath += getConfig("file_extension");

        System.out.println(filePath);

		JFileChooser jfc = new JFileChooser(filePath);
		jfc.setDialogTitle("Fragebogen öffnen...");
		jfc.showOpenDialog((Questionaire)getApp() );

        File myFile = jfc.getSelectedFile() ;

        try {
            FileInputStream fis = new FileInputStream(myFile);

            // Read object with ObjectInputStream
            ObjectInputStream obj_in = new ObjectInputStream(fis);

            // Read an object
            Object obj = obj_in.readObject();

            
            quest.setCurrentDoc((IQuestionaire) obj);
            quest.validate();

			

            fis.close();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
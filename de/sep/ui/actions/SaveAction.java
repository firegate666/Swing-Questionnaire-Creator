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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import de.sep.model.questionaire.IQuestionaire;
import de.sep.ui.Questionaire;

/**
 * A <code>SaveAction<code> is invoked if the user changes the questionaire.
 */
public class SaveAction extends AbstractQuestionaireAction {

    //////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    //////////////////////////////////////////////////////////////////////////////

    /**
     * Defines a <code>SaveAction<code> object with the specified name and a
     * reference to the application.
     * 
     * @param	<code>name<code> the name.
     * @param	<code>app<code> a reference to the application. 
     */
    public SaveAction(String name, Questionaire app) {
        super(name, app);
    }

    /////////////////////////////////////////////////////////////////////////////
    // PROTECTED METHODS
    /////////////////////////////////////////////////////////////////////////////

    /* ----------- Mutators --------------------- */

    protected void save() {
        IQuestionaire quest = ((Questionaire) getApp()).getCurrentDoc();

        if (quest.isNull())
            return;

        String filePath = getConfig("file_save_path");

        filePath += quest.getTitle().replace(' ', '_');
        filePath += getConfig("file_extension");

        System.out.println(filePath);

        File myFile = new File(filePath);

        try {
            System.out.println(myFile.createNewFile());
            FileOutputStream fos = new FileOutputStream(myFile);

            // Write object with ObjectOutputStream
            ObjectOutputStream obj_out = new ObjectOutputStream(fos);

            // Write object out to disk
            obj_out.writeObject(quest);

            fos.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //////////////////////////////////////////////////////////////////////////////
    // IMPLEMENT ACTIONLISTENER
    //////////////////////////////////////////////////////////////////////////////

    /**
     * Invoked when an <code>SaveAction<code> occurs.
     * 
     * @param	<code>evt<code> the <code>ActionEvent</code>.
     */
    public void actionPerformed(ActionEvent evt) {
        save();
    }
}
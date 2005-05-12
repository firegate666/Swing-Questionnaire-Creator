package de.sep.dnd;

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

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import de.sep.dnd.*;
import de.sep.ui.question.*;

public class QuestionTranferable implements Transferable {
	public static final DataFlavor QUESTION_FLAVOR =
		new DataFlavor(String.class, "Question");

	private static DataFlavor[] fFlavors = { QUESTION_FLAVOR };

	public DataFlavor[] getTransferDataFlavors() {
		return fFlavors;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return QUESTION_FLAVOR.equals(flavor);
	}

	public Object getTransferData(DataFlavor flavor)
		throws UnsupportedFlavorException, IOException {

		if (!isDataFlavorSupported(flavor))
			throw new UnsupportedFlavorException(flavor);

		return "question";
	}
}
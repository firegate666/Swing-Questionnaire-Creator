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

import de.sep.model.answertype.AnswerType;

public class AnswerTypeTranferable implements Transferable {
	public static final DataFlavor ANSWERTYPE_FLAVOR =
		new DataFlavor(AnswerType.class, "newAnswerType");

	private static DataFlavor[] fFlavors = { ANSWERTYPE_FLAVOR };

	private String fAnswerType;

	public AnswerTypeTranferable(String answerType) {
		fAnswerType = answerType;
	}

	public DataFlavor[] getTransferDataFlavors() {
		return fFlavors;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return ANSWERTYPE_FLAVOR.equals(flavor);
	}

	public Object getTransferData(DataFlavor flavor)
		throws UnsupportedFlavorException, IOException {

		if (!isDataFlavorSupported(flavor))
			throw new UnsupportedFlavorException(flavor);

		return fAnswerType;
	}
}
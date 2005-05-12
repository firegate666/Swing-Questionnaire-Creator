package de.mb.swing.actions;
import java.awt.AWTEvent;
import java.awt.Component;

import javax.swing.JOptionPane;

/**
 * <p>Überschrift: Compiler & Interpreter Praktikum</p>
 * <p>Beschreibung: Compiler für Subset of Pascal</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Organisation: </p>
 * @author unbekannt
 * @version 1.0
 */

public class CloseAction  {

	private Object _app;
	
	public Object getApp() {
		return this._app;
	}

	private void setApp(Object application) {
		this._app = application;
	}

	private void action(AWTEvent e) {
		int test =
			JOptionPane.showConfirmDialog(
				(Component) e.getSource(),
				"Wollen Sie das Programm wirklich beenden?",
				"Programm beenden",
				JOptionPane.YES_NO_OPTION);
		if (test == 0)
			System.exit(0);
	}

	public CloseAction(AWTEvent e) {
		action(e);
	}

	public CloseAction(AWTEvent e, Object application) {
		this(e);
		this.setApp(application);
	}

}
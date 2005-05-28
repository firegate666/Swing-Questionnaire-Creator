package prototype.util.database;/* * SEP-Projekt WS 2001-2002 -- Questionaire *  * Projektteilnehmer: * Marco Behnke <marco@firegate.de> * Sebastian Davids <sdavids@gmx.de> * Martin Koose <martin@koose-hh.de> *  * Projektbegleitung:  * Prof. Dr. Bernd Kahlbrandt <Bernd.Kahlbrandt@informatik.fh-hamburg.de> *  * Copyright (c)2001 Behnke, Davids & Koose. Alle Rechte vorbehalten. * =========================================================================== */import de.mb.database.oracle.*;import de.sep.logic.*;import de.sep.logic.database.*;import javax.swing.JOptionPane;public class RunMe {	public static void main(String[] args) throws Exception {		System.out.println("Das Programm l�uft.");		// Factoryinitialisierung		OracleDBConnectionFactory OraConFac = new OracleDBConnectionFactory();		// Passwort f�r die Datenbankverbindung abfragen		String myDbIp = "141.22.10.44";		String myDbName = "sw02";		String myDbPort = "1521";		String myUser = "behnke_m";		JOptionPane inputBox = new JOptionPane();		String myPassword =			inputBox.showInputDialog(				"Bitte geben Sie das Passwort f�r die\nDatenbankverbindung ein:");		// Verbindung zur Datenbank aufbauen		QuestionaireSQLQueries qsql =			new QuestionaireSQLQueries(				OraConFac.createConnection(					myUser,					myPassword,					myDbIp,					myDbPort,					myDbName));		// Applikation initialisieren		qsql.initializeApplication();		// Testobject erstellen		//de.sep.ui.Questionaire test = new de.sep.ui.Questionaire();//SD-01-12-03: Not used		// L�uft noch nicht		//qsql.storeQuestionaire(test,"Fragebogen 1",0);		//de.sep.ui.Questionaire test2;//SD-01-12-03: Not used		qsql.close();		System.exit(0);	}}
package de.sep.logic.database;

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

import java.sql.SQLException;
import java.util.Vector;

import de.mb.database.oracle.*;
import de.sep.ui.Questionaire;

public class QuestionaireSQLQueries {

    private static String MAIN_TABLE = "Questionaire";

    private OracleSQLConnection fsqlCon;

    public void storeQuestionaire(
        Questionaire questionaire,
        String title,
        int status)
        throws SQLException {

        Vector attributes = new Vector();
        attributes.add("ID");
        attributes.add("OBJECT");
        attributes.add("NAME");
        attributes.add("STATUS");

        Vector values = new Vector();
        values.add("q_seq.nextVal");
        values.add(questionaire);
        values.add(title);
        values.add(new String("" + status));

        fsqlCon.insertInto(MAIN_TABLE, attributes, values);
    }

    /*  public SQLAnswerTable getAnswers(int questionaireID) {
    
    }
    
    /*  public SQLAnswerTable getAnswers(String questionaireTitle) {
    }
    
    public int getIDfromName(String questionaireTitle) {
    }*/

    public QuestionaireSQLQueries(OracleSQLConnection oraSqlConn) {
        fsqlCon = oraSqlConn;
    }

    public void initializeApplication() throws SQLException {
        // Tabellenname
        String tableName = "Questionaire";

        // Tabellenattribute
        Vector attributes = new Vector();
        attributes.add("ID int not null");
        attributes.add("NAME varchar(40) not null");
        attributes.add("OBJECT blob not null");
        attributes.add("STATUS char(1) not null");
        attributes.add("AUTHOR varchar(40)");
        attributes.add("PASSWORD varchar(8)");

        // Tabellenconstraints
        Vector constraints = new Vector();
        constraints.add("constraint q_pk primary key(ID)");
        constraints.add("constraint q_uk unique(NAME)");
        constraints.add("constraint q_status check(STATUS in('0','1'))");

        fsqlCon.createTable(tableName, attributes, constraints);
        fsqlCon.executeSQL("create sequence q_seq");
        fsqlCon.dropTable("Questionaire");
        fsqlCon.executeSQL("drop sequence q_seq");
    }

    public void close() throws SQLException {
        fsqlCon.close();
    }

}
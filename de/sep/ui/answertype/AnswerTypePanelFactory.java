package de.sep.ui.answertype;


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
import de.sep.model.answertype.AnswerType;
import de.sep.model.answertype.IAnswerType;
import de.sep.model.answertype.IllegalAnswerTypeException;
import de.sep.ui.Questionaire;

/**
 * Factory for <code>AnswerTypePanel</code>s.
 *
 * Utilizes the <code>Factory Method</code> pattern.
 *
 * Reference:<br>
 * Gamma, Erich, Richard Helm, Ralph Johnson, and John Vlissides.
 * 1995. Design Patterns. Addison-Wesley. Reading,Mass.
 */
public class AnswerTypePanelFactory {

    //////////////////////////////////////////////////////////////////////////////
    // CLASS METHODS

    //////////////////////////////////////////////////////////////////////////////

    /* ----------- Factory Methods --------------------- */

    /**
     * Create a new <code>AnswerTypePanel</code> view for the given model.
     *
     * Factory method.
     *
     * @param	<code>type</code> the model (type).
     */
    public static AbstractAnswerTypePanel create(IAnswerType type, Questionaire app)
        throws IllegalAnswerTypeException {

        int value = type.getType().getValue();

		AbstractAnswerTypePanel result;

        if (AnswerType.COMPLETION_LIMITED.getValue() == value) {
            result = new CompletionLimitedPanel(type, app.getConfig());
        } else if (AnswerType.COMPLETION_OPEN.getValue() == value) {
            result = new CompletionOpenPanel(type, app.getConfig());
        } else if (AnswerType.MATCH_MULTIPLE.getValue() == value) {
            result = new MatchMultiplePanel(type, app.getConfig());
        } else if (AnswerType.MATCH_SINGLE.getValue() == value) {
            result = new MatchSinglePanel(type, app.getConfig());
        } else if (AnswerType.OPEN_QUALIFIED.getValue() == value) {
            result = new OpenQualifiedPanel(type, app.getConfig());
        } else if (AnswerType.OPEN_QUANTIFIED.getValue() == value) {
            result = new OpenQuantifiedPanel(type, app.getConfig());
        } else if (AnswerType.OPTION_BINARY.getValue() == value) {
            result = new OptionBinaryPanel(type, app.getConfig());
        } else if (AnswerType.OPTION_LIMITED.getValue() == value) {
            result = new OptionLimitedPanel(type, app.getConfig());
        } else if (AnswerType.OPTION_VARIABLE.getValue() == value) {
            result = new OptionVariablePanel(type, app.getConfig());
        } else if (AnswerType.ORDER.getValue() == value) {
            result = new OrderPanel(type, app.getConfig());
        } else if (AnswerType.SCALE_CALCULATED.getValue() == value) {
            result = new ScaleCalculatedPanel(type, app.getConfig());
        } else if (AnswerType.SCALE_LABLED.getValue() == value) {
            result = new ScaleLabledPanel(type, app.getConfig());
        } else {
            throw new IllegalAnswerTypeException("unknown type: " + type);
        }
        
        return result;
    }
}
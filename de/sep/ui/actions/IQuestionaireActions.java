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
package de.sep.ui.actions;

public interface IQuestionaireActions {
	String ACTION_INSERT_ANSWERTYPE = "act.dropNewAnswerType";
	String ACTION_INSERT_QUESTION = "act.dropNewQuestion";
	String ACTION_HTML_PREVIEW = "act.htmlPreview";
	String ACTION_SHOW_ALL_ANSWERTYPES_COLLAPSED = "act.showAllAnswerTypesCollapsed";
	String ACTION_SHOW_ALL_ANSWERTYPES_NOT_COLLAPSED = "act.showAllAnswerTypesNotCollapsed";
	String ACTION_APPEND_ANSWERTYPE = "act.newAnswerType";
	String ACTION_AUTO_REORDER_QUESTIONS = "act.questionaireAutoReorder";
	String ACTION_NEW_QUESTIONAIRE = "act.newQuestionaire";
	String ACTION_APPEND_QUESTION = "act.newQuestion";
	String ACTION_MOVE_QUESTION_UP = "act.moveUpQuestion";
	String ACTION_MOVE_QUESTION_DOWN = "act.moveDownQuestion";
	String ACTION_SCROLL_TOP = "act.scrollTop";
	String ACTION_DELETE_QUESTION = "act.deleteQuestion";
	String ACTION_FLIP_ORIENTATION = "act.flipOrientation";
	String ACTION_RENAME = "act.rename";
	String ACTION_CLOSE = "act.close";
	String ACTION_SAVE = "act.save";
	String ACTION_OPEN = "act.open";
	String ACTION_SCROLL_BOTTOM = "act.scrollBottom";
	String ACTION_KEY_BINDINGS = "act.keyBindings";
	String ACTION_INFO = "act.info";
	String ACTION_SHOW_MAIN_PALETTE = "act.mainPalette";
	String ACTION_EXIT = "act.exit";
	String ACTION_SNAPSHOT_COPY = "act.snapshotCopy";
	String ACTION_ABANDON_CHANGES = "act.abandonChanges";
	String ACTION_MAKE_MILESTONE = "act.makeMilestone";
	String ACTION_REVERT_TO_MILESTONE = "act.revertToMilestone";
	String ACTION_CHANGE_AUTHOR = "act.changeAuthor";
	String ACTION_CUT = "act.cut";
	String ACTION_COPY = "act.copy";
	String ACTION_PASTE = "act.paste";
	String ACTION_SELECT_ALL = "act.selectAll";
}

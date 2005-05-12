package de.sep.ui;/* * SEP-Projekt WS 2001-2002 -- Questionaire * * Projektteilnehmer: * Marco Behnke <marco@firegate.de> * Sebastian Davids <sdavids@gmx.de> * Martin Koose <martin@koose-hh.de> * * Projektbegleitung: * Prof. Dr. Bernd Kahlbrandt <Bernd.Kahlbrandt@informatik.fh-hamburg.de> * * Copyright (c)2001 Behnke, Davids & Koose. Alle Rechte vorbehalten. * =========================================================================== */import java.awt.BorderLayout;import java.awt.Color;import java.awt.Dimension;import java.awt.Frame;import java.awt.Rectangle;import java.awt.Toolkit;import java.awt.event.ActionEvent;import java.awt.event.KeyAdapter;import java.awt.event.KeyEvent;import java.awt.event.WindowAdapter;import java.awt.event.WindowEvent;import java.beans.PropertyChangeEvent;import java.beans.PropertyChangeListener;import java.util.MissingResourceException;import java.util.ResourceBundle;import javax.swing.Action;import javax.swing.JMenu;import javax.swing.JMenuBar;import javax.swing.JPanel;import javax.swing.JScrollPane;import javax.swing.JTextField;import javax.swing.UIManager;import javax.swing.WindowConstants;import javax.swing.text.DefaultEditorKit;import com.incors.plaf.kunststoff.KunststoffLookAndFeel;import de.mb.swing.JDefaultFrame;import de.sdavids.interfaces.IApplication;import de.sdavids.swing.CardPanel;import de.sdavids.swing.OrientationConstants;import de.sdavids.swing.SwingCreator;import de.sdavids.swing.actions.ActionImpl;import de.sdavids.swing.actions.ActionRepository;import de.sep.logic.QuestionaireFactory2;import de.sep.logic.web.html.QuestionaireHTMLFactory;import de.sep.model.questionaire.IQuestionaire;import de.sep.ui.actions.IQuestionaireActions;import de.sep.ui.html.HTMLPreviewPanel;import de.sep.ui.panel.RevertToMilestonePanel;import de.sep.ui.questionaire.QuestionairePanel;/** * Main application. */public class Questionaire	extends JDefaultFrame//JFrame	implements IApplication, IQuestionaireActions {	//////////////////////////////////////////////////////////////////////////////	// ATTRIBUTES	//////////////////////////////////////////////////////////////////////////////	/* ----------- Instance Attributes ---------- */	private ResourceBundle fResources;	private QuestionaireFactory2 fQuestionaireFactory;	private IQuestionaire fQuestionaire;	private QuestionaireHTMLFactory fHTMLFactory;	private ActionRepository fActionRepository;	/* ----------- Menus ---------- */	private JMenuBar fMainMenu;	private JMenu fProgramMenu;	private JMenu fQuestionaireMenu;	private JMenu fQuestionsMenu;	private JMenu fAnswerTypesMenu;	private JMenu fViewMenu;	private JMenu fPaletteMenu;	private JMenu fHelpMenu;	/* ----------- Controls ---------- */	private JPanel fRootPanel;	private CardPanel fContentPanel;	private QuestionairePanel fQuestionairePnl;	private QuestionaireStatusBar fStatusBar;	/* ----------- Views ---------- */	private HTMLPreviewPanel fHTMLPreviewPane;	private RevertToMilestonePanel fRevertToMilestonePane;	private JScrollPane fDocumentPane;	//////////////////////////////////////////////////////////////////////////////	// CONSTRUCTORS	//////////////////////////////////////////////////////////////////////////////	/**	 * Create a new Questionaire <code>JFrame</code>.	 */	public Questionaire() {		super();		initialize();	}	/////////////////////////////////////////////////////////////////////////////	// CONTROLS	/////////////////////////////////////////////////////////////////////////////	public QuestionaireStatusBar getStatusBar() {		if (null == fStatusBar) {			fStatusBar =				new QuestionaireStatusBar(actionForName(ACTION_HTML_PREVIEW));			fStatusBar.setVisible(false);		}		return fStatusBar;	}	public JPanel getRootPanel() {		if (null == fRootPanel) {			fRootPanel = new JPanel(new BorderLayout());		}		return fRootPanel;	}	public QuestionairePanel getQuestionairePanel() {		return fQuestionairePnl;	}	protected CardPanel getContentPanel() {		if (null == fContentPanel) {			fContentPanel = new CardPanel();			fContentPanel.add(getDocumentPane());			fContentPanel.add(getRevertToMilestonePane());			fContentPanel.add(getHTMLPreviewPane());			getRootPanel().add(fContentPanel, BorderLayout.CENTER);		}		return fContentPanel;	}	public boolean isDocumentPaneVisible() {		return getDocumentPane().isVisible();	}	public boolean isHTMLPaneVisible() {		return getHTMLPreviewPane().isVisible();	}	public boolean isRevertToMilestonePaneVisible() {		return getRevertToMilestonePane().isVisible();	}	public void flipRevertToMilestonePaneOrientation() {		getRevertToMilestonePane().flipOrientation();	}	public void showRevertToMilestonePaneHTMLPreview() {		getRevertToMilestonePane().showHTMLPreview();	}	public void hideRevertToMilestonePaneHTMLPreview() {		getRevertToMilestonePane().hideHTMLPreview();	}	public void showHTMLPreviewPane() {		//extrem hohe Kopplung		getHTMLPreviewPane().setVisible(true);		getRevertToMilestonePane().setVisible(false);		getDocumentPane().setVisible(false);		getContentPanel().showCard(getHTMLPreviewPane());		performAction(			ACTION_SHOW_MAIN_PALETTE,			getCurrentDoc(),			getCurrentDoc().hashCode(),			"hide");		getMenuQuestions().setVisible(false);		getMenuAnswerTypes().setVisible(false);		getMenuPalette().setVisible(false);		disableAction(ACTION_RENAME);		disableAction(ACTION_CHANGE_AUTHOR);		//	}	public void showRevertToMilestonePane() {		//extrem hohe Kopplung		getHTMLPreviewPane().setVisible(false);		getRevertToMilestonePane().setVisible(true);		getDocumentPane().setVisible(false);		getContentPanel().showCard(getRevertToMilestonePane());		performAction(			ACTION_SHOW_MAIN_PALETTE,			getCurrentDoc(),			getCurrentDoc().hashCode(),			"hide");		getMenuQuestions().setVisible(false);		getMenuAnswerTypes().setVisible(false);		getMenuPalette().setVisible(false);		disableAction(ACTION_RENAME);		disableAction(ACTION_CHANGE_AUTHOR);		//	}	public void showDocumentPane() {		//extrem hohe Kopplung		getRevertToMilestonePane().setVisible(false);		getHTMLPreviewPane().setVisible(false);		getDocumentPane().setVisible(true);		getContentPanel().showCard(getDocumentPane());		performAction(			ACTION_SHOW_MAIN_PALETTE,			getCurrentDoc(),			getCurrentDoc().hashCode(),			"show");		getMenuQuestions().setVisible(true);		getMenuAnswerTypes().setVisible(true);		getMenuPalette().setVisible(true);		enableAction(ACTION_RENAME);		enableAction(ACTION_CHANGE_AUTHOR);		enableAction(ACTION_REVERT_TO_MILESTONE);		disableAction(ACTION_FLIP_ORIENTATION);		//	}	protected JScrollPane getDocumentPane() {		if (null == fDocumentPane) {			fDocumentPane = new JScrollPane();		}		return fDocumentPane;	}	/**	 * Set the revert to milestone view on the given questionaire.	 */	protected RevertToMilestonePanel getRevertToMilestonePane() {		if (null == fRevertToMilestonePane) {			fRevertToMilestonePane =				new RevertToMilestonePanel(					getCurrentDoc(),					this,					OrientationConstants.NORTH_SOUTH);		}		return fRevertToMilestonePane;	}	/**	 * Set the HTML view on the given questionaire.	 */	protected HTMLPreviewPanel getHTMLPreviewPane() {		if (null == fHTMLPreviewPane)			fHTMLPreviewPane =				new HTMLPreviewPanel(getCurrentDoc(), getHTMLFactory());		return fHTMLPreviewPane;	}	/////////////////////////////////////////////////////////////////////////////	// MENUS	/////////////////////////////////////////////////////////////////////////////	public JMenuBar getJMenuBar() {		if (null == fMainMenu) {			fMainMenu = new JMenuBar();			fMainMenu.add(getMenuProgram());			fMainMenu.add(getMenuQuestionaire());			fMainMenu.add(getMenuQuestions());			fMainMenu.add(getMenuAnswerTypes());			fMainMenu.add(getMenuView());			fMainMenu.add(getMenuPalette());			fMainMenu.add(getMenuHelp());			//fMainMenu.setHelpMenu(getMenuHelp());//not implemented in JDK 1.3.1		}		return fMainMenu;	}	public JMenu getMenuProgram() {		if (null == fProgramMenu) {			ActionImpl[] actions = { actionForName(ACTION_EXIT)};			fProgramMenu =				SwingCreator.newJMenu("mnu.program", getConfig(), actions);		}		return fProgramMenu;	}	public JMenu getMenuQuestionaire() {		if (null == fHelpMenu) {			ActionImpl[] actions =				{					actionForName(ACTION_NEW_QUESTIONAIRE),					actionForName(ACTION_OPEN),					actionForName(ACTION_SAVE),					actionForName(ACTION_CLOSE),					null,					actionForName(ACTION_RENAME),					actionForName(ACTION_CHANGE_AUTHOR),					null,					actionForName(ACTION_SNAPSHOT_COPY),					actionForName(ACTION_MAKE_MILESTONE),					actionForName(ACTION_REVERT_TO_MILESTONE),					null,					actionForName(ACTION_ABANDON_CHANGES)};			fQuestionaireMenu =				SwingCreator.newJMenu("mnu.questionaire", getConfig(), actions);		}		return fQuestionaireMenu;	}	public JMenu getMenuView() {		if (null == fViewMenu) {			ActionImpl[] actions =				{					actionForName(ACTION_HTML_PREVIEW),					null,					actionForName(ACTION_FLIP_ORIENTATION)};			fViewMenu = SwingCreator.newJMenu("mnu.view", getConfig(), actions);			fViewMenu.setVisible(false);		}		return fViewMenu;	}	public JMenu getMenuPalette() {		if (null == fPaletteMenu) {			ActionImpl[] actions = { actionForName(ACTION_SHOW_MAIN_PALETTE)};			fPaletteMenu =				SwingCreator.newJMenu("mnu.palette", getConfig(), actions);		}		return fPaletteMenu;	}	public JMenu getMenuQuestions() {		if (null == fQuestionsMenu) {			ActionImpl[] actions =				{					actionForName(ACTION_APPEND_QUESTION),					null,					actionForName(ACTION_AUTO_REORDER_QUESTIONS)};			fQuestionsMenu =				SwingCreator.newJMenu("mnu.questions", getConfig(), actions);		}		return fQuestionsMenu;	}	public JMenu getMenuAnswerTypes() {		if (null == fAnswerTypesMenu) {			ActionImpl[] actions =				{					actionForName(ACTION_SHOW_ALL_ANSWERTYPES_COLLAPSED),					actionForName(ACTION_SHOW_ALL_ANSWERTYPES_NOT_COLLAPSED)};			fAnswerTypesMenu =				SwingCreator.newJMenu("mnu.answertypes", getConfig(), actions);		}		return fAnswerTypesMenu;	}	public JMenu getMenuHelp() {		if (null == fHelpMenu) {			ActionImpl[] actions =				{					actionForName(ACTION_KEY_BINDINGS),					null,					actionForName(ACTION_INFO)};			fHelpMenu = SwingCreator.newJMenu("mnu.help", getConfig(), actions);		}		return fHelpMenu;	}	/////////////////////////////////////////////////////////////////////////////	// ACTIONS	/////////////////////////////////////////////////////////////////////////////	protected void initializeTextComponentActions() {		Action copy = null;		Action cut = null;		Action paste = null;		Action selectAll = null;		Action[] actions = new JTextField().getActions();		Action action;		String name;		for (int i = (actions.length - 1); i >= 0; i--) {			action = actions[i];			name = (String) action.getValue(action.NAME);			if (DefaultEditorKit.selectAllAction.equals(name)) {				selectAll = action;				selectAll.putValue(					Action.ACCELERATOR_KEY,					actionForName(ACTION_SELECT_ALL).getAccelerator());				selectAll.putValue(					Action.LONG_DESCRIPTION,					actionForName(ACTION_SELECT_ALL).getLongDescription());			} else if (DefaultEditorKit.cutAction.equals(name)) {				cut = action;				cut.putValue(					Action.ACCELERATOR_KEY,					actionForName(ACTION_CUT).getAccelerator());				cut.putValue(					Action.LONG_DESCRIPTION,					actionForName(ACTION_CUT).getLongDescription());			} else if (DefaultEditorKit.copyAction.equals(name)) {				copy = action;				copy.putValue(					Action.ACCELERATOR_KEY,					actionForName(ACTION_COPY).getAccelerator());				copy.putValue(					Action.LONG_DESCRIPTION,					actionForName(ACTION_COPY).getLongDescription());			} else if (DefaultEditorKit.pasteAction.equals(name)) {				paste = action;				paste.putValue(					Action.ACCELERATOR_KEY,					actionForName(ACTION_PASTE).getAccelerator());				paste.putValue(					Action.LONG_DESCRIPTION,					actionForName(ACTION_PASTE).getLongDescription());			}			if ((null != selectAll)				&& (null != cut)				&& (null != copy)				&& (null != paste))				break;		}	}	//////////////////////////////////////////////////////////////////////////////	// PUBLIC INTERFACE	//////////////////////////////////////////////////////////////////////////////	/* ----------- Mutators --------------------- */	public void setCurrentDoc(IQuestionaire questionaire) {		fQuestionaire = questionaire;		boolean isNotNull = (!fQuestionaire.isNull());		if (isNotNull) {			getCurrentDoc()				.addPropertyChangeListener(new PropertyChangeListener() {				public void propertyChange(PropertyChangeEvent evt) {					String propName = evt.getPropertyName();					IQuestionaire doc = getCurrentDoc();					if ("title".equals(propName)) {						setTitle(doc.getTitle());					} else if (						"questionsInsert".equals(propName)) { //non-I18N-NLS						performAction(							ACTION_SHOW_MAIN_PALETTE,							getCurrentDoc(),							getCurrentDoc().hashCode(),							(doc.isNotEmpty())								? "showAnswerTypes"								: "hideAnswerTypes");					} else if (						"questionsAdd".equals(propName)) { //non-I18N-NLS						performAction(							ACTION_SHOW_MAIN_PALETTE,							getCurrentDoc(),							getCurrentDoc().hashCode(),							(doc.isNotEmpty())								? "showAnswerTypes"								: "hideAnswerTypes");					} else if ("questions".equals(propName)) { //non-I18N-NLS						performAction(							ACTION_SHOW_MAIN_PALETTE,							getCurrentDoc(),							getCurrentDoc().hashCode(),							(doc.isNotEmpty())								? "showAnswerTypes"								: "hideAnswerTypes");						//non-I18N-NLS					} else {						//System.out.println("Not handled@" + propName);					}					performAction(ACTION_SAVE, doc, doc.hashCode(), "save");					if (!isActionEnabled(ACTION_ABANDON_CHANGES))						enableAction(ACTION_ABANDON_CHANGES);					if (!isActionEnabled(ACTION_SNAPSHOT_COPY))						enableAction(ACTION_SNAPSHOT_COPY);					if (!isActionEnabled(ACTION_MAKE_MILESTONE))						enableAction(ACTION_MAKE_MILESTONE);					if (!getMenuAnswerTypes().isEnabled()) {						getMenuAnswerTypes().setEnabled(							getCurrentDoc().isNotEmpty());						getMenuAnswerTypes().revalidate();					}				}			});			setQuestionairePanel(getCurrentDoc());			getHTMLPreviewPane().setDocument(getCurrentDoc());			showDocumentPane();		} else {			performAction(				ACTION_SHOW_MAIN_PALETTE,				getCurrentDoc(),				getCurrentDoc().hashCode(),				"hideAnswerTypes");		}		getDocumentPane().setViewportView(			(isNotNull) ? getQuestionairePanel() : null);		getHTMLPreviewPane().setVisible(false);		getRevertToMilestonePane().setVisible(false);		getStatusBar().setVisible(isNotNull);		getMenuPalette().setVisible(isNotNull);		getMenuPalette().setEnabled(isNotNull);		getMenuQuestions().setVisible(isNotNull);		getMenuQuestions().setEnabled(isNotNull);		getMenuAnswerTypes().setVisible(isNotNull);		getMenuAnswerTypes().setEnabled(getCurrentDoc().isNotEmpty());		getMenuView().setVisible(isNotNull);		getMenuView().setEnabled(isNotNull);		performAction(			ACTION_SHOW_MAIN_PALETTE,			fQuestionaire,			fQuestionaire.hashCode(),			(isNotNull) ? "show" : "hide");		//non-I18N-NLS		if (isNotNull) {			getHTMLPreviewPane().setDocument(fQuestionaire);			getRevertToMilestonePane().setDocument(fQuestionaire);			disableAction(ACTION_ABANDON_CHANGES);			disableAction(ACTION_MAKE_MILESTONE);			disableAction(ACTION_FLIP_ORIENTATION);			enableAction(ACTION_SNAPSHOT_COPY);			enableAction(ACTION_REVERT_TO_MILESTONE);			enableAction(ACTION_AUTO_REORDER_QUESTIONS);			enableAction(ACTION_HTML_PREVIEW);			enableAction(ACTION_CLOSE);			enableAction(ACTION_RENAME);			enableAction(ACTION_CHANGE_AUTHOR);			enableAction(ACTION_APPEND_QUESTION);			selectAction(ACTION_SHOW_MAIN_PALETTE);			getMenuAnswerTypes().setEnabled(getCurrentDoc().isNotEmpty());		} else {			disableAction(ACTION_SNAPSHOT_COPY);			disableAction(ACTION_REVERT_TO_MILESTONE);			disableAction(ACTION_AUTO_REORDER_QUESTIONS);			disableAction(ACTION_HTML_PREVIEW);			disableAction(ACTION_CLOSE);			disableAction(ACTION_RENAME);			disableAction(ACTION_CHANGE_AUTHOR);			disableAction(ACTION_APPEND_QUESTION);			deselectAction(ACTION_SHOW_MAIN_PALETTE);		}		deselectAction(ACTION_HTML_PREVIEW);		setTitle(fQuestionaire.getTitle());	}	public QuestionaireFactory2 getFactory() {		if (null == fQuestionaireFactory) {			fQuestionaireFactory = new QuestionaireFactory2();		}		return fQuestionaireFactory;	}	public QuestionaireHTMLFactory getHTMLFactory() {		if (null == fHTMLFactory) {			fHTMLFactory = new QuestionaireHTMLFactory();		}		return fHTMLFactory;	}	public void setTitle(String title) {		String appName = getConfig("app.title");		String newtitle = "";		if (!((null == title) || ("".equals(title)))) {			newtitle += title;			newtitle += " - ";		}		newtitle += appName;		super.setTitle(newtitle);	}	public void setStatus(String status) {		getStatusBar().setStatus(status);	}	//////////////////////////////////////////////////////////////////////////////	// IMPLEMENT IAPPLICATION	//////////////////////////////////////////////////////////////////////////////	/* ----------- Accessors -------------------- */	/**	 * Answer the application's <code>Frame</code>.	 *	 * @return	the frame.	 */	public Frame getFrame() {		return this;	}	/**	 * Answer the configuration.	 *	 * @return	the configuration.	 */	public ResourceBundle getConfig() {		if (null == fResources) {			fResources = ResourceBundle.getBundle("client");		}		return fResources;	}	/**	 * Answer the string resource with the given key.	 *	 * @param	<code>key</code> the resource's key.	 * @return	the string.	 */	public String getConfig(String key) {		return getConfig().getString(key);	}	public IQuestionaire getCurrentDoc() {		return fQuestionaire;	}	public Color getDNDSelectedColor() {		return Color.orange;	}	public Color getSelectedColor() {		return Color.lightGray;	}	public Color getHighlightColor() {		return Color.yellow;	}	public ActionImpl[] getActions() {		//should be in a ActionRepostitory		ActionImpl[] result =			{				actionForName(ACTION_INSERT_ANSWERTYPE),				actionForName(ACTION_INSERT_QUESTION),				actionForName(ACTION_HTML_PREVIEW),				actionForName(ACTION_SHOW_ALL_ANSWERTYPES_COLLAPSED),				actionForName(ACTION_SHOW_ALL_ANSWERTYPES_NOT_COLLAPSED),				actionForName(ACTION_APPEND_ANSWERTYPE),				actionForName(ACTION_AUTO_REORDER_QUESTIONS),				actionForName(ACTION_NEW_QUESTIONAIRE),				actionForName(ACTION_APPEND_QUESTION),				actionForName(ACTION_MOVE_QUESTION_UP),				actionForName(ACTION_MOVE_QUESTION_DOWN),				actionForName(ACTION_SCROLL_TOP),				actionForName(ACTION_DELETE_QUESTION),				actionForName(ACTION_FLIP_ORIENTATION),				actionForName(ACTION_RENAME),				actionForName(ACTION_CLOSE),				actionForName(ACTION_SAVE),				actionForName(ACTION_OPEN),				actionForName(ACTION_SCROLL_BOTTOM),				actionForName(ACTION_KEY_BINDINGS),				actionForName(ACTION_INFO),				actionForName(ACTION_SHOW_MAIN_PALETTE),				actionForName(ACTION_EXIT),				actionForName(ACTION_SNAPSHOT_COPY),				actionForName(ACTION_ABANDON_CHANGES),				actionForName(ACTION_MAKE_MILESTONE),				actionForName(ACTION_REVERT_TO_MILESTONE),				actionForName(ACTION_CHANGE_AUTHOR),				actionForName(ACTION_CUT),				actionForName(ACTION_COPY),				actionForName(ACTION_PASTE),				actionForName(ACTION_SELECT_ALL)};		return result;	}	/////////////////////////////////////////////////////////////////////////////	// PROTECTED METHODS	/////////////////////////////////////////////////////////////////////////////	/* ----------- Mutators --------------------- */	/**	 * Initialize the application.	 */	protected void initialize() {		initActions();		getContentPane().setLayout(new BorderLayout());		setJMenuBar(getJMenuBar());		setCurrentDoc(QuestionaireFactory2.getNullQuestionaire());		getContentPane().add(getRootPanel(), BorderLayout.CENTER);		getContentPane().add(getStatusBar(), BorderLayout.SOUTH);		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		addWindowListener(new WindowAdapter() {			public void windowClosed(WindowEvent e) {				performAction(					ACTION_EXIT,					e.getSource(),					e.getID(),					"window close");			};		});		addKeyListener(new KeyAdapter() {			public void keyReleased(KeyEvent evt) {				switch (evt.getKeyCode()) {					case KeyEvent.VK_PAGE_UP :						scrollTop();						break;					case KeyEvent.VK_PAGE_DOWN :						scrollBottom();						break;					default :						}			}		});		initializeTextComponentActions();	}	/**	 * Scroll the viewport to it's top.	 */	protected void scrollTop() {		getQuestionairePanel().scrollRectToVisible(new Rectangle(0, 0, 1, 1));	}	/**	 * Scroll the viewport to it's bottom.	 */	protected void scrollBottom() {		getQuestionairePanel().scrollRectToVisible(			new Rectangle(0, (getQuestionairePanel().getHeight() - 1), 1, 1));	}	/**	 * Set the &quot;editable&quot;view on the given questionaire.	 *	 * @param	<code>questionaire</code> the questionaire.	 */	protected void setQuestionairePanel(IQuestionaire questionaire) {		fQuestionairePnl = new QuestionairePanel(questionaire, this);	}	/* ----------- Accessors --------------------- */	public static void main(String[] args) {		try {			UIManager.setLookAndFeel(new KunststoffLookAndFeel());			Questionaire aQuestionaire = new Questionaire();			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();			int width = 800;			int height = 600;			try {				ResourceBundle bundle = ResourceBundle.getBundle("client");				//non-I18N-NLS				try {					width =						new Integer(bundle.getString("app.width")).intValue();					//non-I18N-NLS				} catch (MissingResourceException e) {					//use default				} catch (NumberFormatException e) {				}				try {					width =						new Integer(bundle.getString("app.width")).intValue();					//non-I18N-NLS				} catch (MissingResourceException e) {					//use default				} catch (NumberFormatException e) {				}			} catch (MissingResourceException e) {			}			aQuestionaire.setSize(width, height);			Dimension frameSize = aQuestionaire.getSize();			if (frameSize.height > screenSize.height)				frameSize.height = screenSize.height;			if (frameSize.width > screenSize.width)				frameSize.width = screenSize.width;			aQuestionaire.setLocation(				(screenSize.width - frameSize.width) / 2,				(screenSize.height - frameSize.height) / 2);			aQuestionaire.setVisible(true);		} catch (Throwable e) {			e.printStackTrace(System.out);		}	}	/**	 * Looks up the action with the given name and calls	 * {@link #actionPerformed(ActionEvent)	 * actionPerformed(ActionEvent)} on the looked up action	 * with the the given event.	 * <p>	 * Nothing happens if there's no action with the given name.	 *	 * @param	name		the name of the action.	 * @param	event		the action event.	 * @throws	IllegalArgumentException	if <code>evt</code>	 * 			is <code>null</code>.	 */	public void performAction(String name, ActionEvent evt)		throws IllegalArgumentException {		getActionRepository().performAction(name, evt);	}	/**	 * Looks up the action with the given name, creates a new	 * <code>ActionEvent</code> with the given information and calls	 * {@link #actionPerformed(ActionEvent)	 * actionPerformed(ActionEvent)} on the looked up action	 * with the newly created event.	 * <p>	 * Nothing happens if there's no action with the given name.	 *	 * @param	name		the name of the action.	 * @param	source		the object that originated the event.	 * @param	id			an integer that identifies the event.	 * @param	command		a string that may specify a command (possibly one	 * 						of several) associated with the event.	 */	public void performAction(		String name,		Object source,		int id,		String command) {		performAction(name, source, id, command, 0);	}	/**	 * Looks up the action with the given name, creates a new	 * <code>ActionEvent</code> with the given information and calls	 * {@link #actionPerformed(ActionEvent)	 * actionPerformed(ActionEvent)} on the looked up action	 * with the newly created event.	 * <p>	 * Nothing happens if there's no action with the given name.	 *	 * @param	name		the name of the action.	 * @param	source		the object that originated the event.	 * @param	id			an integer that identifies the event.	 * @param	command		a string that may specify a command (possibly one	 * 						of several) associated with the event.	 * @param	modifiers	the modifier keys held down during this action.	 */	public void performAction(		String name,		Object source,		int id,		String command,		int modifiers) {		performAction(name, new ActionEvent(source, id, command, modifiers));	}	/**	 * Register the given action with this application.	 * <p>	 * Use this method for eagerly instantiated actions.	 * <p>	 * The action can be later accessed through	 * {@link #performAction(String, Object, int, String)	 * performAction(String, Object, int, String)} or	 * {@link #performAction(String, ActionEvent)	 * performAction(String, ActionEvent)}	 * <p>	 * Just use <code>action.getName()</code> as the <code>name</code>	 * parameter of those methods.	 * <p>	 * The <code>NullAction</code> will not be registered.	 *	 * @param	action		the action.	 * @throws	IllegalArgumentException	if <code>action</code>	 * 			is <code>null</code> or if <code>action.getName()</code> is	 * 			<code>null</code> or an empty string, or an action with the	 * 			given name is already registered.	 */	public void registerAction(ActionImpl action) {		//action.addActionListener(this);//who's going to handle the events?		getActionRepository().registerAction(action);	}	/**	 * Register the given action with this application.	 * <p>	 * The action will be lazily instantiated when the action is accessed	 * for the first time.	 * <p>	 * The action can be later accessed through	 * {@link #performAction(String, Object, int, String)	 * performAction(String, Object, int, String)} or	 * {@link #performAction(String, ActionEvent)	 * performAction(String, ActionEvent)}	 * <p>	 * Just use the <code>name</code> parameter of those methods.	 *	 * @param	name				the name.	 * @param	actionClassName		the name of the action class to be lazily	 * 								instantiated.	 * @throws	IllegalArgumentException	if <code>name</code> is	 * 			<code>null</code> or an empty string, or if the given	 * 			<code>actionClassName</code> is not loadable, i.e.	 * 			<code>Class.forName(actionClassName)</code> throws a	 * 			<code>ClassNotFoundException</code>, or an action with the	 * 			given name is already registered.	 */	public void registerAction(String name, String actionClassName) {		//action.addActionListener(this);//who's going to handle the events?		getActionRepository().registerAction(name, actionClassName);	}	/**	 * Enable the action with the given name.	 *	 * @param	name the name of the action.	 */	public void enableAction(String name) {		getActionRepository().enableAction(name);	}	/**	 * Disable the action with the given name.	 *	 * @param	name the name of the action.	 */	public void disableAction(String name) {		getActionRepository().disableAction(name);	}	/**	 * Answer if the action with the given name is enabled.	 *	 * @param	name the name of the action.	 * @return	<code>true</code> if it is enabled.	 */	public boolean isActionEnabled(String name) {		return getActionRepository().isActionEnabled(name);	}	/**	 * Selects the action with the given name.	 *	 * @param	name the name of the action.	 */	public void selectAction(String name) {		getActionRepository().selectAction(name);	}	/**	 * Deselects the action with the given name.	 *	 * @param	name the name of the action.	 */	public void deselectAction(String name) {		getActionRepository().deselectAction(name);	}	/**	 * Answer if the action with the given name is selected.	 *	 * @param	name the name of the action.	 * @return	<code>true</code> if it is enabled.	 */	public boolean isActionSelected(String name) {		return getActionRepository().isActionSelected(name);	}	//////////////////////////////////////////////////////////////////////////////	// IMPLEMENT IAPPLICATION	//////////////////////////////////////////////////////////////////////////////	/* ----------- Accessors -------------------- */	/**	 * Answer the action repository.	 *	 * @return	the configuration.	 */	public ActionRepository getActionRepository() {		if (null == fActionRepository) {			fActionRepository = new ActionRepository(this);		}		return fActionRepository;	}	/**	 * Answer the action with the given name.	 *	 * @param	name		the name.	 * @return	the action with the given name or the <code>NullAction</code>	 * 			if no action with the given name is registered in this	 * 			repository.	 */	public ActionImpl actionForName(String name) {		return getActionRepository().lookup(name);	}	protected void initActions() {		registerAction(			ACTION_CHANGE_AUTHOR,			"de.sep.ui.actions.ChangeAuthorAction");		registerAction(ACTION_CUT, "de.sep.ui.actions.CutAction");		registerAction(ACTION_COPY, "de.sep.ui.actions.CopyAction");		registerAction(ACTION_PASTE, "de.sep.ui.actions.PasteAction");		registerAction(ACTION_SELECT_ALL, "de.sep.ui.actions.SelectAllAction");		registerAction(			ACTION_REVERT_TO_MILESTONE,			"de.sep.ui.actions.RevertToMilestoneAction");		registerAction(			ACTION_SNAPSHOT_COPY,			"de.sep.ui.actions.SnapshotCopyAction");		registerAction(			ACTION_MAKE_MILESTONE,			"de.sep.ui.actions.MakeMilestoneAction");		registerAction(			ACTION_ABANDON_CHANGES,			"de.sep.ui.actions.AbandonChangesAction");		registerAction(ACTION_EXIT, "de.sep.ui.actions.ExitAction");		registerAction(			ACTION_SHOW_MAIN_PALETTE,			"de.sep.ui.actions.MainPaletteAction");		registerAction(ACTION_INFO, "de.sep.ui.actions.InfoAction");		registerAction(			ACTION_KEY_BINDINGS,			"de.sep.ui.actions.KeyBindingsAction");		registerAction(			ACTION_SCROLL_BOTTOM,			"de.sep.ui.actions.ScrollBottomAction");		registerAction(ACTION_OPEN, "de.sep.ui.actions.OpenAction");		registerAction(ACTION_SAVE, "de.sep.ui.actions.SaveAction");		registerAction(ACTION_CLOSE, "de.sep.ui.actions.CloseAction");		registerAction(ACTION_RENAME, "de.sep.ui.actions.RenameAction");		registerAction(			ACTION_FLIP_ORIENTATION,			"de.sep.ui.actions.FlipOrientationAction");		registerAction(			ACTION_DELETE_QUESTION,			"de.sep.ui.actions.DeleteQuestionAction");		registerAction(ACTION_SCROLL_TOP, "de.sep.ui.actions.ScrollTopAction");		registerAction(			ACTION_MOVE_QUESTION_DOWN,			"de.sep.ui.actions.MoveDownQuestionAction");		registerAction(			ACTION_MOVE_QUESTION_UP,			"de.sep.ui.actions.MoveUpQuestionAction");		registerAction(			ACTION_APPEND_QUESTION,			"de.sep.ui.actions.NewQuestionAction");		registerAction(			ACTION_NEW_QUESTIONAIRE,			"de.sep.ui.actions.NewQuestionaireAction");		registerAction(			ACTION_AUTO_REORDER_QUESTIONS,			"de.sep.ui.actions.AutoReorderQuestionsAction");		registerAction(			ACTION_APPEND_ANSWERTYPE,			"de.sep.ui.actions.NewAnswerTypeAction");		registerAction(			ACTION_SHOW_ALL_ANSWERTYPES_NOT_COLLAPSED,			"de.sep.ui.actions.UncollapseAllAnswerTypesAction");		registerAction(			ACTION_SHOW_ALL_ANSWERTYPES_COLLAPSED,			"de.sep.ui.actions.CollapseAllAnswerTypesAction");		registerAction(			ACTION_HTML_PREVIEW,			"de.sep.ui.actions.HTMLPreviewAction");		registerAction(			ACTION_INSERT_QUESTION,			"de.sep.ui.actions.DropNewQuestionAction");		registerAction(			ACTION_INSERT_ANSWERTYPE,			"de.sep.ui.actions.DropNewAnswerTypeAction");	}}
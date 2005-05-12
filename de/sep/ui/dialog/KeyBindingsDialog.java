package de.sep.ui.dialog;

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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.TableColumn;

import de.sdavids.swing.SwingCreator;
import de.sdavids.swing.actions.ActionImpl;
import de.sdavids.swing.table.ImmutableTableModel;
import de.sdavids.swing.table.JSortTable;
import de.sdavids.util.KeyCodeUtils;

/**
 * A dialog displaying the key bindings.
 */
public class KeyBindingsDialog extends JDialog {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** The bundle containing the configuration. */
	private ResourceBundle fConfig;

	/** The actions. */
	private ActionImpl[] fActions;

	/* ----------- Widgets ---------- */

	/** The close button. */
	private JButton fCloseBtn;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Creates a non-modal dialog with the specified configuration, actions, and
	 * with the specified owner frame.
	 *
	 * @param	<code>owner</code> the <code>Frame</code> from which
	 * 			the dialog is displayed.
	 * @param	<code>config</code> the configuration.
	 * @param	<code>actions</code> the actions.
	 */
	public KeyBindingsDialog(
		Frame owner,
		ResourceBundle config,
		ActionImpl[] actions) {
		super(owner);

		setConfig(config);
		setActions(actions);

		initialize();
	}

	/////////////////////////////////////////////////////////////////////////////
	// WIDGETS
	/////////////////////////////////////////////////////////////////////////////

	/**
	 * Answer the close button.
	 * 
	 * @return	the button.
	 */
	protected JButton getCloseBtn() {
		if (null == fCloseBtn) {
			//non-I18N-NLS
			fCloseBtn =
				SwingCreator.newJButton("btn.close", getConfig(), new ActionListener() {

				public void actionPerformed(ActionEvent evt) {
					closeAction();
				}
			});
		}

		return fCloseBtn;
	}

	/**
	 * Answer the content panel.
	 * 
	 * The content panel is the root panel.
	 * 
	 * @return	the panel.
	 */
	protected JPanel getContentPnl() {
		JPanel pnl = new JPanel(new BorderLayout());

		pnl.add(getCloseBtn(), BorderLayout.EAST);

		JPanel result = new JPanel(new BorderLayout(0, 16));

		result.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

		result.add(pnl, BorderLayout.SOUTH);
		result.add(new JScrollPane(getKeyBindingsTable()), BorderLayout.CENTER);

		return result;
	}

	/**
	 * Answer the table showing the key bindings.
	 * 
	 * The content panel is the root panel.
	 * 
	 * @return	the panel.
	 */
	protected JTable getKeyBindingsTable() {
		String[] columnNames =
			{
				getResource("dlg.keyBindings.table.key"),
				getResource("dlg.keyBindings.table.info")};

		JSortTable result =
			new JSortTable(new ImmutableTableModel(getRowData(), columnNames));

		Icon upArrow = SwingCreator.newIcon("tableUp", getConfig());
		Icon downArrow = SwingCreator.newIcon("tableDown", getConfig());

		result.setUpArrow(upArrow);
		result.setDownArrow(downArrow);

		result.setColumnSelectionAllowed(false);
		result.setRowSelectionAllowed(false);
		result.setCellSelectionEnabled(false);
		result.getTableHeader().setResizingAllowed(false);

		//adjust column sizes		
		int iconSize = 0;

		if (null != upArrow)
			iconSize = upArrow.getIconWidth();

		if (null != downArrow) {
			int w = downArrow.getIconWidth();

			if (iconSize < w)
				iconSize = w;
		}

		iconSize *= 2; //just to be sure it will fit

		TableColumn keys = result.getColumn(result.getColumnName(0));
		TableColumn infos = result.getColumn(result.getColumnName(1));

		int keysW = result.getPreferredWidthForColumn(keys) + iconSize;

		keys.setMinWidth(keysW);
		keys.setMaxWidth(keysW);

		int infosW = result.getPreferredWidthForColumn(infos) + iconSize;

		infos.setMinWidth(infosW);
		infos.setMaxWidth(infosW);

		result.sizeColumnsToFit(0);

		// adjust viewport
		Dimension prefSize = result.getPreferredScrollableViewportSize();

		final int UNNECESSARY_PIXELS = 3; //leftover pixels at the right side
		final int SHOW_MAX_ROWS = 20; //show not more than 15 rows

		int nRows = result.getRowCount();

		int rowHeights =
			((nRows > SHOW_MAX_ROWS) ? SHOW_MAX_ROWS : nRows) * result.getRowHeight();

		prefSize.setSize((keysW + infosW - UNNECESSARY_PIXELS), rowHeights);

		result.setPreferredScrollableViewportSize(prefSize);

		return result;
	}

	/////////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Makes the dialog visible.
	 * 
	 * Puts the focus on the &quot;Close&quot; button
	 */
	public void show() {
		if (!getCloseBtn().hasFocus()) {
			if (!getCloseBtn().isRequestFocusEnabled())
				getCloseBtn().setRequestFocusEnabled(true);

			getCloseBtn().requestFocus();
		}
		
		super.show();		
	}
	
	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Set the dialog's configuration.
	 *
	 * @param	<code>config</code> the bundle.
	 */
	protected void setConfig(ResourceBundle config) {
		fConfig = config;
	}

	/**
	 * Set the dialog's actions.
	 *
	 * @param	<code>actions</code> the actions.
	 */
	protected void setActions(ActionImpl[] actions) {
		fActions = actions;
	}

	/**
	 * Initialize the dialog.
	 * 
	 * Lays out all <code>Component</code>s and registers needed
	 * <code>EventListener</code>s.
	 */
	protected void initialize() {
		addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				if (KeyEvent.VK_ESCAPE == evt.getKeyCode())
					closeAction();
			}
		});			
		
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent evt) {
				if (!getCloseBtn().hasFocus()) {
					if (!getCloseBtn().isRequestFocusEnabled())
						getCloseBtn().setRequestFocusEnabled(true);
		
					getCloseBtn().requestFocus();
				}			
			}			
		});

		setTitle(getResource("dlg.keyBindings.title")); //non-I18N-NLS
		setContentPane(getContentPnl());

		pack();

		setResizable(false);
	}

	/**
	 * Invoked when an the dialog is closed.
	 */
	protected void closeAction() {
		dispose();
	}
	
	/* ----------- Accessors -------------------- */

	/**
	 * Answer the resource bundle containing the configuration.
	 * 
	 * @return	the bundle.
	 */
	protected ResourceBundle getConfig() {
		return fConfig;
	}

	/**
	 * Answer the actions.
	 * 
	 * @return	the actions.
	 */
	protected ActionImpl[] getActions() {
		return fActions;
	}

	/**
	 * Answer the string resource with the given key.
	 * 
	 * @return	the resource.
	 */
	protected String getResource(String key) {
		return getConfig().getString(key);
	}

	/**
	 * Answer the descriptor for the action at the given index.
	 * 
	 * The first index in the <code>String[]</code> array (descriptor) is
	 * the key and the second is the info.
	 * 
	 * @param	<code>index</code> the index of the action in the
	 * 			<code>fActions</code> array.
	 * @return	the descriptor.
	 */
	protected String[] getDescriptor(int index) {
		String[] result = new String[2];

		KeyStroke key = getActions()[index].getAccelerator();

		if (null == key)
			return null;

		result[0] = KeyCodeUtils.toStandardRepresentation(key);
		result[1] = getActions()[index].getLongDescription();

		return result;
	}
	/**
	 * Answer the table data.
	 * 
	 * The first index in the <code>String[][]</code> array (rowData) is
	 * the row number and the second is the descriptor.
	 * 
	 * @return	the descriptor.
	 */
	protected String[][] getRowData() {
		int n = getActions().length;

		ArrayList descriptors = new ArrayList();

		Object descriptor;

		int i;

		for (i = (n - 1); i >= 0; i--) {
			descriptor = getDescriptor(i);

			if (null != descriptor)
				descriptors.add(descriptor);
		}

		String[][] result = new String[descriptors.size()][2];

		Iterator it = descriptors.iterator();

		i = 0;

		while (it.hasNext()) {
			result[i] = (String[]) it.next();
			i++;
		}

		return result;
	}
}
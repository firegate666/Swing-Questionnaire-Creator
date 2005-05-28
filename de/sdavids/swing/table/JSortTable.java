package de.sdavids.swing.table;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class JSortTable extends JTable {
	protected class SortTableHeaderRenderer implements TableCellRenderer {

		private TableCellRenderer fRenderer;
		private JPanel fPanel;
		private JLabel fArrow;

		protected void setRenderer(TableCellRenderer r) {
			fRenderer =
				(r instanceof SortTableHeaderRenderer)
					? ((SortTableHeaderRenderer) r).getRenderer()
					: r;
		}

		protected void embellishComponent(Component c) {
			if (null != fPanel)
				return;

			fPanel = new JPanel();

			fPanel.setLayout(new BorderLayout(6, 0));

			fPanel.add(c, BorderLayout.CENTER);
			
			if (fArrow != null)
				fPanel.add(fArrow, BorderLayout.WEST);
		}

		public SortTableHeaderRenderer(TableCellRenderer r, JLabel arrow) {
			setRenderer((null == r) ? new DefaultTableCellRenderer() : r);
			setArrow(arrow);
		}
		
		public SortTableHeaderRenderer(TableCellRenderer r) {
			this(r, null);
		}

		public Component getTableCellRendererComponent(
			JTable table,
			Object value,
			boolean isSelected,
			boolean hasFocus,
			int row,
			int col) {
		
			Component c =
				getRenderer().getTableCellRendererComponent(
					table,
					value,
					isSelected,
					hasFocus,
					row,
					col);
					
			embellishComponent(c);
			
			return fPanel;
		}

		protected TableCellRenderer getRenderer() {
			return fRenderer;
		}

		protected void setArrow(JLabel arrow) {
			fArrow = arrow;
		}
	}

	private class SortTableModelDecorator
		extends TableModelDecorator
		implements TableModelListener {

		private int[] fMappings;

		public SortTableModelDecorator(TableModel m) {
			super(m);

			m.addTableModelListener(this);
			allocate();
		}

		public Object getValueAt(int row, int column) {
			return super.getValueAt(fMappings[row], column);
		}

		public void tableChanged(TableModelEvent e) {
			allocate();
		}

		protected void swap(int i, int j) {
			int tmp = fMappings[i];
			
			fMappings[i] = fMappings[j];
			fMappings[j] = tmp;
		}

		protected void allocate() {
			fMappings = new int[getRowCount()];

			for (int i = (fMappings.length - 1); i >= 0; i--) {
				fMappings[i] = i;
			}
		}

		public void sort(int column) {
			sort(column, true);
		}

		public void sort(int column, boolean ascending) {
			int rowCount = getRowCount();

			for (int i = 0; i < rowCount; i++) {
				for (int j = i + 1; j < rowCount; j++) {
					if (compare(fMappings[i], fMappings[j], column, ascending) < 0) {
						swap(i, j);
					}
				}
			}
			
			clearSelection();
			
			fireTableStructureChanged();
		}

		protected int compare(int i, int j, int column, boolean ascending) {
			Object io = getModel().getValueAt(i, column);
			
			if (null == io)
				return (ascending) ? -1 : 1;

			Object jo = getModel().getValueAt(j, column);
			
			if (null == jo)
				return (ascending) ? 1 : -1;

			int result =
				((io.getClass() == jo.getClass()) && (io instanceof Comparable))
					? ((Comparable) io).compareTo(jo)
					: jo.toString().compareTo(io.toString());

			return (ascending) ? (-1 * result) : result;
		}
	}

	private JLabel fArrowUp;
	private JLabel fArrowDown;
	
	private int fSelectedColumn;
	private boolean fAscending;
	
	/**
	 * JSortTable constructor comment.
	 */
	public JSortTable() {
		super();
		initialize();
	}

	/**
	 * JSortTable constructor comment.
	 * @param rowData java.lang.Object[][]
	 * @param columnNames java.lang.Object[]
	 */
	public JSortTable(
		java.lang.Object[][] rowData,
		java.lang.Object[] columnNames) {
			
		super(rowData, columnNames);
		initialize();
	}

	/**
	 * JSortTable constructor comment.
	 * @param numRows int
	 * @param numColumns int
	 */
	public JSortTable(int numRows, int numColumns) {
		super(numRows, numColumns);
		initialize();
	}

	/**
	 * JSortTable constructor comment.
	 * @param rowData java.util.Vector
	 * @param columnNames java.util.Vector
	 */
	public JSortTable(java.util.Vector rowData, java.util.Vector columnNames) {
		super(rowData, columnNames);
		initialize();
	}

	/**
	 * JSortTable constructor comment.
	 * @param dm javax.swing.table.TableModel
	 */
	public JSortTable(javax.swing.table.TableModel dm) {
		super(dm);
		initialize();
	}

	/**
	 * JSortTable constructor comment.
	 * @param dm javax.swing.table.TableModel
	 * @param cm javax.swing.table.TableColumnModel
	 */
	public JSortTable(
		javax.swing.table.TableModel dm,
		javax.swing.table.TableColumnModel cm) {
		super(dm, cm);
		initialize();
	}

	/**
	 * JSortTable constructor comment.
	 * @param dm javax.swing.table.TableModel
	 * @param cm javax.swing.table.TableColumnModel
	 * @param sm javax.swing.ListSelectionModel
	 */
	public JSortTable(
		javax.swing.table.TableModel dm,
		javax.swing.table.TableColumnModel cm,
		ListSelectionModel sm) {
		super(dm, cm, sm);
		initialize();
	}

	/**
	 * Sets the data model for this table to <I>model</I> and registers
	 * with for listner notifications from the new data model.
	 *
	 * @param	<code>model</code> the new data source for this table.
	 * @throws	<code>IllegalArgumentException</code> if <code>model</code> is null.
	 * @see	#getModel
	 */
	public void setModel(TableModel model) {
		super.setModel(new SortTableModelDecorator(model));

		if (0 != getColumnCount())
			sort(0);		
	}

	public void setColumnModel(TableColumnModel newModel) {
		super.setColumnModel(newModel);
	}

	public int getPreferredWidthForColumn(TableColumn column) {
		int hw = columnHeaderWidth(column);
		int cw = widestCellInColumn(column);
		
		return (hw > cw) ? hw : cw;

	}
	
		
	/**
	 * Sets the row selection model for this table to <code>model</code>
	 * and registers for listener notifications from the new selection model.
	 *
	 * @param	<code>model</code> the new selection model.
	 * @throws	<code>IllegalArgumentException</code> if <code>model</code> is null.
	 * @see	#getSelectionModel.
	 */
	public void setSelectionModel(ListSelectionModel model) {
		if (null == model)
			throw new IllegalArgumentException("model null");

		ListSelectionModel oldModel = getSelectionModel();

		if (model.equals(oldModel))
			return;

		if (null != oldModel)
			oldModel.removeListSelectionListener(this);

		selectionModel = model;
		
		model.addListSelectionListener(this);

		repaint();

		firePropertyChange("selectionModel", oldModel, model);
	}

	public void setDownArrow(Icon down) {
		fArrowDown = (null == down) ? new JLabel("v") : new JLabel(down);
		
		if (!fAscending) adjustArrow(false);
	}

	public void setUpArrow(Icon up) {
		fArrowUp = (null == up) ? new JLabel("^") : new JLabel(up);
		
		if (fAscending) adjustArrow(true);
	}
	
	protected void adjustArrow(boolean up) {
		if (0 == getColumnCount())
			return;
			
		TableColumnModel tcm = getColumnModel();
		
		TableColumn column = tcm.getColumn(fSelectedColumn);
		TableCellRenderer renderer = column.getHeaderRenderer();

		column.setHeaderRenderer(
				new SortTableHeaderRenderer(renderer, fArrowUp));
	}		
		
	protected void initialize() {
		setModel(getModel());

		fSelectedColumn = 0;
		fAscending = false;
			
		setDownArrow(null);
		setUpArrow(null);

		JTableHeader hdr = getTableHeader();
		
		hdr.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sort(getColumnModel().getColumnIndexAtX(e.getX()));
			}
		});
		
		getColumnModel().addColumnModelListener(new TableColumnModelListener() {
			public void columnAdded(TableColumnModelEvent evt) {
				//should be implemented
			}
			public void columnRemoved(TableColumnModelEvent evt) {
				//should be implemented			
			}
			public void columnMoved(TableColumnModelEvent evt) {
				int from = evt.getFromIndex();
				int to = evt.getToIndex();
				
				if (fSelectedColumn == from) {
					fSelectedColumn = to;
				} else if (fSelectedColumn == to) {
					fSelectedColumn = from;
				}
			}
			public void columnMarginChanged(ChangeEvent evt) {}
			
			public void columnSelectionChanged(ListSelectionEvent evt) {}
		});
		
		if (0 != getColumnCount())
			sort(0);
	}

	protected void sort(int index) {
		TableColumnModel tcm = getColumnModel();
		
		int mc = convertColumnIndexToModel(index);

		TableColumn column;
		TableCellRenderer renderer;

		if (fSelectedColumn == index) {
			fAscending = !fAscending;
		} else {
			if (-1 != fSelectedColumn) {
				column = tcm.getColumn(fSelectedColumn);
				renderer = column.getHeaderRenderer();

				column.setHeaderRenderer(new SortTableHeaderRenderer(renderer));

				fAscending = true;
			}
		}

		column = tcm.getColumn(index);
		renderer = column.getHeaderRenderer();
		
		column.setHeaderRenderer(
			new SortTableHeaderRenderer(renderer, (fAscending) ? fArrowUp : fArrowDown));
			
		((SortTableModelDecorator) getModel()).sort(mc, fAscending);
		
		
		fSelectedColumn = index;
	}	
	
	protected int columnHeaderWidth(TableColumn column) {
		TableCellRenderer renderer = column.getHeaderRenderer();

		if (null == renderer) {
			renderer = new DefaultTableCellRenderer();
			column.setHeaderRenderer(renderer);
		}

		Component comp =
			renderer.getTableCellRendererComponent(
				this,
				column.getHeaderValue(),
				false,
				false,
				0,
				0);

		return comp.getPreferredSize().width;
	}
	
	protected int widestCellInColumn(TableColumn column) {
		int col = column.getModelIndex();
		int width = 0;
		int result = width;
		
		Component comp;
		TableCellRenderer renderer;
		
		for (int row = (getRowCount() - 1); row >= 0; row--) {
			renderer = getCellRenderer(row, col);
			
			comp = renderer.getTableCellRendererComponent(
					this,
					getValueAt(row, col),
					false,
					false,
					row,
					col);
					
			width = comp.getPreferredSize().width;
			
			if (result < width)
				result = width;				
		}
		
		return result;
	}
}
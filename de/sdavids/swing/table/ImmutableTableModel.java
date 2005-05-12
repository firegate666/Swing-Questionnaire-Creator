package de.sdavids.swing.table;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

//The model is immutable in that the column names and the row data cannot be reassigned
//The present implementation does not copy the arrays
//References to the parameter arrays could still manipulate them!
public class ImmutableTableModel implements TableModel {

	//////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/** The column names. */
	private final Object[] fColumnNames;

	/**
	 * The <code>Object</code> values.
	 */
	private final Object[][] fRowData;

	//////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////////

    /**
     * Constructs a <code>ImmutableTableModel</code> and initializes the table
     * with the given <code>rowData</code> and <code>columnNames</code>.
     * 
     * The first index in the <code>Object[][]</code> array is the row index and
     * the second is the column index.
     *
	 * @param  <code>rowData</code> the data of the table.
	 * @param	<code>columnNames</code> the names of the columns.
     */
	public ImmutableTableModel(
		final Object[][] rowData,
		final Object[] columnNames) {
			
		if (null == rowData)
			throw new IllegalArgumentException("rowData null");

		fRowData = rowData;
		fColumnNames = columnNames;
	}

	/////////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Accessors -------------------- */

	/**
	 * Returns a default name for the column using spreadsheet conventions:
	 * A, B, C, ... Z, AA, AB, etc.
	 * 
	 * If <code>column</code> cannot be found, returns an empty string.
	 *
	 * @param	<code>column</code> the column whose name is to queried.
	 * @return	a string containing the default name of <code>column</code>
	 */
	protected String getSpreadSheetName(int column) {
		String result = "";

		for (; column >= 0; column = column / 26 - 1) {
			result = (char) ((char) (column % 26) + 'A') + result;
		}

		return result;
	}
	
	//////////////////////////////////////////////////////////////////////////////
	// IMPLEMENT TABLEMODEL
	//////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Leave model unchanged regardless of parameter values.
	 * 
	 * Does <strong>not</strong> fire a <code>TableModelEvent</code> or throws
	 * an <code>ArrayIndexOutOfBoundsException</code>.
	 *
	 * @param  <code>aValue</code> the new value; this can be null.
	 * @param	<code>row</code> the row whose value is to be changed.
	 * @param	<code>column</code> the column whose value is to be changed.
	 */
	public void setValueAt(Object aValue, int row, int column) {}

	/**
	 * We are immutable so no listeners can be added.
	 *
	 * @param	<code>l</code> the TableModelListener.
	 */
	public void addTableModelListener(TableModelListener l) {}

	/**
	 * We are immutable so no listeners can be removed.
	 *
	 * @param	<code>l</code> the TableModelListener.
	 */
	public void removeTableModelListener(TableModelListener l) {}

	/* ----------- Accessors -------------------- */

	/**
	 * Answer the number of columns in this data table.
	 * 
	 * @return	the number of columns in the model.
	 */
	public int getColumnCount() {
		return (null == fColumnNames) ? 0 : fColumnNames.length;
	}

    /**
     * Returns the most specific superclass for all the cell values 
     * in the column.  This is used by the <code>JTable</code> to set up a 
     * default renderer and editor for the column.
     *
     * @param <code>columnIndex</code> the index of the column.
     * @return the common ancestor class of the object values in the model.
     */
	public Class getColumnClass(int columnIndex) {
		Object o = null;
		
		for (int i = (getRowCount() - 1); i >= 0; i--) {
			o = fRowData[i][columnIndex];
			
			if (null != o)
				break;
		}
		
		return (null == o) ? Object.class : o.getClass();
    }
    
	/**
	 * Answer the number of rows in this data table.
	 * 
	 * @return	the number of rows in the model.
	 */
	public int getRowCount() {
		return fRowData.length;
	}

	/**
	 * Answer the column name.
	 *
	 * 
	 * If <code>fColumnNames</code> is <code>null</code>or does not have 
	 * an entry for this index, returns a default name for the column using
	 * spreadsheet conventions: A, B, C, ... Z, AA, AB, etc.
	 * 
	 * @param	<code>column</code> the column whose name is to queried.
	 * @return	a name for this column using the string value of the
	 * 			appropriate member in <code>columnIdentifiers</code>.
	 */
	public String getColumnName(int column) {
		if (getColumnCount() <= column)
			return getSpreadSheetName(column);

		Object id = fColumnNames[column];

		if (null == id)
			return getSpreadSheetName(column);

		return id.toString();
	}

	/**
	 * Answer an attribute value for the cell at <code>row</code>
	 * and <code>column</code>.
	 *
	 * @param	<code>row</code> the row whose value is to be queried.
	 * @param	<code>column</code>column the column whose value is to be
	 * 			queried.
	 * @return	the value <code>Object</code> at the specified cell.
	 * @throws	<code>ArrayIndexOutOfBoundsException</code>  if an invalid row
	 * 			or column was given.
	 */
	public Object getValueAt(int row, int col) {
		return fRowData[row][col];
	}

	/**
	 * Answer <code>false</code> regardless of parameter values.
	 *
	 * @param	<code>row</code> the row whose value is to be queried.
	 * @param	<code>column</code>column the column whose value is to be
	 * 			queried.
	 * @return	<code>false</code>.
	 */
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
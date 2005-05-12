package de.sdavids.swing.table;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class TableModelDecorator extends AbstractTableModel { //implements TableModel {
	protected TableModel model_;

public TableModelDecorator(TableModel model) {
	if(null == model)
		throw new IllegalArgumentException("model null");
		
	model_ = model;
}
public void addTableModelListener(TableModelListener l) {
	getModel().addTableModelListener(l);
}
public Class getColumnClass(int columnIndex) {
	return getModel().getColumnClass(columnIndex);
}
public int getColumnCount() {
	return getModel().getColumnCount();
}
public String getColumnName(int columnIndex) {
	return getModel().getColumnName(columnIndex);
}
	protected TableModel getModel() {
		return model_;
	}
public int getRowCount() {
	return getModel().getRowCount();
}
public Object getValueAt(int rowIndex, int columnIndex) {
	return getModel().getValueAt(rowIndex, columnIndex);
}
public boolean isCellEditable(int rowIndex, int columnIndex) {
	return getModel().isCellEditable(rowIndex, columnIndex);
}
public void removeTableModelListener(TableModelListener l) {
	getModel().removeTableModelListener(l);
}
public void setValueAt(Object aValue,
							int rowIndex, int columnIndex) {
	getModel().setValueAt(aValue, rowIndex, columnIndex);
}
}

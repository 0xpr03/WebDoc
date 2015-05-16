package webdoc.gui.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * Custom TableModel for Patient Treatment JTable
 * @author "Aron Heinecke"
 */
@SuppressWarnings("serial")
public class PatientTableModel extends AbstractTableModel {
	
    protected static final String[] COLUMN_NAMES = {
        "Date",
        "Type",
    };
    private List<TDListElement> rowData;
    
    public PatientTableModel() {
    	rowData = new ArrayList<>(25);
    }
    
    public void add(TDListElement tdl) {
    	add(Arrays.asList(tdl));
    }
    
    public void add(List<TDListElement> tdl){
    	rowData.addAll(tdl);
    	fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
    	return rowData.size();
    }
    
    @Override
    public int getColumnCount() {
    	return COLUMN_NAMES.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
    
    public TDListElement getTDLEAt(int row){
    	return rowData.get(row);
    }
    
    /**
     * Return the values according to the headers, from the TDListElement
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
    	TDListElement tdl = getTDLEAt(rowIndex);
    	Object value = null;
    	switch(columnIndex) {
    	case 0:
    		value = tdl.getDate();
    		break;
    	case 1:
    		value = tdl.getType();
    		break;
    	}
    	return value;
    }
    
}

package Vue;

import javax.swing.table.AbstractTableModel;

/**
 * Classe définissant le modèle du tableau d'affichage des patients, en étendant la classe abstraite AbstractTableModel
 * @author Romain
 *
 */
@SuppressWarnings("serial")
public class PatientModelTable extends AbstractTableModel {
	
	private String[][] data;
	private String [] title;
	
	public PatientModelTable (String[][] data, String[] title) {
		this.data=data;
		this.title=title;
	}
	
	@Override
	public int getRowCount() {
		return this.data.length;
	}

	@Override
	public int getColumnCount() {
		return this.title.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.data[rowIndex][columnIndex];
	}
	
	@Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
	
	@Override
	public String getColumnName(int col) {
        return title[col].toString();
    }	
	

}

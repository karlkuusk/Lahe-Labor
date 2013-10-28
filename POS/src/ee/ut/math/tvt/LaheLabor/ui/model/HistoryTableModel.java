package ee.ut.math.tvt.LaheLabor.ui.model;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.LaheLabor.domain.data.SoldItemsArray;
import ee.ut.math.tvt.LaheLabor.domain.data.SoldItem;
import ee.ut.math.tvt.LaheLabor.ui.SalesSystemUI;

/**
 * Purchase history details model.
 */
public class HistoryTableModel extends SalesSystemTableModel<SoldItemsArray> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(HistoryTableModel.class);
	
	private ArrayList<SoldItemsArray> sales=new ArrayList<SoldItemsArray>();
	
	public HistoryTableModel() {
		super(new String[] { "Id", "Date", "Time", "Sum"});
	}

	@Override
	protected Object getColumnValue(SoldItemsArray item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getDate();
		case 2:
			return item.getTime();
		case 3:
			return item.getSum();
		}
		throw new IllegalArgumentException("Column index out of range");
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final SoldItemsArray item : rows) {
			buffer.append(item.getId() + "\t");
			buffer.append(item.getDate() + "\t");
			buffer.append(item.getTime() + "\t");
			buffer.append(item.getSum() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}
	

    public void addItem(final SoldItemsArray item) {
    	sales.add(item);
        rows.add(item);
        log.debug("Added sale " + item.getId() + " with sum of " + item.getSum());
        fireTableDataChanged();
    }
    
    public SoldItemsArray getSale(int index){

    	return sales.get(index);
    }
}
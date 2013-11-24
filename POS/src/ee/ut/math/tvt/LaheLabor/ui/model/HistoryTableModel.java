package ee.ut.math.tvt.lahelabor.ui.model;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.lahelabor.domain.data.SoldItemsArray;
import ee.ut.math.tvt.lahelabor.domain.data.SoldItem;
import ee.ut.math.tvt.lahelabor.ui.SalesSystemUI;

/**
 * Purchase history details model.
 */
public class HistoryTableModel extends SalesSystemTableModel<SoldItemsArray> {
	private static final long serialVersionUID = 1L;
	private SalesSystemModel model;
	private static final Logger log = Logger.getLogger(HistoryTableModel.class);
	private ArrayList<SoldItemsArray> sales;
	
	public HistoryTableModel(SalesSystemModel model) {
		super(new String[] { "Id", "Date", "Time", "Sum"});
		this.model=model;
		sales=new ArrayList<SoldItemsArray>();
		for(SoldItemsArray s: model.getSoldItemsArray()){
			addItem(s);
		}
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
		model.addDataBaseItem(item);
		for(SoldItem s: item.getSoldItems()){
			s.setSoldItemsArray(item);
			model.addDataBaseItem(s);
		}
        rows.add(item);
        log.debug("Added sale " + item.getId() + " with sum of " + item.getSum());
        fireTableDataChanged();
    }
    
    public SoldItemsArray getSale(int index){

    	return sales.get(index);
    }
}
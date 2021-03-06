package ee.ut.math.tvt.lahelabor.domain.data;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.log4j.Logger;
import com.jgoodies.looks.windows.WindowsLookAndFeel;
import ee.ut.math.tvt.lahelabor.domain.data.SoldItemsArray;
import ee.ut.math.tvt.lahelabor.domain.data.SoldItem;
import ee.ut.math.tvt.lahelabor.ui.model.DetailedHistoryTableModel;


public class DetailedHistory extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(DetailedHistory.class);
	private final SoldItemsArray sale;
	
	private final DetailedHistoryTableModel model;
	

	public DetailedHistory(final SoldItemsArray sale, DetailedHistoryTableModel model) {
	    this.sale=sale;
		this.model=model;
		
	    setTitle("Sales system history");

	    try {
	      UIManager.setLookAndFeel(new WindowsLookAndFeel());

	    } catch (UnsupportedLookAndFeelException e1) {
	      log.warn(e1.getMessage());
	    }

	    drawWidgets();

	    int width = 600;
	    int height = 400;
	    setSize(width, height);
	    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    setLocation((screen.width - width) / 2, (screen.height - height) / 2);

	    addData();
	    setVisible(true);
	  }

	  private void drawWidgets() {

			JPanel pane = new JPanel();
			pane.setLayout(new GridBagLayout());
			pane.setBorder(BorderFactory.createTitledBorder("Shopping history"));

			JTable table = new JTable(model);
			JScrollPane scrollPane = new JScrollPane(table);

			pane.add(scrollPane, getBacketScrollPaneConstraints());
			add(pane);

		  
	  }
	  
	  private void addData(){
		
		  List<SoldItem> items=sale.getSoldItems();
		  for(int i=0;i<items.size();i++){
			  model.addItem(items.get(i));
		  }
		  
	  }	  
	  

		private GridBagConstraints getBacketScrollPaneConstraints() {
			GridBagConstraints gc = new GridBagConstraints();

			gc.fill = GridBagConstraints.BOTH;
			gc.weightx = 1.0;
			gc.weighty = 1.0;

			return gc;
		}
		
}

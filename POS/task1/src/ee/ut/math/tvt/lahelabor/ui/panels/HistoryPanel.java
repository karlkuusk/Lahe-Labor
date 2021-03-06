package ee.ut.math.tvt.lahelabor.ui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import ee.ut.math.tvt.lahelabor.domain.data.DetailedHistory;
import ee.ut.math.tvt.lahelabor.ui.model.SalesSystemModel;

public class HistoryPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Integer row=null;
	private SalesSystemModel model;



	public HistoryPanel(SalesSystemModel model) {
		this.model = model;

		setLayout(new GridBagLayout());
		add(drawHistoryPane(), getBasketPaneConstraints());
	}


	private JComponent drawHistoryPane() {

		JPanel basketPane = new JPanel();
		basketPane.setLayout(new GridBagLayout());
		basketPane.setBorder(BorderFactory.createTitledBorder("Shopping history"));

		JTable table = new JTable(model.getCurrentHistoryTabelModel());
		JScrollPane scrollPane = new JScrollPane(table);
		basketPane.add(scrollPane, getBacketScrollPaneConstraints());

		table.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
				   model.getCurrentDetailedHistoryTableModel().clear();
				   JTable target = (JTable)e.getSource();
				   row = target.getSelectedRow();
				   openDetailedHistory(row);
			  }
		});
		

		return basketPane;
	}

	private GridBagConstraints getBasketPaneConstraints() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.anchor = GridBagConstraints.WEST;
		gc.weightx = 0.2;
		gc.weighty = 1.0;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.fill = GridBagConstraints.BOTH;

		return gc;
	}

	private GridBagConstraints getBacketScrollPaneConstraints() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1.0;
		gc.weighty = 1.0;

		return gc;
	}
	
	private void openDetailedHistory(int row){
		DetailedHistory history=new DetailedHistory(model.getCurrentHistoryTabelModel().getSale(row), model.getCurrentDetailedHistoryTableModel());
	}

}
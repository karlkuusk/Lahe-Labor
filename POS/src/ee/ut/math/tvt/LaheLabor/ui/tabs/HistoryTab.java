package ee.ut.math.tvt.lahelabor.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.apache.log4j.Logger;
import ee.ut.math.tvt.lahelabor.domain.controller.SalesDomainController;
import ee.ut.math.tvt.lahelabor.ui.model.SalesSystemModel;
import ee.ut.math.tvt.lahelabor.ui.panels.HistoryPanel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryTab {
    
	  private static final Logger log = Logger.getLogger(HistoryTab.class);

	  private final SalesDomainController domainController;

	  private HistoryPanel historyPane;

	  private SalesSystemModel model;

	  public HistoryTab(SalesDomainController controller, SalesSystemModel model){
		        this.domainController = controller;
		        this.model = model;
	} 
   
    
    public Component draw() {
        JPanel panel = new JPanel();

        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setLayout(new GridBagLayout());

        historyPane = new HistoryPanel(model);
        panel.add(historyPane, getConstraintsForPurchasePanel());

        return panel;
      }


      private GridBagConstraints getConstraintsForPurchasePanel() {
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.NORTH;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.weightx = 1.0d;
        gc.weighty = 1.0;

        return gc;
      }

    }
package ee.ut.math.tvt.lahelabor.ui.panels;

import ee.ut.math.tvt.lahelabor.domain.data.SoldItem;
import ee.ut.math.tvt.lahelabor.domain.data.SoldItemsArray;
import ee.ut.math.tvt.lahelabor.domain.data.StockItem;
import ee.ut.math.tvt.lahelabor.domain.data.ConfirmPurchase;
import ee.ut.math.tvt.lahelabor.ui.model.SalesSystemModel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Purchase pane + shopping cart tabel UI.
 */
public class PurchaseItemPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    // Text field on the dialogPane
    private JComboBox<String> itemField;
    private JTextField quantityField;
    private JTextField nameField;
    private JTextField priceField;
    private JButton addItemButton;    
    
    public ArrayList<SoldItem> soldItems;

    // Warehouse model
    private SalesSystemModel model;

    /**
     * Constructs new purchase item panel.
     * 
     * @param model
     *            composite model of the warehouse and the shopping cart.
     */
    public PurchaseItemPanel(SalesSystemModel model) {
        this.model = model;
		soldItems = new ArrayList<SoldItem>();
		soldItems.addAll(model.getSoldItems());
        setLayout(new GridBagLayout());

        add(drawDialogPane(), getDialogPaneConstraints());
        add(drawBasketPane(), getBasketPaneConstraints());

        setEnabled(false);
    }

    // shopping cart pane
    private JComponent drawBasketPane() {

        // Create the basketPane
        JPanel basketPane = new JPanel();
        basketPane.setLayout(new GridBagLayout());
        basketPane.setBorder(BorderFactory.createTitledBorder("Shopping cart"));

        // Create the table, put it inside a scollPane,
        // and add the scrollPane to the basketPanel.
        JTable table = new JTable(model.getCurrentPurchaseTableModel());
        JScrollPane scrollPane = new JScrollPane(table);

        basketPane.add(scrollPane, getBacketScrollPaneConstraints());

        return basketPane;
    }

    // purchase dialog
    private JComponent drawDialogPane() {

        // Create the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        panel.setBorder(BorderFactory.createTitledBorder("Product"));

        // Initialize the textfields
        itemField = new JComboBox<String>(getStockName());
        quantityField = new JTextField("1");
        nameField = new JTextField();
        priceField = new JTextField();

        // Fill the dialog fields if the bar code text field loses focus
        itemField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                fillDialogFields();
            }
        });

        nameField.setEditable(false);
        priceField.setEditable(false);

        // == Add components to the panel

        // - bar code
        panel.add(new JLabel("Item :"));
        panel.add(itemField);

        // - amount
        panel.add(new JLabel("Amount:"));
        panel.add(quantityField);

        // - name
        panel.add(new JLabel("Name:"));
        panel.add(nameField);

        // - price
        panel.add(new JLabel("Price:"));
        panel.add(priceField);

        // Create and add the button
        addItemButton = new JButton("Add to cart");
        addItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItemEventHandler();
            }
        });

        panel.add(addItemButton);

        return panel;
    }
    private String[] getStockName() {

        int allStockItems = model.getWarehouseTableModel().getTableRows().size();
        String[] itemsarray = new String[allStockItems];
        int i = 0;
        for (final StockItem stockItem : model.getWarehouseTableModel()
                        .getTableRows()) {
                itemsarray[i] = stockItem.getName();
                i++;
        }
        return itemsarray;
    }


    // Fill dialog with data from the "database".
    public void fillDialogFields() {
        StockItem stockItem = getStockItemByBarcode();

        if (stockItem != null) {
            nameField.setText(stockItem.getName());
            String priceString = String.valueOf(stockItem.getPrice());
            priceField.setText(priceString);
        } else {
            reset();
        }
    }

    // Search the warehouse for a StockItem with the bar code entered
    // to the barCode text field.
    private StockItem getStockItemByBarcode() {
        try {
            String name = (String) itemField.getSelectedItem();
            return model.getWarehouseTableModel().getItemByName(name);
        } catch (NumberFormatException ex) {
            return null;
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    /**
     * Add new item to the cart.
     */
    public void addItemEventHandler() {
	
        // add chosen item to the shopping cart.
        StockItem stockItem = getStockItemByBarcode();
        if (stockItem != null){
			StockItem warehouseItem = model.getWarehouseTableModel().getItemById(stockItem.getId());	
			int checkOrderResult=checkOrder(quantityField.getText(),warehouseItem);
			switch (checkOrderResult){
				case 1:
					int quantity=Integer.parseInt(quantityField.getText());		
					SoldItem soldItem = new SoldItem(stockItem, quantity);     
					soldItems.add(soldItem);
					stockItem.setQuantity(stockItem.getQuantity() - quantity);                
					model.getCurrentPurchaseTableModel().addItem(soldItem);            	
					break;
				case -3:
					String negativerror ="Quantity must be positive";
					JOptionPane.showMessageDialog(null, negativerror, "Error", JOptionPane.ERROR_MESSAGE);
					break;					
				case -2:
					String quantityerror = stockItem.getName()+ ": Only " + warehouseItem.getQuantity()+ " left in stock." ;
					JOptionPane.showMessageDialog(null, quantityerror, "Error", JOptionPane.ERROR_MESSAGE);
					break;
					
				case -1:
					String valueerror="Quantity must be integer";
					JOptionPane.showMessageDialog(null, valueerror, "Error", JOptionPane.ERROR_MESSAGE);	
					quantityField.setText("0");
					break;
					
				default:
					;
			}           
        }
    }


    /**
     * Sets whether or not this component is enabled.
     */
    @Override
    public void setEnabled(boolean enabled) {
        this.addItemButton.setEnabled(enabled);
        this.itemField.setEnabled(enabled);
        this.quantityField.setEnabled(enabled);
    }

	
	public int checkOrder(String orderQuantity,StockItem warehouseitem){
		int quantity;
		try {
			quantity = Integer.parseInt(orderQuantity);
			if(quantity<=0){
				return -3;
			}
			
		} catch (NumberFormatException ex) {
			return -1;
		}
		if (quantity > warehouseitem.getQuantity()){
			return -2;
		}
		else {
			return 1;         	
		}
	}
		
    /**
     * Reset dialog fields.
     */
    public void reset() {
    	updateItemField();
        itemField.setSelectedIndex(-1);
        quantityField.setText("1");
        nameField.setText("");
        priceField.setText("");
    }
    
    
    private void updateItemField(){
    	itemField.removeAllItems();
    	String [] stockitems=getStockName();
    	for(int i=0;i<stockitems.length;i++){
    		itemField.addItem(stockitems[i]);
    	}
    }
    
	public void updateWarehouse() {
		// log.debug(model.getCurrentPurchaseTableModel());
		
		double sum = 0;
		ArrayList<SoldItem> items = new ArrayList<SoldItem>();
		
		for (int i = 0; i < soldItems.size(); i++) {
			SoldItem soldItem = soldItems.get(i);
			items.add(soldItem);
			StockItem warehouseItem = model.getWarehouseTableModel()
			.getItemByName(soldItem.getName());
			sum += soldItem.getSum();
			//warehouseItem.setQuantity(warehouseItem.getQuantity()- soldItem.getQuantity());
			
		}
		Date date = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
		
		SoldItemsArray sale = new SoldItemsArray(items, dateFormatter.format(date).toString(),
		timeFormatter.format(date).toString(), sum);
		
		//System.out.println(sale.getSoldItems);
		
		model.getCurrentHistoryTabelModel().addItem(sale);
		soldItems.clear();
	}
	
	public void restoreWarehouse(){
		for(int i=0;i<soldItems.size();i++){
			SoldItem soldItem = soldItems.get(i);
			StockItem warehouseItem = model.getWarehouseTableModel().getItemByName(soldItem.getName());
			warehouseItem.setQuantity(warehouseItem.getQuantity()+ soldItem.getQuantity());
		}
		soldItems.clear();
	}
    /*
     * === Ideally, UI's layout and behavior should be kept as separated as
     * possible. If you work on the behavior of the application, you don't want
     * the layout details to get on your way all the time, and vice versa. This
     * separation leads to cleaner, more readable and better maintainable code.
     * 
     * In a Swing application, the layout is also defined as Java code and this
     * separation is more difficult to make. One thing that can still be done is
     * moving the layout-defining code out into separate methods, leaving the
     * more important methods unburdened of the messy layout code. This is done
     * in the following methods.
     */

    // Formatting constraints for the dialogPane
    private GridBagConstraints getDialogPaneConstraints() {
        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.WEST;
        gc.weightx = 0.2;
        gc.weighty = 0d;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.fill = GridBagConstraints.NONE;

        return gc;
    }

    // Formatting constraints for the basketPane
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

    public SalesSystemModel getModel(){
    	return model;
    }
}

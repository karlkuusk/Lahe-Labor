package ee.ut.math.tvt.lahelabor.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import ee.ut.math.tvt.lahelabor.domain.data.StockItem;
import ee.ut.math.tvt.lahelabor.ui.model.StockTableModel;

public class AddToWarehousePopup extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField idField;
	private JTextField nameField;
	private JTextField quantityField;
	private JTextField priceField;

	private StockTableModel stockTableModel;

	/**
	 * Create the dialog.
	 */
	public AddToWarehousePopup(StockTableModel stockTableModel) {
		this.stockTableModel = stockTableModel;

		setTitle("Add a warehouse item");
		setBounds(750, 425, 150, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));
		{
			JLabel lblId = new JLabel("Id:");
			contentPanel.add(lblId, "2, 2, right, default");
		}
		{
			idField = new JTextField();
			contentPanel.add(idField, "4, 2, fill, default");
			idField.setColumns(10);
		}
		{
			JLabel lblPrice = new JLabel("Name:");
			contentPanel.add(lblPrice, "2, 4, right, default");
		}
		{
			nameField = new JTextField();
			contentPanel.add(nameField, "4, 4, fill, default");
			nameField.setColumns(10);
		}
		{
			JLabel lblPrice = new JLabel("Price:");
			contentPanel.add(lblPrice, "2, 6, right, default");
		}
		{
			priceField = new JTextField();
			contentPanel.add(priceField, "4, 6, fill, default");
			priceField.setColumns(10);
		}
		{
			JLabel lblQuantity = new JLabel("Quantity:");
			contentPanel.add(lblQuantity, "2, 8, right, default");
		}
		{
			quantityField = new JTextField();
			contentPanel.add(quantityField, "4, 8, fill, default");
			quantityField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						clickingOkButton();

					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			}
		}
	}

	protected void clickingOkButton() {
		Long id = 0L;
		String name = "";
		double price = 0;
		int quantity = 0;
		try {
			id = Long.valueOf(this.idField.getText());
			if (id < 0) {
				JOptionPane.showMessageDialog(null, "Id must be positive.",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Id must be an integer.",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		name = this.nameField.getText();
		if (name.length() == 0) {
			JOptionPane.showMessageDialog(null, "Please provide a name.",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			price = Double.valueOf(this.priceField.getText());
			if (price < 0) {
				JOptionPane.showMessageDialog(null, "Price must be positive.",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid price value.",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			quantity = Integer.valueOf(this.quantityField.getText());
			if (quantity < 1) {
				JOptionPane.showMessageDialog(null,
						"Quantity must be positive.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid quantity.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		StockItem newItem = new StockItem(id, name, "", price, quantity);
		this.stockTableModel.addItem(newItem);
		dispose();

	}

}
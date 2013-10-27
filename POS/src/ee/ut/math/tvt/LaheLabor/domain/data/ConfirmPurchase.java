package ee.ut.math.tvt.LaheLabor.domain.data;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.apache.log4j.Logger;
import ee.ut.math.tvt.LaheLabor.ui.panels.PurchaseItemPanel;
import ee.ut.math.tvt.LaheLabor.ui.tabs.PurchaseTab;


public class ConfirmPurchase extends JDialog{
	
	private PurchaseItemPanel purchaseItemPanel;
	
	private final JPanel panel = new JPanel();
	private static final Logger log = Logger.getLogger(ConfirmPurchase.class);
	public double sumOfBill;
	private JTextField paymentAmount;
	double dChangeAmount = 0;
	private JLabel changeAmount;
	
	public ConfirmPurchase(PurchaseItemPanel purchaseItemPanel,
			double sumOfBill) {
		this.purchaseItemPanel = purchaseItemPanel;
		this.sumOfBill = sumOfBill;
		this.dChangeAmount = -sumOfBill;
		panel.setLocation(200, 200);
		panel.setSize(450, 150);
		getContentPane().setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		{	JLabel arveSumma = new JLabel("Bill: " + sumOfBill);
			panel.add(arveSumma);	}
		
		{	JLabel makstudSumma = new JLabel("Amount paid: ");
			panel.add(makstudSumma);	}
		
		{	paymentAmount = new JTextField();
			paymentAmount.setColumns(7);
			paymentAmount.setText("...");
			panel.add(paymentAmount);	}
		
		{
			// EPAButton is short for Enter_Payment_Amount_Button
			{	JButton EPAButton = new JButton("Enter");
				panel.add(EPAButton);
				getRootPane().setDefaultButton(EPAButton);
				EPAButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EPAButtonClicked();	}
					});
				}
			
			changeAmount = new JLabel();
			changeAmount.setText("Amount to return: " + dChangeAmount);
			panel.add(changeAmount);
		}
		{
			JPanel bPane = new JPanel();
			bPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(bPane, BorderLayout.PAGE_END);
			{
				JButton acceptButton = new JButton("Accept");
				acceptButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						acceptButtonClicked();

					}
				});
				bPane.add(acceptButton);
			}
			
			{	JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();}
				});
				
				bPane.add(cancelButton);
			}			
		}
	}
	
	protected void acceptButtonClicked() {
		dispose();
//		purchaseItemPanel.uuendaLaoseisu();
	}
	
	protected void EPAButtonClicked() {
			String strAmountPaid = paymentAmount.getText();
			paymentAmount.setText("");
			double dPaymentAmount = Double.parseDouble(strAmountPaid);
			dChangeAmount += dPaymentAmount;
			changeAmount.setText("Amount to return: " + dChangeAmount);
		}

	
}
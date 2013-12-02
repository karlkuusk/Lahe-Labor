package ee.ut.math.tvt.salessystem.ui.model;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.Client;
import ee.ut.math.tvt.salessystem.domain.data.Sale;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import java.util.*;

/**
 * Main model. Holds all the other models.
 */
public class SalesSystemModel {

    // Warehouse model
    private StockTableModel warehouseTableModel;

    // Current shopping cart model
    private PurchaseInfoTableModel currentPurchaseTableModel;

    // Puchase history model
    private PurchaseHistoryTableModel purchaseHistoryTableModel;

    private ClientTableModel clientTableModel;

    private Client selectedClient;
	
	private Sale sale;
	
	private SalesDomainController dc;

    /**
     * Construct application model.
     * @param domainController Sales domain controller.
     */
    public SalesSystemModel(SalesDomainController domainController) {
		dc=domainController;
        warehouseTableModel = new StockTableModel();
        currentPurchaseTableModel = new PurchaseInfoTableModel(this);
        purchaseHistoryTableModel = new PurchaseHistoryTableModel();
        clientTableModel = new ClientTableModel();

        // Load data from the database

        List<StockItem> stockItems = domainController.getAllStockItems();
        warehouseTableModel.populateWithData(stockItems);

        List<Client> clients = domainController.getAllClients();
        clientTableModel.populateWithData(clients);

        List<Sale> sales = domainController.getAllSales();
        purchaseHistoryTableModel.populateWithData(sales);

    }

    public StockTableModel getWarehouseTableModel() {
        return warehouseTableModel;
    }

    public PurchaseInfoTableModel getCurrentPurchaseTableModel() {
        return currentPurchaseTableModel;
    }

    public PurchaseHistoryTableModel getPurchaseHistoryTableModel() {
        return purchaseHistoryTableModel;
    }

    public ClientTableModel getClientTableModel() {
        return clientTableModel;
    }

    public void setPurchaseHistoryTableModel(
            PurchaseHistoryTableModel purchaseHistoryTableModel) {
        this.purchaseHistoryTableModel = purchaseHistoryTableModel;
    }

    public Client getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Client client) {
        this.selectedClient = client;
    }
	
	public Sale getSale(){
		return sale;
	}
	
	public void setSale(Sale sale){
		this.sale=sale;
		currentPurchaseTableModel.setSale(sale);
	}
	
	public void reLoadSales(){
		List<Sale> sales=dc.getAllSales();
		for(Sale sale: sales){
			Set<SoldItem> soldItems=new HashSet<SoldItem>(dc.getSoldItemsBySale(sale));
			sale.setSoldItems(soldItems);
		}
		purchaseHistoryTableModel.populateWithData(sales);
	}
	
	public void reLoadStock(){
	
		warehouseTableModel.populateWithData(dc.getAllStockItems());
	}
	
	public void reLoadClients(){
		clientTableModel.populateWithData(dc.getAllClients());
	}
}

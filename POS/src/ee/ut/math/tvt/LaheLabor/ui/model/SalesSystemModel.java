package ee.ut.math.tvt.lahelabor.ui.model;

import org.apache.log4j.Logger;
import java.util.List;
import ee.ut.math.tvt.lahelabor.domain.controller.SalesDomainController;
import ee.ut.math.tvt.lahelabor.domain.data.SoldItem;
import ee.ut.math.tvt.lahelabor.domain.data.SoldItemsArray;
/**
 * Main model. Holds all the other models.
 */
public class SalesSystemModel {
    
    private static final Logger log = Logger.getLogger(SalesSystemModel.class);

    // Warehouse model
    private StockTableModel warehouseTableModel;
    
    // Current shopping cart model
    private PurchaseInfoTableModel currentPurchaseTableModel;
    private HistoryTableModel currentHistoryTableModel;
    private DetailedHistoryTableModel currentDetailedHistoryTableModel;
    private final SalesDomainController domainController;

    /**
     * Construct application model.
     * @param domainController Sales domain controller.
     */
    public SalesSystemModel(SalesDomainController domainController) {
        this.domainController = domainController;
        
        warehouseTableModel = new StockTableModel(this);
        currentPurchaseTableModel = new PurchaseInfoTableModel();
        currentHistoryTableModel =new HistoryTableModel(this);
        currentDetailedHistoryTableModel=new DetailedHistoryTableModel();
        // populate stock model with data from the warehouse
        warehouseTableModel.populateWithData(domainController.loadWarehouseState());

    }

    public StockTableModel getWarehouseTableModel() {
        return warehouseTableModel;
    }

    public PurchaseInfoTableModel getCurrentPurchaseTableModel() {
        return currentPurchaseTableModel;
    }
    
    public HistoryTableModel getCurrentHistoryTabelModel() {
    	return currentHistoryTableModel;
    }
    
    public DetailedHistoryTableModel getCurrentDetailedHistoryTableModel() {
    	return currentDetailedHistoryTableModel;
    }
	
	public List<SoldItem> getSoldItems(){
		return domainController.getSoldItems();
	}
	
	public List<SoldItemsArray> getSoldItemsArray(){
		return domainController.getSoldItemsArray();
	}
	
	public void updateDatabaseItem(Object o){
		domainController.updateDataBaseItem(o);
	}
 
	public void addDataBaseItem(Object o){
		domainController.addDataBaseItem(o);
	}
}

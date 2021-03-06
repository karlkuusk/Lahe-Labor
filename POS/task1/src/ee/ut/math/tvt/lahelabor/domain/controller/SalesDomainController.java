package ee.ut.math.tvt.lahelabor.domain.controller;

import java.util.List;

import ee.ut.math.tvt.lahelabor.domain.data.SoldItem;
import ee.ut.math.tvt.lahelabor.domain.data.StockItem;
import ee.ut.math.tvt.lahelabor.domain.data.SoldItemsArray;
import ee.ut.math.tvt.lahelabor.domain.exception.VerificationFailedException;

/**
 * Sales domain controller is responsible for the domain specific business
 * processes.
 */
public interface SalesDomainController {


    /**
     * Load the current state of the warehouse.
     * 
     * @return List of ${link
     *         ee.ut.math.tvt.salessystem.domain.data.StockItem}s.
     */
    public List<StockItem> loadWarehouseState();

    // business processes
    /**
     * Initiate new business transaction - purchase of the goods.
     * 
     * @throws VerificationFailedException
     */
    public void startNewPurchase() throws VerificationFailedException;

    /**
     * Rollback business transaction - purchase of goods.
     * 
     * @throws VerificationFailedException
     */
    public void cancelCurrentPurchase() throws VerificationFailedException;

	public double calculateSumOfItems(List<SoldItem> soldItems);
	
	public List<SoldItem> getSoldItems();
	
	public List<SoldItemsArray> getSoldItemsArray();
    /**
     * Commit business transaction - purchsae of goods.
     * 
     * @param goods
     *            Goods that the buyer has chosen to buy.
     * @throws VerificationFailedException
     */
    public void submitCurrentPurchase(List<SoldItem> goods)
            throws VerificationFailedException;
	
	public void updateDataBaseItem(Object o);
	
	public void addDataBaseItem(Object o);

		
	public void endSession();
}

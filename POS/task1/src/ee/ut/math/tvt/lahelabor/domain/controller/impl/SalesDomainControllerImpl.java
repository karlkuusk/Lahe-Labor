package ee.ut.math.tvt.lahelabor.domain.controller.impl;

import java.util.ArrayList;
import java.util.List;

import ee.ut.math.tvt.lahelabor.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.lahelabor.domain.controller.SalesDomainController;
import ee.ut.math.tvt.lahelabor.domain.data.SoldItem;
import ee.ut.math.tvt.lahelabor.domain.data.StockItem;
import ee.ut.math.tvt.lahelabor.domain.data.SoldItemsArray;
import ee.ut.math.tvt.lahelabor.util.HibernateUtil;
import ee.ut.math.tvt.lahelabor.hibernate.service.HibernateDataService;

/**
 * Implementation of the sales domain controller.
 */
 @SuppressWarnings("unchecked")
public class SalesDomainControllerImpl implements SalesDomainController {
	private HibernateDataService hibernateDataService;
	
	public SalesDomainControllerImpl(){
		hibernateDataService=new HibernateDataService();
	}

	public void submitCurrentPurchase(List<SoldItem> goods) throws VerificationFailedException {
		if(goods.size()<=0){
			throw new VerificationFailedException();
		}
		// Let's assume we have checked and found out that the buyer is underaged and
		// cannot buy chupa-chups
		
		//throw new VerificationFailedException("Underaged!");
		// XXX - Save purchase
	}

	public void cancelCurrentPurchase() throws VerificationFailedException {				
		// XXX - Cancel current purchase
	}
	

	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase
	}

	public List<StockItem> loadWarehouseState() {
		// XXX mock implementation
		List<StockItem> dataset = new ArrayList<StockItem>();
		dataset.addAll(hibernateDataService.getStockItems());
		return dataset;
	}
	
	public List<SoldItem> getSoldItems(){
		return hibernateDataService.getSoldItems();
	}
	
	public List<SoldItemsArray> getSoldItemsArray(){
		return hibernateDataService.getSoldItemsArray();
	}
	
	public double calculateSumOfItems(List<SoldItem> soldItems){
		if(soldItems.size()>0){
			double itemsum=0.0;
			for (final SoldItem soldItem : soldItems) {
				itemsum += soldItem.getSum();
			} 
			return itemsum;
		}
		else{
			return 0;
		}
	}
	
	public void endSession() {
		HibernateUtil.closeSession();
	}

	public void updateDataBaseItem(Object o){
		hibernateDataService.updateDataBaseItem(o);
	}
	
	public void addDataBaseItem(Object o){
		hibernateDataService.addDataBaseItem(o);
	}

}

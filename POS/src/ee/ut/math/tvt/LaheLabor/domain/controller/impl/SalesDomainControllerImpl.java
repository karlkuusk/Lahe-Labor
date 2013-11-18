package ee.ut.math.tvt.lahelabor.domain.controller.impl;

import java.util.ArrayList;
import java.util.List;

import ee.ut.math.tvt.lahelabor.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.lahelabor.domain.controller.SalesDomainController;
import ee.ut.math.tvt.lahelabor.domain.data.SoldItem;
import ee.ut.math.tvt.lahelabor.domain.data.StockItem;
import ee.ut.math.tvt.lahelabor.util.HibernateUtil;

import org.hibernate.*;
/**
 * Implementation of the sales domain controller.
 */
 @SuppressWarnings("unchecked")
public class SalesDomainControllerImpl implements SalesDomainController {
	Session session=HibernateUtil.currentSession();
	List<StockItem> stockitems=session.createQuery("from SoldItem").list();

	public void submitCurrentPurchase(List<SoldItem> goods) throws VerificationFailedException {
		// Let's assume we have checked and found out that the buyer is underaged and
		// cannot buy chupa-chups
		throw new VerificationFailedException("Underaged!");
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
		for(StockItem stockitem:stockitems){
			dataset.add(stockitem);
			System.out.println("Foudn stockitem: ");
			System.out.println(stockitem);
		}
		return dataset;
	}
	public void endSession() {
		HibernateUtil.closeSession();
	}


}

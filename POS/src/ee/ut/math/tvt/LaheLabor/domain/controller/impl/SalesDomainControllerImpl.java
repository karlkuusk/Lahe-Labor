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
public class SalesDomainControllerImpl implements SalesDomainController {
	
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

		Session session=HibernateUtil.currentSession();
		Query a=session.createQuery("from StockItem");
		System.out.println(a);
		return dataset;
	}
	public void endSession() {
		HibernateUtil.closeSession();
	}


}

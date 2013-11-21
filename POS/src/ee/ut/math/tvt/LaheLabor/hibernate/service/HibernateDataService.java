package ee.ut.math.tvt.lahelabor.hibernate.service;

import java.util.List;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;


import ee.ut.math.tvt.lahelabor.domain.data.SoldItem;
import ee.ut.math.tvt.lahelabor.domain.data.StockItem;
import ee.ut.math.tvt.lahelabor.domain.data.SoldItemsArray;
import ee.ut.math.tvt.lahelabor.util.HibernateUtil;


@SuppressWarnings("unchecked")
public class HibernateDataService {

	private Session session = HibernateUtil.currentSession();

	public List<StockItem> getStockItems() {
		List<StockItem> result = session.createQuery("from StockItem").list();
		return result;
	}

	public List<SoldItem> getSoldItems() {
		List<SoldItem> result = session.createQuery("from SoldItem").list();
		return result;
	}
	
	public List<SoldItemsArray> getSoldItemsArray(){
		List<SoldItemsArray> result=session.createQuery("from SoldItemsArray").list();
		return result;
	}
	
	public void update(Object stockitem) {
			try {
				session.beginTransaction();
				session.update(stockitem);
				session.getTransaction().commit();
			} catch (NonUniqueObjectException e) {
					session.getTransaction().commit();
			}
	}
	
	public void addItem(Object stockitem) {
			try {
				session.beginTransaction();
				session.save(stockitem);
				session.getTransaction().commit();
			} catch (NonUniqueObjectException e) {
				session.getTransaction().commit();
			}
	}

}


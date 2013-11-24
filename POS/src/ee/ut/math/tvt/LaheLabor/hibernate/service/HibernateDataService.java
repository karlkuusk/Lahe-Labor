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
		System.out.println("Test 2");
		List<SoldItem> result = session.createQuery("from SoldItem").list();
		return result;
	}
	
	public List<SoldItemsArray> getSoldItemsArray(){
		System.out.println("TEST");
		List<SoldItemsArray> result=session.createQuery("from SoldItemsArray").list();
		return result;
	}
	
	public void updateDataBaseItem(Object o) {
			try {
				session.beginTransaction();
				session.update(o);
				session.getTransaction().commit();
			} catch (NonUniqueObjectException e) {
					session.getTransaction().commit();
			}
	}
	
	public void addDataBaseItem(Object o) {
			try {
				session.beginTransaction();
				session.save(o);
				session.getTransaction().commit();
			} catch (NonUniqueObjectException e) {
				session.getTransaction().commit();
			}
	}
}


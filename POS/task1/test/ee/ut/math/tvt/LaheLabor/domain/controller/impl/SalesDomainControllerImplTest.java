package ee.ut.math.tvt.lahelabor.domain.controller.impl;


import org.junit.Before;
import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.*;
import java.lang.RuntimeException;
import ee.ut.math.tvt.lahelabor.domain.data.*;
import ee.ut.math.tvt.lahelabor.domain.controller.*;
import java.util.Collections;
import java.util.ArrayList;

public class SalesDomainControllerImplTest{
	private SalesDomainController domainController;
	private ArrayList<SoldItem> soldItems;
	@Before
	public void setUp(){
		domainController=new SalesDomainControllerImpl();
		soldItems=new ArrayList<SoldItem>();
	}
	
	@Test
	public void testSumWithNoElements(){
		assertEquals(0.0,domainController.calculateSumOfItems(soldItems),0.0001);
	}
	
	@Test
	public void testSumWithOneElement(){
		SoldItem soldItem1=new SoldItem(new StockItem(new Long(1),"test6","for salesdomaincontroller testing purposes",7.0,10),5);
		soldItems.add(soldItem1);
		assertEquals(35.0,domainController.calculateSumOfItems(soldItems),0.0001);
	}
	
	@Test
	public void testSumWithMultipleElements(){
		SoldItem soldItem1=new SoldItem(new StockItem(new Long(1),"test6","for salesdomaincontroller testing purposes",3.0,10),4);
		SoldItem soldItem2=new SoldItem(new StockItem(new Long(1),"test6","for salesdomaincontroller testing purposes",4.0,10),2);
		SoldItem soldItem3=new SoldItem(new StockItem(new Long(1),"test6","for salesdomaincontroller testing purposes",5.0,10),1);
		SoldItem soldItem4=new SoldItem(new StockItem(new Long(1),"test6","for salesdomaincontroller testing purposes",6.0,10),7);
		soldItems.add(soldItem1);
		soldItems.add(soldItem2);
		soldItems.add(soldItem3);
		soldItems.add(soldItem4);
		assertEquals(67.0,domainController.calculateSumOfItems(soldItems),0.0001);
	}
}	
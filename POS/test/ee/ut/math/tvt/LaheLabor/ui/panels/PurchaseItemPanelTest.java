package ee.ut.math.tvt.lahelabor.domain.data;
import ee.ut.math.tvt.lahelabor.ui.model.*;
import ee.ut.math.tvt.lahelabor.domain.controller.impl.*;
import ee.ut.math.tvt.lahelabor.ui.panels.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.NoSuchElementException;

public class PurchaseItemPanelTest{
	private PurchaseItemPanel purchaseItemPanelTest;
	private StockItem stockItem;
	@Before
	public void setUp(){
		purchaseItemPanelTest=new PurchaseItemPanel(new SalesSystemModel(new SalesDomainControllerImpl()));
		stockItem=new StockItem(new Long(100),"test 6","for PurchaseItemPanel testing purposes",1.5,10);
	}
	
	@Test
	public void testIfAmountEnteredIsNotInteger(){
		assertEquals(1,purchaseItemPanelTest.checkOrder("5",stockItem));
		assertEquals(-1,purchaseItemPanelTest.checkOrder("abc",stockItem));
		assertEquals(-1,purchaseItemPanelTest.checkOrder("0.9",stockItem));
		assertEquals(-1,purchaseItemPanelTest.checkOrder("0abc",stockItem));
		assertEquals(-1,purchaseItemPanelTest.checkOrder("abc0",stockItem));
		assertEquals(-1,purchaseItemPanelTest.checkOrder("-55.aaa",stockItem));
	}
	
	@Test
	public void testIfAmountEnteredIsPositive(){
		assertEquals(1,purchaseItemPanelTest.checkOrder("7",stockItem));
		assertEquals(-3,purchaseItemPanelTest.checkOrder("0",stockItem));
		assertEquals(-3,purchaseItemPanelTest.checkOrder("-7",stockItem));
	}
	
	@Test
	public void testHasEnoughInStock(){
		assertEquals(1,purchaseItemPanelTest.checkOrder("1",stockItem));
		assertEquals(1,purchaseItemPanelTest.checkOrder("10",stockItem));
		assertEquals(-2,purchaseItemPanelTest.checkOrder("11",stockItem));
	}
}
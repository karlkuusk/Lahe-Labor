package ee.ut.math.tvt.lahelabor.domain.data;
import ee.ut.math.tvt.lahelabor.ui.model.*;
import ee.ut.math.tvt.lahelabor.domain.controller.impl.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.NoSuchElementException;

public class StockTableModelTest{
	private StockTableModel stockTableModel;
	@Before
	public void setUp(){
		stockTableModel=new StockTableModel(new SalesSystemModel(new SalesDomainControllerImpl()));
	}
	

	@Test
	public void testGetItemByIdWhenThrowsException(){
		try{
			StockItem item = stockTableModel.getItemById(0);
			assertTrue("Excepted NoSuchElementException",false);
		}
		catch(NoSuchElementException e){
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetItemByIdWhenItemExists(){
		stockTableModel.addItem(new StockItem(new Long(100),"test 4","for StockTableModel testing purposes",1.5,10));
		try{
			StockItem item = stockTableModel.getItemById(100);
			assertTrue(true);
		}
		catch(NoSuchElementException e){
			assertTrue("Did not Except NoSuchElementException",false);
		}
	}
	/** Seperate test because StockItem names can be the same, but id-s cannot **/
	
	@Test
	public void testValidateIdUniquenessItemExists(){ 
		int current_quantity;
		int later_quantity;
		StockItem item;
		stockTableModel.addItem(new StockItem(new Long(500),"test 5","for StockTableModel testing purposes",1.5,10));
		try{
			item=stockTableModel.getItemById(500);
			current_quantity=item.getQuantity();
			stockTableModel.addItem(item);
			later_quantity=stockTableModel.getItemById(500).getQuantity();
			assertEquals(current_quantity*2,later_quantity);
		}
		catch (NoSuchElementException e){
			assertTrue("Did not found item",false);
		}
	}

	@Test
	public void testValidateIdUniquenessItemDoesNotExist(){ 
		int startId=700;

		while(true){
			try{
				stockTableModel.getItemById(startId);
				startId++;
			}
			catch(NoSuchElementException ex){
				break;
			}
		}
		stockTableModel.addItem(new StockItem(new Long(startId),"test 6","for StockTableModel testing purposes",1.5,1));
		
		assertEquals(1,stockTableModel.getItemById(startId).getQuantity());
		
	}
			
}
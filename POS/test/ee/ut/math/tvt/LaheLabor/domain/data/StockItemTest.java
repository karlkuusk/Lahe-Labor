package ee.ut.math.tvt.lahelabor.domain.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.*;
import java.lang.RuntimeException;

public class StockItemTest{
	private StockItem stockItem1;
	private StockItem stockItem2;
	@Before
	public void setUp(){
		stockItem1=new StockItem(new Long(1),"test1","for testing purposes without quantity",5.0);
		stockItem2=new StockItem(new Long(1),"test2","for testing purposes with quantity",5.0,10);
	}
	
	@Test
	public void testConstructor(){
		System.out.println("Made StockItem1: "+ stockItem1);
		System.out.println("Made StockItem1: "+ stockItem2);
	}
	
	@Test
	public void testGetColumnGetId(){
		assertEquals(new Long(1),stockItem1.getColumn(0));
		assertEquals(new Long(1),stockItem2.getColumn(0));
	}

	@Test
	public void testGetColumnGetName(){
		assertEquals("test1",stockItem1.getColumn(1));
		assertEquals("test2",stockItem2.getColumn(1));
	}
	
	@Test
	public void testGetColumnGetPrice(){
		assertEquals(5.0,stockItem1.getColumn(2));
		assertEquals(5.0,stockItem2.getColumn(2));
	}

	@Test
	public void testGetColumnGetQuantity(){
		assertEquals(0,stockItem1.getColumn(3));
		assertEquals(10,stockItem2.getColumn(3));
	}
	
	@Test
	public void testGetColumnOutofBounds(){
		try{
			stockItem1.getColumn(5);
			stockItem2.getColumn(-1);
			assertTrue("Excepted Runtime exception",false);
		}
		catch(RuntimeException e){
			assertTrue(true);
		}
	}
	
	@Test
	public void testClone(){
		StockItem stockItem3=(StockItem)stockItem1.clone();
		for(int i=0;i<4;i++){
			assertEquals("clone check with getColumn failed at "+i,stockItem1.getColumn(i),stockItem3.getColumn(i));
		}
	}
}
	
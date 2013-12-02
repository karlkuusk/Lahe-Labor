package ee.ut.math.tvt.lahelabor.domain.data;
import org.junit.*;
import static org.junit.Assert.*;
public class SoldItemTest{
	private SoldItem soldItem1;
	private SoldItem soldItem2;
	
	@Before
	public void setUp(){
		soldItem1=new SoldItem(new StockItem(new Long(1),"test3","for solditem testing purposes",7.0,10),5);
		soldItem2=new SoldItem(new StockItem(new Long(1),"test3","for solditem testing purposes with zero quantity",7.0,10),0);
		System.out.println("Made soldItem1 "+soldItem1);
		System.out.println("Made soldItem2 "+soldItem2);
	}
	
	@Test
	public void testGetSum(){
		assertEquals(35.0,soldItem1.getSum(),0.001);
	}
	
	@Test
	public void testGetSumWithZeroQuantity(){
		assertEquals(0.0,soldItem2.getSum(),0.001);
	}
}
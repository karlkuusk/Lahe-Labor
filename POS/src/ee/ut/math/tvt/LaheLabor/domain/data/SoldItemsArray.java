package ee.ut.math.tvt.lahelabor.domain.data;
import java.util.ArrayList;

public class SoldItemsArray implements DisplayableItem {

	private static long ID=0l;
	
	private Long id;
	private ArrayList<SoldItem> soldItems;
	
	private String date;
	private String time;
	private double sum;
	
	public SoldItemsArray(ArrayList<SoldItem> soldItem, String date,String time, double sum) {
		this.soldItems=soldItem;
		this.date=date;
		this.time=time;
		this.sum=sum;
		this.id=ID;
		ID+=1;
	}
	
	
	public Long getId() {
	return id;
	}
	
	public void setId(Long id) {
	this.id = id;
	}  
	
	public ArrayList<SoldItem> getSoldItems() {
	return soldItems;
	}
	
	public String getDate() {
	return date;
	}
	
	
	public String getTime() {
	return time;
	}
	
	
	public double getSum() {
	return sum;
	}

}
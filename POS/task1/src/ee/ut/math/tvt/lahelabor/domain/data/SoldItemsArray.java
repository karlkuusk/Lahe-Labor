package ee.ut.math.tvt.lahelabor.domain.data;
import java.util.ArrayList;
import javax.persistence.*;
import java.util.*;
@Entity
@Table(name="TRANSACTION")
public class SoldItemsArray implements Cloneable, DisplayableItem{
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="DATE")
	private String date;
	@Column(name="TIME")
	private String time;
	@Column(name="TOTALSUM")
	private double sum;
	@OneToMany(mappedBy="soldItemsArray")
	private List<SoldItem> soldItems;

	public SoldItemsArray(List<SoldItem> soldItem, String date,String time, double sum) {
		this.soldItems=soldItem;
		this.date=date;
		this.time=time;
		this.sum=sum;
	}
	
	public SoldItemsArray(){
		;
	}
	
	public Long getId() {
	return id;
	}
	
	public void setId(Long id) {
	this.id = id;
	}  
	
	public List<SoldItem> getSoldItems() {
		return soldItems;
	}
	
	public void setSoldItems(List<SoldItem> soldItems) {
		this.soldItems=soldItems;
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

	public void setDate(String date) {
		this.date=date;
	}
	
	
	public void setTime(String time) {
		this.time=time;
	}
	
	
	public void setSum(double sum) {
		this.sum=sum;
	}

}
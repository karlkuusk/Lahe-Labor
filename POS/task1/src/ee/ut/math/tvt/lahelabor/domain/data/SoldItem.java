package ee.ut.math.tvt.lahelabor.domain.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.*;
/**
 * Already bought StockItem. SoldItem duplicates name and price for preserving history. 
 */
@Entity
@Table(name="SOLDITEM")
public class SoldItem implements Cloneable, DisplayableItem {
    @Id
	@Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SALE_ID")
	private SoldItemsArray soldItemsArray;
    @Column(name="STOCKITEM_ID")
    private Long stockItemId;    
    @Column(name="NAME")
    private String name;
    @Column(name="QUANTITY")
    private Integer quantity;
    @Column(name="ITEMPRICE")
    private double price; 
	@Transient
	private StockItem stockItem;

    
    public SoldItem(StockItem stockItem, int quantity) { 
		this.stockItem=stockItem;
        this.name = stockItem.getName();
        this.price = stockItem.getPrice();
        this.quantity = quantity;
        this.stockItemId=stockItem.getId();     

    }
    
	public SoldItem(){
		;
	}
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
	
	public void setStockItemId(Long stockItemId){
		this.stockItemId=stockItemId;
	}
	
	public Long getStockItemId(){
		return stockItemId;
	}
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getSum() {
        return price * ((double) quantity);
    }

	public StockItem getStockItem() {
		return stockItem;
	}

	public void setSoldItemsArray(SoldItemsArray soldItemsArray){
		this.soldItemsArray=soldItemsArray;
	}
	
	public SoldItemsArray getSoldItemsArray(){
		return soldItemsArray;
	}

	public void setStockItem(StockItem stockItem) {
		this.stockItem = stockItem;
	}
	
	public String toString(){
		return stockItem.toString()+ " quantity: "+quantity;
	}
}
    


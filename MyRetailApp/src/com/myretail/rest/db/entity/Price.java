package com.myretail.rest.db.entity;

import javax.persistence.*;

/**
 * Mapping of Price class to PRICE table  
 * @author Trang
 *
 */

@Entity
@Table(name ="PRICE")
public class Price {
	
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "price")
	private double price;
	
	public Price()
	{
		
	}
	public Price(String id, double price) {
		this.id = id;
		this.price = price;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		return true;
	}
	

}

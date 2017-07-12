package com.forex.domain;

public class Historical {
	
	private int ref_id;
	private Currency currency;
	private double price;
	private int lot_size;
	String transaction_time;
	
	
	public int getRef_id() {
		return ref_id;
	}
	public void setRef_id(int ref_id) {
		this.ref_id = ref_id;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getLot_size() {
		return lot_size;
	}
	public void setLot_size(int lot_size) {
		this.lot_size = lot_size;
	}
	public String getTransaction_time() {
		return transaction_time;
	}
	public void setTransaction_time(String transaction_time) {
		this.transaction_time = transaction_time;
	}
	
	
//	ref_id INT NOT NULL AUTO_INCREMENT,
//	currency_pair VARCHAR(16) NOT NULL,
//	price DOUBLE NOT NULL,
//	lot_size INT NOT NULL,
//	transaction_time VARCHAR(50) NOT NULL,
}

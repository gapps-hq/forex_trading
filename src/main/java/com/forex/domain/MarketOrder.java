package com.forex.domain;

public class MarketOrder 
{
	private Currency currency_base;
	public Currency getCurrency_base() {
		return currency_base;
	}

	public void setCurrency_base(Currency currency_base) {
		this.currency_base = currency_base;
	}

	public Currency getCurrency_quote() {
		return currency_quote;
	}

	public void setCurrency_quote(Currency currency_quote) {
		this.currency_quote = currency_quote;
	}

	private Currency currency_quote;
	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}



	public String getTransaction_time() {
		return transaction_time;
	}

	public void setTransaction_time(String transaction_time) {
		this.transaction_time = transaction_time;
	}

	public TypeOfOrder getType_of_order() {
		return type_of_order;
	}

	public void setType_of_order(TypeOfOrder type_of_order) {
		this.type_of_order = type_of_order;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public double getLimit_price() {
		return limit_price;
	}

	public void setLimit_price(double limit_price) {
		this.limit_price = limit_price;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	private double price;
	private int lot_size;
	public int getLot_size() {
		return lot_size;
	}

	public void setLot_size(int lot_size) {
		this.lot_size = lot_size;
	}

	private String transaction_time;
	private TypeOfOrder type_of_order;
    private Side side;
    private double limit_price;
    private Status status;
    
    public MarketOrder() {
		// TODO Auto-generated constructor stub
	}
    
	
		
}

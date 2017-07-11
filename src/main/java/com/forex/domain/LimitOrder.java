package com.forex.domain;

public class LimitOrder {

	private double limit_price;
    private double lot_size;
    private Side side;
    private double price;
	private int order_id;
	private Currency currency;
	private Currency counterCurrency;
    
	public String getCurrencyPair(Currency currency, Currency counterCurrency) {
		return String.valueOf(currency + "/" + counterCurrency);
	}

	String transaction_time;
    String type_of_order;
    String status;

    public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}


	public void setSide(Side side) {
		this.side = side;
	}


    public double getLot_size() {
		return lot_size;
	}

	public void setLot_size(double lot_size) {
		this.lot_size = lot_size;
	}

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

	public String getType_of_order() {
		return type_of_order;
	}

	public void setType_of_order(String type_of_order) {
		this.type_of_order = type_of_order;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    public LimitOrder() {
    }

	public double getLimit_price() {
		return limit_price;
	}

	public void setLimit_price(double limit_price) {
		this.limit_price = limit_price;
	}
    

    public Side getSide() {
        return side;
}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Currency getCounterCurrency() {
		return counterCurrency;
	}

	public void setCounterCurrency(Currency counterCurrency) {
		this.counterCurrency = counterCurrency;
	}}
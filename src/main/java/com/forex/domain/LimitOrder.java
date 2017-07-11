package com.forex.domain;

public class LimitOrder {

	private Currency currency;
    private double amount;
    private Side side;

    
    public LimitOrder(Currency currency, double amount, Side side) {
        this.currency = currency;
        this.amount = amount;
        this.side = side;
    }

    public Currency getCurrency() {
        return currency;
    }


    public double getAmount() {
        return amount;
    }

    public Side getSide() {
        return side;
    }

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setSide(Side side) {
		this.side = side;
	}
    
}

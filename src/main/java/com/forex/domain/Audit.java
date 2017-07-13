package com.forex.domain;

import java.sql.Timestamp;

public class Audit {

	private int order_id;
	private int audit_id;
    private Currency currency_base;
    private Currency currency_quote ;
    private Double price ;
    private int lot_size ;
    private Timestamp transaction_time;
    private Timestamp time_updated;
    private Status status;
    
    
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getAudit_id() {
		return audit_id;
	}
	public void setAudit_id(int audit_id) {
		this.audit_id = audit_id;
	}
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getLot_size() {
		return lot_size;
	}
	public void setLot_size(int lot_size) {
		this.lot_size = lot_size;
	}

	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Timestamp getTime_updated() {
		return time_updated;
	}
	public void setTime_updated(Timestamp time_updated) {
		this.time_updated = time_updated;
	}
	public Timestamp getTransaction_time() {
		return transaction_time;
	}
	public void setTransaction_time(Timestamp transaction_time) {
		this.transaction_time = transaction_time;
	} 
}

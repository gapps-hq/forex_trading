package com.forex.domain;
import java.sql.Timestamp;

import java.sql.Timestamp;

public class Historical {

	private int ref_id;
	private Currency curr_quote;
	private Currency curr_base;
	private double price;
	private int lot_size;
	Timestamp transaction_completed;
	public Timestamp getTransaction_completed() {
		return transaction_completed;
	}
	public void setTransaction_completed(Timestamp transaction_completed) {
		this.transaction_completed = transaction_completed;
	}
	private int order_id;

	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getRef_id() {
		return ref_id;
	}
	public void setRef_id(int ref_id) {
		this.ref_id = ref_id;
	}
	public Currency getCurr_quote() {
		return curr_quote;
	}
	public void setCurr_quote(Currency curr_quote) {
		this.curr_quote = curr_quote;
	}
	public Currency getCurr_base() {
		return curr_base;
	}
	public void setCurr_base(Currency curr_base) {
		this.curr_base = curr_base;
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
	
}

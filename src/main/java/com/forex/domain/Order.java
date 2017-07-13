package com.forex.domain;

import java.sql.Timestamp;

public class Order{
	private double limit_price;
	   private int lot_size;
	   private Side side;
	   private double price;
	   private int order_id;
	   private Currency currency_base;
	   private Currency currency_quote;

	   private Timestamp transaction_time;
	   private  TypeOfOrder type_of_order;
	   private Status status;
	   private int cust_id;
	   
	    public Currency getCurrency_base() {
	        return currency_base;
	    }

	    

	    public void setCurrency_base(Currency currency_base) {
	        this.currency_base = currency_base;
	    }


	   public int getOrder_id() {
	        return order_id;
	    }

	    public void setOrder_id(int order_id) {
	        this.order_id = order_id;
	    }


	    public void setSide(Side side) {
	        this.side = side;
	    }

	    public int getLot_size() {
			return lot_size;
		}

		public void setLot_size(int lot_size) {
			this.lot_size = lot_size;
		}

	    public double getPrice() {
	        return price;
	    }


		public TypeOfOrder getType_of_order() {
			return type_of_order;
		}

		public void setType_of_order(TypeOfOrder type_of_order) {
			this.type_of_order = type_of_order;
		}

		public Status getStatus() {
			return status;
		}

		public void setStatus(Status status) {
			this.status = status;
		}
	       
		public void setPrice(double price){
			this.price = price;
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


	    public Currency getCurrency_quote() {
	        return currency_quote;
	    }

	    public void setCurrency_quote(Currency currency_quote) {
	        this.currency_quote = currency_quote;
	    }

	    public int getCust_id() {
	        return cust_id;
	    }

	    public void setCust_id(int cust_id) {
	        this.cust_id = cust_id;
	    }



		public void setTransaction_time(Timestamp timestamp) {
			this.transaction_time = timestamp;
			// TODO Auto-generated method stub
			
		}
	}
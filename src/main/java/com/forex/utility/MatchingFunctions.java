package com.forex.utility;

import com.forex.domain.Order;
import com.forex.domain.Side;

public class MatchingFunctions {
	public boolean checkBaseCurrency(Order order1, Order order2){
		return order1.getCurrency_base().equals(order2.getCurrency_base());
	}
	 
	public boolean checkQuoteCurrency(Order order1, Order order2){
		return order1.getCurrency_quote().equals(order2.getCurrency_quote());

	}
	
	public boolean checkLotSize(Order order1, Order order2){
		return order1.getLot_size() == order2.getLot_size();

	}
	public boolean checkSide(Order order1, Order order2){
		return (order1.getSide().equals(Side.BUY) && order2.getSide().equals(Side.SELL)) ||
				(order1.getSide().equals(Side.SELL) && order2.getSide().equals(Side.BUY));

	}
	
}

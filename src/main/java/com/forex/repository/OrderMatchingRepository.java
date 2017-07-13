package com.forex.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.forex.domain.Currency;
import com.forex.domain.Order;
import com.forex.domain.Side;
import com.forex.domain.Status;
import com.forex.domain.TypeOfOrder;
import com.forex.repository.LimitOrderRepository.LimitOrderMapper;
import com.forex.utility.MatchingFunctions;


@Repository
public class OrderMatchingRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	Order orders = new Order();
	
	  public OrderMatchingRepository(JdbcTemplate jdbcTemplate) {
		    this.jdbcTemplate = jdbcTemplate;
		  }
	
	
	public OrderMatchingRepository() {
		// TODO Auto-generated constructor stub
	}
	public List<Order> listOfOrders(){
		 return jdbcTemplate.query("select * from orders where status = ?", new Object[]{Status.PENDING.name()}, new OrderMapper());
		}

	public void matchOrder(){
		System.out.println("0\n");
		List<Order> o = listOfOrders();
		System.out.println("yo\n");
		int size = o.size();
		if(size <= 1){
			System.out.println("return\n"+size);
			return;
		}
		System.out.println("11\n");
		MatchingFunctions match = new MatchingFunctions();
		double cp = 0;
		double r[] = new double[2];
		for(int i = 0; i < size - 2; i++){
			if(o.get(i).getStatus() == Status.COMPLETED)
				continue;
			cp = match.getCurrentMarketPrice(o.get(i), jdbcTemplate);
			System.out.println(cp+"\n");
			for(int j = i; j < size - 1; j++){System.out.println("1");
				if(o.get(i).getStatus() == Status.COMPLETED)
					break;
				if(i == 4 && j == 5){
					System.out.println("Marketasd");
				}
				if(o.get(j).getStatus() == Status.COMPLETED)
					continue;
				if(i == 3 && j == 4){
					System.out.println(o.get(i).getCurrency_base().name()+" "+o.get(j).getCurrency_base().name()+"\n");
				}
				if(match.checkBaseCurrency(o.get(i), o.get(j))){if(i == 3 && j == 4){
					System.out.println("Market1");
				}System.out.println("2");
					if(match.checkQuoteCurrency(o.get(i), o.get(j))){if(i == 3 && j == 4){
						System.out.println("Market2");
					}System.out.println("3");
						if(match.checkLotSize(o.get(i), o.get(j))){if(i == 3 && j == 4){
							System.out.println("Market3");
						}System.out.println("4");
							if(match.checkSide(o.get(i), o.get(j))){if(i == 3 && j == 4){
								System.out.println("Market4");
							}System.out.println("5");
								r = match.orderWithOrder(o.get(i), o.get(j), cp);
								if(i == 3 && j == 4){
									System.out.println("Market5");
								}
								if(r[0] == -1){System.out.println("6");
									continue;
								}else{
									o.get(i).setPrice(r[0]);System.out.println(r[0]+" "+r[1]+"\n");
									o.get(j).setPrice(r[1]);
									match.executeOrder(o.get(i), o.get(j), jdbcTemplate);
									continue;
								}
							}else
								continue;
						}else
							continue;
					}else
						continue;
				}else
					continue;
			}
		}System.out.println("8");
		 
	}

}

class OrderMapper implements RowMapper<Order> {
	
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
      Order order = new Order();
      order.setCurrency_base(Currency.valueOf(rs.getString("currency_base")));
      order.setCurrency_quote(Currency.valueOf(rs.getString("currency_quote")));
      order.setOrder_id(rs.getInt("order_id"));
      order.setPrice(rs.getDouble("price"));
      order.setLot_size(rs.getInt("lot_size"));
      order.setTransaction_time(rs.getTimestamp("transaction_time"));
      order.setType_of_order(TypeOfOrder.valueOf(rs.getString("type_of_order")));
      order.setStatus(Status.valueOf(rs.getString("status")));
      order.setLimit_price(rs.getDouble("limit_price"));
      order.setSide(Side.valueOf(rs.getString("SIDE")));
      order.setCust_id(1);
      order.setTime_updated(rs.getTimestamp("time_updated"));
      return order;
    }

}


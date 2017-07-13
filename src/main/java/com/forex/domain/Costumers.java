package com.forex.domain;
import com.forex.domain.TYPE_OF_USER;

public class Costumers {
		private int User_id;
		private String password;
		private String name;
		private TYPE_OF_CUSTOMERS type_of_customer;
		
		
		public int getUser_id() {
			return User_id;
		}
		public void setUser_id(int user_id) {
			User_id = user_id;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public TYPE_OF_CUSTOMERS getType_of_customer() {
			return type_of_customer;
		}
		public void setType_of_customer(TYPE_OF_CUSTOMERS type_of_customer) {
			this.type_of_customer = type_of_customer;
		}
		
		

	}



package com.blakelong.springdemo.dao;

import java.util.List;

import com.blakelong.springdemo.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();
}

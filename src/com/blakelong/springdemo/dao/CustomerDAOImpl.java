package com.blakelong.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.blakelong.springdemo.entity.Customer;


public class CustomerDAOImpl implements CustomerDAO {
	
	// need to inject session factory bean from xml config - dependency injection, son!
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {

		// get current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// create query
		Query<Customer> query = session.createQuery("from Customer", Customer.class);
		
		// execute query and get result list
		List<Customer> customers = query.getResultList();
		
		// return the results
		return customers;
	}

}
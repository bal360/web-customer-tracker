package com.blakelong.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blakelong.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	// need to inject session factory bean from xml config - dependency injection, son!
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {

		// get current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// create query
		Query<Customer> query = session.createQuery("from Customer ORDER BY lastName", Customer.class);
		
		// execute query and get result list
		List<Customer> customers = query.getResultList();
		
		// return the results
		return customers;
	}
	
	@Override
	public void saveCustomer(Customer customer) {
		
		// get hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// save OR update the customer
		session.saveOrUpdate(customer);
	}
	
	@Override
	public Customer getCustomer(int id) {
		
		// get hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// get customer from db using primary key
		Customer customer = session.get(Customer.class, id);
		
		// return customer
		return customer;
	}
	
	@Override
	public void deleteCustomer(int id) {
		
		// get hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// get customer by primary key
		Customer customer = session.get(Customer.class, id);
		
		// delete customer
		session.delete(customer);
	}
	
	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		
		// get hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		Query<Customer> theQuery = null;
		
		if (theSearchName != null && theSearchName.trim().length() > 0) {
			// search for firstName or lastName - case insensitive
			theQuery = session.createQuery("from Customer WHERE lower(firstName) LIKE :theName OR lower(lastName) LIKE :theName", Customer.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		} else {
			// theSearchName is empty so just get all customers
			theQuery = session.createQuery("from Customer ORDER BY lastName", Customer.class);
		}
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		//return results
		return customers;
		
	}

}
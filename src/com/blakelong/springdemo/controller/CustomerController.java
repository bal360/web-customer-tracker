package com.blakelong.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blakelong.springdemo.dao.CustomerDAO;
import com.blakelong.springdemo.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// need to inject DAO into controller
	// spring will scan for a component that implements the interface
	// @Repository makes DAOImpl findable
	@Autowired
	private CustomerDAO customerDAO;
	
	
	@GetMapping("/list")
	public String listCustomers(Model model) {
		
		// get customers from DAO
		List<Customer> customers = customerDAO.getCustomers();
		
		// add the customers to the model
		model.addAttribute("customers", customers);
		
		return "list-customers";
	}
}

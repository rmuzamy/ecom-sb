package com.tns.services;

import com.tns.entities.Customer;
import java.util.List;
import java.util.ArrayList;

public class CustomerService {
	private List<Customer> customerList = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    //retrieve Customer by ID
    public Customer getCustomer(int userId) {
        for (Customer customer : customerList) {
            if (customer.getUserId() == userId) {
                return customer;
            }
        }
        return null; // if no customer is found with the given userId
    }


    public List<Customer> getCustomers() {
        return customerList;
    }
}

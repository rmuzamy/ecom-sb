package com.tns.entities;

import java.util.List;

public class Order {
	private int orderId;
    private Customer customer;
    private List<ProductQuantityPair> products;
    private String status; // e.g., "Pending", "Completed"

    public Order(int orderId, Customer customer, List<ProductQuantityPair> products, String status) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = products;
        this.status = status;
    }
    public Order() {
    	
    }
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ProductQuantityPair> getProducts() {
        return products;
    }

    public void setProducts(List<ProductQuantityPair> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

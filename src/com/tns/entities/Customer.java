package com.tns.entities;

import java.util.List;
import java.util.ArrayList;

public class Customer extends User{
	private ShoppingCart shoppingCart;
    private String address;
    private List<Order> orders;

    public Customer(int userId, String username, String email, String address) {
        super(userId, username, email);
        this.shoppingCart = new ShoppingCart();
        this.address = address;
        this.orders = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}

package com.tns.services;

import com.tns.entities.Order;
import com.tns.entities.Product;
import com.tns.entities.ProductQuantityPair;

import java.util.List;
import java.util.ArrayList;

public class OrderService {
    private List<Order> orderList = new ArrayList<>();

    public void placeOrder(Order order) {
        orderList.add(order);
    }

    public void updateOrderStatus(int orderId, String newStatus) {
        Order order = getOrder(orderId);

        if (order == null) {
            System.out.println("Invalid Order");
            return;
        }

        String currentStatus = order.getStatus();

        switch (newStatus.toLowerCase()) {
            case "completed":
                if (currentStatus.equalsIgnoreCase("pending")) {
                    if (isStockAvailable(order)) {
                        reduceStock(order);
                        order.setStatus("Completed");
                    } else {
                        System.out.println("Stock update failed. Not enough quantity.");
                    }
                }
                break;

            case "cancelled":
                if (currentStatus.equalsIgnoreCase("completed")) {
                    System.out.println("Cannot cancel a completed order.");
                } else {
                    order.setStatus("Cancelled");
                }
                break;

            case "delivered":
                if (currentStatus.equalsIgnoreCase("completed")) {
                    order.setStatus("Delivered");
                }
                break;

            default:
                System.out.println("Invalid status change.");
        }
    }


    private boolean isStockAvailable(Order order) {
        for (ProductQuantityPair pair : order.getProducts()) {
            Product product = pair.getProduct();
            if (product.getStockQuantity() < pair.getQuantity()) {
                System.out.println("Insufficient stock for product: " + product.getName());
                return false;
            }
        }
        return true;
    }

    private void reduceStock(Order order) {
        for (ProductQuantityPair pair : order.getProducts()) {
            Product product = pair.getProduct();
            product.setStockQuantity(product.getStockQuantity() - pair.getQuantity());
        }
    }

    public Order getOrder(int orderId) {
        for (Order order : orderList) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public List<Order> getOrders() {
        return orderList;
    }
}

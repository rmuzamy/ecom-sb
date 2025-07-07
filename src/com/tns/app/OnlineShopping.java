package com.tns.app;

import com.tns.entities.Order;
import com.tns.entities.Product;
import com.tns.entities.Customer;
import com.tns.entities.Admin;
import com.tns.entities.ProductQuantityPair;
import com.tns.services.OrderService;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class OnlineShopping {

	private static Scanner scanner = new Scanner(System.in);
    private static List<Product> products = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<Admin> admins = new ArrayList<>();
    private static OrderService orderService = new OrderService();
    private static int orderCounter = 1;

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int mainChoice = scanner.nextInt();

            switch (mainChoice) {
                case 1 -> adminMenu();
                case 2 -> customerMenu();
                case 3 -> {
                    System.out.println("Exiting....");
                    running = false;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void adminMenu() {
        boolean adminRunning = true;
        while (adminRunning) {
            System.out.println("Admin Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. View Products");
            System.out.println("4. Create Admin");
            System.out.println("5. View Admins");
            System.out.println("6. Update Order Status");
            System.out.println("7. View Orders");
            System.out.println("8. Return");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> removeProduct();
                case 3 -> viewProducts();
                case 4 -> createAdmin();
                case 5 -> viewAdmins();
                case 6 -> updateOrderStatus();
                case 7 -> viewAllOrders();
                case 8 -> {
                    System.out.println("Exiting Admin...");
                    adminRunning = false;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void customerMenu() {
        boolean customerRunning = true;
        while (customerRunning) {
            System.out.println("Customer Menu:");
            System.out.println("1. Create Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Place Order");
            System.out.println("4. View Orders");
            System.out.println("5. View Products");
            System.out.println("6. Return");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> createCustomer();
                case 2 -> viewCustomers();
                case 3 -> placeOrder();
                case 4 -> viewCustomerOrders();
                case 5 -> viewProducts();
                case 6 -> {
                    System.out.println("Exiting Customer Menu...");
                    customerRunning = false;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void addProduct() {
        System.out.print("Enter Product ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter Product Name: ");
        String name = scanner.next();
        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Stock Quantity: ");
        int quantity = scanner.nextInt();

        products.add(new Product(id, name, price, quantity));
        System.out.println("Product added successfully!");
    }

    private static void removeProduct() {
        System.out.print("Enter Product ID to remove: ");
        int id = scanner.nextInt();
        products.removeIf(p -> p.getProductId() == id);
        System.out.println("Product removed successfully!");
    }

    private static void viewProducts() {
        System.out.println("Products:");
        for (Product p : products) {
            System.out.println(p);
        }
    }

    private static void createAdmin() {
        System.out.print("Enter Admin ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter Admin Name: ");
        String name = scanner.next();
        admins.add(new Admin(id, name, name));
        System.out.println("Admin created successfully!");
    }

    private static void viewAdmins() {
        System.out.println("Admins:");
        for (Admin a : admins) {
            System.out.println("Admin ID: " + a.getAdminId() + ", Name: " + a.getUsername());
        }
    }

    private static void updateOrderStatus() {
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        System.out.print("Enter new status (Completed/Delivered/Cancelled): ");
        String status = scanner.next();
        orderService.updateOrderStatus(orderId, status);
    }

    private static void viewAllOrders() {
        List<Order> orders = orderService.getOrders();
//        List<Order> orders = [order1,order2, order3,order4];
        System.out.println("Orders:");
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderId() + ", Customer: " + order.getCustomer().getUsername() + ", Status: " + order.getStatus());
            for (ProductQuantityPair pair : order.getProducts()) {
                System.out.println("Product: " + pair.getProduct().getName() + ", Quantity: " + pair.getQuantity());
            }
        }
    }

    private static void createCustomer() {
        System.out.print("Enter User ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter Username: ");
        String username = scanner.next();
        System.out.print("Enter Email: ");
        String email = scanner.next();
        System.out.print("Enter Address: ");
        String address = scanner.next();
        customers.add(new Customer(id, username, email, address));
        System.out.println("Customer created successfully!");
    }

    private static void viewCustomers() {
        System.out.println("Customers:");
        for (Customer c : customers) {
            System.out.println("User ID: " + c.getUserId() + ", Username: " + c.getUsername() + ", Email: " + c.getEmail() + ", Address: " + c.getAddress());
        }
    }

    private static void placeOrder() {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        Customer customer = customers.stream().filter(c -> c.getUserId() == customerId).findFirst().orElse(null);

        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        List<ProductQuantityPair> orderedProducts = new ArrayList<>();

        while (true) {
            System.out.print("Enter Product ID to add to order (or -1 to complete): ");
            int productId = scanner.nextInt();
            if (productId == -1) break;

            Product product = products.stream().filter(p -> p.getProductId() == productId).findFirst().orElse(null);
            if (product == null) {
                System.out.println("Product not found!");
                continue;
            }

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            orderedProducts.add(new ProductQuantityPair(product, quantity));
        }

        Order order = new Order(orderCounter++, customer, orderedProducts, "Pending");
        orderService.placeOrder(order);
        System.out.println("Order placed successfully!");
    }

    private static void viewCustomerOrders() {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        List<Order> orders = orderService.getOrders();
        System.out.println("Orders:");
        for (Order order : orders) {
            if (order.getCustomer().getUserId() == customerId) {
                System.out.println("Order ID: " + order.getOrderId() + ", Status: " + order.getStatus());
                for (ProductQuantityPair pair : order.getProducts()) {
                    System.out.println("Product: " + pair.getProduct().getName() + ", Quantity: " + pair.getQuantity());
                }
            }
        }
    }
}

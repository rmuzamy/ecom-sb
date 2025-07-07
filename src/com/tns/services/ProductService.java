package com.tns.services;

import com.tns.entities.Product;
import java.util.List;
import java.util.ArrayList;

public class ProductService {
	private List<Product> productList = new ArrayList<>();

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void removeProduct(int productId) {
        productList.removeIf(product -> product.getProductId() == productId);
    }

    public List<Product> getProducts() {
        return productList;
    }

    public Product getProductById(int productId) {
        for (Product product : productList) {
            if (product.getProductId() == productId) {
                return product; // return the first match
            }
        }
        return null; // if not found
    }
}

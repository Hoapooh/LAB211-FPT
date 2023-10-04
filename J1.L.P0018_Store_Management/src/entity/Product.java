/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.time.LocalDate;
import java.util.Comparator;

/**
 *
 * @author anphu
 */
public class Product {

    private String productID;
    private String nameProduct;
    private String size;
    private LocalDate manufactoringDate;
    private LocalDate expirationDate;
    private double price;
    private int quantity;
    
    public Product(String id, String name, String size, double price, int quantity) {
        this.productID = id;
        this.nameProduct = name;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String productID, String nameProduct, String size, LocalDate manufactoringDate, LocalDate expirationDate, double price, int quantity) {
        this.productID = productID;
        this.nameProduct = nameProduct;
        this.size = size;
        this.manufactoringDate = manufactoringDate;
        this.expirationDate = expirationDate;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() {
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public LocalDate getManufactoringDate() {
        return manufactoringDate;
    }

    public void setManufactoringDate(LocalDate manufactoringDate) {
        this.manufactoringDate = manufactoringDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    //use this comparator to sort the value of quantity in ascending order
    public static Comparator<Product> compQuantity = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            if (o1.quantity > o2.quantity) {
                return 1;
            } else if (o1.quantity == o2.quantity) {
                return 0;
            } else {
                return -1;
            }
        }
    };
    
    //this is used to print the format of product into receipt
    public String printToReceipt() {
        return String.format("#%-11s# %-18s# %-11s# %-18.2f# %-11d#", productID, nameProduct, size, price, quantity);
    }

    //this is used to display to the console
    @Override
    public String toString() {
        return String.format("#%-11s# %-18s# %-11s# %-20s# %-20s# %-18.2f# %-11d#", 
                productID, nameProduct, size, manufactoringDate, expirationDate, price, quantity);
    }
}

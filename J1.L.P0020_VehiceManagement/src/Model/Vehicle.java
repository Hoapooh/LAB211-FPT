/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author anphu
 */
public class Vehicle {
    private String ID_Vehicle;
    private String Name_Vehicle;
    private String color_Vehicle;
    private double price_Vehicle;
    private String brand_Vehicle;
    private String type;
    private int productYear;

    public Vehicle() {
    }

    public Vehicle(String ID_Vehicle, String Name_Vehicle, String color_Vehicle, double price_Vehicle, String brand_Vehicle, String type, int productYear) {
        this.ID_Vehicle = ID_Vehicle;
        this.Name_Vehicle = Name_Vehicle;
        this.color_Vehicle = color_Vehicle;
        this.price_Vehicle = price_Vehicle;
        this.brand_Vehicle = brand_Vehicle;
        this.type = type;
        this.productYear = productYear;
    }

    public String getID_Vehicle() {
        return ID_Vehicle;
    }

    public void setID_Vehicle(String ID_Vehicle) {
        this.ID_Vehicle = ID_Vehicle;
    }

    public String getName_Vehicle() {
        return Name_Vehicle;
    }

    public void setName_Vehicle(String Name_Vehicle) {
        this.Name_Vehicle = Name_Vehicle;
    }

    public String getColor_Vehicle() {
        return color_Vehicle;
    }

    public void setColor_Vehicle(String color_Vehicle) {
        this.color_Vehicle = color_Vehicle;
    }

    public double getPrice_Vehicle() {
        return price_Vehicle;
    }

    public void setPrice_Vehicle(double price_Vehicle) {
        this.price_Vehicle = price_Vehicle;
    }

    public String getBrand_Vehicle() {
        return brand_Vehicle;
    }

    public void setBrand_Vehicle(String brand_Vehicle) {
        this.brand_Vehicle = brand_Vehicle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getProductYear() {
        return productYear;
    }

    public void setProductYear(int productYear) {
        this.productYear = productYear;
    }

    @Override
    public String toString() {
        return String.format("#%-4s #%-10s #%-11s #%-11.2f #%-14s #%-14s #%-14d #", 
                ID_Vehicle,Name_Vehicle,color_Vehicle,price_Vehicle,brand_Vehicle,type,productYear);
    }
}

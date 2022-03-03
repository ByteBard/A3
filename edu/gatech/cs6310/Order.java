package edu.gatech.cs6310;

import java.util.TreeMap;

public class Order {
    private String orderID;
    private String customerAcc;
    private String droneID;
    private TreeMap<String, OrderItem> requestedItems;
    private int totalPrice;
    private int totalWeight;

    public Order(String orderID, String customerAcc, String droneID) {
        this.orderID = orderID;
        this.customerAcc = customerAcc;
        this.droneID = droneID;
        requestedItems = new TreeMap<>();
        totalPrice = 0;
        totalWeight = 0;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getCustomerAcc() {
        return customerAcc;
    }

    public String getDroneID() {
        return droneID;
    }

    public TreeMap<String, OrderItem> getRequestedItems() {
        return requestedItems;
    }
}

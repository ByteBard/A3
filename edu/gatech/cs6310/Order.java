package edu.gatech.cs6310;

import java.util.TreeMap;

public class Order {
    private String orderID;
    private String storeName;
    private String customerAcc;
    private String droneID;
    private TreeMap<String, Item> requestedItems;

    public Order(String orderID, String storeName, String customerAcc, String droneID) {
        this.orderID = orderID;
        this.storeName = storeName;
        this.customerAcc = customerAcc;
        this.droneID = droneID;
        requestedItems = new TreeMap<>();
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCustomerAcc() {
        return customerAcc;
    }

    public void setCustomerAcc(String customerAcc) {
        this.customerAcc = customerAcc;
    }

    public String getDroneID() {
        return droneID;
    }

    public void setDroneID(String droneID) {
        this.droneID = droneID;
    }

    public TreeMap<String, Item> getRequestedItems() {
        return requestedItems;
    }

    public void setRequestedItems(TreeMap<String, Item> requestedItems) {
        this.requestedItems = requestedItems;
    }
}

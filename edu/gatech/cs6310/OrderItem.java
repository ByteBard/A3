package edu.gatech.cs6310;

public class OrderItem {
    private String name;
    private int totalWeight;
    private int totalPrice;
    private int quantity;

    public OrderItem(String name, int totalWeight, int totalPrice, int quantity) {
        this.name = name;
        this.totalWeight = totalWeight;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

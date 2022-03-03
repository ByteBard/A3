package edu.gatech.cs6310;

public class Store {
    private String name;
    private int revenue;

    public Store(String name, int revenue) {
        this.name = name;
        this.revenue = revenue;
    }

    public String getName() {
        return name;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }
}

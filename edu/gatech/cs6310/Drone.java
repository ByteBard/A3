package edu.gatech.cs6310;

public class Drone {
    private String comboID;
    private String droneID;
    private String storeName;
    private int totalCap;
    private int remainingCap;
    private int totalTrips;
    private int remainingTrips;
    private int pendingOrderNum;

    public Drone(String storeName, String droneID, int totalCap, int remainingCap, int tripsInitial, int tripsLeft) {
        this.comboID = storeName + "_" + droneID;
        this.storeName = storeName;
        this.droneID = droneID;
        this.totalCap = totalCap;
        this.remainingCap = remainingCap;
        this.totalTrips = tripsInitial;
        this.remainingTrips = tripsLeft;
        this.pendingOrderNum = 0;
    }

    public int getPendingOrderNum() {
        return pendingOrderNum;
    }

    public String getComboID() {
        return comboID;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getDroneID() {
        return droneID;
    }

    public int getTotalCap() {
        return totalCap;
    }

    public int getRemainingCap() {
        return remainingCap;
    }

    public void setRemainingCap(int remainingCap) {
        this.remainingCap = remainingCap;
    }

    public int getRemainingTrips() {
        return remainingTrips;
    }

    public void completeOneTrip() {
        this.remainingTrips--;
    }

    public void pendingOrderNumUp() {
        pendingOrderNum++;
    }

    public void pendingOrderDown() {
        pendingOrderNum--;
    }
}

package edu.gatech.cs6310;

public class Drone {
    private String comboID;
    private String droneID;
    private String storeName;
    private int totalCap;
    private int remainingCap;
    private int totalTrips;
    private int remainingTrips;

    public Drone(String storeName, String droneID, int totalCap, int remainingCap, int tripsInitial, int tripsLeft) {
        this.comboID = storeName + "_" + droneID;
        this.storeName = storeName;
        this.droneID = droneID;
        this.totalCap = totalCap;
        this.remainingCap = remainingCap;
        this.totalTrips = tripsInitial;
        this.remainingTrips = tripsLeft;
    }

    public String getComboID() {
        return comboID;
    }

    public void setComboID(String comboID) {
        this.comboID = comboID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDroneID() {
        return droneID;
    }

    public void setDroneID(String droneID) {
        this.droneID = droneID;
    }

    public int getTotalCap() {
        return totalCap;
    }

    public void setTotalCap(int totalCap) {
        this.totalCap = totalCap;
    }

    public int getRemainingCap() {
        return remainingCap;
    }

    public void setRemainingCap(int remainingCap) {
        this.remainingCap = remainingCap;
    }

    public int getTotalTrips() {
        return totalTrips;
    }

    public void setTotalTrips(int totalTrips) {
        this.totalTrips = totalTrips;
    }

    public int getRemainingTrips() {
        return remainingTrips;
    }

    public void setRemainingTrips(int remainingTrips) {
        this.remainingTrips = remainingTrips;
    }

    public void completeOneTrip() {
        this.remainingTrips--;
    }
}

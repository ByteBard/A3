package edu.gatech.cs6310;

public class Item {
    private String name;
    private int unitWeight;

    public Item(String name, int unitWeight) {
        this.name = name;
        this.unitWeight = unitWeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnitWeight() {
        return unitWeight;
    }

    public void setUnitWeight(int unitWeight) {
        this.unitWeight = unitWeight;
    }

}

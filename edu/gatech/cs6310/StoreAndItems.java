package edu.gatech.cs6310;

import java.util.TreeMap;

public class StoreAndItems {
    private TreeMap<String, Item> allItems;
    private Store store;

    public StoreAndItems(Store store, TreeMap<String, Item> allItems) {
        this.allItems = allItems;
        this.store = store;
    }

    public TreeMap<String, Item> getAllItems() {
        return allItems;
    }

    public void setAllItems(TreeMap<String, Item> allItems) {
        this.allItems = allItems;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void sellNewItemForStore(Item item){
        if(allItems.containsKey(item.getName())){
            System.out.println(Utility.duplicateStoreItemMsg);
        }else{
            allItems.put(item.getName(), item);
            System.out.println(Utility.changeCompleteMsg);
        }
    }
}

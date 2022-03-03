package edu.gatech.cs6310;

import java.util.TreeMap;

public class ItemPool {
    private TreeMap<String, Item> allItems;
    public ItemPool(TreeMap<String, Item> allItems) {
        this.allItems = allItems;
    }

    public TreeMap<String, Item> getAllItems() {
        return allItems;
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

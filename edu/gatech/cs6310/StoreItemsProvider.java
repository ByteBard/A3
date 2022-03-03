package edu.gatech.cs6310;

import java.util.HashMap;
import java.util.TreeMap;

public class StoreItemsProvider {
    private HashMap<String, ItemPool> allStoresWithItems;

    public StoreItemsProvider() {
        this.allStoresWithItems = new HashMap<String, ItemPool>();
    }

    public HashMap<String, ItemPool> getAllStoresWithItems() {
        return allStoresWithItems;
    }

    public void displayStoreItems(String storeName, StoreProvider storeProvider){
        if(!storeProvider.getAllStoresWithNameMap().containsKey(storeName)){
            System.out.println(Utility.nonExistingStoreMsg);
        }else {
            if(allStoresWithItems.containsKey(storeName)){
                ItemPool existingItemPool = allStoresWithItems.get(storeName);
                for (Item item: existingItemPool.getAllItems().values()) {
                    System.out.println(item.getName() + "," + item.getUnitWeight());
                }
                System.out.println(Utility.displayCompleteMsg);
            }else{
                System.out.println(Utility.displayCompleteMsg);
            }
        }
    }

    public void sellItemForStore(StoreProvider storeProvider, String storeName, String itemName, int unitWeight) {
        if (!storeProvider.getAllStoresWithNameMap().containsKey(storeName)) {
            System.out.println(Utility.nonExistingStoreMsg);
        } else {
            Store store = storeProvider.GetByStoreName(storeName);
            if (store == null) {
                System.out.println(Utility.nonExistingStoreMsg);
            } else {
                Item item = new Item(itemName, unitWeight);
                if(allStoresWithItems.containsKey(storeName)){
                    ItemPool existingItemPool = allStoresWithItems.get(storeName);
                    existingItemPool.sellNewItemForStore(item);
                }else{
                    TreeMap<String, Item> defaultItemList = new TreeMap<>();
                    defaultItemList.put(itemName, item);
                    allStoresWithItems.put(storeName, new ItemPool(defaultItemList));
                    System.out.println(Utility.changeCompleteMsg);
                }
            }
        }
    }
}

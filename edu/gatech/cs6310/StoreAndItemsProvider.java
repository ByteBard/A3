package edu.gatech.cs6310;

import java.util.HashMap;
import java.util.TreeMap;

public class StoreAndItemsProvider {
    private HashMap<String, StoreAndItems> allStoresWithItems;
    private StoreProvider storeProvider;

    public StoreAndItemsProvider(StoreProvider storeProvider) {
        this.allStoresWithItems = new HashMap<String, StoreAndItems>();
        this.storeProvider = storeProvider;
    }

    public HashMap<String, StoreAndItems> getAllStoresWithItems() {
        return allStoresWithItems;
    }

    public void setAllStoresWithItems(HashMap<String, StoreAndItems> allStoresWithItems) {
        this.allStoresWithItems = allStoresWithItems;
    }

    public void displayStoreItems(String storeName){
        if(!storeProvider.getAllStoresWithNameMap().containsKey(storeName)){
            System.out.println(Utility.nonExistingStoreMsg);
        }else {
            if(allStoresWithItems.containsKey(storeName)){
                StoreAndItems existingStoreAndItems = allStoresWithItems.get(storeName);
                for (Item item: existingStoreAndItems.getAllItems().values()) {
                    System.out.println(item.getName() + "," + item.getUnitWeight());
                }
                System.out.println(Utility.displayCompleteMsg);
            }else{
                System.out.println(Utility.displayCompleteMsg);
            }
        }
    }

    public void sellItemForStore(String storeName, String itemName, int unitWeight) {
        if (!storeProvider.getAllStoresWithNameMap().containsKey(storeName)) {
            System.out.println(Utility.nonExistingStoreMsg);
        } else {
            Store store = storeProvider.GetByStoreName(storeName);
            if (store == null) {
                System.out.println(Utility.nonExistingStoreMsg);
            } else {
                Item item = new Item(itemName, unitWeight);
                if(allStoresWithItems.containsKey(storeName)){
                    StoreAndItems existingStoreAndItems = allStoresWithItems.get(storeName);
                    existingStoreAndItems.sellNewItemForStore(item);
                }else{
                    TreeMap<String, Item> defaultItemList = new TreeMap<>();
                    defaultItemList.put(itemName, item);
                    allStoresWithItems.put(storeName, new StoreAndItems(store, defaultItemList));
                    System.out.println(Utility.changeCompleteMsg);
                }
            }
        }
    }
}

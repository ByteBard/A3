package edu.gatech.cs6310;

import java.util.*;

public class StoreProvider {
    private TreeMap<String, Store> allStoresWithNameMap;
    public TreeMap<String, Store> getAllStoresWithNameMap() {
        return allStoresWithNameMap;
    }

    public void setAllStoresWithNameMap(TreeMap<String, Store> allStoresWithNameMap) {
        this.allStoresWithNameMap = allStoresWithNameMap;
    }

    public StoreProvider() {
        allStoresWithNameMap = new TreeMap<>();
    }

    public Store GetByStoreName(String name){
        if(allStoresWithNameMap.containsKey(name)) return allStoresWithNameMap.get(name);
        return null;
    }

    public void DisplayStore(Store store) {
        System.out.printf("name:%s,revenue:%d\n", store.getName(), store.getRevenue());
    }

    public void AddNewStore(String name, int revenue) {
        if (!allStoresWithNameMap.isEmpty() && allStoresWithNameMap.containsKey(name)) {
            System.out.println(Utility.duplicateStoreMsg);
        } else {
            Store store = new Store(name, revenue);
            allStoresWithNameMap.put(store.getName(), store);
            System.out.println(Utility.changeCompleteMsg);
        }
    }

    public void DisplayStores() {
        if (allStoresWithNameMap.isEmpty()) {
            System.out.println(Utility.displayCompleteMsg);
        } else {
            for (Store store : allStoresWithNameMap.values()) {
                DisplayStore(store);
            }
            System.out.println(Utility.displayCompleteMsg);
        }
    }
}

package edu.gatech.cs6310;

import java.util.HashMap;
import java.util.TreeMap;

public class StoreAndDronesProvider {
    private HashMap<String, TreeMap<String, Drone>> storeDronesWithStoreNameMap;
    private StoreProvider storeProvider;

    public HashMap<String, TreeMap<String, Drone>> getStoreDronesWithStoreNameMap() {
        return storeDronesWithStoreNameMap;
    }

    public StoreProvider getStoreProvider() {
        return storeProvider;
    }

    public StoreAndDronesProvider(StoreProvider storeProvider) {
        this.storeDronesWithStoreNameMap = new HashMap<>();
        this.storeProvider = storeProvider;
    }

    public void addDroneToStore(String storeName, String droneID, int totalCap, int totalTrips) {
        Drone drone = new Drone(storeName, droneID, totalCap, totalCap, totalTrips, totalTrips);
        if (storeProvider.getAllStoresWithNameMap().containsKey(storeName)) {
            if (storeDronesWithStoreNameMap.containsKey(storeName)) {
                TreeMap<String, Drone> existingDrones = storeDronesWithStoreNameMap.get(storeName);
                if (existingDrones.containsKey(droneID)) {
                    System.out.println(Utility.duplicateDroneIdMsg);
                } else {
                    existingDrones.put(droneID, drone);
                    storeDronesWithStoreNameMap.put(storeName, existingDrones);
                    System.out.println(Utility.changeCompleteMsg);
                }
            } else {
                TreeMap<String, Drone> defaultDroneMap = new TreeMap<>();
                defaultDroneMap.put(droneID, drone);
                storeDronesWithStoreNameMap.put(storeName, defaultDroneMap);
                System.out.println(Utility.changeCompleteMsg);
            }
        } else {
            System.out.println(Utility.nonExistingStoreMsg);
        }
    }

}

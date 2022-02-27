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

    public void displayDronesForStore(String storeName) {
        //droneID:1,total_cap:40,num_orders:0,remaining_cap:40,trips_left:1
        if (storeProvider.getAllStoresWithNameMap().containsKey(storeName)) {
            if (storeDronesWithStoreNameMap.containsKey(storeName)) {
                TreeMap<String, Drone> existingDrones = storeDronesWithStoreNameMap.get(storeName);
                for (Drone drone : existingDrones.values()) {
                    System.out.println(
                            "droneID:" + drone.getDroneID() + "," +
                            "total_cap:" + drone.getTotalCap() + "," +
                            "num_orders:" + 0 + "," +
                            "remaining_cap:" + drone.getRemainingCap() + "," +
                            "trips_left:" + drone.getRemainingTrips()
                    );
                }
                System.out.println(Utility.displayCompleteMsg);
            } else {
                System.out.println("DEBUG: Empty Drones in: " + storeName);
            }
        } else {
            System.out.println(Utility.nonExistingStoreMsg);
        }
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

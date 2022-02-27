package edu.gatech.cs6310;

import java.util.HashMap;
import java.util.TreeMap;

public class FlyDroneProvider {
    private PilotDroneBiPair pilotDroneBiPair;
    private StoreAndDronesProvider storeAndDronesProvider;
    private PilotProvider pilotProvider;

    public FlyDroneProvider(StoreAndDronesProvider storeAndDronesProvider, PilotProvider pilotProvider) {
        this.pilotDroneBiPair = new PilotDroneBiPair();
        this.storeAndDronesProvider = storeAndDronesProvider;
        this.pilotProvider = pilotProvider;
    }

    public void flyDrone(String storeName, String droneID, String pilotAcc) {
        if (pilotProvider.getAllPilotsWithAccountMap().containsKey(pilotAcc)) {
            Pilot pilotToBeAssigned = pilotProvider.getAllPilotsWithAccountMap().get(pilotAcc);
            if (storeAndDronesProvider.getStoreProvider().getAllStoresWithNameMap().containsKey(storeName)) {
                if (storeAndDronesProvider.getStoreDronesWithStoreNameMap().containsKey(storeName)) {
                    TreeMap<String, Drone> currentDronesForStore = storeAndDronesProvider.getStoreDronesWithStoreNameMap().get(storeName);
                    if (currentDronesForStore.containsKey(droneID)) {
                        Drone droneToBeAssigned = currentDronesForStore.get(droneID);
                        if(!(droneToBeAssigned.getStoreName()).equals(storeName)){
                            System.out.println("DEBUG: " + storeName + " has drone with different storeName: " + droneToBeAssigned.getStoreName());
                        }
                        pilotDroneBiPair.updateBiPair(pilotToBeAssigned, droneToBeAssigned);
                    } else {
                        System.out.println(Utility.nonExistingDroneIdMsg);
                    }
                } else {
                    System.out.println("DEBUG: " + storeName + " not Added to storeDronesWithStoreNameMap yet!");
                }

            } else {
                System.out.println(Utility.nonExistingStoreMsg);
            }
        } else {
            System.out.println(Utility.nonExistingPilotAccMsg);
        }
    }
}

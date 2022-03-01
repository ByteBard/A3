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

    public StoreAndDronesProvider getStoreAndDronesProvider() {
        return storeAndDronesProvider;
    }

    public PilotDroneBiPair getPilotDroneBiPair() {
        return pilotDroneBiPair;
    }

    public void flyDrone(String storeName, String droneID, String pilotAcc) {
        if (pilotProvider.getAllPilotsWithAccountMap().containsKey(pilotAcc)) {
            Pilot pilotToBeAssigned = pilotProvider.getAllPilotsWithAccountMap().get(pilotAcc);
            if (storeAndDronesProvider.getStoreProvider().getAllStoresWithNameMap().containsKey(storeName)) {
                if (storeAndDronesProvider.getStoreDronesWithStoreNameMap().containsKey(storeName)) {
                    TreeMap<String, Drone> currentDronesForStore = storeAndDronesProvider.getStoreDronesWithStoreNameMap().get(storeName);
                    if (currentDronesForStore.containsKey(droneID)) {
                        Drone droneToBeAssigned = currentDronesForStore.get(droneID);
                        if (!(droneToBeAssigned.getStoreName()).equals(storeName)) {
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

    public void displayDronesForStore(String storeName) {
        //droneID:1,total_cap:40,num_orders:0,remaining_cap:40,trips_left:1
        if (storeAndDronesProvider.getStoreProvider().getAllStoresWithNameMap().containsKey(storeName)) {
            if (storeAndDronesProvider.getStoreDronesWithStoreNameMap().containsKey(storeName)) {
                TreeMap<String, Drone> existingDrones = storeAndDronesProvider.getStoreDronesWithStoreNameMap().get(storeName);
                for (Drone drone : existingDrones.values()) {
                    String msg =
                            "droneID:" + drone.getDroneID() + "," +
                                    "total_cap:" + drone.getTotalCap() + "," +
                                    "num_orders:" + drone.getPendingOrderNum() + "," +
                                    "remaining_cap:" + drone.getRemainingCap() + "," +
                                    "trips_left:" + drone.getRemainingTrips();
                    if (pilotDroneBiPair.getDroneToPilot().containsKey(drone.getComboID())) {
                        Pilot matchedPilot = pilotDroneBiPair.getDroneToPilot().get(drone.getComboID());
                        msg += "," + "flown_by:" + matchedPilot.getFirstName() + "_" + matchedPilot.getLastName();
                    }

                    System.out.println(msg);
                }
                System.out.println(Utility.displayCompleteMsg);
            } else {
                //System.out.println("DEBUG: Empty Drones in: " + storeName);
                System.out.println(Utility.displayCompleteMsg);
            }
        } else {
            System.out.println(Utility.nonExistingStoreMsg);
        }
    }
}

package edu.gatech.cs6310;

import java.util.Scanner;

public class DeliveryService {

    public void commandLoop() {
        Scanner commandLineInput = new Scanner(System.in);
        String wholeInputLine;
        String[] tokens;
        final String DELIMITER = ",";

        StoreProvider storeProvider = new StoreProvider();
        StoreItemsProvider storeItemsProvider = new StoreItemsProvider();
        PilotProvider pilotProvider = new PilotProvider();
        StoreDronesProvider storeDronesProvider = new StoreDronesProvider();
        FlyDroneProvider flyDroneProvider = new FlyDroneProvider();
        CustomerProvider customerProvider = new CustomerProvider();
        OrderProvider orderProvider = new OrderProvider();
        while (true) {
            try {
                // Determine the next command and echo it to the monitor for testing purposes
                wholeInputLine = commandLineInput.nextLine();
                tokens = wholeInputLine.split(DELIMITER);
                System.out.println("> " + wholeInputLine);

                //Make Store
                if (tokens[0].equals("make_store")) {
                    if (!Utility.isEmpty(tokens[1]) && Utility.isInteger(tokens[2])) {
                        String storeName = tokens[1];
                        int storeRevenue = Integer.parseInt(tokens[2]);
                        storeProvider.AddNewStore(storeName, storeRevenue);
                    }
                }

                //Display Stores
                else if (tokens[0].equals("display_stores")) {
                    storeProvider.DisplayStores();
                }

                //Sell Item
                else if (tokens[0].equals("sell_item")) {
                    if (
                            !Utility.isEmpty(tokens[1]) &&
                                    !Utility.isEmpty(tokens[2])
                    ) {
                        String storeName = tokens[1];
                        String itemName = tokens[2];
                        int unitWeight = Integer.parseInt(tokens[3]);
                        storeItemsProvider.sellItemForStore(storeProvider, storeName, itemName, unitWeight);
                    }
                }

                //Display Items
                else if (tokens[0].equals("display_items")) {
                    if (
                            !Utility.isEmpty(tokens[1])
                    ) {
                        String storeName = tokens[1];
                        storeItemsProvider.displayStoreItems(storeName, storeProvider);
                    }
                }

                //Make Pilot
                else if (tokens[0].equals("make_pilot")) {
                    if (
                            !Utility.isEmpty(tokens[1]) &&
                                    !Utility.isEmpty(tokens[2]) &&
                                    !Utility.isEmpty(tokens[3]) &&
                                    !Utility.isEmpty(tokens[4]) &&
                                    !Utility.isEmpty(tokens[5]) &&
                                    !Utility.isEmpty(tokens[6]) &&
                                    Utility.isInteger(tokens[7]) &&
                                    Utility.isValidateNumber(tokens[4])
                    ) {
                        String account = tokens[1];
                        String firstName = tokens[2];
                        String lastName = tokens[3];
                        String phoneNum = tokens[4];
                        String taxID = tokens[5];
                        String licenceID = tokens[6];
                        int experience = Integer.parseInt(tokens[7]);
                        pilotProvider.addNewPilot(account, firstName, lastName, phoneNum, taxID, licenceID, experience);
                    }
                }

                //Display Pilots
                else if (tokens[0].equals("display_pilots")) {
                    pilotProvider.displayAllPilots();
                } else if (tokens[0].equals("make_drone")) {
                    if (
                            !Utility.isEmpty(tokens[1]) &&
                                    !Utility.isEmpty(tokens[2]) &&
                                    !Utility.isEmpty(tokens[3]) &&
                                    !Utility.isEmpty(tokens[4]) &&
                                    Utility.isInteger(tokens[3]) &&
                                    Utility.isInteger(tokens[4])
                    ) {
                        String storeName = tokens[1];
                        String droneID = tokens[2];
                        int totalCap = Integer.parseInt(tokens[3]);
                        int tripsInitial = Integer.parseInt(tokens[4]);
                        storeDronesProvider.addDroneToStore(storeProvider, storeName, droneID, totalCap, tripsInitial);
                    }
                } else if (tokens[0].equals("display_drones")) {
                    String storeName = tokens[1];
                    if (!Utility.isEmpty(tokens[1])) {
                        flyDroneProvider.displayDronesForStore(storeProvider, storeDronesProvider, storeName);
                    }
                } else if (tokens[0].equals("fly_drone")) {
                    if (!Utility.isEmpty(tokens[1]) &&
                            !Utility.isEmpty(tokens[2]) &&
                            !Utility.isEmpty(tokens[3])) {
                        String storeName = tokens[1];
                        String droneID = tokens[2];
                        String pilotAcc = tokens[3];
                        flyDroneProvider.flyDrone(storeProvider, pilotProvider, storeDronesProvider, storeName, droneID, pilotAcc);
                    }
                } else if (tokens[0].equals("make_customer")) {
                    if (
                            !Utility.isEmpty(tokens[1]) &&
                                    !Utility.isEmpty(tokens[2]) &&
                                    !Utility.isEmpty(tokens[3]) &&
                                    !Utility.isEmpty(tokens[4]) &&
                                    !Utility.isEmpty(tokens[5]) &&
                                    !Utility.isEmpty(tokens[6]) &&
                                    Utility.isValidateNumber(tokens[4]) &&
                                    Utility.isInteger(tokens[5]) &&
                                    Utility.isInteger(tokens[6])
                    ) {
                        String customerAcc = tokens[1];
                        String firstName = tokens[2];
                        String lastName = tokens[3];
                        String phoneNum = tokens[4];
                        int rating = Integer.parseInt(tokens[5]);
                        int credits = Integer.parseInt(tokens[6]);
                        customerProvider.addNewCustomer(customerAcc, firstName, lastName, phoneNum, rating, credits);
                    }
                } else if (tokens[0].equals("display_customers")) {
                    customerProvider.displayAllCustomers();
                } else if (tokens[0].equals("start_order")) {
                    if (
                        !Utility.isEmpty(tokens[1]) &&
                        !Utility.isEmpty(tokens[2]) &&
                        !Utility.isEmpty(tokens[3]) &&
                        !Utility.isEmpty(tokens[4])
                    ) {
                        String storeName = tokens[1];
                        String orderID = tokens[2];
                        String droneID = tokens[3];
                        String customerAcc = tokens[4];
                        orderProvider.startOrderForStore(storeProvider, storeName, orderID, droneID, customerAcc, storeDronesProvider, customerProvider);
                    }
                } else if (tokens[0].equals("display_orders")) {
                    if(!Utility.isEmpty(tokens[1])){
                        String storeName = tokens[1];
                        orderProvider.displayOrders(storeName, storeProvider);
                    }

                } else if (tokens[0].equals("request_item")) {
                    if(
                        !Utility.isEmpty(tokens[1]) &&
                        !Utility.isEmpty(tokens[2]) &&
                        !Utility.isEmpty(tokens[3]) &&
                        !Utility.isEmpty(tokens[4]) &&
                        !Utility.isEmpty(tokens[5]) &&
                        Utility.isInteger(tokens[4]) &&
                        Utility.isInteger(tokens[5])
                    ){
                        String storeName = tokens[1];
                        String orderID = tokens[2];
                        String itemName = tokens[3];
                        int quantity = Integer.parseInt(tokens[4]);
                        int unitPrice = Integer.parseInt(tokens[5]);
                        orderProvider.requestItem(storeProvider, storeName, orderID, itemName, quantity, unitPrice, storeDronesProvider, storeItemsProvider, customerProvider);
                    }

                } else if (tokens[0].equals("purchase_order")) {
                    if(!Utility.isEmpty(tokens[1]) && !Utility.isEmpty(tokens[2])){
                        String storeName = tokens[1];
                        String orderID = tokens[2];
                        orderProvider.purchaseOrderSuccess(storeProvider, storeDronesProvider, storeName, orderID, flyDroneProvider, customerProvider);
                    }
                } else if (tokens[0].equals("cancel_order")) {
                    if(!Utility.isEmpty(tokens[1]) && !Utility.isEmpty(tokens[2])){
                        String storeName = tokens[1];
                        String orderID = tokens[2];
                        orderProvider.cancelOrderSuccess(storeProvider, storeDronesProvider, storeName, orderID, flyDroneProvider, customerProvider);
                    }
                } else if (tokens[0].equals("stop")) {
                    System.out.println("stop acknowledged");
                    break;

                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
            }
        }

        System.out.println("simulation terminated");
        commandLineInput.close();
    }
}

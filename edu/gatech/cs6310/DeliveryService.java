package edu.gatech.cs6310;
import java.util.Scanner;

public class DeliveryService {

    public void commandLoop() {
        Scanner commandLineInput = new Scanner(System.in);
        String wholeInputLine;
        String[] tokens;
        final String DELIMITER = ",";

        StoreProvider storeProvider = new StoreProvider();
        StoreAndItemsProvider storeAndItemsProvider = new StoreAndItemsProvider(storeProvider);
        PilotProvider pilotProvider = new PilotProvider();
        StoreAndDronesProvider storeAndDronesProvider = new StoreAndDronesProvider(storeProvider);
        FlyDroneProvider flyDroneProvider = new FlyDroneProvider(storeAndDronesProvider, pilotProvider);
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
                    String storeName = tokens[1];
                    int storeRevenue = Integer.parseInt(tokens[2]);
//                    System.out.println("store: " + storeName + ", revenue: " + storeRevenue);
                    storeProvider.AddNewStore(storeName, storeRevenue);
                }

                //Display Stores
                else if (tokens[0].equals("display_stores")) {
                    storeProvider.DisplayStores();
                }

                //Sell Item
                else if (tokens[0].equals("sell_item")) {
                    String storeName = tokens[1];
                    String itemName = tokens[2];
                    int unitWeight = Integer.parseInt(tokens[3]);
//                    System.out.println("store: " + storeName + ", item: " + itemName + ", weight: " + itemWeight);7
                    storeAndItemsProvider.sellItemForStore(storeName, itemName, unitWeight);
                }

                //Display Items
                else if (tokens[0].equals("display_items")) {
//                    System.out.println("store: " + tokens[1]);
                    String storeName = tokens[1];
                    storeAndItemsProvider.displayStoreItems(storeName);
                }

                //Make Pilot
                else if (tokens[0].equals("make_pilot")) {
                    String account = tokens[1];
                    String firstName = tokens[2];
                    String lastName = tokens[3];
                    String phoneNum = tokens[4];
                    String taxID = tokens[5];
                    String licenceID = tokens[6];
                    int experience = Integer.parseInt(tokens[7]);
                    pilotProvider.addNewPilot(account, firstName, lastName, phoneNum, taxID, licenceID, experience);

//                    System.out.print("account: " + tokens[1] + ", first_name: " + tokens[2] + ", last_name: " + tokens[3]);
//                    System.out.println(", phone: " + tokens[4] + ", tax: " + tokens[5] + ", license: " + tokens[6] + ", experience: " + tokens[7]);
                }

                //Display Pilots
                else if (tokens[0].equals("display_pilots")) {
                    pilotProvider.displayAllPilots();
//                    System.out.println("no parameters needed");
                }

                else if (tokens[0].equals("make_drone")) {
                    String storeName = tokens[1];
                    String droneID = tokens[2];
                    int totalCap = Integer.parseInt(tokens[3]);
                    int tripsInitial = Integer.parseInt(tokens[4]);
                    storeAndDronesProvider.addDroneToStore(storeName, droneID, totalCap, tripsInitial);
//                    System.out.println("store: " + tokens[1] + ", drone: " + tokens[2] + ", capacity: " + tokens[3] + ", fuel: " + tokens[4]);

                } else if (tokens[0].equals("display_drones")) {
                    String storeName = tokens[1];
                    flyDroneProvider.displayDronesForStore(storeName);
//                    System.out.println("store: " + tokens[1]);

                } else if (tokens[0].equals("fly_drone")) {
                    String storeName = tokens[1];
                    String droneID = tokens[2];
                    String pilotAcc = tokens[3];
                    flyDroneProvider.flyDrone(storeName, droneID, pilotAcc);
//                    System.out.println("store: " + tokens[1] + ", drone: " + tokens[2] + ", pilot: " + tokens[3]);

                } else if (tokens[0].equals("make_customer")) {
                    String customerAcc = tokens[1];
                    String firstName = tokens[2];
                    String lastName = tokens[3];
                    String phoneNum = tokens[4];
                    int rating = Integer.parseInt(tokens[5]);
                    int credits = Integer.parseInt(tokens[6]);
                    customerProvider.addNewCustomer(customerAcc, firstName, lastName, phoneNum, rating, credits);
//                    System.out.print("account: " + tokens[1] + ", first_name: " + tokens[2] + ", last_name: " + tokens[3]);
//                    System.out.println(", phone: " + tokens[4] + ", rating: " + tokens[5] + ", credit: " + tokens[6]);

                } else if (tokens[0].equals("display_customers")) {
                    customerProvider.displayAllCustomers();
//                    System.out.println("no parameters needed");

                } else if (tokens[0].equals("start_order")) {
                    String storeName = tokens[1];
                    String orderID = tokens[2];
                    String droneID = tokens[3];
                    String customerAcc = tokens[4];
                    orderProvider.startOrderForStore(storeName, orderID, droneID, customerAcc, storeAndDronesProvider, customerProvider);
//                    System.out.println("store: " + tokens[1] + ", order: " + tokens[2] + ", drone: " + tokens[3] + ", customer: " + tokens[4]);

                } else if (tokens[0].equals("display_orders")) {
                    String storeName = tokens[1];
                    orderProvider.displayOrders(storeName, storeProvider);
//                    System.out.println("store: " + tokens[1]);

                } else if (tokens[0].equals("request_item")) {
                    String storeName = tokens[1];
                    String orderID = tokens[2];
                    String itemName = tokens[3];
                    int quantity = Integer.parseInt(tokens[4]);
                    int unitPrice = Integer.parseInt(tokens[5]);
                    orderProvider.requestItem(storeName, orderID, itemName, quantity, unitPrice, storeAndDronesProvider, storeAndItemsProvider, customerProvider);
//                    System.out.println("store: " + tokens[1] + ", order: " + tokens[2] + ", item: " + tokens[3] + ", quantity: " + tokens[4] + ", unit_price: " + tokens[5]);

                } else if (tokens[0].equals("purchase_order")) {
                    String storeName = tokens[1];
                    String orderID = tokens[2];
                    orderProvider.purchaseOrderSuccess(storeName, orderID, flyDroneProvider, customerProvider);
//                    System.out.println("store: " + tokens[1] + ", order: " + tokens[2]);

                } else if (tokens[0].equals("cancel_order")) {
                    String storeName = tokens[1];
                    String orderID = tokens[2];
                    orderProvider.cancelOrderSuccess(storeName, orderID, flyDroneProvider, customerProvider);
//                    System.out.println("store: " + tokens[1] + ", order: " + tokens[2]);

                } else if (tokens[0].equals("stop")) {
                    System.out.println("stop acknowledged");
                    break;

                } else {
                    System.out.println("command " + tokens[0] + " NOT acknowledged");
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

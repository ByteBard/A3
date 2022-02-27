package edu.gatech.cs6310;

import java.util.HashMap;
import java.util.TreeMap;

public class OrderProvider {
    private HashMap<String, TreeMap<String, Order>> storeOrdersMap;

    public OrderProvider() {
        storeOrdersMap = new HashMap<>();
    }

    public HashMap<String, TreeMap<String, Order>> getStoreOrdersMap() {
        return storeOrdersMap;
    }

    public void addOrderToStore(String storeName, String orderID, String droneID, String customerAcc,
                                StoreAndDronesProvider storeAndDronesProvider, CustomerProvider customerProvider,
                                TreeMap<String, Order> currentOrders) {
        if (storeAndDronesProvider.getStoreDronesWithStoreNameMap().get(storeName).containsKey(droneID)) {
            if (!customerProvider.getAllCustomers().containsKey(customerAcc)) {
                System.out.println(Utility.nonExistingCustomerMsg);
            } else {
                Order order = new Order(orderID, storeName, customerAcc, droneID);
                currentOrders.put(orderID, order);
                storeOrdersMap.put(storeName, currentOrders);
                System.out.println(Utility.changeCompleteMsg);
            }
        } else {
            System.out.println(Utility.nonExistingDroneIdMsg);
        }
    }

    public void startOrderForStore(String storeName, String orderID, String droneID, String customerAcc,
                                   StoreAndDronesProvider storeAndDronesProvider, CustomerProvider customerProvider) {
        if (storeAndDronesProvider.getStoreProvider().getAllStoresWithNameMap().containsKey(storeName)) {
            if (storeOrdersMap.containsKey(storeName)) {
                TreeMap<String, Order> currentOrders = storeOrdersMap.get(storeName);
                if (currentOrders.containsKey(orderID)) {
                    System.out.println(Utility.duplicateOrderIDMsg);
                } else {
                    addOrderToStore(storeName, orderID, droneID, customerAcc, storeAndDronesProvider, customerProvider, currentOrders);
                }
            } else {
                TreeMap<String, Order> defaultOrders = new TreeMap<>();
                addOrderToStore(storeName, orderID, droneID, customerAcc, storeAndDronesProvider, customerProvider, defaultOrders);
            }
        } else {
            System.out.println(Utility.nonExistingStoreMsg);
        }
    }

    public void displayOrders(String storeName, StoreProvider storeProvider) {
        if (storeProvider.getAllStoresWithNameMap().containsKey(storeName)) {
            if (storeOrdersMap.containsKey(storeName)) {
                TreeMap<String, Order> currentOrders = storeOrdersMap.get(storeName);
                for (Order order : currentOrders.values()) {
                    displayOrder(order);
                }
                System.out.println(Utility.displayCompleteMsg);
            } else {
                System.out.println("DEBUG: " + storeName + " not exist in storeOrdersMap");
            }
        } else {
            System.out.println(Utility.nonExistingStoreMsg);
        }
    }

    public void displayOrder(Order order) {
        System.out.println("orderID:" + order.getOrderID());
    }

    public void requestItem(String storeName, String orderID, String itemName, int quantity, int unitPrice,
                            StoreAndDronesProvider storeAndDronesProvider, StoreAndItemsProvider storeAndItemsProvider, CustomerProvider customerProvider) {
        if (storeAndDronesProvider.getStoreProvider().getAllStoresWithNameMap().containsKey(storeName)) {
            if (storeOrdersMap.containsKey(storeName)) {
                TreeMap<String, Order> currentOrders = storeOrdersMap.get(storeName);
                if (currentOrders.containsKey(orderID)) {
                    Order targetOrder = currentOrders.get(orderID);
                    Customer customer = customerProvider.getAllCustomers().get(targetOrder.getCustomerAcc());
                    if (quantity * unitPrice > customer.getRemainingCredits()) {
                        System.out.println(Utility.overCreditMsg);
                    } else {
                        if (storeAndItemsProvider.getAllStoresWithItems().containsKey(storeName)) {
                            StoreAndItems currentStoreAndItems = storeAndItemsProvider.getAllStoresWithItems().get(storeName);
                            if (currentStoreAndItems.getAllItems().containsKey(itemName)) {
                                Item targetItem = currentStoreAndItems.getAllItems().get(itemName);
                                Drone targetDrone = storeAndDronesProvider.getStoreDronesWithStoreNameMap().get(storeName).get(targetOrder.getDroneID());
                                if(targetDrone.getRemainingCap() < targetItem.getWeight() * quantity){
                                    System.out.println(Utility.overWeightMsg);
                                }else{
                                    //Finally add new item to order!
                                    targetOrder.addRequestedItem(targetItem);

                                }
                            } else {
                                System.out.println(Utility.nonExistingItemMsg);
                            }
                        } else {
                            System.out.println("DEBUG: " + storeName + " not exist in allStoresWithItems");
                        }
                    }
                } else {
                    System.out.println(Utility.nonExistingOrderMsg);
                }
            } else {
                System.out.println("DEBUG: " + storeName + " not exist in storeOrdersMap");
            }
        } else {
            System.out.println(Utility.nonExistingStoreMsg);
        }
    }
}

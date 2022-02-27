package edu.gatech.cs6310;

import java.util.HashMap;
import java.util.TreeMap;

public class OrderProvider {
    private HashMap<String, TreeMap<String, Order>> storeOrdersMap;

    public OrderProvider() {
        storeOrdersMap = new HashMap<>();
    }

    public void addOrderToStore(String storeName, String orderID, String droneID, String customerAcc,
                                StoreAndDronesProvider storeAndDronesProvider, CustomerProvider customerProvider,
                                TreeMap<String, Order> currentOrders){
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
            }else{
                TreeMap<String, Order> defaultOrders = new TreeMap<>();
                addOrderToStore(storeName, orderID, droneID, customerAcc, storeAndDronesProvider, customerProvider, defaultOrders);
            }
        } else {
            System.out.println(Utility.nonExistingStoreMsg);
        }
    }

    public void displayOrders(String storeName, StoreProvider storeProvider){
        if(storeProvider.getAllStoresWithNameMap().containsKey(storeName)){
            if (storeOrdersMap.containsKey(storeName)){
                TreeMap<String, Order> currentOrders = storeOrdersMap.get(storeName);
                for (Order order: currentOrders.values()){
                    displayOrder(order);
                }
                System.out.println(Utility.displayCompleteMsg);
            }else{
                System.out.println("DEBUG: " + storeName + " not exist in storeOrdersMap");
            }
        }else {
            System.out.println(Utility.nonExistingStoreMsg);
        }
    }

    public void displayOrder(Order order){
        System.out.println("orderID:" + order.getOrderID());
    }
}

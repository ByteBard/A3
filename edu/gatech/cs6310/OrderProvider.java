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
            Drone drone = storeAndDronesProvider.getStoreDronesWithStoreNameMap().get(storeName).get(droneID);
            if (!customerProvider.getAllCustomers().containsKey(customerAcc)) {
                System.out.println(Utility.nonExistingCustomerMsg);
            } else {
                Order order = new Order(orderID, storeName, customerAcc, droneID);
                currentOrders.put(orderID, order);
                storeOrdersMap.put(storeName, currentOrders);
                drone.pendingOrderNumUp();
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

    public boolean purchaseOrderSuccess(String storeName, String orderID, FlyDroneProvider flyDroneProvider, CustomerProvider customerProvider) {
        if (!flyDroneProvider.getStoreAndDronesProvider().getStoreProvider().getAllStoresWithNameMap().containsKey(storeName)) {
            System.out.println(Utility.nonExistingStoreMsg);
            return false;
        }

        if (!storeOrdersMap.containsKey(storeName)) {
            System.out.println("DEBUG: " + storeName + " not exist in storeOrdersMap");
            return false;
        }

        TreeMap<String, Order> currentOrders = storeOrdersMap.get(storeName);
        if (!currentOrders.containsKey(orderID)) {
            System.out.println(Utility.nonExistingOrderMsg);
            return false;
        }

        Order targetOrder = currentOrders.get(orderID);
        Customer customer = customerProvider.getAllCustomers().get(targetOrder.getCustomerAcc());
        Drone targetDrone = flyDroneProvider.getStoreAndDronesProvider().getStoreDronesWithStoreNameMap().get(storeName).get(targetOrder.getDroneID());
        Pilot matchedPilot = flyDroneProvider.getPilotDroneBiPair().getDroneToPilot().get(targetDrone.getComboID());

        customer.setTotalCredits(customer.getTotalCredits() - targetOrder.getTotalPrice());
        Store store = flyDroneProvider.getStoreAndDronesProvider().getStoreProvider().GetByStoreName(storeName);
        store.setRevenue(store.getRevenue() + targetOrder.getTotalPrice());
        targetDrone.completeOneTrip();
        targetDrone.pendingOrderDown();
        matchedPilot.completeOneDelivery();
        currentOrders.remove(orderID);
        System.out.println(Utility.changeCompleteMsg);
        return true;
    }

    public void displayOrder(Order order) {
        System.out.println("orderID:" + order.getOrderID());
        //item_name:pot_roast,total_quantity:3,total_cost:30,total_weight:15
        for (OrderItem orderItem : order.getRequestedItems().values()) {
            System.out.println(
                    "item_name:" + orderItem.getName() + "," +
                            "total_quantity:" + orderItem.getQuantity() + "," +
                            "total_cost:" + orderItem.getTotalPrice() + "," +
                            "total_weight:" + orderItem.getTotalWeight()
            );
        }
    }

    public void requestItem(String storeName, String orderID, String itemName, int quantity, int unitPrice,
                            StoreAndDronesProvider storeAndDronesProvider, StoreAndItemsProvider storeAndItemsProvider, CustomerProvider customerProvider) {
        if (storeAndDronesProvider.getStoreProvider().getAllStoresWithNameMap().containsKey(storeName)) {
            if (storeOrdersMap.containsKey(storeName)) {
                TreeMap<String, Order> currentOrders = storeOrdersMap.get(storeName);
                if (currentOrders.containsKey(orderID)) {
                    Order targetOrder = currentOrders.get(orderID);
                    Customer customer = customerProvider.getAllCustomers().get(targetOrder.getCustomerAcc());
                    int orderPrice = quantity * unitPrice;
                    if (quantity * unitPrice > customer.getRemainingCredits()) {
                        System.out.println(Utility.overCreditMsg);
                    } else {
                        if (storeAndItemsProvider.getAllStoresWithItems().containsKey(storeName)) {
                            StoreAndItems currentStoreAndItems = storeAndItemsProvider.getAllStoresWithItems().get(storeName);
                            if (currentStoreAndItems.getAllItems().containsKey(itemName)) {
                                Item targetItem = currentStoreAndItems.getAllItems().get(itemName);
                                Drone targetDrone = storeAndDronesProvider.getStoreDronesWithStoreNameMap().get(storeName).get(targetOrder.getDroneID());
                                int orderWeight = targetItem.getUnitWeight() * quantity;
                                if (targetDrone.getRemainingCap() < orderWeight) {
                                    System.out.println(Utility.overWeightMsg);
                                } else {
                                    //Finally add new item to order!
                                    addRequestedItem(targetOrder, targetItem, customer, targetDrone, orderWeight, orderPrice, quantity);
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

    public void addRequestedItem(Order targetOrder, Item item, Customer customer, Drone drone, int orderWeight, int orderPrice, int quantity) {
        boolean isSuccess = true;
        TreeMap<String, OrderItem> requestedItems = targetOrder.getRequestedItems();
        if (requestedItems.containsKey(item.getName())) {
            System.out.println(Utility.duplicateItemInOrderMsg);
            isSuccess = false;
        }

        int remainingCredits = customer.getRemainingCredits();
        int updatedCredits = remainingCredits - orderPrice;
        if (updatedCredits < 0) {
            System.out.println("DEBUG: illegal credit: " + updatedCredits);
            isSuccess = false;
        }

        int updatedWeight = drone.getRemainingCap() - orderWeight;
        if (updatedWeight < 0) {
            System.out.println("DEBUG: illegal weight: " + updatedWeight);
            isSuccess = false;
        }

        if (isSuccess) {
            targetOrder.setTotalPrice(targetOrder.getTotalPrice() + orderPrice);
            OrderItem orderItem = new OrderItem(item.getName(), 0, 0, 0);
            orderItem.setQuantity(quantity + orderItem.getQuantity());
            orderItem.setTotalPrice(orderPrice + orderItem.getTotalPrice());
            orderItem.setTotalWeight(orderWeight + orderItem.getTotalWeight());
            requestedItems.put(item.getName(), orderItem);
            customer.setRemainingCredits(remainingCredits - orderPrice);
            drone.setRemainingCap(updatedWeight);
            System.out.println(Utility.changeCompleteMsg);
        }
    }
}

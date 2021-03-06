package edu.gatech.cs6310;

import java.util.HashMap;
import java.util.TreeMap;

public class OrderProvider {
    private HashMap<String, TreeMap<String, Order>> storeOrdersMap;

    public OrderProvider() {
        storeOrdersMap = new HashMap<>();
    }

    public void addOrderToStore(String storeName, String orderID, String droneID, String customerAcc,
                                StoreDronesProvider storeDronesProvider, CustomerProvider customerProvider,
                                TreeMap<String, Order> currentOrders) {
        if (storeDronesProvider.getStoreDronesWithStoreNameMap().get(storeName).containsKey(droneID)) {
            Drone drone = storeDronesProvider.getStoreDronesWithStoreNameMap().get(storeName).get(droneID);
            if (!customerProvider.getAllCustomers().containsKey(customerAcc)) {
                System.out.println(Utility.nonExistingCustomerMsg);
            } else {
                Order order = new Order(orderID, customerAcc, droneID);
                currentOrders.put(orderID, order);
                storeOrdersMap.put(storeName, currentOrders);
                drone.pendingOrderNumUp();
                System.out.println(Utility.changeCompleteMsg);
            }
        } else {
            System.out.println(Utility.nonExistingDroneIdMsg);
        }
    }

    public void startOrderForStore(StoreProvider storeProvider, String storeName, String orderID, String droneID, String customerAcc,
                                   StoreDronesProvider storeDronesProvider, CustomerProvider customerProvider) {
        if (storeProvider.getAllStoresWithNameMap().containsKey(storeName)) {
            if (storeOrdersMap.containsKey(storeName)) {
                TreeMap<String, Order> currentOrders = storeOrdersMap.get(storeName);
                if (currentOrders.containsKey(orderID)) {
                    System.out.println(Utility.duplicateOrderIDMsg);
                } else {
                    addOrderToStore(storeName, orderID, droneID, customerAcc, storeDronesProvider, customerProvider, currentOrders);
                }
            } else {
                TreeMap<String, Order> defaultOrders = new TreeMap<>();
                addOrderToStore(storeName, orderID, droneID, customerAcc, storeDronesProvider, customerProvider, defaultOrders);
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
                //System.out.println("DEBUG: " + storeName + " not exist in storeOrdersMap");
            }
        } else {
            System.out.println(Utility.nonExistingStoreMsg);
        }
    }

    public boolean cancelOrderSuccess(StoreProvider storeProvider, StoreDronesProvider storeDronesProvider, String storeName, String orderID, FlyDroneProvider flyDroneProvider, CustomerProvider customerProvider) {
        if (!storeProvider.getAllStoresWithNameMap().containsKey(storeName)) {
            System.out.println(Utility.nonExistingStoreMsg);
            return false;
        }

        if (!storeOrdersMap.containsKey(storeName)) {
            //System.out.println("DEBUG: " + storeName + " not exist in storeOrdersMap");
            return false;
        }

        TreeMap<String, Order> currentOrders = storeOrdersMap.get(storeName);
        if (!currentOrders.containsKey(orderID)) {
            System.out.println(Utility.nonExistingOrderMsg);
            return false;
        }

        Order targetOrder = currentOrders.get(orderID);
        Drone targetDrone = storeDronesProvider.getStoreDronesWithStoreNameMap().get(storeName).get(targetOrder.getDroneID());
        Customer customer = customerProvider.getAllCustomers().get(targetOrder.getCustomerAcc());
        customer.setRemainingCredits(customer.getRemainingCredits() + targetOrder.getTotalPrice());
        targetDrone.pendingOrderDown();
        targetDrone.setRemainingCap(targetDrone.getRemainingCap() + targetOrder.getTotalWeight());
        currentOrders.remove(orderID);
        System.out.println(Utility.changeCompleteMsg);
        return true;
    }

    public boolean purchaseOrderSuccess(StoreProvider storeProvider, StoreDronesProvider storeDronesProvider, String storeName, String orderID, FlyDroneProvider flyDroneProvider, CustomerProvider customerProvider) {
        if (!storeProvider.getAllStoresWithNameMap().containsKey(storeName)) {
            System.out.println(Utility.nonExistingStoreMsg);
            return false;
        }

        if (!storeOrdersMap.containsKey(storeName)) {
            //System.out.println("DEBUG: " + storeName + " not exist in storeOrdersMap");
            return false;
        }

        TreeMap<String, Order> currentOrders = storeOrdersMap.get(storeName);
        if (!currentOrders.containsKey(orderID)) {
            System.out.println(Utility.nonExistingOrderMsg);
            return false;
        }

        Order targetOrder = currentOrders.get(orderID);
        Drone targetDrone = storeDronesProvider.getStoreDronesWithStoreNameMap().get(storeName).get(targetOrder.getDroneID());

        Pilot matchedPilot = flyDroneProvider.getPilotsDronesPool().getDroneToPilot().get(targetDrone.getComboID());
        if (matchedPilot == null) {
            System.out.println(Utility.noPilotMsg);
            return false;
        }

        if (targetDrone.getRemainingTrips() == 0) {
            System.out.println(Utility.notEnoughFuelMsg);
            return false;
        }

        Customer customer = customerProvider.getAllCustomers().get(targetOrder.getCustomerAcc());
        customer.setTotalCredits(customer.getTotalCredits() - targetOrder.getTotalPrice());
        Store store = storeProvider.GetByStoreName(storeName);
        store.setRevenue(store.getRevenue() + targetOrder.getTotalPrice());
        targetDrone.completeOneTrip();
        targetDrone.pendingOrderDown();
        targetDrone.setRemainingCap(targetDrone.getRemainingCap() + targetOrder.getTotalWeight());
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

    public void requestItem(StoreProvider storeProvider, String storeName, String orderID, String itemName, int quantity, int unitPrice,
                            StoreDronesProvider storeDronesProvider, StoreItemsProvider storeItemsProvider, CustomerProvider customerProvider) {
        if (storeProvider.getAllStoresWithNameMap().containsKey(storeName)) {
            if (storeOrdersMap.containsKey(storeName)) {
                TreeMap<String, Order> currentOrders = storeOrdersMap.get(storeName);
                if (currentOrders.containsKey(orderID)) {
                    Order targetOrder = currentOrders.get(orderID);
                    Customer customer = customerProvider.getAllCustomers().get(targetOrder.getCustomerAcc());
                    int orderPrice = quantity * unitPrice;
                    if (storeItemsProvider.getAllStoresWithItems().containsKey(storeName)) {
                        ItemPool currentItemPool = storeItemsProvider.getAllStoresWithItems().get(storeName);
                        if (currentItemPool.getAllItems().containsKey(itemName)) {
                            Item targetItem = currentItemPool.getAllItems().get(itemName);
                            Drone targetDrone = storeDronesProvider.getStoreDronesWithStoreNameMap().get(storeName).get(targetOrder.getDroneID());
                            int itemsWeight = targetItem.getUnitWeight() * quantity;
                            addRequestedItem(targetOrder, targetItem, customer, targetDrone, itemsWeight, orderPrice, quantity);
                        } else {
                            System.out.println(Utility.nonExistingItemMsg);
                        }
                    } else {
                       // System.out.println("DEBUG: " + storeName + " not exist in allStoresWithItems");
                    }
                } else {
                    System.out.println(Utility.nonExistingOrderMsg);
                }
            } else {
                //System.out.println("DEBUG: " + storeName + " not exist in storeOrdersMap");
            }
        } else {
            System.out.println(Utility.nonExistingStoreMsg);
        }
    }

    public void addRequestedItem(Order targetOrder, Item item, Customer customer, Drone targetDrone, int itemsWeight, int orderPrice, int quantity) {
        boolean isSuccess = true;
        TreeMap<String, OrderItem> requestedItems = targetOrder.getRequestedItems();
        if (requestedItems.containsKey(item.getName())) {
            System.out.println(Utility.duplicateItemInOrderMsg);
            isSuccess = false;
        }

        if (isSuccess && orderPrice > customer.getRemainingCredits()) {
            System.out.println(Utility.overCreditMsg);
            isSuccess = false;
        }

        if (isSuccess && targetDrone.getRemainingCap() < itemsWeight) {
            System.out.println(Utility.overWeightMsg);
            isSuccess = false;
        }

        int remainingCredits = customer.getRemainingCredits();
        int updatedCredits = remainingCredits - orderPrice;
        if (isSuccess && updatedCredits < 0) {
           // System.out.println("DEBUG: illegal credit: " + updatedCredits);
            isSuccess = false;
        }

        int updatedWeight = targetDrone.getRemainingCap() - itemsWeight;
        if (isSuccess && updatedWeight < 0) {
            //System.out.println("DEBUG: illegal weight: " + updatedWeight);
            isSuccess = false;
        }

        if (isSuccess) {
            targetOrder.setTotalPrice(targetOrder.getTotalPrice() + orderPrice);
            targetOrder.setTotalWeight(targetOrder.getTotalWeight() + itemsWeight);
            OrderItem orderItem = new OrderItem(item.getName(), 0, 0, 0);
            orderItem.setQuantity(quantity + orderItem.getQuantity());
            orderItem.setTotalPrice(orderPrice + orderItem.getTotalPrice());
            orderItem.setTotalWeight(itemsWeight + orderItem.getTotalWeight());
            requestedItems.put(item.getName(), orderItem);
            customer.setRemainingCredits(remainingCredits - orderPrice);
            targetDrone.setRemainingCap(updatedWeight);
            System.out.println(Utility.changeCompleteMsg);
        }
    }
}

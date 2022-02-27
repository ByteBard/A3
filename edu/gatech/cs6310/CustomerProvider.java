package edu.gatech.cs6310;

import java.util.TreeMap;

public class CustomerProvider {
    private TreeMap<String, Customer> allCustomers;

    public CustomerProvider() {
        this.allCustomers = new TreeMap<>();
    }

    public void addNewCustomer(String account, String firstName, String lastName, String phoneNum, int rating, int credits) {
        if (allCustomers.containsKey(account)) {
            System.out.println(Utility.duplicateCustomerAccMsg);
        } else {
            Customer customer = new Customer(account, firstName, lastName, phoneNum, rating, credits);
            allCustomers.put(customer.getAccount(), customer);
            System.out.println(Utility.changeCompleteMsg);
        }
    }

    public void displayAllCustomers() {
        for (Customer customer : allCustomers.values()) {
            customer.displayCustomer();
        }
        System.out.println(Utility.displayCompleteMsg);
    }
}

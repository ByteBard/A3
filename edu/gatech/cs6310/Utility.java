package edu.gatech.cs6310;

public class Utility {
    public static String changeCompleteMsg = "OK:change_completed";
    public static String nonExistingDroneIdMsg = "ERROR:drone_identifier_does_not_exist";
    public static String nonExistingPilotAccMsg = "ERROR:pilot_identifier_does_not_exist";
    public static String duplicateCustomerAccMsg = "ERROR:customer_identifier_already_exists";
    public static String duplicateDroneIdMsg = "ERROR:drone_identifier_already_exists";
    public static String duplicatePilotAccountMsg = "ERROR:pilot_identifier_already_exists";
    public static String duplicatePilotLicenceMsg = "ERROR:pilot_license_already_exists";
    public static String duplicateStoreMsg = "ERROR:store_identifier_already_exists";
    public static String duplicateStoreItemMsg = "ERROR:item_identifier_already_exists";
    public static String duplicateOrderIDMsg = "ERROR:order_identifier_already_exists";
    public static String displayCompleteMsg = "OK:display_completed";
    public static String nonExistingStoreMsg = "ERROR:store_identifier_does_not_exist";
    public static String nonExistingCustomerMsg = "ERROR:customer_identifier_does_not_exist";

    public static String getFullName(String firstName, String lastName){
        return firstName + "_" + lastName;
    }
}


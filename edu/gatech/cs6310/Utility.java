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
    public static String nonExistingOrderMsg = "ERROR:order_identifier_does_not_exist";
    public static String nonExistingItemMsg = "ERROR:item_identifier_does_not_exist";
    public static String duplicateItemInOrderMsg = "ERROR:item_already_ordered";
    public static String overCreditMsg = "ERROR:customer_cant_afford_new_item";
    public static String overWeightMsg = "ERROR:drone_cant_carry_new_item";
    public static String notEnoughFuelMsg = "ERROR:drone_needs_fuel";
    public static String noPilotMsg = "ERROR:drone_needs_pilot";

    public static String getFullName(String firstName, String lastName) {
        return firstName + "_" + lastName;
    }

    public static boolean isEmpty(String val) {
        return val == null || val.isEmpty() || val.trim().isEmpty();
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //function validates mobile numbers
    //ref link: https://www.javatpoint.com/mobile-number-validation-in-java
    public static boolean isValidateNumber(String mobNumber) {
//validates phone numbers having 10 digits (9998887776)
        if (mobNumber.matches("\\d{10}"))
            return true;
//validates phone numbers having digits, -, . or spaces
        else if (mobNumber.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
            return true;
        else if (mobNumber.matches("\\d{4}[-\\.\\s]\\d{3}[-\\.\\s]\\d{3}"))
            return true;
//validates phone numbers having digits and extension (length 3 to 5)
        else if (mobNumber.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
            return true;
//validates phone numbers having digits and area code in braces
        else if (mobNumber.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
            return true;
        else if (mobNumber.matches("\\(\\d{5}\\)-\\d{3}-\\d{3}"))
            return true;
        else if (mobNumber.matches("\\(\\d{4}\\)-\\d{3}-\\d{3}"))
            return true;
//return false if any of the input matches is not found
        else
            return false;
    }
}


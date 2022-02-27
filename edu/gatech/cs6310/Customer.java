package edu.gatech.cs6310;

public class Customer {
    private String account;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private int rating;
    private int credits;

    public Customer(String account, String firstName, String lastName, String phoneNum, int rating, int credits) {
        this.account = account;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.rating = rating;
        this.credits = credits;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void displayCustomer(){
        //name:Alana_Apple,phone:222-222-2222,rating:4,credit:100
        System.out.println(
                "name:" + firstName + "_" + lastName + "," +
                "phone:" + phoneNum + "," +
                "rating:" + rating + "," +
                "credit:" + credits
                );
    }
}

package edu.gatech.cs6310;

public class Customer {
    private String account;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private int rating;
    private int totalCredits;
    private int remainingCredits;

    public Customer(String account, String firstName, String lastName, String phoneNum, int rating, int credits) {
        this.account = account;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.rating = rating;
        this.totalCredits = credits;
        this.remainingCredits = credits;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public int getRemainingCredits() {
        return remainingCredits;
    }

    public void setRemainingCredits(int remainingCredits) {
        this.remainingCredits = remainingCredits;
    }

    public String getAccount() {
        return account;
    }

    public void displayCustomer(){
        //name:Alana_Apple,phone:222-222-2222,rating:4,credit:100
        System.out.println(
                "name:" + firstName + "_" + lastName + "," +
                "phone:" + phoneNum + "," +
                "rating:" + rating + "," +
                "credit:" + totalCredits
                );
    }
}

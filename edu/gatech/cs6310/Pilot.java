package edu.gatech.cs6310;

public class Pilot {
    //ffig8,Finneas,Fig,888-888-8888,890-12-3456,panam_10,33
    private String account;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String taxID;
    private String licenceID;
    private int experienceCount;

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

    public String getTaxID() {
        return taxID;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public String getLicenceID() {
        return licenceID;
    }

    public void setLicenceID(String licenceID) {
        this.licenceID = licenceID;
    }

    public int getExperienceCount() {
        return experienceCount;
    }

    public void setExperienceCount(int experienceCount) {
        this.experienceCount = experienceCount;
    }

    public Pilot(String account, String firstName, String lastName, String phoneNum, String taxID, String licenceID, int experienceCount) {
        this.account = account;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.taxID = taxID;
        this.licenceID = licenceID;
        this.experienceCount = experienceCount;
    }


}

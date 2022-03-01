package edu.gatech.cs6310;

import java.util.HashSet;
import java.util.TreeMap;

public class PilotProvider {
    private TreeMap<String, Pilot> allPilotsWithAccountMap;
    private HashSet<String> allPilotsLicences;

    public PilotProvider() {
        this.allPilotsWithAccountMap = new TreeMap<>();
        this.allPilotsLicences = new HashSet<>();
    }

    public TreeMap<String, Pilot> getAllPilotsWithAccountMap() {
        return allPilotsWithAccountMap;
    }

    public HashSet<String> getAllPilotsLicences() {
        return allPilotsLicences;
    }

    public void addNewPilot(String account, String firstName, String lastName, String phoneNum, String taxId, String licenceNum, int experienceCount) {
        Pilot pilot = new Pilot(account, firstName, lastName, phoneNum, taxId, licenceNum, experienceCount);
        if (allPilotsWithAccountMap.containsKey(account)) {
            System.out.println(Utility.duplicatePilotAccountMsg);
        } else if (allPilotsLicences.contains(licenceNum)) {
            System.out.println(Utility.duplicatePilotLicenceMsg);
        } else {
            allPilotsWithAccountMap.put(account, pilot);
            allPilotsLicences.add(pilot.getLicenceID());
            System.out.println(Utility.changeCompleteMsg);
        }
    }

    public void displayPilot(Pilot pilot){
        //name:Finneas_Fig,phone:888-888-8888,taxID:890-12-3456,licenseID:panam_10,experience:33
        System.out.println(
                "name:" + Utility.getFullName(pilot.getFirstName(), pilot.getLastName()) + "," +
                "phone:" + pilot.getPhoneNum() + "," +
                "taxID:" + pilot.getTaxID() + "," +
                "licenseID:" + pilot.getLicenceID() + "," +
                "experience:" + pilot.getExperienceCount()
                );
    }

    public void displayAllPilots() {
        for (Pilot pilot : allPilotsWithAccountMap.values()) {
            displayPilot(pilot);
        }
        System.out.println(Utility.displayCompleteMsg);
    }
}

package by.tseluiko.gallery.core;

import java.util.ArrayList;
import java.util.List;

public class Administrator {
    private final String name;
    private final String contactDetails;
    private List<Exhibition> managedExhibitions;
    private List<Auction> managedAuctions;

    public Administrator(String name, String contactDetails) {
        this.name = name;
        this.contactDetails = contactDetails;
        this.managedExhibitions = new ArrayList<>();
        this.managedAuctions = new ArrayList<>();
    }

    public boolean manageExhibition(Exhibition exhibition) {
        if (managedExhibitions.contains(exhibition)) {
            return false;
        }
        return managedExhibitions.add(exhibition);
    }

    public boolean stopManageExhibition(Exhibition exhibition) {
        if (managedExhibitions.contains(exhibition)) {
            return managedExhibitions.remove(exhibition);
        }
        return false;
    }

    public boolean manageAuction(Auction auction) {
        if (managedAuctions.contains(auction)) {
            return false;
        }
        return managedAuctions.add(auction);
    }

    public boolean stopManageAuction(Auction auction) {
        if (managedAuctions.contains(auction)) {
            return managedAuctions.remove(auction);
        }
        return false;
    }

    public String displayAdministratorInfo() {
        return "Name: " + name + ", Contact Details: " + contactDetails;
    }
}
package by.tseluiko.gallery.core;

import java.util.ArrayList;
import java.util.List;

public class Auction {
    private final double startingPrice;
    private final double currentPrice;
    private final List<Picture> pictures;

    public Auction(double startingPrice, double currentPrice, Administrator administrator) {
        this.startingPrice = startingPrice;
        this.currentPrice = currentPrice;

        administrator.manageAuction(this);
        this.pictures = new ArrayList<>();
    }

    public boolean setManagedBy(Administrator administrator) {
        return administrator.manageAuction(this);
    }

    public boolean addPicture(Picture picture) {
        if (pictures.contains(picture)) {
            return false;
        }
        return pictures.add(picture);
    }

    public boolean removePicture(Picture picture) {
        if (pictures.contains(picture)) {
            return pictures.remove(picture);
        }
        return false;
    }

    public boolean displayPictures() {
        if (pictures.isEmpty()) {
            return false;
        }
        for (Picture picture : pictures) {
            System.out.println(picture.getPictureDetails());
        }
        return true;
    }

    public String displayPrices() {
        return "Starting Price: " + startingPrice + ", Current Price: " + currentPrice;
    }
}
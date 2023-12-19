package by.tseluiko.gallery.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExhibitionHall {
    protected final int maxCapacity;
    protected int currentCapacity;
    protected List<Picture> pictures;

    public ExhibitionHall(int maxCapacity) {
        this.pictures = new ArrayList<>();
        this.maxCapacity = maxCapacity;
        this.currentCapacity = 0;
    }

    public boolean removePicture(Picture picture) {
        if (pictures.contains(picture)) {
            currentCapacity -= 1;
            return pictures.remove(picture);
        }
        return false;
    }

    public boolean displayPictures() {
        if (pictures.isEmpty()){
            return false;
        }
        for (Picture picture: pictures){
            System.out.println(picture.getPictureDetails());
        }
        return true;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
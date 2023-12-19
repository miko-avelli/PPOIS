package by.tseluiko.gallery.core;

import java.util.ArrayList;
import java.util.List;

public class LandscapeExhibitionHall extends ExhibitionHall {
    private final List<String> locations;

    public LandscapeExhibitionHall(int maxCapacity) {
        super(maxCapacity);
        this.locations = new ArrayList<>();
    }

    public boolean addPicture(Picture picture) {
        if (picture.getGenre() == PictureGenres.LANDSCAPE && currentCapacity < maxCapacity) {
            if (pictures.contains(picture)) {
                return false;
            }
            currentCapacity += 1;
            return pictures.add(picture);
        } else {
            return false;
        }
    }

    public boolean addLocation(String location) {
        if (locations.contains(location)) {
            return false;
        }
        return locations.add(location);
    }

    public boolean removeLocation(String location) {
        if (locations.contains(location)) {
            return locations.remove(location);
        }
        return false;
    }

    public String displayLocations() {
        return "Locations: " + locations.toString();
    }
}
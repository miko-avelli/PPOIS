package by.tseluiko.gallery.core;

import java.util.ArrayList;
import java.util.List;

public class PortraitExhibitionHall extends ExhibitionHall {
    private final List<String> portraitArtists;

    public PortraitExhibitionHall(int maxCapacity) {
        super(maxCapacity);
        this.portraitArtists = new ArrayList<>();
    }

    public boolean addPicture(Picture picture) {
        if (picture.getGenre() == PictureGenres.PORTRAIT && currentCapacity < maxCapacity) {
            if (pictures.contains(picture)) {
                return false;
            }
            currentCapacity += 1;
            return pictures.add(picture);
        } else {
            return false;
        }
    }

    public boolean addPortraitArtist(String portrait) {
        if (portraitArtists.contains(portrait)) {
            return false;
        }
        return portraitArtists.add(portrait);
    }

    public boolean removePortraitArtist(String artist) {
        if (portraitArtists.contains(artist)) {
            return portraitArtists.remove(artist);
        }
        return false;
    }

    public String displayPortraitArtists() {
        return "Artists: " + portraitArtists.toString();
    }
}
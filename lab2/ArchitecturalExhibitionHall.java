package by.tseluiko.gallery.core;

import java.util.ArrayList;
import java.util.List;

public class ArchitecturalExhibitionHall extends ExhibitionHall {
    private List<String> architecturalStyles;

    public ArchitecturalExhibitionHall(int maxCapacity) {
        super(maxCapacity);
        this.architecturalStyles = new ArrayList<>();
    }

    public boolean addPicture(Picture picture) {
        if (picture.getGenre() == PictureGenres.ARCHITECTURAL && currentCapacity < maxCapacity) {
            if (pictures.contains(picture)) {
                return false;
            }
            currentCapacity += 1;
            return pictures.add(picture);
        } else {
            return false;
        }
    }

    public boolean addArchitecturalStyle(String style) {
        if (architecturalStyles.contains(style)) {
            return false;
        }
        return architecturalStyles.add(style);
    }

    public boolean removeArchitecturalStyle(String style) {
        if (architecturalStyles.contains(style)) {
            return architecturalStyles.remove(style);
        }
        return false;
    }

    public String displayArchitecturalStyles() {
        return "Architectural Styles: " + architecturalStyles.toString();
    }
}
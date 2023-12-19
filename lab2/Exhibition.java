package by.tseluiko.gallery.core;

import java.util.ArrayList;
import java.util.List;

public class Exhibition {
    private List<ExhibitionHall> exhibitionHalls;

    public Exhibition() {
        this.exhibitionHalls = new ArrayList<>();
    }

    public boolean setManagedBy(Administrator administrator) {
        return administrator.manageExhibition(this);
    }

    public boolean addExhibitionHall(ExhibitionHall exhibitionHall) {
        if (exhibitionHalls.contains(exhibitionHall)) {
            return false;
        }
        return exhibitionHalls.add(exhibitionHall);
    }

    public boolean removeExhibitionHall(ExhibitionHall exhibitionHall) {
        if (exhibitionHalls.contains(exhibitionHall)) {
            return exhibitionHalls.remove(exhibitionHall);
        }
        return false;
    }
}
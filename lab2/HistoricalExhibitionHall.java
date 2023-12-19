package by.tseluiko.gallery.core;

import java.util.ArrayList;
import java.util.List;

public class HistoricalExhibitionHall extends ExhibitionHall {
    private List<String> historicalPeriod;

    public HistoricalExhibitionHall(int maxCapacity) {
        super(maxCapacity);
        this.historicalPeriod = new ArrayList<>();
    }

    public boolean addPicture(Picture picture) {
        if (picture.getGenre() == PictureGenres.HISTORICAL && currentCapacity < maxCapacity) {
            if (pictures.contains(picture)) {
                return false;
            }
            currentCapacity += 1;
            return pictures.add(picture);
        } else {
            return false;
        }
    }

    public boolean addHistoricalPeriod(String period) {
        if (historicalPeriod.contains(period)) {
            return false;
        }
        return historicalPeriod.add(period);
    }

    public boolean removeHistoricalPeriod(String period) {
        if (historicalPeriod.contains(period)) {
            return historicalPeriod.remove(period);
        }
        return false;
    }

    public String displayHistoricalPeriods() {
        return "Historical Periods: " + historicalPeriod.toString();
    }
}
package by.tseluiko.gallery.core;

import java.util.ArrayList;
import java.util.List;

public class BattleExhibitionHall extends ExhibitionHall {
    private List<String> battleThemes;

    public BattleExhibitionHall(int maxCapacity) {
        super(maxCapacity);
        this.battleThemes = new ArrayList<>();
    }

    public boolean addPicture(Picture picture) {
        if (picture.getGenre() == PictureGenres.BATTLE && currentCapacity < maxCapacity) {
            if (pictures.contains(picture)) {
                return false;
            }
            currentCapacity += 1;
            return pictures.add(picture);
        } else {
            return false;
        }
    }

    public boolean addBattleTheme(String theme) {
        if (battleThemes.contains(theme)) {
            return false;
        }
        return battleThemes.add(theme);
    }

    public boolean removeBattleTheme(String theme) {
        if (battleThemes.contains(theme)) {
            return battleThemes.remove(theme);
        }
        return false;
    }

    public String displayBattleThemes() {
        return "Battle Themes: " + battleThemes.toString();
    }
}
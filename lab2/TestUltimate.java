import by.tseluiko.gallery.core.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestUltimate {
    @Test
    void testArchitecturalHall() {
        ArchitecturalExhibitionHall aeh = new ArchitecturalExhibitionHall(5);
        Assertions.assertEquals(aeh.getMaxCapacity(), 5);

        Picture picture1 = new Picture("Construction of the Tower", "Unger Avranches", PictureGenres.ARCHITECTURAL, 1078);
        Picture picture2 = new Picture("The Cathedral of Our Lady of Paris", "Unknown", PictureGenres.ARCHITECTURAL, 1265);
        Picture pictureTest = new Picture("-", "-", PictureGenres.BATTLE, 1000);

        Assertions.assertTrue(aeh.addPicture(picture1));
        Assertions.assertFalse(aeh.addPicture(picture1));
        Assertions.assertTrue(aeh.addPicture(picture2));

        Assertions.assertTrue(aeh.displayPictures());
        Assertions.assertEquals(aeh.getCurrentCapacity(), 2);

        Assertions.assertFalse(aeh.addPicture(pictureTest));

        Assertions.assertEquals(aeh.getCurrentCapacity(), 2);

        Assertions.assertTrue(aeh.addArchitecturalStyle("Romantic"));
        Assertions.assertFalse(aeh.addArchitecturalStyle("Romantic"));
        Assertions.assertTrue(aeh.addArchitecturalStyle("Minimalistic"));

        Assertions.assertEquals(aeh.displayArchitecturalStyles(), "Architectural Styles: [Romantic, Minimalistic]");

        Assertions.assertTrue(aeh.removeArchitecturalStyle("Romantic"));
        Assertions.assertFalse(aeh.removeArchitecturalStyle("-"));
    }

    @Test
    void testBattleHall() {
        BattleExhibitionHall beh = new BattleExhibitionHall(4);

        Picture picture1 = new Picture("Battle of Poltava", "Adam François van der", PictureGenres.BATTLE, 1709);
        Picture picture2 = new Picture("Attila fighting the Romans", "Raphael", PictureGenres.BATTLE, 1844);
        Picture pictureTest = new Picture("-", "-", PictureGenres.HISTORICAL, 1000);

        Assertions.assertTrue(beh.addPicture(picture1));
        Assertions.assertFalse(beh.addPicture(picture1));
        Assertions.assertTrue(beh.addPicture(picture2));
        Assertions.assertFalse(beh.addPicture(pictureTest));

        Assertions.assertTrue(beh.addBattleTheme("Sea"));
        Assertions.assertFalse(beh.addBattleTheme("Sea"));
        Assertions.assertTrue(beh.addBattleTheme("Mountain"));

        Assertions.assertEquals(beh.displayBattleThemes(), "Battle Themes: [Sea, Mountain]");

        Assertions.assertTrue(beh.removeBattleTheme("Sea"));
        Assertions.assertFalse(beh.removeBattleTheme("-"));
    }

    @Test
    void testHistoricalHall() {
        HistoricalExhibitionHall heh = new HistoricalExhibitionHall(3);

        Picture picture1 = new Picture("Napoleon's First Campaign", "Jean-Louis David", PictureGenres.HISTORICAL, 1804);
        Picture picture2 = new Picture("Signing of the Act of Independence of the United States", "John Trumble", PictureGenres.HISTORICAL, 1776);
        Picture pictureTest = new Picture("-", "-", PictureGenres.LANDSCAPE, 1000);

        Assertions.assertTrue(heh.addPicture(picture1));
        Assertions.assertFalse(heh.addPicture(picture1));
        Assertions.assertTrue(heh.addPicture(picture2));
        Assertions.assertFalse(heh.addPicture(pictureTest));

        Assertions.assertTrue(heh.addHistoricalPeriod("1939-1945"));
        Assertions.assertFalse(heh.addHistoricalPeriod("1939-1945"));
        Assertions.assertTrue(heh.addHistoricalPeriod("1000-1115"));

        Assertions.assertEquals(heh.displayHistoricalPeriods(), "Historical Periods: [1939-1945, 1000-1115]");

        Assertions.assertTrue(heh.removeHistoricalPeriod("1939-1945"));
        Assertions.assertFalse(heh.removeHistoricalPeriod("-"));
    }

    @Test
    void testLandscapeHall() {
        LandscapeExhibitionHall leh = new LandscapeExhibitionHall(2);

        Picture picture1 = new Picture("Cranes over the Palace", "Emperor Huong", PictureGenres.LANDSCAPE, 1112);
        Picture picture2 = new Picture("Starry Night", "Vincent van Gogh", PictureGenres.LANDSCAPE, 1889);
        Picture pictureTest = new Picture("-", "-", PictureGenres.PORTRAIT, 1000);


        Assertions.assertTrue(leh.addPicture(picture1));
        Assertions.assertFalse(leh.addPicture(picture1));
        Assertions.assertTrue(leh.addPicture(picture2));
        Assertions.assertFalse(leh.addPicture(pictureTest));

        Assertions.assertTrue(leh.addLocation("China"));
        Assertions.assertFalse(leh.addLocation("China"));
        Assertions.assertTrue(leh.addLocation("Belarus"));

        Assertions.assertEquals(leh.displayLocations(), "Locations: [China, Belarus]");

        Assertions.assertTrue(leh.removeLocation("China"));
        Assertions.assertFalse(leh.removeLocation("-"));
    }

    @Test
    void testPortraitHall() {
        PortraitExhibitionHall peh = new PortraitExhibitionHall(2);
        Assertions.assertFalse(peh.displayPictures());

        Picture picture1 = new Picture("Napoleon", "Felix-Henri", PictureGenres.PORTRAIT, 1792);
        Picture pictureTest = new Picture("-", "-", PictureGenres.ARCHITECTURAL, 1000);

        Assertions.assertTrue(peh.addPicture(picture1));
        Assertions.assertFalse(peh.addPicture(picture1));

        Assertions.assertTrue(peh.removePicture(picture1));
        Assertions.assertFalse(peh.removePicture(picture1));
        Assertions.assertFalse(peh.addPicture(pictureTest));

        Assertions.assertTrue(peh.addPortraitArtist("Napoleon"));
        Assertions.assertFalse(peh.addPortraitArtist("Napoleon"));
        Assertions.assertTrue(peh.addPortraitArtist("Mona Lisa"));

        Assertions.assertEquals(peh.displayPortraitArtists(), "Artists: [Napoleon, Mona Lisa]");

        Assertions.assertTrue(peh.removePortraitArtist("Napoleon"));
        Assertions.assertFalse(peh.removePortraitArtist("-"));
    }

    @Test
    void testPicture() {
        Picture picture1 = new Picture("Construction of the Tower", "Unger Avranches", PictureGenres.ARCHITECTURAL, 1078);
        Picture picture2 = new Picture("The Cathedral of Our Lady of Paris", "Unknown", PictureGenres.BATTLE, 1265);

        Assertions.assertEquals(picture1.getTitle(), "Construction of the Tower");
        Assertions.assertEquals(picture2.getAuthor(), "Unknown");
        Assertions.assertEquals(picture1.getGenre(), PictureGenres.ARCHITECTURAL);
        Assertions.assertEquals(picture2.getCreationDate(), 1265);
        Assertions.assertEquals(picture1.getPictureDetails(), "Title: Construction of the Tower, Author: Unger Avranches, Genre: ARCHITECTURAL, Creation Date: 1078");
    }

    @Test
    void testAdministrator() {
        Administrator administrator = new Administrator("Zlatan", "+55555");
        Assertions.assertEquals(administrator.displayAdministratorInfo(), "Name: Zlatan, Contact Details: +55555");

        Exhibition exhibition = new Exhibition();
        HistoricalExhibitionHall heh = new HistoricalExhibitionHall(3);
        LandscapeExhibitionHall leh = new LandscapeExhibitionHall(2);

        Assertions.assertTrue(exhibition.addExhibitionHall(heh));
        Assertions.assertFalse(exhibition.addExhibitionHall(heh));
        Assertions.assertTrue(exhibition.addExhibitionHall(leh));

        exhibition.setManagedBy(administrator);


        Auction auction = new Auction(15000, 30000, administrator);
        Picture picture1 = new Picture("Cranes over the Palace", "Emperor Huong", PictureGenres.LANDSCAPE, 1112);
        Picture picture2 = new Picture("Starry Night", "Vincent van Gogh", PictureGenres.LANDSCAPE, 1889);
        Assertions.assertTrue(auction.addPicture(picture1));
        Assertions.assertFalse(auction.addPicture(picture1));
        Assertions.assertTrue(auction.addPicture(picture2));

        Assertions.assertEquals(auction.displayPrices(), "Starting Price: 15000.0, Current Price: 30000.0");
        Assertions.assertTrue(auction.displayPictures());


        Assertions.assertTrue(auction.removePicture(picture1));
        Assertions.assertFalse(auction.removePicture(picture1));
        Assertions.assertTrue(administrator.stopManageAuction(auction));
        Assertions.assertFalse(administrator.stopManageAuction(auction));

        Assertions.assertTrue(exhibition.removeExhibitionHall(leh));
        Assertions.assertFalse(exhibition.removeExhibitionHall(leh));
        Assertions.assertTrue(administrator.stopManageExhibition(exhibition));
        Assertions.assertFalse(administrator.stopManageExhibition(exhibition));
    }
}
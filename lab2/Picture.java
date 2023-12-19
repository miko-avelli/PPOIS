package by.tseluiko.gallery.core;

public class Picture {
    private final String title;
    private final String author;
    private final int creationDate;
    private final PictureGenres genre;

    public Picture(String title, String author, PictureGenres genre, int creationDate) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.creationDate = creationDate;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public PictureGenres getGenre() {
        return genre;
    }

    public int getCreationDate() {
        return creationDate;
    }

    public String getPictureDetails() {
        return "Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Creation Date: " + creationDate;
    }
}
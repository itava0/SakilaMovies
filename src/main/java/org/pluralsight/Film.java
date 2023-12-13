package org.pluralsight;

public class Film {
    private int filmID, releaseYear, length;
    private String title, description;

    public Film(int filmID, int releaseYear, int length, String title, String description) {
        this.filmID = filmID;
        this.releaseYear = releaseYear;
        this.length = length;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Film ID: " + filmID);
        str.append("\nTitle: " + title);
        str.append("\nReleased: " + releaseYear);
        str.append("\nRun Time: " + length);
        str.append("\nDescription: \n" + description);
        str.append("\n_________________________________________________________________________________");
        return str.toString();
    }
}

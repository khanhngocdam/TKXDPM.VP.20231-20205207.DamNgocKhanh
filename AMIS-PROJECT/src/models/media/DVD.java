package models.media;

import java.util.Date;

public class DVD extends Media{
    String discType;
    String director;
    int runtime;
    String studio;
    String subtitles;
    String language;
    Date releasedDate; //Null
    String DVDCategory; // NULL

    // Constructor

    public DVD(int id, String title, String category, int value, int price, int quantity, String imageURL, Boolean rushDelivery, String discType, String director, int runtime, String studio, String subtitles, String language) {
        super(id, title, category, value, price, quantity, imageURL, rushDelivery);
        this.discType = discType;
        this.director = director;
        this.runtime = runtime;
        this.studio = studio;
        this.subtitles = subtitles;
        this.language = language;
    }

    //Getter - Setter

    public String getDiscType() {
        return discType;
    }

    public void setDiscType(String discType) {
        this.discType = discType;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(String subtitles) {
        this.subtitles = subtitles;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String getDVDCategory() {
        return DVDCategory;
    }

    public void setDVDCategory(String DVDCategory) {
        this.DVDCategory = DVDCategory;
    }
}

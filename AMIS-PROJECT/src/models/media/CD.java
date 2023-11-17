package models.media;

import java.util.Date;
import java.util.List;

public class CD extends Media {
    String artist;
    String recordLabel;
    String musicType;
    Date releasedDate;
    String CDCategory;
    List<Track> listTrack;
    //Constructor

    public CD(int id, String title, String category, int value, int price, int quantity, String imageURL, Boolean rushDelivery, String artist, String recordLabel, String musicType, Date releasedDate, String CDCategory, List<Track> listTrack) {
        super(id, title, category, value, price, quantity, imageURL, rushDelivery);
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.musicType = musicType;
        this.releasedDate = releasedDate;
        this.CDCategory = CDCategory;
        this.listTrack = listTrack;
    }


    //Getter - Setter

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getRecordLabel() {
        return recordLabel;
    }

    public void setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
    }

    public String getMusicType() {
        return musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

    public Date getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String getCDCategory() {
        return CDCategory;
    }

    public void setCDCategory(String CDCategory) {
        this.CDCategory = CDCategory;
    }

    public List<Track> getListTrack() {
        return listTrack;
    }

    public void setListTrack(List<Track> listTrack) {
        this.listTrack = listTrack;
    }
}

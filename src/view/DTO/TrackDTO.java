package view.DTO;

import data.Genre;
import data.TrackDataObject;

public class TrackDTO {
    private String id;
    private String title;
    private String performer;
    private String album;
    private Genre genre;
    private Integer duration;

    public TrackDTO(String id, String title, String performer, String album, Genre genre, Integer duration) {
        this.id = id;
        this.title = title;
        this.performer = performer;
        this.album = album;
        this.genre = genre;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}

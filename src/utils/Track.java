package utils;

import java.io.Serializable;

public class Track implements Serializable {
    private String title;
    private String performer;
    private String album;
    private Genre genre;
    private Integer duration;

    public Track(String title, String performer, String album, Genre genre, Integer duration) {
        this.title = title;
        this.performer = performer;
        this.album = album;
        this.genre = genre;
        this.duration = duration;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getAlbum() {
        return album;
    }

    public String getPerformer() {
        return performer;
    }

    public String getTitle() {
        return title;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean equals(Object object) {
        if (object == null) return false;
        if (!(object instanceof Track)) return false;
        return getTitle().equals(((Track) object).getTitle())
                && getPerformer().equals(((Track) object).getPerformer())
                && getAlbum().equals(((Track) object).getAlbum())
                && getGenre().equals(((Track) object).getGenre());
    }

    public Integer getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Track{" +
                "title='" + title + '\'' +
                ", performer='" + performer + '\'' +
                ", album='" + album + '\'' +
                ", genre=" + genre +
                ", duration=" + duration +
                '}';
    }
}

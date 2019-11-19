package view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utils.Genre;
import utils.Track;

public class TrackView {
    //private final String id;
    private final StringProperty trackTitle;
    private final StringProperty performer;
    private final StringProperty album;
    private final SimpleIntegerProperty duration;
    private final StringProperty genre;

    public TrackView(String trackLabel, String performer, String genre, String album, Integer duration) {
        this.trackTitle = new SimpleStringProperty(trackLabel);
        this.duration = new SimpleIntegerProperty(duration);
        this.performer = new SimpleStringProperty(performer);
        this.album = new SimpleStringProperty(album);
        this.genre = new SimpleStringProperty(genre);
    }

    public TrackView(Track track){
        this(track.getTitle(), track.getPerformer(), track.getGenre().toString(), track.getAlbum(), track.getDuration());
    }

    public String getTrackTitle() {
        return trackTitle.get();
    }

    public StringProperty trackTitleProperty() {
        return trackTitle;
    }

    public String getPerformer() {
        return performer.get();
    }

    public StringProperty performerProperty() {
        return performer;
    }

    public String getAlbum() {
        return album.get();
    }

    public StringProperty albumProperty() {
        return album;
    }

    public int getDuration() {
        return duration.get();
    }

    public SimpleIntegerProperty durationProperty() {
        return duration;
    }

    public String getGenre() {
        return genre.get();
    }

    public StringProperty genreProperty() {
        return genre;
    }

    @Override
    public String toString() {
        return "TrackView{" +
                "trackTitle=" + trackTitle +
                ", performer=" + performer +
                ", album=" + album +
                ", duration=" + duration +
                ", genre=" + genre +
                '}';
    }
}

package view;

import data.TrackDataObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import view.DTO.TrackDTO;

public class TrackView {
    private final String id;
    private final StringProperty title;
    private final StringProperty performer;
    private final StringProperty album;
    private final StringProperty genre;
    private final SimpleIntegerProperty duration;

    public TrackView(String id, String trackLabel, String performer, String album, String genre, Integer duration) {
        this.id = id;
        this.title = new SimpleStringProperty(trackLabel);
        this.duration = new SimpleIntegerProperty(duration);
        this.performer = new SimpleStringProperty(performer);
        this.album = new SimpleStringProperty(album);
        this.genre = new SimpleStringProperty(genre);
    }

    public TrackView(TrackDTO trackDTO) {
        this(trackDTO.getId(), trackDTO.getTitle(), trackDTO.getPerformer(), trackDTO.getGenre().toString(), trackDTO.getAlbum(), trackDTO.getDuration());
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
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
                "title=" + title +
                ", performer=" + performer +
                ", album=" + album +
                ", duration=" + duration +
                ", genre=" + genre +
                '}';
    }
}

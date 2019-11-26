package model;

import utils.Genre;
import utils.Track;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TrackModel implements Serializable {
    private List<Track> arrTrack;

    public TrackModel() {
        arrTrack = new LinkedList<>();
        arrTrack.add(new Track("A", "A", "A", new Genre("A"), 3));
        arrTrack.add(new Track("Б", "Б", "3 псни", new Genre("P"), 3));
    }

    public List<Track> getAllTracks() {
        return arrTrack;
    }


    public Track addTrack(String title, String performer, String album, String genreTitle, Integer duration) {
        Track newTrack = new Track(title, performer, album, new Genre(genreTitle), duration);
        for (Track track : arrTrack) {
            if (track.equals(newTrack))
                throw new IllegalArgumentException("This track already exists");
        }
        arrTrack.add(newTrack);
        return newTrack;
    }
    public Track getTrack(String title)
    {
        for (Track track : arrTrack) {
            if (track.getTitle().equals(title)) {
                return track;
            }
        }
        return null;
    }


    public void removeTrack(String title) {
        for (Track track : arrTrack) {
            if (track.getTitle().equals(title)) {
                this.arrTrack.remove(track);
                break;
            }
        }
    }

    public void setTitleTrack(String oldTitleTrack, String newTitleTrack) {
        for (Track track : arrTrack) {
            if (track.getTitle().equals(oldTitleTrack)) {
                track.setTitle(newTitleTrack);
                break;
            }
        }
    }
    public void setPerformerTrack(String titleTrack, String newPerformerTrack) {
        for (Track track : arrTrack) {
            if (track.getTitle().equals(titleTrack)) {
                track.setPerformer(newPerformerTrack);
                break;
            }
        }
    }
    public void setAlbumTrack(String titleTrack, String newAlbumTrack) {
        for (Track track : arrTrack) {
            if (track.getTitle().equals(titleTrack)) {
                track.setAlbum(newAlbumTrack);
                break;
            }
        }
    }
    public void setDurationTrack(String titleTrack, Integer newDurationTrack) {
        for (Track track : arrTrack) {
            if (track.getTitle().equals(titleTrack)) {
                track.setDuration(newDurationTrack);
                break;
            }
        }
    }


}

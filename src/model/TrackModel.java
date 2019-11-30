package model;

import data.Genre;
import data.TrackDataObject;
import javafx.beans.InvalidationListener;
import view.EventListener;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

public class TrackModel implements Serializable, Observable {
    private List<TrackDataObject> arrTrackDataObject;
    private List<EventListener> listeners = new LinkedList<>();


    public TrackModel() {
        arrTrackDataObject = new LinkedList<>();
        arrTrackDataObject.add(new TrackDataObject("A", "A", "A", new Genre("A"), 3));
        arrTrackDataObject.add(new TrackDataObject("Б", "Б", "3 псни", new Genre("P"), 3));
    }

    public List<TrackDataObject> getAllTracks() {
        return arrTrackDataObject;
    }


    public TrackDataObject addTrack(String title, String performer, String album, String genreTitle, Integer duration) {
        TrackDataObject newTrackDataObject = new TrackDataObject(title, performer, album, new Genre(genreTitle), duration);
        for (TrackDataObject trackDataObject : arrTrackDataObject) {
            if (trackDataObject.equals(newTrackDataObject))
                throw new IllegalArgumentException("This trackDataObject already exists");
        }
        arrTrackDataObject.add(newTrackDataObject);
        for(EventListener listener: listeners){
            listener.update(Event.ADDTRACK, arrTrackDataObject.indexOf(newTrackDataObject));
        }
        return newTrackDataObject;
    }
    public TrackDataObject getTrack(String title)
    {
        for (TrackDataObject trackDataObject : arrTrackDataObject) {
            if (trackDataObject.getTitle().equals(title)) {
                return trackDataObject;
            }
        }
        return null;
    }

    public TrackDataObject getTrack(int index)
    {
        return arrTrackDataObject.get(index);
    }


    public void removeTrack(String title) {
        for (TrackDataObject trackDataObject : arrTrackDataObject) {
            if (trackDataObject.getTitle().equals(title)) {
                this.arrTrackDataObject.remove(trackDataObject);
                break;
            }
        }
    }

    public void setTitleTrack(String oldTitleTrack, String newTitleTrack) {
        for (TrackDataObject trackDataObject : arrTrackDataObject) {
            if (trackDataObject.getTitle().equals(oldTitleTrack)) {
                trackDataObject.setTitle(newTitleTrack);
                break;
            }
        }
    }
    public void setPerformerTrack(String titleTrack, String newPerformerTrack) {
        for (TrackDataObject trackDataObject : arrTrackDataObject) {
            if (trackDataObject.getTitle().equals(titleTrack)) {
                trackDataObject.setPerformer(newPerformerTrack);
                break;
            }
        }
    }
    public void setAlbumTrack(String titleTrack, String newAlbumTrack) {
        for (TrackDataObject trackDataObject : arrTrackDataObject) {
            if (trackDataObject.getTitle().equals(titleTrack)) {
                trackDataObject.setAlbum(newAlbumTrack);
                break;
            }
        }
    }
    public void setDurationTrack(String titleTrack, Integer newDurationTrack) {
        for (TrackDataObject trackDataObject : arrTrackDataObject) {
            if (trackDataObject.getTitle().equals(titleTrack)) {
                trackDataObject.setDuration(newDurationTrack);
                break;
            }
        }
    }

    @Override
    public void subscribe(EventListener eventListener) {
        listeners.add(eventListener);
    }

    @Override
    public void unsubscribe(EventListener eventListener) {
        //listeners.
    }
}

package model;

import data.Genre;
import data.TrackDataObject;
import view.EventListener;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class TrackModel implements Observable {
    private List<TrackDataObject> arrTrack;
    private List<EventListener> listeners = new LinkedList<>();


    public TrackModel() {
        arrTrack = new LinkedList<>();
        arrTrack.add(new TrackDataObject(UUID.randomUUID().toString(), "A", "A", "A", new Genre("A"), 3));
        arrTrack.add(new TrackDataObject(UUID.randomUUID().toString(), "Б", "Б", "3 псни", new Genre("P"), 3));
    }

    public List<TrackDataObject> getAllTracks() {
        return arrTrack;
    }


    public void addTrack(String id, String title, String performer, String album, String genreTitle, Integer duration) {
        id = UUID.randomUUID().toString();
        TrackDataObject newTrack = new TrackDataObject(id, title, performer, album, new Genre(genreTitle), duration);
        for (TrackDataObject track : arrTrack) {
            if (track.equals(newTrack))
                throw new IllegalArgumentException("This track already exists");
        }
        arrTrack.add(newTrack);
        for (EventListener listener : listeners) {
            listener.update(Event.ADDTRACK, newTrack.getId());
        }
    }

    /*public TrackDataObject getTrack(String title)
    {
        for (TrackDataObject trackDataObject : arrTrack) {
            if (trackDataObject.getTitle().equals(title)) {
                return trackDataObject;
            }
        }
        return null;
    }

     */

    public TrackDataObject getTrack(String id) {
        for (TrackDataObject track : arrTrack) {
            if (track.getId().equals(id)) {
                return track;
            }
        }
        return null;
    }


    public void removeTrack(String id) {
        for (TrackDataObject track : arrTrack) {
            if (track.getId().equals(id)) {
                this.arrTrack.remove(track);
                for (EventListener listener : listeners) {
                    listener.update(Event.DELETETRACK, id);
                }
                break;
            }
        }
    }

    public void setTitleTrack(String id, String newTitleTrack) {
        getTrack(id).setTitle(newTitleTrack);
        System.out.println(getTrack(id).getTitle());
        for (EventListener listener : listeners) {
            listener.update(Event.UPDATETRACKTITLE, id);
        }
    }

    public void setPerformerTrack(String titleTrack, String newPerformerTrack) {
        for (TrackDataObject track : arrTrack) {
            if (track.getTitle().equals(titleTrack)) {
                track.setPerformer(newPerformerTrack);
                break;
            }
        }
    }

    public void setAlbumTrack(String titleTrack, String newAlbumTrack) {
        for (TrackDataObject track : arrTrack) {
            if (track.getTitle().equals(titleTrack)) {
                track.setAlbum(newAlbumTrack);
                break;
            }
        }
    }

    public void setDurationTrack(String titleTrack, Integer newDurationTrack) {
        for (TrackDataObject track : arrTrack) {
            if (track.getTitle().equals(titleTrack)) {
                track.setDuration(newDurationTrack);
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

package model;

import data.GenreDataObject;
import data.TrackDataObject;
import view.EventListener;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class TrackModel implements Observable {
    private List<TrackDataObject> arrTrack;
    private List<EventListener> listeners = new LinkedList<>();

    public TrackModel() {
        arrTrack = new LinkedList<>();
    }

    public List<TrackDataObject> getAllTracks() {
        return arrTrack;
    }

    public void addTrack(String id, String title, String performer, String album, GenreDataObject genre, Integer duration) {
        id = UUID.randomUUID().toString();
        TrackDataObject newTrack = new TrackDataObject(id, title, performer, album, genre, duration);
        for (TrackDataObject track : arrTrack) {
            if (track.equals(newTrack))
                throw new IllegalArgumentException("This track already exists");
        }
        arrTrack.add(newTrack);
        for (EventListener listener : listeners) {
            listener.update();
        }
    }

    public void updateTrackArr(List<TrackDataObject> addedArrTrack) {
        //if(!addedArrTrack.isEmpty()) {
            if (!arrTrack.isEmpty()) {
                arrTrack.removeAll(arrTrack);
            }
            arrTrack.addAll(addedArrTrack);
            for (EventListener listener : listeners) {
                listener.update();
            }
        //}
    }

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
                    listener.update();
                }
                break;
            }
        }
    }

    public void changeTrack(String id, String title, String performer, String album, GenreDataObject genre, Integer duration) {
        for (TrackDataObject trackDataObject : arrTrack) {
            if(trackDataObject.getId().equals(id)){
                trackDataObject.setTitle(title);
                trackDataObject.setPerformer(performer);
                trackDataObject.setAlbum(album);
                trackDataObject.setGenre(genre);
                trackDataObject.setDuration(duration);
            }
        }
        for (EventListener listener : listeners) {
            listener.update();
        }
    }

    public void setTitleTrack(String id, String newTitleTrack) {
        getTrack(id).setTitle(newTitleTrack);
        System.out.println(getTrack(id).getTitle());
        for (EventListener listener : listeners) {
            listener.update();
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

    public void removeTrackByGenreId(String id) {
        for (int i = 0; i < arrTrack.size(); i++) {
            if (arrTrack.get(i).getGenre().getId().equals(id)) {
                arrTrack.remove(i);
            }
        }
        /*
        for (TrackDataObject trackDataObject : arrTrack) {
            if(trackDataObject.getGenre().getId().equals(id)){

            }
        }

         */
        for (EventListener listener : listeners) {
            listener.update();
        }
    }
}

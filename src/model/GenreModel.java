package model;

import data.GenreDataObject;
import view.EventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class GenreModel implements Observable {
    private List<GenreDataObject> arrGenre;
    private List<EventListener> listeners = new LinkedList<>();

    public GenreModel() {
        arrGenre = new ArrayList<>();
    }

    public List<GenreDataObject> getAllGenres() {
        return arrGenre;
    }

    public GenreDataObject addGenre(String genreTitle) {
        for (GenreDataObject storageGenre : arrGenre) {
            if (storageGenre.getTitle().equals(genreTitle)) {
                return storageGenre;
            }
        }
        GenreDataObject newGenre = new GenreDataObject(UUID.randomUUID().toString(), genreTitle);
        arrGenre.add(newGenre);
        for (EventListener listener : listeners) {
            listener.update();
        }
        return newGenre;
    }

    public GenreDataObject getGenre(String id) {
        for (GenreDataObject genre : arrGenre) {
            if (genre.getId().equals(id)) {
                return genre;
            }
        }
        return null;
    }

    public void removeGenre(String id) {
        for (GenreDataObject genre : arrGenre) {
            if (genre.getId().equals(id)) {
                arrGenre.remove(genre);
                for (EventListener listener : listeners) {
                    listener.update();
                }
                break;
            }
        }
    }

    public void changeGenre(String id, String newGenreTitle) {
        //newGenreTitle.setId(UUID.randomUUID().toString());
        for (GenreDataObject genreDataObject : arrGenre) {
            if (genreDataObject.getId().equals(id)) {
                genreDataObject.setTitle(newGenreTitle);
            }
        }
        for (int i = 0; i < arrGenre.size(); i++) {
            if (arrGenre.get(i).getId().equals(id)) {

                for (EventListener listener : listeners) {
                    listener.update();
                }
                break;
            }
        }
    }

    public void setGenre(String oldGenre, String newGenre) {
        for (GenreDataObject genres : arrGenre) {
            if (genres.getTitle().equals(oldGenre)) {
                genres.setTitle(newGenre);
                break;
            }
        }
    }

    public void addToArrGenre(List<GenreDataObject> addedArrGenres) {

        if (!arrGenre.isEmpty()) {
            addedArrGenres.removeAll(arrGenre);
        }
        arrGenre.addAll(addedArrGenres);
        /*for (TrackDataObject addedTrack : addedArrTrack) {
            boolean isDuplicate = false;
            for (TrackDataObject trackInStorage : storageTracks) {
                if (addedTrack.getId().equals(trackInStorage.getId())) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                arrTrack.add(addedTrack);
            }

        }

         */
        for (EventListener listener : listeners) {
            listener.update();
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

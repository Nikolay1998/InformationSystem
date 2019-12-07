package model;

import data.GenreDataObject;
import view.EventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class GenreModel implements Observable
{
    private List<GenreDataObject> arrGenre;
    private List<EventListener> listeners = new LinkedList<>();

    public GenreModel()
    {
        arrGenre = new ArrayList<>();
    }
    public List<GenreDataObject> getAllGenres() {
        return arrGenre;
    }
    public void addGenre(String id, String genre) {
       id = UUID.randomUUID().toString();
        GenreDataObject newGenre = new GenreDataObject(id , genre);
        for (GenreDataObject genres : arrGenre) {
            if (genres.equals(newGenre))
                throw new IllegalArgumentException("This genre already exists");
        }
        arrGenre.add(newGenre);
        for (EventListener listener : listeners) {
            listener.update();
        }
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
    public void changeGenre(String id, GenreDataObject newGenre) {
        newGenre.setId(UUID.randomUUID().toString());
        for (int i = 0; i < arrGenre.size(); i++) {
            if (arrGenre.get(i).getId().equals(id)) {
                this.arrGenre.set(i, newGenre);
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

    @Override
    public void subscribe(EventListener eventListener) {
        listeners.add(eventListener);
    }

    @Override
    public void unsubscribe(EventListener eventListener) {
        //listeners.
    }

}

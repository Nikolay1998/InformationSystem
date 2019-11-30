package model;

import data.Genre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GenreModel implements Serializable
{
    private List<Genre> arrGenre;
    public GenreModel()
    {
        arrGenre = new ArrayList<>();
    }
    public List<Genre> getAllGenres() {
        return arrGenre;
    }
    public Genre addGenre(String genre) {
        Genre newGenre = new Genre(genre);
        for (Genre genres : arrGenre) {
            if (genres.equals(newGenre))
                throw new IllegalArgumentException("This track already exists");
        }
        arrGenre.add(newGenre);
        return newGenre;
    }
    public void removeTrack(String genre) {
        for (Genre genres : arrGenre) {
            if (genres.getTitle().equals(genre)) {
                this.arrGenre.remove(genres);
                break;
            }
        }
    }
    public void setGenre(String oldGenre, String newGenre) {
        for (Genre genres : arrGenre) {
            if (genres.getTitle().equals(oldGenre)) {
                genres.setTitle(newGenre);
                break;
            }
        }
    }

}

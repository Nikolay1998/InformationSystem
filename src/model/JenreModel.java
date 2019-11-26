package model;

import utils.Genre;
import utils.Track;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JenreModel implements Serializable
{
    private List<Genre> arrGenre;
    public JenreModel()
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

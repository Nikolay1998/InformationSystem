package model;

import utils.Genre;
import utils.Track;

import java.util.ArrayList;
import java.util.List;

public class RepositoryModel {
    private List<Track> arrTrack;
    private List<Genre> arrGenre;

    public RepositoryModel(){
        arrTrack = new ArrayList<>();
        arrGenre = new ArrayList<>();
    }

    public List<Track> getArrTrack() {
        return arrTrack;
    }

    public List<Genre> getArrGenre() {
        return arrGenre;
    }

    public void addTrack(Track track) {
        arrTrack.add(track);
    }

    public void addGenre(Genre genre) {
        arrGenre.add(genre);
    }

    public void removeTrack(String title) {
        for (Track track : arrTrack) {
            if (track.getTitle().equals(title)) {
                this.arrTrack.remove(track);
                break;
            }
        }
    }

    public void removeGenre(String title) {
        for (Genre genre : arrGenre) {
            if (genre.getTitle().equals(title)) {
                this.arrTrack.remove(genre);
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

    public void setTitleGenre(String oldTitleGenre, String newTitleGenre) {
        for (Genre genre : arrGenre) {
            if (genre.getTitle().equals(oldTitleGenre)) {
                genre.setTitle(newTitleGenre);
                break;
            }
        }
    }
}

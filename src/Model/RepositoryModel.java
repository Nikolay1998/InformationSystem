package Model;

import Utils.Genre;
import Utils.Track;

import java.util.ArrayList;
import java.util.List;

public class RepositoryModel {
    private List<Track> arrTrack;
    private List<Genre> arrGenre;

    public RepositoryModel(List<Track> arrTrack, List<Genre> arrGenre) {
        this.arrTrack = arrTrack;
        this.arrGenre = arrGenre;
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

    public ArrayList<Track> getArrTrack() {
        return (ArrayList<Track>) arrTrack;
    }

    public ArrayList<Genre> getArrGenre() {
        return (ArrayList<Genre>) arrGenre;
    }

    public void setArrTrack(List<Track> arrTrack) {
        this.arrTrack = arrTrack;
    }

    public void setArrGenre(List<Genre> arrGenre) {
        this.arrGenre = arrGenre;
    }
}

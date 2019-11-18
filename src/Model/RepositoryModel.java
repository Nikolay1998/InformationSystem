package Model;

import Utils.Genre;
import Utils.Track;

import java.util.ArrayList;
import java.util.List;

public class RepositoryModel {
    private List<Track> arrTrack = new ArrayList<>();
    private List<Genre> arrGenre = new ArrayList<>();

    public void addTrack(Track track) {
        arrTrack.add(track);
    }

    public void addGenre(Genre genre) {
        arrGenre.add(genre);
    }

    public boolean removeTrack(String title)
    {
        for (Track track: arrTrack)
        {
            if (track.getTitle().equals(title))
            {
                return this.arrTrack.remove(track);
            }
        }
        return false;
    }
    public boolean removeGenre(String title)
    {
        for (Genre genre: arrGenre)
        {
            if (genre.getTitle().equals(title))
            {
                return this.arrTrack.remove(genre);
            }
        }
        return false;

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
    public void setPerformer(String TitleTrack, String newPerformer)
    {
        for (Track track: arrTrack)
        {
            if (track.getTitle().equals(TitleTrack))
            {
                track.setPerformer(newPerformer);
                break;
            }
        }

    }
    public void setAlbum(String TitleTrack, String newAlbum)
    {
        for (Track track: arrTrack)
        {
            if (track.getTitle().equals(TitleTrack))
            {
                track.setAlbum(newAlbum);
                break;
            }
        }
    }


}

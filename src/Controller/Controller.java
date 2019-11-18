package Controller;

import Model.RepositoryModel;
import Utils.Genre;
import Utils.Track;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    private RepositoryModel model;

    public Controller(RepositoryModel model) {
        this.model = model;
    }

    public void loadData() {

    }

    public void saveData() {
        ArrayList<Track> tracks = model.getArrTrack();
        ArrayList<Genre> genres = model.getArrGenre();
        try {
            FileWriter fileWriter = new FileWriter("Data.txt");
            fileWriter.write(tracks.size());
            Track track;
            for (Track value : tracks) {
                track = value;
                fileWriter.write(" " + track.getTitle() + " " + track.getPerformer() + " " + track.getAlbum() + " " + track.getGenre().getTitle());
            }
            Genre genre;
            fileWriter.write(genres.size());
            for (Genre value : genres) {
                genre = value;
                fileWriter.write(" " + genre.getTitle());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTrack(Track track) {

    }

    public void deleteTrack(Track track) {

    }

    public void changeTrack(Track track) {

    }

    public void browsingTrack() {

    }

    public void updateModel() {

    }

}

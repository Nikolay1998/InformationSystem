package controller;

import model.RepositoryModel;
import utils.Genre;
import utils.Track;
import javafx.fxml.Initializable;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private RepositoryModel model;

   /* public controller(RepositoryModel model) {
        this.model = model;
    }

    */
   @Override
   public void initialize(URL location, ResourceBundle resources) {
       model = new RepositoryModel();
   }

    public void loadData() {

    }

    public void saveData() {
        ArrayList<Track> tracks = (ArrayList<Track>)model.getArrTrack();
        ArrayList<Genre> genres = (ArrayList<Genre>) model.getArrGenre();
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

package controller;

import model.RepositoryModel;
import utils.Genre;
import utils.Track;
import javafx.fxml.Initializable;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    private RepositoryModel model;

   @Override
   public void initialize(URL location, ResourceBundle resources) {
       model = new RepositoryModel();
   }

    public void loadData() {
        try {
            FileReader fileReader = new FileReader("Data.txt");
            StringBuilder answer = new StringBuilder();
            int data = fileReader.read();
            while (data != -1) {
                answer.append((char) data);
                data = fileReader.read();
            }
            fileReader.close();
            Scanner scanner = new Scanner(answer.toString());
            scanner.useDelimiter("~");
            ArrayList<Track> tracks = new ArrayList<>();
            int trackSize = Integer.parseInt(scanner.next());
            for (int i = 0; i < trackSize; i++) {
                tracks.add(new Track(scanner.next(), scanner.next(), scanner.next(), new Genre(scanner.next())));
            }
            ArrayList<Genre> genres = new ArrayList<>();
            int genreSize = Integer.parseInt(scanner.next());
            for (int i = 0; i < genreSize; i++) {
                genres.add(new Genre(scanner.next()));
            }

            setModel(new RepositoryModel(tracks,genres));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void saveData() {
        ArrayList<Track> tracks = (ArrayList<Track>) model.getArrTrack();
        ArrayList<Genre> genres = (ArrayList<Genre>) model.getArrGenre();
        try {
            FileWriter fileWriter = new FileWriter("Data.txt");
            fileWriter.write(((Integer) tracks.size()).toString());
            fileWriter.write("~");
            Track track;
            for (Track value : tracks) {
                track = value;
                fileWriter.write(track.getTitle() + "~" + track.getPerformer() + "~" + track.getAlbum() + "~" + track.getGenre().getTitle() + "~");
            }
            Genre genre;
            fileWriter.write(((Integer) genres.size()).toString());
            fileWriter.write("~");
            for (Genre value : genres) {
                genre = value;
                fileWriter.write(genre.getTitle() + "~");
            }
            fileWriter.close();
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

    public void setModel(RepositoryModel model) {
        this.model = model;
    }
}

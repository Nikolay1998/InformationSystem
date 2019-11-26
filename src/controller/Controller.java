package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.TrackModel;
import utils.Track;
import javafx.fxml.Initializable;
import view.TrackView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TableView<TrackView> trackListTable;
    @FXML
    private TableColumn<TrackView, String> trackColumn;
    @FXML
    private TableColumn<TrackView, Integer> durationColumn;
    @FXML
    private TableColumn<TrackView, String> authorColumn;
    @FXML
    private TableColumn<TrackView, String> genreColumn;
    @FXML
    private TableColumn<TrackView, String> albumColumn;
    @FXML
    private TextField trackLabelField;
    @FXML
    private TextField durationField;
    @FXML
    private TextField performerField;
    @FXML
    private TextField genreField;
    @FXML
    private TextField albumField;

    private Adapter model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new Adapter(new TrackModel());
        trackListTable.getItems().addAll(model.getAllTracks());
    }

    public void loadData() {

    }
/*
    public void saveData() {
        ArrayList<Track> tracks = (ArrayList<Track>)model.getAllTracks();
        ArrayList<Genre> genres = (ArrayList<Genre>) model.getAllGenres();
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

 */

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

    public void addNewTrackAction(ActionEvent actionEvent) {
        //достаём значения которые вбил пользователь:
        String title = trackLabelField.getText();
        String duration = durationField.getText();
        String performer = performerField.getText();
        String album = albumField.getText();
        String genre = genreField.getText();

        TrackView newTrack = model.addTrack(title, performer, album, genre, Integer.valueOf(duration)); //Вызываем у адаптера метод создания нового трека
        trackListTable.getItems().add(newTrack); //Команда для FX, чтобы добавить отображение нового трека на таблицу
    }

    public void deleteTrackAction(ActionEvent actionEvent)
    {
    }

    public void updateTrackName(TableColumn.CellEditEvent<TrackView, String> trackViewStringCellEditEvent) {
    }
}

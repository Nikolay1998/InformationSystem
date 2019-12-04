package view;

import controller.Controller;
import data.TrackDataObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.Event;
import model.TrackModel;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class TrackViewController implements Initializable, EventListener {

    @FXML
    private TableView<TrackDataObject> trackListTable;
    @FXML
    private TableColumn<TrackDataObject, String> trackColumn;
    @FXML
    private TableColumn<TrackDataObject, Integer> durationColumn;
    @FXML
    private TableColumn<TrackDataObject, String> authorColumn;
    @FXML
    private TableColumn<TrackDataObject, String> genreColumn;
    @FXML
    private TableColumn<TrackDataObject, String> albumColumn;
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

    private Controller controller;

    private TrackModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       trackColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TrackDataObject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TrackDataObject, String> param) {
                if (param.getValue() != null) {
                    return new SimpleStringProperty(param.getValue().getTitle());
                }
                else {
                    return new SimpleStringProperty("<no name>");
                }
            }
        });

       /*durationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TrackDataObject, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<TrackDataObject, Integer> param) {
                return new SimpleIntegerProperty(param.getValue().getDuration());
            }
        });

        */
        authorColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TrackDataObject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TrackDataObject, String> param) {
                if (param.getValue() != null) {
                    return new SimpleStringProperty(param.getValue().getPerformer());
                }
                else {
                    return new SimpleStringProperty("<no author>");
                }
            }
        });

        genreColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TrackDataObject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TrackDataObject, String> param) {
                if (param.getValue() != null) {
                    return new SimpleStringProperty(param.getValue().getGenre().getTitle());
                }
                else {
                    return new SimpleStringProperty("<no genre>");
                }
            }
        });

        albumColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TrackDataObject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TrackDataObject, String> param) {
                if (param.getValue() != null) {
                    return new SimpleStringProperty(param.getValue().getAlbum());
                }
                else {
                    return new SimpleStringProperty("<no album>");
                }
            }
        });




    }

    public void setModel(TrackModel model){
        this.model = model;
        model.subscribe(this);
    }

    public void setController(Controller controller){
        this.controller = controller;
        trackListTable.getItems().addAll(model.getAllTracks());
    }


    public void loadData() {

    }
/*
    public void saveData() {
        ArrayList<TrackDataObject> tracks = (ArrayList<TrackDataObject>)adapter.getAllTracks();
        ArrayList<Genre> genres = (ArrayList<Genre>) adapter.getAllGenres();
        try {
            FileWriter fileWriter = new FileWriter("Data.txt");
            fileWriter.write(tracks.size());
            TrackDataObject track;
            for (TrackDataObject value : tracks) {
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

    public void deleteTrack(TrackDataObject track) {

    }

    public void changeTrack(TrackDataObject track) {

    }

    public void browsingTrack() {

    }

    public void updateModel() {

    }

    public void addNewTrackAction(ActionEvent actionEvent) {
        //достаём значения которые вбил пользователь:
        String title = trackLabelField.getText();
        String performer = performerField.getText();
        String album = albumField.getText();
        String genre = genreField.getText();
        String duration = durationField.getText();

        controller.addTrack(null, title, performer, album, genre, Integer.valueOf(duration));

    }

    public void deleteTrackAction(ActionEvent actionEvent) {
        int selectedIndex = trackListTable.getSelectionModel().getSelectedIndex();
        TrackDataObject track = trackListTable.getItems().get(selectedIndex);
        controller.removeTrack(track.getId());
    }

    public void updateTrackTitle(TableColumn.CellEditEvent<TrackDataObject, String> trackStringCellEditEvent) {
        System.out.println("update");
        TrackDataObject track = trackStringCellEditEvent.getRowValue();
        controller.updateTrackTitle(track, trackStringCellEditEvent.getNewValue());
        System.out.println(track.getTitle());
    }



    @Override
    public void update(Event event, String id) {
        trackListTable.getItems().removeAll(trackListTable.getItems());
        trackListTable.getItems().addAll(model.getAllTracks());
    }
}

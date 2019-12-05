package view;

import controller.Controller;
import data.TrackDataObject;
import data.TrackDataObjects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Event;
import model.TrackModel;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

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
    @FXML
    private TextField searchField;

    private Controller controller;

    private TrackModel model;

    private File currentFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        trackColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TrackDataObject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TrackDataObject, String> param) {
                if (param.getValue() != null) {
                    return new SimpleStringProperty(param.getValue().getTitle());
                } else {
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
                } else {
                    return new SimpleStringProperty("<no author>");
                }
            }
        });

        genreColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TrackDataObject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TrackDataObject, String> param) {
                if (param.getValue() != null) {
                    return new SimpleStringProperty(param.getValue().getGenre().getTitle());
                } else {
                    return new SimpleStringProperty("<no genre>");
                }
            }
        });

        albumColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TrackDataObject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TrackDataObject, String> param) {
                if (param.getValue() != null) {
                    return new SimpleStringProperty(param.getValue().getAlbum());
                } else {
                    return new SimpleStringProperty("<no album>");
                }
            }
        });


    }

    public void setModel(TrackModel model) {
        this.model = model;
        model.subscribe(this);
    }

    public void setController(Controller controller) {
        this.controller = controller;
        trackListTable.getItems().addAll(model.getAllTracks());
    }


    public void loadData() {

    }

    public void saveAsAction() {
        FileChooser fileChooser = new FileChooser();
        currentFile = fileChooser.showSaveDialog(new Stage());
        try {
            controller.saveData(currentFile);
        } catch (IOException e) {
            e.printStackTrace(); //toDo: exception Window
        }
    }

    public void saveAction(ActionEvent actionEvent) {
        if(currentFile == null){
            saveAsAction();
        }
        else{
            try {
                controller.saveData(currentFile);
            } catch (IOException e) {
                e.printStackTrace(); //toDo: exception Window
            }
        }
    }

    public void LoadAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        try {
            controller.loadData(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
    public void update() {
        trackListTable.getItems().removeAll(trackListTable.getItems());
        trackListTable.getItems().addAll(model.getAllTracks());
    }


    public void searchAction(ActionEvent actionEvent) {
        List<TrackDataObject> filteredValue = new ArrayList<>();
        String searchString = searchField.getText();
        Predicate<TrackDataObject> trackDataObjectPredicate;

        if (searchString.endsWith("*") && searchString.startsWith("*")) {
            trackDataObjectPredicate = TrackDataObjects.containsPredicate(searchString.substring(1, searchString.length() - 1));
        } else if (searchString.endsWith("*")) {
            trackDataObjectPredicate = TrackDataObjects.startWithPredicate(searchString.substring(0, searchString.length() - 1));
        } else if (searchString.startsWith("*")) {
            trackDataObjectPredicate = TrackDataObjects.endsWithPredicate(searchString.substring(1, searchString.length()));
        } else {
            trackDataObjectPredicate = TrackDataObjects.fullEqual(searchString);
        }


        for (TrackDataObject track : model.getAllTracks()) {
            if (trackDataObjectPredicate.test(track)) {
                filteredValue.add(track);
            }
        }
        trackListTable.getItems().removeAll(trackListTable.getItems());
        trackListTable.getItems().addAll(filteredValue);
    }
}

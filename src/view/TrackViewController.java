package view;

import controller.Controller;
import data.TrackDataObject;
import data.TrackDataObjects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.TrackModel;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class TrackViewController implements Initializable {

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
    @FXML
    public Button changeButton;


    private Controller controller;

    private TrackModel model;

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
        //model.subscribe(this);
    }

    public void setController(Controller controller) {
        this.controller = controller;
        trackListTable.getItems().addAll(model.getAllTracks());
    }
    @FXML
    void AddDialog(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AddDialog.class.getResource("/view/AddDialogView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Track");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(null);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            AddDialog controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setController(this.controller);
            dialogStage.showAndWait();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    @FXML
    void EditDialog(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EditDialogController.class.getResource("/view/EditDialogView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Track");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(null);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            EditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setController(this.controller);
            int selectedIndex = trackListTable.getSelectionModel().getSelectedIndex();
            TrackDataObject track = trackListTable.getItems().get(selectedIndex);
            controller.setTrack(track);
            dialogStage.showAndWait();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
    public void addNewTrackAction(ActionEvent actionEvent) {
        //достаём значения которые вбил пользователь:
        String title = trackLabelField.getText();
        String performer = performerField.getText();
        String album = albumField.getText();
        String genre = genreField.getText();
        String duration = durationField.getText();

        controller.addTrack(null, title, performer, album, genre, Integer.valueOf(duration));

        changeButton.setDisable(true);

    }

    public void deleteTrackAction(ActionEvent actionEvent) {
        int selectedIndex = trackListTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {

            TrackDataObject track = trackListTable.getItems().get(selectedIndex);
            controller.removeTrack(track.getId());
            changeButton.setDisable(true);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No track Selected");
            alert.setContentText("Please select a track in the table.");
            alert.showAndWait();
        }
    }

    public void changeTrackAction(ActionEvent actionEvent) {
        int selectedIndex = trackListTable.getSelectionModel().getSelectedIndex();

            TrackDataObject track = trackListTable.getItems().get(selectedIndex);
            String title = trackLabelField.getText();
            String performer = performerField.getText();
            String album = albumField.getText();
            String gerneTitle = genreField.getText();
            //GenreDataObject genre = new GenreDataObject(genreField.getText());
            Integer duration = new Integer(durationField.getText());
            //TrackDataObject changedTrack = new TrackDataObject(null, title, performer, album, genre, duration);
            controller.changeTrack(track.getId(), title, performer, album, gerneTitle, duration);
            changeButton.setDisable(true);


    }

    public void updateTrackTitle(TableColumn.CellEditEvent<TrackDataObject, String> trackStringCellEditEvent) {
        System.out.println("update");
        TrackDataObject track = trackStringCellEditEvent.getRowValue();
        controller.updateTrackTitle(track, trackStringCellEditEvent.getNewValue());
        System.out.println(track.getTitle());
    }

    //при выделении строки в таблице
    public void onLineClicked(MouseEvent mouseEvent) {
        int selectedIndex = trackListTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            TrackDataObject track = trackListTable.getItems().get(selectedIndex);
            trackLabelField.setText(track.getTitle());
            durationField.setText(String.valueOf(track.getDuration()));
            performerField.setText(track.getPerformer());
            genreField.setText(String.valueOf(track.getGenre()));
            albumField.setText(track.getAlbum());
            changeButton.setDisable(false);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No track Selected");
            alert.setContentText("Please select a track in the table.");
            alert.showAndWait();
        }
    }


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
        if (!filteredValue.isEmpty()) {
            trackListTable.getItems().removeAll(trackListTable.getItems());
            trackListTable.getItems().addAll(filteredValue);
            changeButton.setDisable(true);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Поиска");
            alert.setHeaderText("Результат поиска:");
            alert.setContentText("Такого трека нет в справочнике");
            alert.showAndWait();
        }
    }
    /*

    public void initTab(int currentTabId) {
        System.out.println(">TabController::initTab() with currentTabId=" + currentTabId);
        this.tabId = currentTabId;
    }

     */
}

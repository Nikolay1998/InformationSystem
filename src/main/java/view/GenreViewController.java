package view;

import controller.Controller;
import data.GenreDataObject;
import data.GenreDataObjects;
import data.TrackDataObject;
import data.TrackDataObjects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.GenreModel;
import javafx.event.ActionEvent;

public class GenreViewController implements Initializable {

    @FXML
    private TextField SearchField;

    @FXML
    private Button searchButton;
    @FXML
    private TableView<GenreDataObject> GenreTable;
    @FXML
    private TableColumn<GenreDataObject, String> GenreColumn;
    @FXML
    private TextField addGenreFd;
    @FXML
    private Button changeButton;

    private Controller controller;

    private GenreModel model;

    @FXML
    public void onLineClicked(MouseEvent event) {
        int selectedIndex = GenreTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex>=0) {
            GenreDataObject genre = GenreTable.getItems().get(selectedIndex);
            this.addGenreFd.setText(genre.getTitle());
            changeButton.setDisable(false);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Genre Selected");
            alert.setContentText("Please select a genre in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        GenreColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GenreDataObject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<GenreDataObject, String> param) {
                if (param.getValue() != null) {
                    return new SimpleStringProperty(param.getValue().getTitle());
                } else {
                    return new SimpleStringProperty("<no name>");
                }
            }
        });
    }

    public void setModel(GenreModel model) {
        this.model = model;
    }

    public void setController(Controller controller) {
        this.controller = controller;
        GenreTable.getItems().addAll(model.getAllGenres());
    }

    @FXML
    void AddGenreAction(ActionEvent event) {
        String genre = this.addGenreFd.getText();
        controller.addGenre(genre);
        this.changeButton.setDisable(true);
    }

    @FXML
    void changeButton(ActionEvent event) {

    }

    @FXML
    void deleteGenre(ActionEvent event) {
        int selectedIndex = this.GenreTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            GenreDataObject genre = GenreTable.getItems().get(selectedIndex);
            controller.removeGenre(genre.getId());
            changeButton.setDisable(true);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No genre Selected");
            alert.setContentText("Please select a genre in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    void searchButton(ActionEvent event) {
        List<GenreDataObject> filteredValue = new ArrayList<>();
        String searchString = this.SearchField.getText();
        Predicate<GenreDataObject> genreDataObjectPredicate;
        if (searchString.endsWith("*") && searchString.startsWith("*")) {
            genreDataObjectPredicate = GenreDataObjects.containsPredicate(searchString.substring(1, searchString.length() - 1));
        } else if (searchString.endsWith("*")) {
            genreDataObjectPredicate = GenreDataObjects.startWithPredicate(searchString.substring(0, searchString.length() - 1));
        } else if (searchString.startsWith("*")) {
            genreDataObjectPredicate = GenreDataObjects.endsWithPredicate(searchString.substring(1, searchString.length()));
        } else {
            genreDataObjectPredicate = GenreDataObjects.fullEqual(searchString);
        }
        for (GenreDataObject genre : model.getAllGenres()) {
            if (genreDataObjectPredicate.test(genre)) {
                filteredValue.add(genre);
            }
        }
        if (!filteredValue.isEmpty()) {
            GenreTable.getItems().removeAll(GenreTable.getItems());
            GenreTable.getItems().addAll(filteredValue);
            changeButton.setDisable(true);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Search error");
            alert.setHeaderText("Search");
            alert.setContentText("Genre not find");
            alert.showAndWait();
        }
    }

    @FXML
    void changeGenreAction(ActionEvent event) {
        int selectedIndex = GenreTable.getSelectionModel().getSelectedIndex();
        GenreDataObject genre = GenreTable.getItems().get(selectedIndex);
        String genreTitle = this.addGenreFd.getText();
        //GenreDataObject changedGenre = new GenreDataObject(genreTitle);
        controller.changeGenre(genre.getId(), genreTitle);
        changeButton.setDisable(true);

    }

    public void update() {
        GenreTable.getItems().removeAll(GenreTable.getItems());
        GenreTable.getItems().addAll(model.getAllGenres());
    }
}

package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import model.TrackModel;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {
    @FXML
    private TrackViewController trackViewController;
    @FXML
    private GenreViewController genreViewController;
    @FXML
    private TabPane TabPane;

    private Controller controller;

    private TrackModel model;

    private File currentFile;

   /* public void myInit() {
        TabPane.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> observable,
                                                                        Tab oldValue, Tab newValue)->{
            preparationInitTab(newValue);
        });
    }

    */

    /*public void preparationInitTab(Tab selectedTab) {
        String currentTabId_string = selectedTab.getId();
        String[] parts = currentTabId_string.split("_");
        int currentTabId = Integer.parseInt(parts[1]);
        switch(currentTabId) {
            case 1:
                trackViewController.initTab(currentTabId);
                break;

            default:
                System.out.println("Warning: Select an unassigned tab='" + currentTabId + "'");
        }
    }

     */

    public TrackViewController getTrackViewController() {
        return trackViewController;
    }

    public GenreViewController getGenreViewController() {
        return genreViewController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void LoadAction(ActionEvent actionEvent) {
    }

    public void saveAction(ActionEvent actionEvent) {
    }

    public void saveAsAction(ActionEvent actionEvent) {
    }
}

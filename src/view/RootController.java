package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.GenreModel;
import model.TrackModel;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable, EventListener {
    @FXML
    private TrackViewController trackViewController;
    @FXML
    private GenreViewController genreViewController;
    @FXML
    private TabPane TabPane;

    private TrackModel trackModel;

    private GenreModel genreModel;

    private Controller controller;

    private File currentFile;

    public void setTrackModel(TrackModel trackModel) {
        this.trackModel = trackModel;
        trackModel.subscribe(this);
    }

    public void setGenreModel(GenreModel genreModel) {
        this.genreModel = genreModel;
        genreModel.subscribe(this);
    }
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

    public void saveAsAction() {
        FileChooser fileChooser = new FileChooser();
        currentFile = fileChooser.showSaveDialog(new Stage());
        controller.saveData(currentFile);
        /*
        try {
        } catch (IOException e) {
            e.printStackTrace(); //toDo: exception Window
        }

         */
    }

    public void saveAction(ActionEvent actionEvent) {
        if (currentFile == null) {
            saveAsAction();
        } else {
            controller.saveData(currentFile);
            /*
            try {
            } catch (IOException e) {
                e.printStackTrace(); //toDo: exception Window
            }

             */
        }
    }

    public void LoadAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());

        controller.loadData(file);
        /*
        try {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

         */
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void update() {
        trackViewController.update();
        genreViewController.update();
    }
}

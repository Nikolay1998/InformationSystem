package view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddDialog {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Duration;

    @FXML
    private TextField Name;

    @FXML
    private TextField Author;

    @FXML
    private TextField Album;

    @FXML
    private TextField Genre;

    private Stage dialogStage;
    private Controller controller;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    @FXML
    void initialize() {
    }
    @FXML
    void Cancel(ActionEvent event)
    {
        dialogStage.close();
    }

    @FXML
    void OK(ActionEvent event)
    {
        String title = this.Name.getText();
        String duration = this.Duration.getText();
        String album = this.Album.getText();
        String genre = this.Genre.getText();
        String performer = this.Author.getText();
        controller.addTrack(null, title, performer, album, genre, Integer.valueOf(duration));
        dialogStage.close();
    }
}

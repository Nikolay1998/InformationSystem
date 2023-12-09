package view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddDialogController {

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
    public void Cancel(ActionEvent event)
    {
        dialogStage.close();
    }

    @FXML
    public void OK(ActionEvent event)
    {
        if (isValidInput())
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
    private boolean isValidInput()
    {
        StringBuilder errorMessage = new StringBuilder();
        if (this.Name.getText() == null || this.Name.getText().length() == 0)
            errorMessage.append("Некорретное ввод имени\n");
        if (this.Album.getText() == null || this.Album.getText().length() == 0)
            errorMessage.append("Некорретный ввод альбома\n");
        if (this.Author.getText() == null || this.Author.getText().length() == 0)
            errorMessage.append("Некоретный ввод исполнителя\n");
        if (this.Genre.getText() == null || this.Genre.getText().length() == 0)
            errorMessage.append("Некорретный ввод жанра\n");
        if (this.Duration.getText() == null || this.Duration.getText().length() == 0)
            errorMessage.append("Некорретный ввод длительности");
        else {
            try
            {
                Integer.valueOf(Duration.getText());
            }
            catch (NumberFormatException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Неправильно введеные поля");
                alert.setHeaderText("Пожалуйста, повторите ввод длительности трека");
                alert.setContentText("При вводе длительности вводите цифры");
                alert.showAndWait();
                return false;
            }
        }
        if (errorMessage.length() == 0)
            return true;
        else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Неправильно введеные поля");
                alert.setHeaderText("Пожалуйста, повторите ввод данных полей");
                alert.setContentText(errorMessage.toString());
                alert.showAndWait();
                return false;
            }
    }
}

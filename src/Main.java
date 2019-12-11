import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import model.GenreModel;
import model.TrackModel;
import view.GenreViewController;
import view.RootController;
import view.TrackViewController;

public class Main extends Application {

    //public final static int POP_ID = 0;
    //public final static int ROCK_ID = 1;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxml = new FXMLLoader();
        fxml.setLocation(getClass().getResource("view/Root.fxml"));
        Parent root = fxml.load();
        RootController rootController = fxml.getController();
        TrackViewController trackViewController = rootController.getTrackViewController();
        GenreViewController genreViewController = rootController.getGenreViewController();
        TrackModel trackModel = new TrackModel();
        GenreModel genreModel = new GenreModel();
        rootController.setTrackModel(trackModel);
        rootController.setGenreModel(genreModel);
        trackViewController.setModel(trackModel);
        genreViewController.setModel(genreModel);
        Controller controller = new Controller(trackModel, genreModel);
        rootController.setController(controller);
        trackViewController.setController(controller);
        genreViewController.setController(controller);
        primaryStage.setTitle("Music System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        TextFieldTableCell.forTableColumn();


    }
}


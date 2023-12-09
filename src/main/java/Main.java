import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import model.GenreModel;
import model.TrackModel;
import net.Server;
import net.ServerCommands;
import net.SocketW;
import view.GenreViewController;
import view.RootController;
import view.TrackViewController;

public class Main extends Application {

    private static final int PORT = 1025;
    private static final String IP = "localhost";


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxml = new FXMLLoader();
        fxml.setLocation(getClass().getClassLoader().getResource("Root.fxml"));
        Parent root = fxml.load();

        Server server = new Server(IP, PORT);
        server.connect();
        server.registerCallback(message -> {
            switch (message.getCommand()) {
                case ServerCommands.ADD_GENRE_SUCCESS:
                    break;
            }
        });


        RootController rootController = fxml.getController();
        TrackViewController trackViewController = rootController.getTrackViewController();
        GenreViewController genreViewController = rootController.getGenreViewController();

        TrackModel trackModel = new TrackModel();
        GenreModel genreModel = new GenreModel();

        rootController.setTrackModel(trackModel);
        rootController.setGenreModel(genreModel);
        trackViewController.setModel(trackModel);
        genreViewController.setModel(genreModel);

        SocketW socketW = new SocketW(IP, PORT);

        Controller controller = new Controller(trackModel, genreModel, socketW);
        rootController.setController(controller);
        trackViewController.setController(controller);
        genreViewController.setController(controller);

        socketW.connect();

        primaryStage.setTitle("Music System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        TextFieldTableCell.forTableColumn();

    }
}


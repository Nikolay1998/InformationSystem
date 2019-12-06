import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    //public final static int POP_ID = 0;
    //public final static int ROCK_ID = 1;


    public static void main(String[] args){
        launch(args);
        /*ArrayList<Genre> genres = new ArrayList<>();
        ArrayList<TrackDataObject> tracks  = new ArrayList<>();
        genres.add(new Genre("Pop"));
        genres.add(new Genre("Rock"));

        tracks.add(new TrackDataObject("Когда?","источник","Single", genres.get(ROCK_ID)));
        tracks.add(new TrackDataObject("Seventeen","Peach Pit","Sweat FA", genres.get(ROCK_ID)));

        RepositoryModel repositoryModel = new RepositoryModel(tracks,genres);

        TrackViewController controller = new TrackViewController();
        controller.setModel(repositoryModel);
        controller.saveData();
        controller.loadData();

         */
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       /*
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("view/TrackList.fxml").openStream());
        TrackViewController trackViewController = (TrackViewController) fxmlLoader.getController();
        TrackModel trackModel = new TrackModel();
        trackViewController.setModel(trackModel);
        trackViewController.setController(new Controller(trackModel));
        primaryStage.setTitle("Music System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        TextFieldTableCell.forTableColumn();
        */

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("view/Root.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}


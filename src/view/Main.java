package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public final static int POP_ID = 0;
    public final static int ROCK_ID = 1;


    public static void main(String[] args){
        launch(args);
        /*ArrayList<Genre> genres = new ArrayList<>();
        ArrayList<Track> tracks  = new ArrayList<>();
        genres.add(new Genre("Pop"));
        genres.add(new Genre("Rock"));

        tracks.add(new Track("Когда?","источник","Single", genres.get(ROCK_ID)));
        tracks.add(new Track("Seventeen","Peach Pit","Sweat FA", genres.get(ROCK_ID)));

        RepositoryModel repositoryModel = new RepositoryModel(tracks,genres);

        controller controller = new controller(repositoryModel);
        controller.saveData();
         */




    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TrackList.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
}

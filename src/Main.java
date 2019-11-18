import Controller.Controller;
import Model.RepositoryModel;
import Utils.Genre;
import Utils.Track;

import java.util.ArrayList;

public class Main {

    public final static int POP_ID = 0;
    public final static int ROCK_ID = 1;


    public static void main(String[] args) {
        ArrayList<Genre> genres = new ArrayList<>();
        ArrayList<Track> tracks  = new ArrayList<>();
        genres.add(new Genre("Pop"));
        genres.add(new Genre("Rock"));

        tracks.add(new Track("Когда?","источник","Single", genres.get(ROCK_ID)));
        tracks.add(new Track("Seventeen","Peach Pit","Sweat FA", genres.get(ROCK_ID)));

        RepositoryModel repositoryModel = new RepositoryModel(tracks,genres);

        Controller controller = new Controller(repositoryModel);
        controller.saveData();
    }

}


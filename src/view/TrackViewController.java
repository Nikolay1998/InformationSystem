package view;

import controller.Controller;
import data.TrackDataObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Event;
import model.TrackModel;
import javafx.fxml.Initializable;
import view.DTO.Adapter;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class TrackViewController implements Initializable, EventListener {

    @FXML
    private TableView<TrackView> trackListTable;
    @FXML
    private TableColumn<TrackView, String> trackColumn;
    @FXML
    private TableColumn<TrackView, Integer> durationColumn;
    @FXML
    private TableColumn<TrackView, String> authorColumn;
    @FXML
    private TableColumn<TrackView, String> genreColumn;
    @FXML
    private TableColumn<TrackView, String> albumColumn;
    @FXML
    private TextField trackLabelField;
    @FXML
    private TextField durationField;
    @FXML
    private TextField performerField;
    @FXML
    private TextField genreField;
    @FXML
    private TextField albumField;

    private Controller controller;

    //private Adapter adapter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TrackModel trackModel = new TrackModel();
        //adapter = new Adapter(trackModel);
        controller = new Controller(trackModel);
        trackModel.subscribe(this);
        trackListTable.getItems().addAll(Adapter.toTrackViewList(controller.getAllTracks()));
        //trackListTable.getItems().addAll(adapter.getAllTracks());
    }

    public void loadData() {

    }
/*
    public void saveData() {
        ArrayList<TrackDataObject> tracks = (ArrayList<TrackDataObject>)adapter.getAllTracks();
        ArrayList<Genre> genres = (ArrayList<Genre>) adapter.getAllGenres();
        try {
            FileWriter fileWriter = new FileWriter("Data.txt");
            fileWriter.write(tracks.size());
            TrackDataObject track;
            for (TrackDataObject value : tracks) {
                track = value;
                fileWriter.write(" " + track.getTitle() + " " + track.getPerformer() + " " + track.getAlbum() + " " + track.getGenre().getTitle());
            }
            Genre genre;
            fileWriter.write(genres.size());
            for (Genre value : genres) {
                genre = value;
                fileWriter.write(" " + genre.getTitle());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 */

    public void addTrack(TrackDataObject trackDataObject) {

    }

    public void deleteTrack(TrackDataObject trackDataObject) {

    }

    public void changeTrack(TrackDataObject trackDataObject) {

    }

    public void browsingTrack() {

    }

    public void updateModel() {

    }

    public void addNewTrackAction(ActionEvent actionEvent) {
        //достаём значения которые вбил пользователь:
        String title = trackLabelField.getText();
        String duration = durationField.getText();
        String performer = performerField.getText();
        String album = albumField.getText();
        String genre = genreField.getText();

        TrackView newTrack = new TrackView(null, title, performer, album, genre, Integer.valueOf(duration)); //adapter.addTrack(title, performer, album, genre, Integer.valueOf(duration)); //Вызываем у адаптера метод создания нового трека
        controller.addTrack(Adapter.toTrackDTO(newTrack));

    }

    public void deleteTrackAction(ActionEvent actionEvent) {
        int selectedIndex = trackListTable.getSelectionModel().getSelectedIndex();
        TrackView trackView = trackListTable.getItems().get(selectedIndex);
        controller.removeTrack(trackView.getId());
    }

    public void updateTrackName(TableColumn.CellEditEvent<TrackView, String> trackViewStringCellEditEvent) {
    }

    @Override
    public void update(Event event, String id) {
        switch (event){
            case DELETETRACK:
                System.out.println("DeleteTrack " + id);
                for(TrackView trackView : trackListTable.getItems()){
                    if(trackView.getId().equals(id)){
                        trackListTable.getItems().remove(trackView);
                        break;
                    }
                }
                break;
            case ADDTRACK:
                System.out.println("AddNewTrack");
                trackListTable.getItems().add(Adapter.toTrackView(controller.getTrack(id)));
                break;

        }
    }
}

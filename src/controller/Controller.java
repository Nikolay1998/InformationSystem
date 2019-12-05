package controller;

import data.TrackDataObject;
import model.GenreModel;
import model.TrackModel;

import java.io.*;

public class Controller {
    private TrackModel trackModel;
    private GenreModel genreModel;

    public Controller(TrackModel trackModel) {
        this.trackModel = trackModel;
    }

    public void addTrack(String id, String title, String performer, String album, String genreTitle, Integer duration){
        trackModel.addTrack(id, title, performer, album, genreTitle, duration);
    }

    public void removeTrack(String id) {
        trackModel.removeTrack(id);
    }

    public void updateTrackTitle(TrackDataObject track, String newTitle) {
        trackModel.setTitleTrack(track.getId(), newTitle);
    }

    public void saveData(File file) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            SaveLoadService.getInstance().save(out, (Serializable) trackModel.getAllTracks());
        }
    }


}

package controller;

import data.GenreDataObject;
import data.TrackDataObject;
import model.GenreModel;
import model.TrackModel;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Controller {
    private TrackModel trackModel;
    private GenreModel genreModel;

    public Controller(TrackModel trackModel, GenreModel genreModel) {
        this.trackModel = trackModel;
        this.genreModel = genreModel;
    }

    public void addTrack(String id, String title, String performer, String album, String genreTitle, Integer duration) {
        GenreDataObject genre = genreModel.addGenre(genreTitle);
        trackModel.addTrack(id, title, performer, album, genre, duration);
    }

    public void addGenre(String Genre) {
        genreModel.addGenre(Genre);
    }

    public void removeTrack(String id) {
        trackModel.removeTrack(id);
    }

    public void removeGenre(String id) {
        trackModel.removeTrackByGenreId(id);
        genreModel.removeGenre(id);
    }

    public void updateTrackTitle(TrackDataObject track, String newTitle) {
        trackModel.setTitleTrack(track.getId(), newTitle);
    }

    public void updateGenre(GenreDataObject genre, String newGenre) {
        genreModel.setGenre(genre.getId(), newGenre);
    }

    public void saveData(File file) {
        SaveLoadService.getInstance().save(file, trackModel.getAllTracks());
    }

    public void loadData(File file) {
        List<TrackDataObject> addedTracks = SaveLoadService.getInstance().load(file);
        trackModel.addToArrTrack(addedTracks);
        List<GenreDataObject> addedGenres = new LinkedList<>();
        for (TrackDataObject trackDataObject : addedTracks) {
            addedGenres.add(trackDataObject.getGenre());
        }
        genreModel.addToArrGenre(addedGenres);
    }

    public void changeTrack(String id, String title, String performer, String album, String genreTitle, Integer duration) {
        GenreDataObject genre = genreModel.addGenre(genreTitle);
        trackModel.changeTrack(id, title, performer, album, genre, duration);
    }

    public void changeGenre(String id, String changetTitle) {
        genreModel.changeGenre(id, changetTitle);
    }
}

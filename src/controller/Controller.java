package controller;

import data.GenreDataObject;
import data.TrackDataObject;
import model.GenreModel;
import model.TrackModel;
import net.Server;
import net.ServerCommands;
import net.ServerMessage;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Controller {
    private TrackModel trackModel;
    private GenreModel genreModel;
    private Server server;

    public Controller(TrackModel trackModel, GenreModel genreModel, Server server) {
        this.trackModel = trackModel;
        this.genreModel = genreModel;
        this.server = server;
    }

    public void addTrack(String id, String title, String performer, String album, String genreTitle, Integer duration) {
        GenreDataObject genre = genreModel.addGenre(genreTitle);
        trackModel.addTrack(id, title, performer, album, genre, duration);
    }

    public void addGenre(String Genre) {
        server.sendMessage(new ServerMessage(ServerCommands.ADD_GENRE, new GenreDataObject(Genre)));
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
        //List<GenreDataObject> addedGenres = new LinkedList<>();
        HashSet<GenreDataObject> addedGenres = new HashSet<>();
        for (TrackDataObject trackDataObject : addedTracks) {
            addedGenres.add(trackDataObject.getGenre());
            //addedGenres.add(trackDataObject.getGenre());
        }
        genreModel.addToArrGenre(new LinkedList<>(addedGenres));

    }

    public void changeTrack(String id, String title, String performer, String album, String genreTitle, Integer duration) {
        GenreDataObject genre = genreModel.addGenre(genreTitle);
        trackModel.changeTrack(id, title, performer, album, genre, duration);
    }

    public void changeGenre(String id, String changetTitle) {
        genreModel.changeGenre(id, changetTitle);
    }
}

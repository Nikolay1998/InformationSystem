package controller;

import data.GenreDataObject;
import data.TrackDataObject;
import model.FullModel;
import model.GenreModel;
import model.TrackModel;
import net.Server;
import net.ServerCommands;
import net.ServerMessage;
import net.SocketW;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Controller implements DataUpdateListener {
    private TrackModel trackModel;
    private GenreModel genreModel;
    private SocketW socketW;

    public Controller(TrackModel trackModel, GenreModel genreModel, SocketW socketW) {
        this.trackModel = trackModel;
        this.genreModel = genreModel;
        this.socketW = socketW;
        socketW.subscribe(this);
    }

    public void addTrack(String id, String title, String performer, String album, String genreTitle, Integer duration) {
        TrackDataObject trackDataObject = new TrackDataObject(id, title, performer, album, new GenreDataObject(genreTitle), duration);
        socketW.addTrack(trackDataObject);
    }

    public void addGenre(String Genre) {
        socketW.sendMessage(new ServerMessage(ServerCommands.ADD_GENRE, new GenreDataObject(Genre)));
    }

    public void removeTrack(String id) {
        socketW.removeTrack(id);
    }

    public void removeGenre(String id) {
        trackModel.removeTrackByGenreId(id);
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
        trackModel.updateTrackArr(addedTracks);
        //List<GenreDataObject> addedGenres = new LinkedList<>();
        HashSet<GenreDataObject> addedGenres = new HashSet<>();
        for (TrackDataObject trackDataObject : addedTracks) {
            addedGenres.add(trackDataObject.getGenre());
            //addedGenres.add(trackDataObject.getGenre());
        }
        genreModel.updateGenreArr(new LinkedList<>(addedGenres));

    }

    public void changeTrack(String id, String title, String performer, String album, String genreTitle, Integer duration) {
        GenreDataObject genre = genreModel.addGenre(genreTitle);
        trackModel.changeTrack(id, title, performer, album, genre, duration);
    }

    public void changeGenre(String id, String changetTitle) {
        genreModel.changeGenre(id, changetTitle);
    }

    @Override
    public void update(FullModel model) {
        trackModel.updateTrackArr(model.getTackListArr());
        System.out.println(trackModel.getAllTracks().toString());
        genreModel.updateGenreArr(model.getGenreListArr());
    }

}

package controller;

import model.TrackModel;
import view.TrackView;

import java.util.List;
import java.util.stream.Collectors;

public class Adapter {
    TrackModel trackModel;

    Adapter(TrackModel trackModel){
        this.trackModel = trackModel;
    }

    List<TrackView> getAllTracks() {
        return trackModel
                .getAllTracks()
                .stream()
                .map(TrackView::new)
                .collect(Collectors.toList()); // достаем из репозитория список треков,
        // оборачиваем его в стрим, вызываем для каждого элемнта создание нового TrackView на основе Track,
        //оборачиваем обратно в лист
    }


    public TrackView addTrack(String title, String performer, String album, String genre, Integer duration) {
        //адаптер вызвыает создание нового трека у RepositoryModel, оборачивает его в TrackView и возвращает контроллеру TrackView
        return new TrackView(trackModel.addTrack(title, performer, album, genre, duration));
    }

}

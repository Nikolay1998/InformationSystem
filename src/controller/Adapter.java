package controller;

import model.RepositoryModel;
import utils.Track;
import view.TrackView;

import java.util.List;
import java.util.stream.Collectors;

public class Adapter {
    RepositoryModel repositoryModel;

    Adapter(RepositoryModel repositoryModel){
        this.repositoryModel = repositoryModel;
    }

    List<TrackView> getAllTracks() {
        return repositoryModel
                .getAllTracks()
                .stream()
                .map(TrackView::new)
                .collect(Collectors.toList()); // достаем из репозитория список треков,
        // оборачиваем его в стрим, вызываем для каждого элемнта создание нового TrackView на основе Track,
        //оборачиваем обратно в лист
    }


    public TrackView addTrack(String title, String performer, String album, String genre, Integer duration) {
        //адаптер вызвыает создание нового трека у RepositoryModel, оборачивает его в TrackView и возвращает контроллеру TrackView
        return new TrackView(repositoryModel.addTrack(title, performer, genre, album, duration));
    }
}

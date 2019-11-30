package controller;

import data.TrackDataObject;
import model.GenreModel;
import model.TrackModel;
import view.DTO.Adapter;
import view.DTO.TrackDTO;

import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    private TrackModel trackModel;
    private GenreModel genreModel;

    public Controller(TrackModel trackModel) {
        this.trackModel = trackModel;
    }

    public void addTrack(TrackDTO trackDTO){
        TrackDataObject trackDO = Adapter.toTrackDataObject(trackDTO);
        trackModel.addTrack(trackDO.getId(), trackDO.getTitle(), trackDO.getPerformer(),trackDO.getAlbum(),trackDO.getGenre().getTitle(), trackDO.getDuration());
    }

    public List<TrackDTO> getAllTracks(){
        return Adapter.toTrackDTOList(trackModel.getAllTracks());
    }

    public TrackDTO getTrack(String id){
        return Adapter.toTrackDTO(trackModel.getTrack(id));
    }

    public void removeTrack(String id) {
        trackModel.removeTrack(id);
    }
}

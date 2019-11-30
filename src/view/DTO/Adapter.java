package view.DTO;

import data.Genre;
import data.TrackDataObject;
import model.TrackModel;
import view.DTO.TrackDTO;
import view.TrackView;

import java.util.List;
import java.util.stream.Collectors;

public class Adapter {

    public static TrackDataObject toTrackDataObject(TrackDTO trackDTO){
        return new TrackDataObject(trackDTO.getId(), trackDTO.getTitle(), trackDTO.getPerformer(), trackDTO.getAlbum(), trackDTO.getGenre(), trackDTO.getDuration());
    }

    public static TrackView toTrackView(TrackDTO trackDTO){
        return new TrackView(trackDTO.getId(), trackDTO.getTitle(), trackDTO.getPerformer(), trackDTO.getAlbum(), trackDTO.getGenre().getTitle(), trackDTO.getDuration());
    }

    public static TrackDTO toTrackDTO(TrackDataObject trackDO){
        return new TrackDTO(trackDO.getId(), trackDO.getTitle(), trackDO.getPerformer(), trackDO.getAlbum(), trackDO.getGenre(), trackDO.getDuration());
    }

    public static TrackDTO toTrackDTO(TrackView trackView){
        return new TrackDTO(trackView.getId(), trackView.getTitle(), trackView.getPerformer(),
                trackView.getAlbum(), new Genre(trackView.getGenre()), trackView.getDuration()); //toDO:genre
    }

    public static List<TrackDTO> toTrackDTOList(List<TrackDataObject> trackDOList){
        return   trackDOList.stream()
                .map(TrackDTO::new)
                .collect(Collectors.toList());
    }

    public static List<TrackView> toTrackViewList(List<TrackDTO> trackDTOList){
        return   trackDTOList.stream()
                .map(TrackView::new)
                .collect(Collectors.toList());
    }

}

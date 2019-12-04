package data;

import java.util.function.Predicate;

public class TrackDataObjects {
    static public Predicate<TrackDataObject> startWithPredicate(String searchString) {
        return new Predicate<TrackDataObject>() {
            @Override
            public boolean test(TrackDataObject trackDataObject) {
                return trackDataObject.getTitle().toLowerCase().startsWith(searchString.toLowerCase());
            }
        };
    }

    public static Predicate<TrackDataObject> endsWithPredicate(String searchString) {
        return new Predicate<TrackDataObject>() {
            @Override
            public boolean test(TrackDataObject trackDataObject) {
                return trackDataObject.getTitle().toLowerCase().endsWith(searchString.toLowerCase());
            }
        };
    }



    static public Predicate<TrackDataObject> fullEqual(String searchString) {
        return new Predicate<TrackDataObject>() {
            @Override
            public boolean test(TrackDataObject trackDataObject) {
                return trackDataObject.getTitle().toLowerCase().equals(searchString.toLowerCase());
            }
        };
    }

    public static Predicate<TrackDataObject> containsPredicate(String searchString) {
        return new Predicate<TrackDataObject>() {
            @Override
            public boolean test(TrackDataObject trackDataObject) {
                return trackDataObject.getTitle().toLowerCase().contains(searchString.toLowerCase());
            }
        };
    }
}

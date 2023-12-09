package data;

import java.util.function.Predicate;

public class GenreDataObjects {
    static public Predicate<GenreDataObject> startWithPredicate(String searchString) {
        return new Predicate<GenreDataObject>() {
            @Override
            public boolean test(GenreDataObject genreDataObject) {
                return genreDataObject.getTitle().toLowerCase().startsWith(searchString.toLowerCase());
            }
        };
    }
    public static Predicate<GenreDataObject> endsWithPredicate(String searchString) {
        return new Predicate<GenreDataObject>() {
            @Override
            public boolean test(GenreDataObject genreDataObject) {
                return genreDataObject.getTitle().toLowerCase().endsWith(searchString.toLowerCase());
            }
        };
    }



    static public Predicate<GenreDataObject> fullEqual(String searchString) {
        return new Predicate<GenreDataObject>() {
            @Override
            public boolean test(GenreDataObject genreDataObject) {
                return genreDataObject.getTitle().toLowerCase().equals(searchString.toLowerCase());
            }
        };
    }

    public static Predicate<GenreDataObject> containsPredicate(String searchString) {
        return new Predicate<GenreDataObject>() {
            @Override
            public boolean test(GenreDataObject genreDataObject) {
                return genreDataObject.getTitle().toLowerCase().contains(searchString.toLowerCase());
            }
        };
    }
}

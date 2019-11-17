package Utils;

public class Genre {
    private String title;
    public Genre(String title)
    {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public boolean equals(Object object) {
        return title.equals(object);
    }
}

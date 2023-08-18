package model.schema;

public class Genre {
    private int genreId;
    private String genreName;
    
    public Genre(int genreId, String genreName) {
        this.genreId = genreId;
        this.genreName = genreName;
    }
    
    public int getGenreId() {
        return genreId;
    }
    public String getGenreName() {
        return genreName;
    }

}
